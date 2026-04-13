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
import logico.Cliente;
import logico.Persona;
import logico.Cliente.Cat;

public class ListarClientesLeury extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object raw[];
	private static DefaultTableModel model;
	private Cliente selected = null;
	public static JButton btnActualizar;
	public static JButton btnInhabilitar;
	private JButton btnCancelar;
	private JScrollPane scrollPane;
	private static JComboBox<String> cbxTipoCliente;
	private static JRadioButton rdbtnInactivos;

	public static void main(String[] args) {
		try {
			ListarClientesLeury dialog = new ListarClientesLeury();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ListarClientesLeury() {
		setTitle("Lista de Clientes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarClientesLeury.class.getResource("/Imagenes/AlticeLogoVentanas.PNG")));
		setBounds(100, 100, 1077, 640);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		
		contentPanel.setBackground(new Color(29, 41, 59)); 
		contentPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(38, 110, 995, 401); 
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
							String rnc = table.getValueAt(index, 0).toString();
							selected = (Cliente) Altice.getInstance().buscarPersonaByRNC(rnc);

							if (selected != null) {
								if (selected.isEstado()) {
									btnActualizar.setEnabled(true);
									btnInhabilitar.setEnabled(true);
								} else {
									btnInhabilitar.setEnabled(false);
									btnActualizar.setEnabled(false);
								}
								if (e.getClickCount() == 2) {
									RegistrarClienteLeury det = new RegistrarClienteLeury(selected);
									det.modoDetalle();
									det.setModal(true);
									det.setVisible(true);
								}
							}
						}
					}
				});
				scrollPane.setViewportView(table);
			}
		}

		JLabel lblTitulo = new JLabel("Gestión y listado de clientes registrados");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitulo.setBounds(38, 25, 505, 31);
		contentPanel.add(lblTitulo);

		JLabel lblFiltrar = new JLabel("Filtrar por Tipo:");
		lblFiltrar.setForeground(Color.WHITE);
		lblFiltrar.setBounds(780, 25, 100, 14);
		contentPanel.add(lblFiltrar);

		cbxTipoCliente = new JComboBox<String>();
		cbxTipoCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadHeaders();
				btnActualizar.setEnabled(false);
				btnInhabilitar.setEnabled(false);
				selected = null;
			}
		});
		cbxTipoCliente.setModel(new DefaultComboBoxModel<String>(new String[] {"<<Seleccione>>", "Físico", "Jurídico"}));
		cbxTipoCliente.setBounds(880, 21, 150, 22);
		contentPanel.add(cbxTipoCliente);

		rdbtnInactivos = new JRadioButton("Mostrar Inactivos");
		rdbtnInactivos.setBackground(new Color(29, 41, 59));
		rdbtnInactivos.setForeground(Color.WHITE);
		rdbtnInactivos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadClientes();
			}
		});
		rdbtnInactivos.setBounds(880, 55, 150, 23);
		contentPanel.add(rdbtnInactivos);

		JLabel lblAyuda = new JLabel("Doble clic para ver el detalle completo del cliente");
		lblAyuda.setForeground(Color.LIGHT_GRAY);
		lblAyuda.setBounds(38, 515, 300, 14);
		contentPanel.add(lblAyuda);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnInhabilitar = new JButton("Inhabilitar");
				btnInhabilitar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (selected != null) {
							int option = JOptionPane.showConfirmDialog(null, "¿Desea inhabilitar al cliente: " + selected.getNombre() + "?", "Confirmación", JOptionPane.WARNING_MESSAGE);
							if (option == JOptionPane.OK_OPTION) {
								selected.setEstado(false); 
								loadClientes();
								btnInhabilitar.setEnabled(false);
								btnActualizar.setEnabled(false);
							}
						}
					}
				});
				btnInhabilitar.setEnabled(false);
				buttonPane.add(btnInhabilitar);
			}
			{
				btnActualizar = new JButton("Actualizar Cliente");
				btnActualizar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (selected != null) {
							RegistrarClienteLeury update = new RegistrarClienteLeury(selected);
							update.setModal(true);
							update.setVisible(true);
							table.clearSelection();
							selected = null;
							btnActualizar.setEnabled(false);
							btnInhabilitar.setEnabled(false);
							loadClientes();
						}
					}
				});
				btnActualizar.setEnabled(false);
				buttonPane.add(btnActualizar);
			}
			{
				btnCancelar = new JButton("Cerrar");
				btnCancelar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(btnCancelar);
			}
		}
		loadHeaders();
	}

	public static void loadClientes() {
		model.setRowCount(0);
		raw = new Object[table.getColumnCount()];
		String filtro = cbxTipoCliente.getSelectedItem().toString();
		
		boolean estadoDeseado;
		if (rdbtnInactivos.isSelected()) {
			estadoDeseado = false;
		} else {
			estadoDeseado = true;
		}

		for (Persona temp : Altice.getInstance().getMisHumanos()) {
			if (temp instanceof Cliente) {
				Cliente cli = (Cliente) temp;
				boolean cumpleFiltro = false;

				if (filtro.equalsIgnoreCase("<<Seleccione>>")) {
					cumpleFiltro = true;
				} else if (filtro.equalsIgnoreCase("Físico")) {
					if (cli.getCategoria() == Cat.FISICO) {
						cumpleFiltro = true;
					}
				} else if (filtro.equalsIgnoreCase("Jurídico")) {
					if (cli.getCategoria() == Cat.JURIDICO) {
						cumpleFiltro = true;
					}
				}

				if (cumpleFiltro && cli.isEstado() == estadoDeseado) {
					raw[0] = cli.getRnc(); 
					raw[1] = cli.getNombre();
					
					if (cli.getCategoria() == Cat.FISICO) {
						raw[2] = "Físico";
					} else {
						raw[2] = "Jurídico";
					}
					
					raw[3] = cli.getNumeroContacto();
					
					if (cli.isEstado()) {
						raw[4] = "Activo";
					} else {
						raw[4] = "Inactivo";
					}
					
					if (cli.isDeuda()) {
						raw[5] = "Sí ($" + cli.getMontoDeuda() + ")";
					} else {
						raw[5] = "Al día";
					}

					model.addRow(raw);
				}
			}
		}
	}

	public void loadHeaders() {
		String headers[] = {"Cédula/RNC", "Nombre", "Categoría", "Teléfono", "Estado", "Estatus Pago"};
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		scrollPane.setViewportView(table);
		loadClientes();
	}
}