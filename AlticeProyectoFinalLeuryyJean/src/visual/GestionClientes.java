package visual;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Altice;
import logico.Persona;

import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.BorderLayout;

public class GestionClientes extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel panelAtras;
	private JButton btnCrearCli;
	private JButton btnListarCli;
	private JPanel panelGraficoGestion;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	
	public GestionClientes() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestionClientes.class.getResource("/Imagenes/AlticeLogoVentanas.PNG")));
		setTitle("Gestión de Clientes | Perfil Empleado: " + logged().getNombre());
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 761, 445);
		setLocationRelativeTo(null);
		panelAtras = new JPanel();
		panelAtras.setBackground(new Color(29, 41, 59));
		panelAtras.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelAtras);
		panelAtras.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(44, 51, 70));
		panel.setBounds(43, 32, 658, 341);
		panelAtras.add(panel);
		panel.setLayout(null);
		
		btnCrearCli = new JButton("Crear Cliente");
		btnCrearCli.setForeground(Color.WHITE);
		btnCrearCli.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCrearCli.setBackground(new Color(255, 110, 52));
		btnCrearCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Llamada a la clase solicitada
				RegistrarClienteLeury reg = new RegistrarClienteLeury(null);
				reg.setModal(true);
				reg.setVisible(true);	
				actualizarGraficoClientes();
			}
		});
		btnCrearCli.setBounds(468, 75, 169, 96);
		panel.add(btnCrearCli);
		
		btnListarCli = new JButton("Listar Clientes");
		btnListarCli.setForeground(Color.WHITE);
		btnListarCli.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnListarCli.setBackground(new Color(255, 110, 52));
		btnListarCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				ListarClientesLeury list = new ListarClientesLeury();
				list.setModal(true);
				list.setVisible(true);
				actualizarGraficoClientes();
			}
		});
		btnListarCli.setBounds(468, 220, 169, 96);
		panel.add(btnListarCli);
		
		panelGraficoGestion = new JPanel();
		panelGraficoGestion.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelGraficoGestion.setBounds(20, 75, 428, 241);
		panel.add(panelGraficoGestion);
		
		JLabel lblTituloGrafico = new JLabel("Clientes registrados este mes");
		lblTituloGrafico.setForeground(Color.WHITE);
		lblTituloGrafico.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblTituloGrafico.setBounds(20, 24, 340, 27);
		panel.add(lblTituloGrafico);
		actualizarGraficoClientes();
	}
	
	public Persona logged() {
		Persona logged = null;
		if (Altice.getLoginUser() == null) {
			return new logico.Empleado("Invitado", null, 0, "", "", "", "", "", "");
		}
		
		for(Persona p: Altice.getInstance().getMisHumanos()) {
			if(p.getUser() != null && p.getUser().getUserName().equalsIgnoreCase(Altice.getLoginUser().getUserName())) {
				logged = p;
			}
		}
		return logged;
	}
	
	
	private void actualizarGraficoClientes() {
	    ArrayList<Integer> datos = Altice.getInstance().cantidadClientesGrafico();
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    dataset.addValue(datos.get(0), "Clientes", "Activos");
	    dataset.addValue(datos.get(1), "Clientes", "Total");

	    JFreeChart barChart = ChartFactory.createBarChart(
	        "",
	        "Estado", 
	        "Cantidad", 
	        dataset, 
	        PlotOrientation.VERTICAL, 
	        false, true, false
	    );

	    CategoryPlot plot = barChart.getCategoryPlot();
	    plot.setBackgroundPaint(Color.WHITE);
	    
	    NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
	    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	    rangeAxis.setLowerBound(0);
	    
	    BarRenderer renderer = (BarRenderer) plot.getRenderer();
	    renderer.setSeriesPaint(0, new Color(255, 110, 52));

	    ChartPanel chartPanel = new ChartPanel(barChart);
	    panelGraficoGestion.setLayout(new BorderLayout());
	    panelGraficoGestion.removeAll();
	    panelGraficoGestion.add(chartPanel, BorderLayout.CENTER);
	    panelGraficoGestion.revalidate();
	    panelGraficoGestion.repaint();
	}
}