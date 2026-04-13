package visual;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import logico.Altice;
import logico.Persona;

public class GestionServicios extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel panelAtras;
	private JButton btnCrearServ;
	private JButton btnListarServ;
	private JPanel panelGraficoGestion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionServicios frame = new GestionServicios();
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
	public GestionServicios() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestionServicios.class.getResource("/Imagenes/AlticeLogoVentanas.PNG")));
		// Usamos el mismo método logged() para el título
		setTitle("Gestión de Servicios | Perfil Admin: " + logged().getNombre());
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
		
		btnCrearServ = new JButton("Crear Servicio");
		btnCrearServ.setForeground(Color.WHITE);
		btnCrearServ.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCrearServ.setBackground(new Color(255, 110, 52));
		btnCrearServ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Llamada a tu ventana de RegistrarServicio
				RegistrarServicio reg = new RegistrarServicio(null);
				reg.setModal(true);
				reg.setVisible(true);	
			}
		});
		btnCrearServ.setBounds(468, 75, 169, 96);
		panel.add(btnCrearServ);
		
		btnListarServ = new JButton("Listar Servicios");
		btnListarServ.setForeground(Color.WHITE);
		btnListarServ.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnListarServ.setBackground(new Color(255, 110, 52));
		btnListarServ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Llamada a tu ventana de ListarServicios
				ListarServicios list = new ListarServicios();
				list.setModal(true);
				list.setVisible(true);
			}
		});
		btnListarServ.setBounds(468, 220, 169, 96);
		panel.add(btnListarServ);
		
		panelGraficoGestion = new JPanel();
		panelGraficoGestion.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelGraficoGestion.setBounds(20, 75, 428, 241);
		panel.add(panelGraficoGestion);
		
		JLabel lblTitulo = new JLabel("Catálogo de servicios activos");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblTitulo.setBounds(20, 24, 380, 27);
		panel.add(lblTitulo);
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
}