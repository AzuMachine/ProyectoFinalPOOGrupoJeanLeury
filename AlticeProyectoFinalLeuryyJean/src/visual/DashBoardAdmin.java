package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Altice;
import logico.Persona;

import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DashBoardAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelAdmin;
	private Dimension dim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashBoardAdmin frame = new DashBoardAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DashBoardAdmin() {
		//Listener para guardar los datos al cerrar
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
			}
		});
		setResizable(false);
		setTitle("Administración Global | Perfil Admin: " + logged().getNombre());
		setIconImage(Toolkit.getDefaultToolkit().getImage(DashBoardAdmin.class.getResource("/Imagenes/AlticeLogoVentanas.PNG")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 857, 661);
		dim = getToolkit().getScreenSize();
		setSize(dim.width-50,dim.height - 45);
		setLocationRelativeTo(null);
		panelAdmin = new JPanel();
		panelAdmin.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelAdmin);
		panelAdmin.setBackground(new Color(29,41,59));
		panelAdmin.setLayout(null);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelBotones.setBounds(118, 54, 1297, 204);
		panelAdmin.add(panelBotones);
		panelBotones.setLayout(null);
		
		//Boton de Seccion empleados
		JButton btnGeEmp = new JButton("Gestión de Empleados");
		//Gestion empleados cambio de color
		btnGeEmp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		//Gestion empleados funcion del boton
		btnGeEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGeEmp.setBackground(new Color(255, 110, 52));
		btnGeEmp.setForeground(Color.WHITE);
		btnGeEmp.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnGeEmp.setIcon(new ImageIcon(DashBoardAdmin.class.getResource("/Imagenes/perfil.png")));
		btnGeEmp.setBounds(55, 41, 255, 122);
		panelBotones.add(btnGeEmp);
		
		//Boton de Seccion Planes
		JButton btnGePlan = new JButton("Gestión de Planes");
		//Gestion planes cambio de color
		btnGePlan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		//Plan funcion del boton
		btnGePlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGePlan.setBackground(new Color(255, 110, 52));
		btnGePlan.setIcon(new ImageIcon(DashBoardAdmin.class.getResource("/Imagenes/configPlan2.png")));
		btnGePlan.setForeground(Color.WHITE);
		btnGePlan.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnGePlan.setBounds(365, 41, 255, 122);
		panelBotones.add(btnGePlan);
		
		//Boton de Seccion Reportes
		JButton btnReportes = new JButton("Finanzas y Reportes");
		//reportes cambio color
		btnReportes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		//Reportes funcion del boton
		btnReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReportes.setBackground(new Color(255, 110, 52));
		btnReportes.setIcon(new ImageIcon(DashBoardAdmin.class.getResource("/Imagenes/reportes.png")));
		btnReportes.setForeground(Color.WHITE);
		btnReportes.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnReportes.setBounds(675, 41, 255, 122);
		panelBotones.add(btnReportes);
		
		//Boton de Perfil de Admin
		JButton btnMisDatos = new JButton("Mis Datos");
		//Admin cambio de color
		btnMisDatos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		//Admin funcion del boton
		btnMisDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnMisDatos.setBackground(new Color(255, 110, 52));
		btnMisDatos.setIcon(new ImageIcon(DashBoardAdmin.class.getResource("/Imagenes/admin.png")));
		btnMisDatos.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnMisDatos.setForeground(Color.WHITE);
		btnMisDatos.setBounds(985, 41, 255, 122);
		panelBotones.add(btnMisDatos);
		
		JPanel panelGraphYStats = new JPanel();
		panelGraphYStats.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelGraphYStats.setBounds(198, 312, 1137, 449);
		panelAdmin.add(panelGraphYStats);
		panelGraphYStats.setLayout(null);
		
		JPanel panelGraphic = new JPanel();
		panelGraphic.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelGraphic.setBounds(51, 51, 591, 346);
		panelGraphYStats.add(panelGraphic);

	}
	
	//1.Dashboard.1 funcion buscar quien hizo logIn para titulo dinamico
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