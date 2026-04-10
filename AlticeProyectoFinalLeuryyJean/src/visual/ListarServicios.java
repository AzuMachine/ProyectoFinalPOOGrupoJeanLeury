package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarServicios extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel panelPrincipal = new JPanel();
	private JTable table;
	private static Object raw[];
	private static DefaultTableModel model;
	private JComboBox cbxFiltroListar;
	private JButton btnSalir;
	private JScrollPane scrollPane;
	private JButton btnEliminar;
	private JButton btnModificar;

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
	 * Create the dialog.
	 */
	public ListarServicios() {
		setTitle("Listar Servicios");
		setResizable(false);
		setBounds(100, 100, 859, 578);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Servicios Registrados en el Sistema");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblNewLabel.setBounds(10, 21, 341, 27);
			panelPrincipal.add(lblNewLabel);
		}
		{
			cbxFiltroListar = new JComboBox();
			cbxFiltroListar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			cbxFiltroListar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			cbxFiltroListar.setModel(new DefaultComboBoxModel(new String[] {"<<Seleccionar>>", " INTERNET – Fibra Óptica", "TELEVISIÓN – Cable Básico HD", "TELEFONÍA – Fija (Voz Digital)"}));
			cbxFiltroListar.setBounds(614, 26, 221, 20);
			panelPrincipal.add(cbxFiltroListar);
		}
		{
			JPanel panelScroll = new JPanel();
			panelScroll.setBounds(10, 69, 825, 402);
			panelPrincipal.add(panelScroll);
			panelScroll.setLayout(new BorderLayout(0, 0));
			{
				scrollPane = new JScrollPane();
				panelScroll.add(scrollPane, BorderLayout.CENTER);
				{
					table = new JTable();
					model = new DefaultTableModel();
					scrollPane.setViewportView(table);
				}
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
				btnModificar = new JButton("Modificar");
				btnModificar.setFont(new Font("Tahoma", Font.BOLD, 11));
				btnModificar.setEnabled(false);
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
			}
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.setEnabled(false);
				btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
				btnEliminar.setActionCommand("OK");
				buttonPane.add(btnEliminar);
				getRootPane().setDefaultButton(btnEliminar);
			}
			{
				btnSalir = new JButton("Salir");
				btnSalir.setFont(new Font("Tahoma", Font.BOLD, 11));
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
	}

}
