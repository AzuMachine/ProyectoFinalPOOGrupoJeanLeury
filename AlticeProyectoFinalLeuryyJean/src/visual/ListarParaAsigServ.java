package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Altice;
import logico.Servicio;
import logico.Servicio.Serv;

import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

public class ListarParaAsigServ extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	public static Servicio selected = null;
	private JTable table;
	private static Object raw[];
	private static DefaultTableModel model;
	private JButton btnAgregar;
	private JButton btnCancelar;
	private JComboBox cbxSelecFiltro;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public ListarParaAsigServ() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarParaAsigServ.class.getResource("/Imagenes/AlticeLogoVentanas.PNG")));
		setTitle("Agregar al plan: Listado de servicios");
		setBounds(100, 100, 650, 590);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(29,41,59));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(25, 91, 583, 402);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index = table.getSelectedRow();

						if(index !=-1) {
							String id = table.getValueAt(index, 0).toString();
							selected = Altice.getInstance().buscarServicioByID(id);

							if(selected!= null) {
								btnAgregar.setEnabled(true);
							}
							else {
								btnAgregar.setEnabled(false);
								selected =null;
							}
						}
					}
				});
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				model = new DefaultTableModel();
				scrollPane.setViewportView(table);
			}
		}

		JLabel lblNewLabel = new JLabel("Filtrar por: ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(422, 46, 80, 14);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Servicios");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setBounds(25, 24, 237, 42);
		contentPanel.add(lblNewLabel_1);

		cbxSelecFiltro = new JComboBox();
		cbxSelecFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadHeaders();
			}
		});
		cbxSelecFiltro.setModel(new DefaultComboBoxModel(new String[] {"<<Seleccione>>", "Internet", "Telefonía", "Televisión"}));
		cbxSelecFiltro.setBounds(487, 42, 121, 22);
		contentPanel.add(cbxSelecFiltro);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAgregar = new JButton("Agregar");
				btnAgregar.setEnabled(false);
				btnAgregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(selected!=null) {
							int option = JOptionPane.showConfirmDialog(null, "Desea seleccionar el servicio: " + selected.getNombre(), "Confirmación", JOptionPane.YES_NO_OPTION);

							if(option == JOptionPane.YES_OPTION) {
								dispose();
							}
						}
					}
				});
				btnAgregar.setActionCommand("OK");
				buttonPane.add(btnAgregar);
				getRootPane().setDefaultButton(btnAgregar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ListarParaAsigServ.selected = null;
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		loadHeaders();
	}

	public void loadHeaders() {
		String headersServicios[] = {"ID", "Nombre", "Tipo", "Precio Mensual"};
		model.setColumnIdentifiers(headersServicios);
		table.setModel(model);
		loadServicios();

	}

	public void loadServicios() {
		model.setRowCount(0);

		if (btnAgregar != null) {
			btnAgregar.setEnabled(false);
		}

		raw = new Object[table.getColumnCount()]; 
		String seleccion = cbxSelecFiltro.getSelectedItem().toString();

		for(Servicio temp: Altice.getInstance().getMisServicios()) {
			boolean agregar = false;
			Serv tipoServicio = temp.getTipo(); 
			String tipo= "";


			if(tipoServicio == Serv.INTERNET) {
				tipo = "Internet";
				if(seleccion.equalsIgnoreCase("<<Seleccione>>") || seleccion.equalsIgnoreCase("Internet")) {
					agregar = true;
				}
			}
			if(tipoServicio == Serv.TELEFONIA) {
				tipo = "Telefonía";
				if(seleccion.equalsIgnoreCase("<<Seleccione>>") || seleccion.equalsIgnoreCase("Telefonía")) {
					agregar = true;
				}
			}
			if(tipoServicio == Serv.TELEVISION) {
				tipo = "Televisión";
				if(seleccion.equalsIgnoreCase("<<Seleccione>>") || seleccion.equalsIgnoreCase("Televisión")) {
					agregar = true;
				}
			}
		
			if(agregar) {
				raw[0] = temp.getIdService();
				raw[1] = temp.getNombre();
				raw[2] = tipo;
				raw[3] = temp.getCostoMensualInd();
				model.addRow(raw);
			}
		}

	}
}
