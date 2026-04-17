package visual;

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
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private JTextField txtUsuarioLog;
	private JTextField txtPassLog;
	private JButton btnLogIn;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				boolean cargado = false;

				// 1. Intentar cargar el archivo principal o el respaldo local
				cargado = cargarDatosLocal("Altice.dat") || cargarDatosLocal("RespaldoEmpresa.dat");

				// 2. Si nada funcionó, intentar del servidor (opcional) o crear inicial
				if (!cargado) {
					// Intentar recuperar del servidor con timeout para no bloquear
					if (!recuperarRespaldoDeServidor()) {
						crearConfiguracionInicial();
					}
				}

				// 3. Iniciar la interfaz
				try {
					LogIn frame = new LogIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Método auxiliar para evitar repetición de código y fugas de recursos
	private static boolean cargarDatosLocal(String nombreArchivo) {
		try (FileInputStream fis = new FileInputStream(nombreArchivo);
			 ObjectInputStream ois = new ObjectInputStream(fis)) {
			Altice.setAlt((Altice) ois.readObject());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public LogIn() {
		setTitle("Altice v1.0");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LogIn.class.getResource("/Imagenes/AlticeLogoVentanas.PNG")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 403, 541);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(29, 41, 59));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelLogin = new JPanel();
		panelLogin.setBackground(new Color(44, 51, 70));
		panelLogin.setBounds(92, 98, 203, 305);
		contentPane.add(panelLogin);
		panelLogin.setLayout(null);

		JLabel alticeIconoLogIn = new JLabel("");
		alticeIconoLogIn.setBounds(74, 19, 59, 63);
		alticeIconoLogIn.setIcon(new ImageIcon(LogIn.class.getResource("/Imagenes/AlticeIcon40px.png")));
		panelLogin.add(alticeIconoLogIn);

		txtUsuarioLog = new JTextField();
		txtUsuarioLog.setForeground(Color.WHITE);
		txtUsuarioLog.setBackground(new Color(73, 87, 107));
		txtUsuarioLog.setBounds(20, 134, 160, 27);
		panelLogin.add(txtUsuarioLog);

		txtPassLog = new JTextField();
		txtPassLog.setForeground(Color.WHITE);
		txtPassLog.setBackground(new Color(73, 87, 107));
		txtPassLog.setBounds(20, 213, 160, 27);
		panelLogin.add(txtPassLog);

		JLabel lblUser = new JLabel("Usuario:");
		lblUser.setForeground(Color.WHITE);
		lblUser.setBounds(20, 101, 86, 14);
		panelLogin.add(lblUser);

		JLabel lblPass = new JLabel("Contraseña:");
		lblPass.setForeground(Color.WHITE);
		lblPass.setBounds(20, 180, 86, 14);
		panelLogin.add(lblPass);

		btnLogIn = new JButton("Iniciar Sesión");
		btnLogIn.setBackground(new Color(255, 110, 52));
		btnLogIn.setForeground(Color.WHITE);
		btnLogIn.setBounds(57, 259, 110, 23);
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = txtUsuarioLog.getText();
				String pass = txtPassLog.getText();

				if (Altice.getInstance().confirmarIngreso(user, pass)) {
					Usuario logueado = Altice.getLoginUser();
					Persona per = Altice.getInstance().buscarPersonaByRNC(user);

					if (logueado.getRol() == Usuario.rolEmp.ADMINISTRADOR) {
						new DashBoardAdmin().setVisible(true);
					} else if (logueado.getRol() == Usuario.rolEmp.TECNICO) {
						DashBoardTecnico tecnico = new DashBoardTecnico();
						tecnico.setTecnicoLogueado((Empleado) per);
						tecnico.setVisible(true);
					}
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Credenciales incorrectas");
				}
			}
		});
		panelLogin.add(btnLogIn);
	}

	private static boolean recuperarRespaldoDeServidor() {
		// Try-with-resources para cerrar socket y streams automáticamente
		try (Socket socket = new Socket()) {
			// Timeout de 2 segundos: si el servidor no responde, no congela el LogIn
			socket.connect(new InetSocketAddress("127.0.0.1", 7001), 2000);
			
			try (ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
				 FileOutputStream altSale = new FileOutputStream("Altice.dat");
				 ObjectOutputStream altWrite = new ObjectOutputStream(altSale)) {
				
				Altice temp = (Altice) entrada.readObject();
				Altice.setAlt(temp);
				altWrite.writeObject(Altice.getInstance());
				
				JOptionPane.showMessageDialog(null, "Sistema restaurado desde el servidor.");
				return true;
			}
		} catch (Exception e) {
			System.out.println("Servidor de respaldo no disponible.");
			return false;
		}
	}

	private static void crearConfiguracionInicial() {
		try (FileOutputStream altSale = new FileOutputStream("Altice.dat");
			 ObjectOutputStream altWrite = new ObjectOutputStream(altSale)) {
			
			Usuario def = new Usuario(Usuario.rolEmp.ADMINISTRADOR, "admin", "admin");
			Empleado aux = new Empleado("AdminDefault", def, 0, "Disco duro", "M", "N/A", "N/A", "N/A", "EMP-0");
			aux.setDepartamento("Administración");
			
			Altice.getInstance().regPersona(aux);
			altWrite.writeObject(Altice.getInstance());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}