package visual;

//import java.awt.Dimension;
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
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class GestionEmpleados extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel panelAtras;
	private JButton btnCrearEmp;
	private JButton btnListarEmp;
	private JPanel panelGraficoGestion;
	//private Dimension dim;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public GestionEmpleados() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestionEmpleados.class.getResource("/Imagenes/AlticeLogoVentanas.PNG")));
		setTitle("Gestión de Empleados | Perfil Admin: " + logged().getNombre());
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 761, 445);
		setLocationRelativeTo(null);
		setResizable(false);
		panelAtras = new JPanel();
		panelAtras.setBackground(new Color(29,41,59));
		panelAtras.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelAtras);
		panelAtras.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(44,51,70));
		panel.setBounds(43, 32, 658, 341);
		panelAtras.add(panel);
		panel.setLayout(null);
		
		btnCrearEmp = new JButton("Crear Empleado");
		btnCrearEmp.setBackground(new Color(255, 110, 52));
		btnCrearEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarEmpleado reg = new RegistrarEmpleado(null);
				reg.setModal(true);
				reg.setVisible(true);	
			}
		});
		btnCrearEmp.setBounds(468, 75, 169, 96);
		panel.add(btnCrearEmp);
		
		btnListarEmp = new JButton("Listar Empleados");
		btnListarEmp.setBackground(new Color(255, 110, 52));
		btnListarEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarEmpleados list= new ListarEmpleados();
				list.setModal(true);
				list.setVisible(true);
			}
		});
		btnListarEmp.setBounds(468, 220, 169, 96);
		panel.add(btnListarEmp);
		
		panelGraficoGestion = new JPanel();
		panelGraficoGestion.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelGraficoGestion.setBounds(20, 75, 428, 241);
		panel.add(panelGraficoGestion);
		
		JLabel lblNewLabel = new JLabel("Empleados activos este mes\r\n");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel.setBounds(20, 24, 340, 27);
		panel.add(lblNewLabel);

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
}
