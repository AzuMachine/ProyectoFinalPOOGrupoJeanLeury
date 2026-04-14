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
import javax.swing.SwingConstants;

public class Informes extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel panelAtras;
	private JButton btnGestionOperativa;
	private JButton btnAnalisisFinanciero;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Informes frame = new Informes();
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
	public Informes() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Informes.class.getResource("/Imagenes/AlticeLogoVentanas.PNG")));
		setTitle("Panel de Informes Clave | Perfil Admin: " + logged().getNombre());
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
		
		btnGestionOperativa = new JButton("Gestión Operativa");
		btnGestionOperativa.setForeground(Color.WHITE);
		btnGestionOperativa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnGestionOperativa.setBackground(new Color(255, 110, 52));
		btnGestionOperativa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InformesGestionOperativa operativo = new InformesGestionOperativa();
				operativo.setModal(true);
				operativo.setVisible(true);	
			}
		});
		btnGestionOperativa.setBounds(106, 122, 169, 96);
		panel.add(btnGestionOperativa);
		
		btnAnalisisFinanciero = new JButton("Análisis Financiero");
		btnAnalisisFinanciero.setForeground(Color.WHITE);
		btnAnalisisFinanciero.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAnalisisFinanciero.setBackground(new Color(255, 110, 52));
		btnAnalisisFinanciero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InformesAnalisisFinanciero financiero = new InformesAnalisisFinanciero();
				financiero.setModal(true);
				financiero.setVisible(true);
			}
		});
		btnAnalisisFinanciero.setBounds(381, 122, 169, 96);
		panel.add(btnAnalisisFinanciero);
		JLabel lblTituloGrafico = new JLabel("Informes del sistema");
		lblTituloGrafico.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloGrafico.setForeground(Color.WHITE);
		lblTituloGrafico.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblTituloGrafico.setBounds(159, 24, 340, 27);
		panel.add(lblTituloGrafico);
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