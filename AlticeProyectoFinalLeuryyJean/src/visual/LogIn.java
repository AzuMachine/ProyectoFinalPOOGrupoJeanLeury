package visual;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;
import java.nio.file.FileSystemNotFoundException;
import java.awt.event.ActionEvent;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import logico.Altice;
import logico.Empleado;
import logico.Persona;
import logico.Usuario;


public class LogIn extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel alticeIconoLogIn;
	private JTextField txtUsuarioLog;
	private JTextField txtPassLog;
	private JButton btnLogIn;

	/**
	 * Launch the application.
	 */
	/*Creando proceso de archivos*/
	public static void main(String[] args) {
	    EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            FileInputStream altEntra; 
	            ObjectInputStream altRead;
	            boolean cargado = false;

	            // 1. Intentar cargar el archivo principal
	            try {
	                altEntra = new FileInputStream("Altice.dat");
	                altRead = new ObjectInputStream(altEntra);
	                Altice.setAlt((Altice)altRead.readObject());
	                altRead.close();
	                cargado = true;
	            } catch (FileNotFoundException e) {
	                // 2. Si no existe, intentar cargar el respaldo local "RespaldoEmpresa.dat"
	                try {
	                    altEntra = new FileInputStream("RespaldoEmpresa.dat");
	                    altRead = new ObjectInputStream(altEntra);
	                    Altice.setAlt((Altice)altRead.readObject());
	                    altRead.close();
	                    cargado = true;
	                    System.out.println("Cargado desde respaldo local.");
	                } catch (Exception e2) {
	                    System.out.println("No hay respaldo local.");
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }

	            // 3. Si nada funcionó, crear configuración inicial
	            if (!cargado) {
	                crearConfiguracionInicial();
	            }

	            // 4. Iniciar la interfaz siempre
	            try {
	                LogIn frame = new LogIn();
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
	public LogIn() {
		setTitle("Altice v1.0");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LogIn.class.getResource("/Imagenes/AlticeLogoVentanas.PNG")));

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 403, 541);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setLocation(-173, 0);
		contentPane.setBackground(new Color(29,41,59));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelLogin = new JPanel();
		panelLogin.setBorder(new LineBorder(new Color(44, 51, 70), 1, true));
		panelLogin.setBackground(new Color(44, 51, 70));
		panelLogin.setBounds(92, 98, 203, 305);
		contentPane.add(panelLogin);
		panelLogin.setLayout(null);

		alticeIconoLogIn = new JLabel("");
		alticeIconoLogIn.setBounds(74, 19, 59, 63);
		panelLogin.add(alticeIconoLogIn);
		alticeIconoLogIn.setIcon(new ImageIcon(LogIn.class.getResource("/Imagenes/AlticeIcon40px.png")));

		txtUsuarioLog = new JTextField();
		txtUsuarioLog.setForeground(SystemColor.window);
		txtUsuarioLog.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
		txtUsuarioLog.setBounds(20, 134, 160, 27);
		txtUsuarioLog.setBackground(new Color (73,87,107));
		panelLogin.add(txtUsuarioLog);
		txtUsuarioLog.setColumns(10);

		txtPassLog = new JTextField();
		txtPassLog.setForeground(SystemColor.window);
		txtPassLog.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtPassLog.setBounds(20, 213, 160, 27);
		txtPassLog.setBackground(new Color (73,87,107));
		panelLogin.add(txtPassLog);
		txtPassLog.setColumns(10);

		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(20, 101, 86, 14);
		panelLogin.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Contraseña:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(20, 180, 86, 14);
		panelLogin.add(lblNewLabel_1);

		btnLogIn = new JButton("Iniciar Sesión");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = txtUsuarioLog.getText();
				String pass = txtPassLog.getText();
				
				
				if (Altice.getInstance().confirmarIngreso(user, pass)) {
					Usuario logueado = Altice.getLoginUser();
					Persona per = Altice.getInstance().buscarPersonaByRNC(user);
					
					if (logueado.getRol() == Usuario.rolEmp.ADMINISTRADOR) {
						DashBoardAdmin admin = new DashBoardAdmin();
						admin.setVisible(true);
					} 
					else if (logueado.getRol() == Usuario.rolEmp.COMERCIAL) {
						DashBoardComercial comercial = new DashBoardComercial();
						comercial.setVisible(true);
					} 
					else if (logueado.getRol() == Usuario.rolEmp.TECNICO) {
						Empleado emp = (Empleado) per;
						DashBoardTecnico tecnico = new DashBoardTecnico();
						tecnico.setTecnicoLogueado(emp);
						tecnico.setVisible(true);
						dispose();
					} 
					else if (logueado.getRol() == Usuario.rolEmp.CLIENTE) {
						DashBoardCliente cliente = new DashBoardCliente();
						cliente.setVisible(true);
					}
					dispose(); 

				} else {
					if (user.isEmpty()||pass.isEmpty()) {
						javax.swing.JOptionPane.showMessageDialog(null, "El campo del usuario y la contraseña no deben estar vacíos", "Campos Incompletos", javax.swing.JOptionPane.ERROR_MESSAGE);
					}
					else {
						javax.swing.JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error de Autenticación", javax.swing.JOptionPane.ERROR_MESSAGE);						
					}
					
				}
			}
		});
		btnLogIn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnLogIn.setForeground(SystemColor.textHighlightText);
		btnLogIn.setBorder(new LineBorder(new Color(255, 110, 52), 1, true));
		btnLogIn.setBackground(new Color(255, 110, 52));
		btnLogIn.setBounds(57, 259, 89, 23);

		panelLogin.add(btnLogIn);

	}
	private static boolean recuperarRespaldoDeServidor() {
		try {
			Socket socket = new Socket("127.0.0.1", 7000);
			ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
			
			// Recibimos el objeto Altice del servidor
			Altice temp = (Altice) entrada.readObject();
			Altice.setAlt(temp);
			
			// Guardamos localmente para que ya exista la próxima vez
			FileOutputStream altSale = new FileOutputStream("Altice.dat");
			ObjectOutputStream altWrite = new ObjectOutputStream(altSale);
			altWrite.writeObject(Altice.getInstance());
			altWrite.close();
			entrada.close();
			socket.close();
			JOptionPane.showMessageDialog(null, "Sistema restaurado exitosamente desde el servidor de respaldo.");
			return true;
		} catch (Exception e) {
			System.out.println("No se pudo contactar al servidor de respaldo.");
			return false;
		}
	}
	private static void crearConfiguracionInicial() {
		try {
			FileOutputStream altSale = new FileOutputStream("Altice.dat");
			ObjectOutputStream altWrite = new ObjectOutputStream(altSale);
			Usuario def = new Usuario(Usuario.rolEmp.ADMINISTRADOR, "admin", "admin");
			Empleado aux = new Empleado("AdminDefault", def, 0, "Disco duro", "M", "N/A", "N/A", "N/A", "EMP-0"); 
			aux.setDepartamento("Administración");
			Altice.getInstance().regPersona(aux);
			altWrite.writeObject(Altice.getInstance());
			altSale.close();
			altWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
