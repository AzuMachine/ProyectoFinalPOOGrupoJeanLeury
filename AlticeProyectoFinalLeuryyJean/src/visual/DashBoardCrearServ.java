package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

import logico.Altice;
import logico.Servicio;
import logico.Servicio.Serv;

public class DashBoardCrearServ extends JDialog {

	private static final long serialVersionUID = 1L;

	// Colores de la paleta
	private final Color NAVY_ALTICE = new Color(33, 50, 65);      
	private final Color INPUT_DARK = new Color(43, 51, 73);       
	private final Color BURNT_SIENNA = new Color(221, 112, 87);  

	// Componentes Principales
	private final JPanel panelNum1 = new JPanel();
	private JPanel panelCreacion;
	private JPanel buttonPane;

	// Campos de Texto y Entrada
	private JTextField txtID_Serv;
	private JTextField txtNombreServ;
	private JTextField txtPrecio_Serv;
	private JTextPane txtPane_Descrip_Serv;
	private JComboBox<String> cbxFiltroServ;

	// Botones
	private JButton btnRegServ;
	private JButton btnCancelar;

	// Constantes de Precios
	private static float precioInternet = 1695f;
	private static float precioTV = 1200f;
	private static float precioTelefonia = 350f;

	public static void main(String[] args) {
		try {
			DashBoardCrearServ dialog = new DashBoardCrearServ();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DashBoardCrearServ() {
		// Configuración del JDialog
		setTitle("Registrar Servicios");
		setResizable(false);
		setBounds(100, 100, 503, 683);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		// Configuración Panel Principal
		panelNum1.setBackground(NAVY_ALTICE);
		panelNum1.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelNum1.setLayout(new BorderLayout(0, 0));
		getContentPane().add(panelNum1, BorderLayout.CENTER);

		// Panel de Creación
		panelCreacion = new JPanel();
		panelCreacion.setBackground(NAVY_ALTICE);
		panelCreacion.setBorder(new TitledBorder(new LineBorder(Color.GRAY), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCreacion.setLayout(null);
		panelNum1.add(panelCreacion, BorderLayout.CENTER);

		initComponents();
		createButtonPane();
	}

	private void initComponents() {
		// --- Sección Información General ---
		JLabel lblInfoGral = new JLabel("Información General:");
		lblInfoGral.setForeground(BURNT_SIENNA);
		lblInfoGral.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInfoGral.setBounds(10, 23, 199, 25);
		panelCreacion.add(lblInfoGral);

		cbxFiltroServ = new JComboBox<>();
		cbxFiltroServ.setBackground(INPUT_DARK);
		cbxFiltroServ.setForeground(BURNT_SIENNA);
		cbxFiltroServ.setModel(new DefaultComboBoxModel<>(new String[] {
				"<<Seleccionar>>", 
				"INTERNET – Fibra Óptica", 
				"TELEVISIÓN – Cable Básico HD", 
				"TELEFONÍA – Fija (Voz Digital)"
		}));
		cbxFiltroServ.setBounds(251, 27, 215, 20);
		cbxFiltroServ.addActionListener(e -> actualizarSegunSeleccion());
		panelCreacion.add(cbxFiltroServ);

		// --- Panel de Campos de Datos ---
		JPanel panelCampos = new JPanel();
		panelCampos.setBackground(NAVY_ALTICE);
		panelCampos.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelCampos.setBounds(10, 77, 456, 364);
		panelCampos.setLayout(null);
		panelCreacion.add(panelCampos);

		JLabel lblID = new JLabel("ID:");
		lblID.setForeground(Color.WHITE);
		lblID.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblID.setBounds(10, 28, 44, 12);
		panelCampos.add(lblID);

		txtID_Serv = new JTextField();
		txtID_Serv.setBackground(INPUT_DARK);
		txtID_Serv.setForeground(BURNT_SIENNA);
		txtID_Serv.setCaretColor(Color.WHITE);
		txtID_Serv.setEditable(false);
		txtID_Serv.setBounds(10, 50, 155, 25); // Un poco más alto para mejor visibilidad
		panelCampos.add(txtID_Serv);

		JLabel lblNombre = new JLabel("Nombre del Servicio:");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombre.setBounds(10, 93, 194, 15);
		panelCampos.add(lblNombre);

		txtNombreServ = new JTextField();
		txtNombreServ.setBackground(INPUT_DARK);
		txtNombreServ.setForeground(Color.WHITE);
		txtNombreServ.setCaretColor(Color.WHITE);
		txtNombreServ.setBounds(10, 118, 391, 25);
		panelCampos.add(txtNombreServ);

		JLabel lblDesc = new JLabel("Descripción del Servicio:");
		lblDesc.setForeground(Color.WHITE);
		lblDesc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDesc.setBounds(10, 166, 194, 15);
		panelCampos.add(lblDesc);

		txtPane_Descrip_Serv = new JTextPane();
		txtPane_Descrip_Serv.setBackground(INPUT_DARK);
		txtPane_Descrip_Serv.setForeground(Color.WHITE);
		txtPane_Descrip_Serv.setCaretColor(Color.WHITE);
		txtPane_Descrip_Serv.setBounds(10, 191, 391, 139);
		panelCampos.add(txtPane_Descrip_Serv);

		// --- Sección Detalles de Precio ---
		JLabel lblDetallesAdic = new JLabel("Detalles adicionales:");
		lblDetallesAdic.setForeground(BURNT_SIENNA);
		lblDetallesAdic.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDetallesAdic.setBounds(10, 453, 199, 25);
		panelCreacion.add(lblDetallesAdic);

		JPanel panelPrecio = new JPanel();
		panelPrecio.setBackground(NAVY_ALTICE);
		panelPrecio.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelPrecio.setBounds(10, 488, 456, 76);
		panelPrecio.setLayout(null);
		panelCreacion.add(panelPrecio);

		JLabel lblPrecioTit = new JLabel("Precio del servicio seleccionado ($):");
		lblPrecioTit.setForeground(Color.WHITE);
		lblPrecioTit.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrecioTit.setBounds(93, 8, 267, 14);
		panelPrecio.add(lblPrecioTit);

		txtPrecio_Serv = new JTextField();
		txtPrecio_Serv.setBackground(INPUT_DARK);
		txtPrecio_Serv.setForeground(BURNT_SIENNA);
		txtPrecio_Serv.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrecio_Serv.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtPrecio_Serv.setEditable(false);
		txtPrecio_Serv.setBounds(93, 32, 230, 30);
		panelPrecio.add(txtPrecio_Serv);
	}

	private void createButtonPane() {
		buttonPane = new JPanel();
		buttonPane.setBackground(INPUT_DARK);
		buttonPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		btnRegServ = new JButton("Registrar");
		btnRegServ.setBackground(BURNT_SIENNA);
		btnRegServ.setForeground(Color.BLACK);
		btnRegServ.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRegServ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cumple = cbxFiltroServ.getSelectedItem().toString();
				if(cumple.equalsIgnoreCase("<<Seleccionar>>")) {
					JOptionPane.showMessageDialog(null, "Error: Debe seleccionar un tipo.", "Información", JOptionPane.WARNING_MESSAGE);
					return;
				} else {
					String seleccion = cbxFiltroServ.getSelectedItem().toString();
					Serv servEnum = null;

					if(!estanLlenos(seleccion)) {
						JOptionPane.showMessageDialog(null, "Error: Todos los campos son obligatorios.", "Información", JOptionPane.WARNING_MESSAGE);
						return;
					}

					try {
						if(seleccion.equalsIgnoreCase("INTERNET – Fibra Óptica")) {
							servEnum = Serv.INTERNET;
							Servicio internet = new Servicio(txtID_Serv.getText(), txtNombreServ.getText(), txtPane_Descrip_Serv.getText(), servEnum, precioInternet, true);
							Altice.getInstance().guardarServ(internet);
						} else if (seleccion.equalsIgnoreCase("TELEVISIÓN – Cable Básico HD")) {
							servEnum = Serv.TELEVISION;
							Servicio television = new Servicio(txtID_Serv.getText(), txtNombreServ.getText(), txtPane_Descrip_Serv.getText(), servEnum, precioTV, true);
							Altice.getInstance().guardarServ(television);
						} else if (seleccion.equalsIgnoreCase("TELEFONÍA – Fija (Voz Digital)")) {
							servEnum = Serv.TELEFONIA;
							Servicio telefonia = new Servicio(txtID_Serv.getText(), txtNombreServ.getText(), txtPane_Descrip_Serv.getText(), servEnum, precioTelefonia, true);
							Altice.getInstance().guardarServ(telefonia);
						}

						JOptionPane.showMessageDialog(null, "Operación exitosa", "Información", JOptionPane.INFORMATION_MESSAGE);
						clean(seleccion);

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Error inesperado: " + e2.getMessage());
					}
				}
			}
		});
		buttonPane.add(btnRegServ);
		getRootPane().setDefaultButton(btnRegServ);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.DARK_GRAY);
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancelar.addActionListener(e -> dispose());
		buttonPane.add(btnCancelar);
	}

