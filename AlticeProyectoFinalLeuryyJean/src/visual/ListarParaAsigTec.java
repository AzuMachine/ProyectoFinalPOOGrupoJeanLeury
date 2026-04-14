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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Altice;
import logico.Empleado;
import logico.Persona;

public class ListarParaAsigTec extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JButton btnAsignar;
	private JButton btnSalir;
	private static Object raw[];
	private static DefaultTableModel model;
	public static Persona selected = null;
	
	// Colores de la línea gráfica
	private final Color NAVY_ALTICE = new Color(33, 50, 65);      
	private final Color ACCENT_ORANGE = new Color(246, 114, 75);  

	public static void main(String[] args) {
		try {
			ListarParaAsigTec dialog = new ListarParaAsigTec();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ListarParaAsigTec() {
		setTitle("Asignación de Técnicos - Altice");
		setResizable(false);
		setBounds(100, 100, 840, 602);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(NAVY_ALTICE); // Fondo Navy
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(NAVY_ALTICE);
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(10, 80, 796, 380);
			panel.add(panel_1);
			panel_1.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel_1.add(scrollPane, BorderLayout.CENTER);
				{
					table = new JTable();
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int indice = table.getSelectedRow();
							if(indice >= 0) {
								String id_Tecnico = table.getValueAt(indice, 0).toString();
								selected = Altice.getInstance().buscarEmpleadoByID(id_Tecnico);
								btnAsignar.setEnabled(true);
							}
						}
					});
					model = new DefaultTableModel();
					scrollPane.setViewportView(table);
				}
			}
			
			JLabel lblSeleccioneElTcnico = new JLabel("Seleccione el Técnico para ser Asignado: ");
			lblSeleccioneElTcnico.setForeground(ACCENT_ORANGE); // Texto Naranja
			lblSeleccioneElTcnico.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblSeleccioneElTcnico.setBounds(10, 37, 787, 22);
			panel.add(lblSeleccioneElTcnico);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(NAVY_ALTICE);
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.RIGHT);
			fl_buttonPane.setVgap(15);
			fl_buttonPane.setHgap(20);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAsignar = new JButton("Asignar");
				btnAsignar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// El objeto 'selected' ya fue cargado en el MouseClick
						dispose(); 
					}
				});
				btnAsignar.setEnabled(false);
				btnAsignar.setPreferredSize(new Dimension(120, 40));
				btnAsignar.setBackground(ACCENT_ORANGE);
				btnAsignar.setForeground(Color.BLACK);
				btnAsignar.setFont(new Font("Tahoma", Font.BOLD, 14));
				buttonPane.add(btnAsignar);
			}
			{
				btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						selected = null;
						dispose();
					}
				});
				btnSalir.setPreferredSize(new Dimension(100, 40));
				btnSalir.setBackground(new Color(192, 57, 43)); // Rojo
				btnSalir.setForeground(Color.WHITE);
				btnSalir.setFont(new Font("Tahoma", Font.BOLD, 14));
				buttonPane.add(btnSalir);
			}
			loadHeader();
			selected = null;
		}
	}
	
	private void loadTecnico() {
		model.setRowCount(0);
		raw = new Object[3];
		
		for (Persona aux : Altice.getInstance().getMisHumanos()) {
			if (aux instanceof Empleado) {
				Empleado emp = (Empleado) aux;
				if(emp.getDepartamento().equalsIgnoreCase("Técnico")) {
					raw[0] = emp.getIdEmpleado();
					raw[1] = emp.getRnc();
					raw[2] = emp.getNombre();
					model.addRow(raw);
				}
			}
		}
	}
	
	public void loadHeader() {
		String headerTecnico[] = {"N° ID", "RNC", "Nombre"};
		model.setColumnIdentifiers(headerTecnico);
		table.setModel(model);
		loadTecnico();
	}
}