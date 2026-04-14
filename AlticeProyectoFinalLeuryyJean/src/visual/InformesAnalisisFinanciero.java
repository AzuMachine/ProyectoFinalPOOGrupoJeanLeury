package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JSlider;

public class InformesAnalisisFinanciero extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel pnlGraficoIngresos;
	private JPanel panelGraficoMargen;
	private JPanel panelPagos;
	private JTable table;
	private JLabel lblCantPagosHechos;
	private JLabel lblCantPagosAtrasados;
	private JLabel lblIngresoBruto;
	private JLabel lblIngresoNeto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InformesAnalisisFinanciero dialog = new InformesAnalisisFinanciero();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InformesAnalisisFinanciero() {
		setResizable(false);
		setTitle("Gestión financiera");
		setIconImage(Toolkit.getDefaultToolkit().getImage(InformesAnalisisFinanciero.class.getResource("/Imagenes/AlticeLogoVentanas.PNG")));
		setBounds(100, 100, 1139, 804);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(29, 41, 59));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblGestinYListado = new JLabel("Altice - Estado Actual de Volúmen");
		lblGestinYListado.setForeground(Color.WHITE);
		lblGestinYListado.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGestinYListado.setBounds(10, 11, 381, 31);
		contentPanel.add(lblGestinYListado);
		
		JLabel lblNewLabel_2 = new JLabel("Detalles de operaciones");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(10, 40, 218, 14);
		contentPanel.add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(44, 51, 70));
		panel.setBounds(10, 70, 576, 408);
		contentPanel.add(panel);
		
		JLabel lblIndicadoresDelVolmen = new JLabel("Resumen Monetario");
		lblIndicadoresDelVolmen.setForeground(Color.WHITE);
		lblIndicadoresDelVolmen.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblIndicadoresDelVolmen.setBounds(10, 21, 342, 25);
		panel.add(lblIndicadoresDelVolmen);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(44, 51, 70));
		panel_1.setBounds(81, 67, 166, 132);
		panel.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(new Color(29, 41, 59));
		panel_2.setBounds(0, 0, 166, 33);
		panel_1.add(panel_2);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Pagos realizados");
		lblNewLabel_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_2.add(lblNewLabel_2_1_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBackground(new Color(29, 41, 59));
		panel_3.setBounds(0, 110, 166, 22);
		panel_1.add(panel_3);
		
		lblCantPagosHechos = new JLabel("0");
		lblCantPagosHechos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantPagosHechos.setForeground(Color.WHITE);
		lblCantPagosHechos.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCantPagosHechos.setBounds(16, 46, 133, 40);
		lblCantPagosHechos.setForeground(new Color(221, 112, 87));
		panel_1.add(lblCantPagosHechos);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_1.setBackground(new Color(44, 51, 70));
		panel_1_1.setBounds(328, 67, 166, 132);
		panel.add(panel_1_1);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_1.setBackground(new Color(29, 41, 59));
		panel_2_1.setBounds(0, 0, 166, 33);
		panel_1_1.add(panel_2_1);
		
		JLabel lblNewLabel_2_1_1_2 = new JLabel("Pagos atrasados");
		lblNewLabel_2_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_2_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_2_1.add(lblNewLabel_2_1_1_2);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3_1.setBackground(new Color(29, 41, 59));
		panel_3_1.setBounds(0, 110, 166, 22);
		panel_1_1.add(panel_3_1);
		
		lblCantPagosAtrasados = new JLabel("0");
		lblCantPagosAtrasados.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantPagosAtrasados.setForeground(Color.WHITE);
		lblCantPagosAtrasados.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCantPagosAtrasados.setBounds(16, 46, 133, 40);
		lblCantPagosAtrasados.setForeground(new Color(221, 112, 87));
		panel_1_1.add(lblCantPagosAtrasados);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_1_1.setBackground(new Color(44, 51, 70));
		panel_1_1_1.setBounds(328, 220, 166, 132);
		panel.add(panel_1_1_1);
		
		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_1_1.setBackground(new Color(29, 41, 59));
		panel_2_1_1.setBounds(0, 0, 166, 33);
		panel_1_1_1.add(panel_2_1_1);
		
		JLabel lblNewLabel_2_1_1_3 = new JLabel("Ingreso Neto");
		lblNewLabel_2_1_1_3.setForeground(Color.WHITE);
		lblNewLabel_2_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_2_1_1.add(lblNewLabel_2_1_1_3);
		
		JPanel panel_3_1_1 = new JPanel();
		panel_3_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3_1_1.setBackground(new Color(29, 41, 59));
		panel_3_1_1.setBounds(0, 110, 166, 22);
		panel_1_1_1.add(panel_3_1_1);
		
		lblIngresoNeto = new JLabel("0");
		lblIngresoNeto.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngresoNeto.setForeground(Color.WHITE);
		lblIngresoNeto.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblIngresoNeto.setBounds(16, 46, 133, 40);
		lblIngresoNeto.setForeground(new Color(221, 112, 87));
		panel_1_1_1.add(lblIngresoNeto);
		
		JPanel panel_1_1_1_1 = new JPanel();
		panel_1_1_1_1.setLayout(null);
		panel_1_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_1_1_1.setBackground(new Color(44, 51, 70));
		panel_1_1_1_1.setBounds(81, 220, 166, 132);
		panel.add(panel_1_1_1_1);
		
		JPanel panel_2_1_1_1 = new JPanel();
		panel_2_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_1_1_1.setBackground(new Color(29, 41, 59));
		panel_2_1_1_1.setBounds(0, 0, 166, 33);
		panel_1_1_1_1.add(panel_2_1_1_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Ingreso Bruto Total");
		lblNewLabel_2_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_2_1_1_1.add(lblNewLabel_2_1_1_1);
		
		JPanel panel_3_1_1_1 = new JPanel();
		panel_3_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3_1_1_1.setBackground(new Color(29, 41, 59));
		panel_3_1_1_1.setBounds(0, 110, 166, 22);
		panel_1_1_1_1.add(panel_3_1_1_1);
		
		lblIngresoBruto = new JLabel("0");
		lblIngresoBruto.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngresoBruto.setForeground(Color.WHITE);
		lblIngresoBruto.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblIngresoBruto.setBounds(16, 46, 133, 40);
		lblIngresoBruto.setForeground(new Color(221, 112, 87));
		panel_1_1_1_1.add(lblIngresoBruto);
		
		JLabel lblAnlisisDeDistribucin = new JLabel(" Análisis Detallado");
		lblAnlisisDeDistribucin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnlisisDeDistribucin.setForeground(Color.WHITE);
		lblAnlisisDeDistribucin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAnlisisDeDistribucin.setBounds(371, 489, 381, 31);
		contentPanel.add(lblAnlisisDeDistribucin);
		
		JLabel lblNewLabel_2_1 = new JLabel("Inrgeso promedio por servicio");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_1.setBounds(241, 529, 200, 14);
		contentPanel.add(lblNewLabel_2_1);
		
		pnlGraficoIngresos = new JPanel();
		pnlGraficoIngresos.setBounds(241, 554, 200, 200);
		pnlGraficoIngresos.setBackground(new Color(29, 41, 59));
		contentPanel.add(pnlGraficoIngresos);
		pnlGraficoIngresos.setLayout(new BorderLayout(0, 0));
		
		panelGraficoMargen = new JPanel();
		panelGraficoMargen.setBounds(682, 554, 200, 200);
		panelGraficoMargen.setBackground(new Color(29, 41, 59));
		contentPanel.add(panelGraficoMargen);
		panelGraficoMargen.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Margen de Beneficio");
		lblNewLabel_2_1_2.setForeground(Color.WHITE);
		lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_1_2.setBounds(682, 529, 200, 14);
		contentPanel.add(lblNewLabel_2_1_2);
		
		panelPagos = new JPanel();
		panelPagos.setBounds(628, 70, 459, 408);
		contentPanel.add(panelPagos);
		panelPagos.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelPagos.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblPagosPorPeriodo = new JLabel("Pagos por período: Mes actual");
		lblPagosPorPeriodo.setForeground(Color.WHITE);
		lblPagosPorPeriodo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPagosPorPeriodo.setBounds(628, 40, 342, 25);
		contentPanel.add(lblPagosPorPeriodo);
	}
}
