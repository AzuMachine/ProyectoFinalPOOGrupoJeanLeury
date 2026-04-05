package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Empleado;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListarEmpleados extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object raw[];
	private static DefaultTableModel model;
	private Empleado selected =null;
	private JButton btnActualizar;
	private JButton btnDespedir;
	private JButton cancelButton;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarEmpleados dialog = new ListarEmpleados();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarEmpleados() {
		setTitle("Lista de Empleados");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarEmpleados.class.getResource("/Imagenes/AlticeLogoVentanas.PNG")));
		setBounds(100, 100, 1077, 640);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(508, 136, 472, 375);
			contentPanel.add(scrollPane);
			{
				table_1 = new JTable();
				table_1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
					}
				});
				scrollPane.setViewportView(table_1);
			}
		}
		
		JLabel lblAquSeMuestran = new JLabel("Gestión y listado de empleados");
		lblAquSeMuestran.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblAquSeMuestran.setBounds(10, 11, 505, 31);
		contentPanel.add(lblAquSeMuestran);
		
		JLabel lblNewLabel_1 = new JLabel("Filtrar por:");
		lblNewLabel_1.setBounds(865, 18, 60, 14);
		contentPanel.add(lblNewLabel_1);
		
		JComboBox cbxSelecFiltro = new JComboBox();
		cbxSelecFiltro.setModel(new DefaultComboBoxModel(new String[] {"<<Seleccione>>", "Administrador", "Comercial", "Técnico"}));
		cbxSelecFiltro.setBounds(930, 18, 121, 22);
		contentPanel.add(cbxSelecFiltro);
		
		JRadioButton rdbtnDespedidos = new JRadioButton("Mostrar Despedidos");
		rdbtnDespedidos.setBounds(832, 50, 161, 23);
		contentPanel.add(rdbtnDespedidos);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(29, 75, 222, 436);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JPanel panelTopVentas = new JPanel();
		panelTopVentas.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelTopVentas.setBounds(18, 29, 185, 136);
		panel.add(panelTopVentas);
		
		JPanel panelTopTecnicos = new JPanel();
		panelTopTecnicos.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelTopTecnicos.setBounds(18, 194, 185, 92);
		panel.add(panelTopTecnicos);
		
		JPanel panelPeoresTecnicos = new JPanel();
		panelPeoresTecnicos.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPeoresTecnicos.setBounds(18, 315, 185, 92);
		panel.add(panelPeoresTecnicos);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(29, 53, 46, 14);
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnDespedir = new JButton("Despedir");
				buttonPane.add(btnDespedir);
			}
			{
				btnActualizar = new JButton("Actualizar perfil");
				btnActualizar.setActionCommand("OK");
				buttonPane.add(btnActualizar);
				getRootPane().setDefaultButton(btnActualizar);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
