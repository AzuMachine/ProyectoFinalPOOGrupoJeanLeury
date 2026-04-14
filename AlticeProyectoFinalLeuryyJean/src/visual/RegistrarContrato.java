package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame; 
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import logico.Altice;
import logico.Cliente;
import logico.Contrato;
import logico.Empleado;
import logico.Pago;
import logico.Persona;
import logico.Plan;
import logico.Ticket;

public class RegistrarContrato extends JDialog { 

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
	private JButton btnBuscarClienteRNC;
	private JComboBox cmbxMetodoPago;
	private JButton btnFinalizarContra;
	private Cliente clienteSelected = null;
	private Plan planSelected = null;



	public RegistrarContrato(Contrato encontrado) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setFont(new Font("Tahoma", Font.BOLD, 16));
		setTitle("SISTEMA DE GESTIÓN DE CLIENTES Y CONTRATOS - ALTICE");
		setResizable(false);
		setBounds(100, 100, 900, 750);
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
		panelBusqueda.setBounds(25, 50, 830, 130);
		panel.add(panelBusqueda);
		panelBusqueda.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(RegistrarContrato.class.getResource("/Imagenes/50configPlan2.png")));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(53, 40, 66, 70);
		panelBusqueda.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("RNC del Cliente:");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(110, 50, 150, 25);
		panelBusqueda.add(lblNewLabel_2);

		txtRNC_Cliente = new JTextField();
		txtRNC_Cliente.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtRNC_Cliente.setBackground(INPUT_DARK);
		txtRNC_Cliente.setForeground(BURNT_SIENNA);
		txtRNC_Cliente.setBounds(270, 45, 250, 35);
		panelBusqueda.add(txtRNC_Cliente);
		txtRNC_Cliente.setColumns(10);

		btnBuscarClienteRNC = new JButton("Buscar Cliente");
		btnBuscarClienteRNC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Persona p = Altice.getInstance().buscarPersonaByRNC(txtRNC_Cliente.getText());
				if (p instanceof Cliente) {
					clienteSelected = (Cliente) p;
					JOptionPane.showMessageDialog(null, "Cliente encontrado: " + clienteSelected.getNombre(), "Información", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Cliente no registrado en el sistema", "Error", JOptionPane.ERROR_MESSAGE);
					clienteSelected = null;
				}
			}
		});
		btnBuscarClienteRNC.setBackground(ACCENT_ORANGE);
		btnBuscarClienteRNC.setForeground(Color.WHITE);
		btnBuscarClienteRNC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBuscarClienteRNC.setBounds(540, 45, 180, 35);
		panelBusqueda.add(btnBuscarClienteRNC);

		JLabel lblNewLabel_3 = new JLabel("Ingrese documento de identidad para la busqueda del Cliente");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(110, 90, 450, 20);
		panelBusqueda.add(lblNewLabel_3);

		JLabel lblNewLabel = new JLabel("Bloque de busqueda:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(25, 10, 250, 35);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));

		// Panel Planes
		JPanel panelPlanes = new JPanel();
		panelPlanes.setBackground(NAVY_ALTICE);
		panelPlanes.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelPlanes.setBounds(25, 240, 400, 350);
		panel.add(panelPlanes);
		panelPlanes.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Seleccionar un Plan disponible:");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(15, 15, 300, 25);
		panelPlanes.add(lblNewLabel_4);

		JLabel lblNewLabel_DescPlan = new JLabel("Descripción del Plan:");
		lblNewLabel_DescPlan.setForeground(Color.WHITE);
		lblNewLabel_DescPlan.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_DescPlan.setBounds(15, 90, 250, 25);
		panelPlanes.add(lblNewLabel_DescPlan);

		txtPlanes = new JTextField();
		txtPlanes.setText("Haga clic aquí para listar planes...");
		txtPlanes.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPlanes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListarParaAsignarPlanes listP = new ListarParaAsignarPlanes();
				listP.setModal(true);
				listP.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						if (ListarParaAsignarPlanes.selected != null) {
							planSelected = ListarParaAsignarPlanes.selected;
							txtPlanes.setText(planSelected.getNombreComercial());
							actualizarCaja();
						}
					}
				});
				listP.setVisible(true);
			}
		});
		txtPlanes.setEditable(false);
		txtPlanes.setBounds(15, 50, 370, 35);
		panelPlanes.add(txtPlanes);

		JLabel lblSeleccinDelPlan = new JLabel("Selección del Plan:");
		lblSeleccinDelPlan.setForeground(Color.WHITE);
		lblSeleccinDelPlan.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSeleccinDelPlan.setBounds(25, 200, 250, 35);
		panel.add(lblSeleccinDelPlan);

		// Panel Caja y Cierre
		JPanel panelDeCaja = new JPanel();
		panelDeCaja.setBackground(NAVY_ALTICE);
		panelDeCaja.setLayout(null);
		panelDeCaja.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelDeCaja.setBounds(455, 240, 400, 350);
		panel.add(panelDeCaja);

		JLabel lblCajaCierre = new JLabel("Caja y Cierre");
		lblCajaCierre.setForeground(Color.WHITE);
		lblCajaCierre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCajaCierre.setBounds(15, 15, 250, 25);
		panelDeCaja.add(lblCajaCierre);

		JLabel lblMensualidad = new JLabel("Mensualidad Base:");
		lblMensualidad.setForeground(Color.WHITE);
		lblMensualidad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMensualidad.setBounds(15, 55, 160, 25);
		panelDeCaja.add(lblMensualidad);

		txtPrecioBasePlan = new JTextField();
		txtPrecioBasePlan.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtPrecioBasePlan.setEditable(false);
		txtPrecioBasePlan.setBounds(185, 50, 200, 35);
		panelDeCaja.add(txtPrecioBasePlan);

		JLabel lblInstalacion = new JLabel("Costo Instalación:");
		lblInstalacion.setForeground(Color.WHITE);
		lblInstalacion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInstalacion.setBounds(15, 100, 160, 25);
		panelDeCaja.add(lblInstalacion);

		txtCostoInstalacion = new JTextField();
		txtCostoInstalacion.setText("1500.00");
		txtCostoInstalacion.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtCostoInstalacion.setEditable(false);
		txtCostoInstalacion.setBackground(ACCENT_ORANGE);
		txtCostoInstalacion.setForeground(Color.WHITE);
		txtCostoInstalacion.setBounds(185, 95, 200, 35);
		panelDeCaja.add(txtCostoInstalacion);

		JPanel panelCajaInicial = new JPanel();
		panelCajaInicial.setBackground(NAVY_ALTICE);
		panelCajaInicial.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelCajaInicial.setBounds(15, 145, 370, 90);
		panelDeCaja.add(panelCajaInicial);
		panelCajaInicial.setLayout(null);

		JLabel lblTotalPag = new JLabel("Total inicial a Pagar:");
		lblTotalPag.setForeground(Color.WHITE);
		lblTotalPag.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotalPag.setBounds(10, 45, 180, 25);
		panelCajaInicial.add(lblTotalPag);

		txtTotalCajaInicial = new JTextField();
		txtTotalCajaInicial.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtTotalCajaInicial.setEditable(false);
		txtTotalCajaInicial.setBounds(195, 40, 160, 35);
		panelCajaInicial.add(txtTotalCajaInicial);

		JLabel lblCajaMini = new JLabel("Caja");
		lblCajaMini.setForeground(Color.WHITE);
		lblCajaMini.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCajaMini.setBounds(10, 10, 100, 20);
		panelCajaInicial.add(lblCajaMini);

		JPanel panelDatosPago = new JPanel();
		panelDatosPago.setLayout(null);
		panelDatosPago.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelDatosPago.setBounds(15, 245, 370, 95);
		panelDeCaja.add(panelDatosPago);

		JLabel lblMetodo = new JLabel("Método de Pago:");
		lblMetodo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMetodo.setBounds(15, 35, 150, 25);
		panelDatosPago.add(lblMetodo);

		JLabel lblDatosPago = new JLabel("Datos del Pago:");
		lblDatosPago.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDatosPago.setBounds(15, 5, 150, 25);
		panelDatosPago.add(lblDatosPago);

		cmbxMetodoPago = new JComboBox();
		cmbxMetodoPago.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cmbxMetodoPago.setModel(new DefaultComboBoxModel(new String[] {"<<Seleccione>>","Efectivo","Transferencia","Tarjeta","Punto Altice"}));
		cmbxMetodoPago.setBounds(175, 30, 180, 30);
		panelDatosPago.add(cmbxMetodoPago);

		JLabel lblFactura = new JLabel("Código Factura:");
		lblFactura.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFactura.setBounds(15, 65, 150, 25);
		panelDatosPago.add(lblFactura);

		textField = new JTextField();
		textField.setText("FAC-" + (int)(Math.random() * 100000));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setEditable(false);
		textField.setBounds(175, 63, 180, 28);
		panelDatosPago.add(textField);

		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.RIGHT);
		fl_buttonPane.setVgap(15);
		fl_buttonPane.setHgap(20);
		buttonPane.setLayout(fl_buttonPane);
		contentPane.add(buttonPane, BorderLayout.SOUTH);

		btnFinalizarContra = new JButton("Finalizar y Cobrar");
		btnFinalizarContra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (clienteSelected != null && planSelected != null && cmbxMetodoPago.getSelectedIndex() > 0) {
					
					// Obtenemos el empleado logueado desde la instancia de Altice
					Empleado empLog = null;
					Persona pLog = Altice.getInstance().buscarPersonaByRNC(Altice.loginUser.getUserName());
					if (pLog instanceof Empleado) {
						empLog = (Empleado) pLog;
					}

					// 1. Crear el Contrato
					String codContrato = textField.getText();
					Contrato nuevoContrato = new Contrato(codContrato, planSelected, clienteSelected, empLog);
					Altice.getInstance().getMisContratos().add(nuevoContrato);

					// 2. Crear el Pago
					float total = planSelected.getPrecioTotal() + Float.parseFloat(txtCostoInstalacion.getText());
					Pago nuevoPago = new Pago("REC-" + codContrato, nuevoContrato, total, "N/A");
					Altice.getInstance().getMisPagos().add(nuevoPago);

					JOptionPane.showMessageDialog(null, "Contrato registrado con éxito. Ticket de instalación generado.", "Finalizado", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					
				} else {
					JOptionPane.showMessageDialog(null, "Debe buscar un cliente, seleccionar un plan y elegir un método de pago", "Atención", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnFinalizarContra.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnFinalizarContra.setFocusPainted(false);
		buttonPane.add(btnFinalizarContra);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 16));
		buttonPane.add(btnCancelar);
	}

	private void actualizarCaja() {
		if (planSelected != null) {
			float precioBase = planSelected.getPrecioTotal();
			float costoInstalacion = Float.parseFloat(txtCostoInstalacion.getText());
			txtPrecioBasePlan.setText("RD$ " + precioBase);
			txtTotalCajaInicial.setText("RD$ " + (precioBase + costoInstalacion));
		}
	}
}