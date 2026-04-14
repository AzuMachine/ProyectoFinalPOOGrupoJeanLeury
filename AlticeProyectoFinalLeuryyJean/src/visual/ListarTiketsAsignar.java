package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import logico.Altice;
import logico.Ticket;

public class ListarTiketsAsignar extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	public JButton btnSeleccionar;
	private JButton btnSalir;
	private static Object raw[];
	private static DefaultTableModel model;
	public static Ticket selected = null;
	
	// Colores de la línea gráfica
	private final Color NAVY_ALTICE = new Color(33, 50, 65);      
	private final Color INPUT_DARK = new Color(43, 51, 73);        
	private final Color ACCENT_ORANGE = new Color(246, 114, 75);

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public ListarTiketsAsignar() {
		setResizable(false);
		setTitle("Asignar Tickets - Altice");
		setBounds(100, 100, 831, 585);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		
		contentPanel.setBackground(NAVY_ALTICE);
		contentPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 15));
		
		{
			JLabel lblSeleccioneElTicket = new JLabel("Seleccione el Ticket para ser Asignado: ");
			lblSeleccioneElTicket.setForeground(ACCENT_ORANGE);
			lblSeleccioneElTicket.setFont(new Font("Tahoma", Font.BOLD, 18));
			contentPanel.add(lblSeleccioneElTicket, BorderLayout.NORTH);
		}
		
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBorder(new LineBorder(Color.GRAY));
			scrollPane.getViewport().setBackground(NAVY_ALTICE);
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			
			table = new JTable();
			table.setRowHeight(30);
			table.setBackground(INPUT_DARK);
			table.setForeground(Color.WHITE);
			table.setSelectionBackground(ACCENT_ORANGE);
			table.setSelectionForeground(Color.BLACK);
			table.setFont(new Font("Tahoma", Font.PLAIN, 14));
			table.setGridColor(NAVY_ALTICE);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			// Estilo de encabezado
			JTableHeader header = table.getTableHeader();
			header.setBackground(Color.BLACK);
			header.setForeground(Color.WHITE);
			header.setFont(new Font("Tahoma", Font.BOLD, 13));
			header.setPreferredSize(new Dimension(0, 35));

			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int indice = table.getSelectedRow();
					if(indice >= 0) {
						String id_Ticket = table.getValueAt(indice, 0).toString();
						selected = Altice.getInstance().buscarTiketByID(id_Ticket);
						btnSeleccionar.setEnabled(selected != null);
					}
				}
			});
			
			model = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			scrollPane.setViewportView(table);
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(NAVY_ALTICE);
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.RIGHT, 15, 10);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			{
				btnSeleccionar = new JButton("Confirmar Selección");
				btnSeleccionar.setPreferredSize(new Dimension(180, 40));
				btnSeleccionar.setBackground(ACCENT_ORANGE);
				btnSeleccionar.setForeground(Color.WHITE);
				btnSeleccionar.setFont(new Font("Tahoma", Font.BOLD, 14));
				btnSeleccionar.setEnabled(false);
				btnSeleccionar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int option = JOptionPane.showConfirmDialog(null, 
								"¿Desea asignar este ticket: " + selected.getIdTicket() + "?", "Confirmación", JOptionPane.YES_NO_OPTION);
						if(option == JOptionPane.YES_OPTION) {
							dispose();
						}
					}
				});
				buttonPane.add(btnSeleccionar);
				getRootPane().setDefaultButton(btnSeleccionar);
			}
			
			{
				btnSalir = new JButton("Salir");
				btnSalir.setPreferredSize(new Dimension(100, 40));
				btnSalir.setBackground(new Color(192, 57, 43)); // Rojo para salida
				btnSalir.setForeground(Color.WHITE);
				btnSalir.setFont(new Font("Tahoma", Font.BOLD, 14));
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(btnSalir);
			}
		}
		loadHeaders();
		selected = null;
	}
	
	private void loadTickets() {
		model.setRowCount(0);
		raw = new Object[5];
		
		for (Ticket tempTicket : Altice.getInstance().getMisTickets()) {
			raw[0] = tempTicket.getIdTicket();
			raw[1] = tempTicket.getCli().getNombre();
			raw[2] = tempTicket.getCon().getNumeroContrato();
			raw[3] = tempTicket.getTecnico().getNombre();
			raw[4] = tempTicket.getFechaCreacion();
			model.addRow(raw);
		}
	}
	
	private void loadHeaders() {
		String headerTiket[] = {"N° Ticket", "Cliente", "N° Contrato", "Técnico Asignado", "Fecha"};
		model.setColumnIdentifiers(headerTiket);
		table.setModel(model);
		loadTickets();
	}
}