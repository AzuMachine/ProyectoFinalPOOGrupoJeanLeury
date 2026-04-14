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
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import logico.Altice;
import logico.Contrato;
import logico.Contrato.Estado;

public class ListarContratos extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object raw[];
	private static DefaultTableModel model;
	private Contrato selected = null;
	public static JButton btnModificar;
	public static JButton btnCancelarContrato;
	private JButton btnSalir;
	private JScrollPane scrollPane;
	private static JComboBox cbxSelecFiltro;
	private static JRadioButton rdbtnCancelados;
	private JTextField txtBusqueda;
	private JButton btnBuscar;


	public ListarContratos() {
		setTitle("Lista de Contratos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarContratos.class.getResource("/Imagenes/AlticeLogoVentanas.PNG")));
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
			scrollPane.setBounds(33, 136, 994, 375);
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
							String num = table.getValueAt(index, 0).toString();
							selected = Altice.getInstance().buscarContratoByNumero(num);

							if (selected != null) {
								if (selected.getEstado() != Estado.CANCELADO) {
									btnModificar.setEnabled(true);
									btnCancelarContrato.setEnabled(true);
								} else {
									btnModificar.setEnabled(false);
									btnCancelarContrato.setEnabled(false);
								}
								
								if (e.getClickCount() == 2) {
									// Aquí abrirías la vista de detalles (puedes reusar RegistrarContrato en modo lectura)
									RegistrarContrato det = new RegistrarContrato(selected);
									det.setVisible(true);
								}
							} else {
								selected = null;
								btnModificar.setEnabled(false);
								btnCancelarContrato.setEnabled(false);
							}
						}
					}
				});
				scrollPane.setViewportView(table);
			}
		}

		JLabel lblTitulo = new JLabel("Gestión y listado de contratos");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setBounds(33, 25, 412, 31);
		contentPanel.add(lblTitulo);

		JLabel lblFiltrar = new JLabel("Filtrar por:");
		lblFiltrar.setForeground(Color.WHITE);
		lblFiltrar.setBounds(906, 77, 60, 14);
		contentPanel.add(lblFiltrar);

		cbxSelecFiltro = new JComboBox();
		cbxSelecFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadHeaders();
				btnModificar.setEnabled(false);
				btnCancelarContrato.setEnabled(false);
				selected = null;
			}
		});
		cbxSelecFiltro.setModel(new DefaultComboBoxModel(new String[] {"<<Seleccione>>", "Vigente", "Pendiente", "Suspendido"}));
		cbxSelecFiltro.setBounds(906, 103, 121, 22);
		contentPanel.add(cbxSelecFiltro);

		rdbtnCancelados = new JRadioButton("Mostrar Cancelados");
		rdbtnCancelados.setForeground(Color.WHITE);
		rdbtnCancelados.setBackground(new Color(29, 41, 59));
		rdbtnCancelados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadContratos();
			}
		});
		rdbtnCancelados.setBounds(667, 106, 143, 23);
		contentPanel.add(rdbtnCancelados);

		JLabel lblAyuda = new JLabel("Doble click para detalle de contrato");
		lblAyuda.setForeground(Color.WHITE);
		lblAyuda.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAyuda.setBounds(421, 513, 218, 14);
		contentPanel.add(lblAyuda);

		txtBusqueda = new JTextField();
		txtBusqueda.setBounds(33, 105, 160, 20);
		contentPanel.add(txtBusqueda);
		txtBusqueda.setColumns(10);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num = txtBusqueda.getText();
				Contrato encontrado = Altice.getInstance().buscarContratoByNumero(num);

				if (encontrado != null) {
					Object[] opciones = {"Ver Detalles", "Seleccionar", "Cancelar"};
					int seleccion = JOptionPane.showOptionDialog(null, 
							"Contrato encontrado. Cliente: " + encontrado.getCli().getNombre(), 
							"Búsqueda", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

					if (seleccion == 0) {
						RegistrarContrato det = new RegistrarContrato(encontrado);
						det.setVisible(true);
					}
					if (seleccion == 1) {
						selected = encontrado;
						btnModificar.setEnabled(selected.getEstado() != Estado.CANCELADO);
						btnCancelarContrato.setEnabled(selected.getEstado() != Estado.CANCELADO);
					}
				} else {
					JOptionPane.showMessageDialog(null, "No se encontró el contrato: " + num, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnBuscar.setBounds(215, 102, 89, 23);
		contentPanel.add(btnBuscar);

		JLabel lblBusqueda = new JLabel("Buscar Contrato por Número");
		lblBusqueda.setForeground(Color.WHITE);
		lblBusqueda.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBusqueda.setBounds(33, 77, 218, 14);
		contentPanel.add(lblBusqueda);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCancelarContrato = new JButton("Cancelar Contrato");
				btnCancelarContrato.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selected != null) {
							int option = JOptionPane.showConfirmDialog(null, "¿Desea cancelar el contrato " + selected.getNumeroContrato() + "?", "Confirmación", JOptionPane.WARNING_MESSAGE);
							if (option == JOptionPane.OK_OPTION) {
								selected.setEstado(Estado.CANCELADO);
								loadContratos();
								btnCancelarContrato.setEnabled(false);
								btnModificar.setEnabled(false);
							}
						}
					}
				});
				btnCancelarContrato.setEnabled(false);
				buttonPane.add(btnCancelarContrato);
			}
			{
				btnModificar = new JButton("Modificar Contrato");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selected != null) {
							// Al modificar, se crea uno nuevo (como pediste) y el viejo se cancela
							selected.setEstado(Estado.CANCELADO);
							RegistrarContrato nuevo = new RegistrarContrato(selected); // Abre para uno nuevo
							nuevo.setVisible(true);
							loadContratos();
							selected = null;
							btnModificar.setEnabled(false);
							btnCancelarContrato.setEnabled(false);
						}
					}
				});
				btnModificar.setEnabled(false);
				buttonPane.add(btnModificar);
			}
			{
				btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(btnSalir);
			}
		}
		loadHeaders();
	}

	public static void loadContratos() {
		model.setRowCount(0);
		raw = new Object[table.getColumnCount()];
		String filtro = cbxSelecFiltro.getSelectedItem().toString();
		boolean mostrarCancelados = rdbtnCancelados.isSelected();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		for (Contrato con : Altice.getInstance().getMisContratos()) {
			boolean cumpleFiltro = false;

			if (filtro.equalsIgnoreCase("<<Seleccione>>")) {
				cumpleFiltro = true;
			} else if (filtro.equalsIgnoreCase("Vigente") && con.getEstado() == Estado.VIGENTE) {
				cumpleFiltro = true;
			} else if (filtro.equalsIgnoreCase("Pendiente") && con.getEstado() == Estado.PENDIENTE) {
				cumpleFiltro = true;
			} else if (filtro.equalsIgnoreCase("Suspendido") && con.getEstado() == Estado.SUSPENDIDO) {
				cumpleFiltro = true;
			}

			// Lógica de visibilidad (Activos vs Cancelados)
			if (cumpleFiltro) {
				if ((mostrarCancelados && con.getEstado() == Estado.CANCELADO) || (!mostrarCancelados && con.getEstado() != Estado.CANCELADO)) {
					raw[0] = con.getNumeroContrato();
					raw[1] = con.getCli().getNombre();
					raw[2] = con.getPlan().getNombreComercial();
					raw[3] = con.getEstado().toString();
					raw[4] = con.getInicio().format(formatter);
					raw[5] = con.getCorte().format(formatter);
					model.addRow(raw);
				}
			}
		}
	}

	public void loadHeaders() {
		String headers[] = {"Número", "Cliente", "Plan", "Estado", "Fecha Inicio", "Fecha Corte"};
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		scrollPane.setViewportView(table);
		loadContratos();
	}
}