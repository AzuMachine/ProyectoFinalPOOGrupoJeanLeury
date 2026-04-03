package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;

public class LogIn extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel alticeIconoLogIn;
	private JTextField txtUsuarioLog;
	private JTextField txtPassLog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
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
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 618, 625);
		contentPane = new JPanel();
		contentPane.setLocation(-173, 0);
		contentPane.setBackground(new Color(29,41,59));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelLogin = new JPanel();
		panelLogin.setBorder(new LineBorder(new Color(171, 173, 179), 1, true));
		panelLogin.setBackground(new Color(33,50,65));
		panelLogin.setBounds(173, 105, 203, 331);
		contentPane.add(panelLogin);
		panelLogin.setLayout(null);
		
		alticeIconoLogIn = new JLabel("");
		alticeIconoLogIn.setBounds(74, 11, 59, 63);
		panelLogin.add(alticeIconoLogIn);
		alticeIconoLogIn.setIcon(new ImageIcon(LogIn.class.getResource("/Imagenes/AlticeIcon40px.png")));
		
		txtUsuarioLog = new JTextField();
		txtUsuarioLog.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
		txtUsuarioLog.setBounds(20, 132, 160, 27);
		txtUsuarioLog.setBackground(new Color (73,87,107));
		panelLogin.add(txtUsuarioLog);
		txtUsuarioLog.setColumns(10);
		
		txtPassLog = new JTextField();
		txtPassLog.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtPassLog.setBounds(20, 217, 160, 27);
		txtPassLog.setBackground(new Color (73,87,107));
		panelLogin.add(txtPassLog);
		txtPassLog.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(20, 107, 86, 14);
		panelLogin.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contraseña:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(20, 192, 86, 14);
		panelLogin.add(lblNewLabel_1);
		
		JButton btnLogIn = new JButton("New button");
		btnLogIn.setBounds(51, 279, 89, 23);
		
		panelLogin.add(btnLogIn);

	}
}
