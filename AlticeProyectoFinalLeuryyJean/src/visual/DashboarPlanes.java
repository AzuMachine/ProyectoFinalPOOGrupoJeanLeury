package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

import logico.Altice;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DashboarPlanes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCancelar;
	private JTextField txtIDplan;
	private JTextField txtNombreComercial;
	private JTextField textField;
	
	private final Color NAVY_ALTICE = new Color(33, 50, 65);      
	private final Color ACCENT_ORANGE = new Color(246, 114, 75);  
	private final Color INPUT_DARK = new Color(43, 51, 73);       
	private final Color BURNT_SIENNA = new Color(221, 112, 87);  

	public static void main(String[] args) {
		try {
			DashboarPlanes frame = new DashboarPlanes();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DashboarPlanes() {
		setFont(new Font("Tahoma", Font.BOLD, 16));
		setTitle("PORTAL DE CREACION DE LOS PLANES");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 558, 968);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(NAVY_ALTICE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelDelaCreacion = new JPanel();
		panelDelaCreacion.setBackground(NAVY_ALTICE);
		panelDelaCreacion.setLayout(null);
		contentPane.add(panelDelaCreacion, BorderLayout.CENTER);
		
		JPanel panelDetalles = new JPanel();
		panelDetalles.setBackground(NAVY_ALTICE);
		panelDetalles.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelDetalles.setBounds(10, 60, 512, 192);
		panelDelaCreacion.add(panelDetalles);
		panelDetalles.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("ID Plan:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 26, 72, 16);
		panelDetalles.add(lblNewLabel_1);
		
		txtIDplan = new JTextField();
		txtIDplan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtIDplan.setText("Plan-" + Altice.idPlan);
		txtIDplan.setCaretColor(Color.WHITE);
		txtIDplan.setForeground(BURNT_SIENNA);
		txtIDplan.setBackground(INPUT_DARK);
		txtIDplan.setEditable(false);
		txtIDplan.setBounds(10, 52, 492, 48);
		panelDetalles.add(txtIDplan);
		txtIDplan.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre Comercial (Nombre para la venta)");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 110, 232, 12);
		panelDetalles.add(lblNewLabel_2);
		
		txtNombreComercial = new JTextField();
		txtNombreComercial.setCaretColor(Color.WHITE);
		txtNombreComercial.setForeground(Color.WHITE);
		txtNombreComercial.setBackground(INPUT_DARK);
		txtNombreComercial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNombreComercial.setBounds(10, 133, 492, 36);
		panelDetalles.add(txtNombreComercial);
		
		JLabel lblNewLabel = new JLabel("Detalles del Plan");
		lblHeaderStyle(lblNewLabel);
		lblNewLabel.setBounds(182, 10, 165, 40);
		panelDelaCreacion.add(lblNewLabel);
		
		JPanel panelResumen_Precio = new JPanel();
		panelResumen_Precio.setBackground(NAVY_ALTICE);
		panelResumen_Precio.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelResumen_Precio.setBounds(10, 304, 512, 526);
		panelDelaCreacion.add(panelResumen_Precio);
		panelResumen_Precio.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("PRECIO TOTAL CON IMPUESTO");
		lblNewLabel_5.setForeground(BURNT_SIENNA);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setBounds(118, 22, 263, 12);
		panelResumen_Precio.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Precio con impuestos aplicados (18% ITBIS, 10% ISC, 2% CDT)");
		lblNewLabel_6.setForeground(BURNT_SIENNA);
		lblNewLabel_6.setBounds(86, 121, 369, 12);
		panelResumen_Precio.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Configuración del Plan");
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_7.setBounds(10, 204, 183, 22);
		panelResumen_Precio.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Tipo:");
		lblNewLabel_8.setForeground(Color.WHITE);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_8.setBounds(10, 236, 44, 22);
		panelResumen_Precio.add(lblNewLabel_8);
		
		JComboBox<String> cbxTipoCliente = new JComboBox<>();
		cbxTipoCliente.setBackground(INPUT_DARK);
		cbxTipoCliente.setForeground(BURNT_SIENNA);
		cbxTipoCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
		cbxTipoCliente.setModel(new DefaultComboBoxModel<>(new String[] {"Residencial", "Negocios"}));
		cbxTipoCliente.setBounds(64, 238, 176, 20);
		panelResumen_Precio.add(cbxTipoCliente);
		
		JLabel lblNewLabel_7_1 = new JLabel("Servicios del Plan");
		lblNewLabel_7_1.setForeground(Color.WHITE);
		lblNewLabel_7_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_7_1.setBounds(272, 205, 183, 22);
		panelResumen_Precio.add(lblNewLabel_7_1);
		
		JLabel lblNewLabel_7_1_1 = new JLabel("Servicios del Plan");
		lblNewLabel_7_1_1.setForeground(Color.WHITE);
		lblNewLabel_7_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_7_1_1.setBounds(272, 236, 183, 22);
		panelResumen_Precio.add(lblNewLabel_7_1_1);
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		panel.setBackground(INPUT_DARK);
		panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(272, 268, 230, 248);
		panelResumen_Precio.add(panel);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.BOLD, 20));
		textField.setForeground(BURNT_SIENNA);
		textField.setBackground(INPUT_DARK);
		textField.setEditable(false);
		textField.setBounds(106, 45, 279, 66);
		panelResumen_Precio.add(textField);
		
		JLabel lblNewLabel_4 = new JLabel("Resumen de Precios");
		lblHeaderStyle(lblNewLabel_4);
		lblNewLabel_4.setBounds(182, 262, 188, 32);
		panelDelaCreacion.add(lblNewLabel_4);

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(INPUT_DARK);
		buttonPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.RIGHT);
		fl_buttonPane.setVgap(10);
		buttonPane.setLayout(fl_buttonPane);
		contentPane.add(buttonPane, BorderLayout.SOUTH);

		JButton btnCrearPlan = new JButton("Crear Plan");
		btnCrearPlan.setBackground(BURNT_SIENNA);
		btnCrearPlan.setForeground(Color.BLACK);
		btnCrearPlan.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonPane.add(btnCrearPlan);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.DARK_GRAY);
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.addActionListener(e -> dispose());
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonPane.add(btnCancelar);
	}
	
	public void clean() {
		txtNombreComercial.setText("");
		txtIDplan.setText("Plan-" + Altice.idPlan);
	}
	
	private void lblHeaderStyle(JLabel lbl) {
		lbl.setForeground(BURNT_SIENNA);
		lbl.setFont(new Font("Tahoma", Font.BOLD, 17));
	}
}