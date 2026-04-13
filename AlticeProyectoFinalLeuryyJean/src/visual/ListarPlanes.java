package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import logico.Altice;
import logico.Plan;
import logico.Plan.Estado;
import logico.Plan.Tipo;

public class ListarPlanes extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object raw[];
	private static DefaultTableModel model;
	private Plan selected = null;
	public static JButton btnActualizar;
	public static JButton btnDescontinuar;
	private JButton btnCancelar;
	private JScrollPane scrollPane;
	private static JComboBox cbxSelecFiltro;
	private static JRadioButton rdbtnDescontinuados;

	public static void main(String[] args) {
		try {
			ListarPlanes dialog = new ListarPlanes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ListarPlanes() {
		setTitle("Listado de Planes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarPlanes.class.getResource("/Imagenes/AlticeLogoVentanas.PNG")));
		setBounds(100, 100, 1077, 640);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPanel.setBackground(new Color(29,41,59));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(38, 118, 995, 393); // Ajustado al ancho total al no tener panel izquierdo
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
						if (index >= 0) {
							String id = table.getValueAt(index, 0).toString();
							selected = Altice.getInstance().buscarPlanByID(id);

							if (selected != null) {
								if (selected.getState() == Estado.VIGENTE) {
									btnActualizar.setEnabled(true);
									btnDescontinuar.setEnabled(true);
								} else {
									btnDescontinuar.setEnabled(false);
									btnActualizar.setEnabled(false);
								}
								if (e.getClickCount() == 2) {
									// Llamada a la ventana de registro en modo detalle o actualización
									RegistrarPlan det = new RegistrarPlan(selected);
									det.setModal(true);
									det.setVisible(true);
									loadPlanes(); // Recargar tras cerrar
								}
							} else {
								selected = null;
								btnActualizar.setEnabled(false);
								btnDescontinuar.setEnabled(false);
							}
						}
					}
				});
				scrollPane.setViewportView(table);
			}
		}

		JLabel lblTitulo = new JLabel("Gestión y listado de planes de servicios");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTitulo.setBounds(38, 40, 505, 31);
		contentPanel.add(lblTitulo);

		JLabel lblFiltrar = new JLabel("Filtrar por:");
		lblFiltrar.setForeground(Color.WHITE);
		lblFiltrar.setBounds(845, 33, 80, 14);
		contentPanel.add(lblFiltrar);

		cbxSelecFiltro = new JComboBox();
		cbxSelecFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadHeaders();
				btnActualizar.setEnabled(false);
				btnDescontinuar.setEnabled(false);
				selected = null;
			}
		});
		cbxSelecFiltro.setModel(new DefaultComboBoxModel(new String[] {"<<Seleccione>>", "Residencial", "Negocios"}));
		cbxSelecFiltro.setBounds(912, 29, 121, 22);
		contentPanel.add(cbxSelecFiltro);

		rdbtnDescontinuados = new JRadioButton("Mostrar Descontinuados");
		rdbtnDescontinuados.setForeground(Color.WHITE);
		rdbtnDescontinuados.setBackground(new Color(29,41,59));
		rdbtnDescontinuados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadPlanes();
			}
		});
		rdbtnDescontinuados.setBounds(850, 65, 183, 23);
		contentPanel.add(rdbtnDescontinuados);

		JLabel lblDobleClick = new JLabel("Doble click para ver detalles del plan");
		lblDobleClick.setForeground(Color.WHITE);
		lblDobleClick.setBounds(38, 513, 250, 14);
		contentPanel.add(lblDobleClick);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnDescontinuar = new JButton("Descontinuar");
				btnDescontinuar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selected != null) {
							int option = JOptionPane.showConfirmDialog(null, "¿Desea realmente descontinuar el Plan: " + selected.getNombreComercial() + "?", "Confirmación", JOptionPane.WARNING_MESSAGE);
							if (option == JOptionPane.OK_OPTION) {
								selected.setState(Estado.DESCONTINUADO);
								loadPlanes();
								btnDescontinuar.setEnabled(false);
								btnActualizar.setEnabled(false);
								JOptionPane.showMessageDialog(null, "Plan descontinuado correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Debe seleccionar un plan de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
						}
					}
				});
				btnDescontinuar.setEnabled(false);
				buttonPane.add(btnDescontinuar);
			}
			{
				btnActualizar = new JButton("Actualizar Plan");
				btnActualizar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selected != null) {
							RegistrarPlan update = new RegistrarPlan(selected);
							update.setModal(true);
							update.setVisible(true);
							table.clearSelection();
							selected = null;
							btnActualizar.setEnabled(false);
							btnDescontinuar.setEnabled(false);
							loadPlanes();
							JOptionPane.showMessageDialog(null, "Plan actualizado correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Debe seleccionar un plan de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
						}
					}
				});
				btnActualizar.setEnabled(false);
				buttonPane.add(btnActualizar);
				getRootPane().setDefaultButton(btnActualizar);
			}
			{
				btnCancelar = new JButton("Cerrar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(btnCancelar);
			}
		}
		loadHeaders();
	}

	public static void loadPlanes() {
		model.setRowCount(0);
		raw = new Object[table.getColumnCount()];
		String seleccion = cbxSelecFiltro.getSelectedItem().toString();
		
		Estado estadoBuscado;
		if (rdbtnDescontinuados.isSelected()) {
			estadoBuscado = Estado.DESCONTINUADO;
		} else {
			estadoBuscado = Estado.VIGENTE;
		}

		for (Plan plan : Altice.getInstance().getMisPlanes()) {
			boolean esTipoBuscado = false;

			if (seleccion.equalsIgnoreCase("<<Seleccione>>")) {
				esTipoBuscado = true;
			} else if (seleccion.equalsIgnoreCase("Residencial") && plan.getType() == Tipo.RESIDENCIAL) {
				esTipoBuscado = true;
			} else if (seleccion.equalsIgnoreCase("Negocios") && plan.getType() == Tipo.NEGOCIOS) {
				esTipoBuscado = true;
			}

			if (esTipoBuscado && plan.getState() == estadoBuscado) {
				raw[0] = plan.getIdPlan();
				raw[1] = plan.getNombreComercial();
				raw[2] = plan.getType().toString();
				raw[3] = plan.getPlanServices().size();
				raw[4] = plan.getPrecioTotal();
				
				if (plan.getState() == Estado.VIGENTE) {
					raw[5] = "Vigente";
				} else {
					raw[5] = "Descontinuado";
				}

				model.addRow(raw);
			}
		}
	}

	public void loadHeaders() {
		String headersPlanes[] = {"ID Plan", "Nombre Comercial", "Tipo", "Cant. Servicios", "Precio Final", "Estado"};
		model.setColumnIdentifiers(headersPlanes);
		table.setModel(model);
		scrollPane.setViewportView(table);
		loadPlanes();
	}
}