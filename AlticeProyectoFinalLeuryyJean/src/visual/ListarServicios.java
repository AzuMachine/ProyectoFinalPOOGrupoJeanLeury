package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import logico.Altice;
import logico.Servicio;
import logico.Servicio.Serv;

public class ListarServicios extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel panelPrincipal = new JPanel();
	private JTable table;
	private static Object raw[];
	private static DefaultTableModel model;
	private static JComboBox<String> cbxFiltroListar;
	private JButton btnSalir;
	private JScrollPane scrollPane;
	public static JButton btnEliminar;
	public static JButton btnModificar;
	private Servicio selectedServ = null;

	private final Color NAVY_ALTICE = new Color(33, 50, 65);      
	private final Color INPUT_DARK = new Color(43, 51, 73);        
	private final Color BURNT_SIENNA = new Color(221, 112, 87);  

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarServicios dialog = new ListarServicios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructor adaptado para JDialog
	 */
	public ListarServicios() {
		setTitle("Listado General de Servicios");
		setResizable(false);
		setBounds(100, 100, 1050, 750); 
		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		panelPrincipal.setBackground(NAVY_ALTICE); 
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(null);

		JLabel lblTitulo = new JLabel("Servicios Registrados en el Sistema");
		lblTitulo.setForeground(BURNT_SIENNA); 
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22)); 
		lblTitulo.setBounds(20, 21, 500, 35);
		panelPrincipal.add(lblTitulo);

		cbxFiltroListar = new JComboBox<String>();
		cbxFiltroListar.setBackground(INPUT_DARK);
		cbxFiltroListar.setForeground(Color.WHITE);
		cbxFiltroListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadHeaders();
			}
		});
		cbxFiltroListar.setFont(new Font("Tahoma", Font.BOLD, 14));
		cbxFiltroListar.setModel(new DefaultComboBoxModel<String>(new String[] {"<< Filtrar por tipo >>", "INTERNET", "TELEFONIA", "TELEVISION"}));
		cbxFiltroListar.setBounds(740, 26, 270, 30);
		panelPrincipal.add(cbxFiltroListar);

		JPanel panelScroll = new JPanel();
		panelScroll.setBackground(NAVY_ALTICE);
		panelScroll.setBounds(20, 80, 990, 540); 
		panelPrincipal.add(panelScroll);
		panelScroll.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		panelScroll.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				if (index >= 0) {
					selectedServ = Altice.getInstance().buscarServicioByID(table.getValueAt(index, 0).toString());
					btnModificar.setEnabled(true);
					btnEliminar.setEnabled(true);
				}
			}
		});
		table.setRowHeight(60); 
		table.setFont(new Font("Tahoma", Font.PLAIN, 13)); 
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		scrollPane.setViewportView(table);

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(INPUT_DARK); 
		buttonPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
		FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.RIGHT);
		fl_buttonPane.setHgap(20);
		fl_buttonPane.setVgap(15);
		buttonPane.setLayout(fl_buttonPane);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		btnModificar = new JButton("Modificar Servicio");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedServ != null) {
					DashBoardCrearServ aux = new DashBoardCrearServ(selectedServ);
					aux.setModal(true);
					aux.setVisible(true);
				}
			}
		});
		btnModificar.setBackground(BURNT_SIENNA);
		btnModificar.setForeground(Color.BLACK);
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnModificar.setEnabled(false);
		buttonPane.add(btnModificar);

		btnEliminar = new JButton("Eliminar Seleccionado");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedServ != null) {
					int option = JOptionPane.showConfirmDialog(null, "Esta seguro que dese eliminar el Alquiler " + selectedServ.getIdService(), "Confirmación", JOptionPane.WARNING_MESSAGE);
					if(option == JOptionPane.OK_OPTION) {
						Altice.getInstance().eliminarServicio(selectedServ);
						loadServicios();
						
					}

				}
			}
		});
		btnEliminar.setBackground(BURNT_SIENNA);
		btnEliminar.setForeground(Color.BLACK);
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEliminar.setEnabled(false);
		buttonPane.add(btnEliminar);

		btnSalir = new JButton("Cerrar Ventana");
		btnSalir.setBackground(Color.DARK_GRAY);
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(btnSalir);

		loadHeaders();
	}

	public static void loadServicios() {
		model.setRowCount(0);
		raw = new Object[model.getColumnCount()];
		String seleccion = cbxFiltroListar.getSelectedItem().toString();

		for (Servicio servi : Altice.getInstance().getMisServicios()) {
			boolean agregar = false;

			if(seleccion.contains("<<")) {
				agregar = true;
			} else if (seleccion.equalsIgnoreCase("INTERNET") && servi.getTipo().equals(Serv.INTERNET)) {
				agregar = true;
			} else if (seleccion.equalsIgnoreCase("TELEFONIA") && servi.getTipo().equals(Serv.TELEFONIA)) {
				agregar = true;
			} else if (seleccion.equalsIgnoreCase("TELEVISION") && servi.getTipo().equals(Serv.TELEVISION)) {
				agregar = true;
			}

			if(agregar) {
				raw[0] = servi.getIdService();
				raw[1] = servi.getNombre();
				raw[2] = servi.getDescTecnica();
				raw[3] = servi.getTipo();
				raw[4] = "RD$ " + servi.getCostoMensualInd();
				model.addRow(raw);
			}
		}
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
	}

	private void loadHeaders() {
		String header[] = {"Código ID", "Nombre del Servicio", "Descripción Técnica", "Categoría", "Mensualidad"};
		model = new DefaultTableModel(null, header) {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);

		// Multilineas
		table.getColumnModel().getColumn(2).setCellRenderer(new MultiLineCellRenderer());

		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(180);
		table.getColumnModel().getColumn(2).setPreferredWidth(450);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(120);

		loadServicios();
	}

	class MultiLineCellRenderer extends JTextArea implements TableCellRenderer {
		private static final long serialVersionUID = 1L;

		public MultiLineCellRenderer() {
			setLineWrap(true);
			setWrapStyleWord(true);
			setOpaque(true);
			setFont(new Font("Tahoma", Font.PLAIN, 12));
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			if (isSelected) {
				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());
			} else {
				setForeground(table.getForeground());
				setBackground(table.getBackground());
			}
			setText((value == null) ? "" : value.toString());
			return this;
		}
	}
}