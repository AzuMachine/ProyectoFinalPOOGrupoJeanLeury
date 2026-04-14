package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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

public class GestionContratos extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel panelAtras;
	private JButton btnCrearCon;
	private JButton btnListarCon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GestionContratos dialog = new GestionContratos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GestionContratos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestionClientes.class.getResource("/Imagenes/AlticeLogoVentanas.PNG")));
		setTitle("Gestión de Contratos | Perfil Admin: <dynamic>");
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
		
		btnCrearCon = new JButton("Crear Contrato");
		btnCrearCon.setForeground(Color.WHITE);
		btnCrearCon.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCrearCon.setBackground(new Color(255, 110, 52));
		btnCrearCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Llamada a la clase solicitada
				RegistrarClienteLeury reg = new RegistrarClienteLeury(null);
				reg.setModal(true);
				reg.setVisible(true);	
			}
		});
		btnCrearCon.setBounds(106, 150, 169, 96);
		panel.add(btnCrearCon);
		
		btnListarCon = new JButton("Listar Contratos");
		btnListarCon.setForeground(Color.WHITE);
		btnListarCon.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnListarCon.setBackground(new Color(255, 110, 52));
		btnListarCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Llamada a la clase solicitada
				ListarClientesLeury list = new ListarClientesLeury();
				list.setModal(true);
				list.setVisible(true);
			}
		});
		btnListarCon.setBounds(381, 150, 169, 96);
		panel.add(btnListarCon);
		
		JLabel lblTituloGrafico = new JLabel("Gestionar Contratos");
		lblTituloGrafico.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloGrafico.setForeground(Color.WHITE);
		lblTituloGrafico.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblTituloGrafico.setBounds(159, 53, 340, 27);
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
