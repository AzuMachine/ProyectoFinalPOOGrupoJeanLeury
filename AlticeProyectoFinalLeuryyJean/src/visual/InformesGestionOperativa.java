package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;

import logico.Altice;
import logico.Cliente;
import logico.Empleado;
import logico.Persona;
import logico.Plan;

import javax.swing.SwingConstants;

public class InformesGestionOperativa extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel lblCantEmpleadosContratados;
	private JLabel lblCantPlanesActivos;
	private JLabel lblCantClientesActivos;
	private JLabel lblCantCancelaciones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InformesGestionOperativa dialog = new InformesGestionOperativa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InformesGestionOperativa() {
		setTitle("Gestión operativa");
		setIconImage(Toolkit.getDefaultToolkit().getImage(InformesGestionOperativa.class.getResource("/Imagenes/AlticeLogoVentanas.PNG")));
		setResizable(false);
		setBounds(100, 100, 1125, 586);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLocation(-294, -156);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(29,41,59));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblGestinYListado = new JLabel("Altice - Estado Actual de Volúmen");
		lblGestinYListado.setForeground(Color.WHITE);
		lblGestinYListado.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGestinYListado.setBounds(13, 23, 381, 31);
		contentPanel.add(lblGestinYListado);
		
		JLabel lblNewLabel_2 = new JLabel("Detalles de operaciones");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(13, 52, 218, 14);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblAnlisisDeDistribucin = new JLabel("Análisis de distribución mensual");
		lblAnlisisDeDistribucin.setForeground(Color.WHITE);
		lblAnlisisDeDistribucin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAnlisisDeDistribucin.setBounds(673, 23, 381, 31);
		contentPanel.add(lblAnlisisDeDistribucin);
		
		JLabel lblNewLabel_2_1 = new JLabel("Planes activos");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_1.setBounds(673, 51, 121, 14);
		contentPanel.add(lblNewLabel_2_1);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(13, 82, 613, 408);
		panel.setBackground(new Color(44, 51, 70));
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblIndicadoresDelVolmen = new JLabel("Indicadores del volúmen mensual");
		lblIndicadoresDelVolmen.setBounds(10, 21, 342, 25);
		panel.add(lblIndicadoresDelVolmen);
		lblIndicadoresDelVolmen.setForeground(Color.WHITE);
		lblIndicadoresDelVolmen.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(44, 51, 70));
		panel_1.setBounds(93, 67, 166, 132);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(0, 0, 166, 33);
		panel_2.setBackground(new Color(29,41,59));
		panel_1.add(panel_2);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Empleados Contratados");
		panel_2.add(lblNewLabel_2_1_1);
		lblNewLabel_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(0, 110, 166, 22);
		panel_3.setBackground(new Color(29,41,59));
		panel_1.add(panel_3);
		
		lblCantEmpleadosContratados = new JLabel("0");
		lblCantEmpleadosContratados.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantEmpleadosContratados.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCantEmpleadosContratados.setForeground(new Color(255, 255, 255));
		lblCantEmpleadosContratados.setBounds(16, 46, 133, 40);
		lblCantEmpleadosContratados.setForeground(new Color(221, 112, 87));
		panel_1.add(lblCantEmpleadosContratados);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_1.setBackground(new Color(44, 51, 70));
		panel_1_1.setBounds(352, 67, 166, 132);
		panel.add(panel_1_1);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_1.setBackground(new Color(29, 41, 59));
		panel_2_1.setBounds(0, 0, 166, 33);
		panel_1_1.add(panel_2_1);
		
		JLabel lblNewLabel_2_1_1_2 = new JLabel("Planes activos");
		panel_2_1.add(lblNewLabel_2_1_1_2);
		lblNewLabel_2_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_2_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3_1.setBackground(new Color(29, 41, 59));
		panel_3_1.setBounds(0, 110, 166, 22);
		panel_1_1.add(panel_3_1);
		
		lblCantPlanesActivos = new JLabel("0");
		lblCantPlanesActivos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantPlanesActivos.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCantPlanesActivos.setForeground(new Color(255, 255, 255));
		lblCantPlanesActivos.setBounds(16, 46, 133, 40);
		lblCantPlanesActivos.setForeground(new Color(221, 112, 87));
		panel_1_1.add(lblCantPlanesActivos);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_1_1.setBackground(new Color(44, 51, 70));
		panel_1_1_1.setBounds(352, 220, 166, 132);
		panel.add(panel_1_1_1);
		
		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_1_1.setBackground(new Color(29, 41, 59));
		panel_2_1_1.setBounds(0, 0, 166, 33);
		panel_1_1_1.add(panel_2_1_1);
		
		JLabel lblNewLabel_2_1_1_3 = new JLabel("Cancelaciones");
		panel_2_1_1.add(lblNewLabel_2_1_1_3);
		lblNewLabel_2_1_1_3.setForeground(Color.WHITE);
		lblNewLabel_2_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JPanel panel_3_1_1 = new JPanel();
		panel_3_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3_1_1.setBackground(new Color(29, 41, 59));
		panel_3_1_1.setBounds(0, 110, 166, 22);
		panel_1_1_1.add(panel_3_1_1);
		
		lblCantCancelaciones = new JLabel("0");
		lblCantCancelaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantCancelaciones.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCantCancelaciones.setForeground(new Color(255, 255, 255));
		lblCantCancelaciones.setBounds(16, 46, 133, 40);
		lblCantCancelaciones.setForeground(new Color(221, 112, 87));
		panel_1_1_1.add(lblCantCancelaciones);
		
		JPanel panel_1_1_1_1 = new JPanel();
		panel_1_1_1_1.setBounds(93, 220, 166, 132);
		panel.add(panel_1_1_1_1);
		panel_1_1_1_1.setLayout(null);
		panel_1_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_1_1_1.setBackground(new Color(44, 51, 70));
		
		JPanel panel_2_1_1_1 = new JPanel();
		panel_2_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_1_1_1.setBackground(new Color(29, 41, 59));
		panel_2_1_1_1.setBounds(0, 0, 166, 33);
		panel_1_1_1_1.add(panel_2_1_1_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Clientes Activos");
		panel_2_1_1_1.add(lblNewLabel_2_1_1_1);
		lblNewLabel_2_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JPanel panel_3_1_1_1 = new JPanel();
		panel_3_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3_1_1_1.setBackground(new Color(29, 41, 59));
		panel_3_1_1_1.setBounds(0, 110, 166, 22);
		panel_1_1_1_1.add(panel_3_1_1_1);
		
		lblCantClientesActivos = new JLabel("0");
		lblCantClientesActivos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantClientesActivos.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCantClientesActivos.setForeground(new Color(255, 255, 255));
		lblCantClientesActivos.setBounds(16, 46, 133, 40);
		lblCantClientesActivos.setForeground(new Color(221, 112, 87));
		panel_1_1_1_1.add(lblCantClientesActivos);
		
		JPanel pnlGrafico = new JPanel();
		pnlGrafico.setBounds(673, 82, 381, 376);
		contentPanel.add(pnlGrafico);
		actualizarIndicadores();
	}
	
	private void actualizarIndicadores() {
	    int contEmpleados = 0;
	    int contClientes = 0;
	    int contPlanes = 0;
	    int contCancelaciones = 0;

	    for (Persona p : Altice.getInstance().getMisHumanos()) {
	        if (p.isEstado()) {
	            if (p instanceof Empleado) {
	                contEmpleados++;
	            }
	            if (p instanceof Cliente) {
	                contClientes++;
	            }
	        }
	    }

	    for (Plan pl : Altice.getInstance().getMisPlanes()) {
	        if (pl.getState() == Plan.Estado.VIGENTE) {
	            contPlanes++;
	        }
	    }

	    for (logico.Contrato con : Altice.getInstance().getMisContratos()) {
	        if (con.getEstado() == logico.Contrato.Estado.CANCELADO) {
	            contCancelaciones++;
	        }
	    }

	    lblCantEmpleadosContratados.setText(String.valueOf(contEmpleados));
	    lblCantClientesActivos.setText(String.valueOf(contClientes));
	    lblCantPlanesActivos.setText(String.valueOf(contPlanes));
	    lblCantCancelaciones.setText(String.valueOf(contCancelaciones));
	}
}
