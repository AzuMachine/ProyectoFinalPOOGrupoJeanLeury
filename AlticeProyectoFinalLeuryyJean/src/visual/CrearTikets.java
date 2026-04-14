package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import logico.Altice;
import logico.Ticket;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearTikets extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel panelContent = new JPanel();
	private JTextField txtID_Ticket;
	private JTextField txtContratoSelected;
	private JTextField txtNombreCliente;
	private JTextField txtRNC;
	
	private final Color NAVY_ALTICE = new Color(33, 50, 65);      
	private final Color INPUT_DARK = new Color(43, 51, 73);        
	private final Color ACCENT_ORANGE = new Color(246, 114, 75);
	private JButton btnVerLista;
	private JTextPane txtPane_Comentrio_Ticket;
	private JRadioButton rdbtnAlta;
	private JRadioButton rdbtnMedia;
	private JRadioButton rdbtnBaja;
	private JRadioButton rdbtnAveria;
	private JRadioButton rdbtnInstalacion;
	private JTextField txtTecnico;

	public static void main(String[] args) {
		try {
			CrearTikets dialog = new CrearTikets();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CrearTikets() {
		setTitle("Sistema de Tickets - Altice");
		setBounds(100, 100, 630, 920); 
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		
		panelContent.setBackground(NAVY_ALTICE);
		panelContent.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(panelContent, BorderLayout.CENTER);
		panelContent.setLayout(null);
		
		// --- SECCIÓN IZQUIERDA: DATOS TICKET ---
		JLabel lblTitulo = new JLabel("Creación de Tiket");
		lblTitulo.setForeground(ACCENT_ORANGE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTitulo.setBounds(20, 15, 250, 30);
		panelContent.add(lblTitulo);
		
		JLabel lblIdT = new JLabel("ID Ticket:");
		lblIdT.setForeground(Color.WHITE);
		lblIdT.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIdT.setBounds(20, 60, 112, 20);
		panelContent.add(lblIdT);
		
		txtID_Ticket = new JTextField();
		txtID_Ticket.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtID_Ticket.setText("AVE - " + Altice.getInstance().getIdTiket());
		txtID_Ticket.setEditable(false);
		txtID_Ticket.setBackground(INPUT_DARK);
		txtID_Ticket.setForeground(Color.WHITE);
		txtID_Ticket.setBounds(20, 85, 200, 30);
		panelContent.add(txtID_Ticket);

		JLabel lblIdC = new JLabel("ID Contrato (Click):");
		lblIdC.setForeground(Color.WHITE);
		lblIdC.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIdC.setBounds(20, 125, 150, 20);
		panelContent.add(lblIdC);
		
		txtContratoSelected = new JTextField();
		txtContratoSelected.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtContratoSelected.setEditable(false);
		txtContratoSelected.setBackground(INPUT_DARK);
		txtContratoSelected.setForeground(ACCENT_ORANGE);
		txtContratoSelected.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListarParaAsigContrato.seleted = null;
				ListarParaAsigContrato listAsigCont = new ListarParaAsigContrato();
				listAsigCont.loadHeaders();
				listAsigCont.setModal(true);
				listAsigCont.setVisible(true);
				
				if(ListarParaAsigContrato.seleted != null) {
					txtContratoSelected.setText(ListarParaAsigContrato.seleted.getNumeroContrato());
				}
				else {
					txtContratoSelected.setText("");
				}
				
			}
		});
		txtContratoSelected.setBounds(20, 150, 200, 30);
		panelContent.add(txtContratoSelected);

		// --- SECCIÓN TIPOS Y PRIORIDADES (ORGANIZADOS) ---
		JLabel lblTipo = new JLabel("Tipo de Ticket:");
		lblTipo.setForeground(Color.WHITE);
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipo.setBounds(20, 262, 150, 20);
		panelContent.add(lblTipo);

		rdbtnAveria = new JRadioButton("Avería");
		rdbtnAveria.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnAveria.setForeground(Color.WHITE);
		rdbtnAveria.setBackground(NAVY_ALTICE);
		rdbtnAveria.setBounds(20, 287, 100, 25);
		panelContent.add(rdbtnAveria);

		rdbtnInstalacion = new JRadioButton("Instalación");
		rdbtnInstalacion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnInstalacion.setForeground(Color.WHITE);
		rdbtnInstalacion.setBackground(NAVY_ALTICE);
		rdbtnInstalacion.setBounds(120, 287, 120, 25);
		panelContent.add(rdbtnInstalacion);

		JLabel lblPrioridad = new JLabel("Nivel de Prioridad:");
		lblPrioridad.setForeground(Color.WHITE);
		lblPrioridad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrioridad.setBounds(20, 332, 150, 20);
		panelContent.add(lblPrioridad);

		rdbtnBaja = new JRadioButton("Baja");
		rdbtnBaja.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnBaja.setForeground(Color.WHITE);
		rdbtnBaja.setBackground(NAVY_ALTICE);
		rdbtnBaja.setBounds(20, 357, 70, 25);
		panelContent.add(rdbtnBaja);

		rdbtnMedia = new JRadioButton("Media");
		rdbtnMedia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnMedia.setForeground(Color.WHITE);
		rdbtnMedia.setBackground(NAVY_ALTICE);
		rdbtnMedia.setBounds(95, 358, 75, 25);
		panelContent.add(rdbtnMedia);

		rdbtnAlta = new JRadioButton("Alta");
		rdbtnAlta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnAlta.setForeground(Color.WHITE);
		rdbtnAlta.setBackground(NAVY_ALTICE);
		rdbtnAlta.setBounds(185, 357, 70, 25);
		panelContent.add(rdbtnAlta);

		// --- PANEL DERECHO: INFO CLIENTE ---
		JLabel lblInfoC = new JLabel("Información del Cliente");
		lblInfoC.setForeground(ACCENT_ORANGE);
		lblInfoC.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInfoC.setBounds(280, 15, 250, 30);
		panelContent.add(lblInfoC);

		JPanel panelDatosCliente = new JPanel();
		panelDatosCliente.setBackground(NAVY_ALTICE);
		panelDatosCliente.setBorder(new TitledBorder(new LineBorder(Color.GRAY), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDatosCliente.setBounds(280, 55, 310, 280);
		panelContent.add(panelDatosCliente);
		panelDatosCliente.setLayout(null);
		
		JLabel lblNom = new JLabel("Nombre:");
		lblNom.setForeground(Color.LIGHT_GRAY);
		lblNom.setBounds(10, 10, 100, 15);
		panelDatosCliente.add(lblNom);
		
		txtNombreCliente = new JTextField();
		txtNombreCliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNombreCliente.setEditable(false);
		txtNombreCliente.setBackground(INPUT_DARK);
		txtNombreCliente.setForeground(Color.WHITE);
		txtNombreCliente.setBounds(10, 30, 290, 25);
		panelDatosCliente.add(txtNombreCliente);
		
		JLabel lblDoc = new JLabel("RNC / Cédula:");
		lblDoc.setForeground(Color.LIGHT_GRAY);
		lblDoc.setBounds(10, 65, 150, 15);
		panelDatosCliente.add(lblDoc);
		
		txtRNC = new JTextField();
		txtRNC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtRNC.setEditable(false);
		txtRNC.setBackground(INPUT_DARK);
		txtRNC.setForeground(Color.WHITE);
		txtRNC.setBounds(10, 85, 290, 25);
		panelDatosCliente.add(txtRNC);
		
		JLabel lblDir = new JLabel("Dirección:");
		lblDir.setForeground(Color.LIGHT_GRAY);
		lblDir.setBounds(10, 120, 100, 15);
		panelDatosCliente.add(lblDir);
		
		JTextPane txtPane_Direccion_Cliente = new JTextPane();
		txtPane_Direccion_Cliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPane_Direccion_Cliente.setBackground(INPUT_DARK);
		txtPane_Direccion_Cliente.setForeground(Color.WHITE);
		txtPane_Direccion_Cliente.setEditable(false);
		txtPane_Direccion_Cliente.setBounds(10, 140, 290, 125);
		panelDatosCliente.add(txtPane_Direccion_Cliente);

		// --- SECCIÓN INFERIOR: COMENTARIOS ---
		JLabel lblComentario = new JLabel("Comentario del Ticket:");
		lblComentario.setForeground(ACCENT_ORANGE);
		lblComentario.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblComentario.setBounds(20, 404, 226, 25);
		panelContent.add(lblComentario);
		
		txtPane_Comentrio_Ticket = new JTextPane();
		txtPane_Comentrio_Ticket.setForeground(Color.WHITE);
		txtPane_Comentrio_Ticket.setBackground(INPUT_DARK);
		txtPane_Comentrio_Ticket.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPane_Comentrio_Ticket.setBounds(20, 439, 570, 331);
		panelContent.add(txtPane_Comentrio_Ticket);
		
		JLabel lblAviso = new JLabel("ℹ Esta información no se puede modificar una vez creada.");
		lblAviso.setForeground(Color.LIGHT_GRAY);
		lblAviso.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblAviso.setBounds(20, 780, 400, 20);
		panelContent.add(lblAviso);
		
		JLabel lblIdTcnicoclick = new JLabel("ID Técnico (Click):");
		lblIdTcnicoclick.setForeground(Color.WHITE);
		lblIdTcnicoclick.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIdTcnicoclick.setBounds(20, 190, 150, 20);
		panelContent.add(lblIdTcnicoclick);
		
		txtTecnico = new JTextField();
		txtTecnico.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListarParaAsigTec.selected = null;
				ListarParaAsigTec listTec = new ListarParaAsigTec();
				listTec.loadHeader();
				listTec.setModal(true);
				listTec.setVisible(true);
				
				if(ListarParaAsigTec.selected != null) {
					txtTecnico.setText(ListarParaAsigTec.selected.getNombre());
				}else {
					txtTecnico.setText("");
				}
			}
		});
		txtTecnico.setForeground(new Color(246, 114, 75));
		txtTecnico.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTecnico.setEditable(false);
		txtTecnico.setBackground(new Color(43, 51, 73));
		txtTecnico.setBounds(20, 215, 200, 30);
		panelContent.add(txtTecnico);

		// --- PANEL DE BOTONES ---
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(INPUT_DARK);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		btnVerLista = new JButton("Ver lista");
		btnVerLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarTiketsAsignar list = new ListarTiketsAsignar();
				list.setModal(true);
				list.setVisible(true);
			}
		});
		btnVerLista.setForeground(Color.WHITE);
		btnVerLista.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVerLista.setBackground(new Color(246, 114, 75));
		buttonPane.add(btnVerLista);
		
		JButton btnCrear = new JButton("Crear Ticket");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ejecutarCreacionTickets();
			}

		});
		btnCrear.setBackground(ACCENT_ORANGE);
		btnCrear.setForeground(Color.WHITE);
		btnCrear.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonPane.add(btnCrear);
		
		JButton btnSalir = new JButton("Cancelar");
		btnSalir.addActionListener(e -> dispose());
		btnSalir.setBackground(new Color(192, 57, 43));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonPane.add(btnSalir);
	}

	private void ejecutarCreacionTickets() {
		if(!estanLlenos()) {
			JOptionPane.showMessageDialog(null, "Error: Al parecer hay datos que son obligatorios vacíos.", "Altice", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		String idTicket = txtID_Ticket.getText();
		String comentario = txtPane_Comentrio_Ticket.getText();
		Ticket.Prioridad prioridad = null;
		if(rdbtnAlta.isSelected()) {
			prioridad = Ticket.Prioridad.ALTA;
		}
		if(rdbtnMedia.isSelected()) {
			prioridad = Ticket.Prioridad.MEDIA;
		}
		if(rdbtnBaja.isSelected()) {
			prioridad = Ticket.Prioridad.BAJA;
		}

		Ticket.Tipo tipoSeleccionado = rdbtnAveria.isSelected() ? Ticket.Tipo.AVERIA : Ticket.Tipo.INSTALACION;
		
		try {
			Ticket nuevoTicket = new Ticket(idTicket, ListarParaAsigContrato.seleted.getCli(), ListarParaAsigContrato.seleted, tipoSeleccionado, prioridad, comentario, null);
			
			Altice.getInstance().agregarTiket(nuevoTicket);
			JOptionPane.showMessageDialog(null, "Ticket registrado con éxito bajo la categoría: " + tipoSeleccionado, "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        
	        clean();
	        txtID_Ticket.setText("TK-" + Altice.getInstance().getIdTiket());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al crear el ticket: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void clean() {
		txtContratoSelected.setText("");
		txtPane_Comentrio_Ticket.setText("");
		txtNombreCliente.setText("");
		txtRNC.setText("");
		rdbtnAlta.setSelected(false);
		rdbtnAveria.setSelected(false);
		rdbtnBaja.setSelected(false);
		rdbtnMedia.setSelected(false);
		rdbtnInstalacion.setSelected(false);
	}
	
	private boolean estanLlenos() {
		return !txtContratoSelected.getText().trim().isEmpty() && !txtPane_Comentrio_Ticket.getText().trim().isEmpty();
	}
}