	private void clean(String seleccion) {
		txtNombreServ.setText("");
		txtPane_Descrip_Serv.setText("");
		actualizarSegunSeleccion();
	}

	private void actualizarSegunSeleccion() {
		String seleccion = cbxFiltroServ.getSelectedItem().toString();
		if(seleccion.equalsIgnoreCase("INTERNET – Fibra Óptica")) {
			txtID_Serv.setText("ALT-FIB-" + Altice.idServFIB);
			txtPrecio_Serv.setText("RD$ " + precioInternet);
		} else if(seleccion.equalsIgnoreCase("TELEVISIÓN – Cable Básico HD")) {
			txtID_Serv.setText("ALT-TV-" + Altice.idServTV);
			txtPrecio_Serv.setText("RD$ " + precioTV);
		} else if(seleccion.equalsIgnoreCase("TELEFONÍA – Fija (Voz Digital)")) {
			txtID_Serv.setText("ALT-TEL-" + Altice.idServTEL);
			txtPrecio_Serv.setText("RD$ " + precioTelefonia);
		} else {
			txtID_Serv.setText("");
			txtPrecio_Serv.setText("");
		}
	}

	private boolean estanLlenos(String tipo) {
		if (cbxFiltroServ.getSelectedIndex() == 0) return false;
		return !txtNombreServ.getText().trim().isEmpty() && !txtPane_Descrip_Serv.getText().trim().isEmpty();
	}
}