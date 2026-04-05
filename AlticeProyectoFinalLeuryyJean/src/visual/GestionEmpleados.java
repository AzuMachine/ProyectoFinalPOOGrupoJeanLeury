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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GestionEmpleados extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//private Dimension dim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionEmpleados frame = new GestionEmpleados();
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
	public GestionEmpleados() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestionEmpleados.class.getResource("/Imagenes/AlticeLogoVentanas.PNG")));
		setTitle("Gestión de Empleados | Perfil Admin: " + logged().getNombre());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 761, 445);
		//dim = getToolkit().getScreenSize();
		//setSize(dim.width-50,dim.height - 45);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(43, 32, 658, 341);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnCrearEmp = new JButton("Crear Empleado");
		btnCrearEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarEmpleado reg = new RegistrarEmpleado(null);
				reg.setModal(true);
				reg.setVisible(true);	
				dispose();
			}
		});
		btnCrearEmp.setBounds(146, 285, 122, 23);
		panel.add(btnCrearEmp);
		
		JButton btnListarEmp = new JButton("Listar Empleado");
		btnListarEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnListarEmp.setBounds(383, 285, 122, 23);
		panel.add(btnListarEmp);

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
