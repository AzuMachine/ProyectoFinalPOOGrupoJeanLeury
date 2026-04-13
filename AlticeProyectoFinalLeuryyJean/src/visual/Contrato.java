package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Contrato extends JFrame { 

	private static final long serialVersionUID = 1L;
	private JPanel contentPane; 
	private JButton btnCancelar;
	private JTextField txtRNC_Cliente;
	private JTextField txtPrecioBasePlan;
	private JTextField txtCostoInstalacion;
	private JTextField txtTotalCajaInicial;
	private JTextField textField;

	// Paleta de colores 
	private final Color NAVY_ALTICE = new Color(33, 50, 65);      
	private final Color ACCENT_ORANGE = new Color(246, 114, 75);  
	private final Color INPUT_DARK = new Color(43, 51, 73);       
	private final Color BURNT_SIENNA = new Color(221, 112, 87);   
	private JTextField txtPlanes;


	public static void main(String[] args) {
		try {
			Contrato frame = new Contrato();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Contrato() {
		// Configuración básica del JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFont(new Font("Tahoma", Font.BOLD, 16));
		setTitle("SISTEMA DE GESTIÓN DE CLIENTES Y CONTRATOS - ALTICE");
		setLocationRelativeTo(null);
		setResizable(false);
		setBounds(100, 100, 722, 651);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(NAVY_ALTICE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		// Panel Central
		JPanel panel = new JPanel();
		panel.setBackground(NAVY_ALTICE);
		panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		// Bloque de Búsqueda
		JPanel panelBusqueda = new JPanel();
		panelBusqueda.setBackground(NAVY_ALTICE);
		panelBusqueda.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelBusqueda.setBounds(20, 42, 650, 116);
		panel.add(panelBusqueda);
		panelBusqueda.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Icono");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 23, 72, 63);
		panelBusqueda.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("RNC del Cliente:");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(92, 46, 137, 17);
		panelBusqueda.add(lblNewLabel_2);

		txtRNC_Cliente = new JTextField();
		txtRNC_Cliente.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtRNC_Cliente.setBackground(INPUT_DARK);
		txtRNC_Cliente.setForeground(BURNT_SIENNA);
		txtRNC_Cliente.setBounds(218, 43, 173, 23);
		panelBusqueda.add(txtRNC_Cliente);
		txtRNC_Cliente.setColumns(10);

		JButton BtnBuscarClienteRNC = new JButton("Buscar Cliente");
		BtnBuscarClienteRNC.setBackground(ACCENT_ORANGE);
		BtnBuscarClienteRNC.setForeground(Color.WHITE);
		BtnBuscarClienteRNC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		BtnBuscarClienteRNC.setBounds(423, 42, 141, 25);
		panelBusqueda.add(BtnBuscarClienteRNC);

		JLabel lblNewLabel_3 = new JLabel("Ingrese documento de identidad para la busqueda del Cliente");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(92, 74, 341, 12);
		panelBusqueda.add(lblNewLabel_3);

		JLabel lblNewLabel = new JLabel("Bloque de busqueda:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(20, 10, 181, 30);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

		// Panel Planes
		JPanel panelPlanes = new JPanel();
		panelPlanes.setBackground(NAVY_ALTICE);
		panelPlanes.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelPlanes.setBounds(20, 204, 302, 301);
		panel.add(panelPlanes);
		panelPlanes.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Seleccionar un Plan disponible:");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(10, 10, 282, 12);
		panelPlanes.add(lblNewLabel_4);

		JLabel lblNewLabel_DescPlan = new JLabel("Descripción del Plan:");
		lblNewLabel_DescPlan.setForeground(Color.WHITE);
		lblNewLabel_DescPlan.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_DescPlan.setBounds(10, 75, 223, 12);
		panelPlanes.add(lblNewLabel_DescPlan);

		txtPlanes = new JTextField();
		txtPlanes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Mantiene funcionalidad original
				ListarParaAsignarPlanes listP = new ListarParaAsignarPlanes();
				listP.setEnabled(true);
				listP.setVisible(true);
			}
		});
		txtPlanes.setEditable(false);
		txtPlanes.setBounds(10, 36, 282, 18);
		panelPlanes.add(txtPlanes);
		txtPlanes.setColumns(10);

		JLabel lblSeleccinDelPlan = new JLabel("Selección del Plan:");
		lblSeleccinDelPlan.setForeground(Color.WHITE);
		lblSeleccinDelPlan.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSeleccinDelPlan.setBounds(20, 168, 181, 30);
		panel.add(lblSeleccinDelPlan);

		// Panel Caja y Cierre
		JPanel panelDeCaja = new JPanel();
		panelDeCaja.setBackground(NAVY_ALTICE);
		panelDeCaja.setLayout(null);
		panelDeCaja.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelDeCaja.setBounds(368, 204, 302, 301);
		panel.add(panelDeCaja);

		JLabel lblCajaCierre = new JLabel("Caja y Cierre");
		lblCajaCierre.setForeground(Color.WHITE);
		lblCajaCierre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCajaCierre.setBounds(10, 10, 223, 12);
		panelDeCaja.add(lblCajaCierre);

		JLabel lblMensualidad = new JLabel("Mensualidad Base:");
		lblMensualidad.setForeground(Color.WHITE);
		lblMensualidad.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMensualidad.setBounds(10, 47, 136, 12);
		panelDeCaja.add(lblMensualidad);

		txtPrecioBasePlan = new JTextField();
		txtPrecioBasePlan.setEditable(false);
		txtPrecioBasePlan.setBounds(129, 45, 163, 18);
		panelDeCaja.add(txtPrecioBasePlan);

		JLabel lblInstalacion = new JLabel("Costo Instalación:");
		lblInstalacion.setForeground(Color.WHITE);
		lblInstalacion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInstalacion.setBounds(10, 82, 136, 12);
		panelDeCaja.add(lblInstalacion);

		txtCostoInstalacion = new JTextField();
		txtCostoInstalacion.setEditable(false);
		txtCostoInstalacion.setBackground(ACCENT_ORANGE);
		txtCostoInstalacion.setForeground(Color.WHITE);
		txtCostoInstalacion.setBounds(129, 80, 163, 18);
		panelDeCaja.add(txtCostoInstalacion);

		JPanel panelCajaInicial = new JPanel();
		panelCajaInicial.setBackground(NAVY_ALTICE);
		panelCajaInicial.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelCajaInicial.setBounds(10, 120, 282, 78);
		panelDeCaja.add(panelCajaInicial);
		panelCajaInicial.setLayout(null);

		JLabel lblTotalPag = new JLabel("Total inicial a Pagar:");
		lblTotalPag.setForeground(Color.WHITE);
		lblTotalPag.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotalPag.setBounds(10, 40, 136, 12);
		panelCajaInicial.add(lblTotalPag);

		txtTotalCajaInicial = new JTextField();
		txtTotalCajaInicial.setEditable(false);
		txtTotalCajaInicial.setBounds(147, 38, 125, 18);
		panelCajaInicial.add(txtTotalCajaInicial);

		JLabel lblCajaMini = new JLabel("Caja");
		lblCajaMini.setForeground(Color.WHITE);
		lblCajaMini.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCajaMini.setBounds(10, 6, 136, 12);
		panelCajaInicial.add(lblCajaMini);

		JPanel panelDatosPago = new JPanel();
		panelDatosPago.setLayout(null);
		panelDatosPago.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelDatosPago.setBounds(10, 208, 282, 78);
		panelDeCaja.add(panelDatosPago);

		JLabel lblMetodo = new JLabel("Método de Pago:");
		lblMetodo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMetodo.setBounds(10, 30, 136, 12);
		panelDatosPago.add(lblMetodo);

		JLabel lblDatosPago = new JLabel("Datos del Pago:");
		lblDatosPago.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDatosPago.setBounds(10, 6, 136, 12);
		panelDatosPago.add(lblDatosPago);

		JComboBox cmbxMetodoPago = new JComboBox();
		cmbxMetodoPago.setModel(new DefaultComboBoxModel(new String[] {"<<Seleccione>>"}));
		cmbxMetodoPago.setBounds(142, 27, 130, 20);
		panelDatosPago.add(cmbxMetodoPago);

		JLabel lblFactura = new JLabel("Código Factura:");
		lblFactura.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFactura.setBounds(10, 56, 100, 12);
		panelDatosPago.add(lblFactura);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(142, 54, 130, 18);
		panelDatosPago.add(textField);

		// Panel de Botones Inferior
		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.RIGHT);
		fl_buttonPane.setVgap(10);
		buttonPane.setLayout(fl_buttonPane);
		contentPane.add(buttonPane, BorderLayout.SOUTH);

		JButton BtnFinalizarContra = new JButton("Finalizar y Cobrar");
		BtnFinalizarContra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonPane.add(BtnFinalizarContra);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Cierra el Frame
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonPane.add(btnCancelar);
	}
}