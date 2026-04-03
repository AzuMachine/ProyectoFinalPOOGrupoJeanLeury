package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import logico.Altice;
import logico.Plan;

import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListarPlanes extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnCancelar;
	private JScrollPane scrollPane;
	private JTable table;
	private static Object filasPlanes[];
	private static DefaultTableModel model;
	public static Plan selected = null;
	private JButton btnSeleccionar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarPlanes dialog = new ListarPlanes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarPlanes() {
		setResizable(false);
		setTitle("Listado de Planes");
		setBounds(100, 100, 718, 514);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index = table.getSelectedRow();
						
						if(index >= 0 ) {
							String idPlan = table.getValueAt(index, 0).toString();
							selected = Altice.getInstance().buscarPlanByID(idPlan);
							
							if(selected != null) {
								btnSeleccionar.setEnabled(true);
							}
							else {
								btnSeleccionar.setEnabled(false);
								selected = null;
							}
							
						}
						
					}
				});
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				model = new DefaultTableModel();
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.RIGHT);
			fl_buttonPane.setVgap(10);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ListarPlanes.selected = null;
						dispose();
					}
				});
				{
					btnSeleccionar = new JButton("Seleccionar");
					btnSeleccionar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(selected != null) {
								int option = JOptionPane.showConfirmDialog(null, "Desea seleccionar este Plan: " + selected.getNombreComercial(), "Confirmación", JOptionPane.YES_NO_OPTION);
								
								if(option == JOptionPane.YES_OPTION) {
									dispose();
								}
							}
						}
					});
					btnSeleccionar.setActionCommand("Ok");
					buttonPane.add(btnSeleccionar);
					btnSeleccionar.setEnabled(false);
					btnSeleccionar.setFont(new Font("Tahoma", Font.PLAIN, 13));
					getRootPane().setDefaultButton(btnSeleccionar);
				}
				btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		loadHeaders();
		selected = null;
	}
	
	private void loadPlanes() {
		model.setRowCount(0);
		
		Object[] filas2 = new Object[4];
		
		for (Plan planes : Altice.getInstance().getMisPlanes()) {
			filas2[0] = planes.getIdPlan();
			filas2[1] = planes.getNombreComercial();
			filas2[2] = planes.getPrecioBase();
			filas2[3] = planes.getState();
			
			model.addRow(filas2);
		}
		
	}
	
	private void loadHeaders() {
		String headerPlanes[] = {"ID", "Nombre Comercial", "Precio Base", "Estado"}; 
		model.setColumnIdentifiers(headerPlanes);
		table.setModel(model);
		loadPlanes();
	}


}
