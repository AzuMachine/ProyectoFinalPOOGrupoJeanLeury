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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

public class Contrato extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnCancelar;
	private JTextField txtRNC_Cliente;
	private JTextField txtPrecioBasePlan;
	private JTextField txtCostoInstalacion;
	private JTextField txtTotalCajaInicial;
	private JTextField textField;
	
	//Codigo para los colores
	//Final funciona como un static, le dice al programa que el color no va a cambiar
	/**
	 * Es decir, impide que se le reasigne una nueva referencia. Una vez que la variable 
	 * apunta a un objeto Color, no podrás hacer algo como NAVY_ALTICE = otroColor;.
	 */
	private final Color NAVY_ALTICE = new Color(33, 50, 65);      
	private final Color ACCENT_ORANGE = new Color(246, 114, 75);  
	private final Color INPUT_DARK = new Color(43, 51, 73);       
	private final Color BURNT_SIENNA = new Color(221, 112, 87);   
	private JTextField txtPlanes;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Contrato dialog = new Contrato();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Contrato() {
		setFont(new Font("Tahoma", Font.BOLD, 16));
		setTitle("SISTEMA DE GESTIÓN DE CLIENTES Y CONTRATOS - ALTICE");
		setResizable(false);
		setBounds(100, 100, 722, 651);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		
		contentPanel.setBackground(NAVY_ALTICE); //Se asigna el color de fondo
		
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			
			panel.setBackground(NAVY_ALTICE); //Se le asigna el color al panel
			
			panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JPanel panelBusqueda = new JPanel();
			
			panelBusqueda.setBackground(NAVY_ALTICE);
			
			panelBusqueda.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			panelBusqueda.setBounds(20, 42, 650, 116);
			panel.add(panelBusqueda);
			panelBusqueda.setLayout(null);
			{
				JLabel lblNewLabel_1 = new JLabel("Icono");
				lblNewLabel_1.setForeground(new Color(255, 255, 255));
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblNewLabel_1.setBounds(10, 23, 72, 63);
				panelBusqueda.add(lblNewLabel_1);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("RNC del Cliente:");
				lblNewLabel_2.setForeground(new Color(255, 255, 255));
				lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblNewLabel_2.setBounds(92, 46, 137, 17);
				panelBusqueda.add(lblNewLabel_2);
			}
			
			txtRNC_Cliente = new JTextField();
			txtRNC_Cliente.setFont(new Font("Tahoma", Font.BOLD, 16));
			
			//Fondo Gris oscuro para el RNC
			txtRNC_Cliente.setBackground(INPUT_DARK);
			txtRNC_Cliente.setForeground(BURNT_SIENNA);
			
			txtRNC_Cliente.setBounds(218, 43, 173, 23);
			panelBusqueda.add(txtRNC_Cliente);
			txtRNC_Cliente.setColumns(10);
			
			JButton BtnBuscarClienteRNC = new JButton("Buscar Cliente");
			
			//Colores del boton
			BtnBuscarClienteRNC.setBackground(ACCENT_ORANGE);
			BtnBuscarClienteRNC.setForeground(new Color(255, 255, 255));
			
			BtnBuscarClienteRNC.setFont(new Font("Tahoma", Font.PLAIN, 14));
			
			//Revisar esto
			BtnBuscarClienteRNC.setActionCommand("OK");
			BtnBuscarClienteRNC.setBounds(423, 42, 141, 25);
			panelBusqueda.add(BtnBuscarClienteRNC);
			
			JLabel lblNewLabel_3 = new JLabel("Ingrese documento de identidad para la busqueda del Cliente");
			
			lblNewLabel_3.setForeground(new Color(255, 255, 255));
			lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_3.setBounds(92, 74, 341, 12);
			panelBusqueda.add(lblNewLabel_3);
			
			JLabel lblNewLabel = new JLabel("Bloque de busqueda:");
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setBounds(20, 10, 181, 30);
			panel.add(lblNewLabel);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
			{
				JPanel panelPlanes = new JPanel();
				//Color del panel de planes
				panelPlanes.setBackground(NAVY_ALTICE);
				panelPlanes.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
				panelPlanes.setBounds(20, 204, 302, 301);
				panel.add(panelPlanes);
				panelPlanes.setLayout(null);
				{
					JLabel lblNewLabel_4 = new JLabel("Seleccionar un Plan disponible:");
					lblNewLabel_4.setForeground(new Color(255, 255, 255));
					lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblNewLabel_4.setBounds(10, 10, 282, 12);
					panelPlanes.add(lblNewLabel_4);
				}
				{
					JLabel lblNewLabel_4 = new JLabel("Descripción del Plan:");
					lblNewLabel_4.setForeground(new Color(255, 255, 255));
					lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblNewLabel_4.setBounds(10, 75, 223, 12);
					panelPlanes.add(lblNewLabel_4);
				}
				
				txtPlanes = new JTextField();
				txtPlanes.setEditable(false);
				txtPlanes.setBounds(10, 36, 282, 18);
				panelPlanes.add(txtPlanes);
				txtPlanes.setColumns(10);
			}
			{
				JLabel lblSeleccinDelPlan = new JLabel("Selección del Plan:");
				lblSeleccinDelPlan.setForeground(new Color(255, 255, 255));
				lblSeleccinDelPlan.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblSeleccinDelPlan.setBounds(20, 168, 181, 30);
				panel.add(lblSeleccinDelPlan);
			}
			{
				JPanel panelDeCaja = new JPanel();
				
				panelDeCaja.setBackground(NAVY_ALTICE);
				
				panelDeCaja.setLayout(null);
				panelDeCaja.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
				panelDeCaja.setBounds(368, 204, 302, 301);
				panel.add(panelDeCaja);
				{
					JLabel lblNewLabel_4 = new JLabel("Caja y Cierre");
					lblNewLabel_4.setForeground(new Color(255, 255, 255));
					lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblNewLabel_4.setBounds(10, 10, 223, 12);
					panelDeCaja.add(lblNewLabel_4);
				}
				{
					JLabel lblNewLabel_4 = new JLabel("Mensualidad Base:");
					lblNewLabel_4.setForeground(new Color(255, 255, 255));
					lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblNewLabel_4.setBounds(10, 47, 136, 12);
					panelDeCaja.add(lblNewLabel_4);
				}
				{
					txtPrecioBasePlan = new JTextField();
					txtPrecioBasePlan.setEditable(false);
					txtPrecioBasePlan.setBounds(129, 45, 163, 18);
					panelDeCaja.add(txtPrecioBasePlan);
					txtPrecioBasePlan.setColumns(10);
				}
				{
					JLabel lblNewLabel_4 = new JLabel("Costo Instalación:");
					lblNewLabel_4.setForeground(new Color(255, 255, 255));
					lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblNewLabel_4.setBounds(10, 82, 136, 12);
					panelDeCaja.add(lblNewLabel_4);
				}
				{
					txtCostoInstalacion = new JTextField();
					txtCostoInstalacion.setEditable(false);
					
					txtCostoInstalacion.setBackground(ACCENT_ORANGE);
					txtCostoInstalacion.setForeground(new Color(255, 255, 255));
					txtCostoInstalacion.setColumns(10);
					txtCostoInstalacion.setBounds(129, 80, 163, 18);
					panelDeCaja.add(txtCostoInstalacion);
				}
				{
					JPanel panelCajaInicial = new JPanel();
					
					panelCajaInicial.setBackground(NAVY_ALTICE);
					
					panelCajaInicial.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
					panelCajaInicial.setBounds(10, 120, 282, 78);
					panelDeCaja.add(panelCajaInicial);
					panelCajaInicial.setLayout(null);
					{
						JLabel lblNewLabel_4 = new JLabel("Total inicial a Pagar:");
						lblNewLabel_4.setForeground(new Color(255, 255, 255));
						lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
						lblNewLabel_4.setBounds(10, 40, 136, 12);
						panelCajaInicial.add(lblNewLabel_4);
					}
					{
						txtTotalCajaInicial = new JTextField();
						txtTotalCajaInicial.setEditable(false);
						txtTotalCajaInicial.setColumns(10);
						txtTotalCajaInicial.setBounds(147, 38, 125, 18);
						panelCajaInicial.add(txtTotalCajaInicial);
					}
					{
						JLabel lblNewLabel_4 = new JLabel("Caja");
						lblNewLabel_4.setForeground(new Color(255, 255, 255));
						lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
						lblNewLabel_4.setBounds(10, 6, 136, 12);
						panelCajaInicial.add(lblNewLabel_4);
					}
				}
				{
					JPanel panelDatosPago = new JPanel();
					panelDatosPago.setLayout(null);
					panelDatosPago.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
					panelDatosPago.setBounds(10, 208, 282, 78);
					panelDeCaja.add(panelDatosPago);
					{
						JLabel lblNewLabel_4 = new JLabel("Método de Pago:");
						lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
						lblNewLabel_4.setBounds(10, 30, 136, 12);
						panelDatosPago.add(lblNewLabel_4);
					}
					{
						JLabel lblNewLabel_4 = new JLabel("Datos del Pago:");
						lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
						lblNewLabel_4.setBounds(10, 6, 136, 12);
						panelDatosPago.add(lblNewLabel_4);
					}
					{
						JComboBox cmbxMetodoPago = new JComboBox();
						cmbxMetodoPago.setModel(new DefaultComboBoxModel(new String[] {"<<Seleccione>>"}));
						cmbxMetodoPago.setBounds(142, 27, 130, 20);
						panelDatosPago.add(cmbxMetodoPago);
					}
					{
						JLabel lblNewLabel_5 = new JLabel("Código Factura:");
						lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
						lblNewLabel_5.setBounds(10, 56, 100, 12);
						panelDatosPago.add(lblNewLabel_5);
					}
					{
						textField = new JTextField();
						textField.setEditable(false);
						textField.setBounds(142, 54, 130, 18);
						panelDatosPago.add(textField);
						textField.setColumns(10);
					}
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
				JButton BtnFinalizarContra = new JButton("Finalizar y Cobrar");
				BtnFinalizarContra.setFont(new Font("Tahoma", Font.PLAIN, 14));
				BtnFinalizarContra.setActionCommand("OK");
				buttonPane.add(BtnFinalizarContra);
				getRootPane().setDefaultButton(BtnFinalizarContra);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
}
