package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Altice;
import logico.Empleado;
import logico.Persona;
import logico.Usuario.rol;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarEmpleados extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object raw[];
	private static DefaultTableModel model;
	private Empleado selected =null;
	public static JButton btnActualizar;
	public static JButton btnDespedir;
	private JButton btnCancelar;
	private JScrollPane scrollPane;
	private static JComboBox cbxSelecFiltro;
	private static JRadioButton rdbtnDespedidos;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarEmpleados dialog = new ListarEmpleados();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarEmpleados() {
		setTitle("Lista de Empleados");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarEmpleados.class.getResource("/Imagenes/AlticeLogoVentanas.PNG")));
		setBounds(100, 100, 1077, 640);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(292, 136, 743, 375);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				model = new DefaultTableModel() {
					private static final long serialVersionUID = 1L;
				    @Override
				    public boolean isCellEditable(int row, int column) {
				        return false;
				    }
				};
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index = table.getSelectedRow();

						if (index >=0) {
							String id = table.getValueAt(index, 0).toString();
							selected =  (Empleado) Altice.getInstance().buscarEmpleadoByID(id);

							if(selected!=null) {
								if(selected.isEstado()) {
									btnActualizar.setEnabled(true);
									btnDespedir.setEnabled(true);
								}
								else {
									btnDespedir.setEnabled(false);
									btnActualizar.setEnabled(false);
								}
								if(e.getClickCount()==2) {
									RegistrarEmpleado det = new RegistrarEmpleado(selected);
									det.modoDetalle();
									det.setModal(true);
									det.setVisible(true);
								}
							}
							else {
								selected = null;
								btnActualizar.setEnabled(false);
								btnDespedir.setEnabled(false);
							}
							
						}
						
					}
				});
				scrollPane.setViewportView(table);
			}
		}

		JLabel lblAquSeMuestran = new JLabel("Gestión y listado de empleados");
		lblAquSeMuestran.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblAquSeMuestran.setBounds(10, 11, 505, 31);
		contentPanel.add(lblAquSeMuestran);

		JLabel lblNewLabel_1 = new JLabel("Filtrar por:");
		lblNewLabel_1.setBounds(865, 18, 60, 14);
		contentPanel.add(lblNewLabel_1);

		cbxSelecFiltro = new JComboBox();
		cbxSelecFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadHeaders();
				btnActualizar.setEnabled(false);
				btnDespedir.setEnabled(false);
				selected = null;
			}
		});
		cbxSelecFiltro.setModel(new DefaultComboBoxModel(new String[] {"<<Seleccione>>", "Administrador", "Comercial", "Técnico"}));
		cbxSelecFiltro.setBounds(930, 18, 121, 22);
		contentPanel.add(cbxSelecFiltro);

		rdbtnDespedidos = new JRadioButton("Mostrar Despedidos");
		rdbtnDespedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadEmpleados();
			}
		});
		rdbtnDespedidos.setBounds(832, 50, 161, 23);
		contentPanel.add(rdbtnDespedidos);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(29, 75, 222, 436);
		contentPanel.add(panel);
		panel.setLayout(null);

		JPanel panelTopVentas = new JPanel();
		panelTopVentas.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelTopVentas.setBounds(18, 29, 185, 136);
		panel.add(panelTopVentas);

		JPanel panelTopTecnicos = new JPanel();
		panelTopTecnicos.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelTopTecnicos.setBounds(18, 194, 185, 92);
		panel.add(panelTopTecnicos);

		JPanel panelPeoresTecnicos = new JPanel();
		panelPeoresTecnicos.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPeoresTecnicos.setBounds(18, 315, 185, 92);
		panel.add(panelPeoresTecnicos);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(29, 53, 46, 14);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("Doble click para detalle de empleado");
		lblNewLabel_2.setBounds(537, 513, 218, 14);
		contentPanel.add(lblNewLabel_2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnDespedir = new JButton("Despedir");
				btnDespedir.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
					}
					@Override
					public void mouseExited(MouseEvent e) {
					}
				});
				btnDespedir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(selected != null) {
							int option = JOptionPane.showConfirmDialog(null,"Desea realmente deshabilitar el Empleado con ID:"+ selected.getIdEmpleado(),"Confirmación", JOptionPane.WARNING_MESSAGE);
							if(option == JOptionPane.OK_OPTION) {
								Altice.getInstance().despedirEmpleado(selected);
								loadEmpleados();
								btnDespedir.setEnabled(false);
								btnActualizar.setEnabled(false);
							}
						}
					}
				});
				btnDespedir.setEnabled(false);
				buttonPane.add(btnDespedir);
			}
			{
				btnActualizar = new JButton("Actualizar perfil");
				btnActualizar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(selected != null) {
							RegistrarEmpleado update = new RegistrarEmpleado(selected);
							update.setModal(true);
							update.setVisible(true);
							table.clearSelection();
							selected = null;
							btnActualizar.setEnabled(false);
							btnDespedir.setEnabled(false);
							loadEmpleados();
						}
					}
				});
				btnActualizar.setActionCommand("OK");
				btnActualizar.setEnabled(false);
				buttonPane.add(btnActualizar);
				getRootPane().setDefaultButton(btnActualizar);
			}
			{
				btnCancelar = new JButton("Cancel");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		loadHeaders();
	}

	public static void loadEmpleados() {
		model.setRowCount(0);
		raw = new Object[table.getColumnCount()];
		String selected = cbxSelecFiltro.getSelectedItem().toString();
		boolean estado = !rdbtnDespedidos.isSelected(); //si esta seleccionado buscamos false
		DateTimeFormatter formatoDeFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a"); //formato para fecha establecido, patron dia/mes/anno y hora (formato de 12 horas) 

		for(Persona temp: Altice.getInstance().getMisHumanos()) {
			if(temp instanceof Empleado) {
				Empleado emp = (Empleado) temp;
				boolean esRolBuscado = false; 

				rol rolEmpleado = emp.getUser().getRol();

				//si es <<seleccione>>
				if (selected.equalsIgnoreCase("<<Seleccione>>")){
					esRolBuscado = true; //agrega todos al arreglo
				}
				else if (selected.equalsIgnoreCase("Administrador") && rolEmpleado == rol.ADMINISTRADOR){
					esRolBuscado = true;
				}
				else if (selected.equalsIgnoreCase("Comercial") && rolEmpleado == rol.COMERCIAL){
					esRolBuscado = true;
				}
				else if (selected.equalsIgnoreCase("Técnico") && rolEmpleado == rol.TECNICO){
					esRolBuscado = true;
				}

				if(esRolBuscado && emp.isEstado() == estado) {
					raw[0]= emp.getIdEmpleado();
					raw[1] = emp.getNombre();
					raw[2] = rolEmpleado.toString();
					raw[3] = emp.getDepartamento().toString();
					if(emp.isEstado()) {
						raw[4] = "Activo"; 
					}
					else {
						raw[4] = "Despedido"; 
					}

					LocalDateTime fecha = emp.getUser().getUltimoIngreso();
					if (fecha != null) {
						raw[5] = fecha.format(formatoDeFecha); 
					} else {
						raw[5] = "Nunca"; //Por si no ha entrado
					}

					model.addRow(raw);

				}
			}
		}



	}

	public void loadHeaders() {
		String headersEmpleados[]= {"ID Empleado","Nombre","Rol","Departamento","Estado","Último Ingreso"};
		model.setColumnIdentifiers(headersEmpleados);
		table.setModel(model);
		scrollPane.setViewportView(table);
		loadEmpleados();

	}
}
