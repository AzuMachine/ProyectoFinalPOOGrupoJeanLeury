package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DashBoardComercial extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton cancelButton;
	private JTextField txtFecha;
	private JTextField txtEmpleadoAtiende;
	private JButton btnNewButton;
	private JButton btnNuevasVentas;
	private JButton btnHacerPagocaja;
	private JButton btnAsignarTiket;
	
	// Colores solicitados
	private final Color NAVY_ALTICE = new Color(33, 50, 65);      
	private final Color ACCENT_ORANGE = new Color(246, 114, 75);  
	private final Color INPUT_DARK = new Color(43, 51, 73);       
	private final Color BURNT_SIENNA = new Color(221, 112, 87);  

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DashBoardComercial dialog = new DashBoardComercial();
			dialog.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DashBoardComercial() {
		setFont(new Font("Tahoma", Font.BOLD, 14));
		setTitle("GESTION Y VENTAS ALTICE - SUCURSAL CENTRAL");
		setResizable(false);
		setBounds(100, 100, 690, 886);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		
		// Fondo principal del diálogo
		contentPanel.setBackground(NAVY_ALTICE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelComercial1 = new JPanel();
			panelComercial1.setBackground(NAVY_ALTICE);
			panelComercial1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			contentPanel.add(panelComercial1, BorderLayout.CENTER);
			panelComercial1.setLayout(null);
			
			txtFecha = new JTextField();
			txtFecha.setEditable(false);
			txtFecha.setBackground(INPUT_DARK);
			txtFecha.setForeground(Color.WHITE);
			txtFecha.setBounds(470, 10, 171, 27);
			panelComercial1.add(txtFecha);
			txtFecha.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Empleado que Atiende:");
			lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_1.setBounds(470, 56, 171, 18);
			panelComercial1.add(lblNewLabel_1);
			
			txtEmpleadoAtiende = new JTextField();
			txtEmpleadoAtiende.setEditable(false);
			txtEmpleadoAtiende.setBackground(INPUT_DARK);
			txtEmpleadoAtiende.setForeground(Color.WHITE);
			txtEmpleadoAtiende.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtEmpleadoAtiende.setBounds(470, 84, 171, 27);
			panelComercial1.add(txtEmpleadoAtiende);
			txtEmpleadoAtiende.setColumns(10);
			
			JLabel lblTitulo = new JLabel("DASHBOARD DEL COMERCIAL");
			lblTitulo.setForeground(BURNT_SIENNA);
			lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 26));
			lblTitulo.setBounds(10, 10, 450, 40);
			panelComercial1.add(lblTitulo);
			
			JLabel lblSubtitulo = new JLabel("Gestión y Ventas Altice - Sucursal Central");
			lblSubtitulo.setForeground(Color.WHITE);
			lblSubtitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblSubtitulo.setBounds(10, 60, 450, 25);
			panelComercial1.add(lblSubtitulo);
			
			JLabel lblMetricas = new JLabel("MÉTRICAS CLAVE DEL DÍA");
			lblMetricas.setForeground(BURNT_SIENNA);
			lblMetricas.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblMetricas.setBounds(10, 129, 250, 25);
			panelComercial1.add(lblMetricas);
			
			JPanel panelBotones = new JPanel();
			panelBotones.setBackground(NAVY_ALTICE);
			panelBotones.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			panelBotones.setBounds(364, 373, 297, 398);
			panelComercial1.add(panelBotones);
			panelBotones.setLayout(null);
			
			btnNewButton = new JButton("Gestión Clientes");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnNewButton.setBackground(ACCENT_ORANGE);
			btnNewButton.setForeground(Color.BLACK);
			btnNewButton.setBounds(53, 42, 200, 55);
			panelBotones.add(btnNewButton);
			
			btnNuevasVentas = new JButton("Nuevas Ventas");
			btnNuevasVentas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnNuevasVentas.setBackground(ACCENT_ORANGE);
			btnNuevasVentas.setForeground(Color.BLACK);
			btnNuevasVentas.setBounds(53, 130, 200, 55);
			panelBotones.add(btnNuevasVentas);
			
			btnHacerPagocaja = new JButton("Hacer Pago (Caja)");
			btnHacerPagocaja.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnHacerPagocaja.setBackground(ACCENT_ORANGE);
			btnHacerPagocaja.setForeground(Color.BLACK);
			btnHacerPagocaja.setEnabled(false);
			btnHacerPagocaja.setBounds(53, 222, 200, 55);
			panelBotones.add(btnHacerPagocaja);
			
			btnAsignarTiket = new JButton("Asignar Tiket");
			btnAsignarTiket.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnAsignarTiket.setBackground(ACCENT_ORANGE);
			btnAsignarTiket.setForeground(Color.BLACK);
			btnAsignarTiket.setBounds(53, 313, 200, 55);
			panelBotones.add(btnAsignarTiket);
			
			JPanel panelMonto = new JPanel();
			panelMonto.setLayout(null);
			panelMonto.setBackground(new Color(45, 51, 59));
			panelMonto.setBounds(10, 170, 321, 180);
			panelComercial1.add(panelMonto);
			
			JLabel lblMontoTitulo = new JLabel("MONTO EN CAJA HOY (VALOR EN RD$)");
			lblMontoTitulo.setForeground(new Color(186, 137, 114));
			lblMontoTitulo.setBounds(10, 10, 340, 20);
			panelMonto.add(lblMontoTitulo);
			
			JLabel lblMontoValor = new JLabel("$");
			lblMontoValor.setForeground(new Color(186, 137, 114));
			lblMontoValor.setFont(new Font("Tahoma", Font.BOLD, 42));
			lblMontoValor.setBounds(10, 50, 284, 60);
			panelMonto.add(lblMontoValor);
			
			JPanel panelVentas = new JPanel();
			panelVentas.setLayout(null);
			panelVentas.setBackground(new Color(45, 51, 59));
			panelVentas.setBounds(364, 172, 294, 180);
			panelComercial1.add(panelVentas);
			
			JLabel lblVentasTitulo = new JLabel("VENTAS REALIZADAS HOY (CONTRATOS)");
			lblVentasTitulo.setForeground(new Color(186, 137, 114));
			lblVentasTitulo.setBounds(10, 10, 340, 20);
			panelVentas.add(lblVentasTitulo);
			
			JLabel lblCantVentas = new JLabel("");
			lblCantVentas.setHorizontalAlignment(SwingConstants.CENTER);
			lblCantVentas.setForeground(new Color(220, 220, 220));
			lblCantVentas.setFont(new Font("Tahoma", Font.BOLD, 55));
			lblCantVentas.setBounds(10, 50, 261, 80);
			panelVentas.add(lblCantVentas);
			
			JPanel panelDeudores = new JPanel();
			panelDeudores.setLayout(null);
			panelDeudores.setBorder(new LineBorder(new Color(120, 80, 80)));
			panelDeudores.setBackground(new Color(60, 50, 50));
			panelDeudores.setBounds(10, 373, 321, 180);
			panelComercial1.add(panelDeudores);
			
			JLabel lblDeudaTitulo = new JLabel("CLIENTES CON DEUDA PENDIENTE");
			lblDeudaTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblDeudaTitulo.setForeground(new Color(200, 150, 150));
			lblDeudaTitulo.setBounds(10, 10, 301, 20);
			panelDeudores.add(lblDeudaTitulo);
			
			JLabel lblCantDeudores = new JLabel("");
			lblCantDeudores.setHorizontalAlignment(SwingConstants.CENTER);
			lblCantDeudores.setForeground(new Color(255, 100, 100));
			lblCantDeudores.setFont(new Font("Tahoma", Font.BOLD, 50));
			lblCantDeudores.setBounds(10, 40, 301, 70);
			panelDeudores.add(lblCantDeudores);
			
			JButton btnVerLista = new JButton("VER LISTA COMPLETA");
			btnVerLista.setForeground(Color.WHITE);
			btnVerLista.setBackground(new Color(80, 60, 60));
			btnVerLista.setBounds(56, 120, 200, 30);
			panelDeudores.add(btnVerLista);
			
			JPanel panelDeudores_1 = new JPanel();
			panelDeudores_1.setLayout(null);
			panelDeudores_1.setBorder(new LineBorder(new Color(120, 80, 80)));
			panelDeudores_1.setBackground(new Color(60, 50, 50));
			panelDeudores_1.setBounds(10, 591, 321, 180);
			panelComercial1.add(panelDeudores_1);
			
			JLabel lblCantidadDeTikets = new JLabel("CANTIDAD DE TIKETS SIN ASIGNAR");
			lblCantidadDeTikets.setHorizontalAlignment(SwingConstants.CENTER);
			lblCantidadDeTikets.setForeground(new Color(200, 150, 150));
			lblCantidadDeTikets.setBounds(10, 10, 301, 20);
			panelDeudores_1.add(lblCantidadDeTikets);
			
			JLabel lblCantTiketsAsig = new JLabel("");
			lblCantTiketsAsig.setHorizontalAlignment(SwingConstants.CENTER);
			lblCantTiketsAsig.setForeground(new Color(255, 100, 100));
			lblCantTiketsAsig.setFont(new Font("Tahoma", Font.BOLD, 50));
			lblCantTiketsAsig.setBounds(10, 40, 301, 70);
			panelDeudores_1.add(lblCantTiketsAsig);
			
			JButton btnVerLista_1 = new JButton("VER LISTA COMPLETA");
			btnVerLista_1.setForeground(Color.WHITE);
			btnVerLista_1.setBackground(new Color(80, 60, 60));
			btnVerLista_1.setBounds(56, 120, 200, 30);
			panelDeudores_1.add(btnVerLista_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(INPUT_DARK);
			buttonPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.RIGHT);
			fl_buttonPane.setVgap(10);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setBackground(Color.DARK_GRAY);
				cancelButton.setForeground(Color.WHITE);
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}