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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
			public void windowClosing(WindowEvent e) {
				FileOutputStream altSalida;
				ObjectOutputStream altWrite;
				
				try {
					altSalida = new FileOutputStream("Altice.dat");
					altWrite = new ObjectOutputStream(altSalida);
					altWrite.writeObject(Altice.getInstance());
				} catch (FileNotFoundException e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}catch (IOException e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
			}
		});
		
		setResizable(false);
		setTitle("Administración Global | Perfil Admin: " + logged().getNombre());
		setIconImage(Toolkit.getDefaultToolkit().getImage(DashBoardAdmin.class.getResource("/Imagenes/AlticeLogoVentanas.PNG")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 857, 661);
		setResizable(false);
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
		panelBotones.setBounds(43, 79, 1447, 180);
		panelAdmin.add(panelBotones);
		panelBotones.setLayout(null);
		
		//Boton de Seccion empleados
		JButton btnGeEmp = new JButton("Gestión Empleados");
		//Gestion empleados cambio de color
		btnGeEmp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnGeEmp.setBackground(new Color(255, 110, 0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnGeEmp.setBackground(new Color(255, 110, 52));
			}
		});
		//Gestion empleados funcion del boton
		btnGeEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionEmpleados gemp = new GestionEmpleados();
				gemp.setVisible(true);
			}
		});
		btnGeEmp.setBackground(new Color(255, 110, 52));
		btnGeEmp.setForeground(Color.WHITE);
		btnGeEmp.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnGeEmp.setIcon(new ImageIcon(DashBoardAdmin.class.getResource("/Imagenes/50perfil.png")));
		btnGeEmp.setBounds(14, 52, 224, 75);
		panelBotones.add(btnGeEmp);
		
		//Boton de Seccion Planes
		JButton btnGePlan = new JButton("Gestión de Planes");
		//Gestion planes cambio de color
		btnGePlan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnGePlan.setBackground(new Color(255, 110, 0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnGePlan.setBackground(new Color(255, 110, 52));
			}
		});
		//Plan funcion del boton
		btnGePlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionPlanes ges = new GestionPlanes();
				ges.setModal(true);
				ges.setVisible(true);
			}
		});
		btnGePlan.setBackground(new Color(255, 110, 52));
		btnGePlan.setIcon(new ImageIcon(DashBoardAdmin.class.getResource("/Imagenes/50configPlan2.png")));
		btnGePlan.setForeground(Color.WHITE);
		btnGePlan.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnGePlan.setBounds(252, 52, 224, 75);
		panelBotones.add(btnGePlan);
		
		//Boton de Seccion Reportes
		JButton btnReportes = new JButton("Finanzas y Reportes");
		//reportes cambio color
		btnReportes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnReportes.setBackground(new Color(255, 110, 0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnReportes.setBackground(new Color(255, 110, 52));
			}
		});
		//Reportes funcion del boton
		btnReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Informes inf = new Informes();
				inf.setModal(true);
				inf.setVisible(true);
			}
		});
		btnReportes.setBackground(new Color(255, 110, 52));
		btnReportes.setIcon(new ImageIcon(DashBoardAdmin.class.getResource("/Imagenes/50reportes.png")));
		btnReportes.setForeground(Color.WHITE);
		btnReportes.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnReportes.setBounds(490, 52, 224, 75);
		panelBotones.add(btnReportes);
		
		//Boton de Perfil de Admin
		JButton btnMisDatos = new JButton("Mis Datos");
		//Admin cambio de color
		btnMisDatos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnMisDatos.setBackground(new Color(255, 110, 0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnMisDatos.setBackground(new Color(255, 110, 52));
			}
		});
		//Admin funcion del boton
		btnMisDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnMisDatos.setBackground(new Color(255, 110, 52));
		btnMisDatos.setIcon(new ImageIcon(DashBoardAdmin.class.getResource("/Imagenes/adminDash.png")));
		btnMisDatos.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnMisDatos.setForeground(Color.WHITE);
		btnMisDatos.setBounds(1204, 52, 224, 75);
		panelBotones.add(btnMisDatos);
		
		JButton btnGestionServ = new JButton("Gestión Servicios");
		btnGestionServ.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnGestionServ.setBackground(new Color(255, 110, 0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnGestionServ.setBackground(new Color(255, 110, 52)); 
			}
		});
		btnGestionServ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionServicios ges = new GestionServicios();
				ges.setModal(true);
				ges.setVisible(true);
			}
		});
		btnGestionServ.setIcon(new ImageIcon(DashBoardAdmin.class.getResource("/Imagenes/50servicios.png")));
		btnGestionServ.setForeground(Color.WHITE);
		btnGestionServ.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnGestionServ.setBackground(new Color(255, 110, 52));
		btnGestionServ.setBounds(728, 52, 224, 75);
		panelBotones.add(btnGestionServ);
		
		JButton btnGestionClientes = new JButton("Gestión Clientes");
		btnGestionClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnGestionClientes.setBackground(new Color(255, 110, 0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnGestionClientes.setBackground(new Color(255, 110, 52));
			}
		});
		
		btnGestionClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionClientes ges = new GestionClientes();
				ges.setModal(true);
				ges.setVisible(true);
			}
		});
		btnGestionClientes.setIcon(new ImageIcon(DashBoardAdmin.class.getResource("/Imagenes/adminDash.png"))); //mibombo
		
		btnGestionClientes.setForeground(Color.WHITE);
		btnGestionClientes.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnGestionClientes.setBackground(new Color(255, 110, 52));
		btnGestionClientes.setBounds(966, 52, 224, 75);
		panelBotones.add(btnGestionClientes);
		
		JPanel panelGraphYStats = new JPanel();
		panelGraphYStats.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelGraphYStats.setBounds(198, 546, 1137, 215);
		panelAdmin.add(panelGraphYStats);
		panelGraphYStats.setLayout(null);
		
		JPanel panelGraphic = new JPanel();
		panelGraphic.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelGraphic.setBounds(51, 51, 591, 87);
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