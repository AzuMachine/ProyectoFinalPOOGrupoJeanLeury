package visual;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import logico.Altice;
import logico.Persona;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import java.util.ArrayList;

public class GestionPlanes extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel panelGraficoGestion;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public GestionPlanes() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestionEmpleados.class.getResource("/Imagenes/AlticeLogoVentanas.PNG")));
		setTitle("Gestión de Planes | Perfil Admin: " + logged().getNombre());
		setResizable(false);
		setResizable(false);
		setBounds(100, 100, 761, 445);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel panelAtras = new JPanel();
		panelAtras.setLayout(null);
		panelAtras.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelAtras.setBackground(new Color(29, 41, 59));
		panelAtras.setBounds(0, 0, 745, 406);
		getContentPane().add(panelAtras);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(44, 51, 70));
		panel.setBounds(43, 32, 658, 341);
		panelAtras.add(panel);
		
		JButton btnCrearPlan = new JButton("Crear Plan");
		btnCrearPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarPlan reg = new RegistrarPlan(null);
				reg.setModal(true);
				reg.setVisible(true);
				actualizarGraficoVentas();
			}
		});
		btnCrearPlan.setBackground(new Color(255, 110, 52));
		btnCrearPlan.setBounds(468, 75, 169, 96);
		panel.add(btnCrearPlan);
		
		JButton btnListarPlanes = new JButton("Listar Planes");
		btnListarPlanes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarPlanes lis= new ListarPlanes();
				lis.setModal(true);
				lis.setVisible(true);
				actualizarGraficoVentas();
			}
		});
		btnListarPlanes.setBackground(new Color(255, 110, 52));
		btnListarPlanes.setBounds(468, 220, 169, 96);
		panel.add(btnListarPlanes);
		
		panelGraficoGestion = new JPanel();
		panelGraficoGestion.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelGraficoGestion.setBounds(20, 75, 428, 241);
		panel.add(panelGraficoGestion);
		panelGraficoGestion.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Popularidad: Planes más vendidos este mes\r\n");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel.setBounds(20, 24, 464, 27);
		panel.add(lblNewLabel);
		actualizarGraficoVentas();
	}
	
	public Persona logged() {
	    Persona logged = null;
	    // Si no hay nadie logueado, uno por defecto para que corra (solo el nombre que es lo que pide el titulo)
	    if (Altice.getLoginUser() == null) {
	        return new logico.Empleado("Invitado", null, 0, "", "", "", "", "", "");
	    }
	    
	    for(Persona p: Altice.getInstance().getMisHumanos()) {
	        if(p.getUser() != null && p.getUser().getUserName().equalsIgnoreCase(Altice.getLoginUser().getUserName())) { //Mas seguro comparar los elementos que comparar el objeto entero por la desserializacion y eso
	            logged = p;
	        }
	    }
	    return logged;
	}
	
	public void actualizarGraficoVentas() {
	    ArrayList<Integer> datosVentas = Altice.getInstance().ventasPorCategoriaList();
	    
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    dataset.addValue(datosVentas.get(0), "Ventas", "Internet");
	    dataset.addValue(datosVentas.get(1), "Ventas", "TV");
	    dataset.addValue(datosVentas.get(2), "Ventas", "Telefonía");

	    JFreeChart barChart = ChartFactory.createBarChart(
	        "", "Servicio", "Cantidad", 
	        dataset, PlotOrientation.VERTICAL, 
	        false, true, false
	    );

	    CategoryPlot plot = barChart.getCategoryPlot();
	    plot.setBackgroundPaint(Color.WHITE);
	    ((NumberAxis) plot.getRangeAxis()).setStandardTickUnits(NumberAxis.createIntegerTickUnits());
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
