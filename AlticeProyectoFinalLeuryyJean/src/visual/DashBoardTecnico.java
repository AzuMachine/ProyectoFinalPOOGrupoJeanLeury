package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import logico.Altice;
import logico.Ticket;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;

public class DashBoardTecnico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	// Paleta de colores consistente
	private final Color NAVY_ALTICE = new Color(33, 50, 65);      
	private final Color ACCENT_ORANGE = new Color(246, 114, 75);  
	private JTextField txtNombreTecnico;
	private JTextField textField;
	private JTable table;
	private JButton btnIniciar;
	private JButton btnRealizado;
	private JButton btnSalir;
	private static Object raw[];
	private static DefaultTableModel model;
	public static Ticket selected = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashBoardTecnico frame = new DashBoardTecnico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DashBoardTecnico() {
		setResizable(false);
		setTitle("Panel Técnico - Altice");
		
		// Lógica de guardado al cerrar
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FileOutputStream altSalidaTecnico;
				ObjectOutputStream altWriteTecnico;
				try {
					altSalidaTecnico = new FileOutputStream("Altice.dat");
					altWriteTecnico = new ObjectOutputStream(altSalidaTecnico);
					altWriteTecnico.writeObject(Altice.getInstance());
					altWriteTecnico.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setBounds(100, 100, 681, 998);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(NAVY_ALTICE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		// Panel Central (Donde irán tus tablas o datos en el futuro)
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(NAVY_ALTICE);
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre completo del Técnico ");
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setForeground(ACCENT_ORANGE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(20, 94, 319, 52);
		panelCentral.add(lblNewLabel);
		
		txtNombreTecnico = new JTextField();
		txtNombreTecnico.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtNombreTecnico.setBounds(20, 142, 276, 32);
		txtNombreTecnico.setForeground(ACCENT_ORANGE);
		panelCentral.add(txtNombreTecnico);
		txtNombreTecnico.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setForeground(new Color(246, 114, 75));
		lblId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblId.setBackground(Color.BLACK);
		lblId.setBounds(451, 102, 50, 39);
		panelCentral.add(lblId);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setForeground(new Color(246, 114, 75));
		textField.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField.setColumns(10);
		textField.setBounds(451, 142, 196, 32);
		panelCentral.add(textField);
		
		JLabel lblGes = new JLabel("Gestión del Técnico de Campo");
		lblGes.setForeground(new Color(246, 114, 75));
		lblGes.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGes.setBackground(Color.BLACK);
		lblGes.setBounds(20, 10, 286, 52);
		panelCentral.add(lblGes);
		
		JLabel lblGestin = new JLabel("Gestión de Ordenes de Trabajo ");
		lblGestin.setForeground(new Color(246, 114, 75));
		lblGestin.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGestin.setBackground(Color.BLACK);
		lblGestin.setBounds(20, 197, 319, 52);
		panelCentral.add(lblGestin);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 259, 196, 197);
		panelCentral.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(235, 259, 196, 197);
		panelCentral.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(451, 259, 196, 197);
		panelCentral.add(panel_2);
		
		JLabel lblTabla = new JLabel("Tabla de Tareas");
		lblTabla.setForeground(new Color(246, 114, 75));
		lblTabla.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTabla.setBackground(Color.BLACK);
		lblTabla.setBounds(20, 485, 182, 52);
		panelCentral.add(lblTabla);
		
		JPanel panelTable = new JPanel();
		panelTable.setBounds(20, 533, 627, 292);
		panelCentral.add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int indice = table.getSelectedRow();
				if(indice >= 0) {
					String id_Ticket = table.getValueAt(indice, 0).toString();
					selected = Altice.getInstance().buscarTiketByID(id_Ticket);
					btnIniciar.setEnabled(true);
				}
			}
		});
		model = new DefaultTableModel();
		scrollPane.setViewportView(table);

		// Panel de Botones Inferior
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(NAVY_ALTICE);
		buttonPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.RIGHT);
		fl_buttonPane.setHgap(20);
		fl_buttonPane.setVgap(15);
		buttonPane.setLayout(fl_buttonPane);
		contentPane.add(buttonPane, BorderLayout.SOUTH);

		// Botón Iniciar Trabajo
		btnIniciar = new JButton("Iniciar Trabajo");
		btnIniciar.setEnabled(false);
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnIniciar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnIniciar.setForeground(new Color(0, 0, 0));
		btnIniciar.setBackground(ACCENT_ORANGE);
		btnIniciar.setPreferredSize(new Dimension(150, 40));
		buttonPane.add(btnIniciar);

		// Botón Realizado
		btnRealizado = new JButton("Realizado");
		btnRealizado.setEnabled(false);
		btnRealizado.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRealizado.setForeground(new Color(0, 0, 0));
		btnRealizado.setBackground(new Color(46, 204, 113)); // Verde para éxito
		btnRealizado.setPreferredSize(new Dimension(120, 40));
		buttonPane.add(btnRealizado);

		// Botón Salir
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSalir.setForeground(new Color(0, 0, 0));
		btnSalir.setBackground(new Color(192, 57, 43)); // Rojo para salir
		btnSalir.setPreferredSize(new Dimension(100, 40));
		buttonPane.add(btnSalir);
	}
}