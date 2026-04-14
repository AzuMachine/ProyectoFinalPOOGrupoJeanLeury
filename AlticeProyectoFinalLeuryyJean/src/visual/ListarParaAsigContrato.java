package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
import logico.Contrato;
import logico.Contrato.Estado;

public class ListarParaAsigContrato extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JButton btnSeleccionar;
	private JButton btnSalir;
	private static Object raw[];
	private static DefaultTableModel model;
	public static Contrato seleted = null;

	// Paleta de colores consistente
	private final Color NAVY_ALTICE = new Color(33, 50, 65);      
	private final Color INPUT_DARK = new Color(43, 51, 73);        
	private final Color ACCENT_ORANGE = new Color(246, 114, 75);


	public ListarParaAsigContrato() {
		setTitle("Contratos Disponibles - Altice");
		setResizable(false);
		setBounds(100, 100, 900, 650); 
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		
		contentPanel.setBackground(NAVY_ALTICE);
		contentPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 15));

		{
			JLabel lblSeleccioneContratoA = new JLabel("Seleccione el contrato para la asignación del Ticket:");
			lblSeleccioneContratoA.setForeground(ACCENT_ORANGE);
			lblSeleccioneContratoA.setFont(new Font("Tahoma", Font.BOLD, 18));
			contentPanel.add(lblSeleccioneContratoA, BorderLayout.NORTH);
		}

		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBackground(NAVY_ALTICE);
			scrollPane.setBorder(new LineBorder(Color.GRAY));
			contentPanel.add(scrollPane, BorderLayout.CENTER);

			table = new JTable();
			table.setRowHeight(30);
			table.setBackground(INPUT_DARK);
			table.setForeground(Color.WHITE);
			table.setSelectionBackground(ACCENT_ORANGE);
			table.setSelectionForeground(Color.BLACK);
			table.setFont(new Font("Tahoma", Font.PLAIN, 14));
			table.setGridColor(NAVY_ALTICE);
			
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
						String id_Contrato = table.getValueAt(indice, 0).toString();
						seleted = Altice.getInstance().buscarContratoByNumero(id_Contrato);
						btnSeleccionar.setEnabled(seleted != null);
					}
				}
			});
			
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 10));
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
							"¿Desea asignar este contrato: " + seleted.getNumeroContrato() + "?", 
							"Confirmación", JOptionPane.YES_NO_OPTION);
						if(option == JOptionPane.YES_OPTION) {
							dispose();
						}
					}
				});
				buttonPane.add(btnSeleccionar);
			}
			
			{
				btnSalir = new JButton("Cancelar");
				btnSalir.setPreferredSize(new Dimension(120, 40));
				btnSalir.setBackground(new Color(192, 57, 43)); // Rojo discreto
				btnSalir.setForeground(Color.WHITE);
				btnSalir.setFont(new Font("Tahoma", Font.BOLD, 14));
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ListarParaAsigContrato.seleted = null;
						dispose();
					}
				});
				buttonPane.add(btnSalir);
			}
		}
		
		loadHeaders();
		seleted = null;
	}
	
	private void loadContratos() {
		model.setRowCount(0);
		raw = new Object[6];
		
		for (Contrato contraTemp : Altice.getInstance().getMisContratos()) {
			if(contraTemp.getEstado().equals(Estado.PENDIENTE)) {
				raw[0] = contraTemp.getNumeroContrato();
				raw[1] = contraTemp.getInicio();
				raw[2] = contraTemp.getCorte();
				raw[3] = contraTemp.getCli().getNombre();
				raw[4] = contraTemp.getPlan().getNombreComercial();
				raw[5] = contraTemp.getEmp().getIdEmpleado();
				model.addRow(raw);
			}
		}
	}
	
	public void loadHeaders() {
		String headerContrato[] = {"N° Contrato", "Fecha Inicio", "Fecha Corte", "Cliente", "Plan", "ID Empleado Atiende"};
		model.setColumnIdentifiers(headerContrato);
		table.setModel(model);
		loadContratos();
	}
}