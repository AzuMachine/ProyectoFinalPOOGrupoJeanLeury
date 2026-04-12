package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog; // Cambiado
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import logico.Altice;
import logico.Servicio;
import logico.Servicio.Serv;

public class DashBoardCrearServ extends JDialog { // Cambiado a JDialog

	private static final long serialVersionUID = 1L;

	private final Color NAVY_ALTICE = new Color(33, 50, 65);      
	private final Color INPUT_DARK = new Color(43, 51, 73);        
	private final Color BURNT_SIENNA = new Color(221, 112, 87);  

	private JPanel contentPane;
	private JPanel panelCreacion;
	private JPanel buttonPane;

	private JTextField txtID_Serv;
	private JTextField txtNombreServ;
	private JTextField txtPrecio_Serv;
	private JTextPane txtPane_Descrip_Serv;
	private JComboBox<String> cbxFiltroServ;

	private JButton btnRegServ;
	private JButton btnCancelar;
	private JButton btnListar;

	private Servicio miServi = null;

	public static void main(String[] args) {
		try {
			DashBoardCrearServ dialog = new DashBoardCrearServ(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DashBoardCrearServ(Servicio selectedServ) {
		//Revisar esto aca, Jean
		//setModal(true); 
		miServi = selectedServ;
		setResizable(false);

		if(miServi == null) {
			setTitle("Registrar Servicios - Altice");
		}else {
			setTitle("Modificar Servicios - Altice");
		}

		setBounds(100, 100, 620, 800);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(NAVY_ALTICE);
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panelCreacion = new JPanel();
		panelCreacion.setBackground(NAVY_ALTICE);
		panelCreacion.setBorder(new TitledBorder(new LineBorder(Color.GRAY), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCreacion.setLayout(null);
		contentPane.add(panelCreacion, BorderLayout.CENTER);

		initComponents();
		createButtonPane();

		if(miServi != null) {
			cargarDatos();
		}
	}



	private void initComponents() {
		JLabel lblInfoGral = new JLabel("Información General:");
		lblInfoGral.setForeground(BURNT_SIENNA);
		lblInfoGral.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblInfoGral.setBounds(20, 23, 220, 30);
		panelCreacion.add(lblInfoGral);

		cbxFiltroServ = new JComboBox<>();
		cbxFiltroServ.setBackground(INPUT_DARK);
		cbxFiltroServ.setForeground(BURNT_SIENNA);
		cbxFiltroServ.setFont(new Font("Tahoma", Font.BOLD, 14));
		cbxFiltroServ.setModel(new DefaultComboBoxModel<>(new String[] {
				"<< Seleccionar tipo de servicio >>", 
				"INTERNET – Fibra Óptica", 
				"TELEVISIÓN – Cable Básico HD", 
				"TELEFONÍA – Fija (Voz Digital)"
		}));
		cbxFiltroServ.setBounds(250, 24, 330, 35);
		cbxFiltroServ.addActionListener(e -> actualizarSegunSeleccion());
		panelCreacion.add(cbxFiltroServ);

		JPanel panelCampos = new JPanel();
		panelCampos.setBackground(NAVY_ALTICE);
		panelCampos.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelCampos.setBounds(20, 80, 560, 400); 
		panelCampos.setLayout(null);
		panelCreacion.add(panelCampos);

		JLabel lblID = new JLabel("ID de Registro:");
		lblID.setForeground(Color.WHITE);
		lblID.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblID.setBounds(15, 20, 150, 20);
		panelCampos.add(lblID);

		txtID_Serv = new JTextField();
		txtID_Serv.setBackground(INPUT_DARK);
		txtID_Serv.setForeground(BURNT_SIENNA);
		txtID_Serv.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtID_Serv.setCaretColor(Color.WHITE);
		txtID_Serv.setEditable(false);
		txtID_Serv.setBounds(15, 45, 220, 35);
		panelCampos.add(txtID_Serv);

		JLabel lblNombre = new JLabel("Nombre del Servicio:");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombre.setBounds(15, 100, 250, 20);
		panelCampos.add(lblNombre);

		txtNombreServ = new JTextField();
		txtNombreServ.setBackground(INPUT_DARK);
		txtNombreServ.setForeground(Color.WHITE);
		txtNombreServ.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNombreServ.setCaretColor(Color.WHITE);
		txtNombreServ.setBounds(15, 125, 525, 40);
		panelCampos.add(txtNombreServ);

		JLabel lblDesc = new JLabel("Descripción Detallada:");
		lblDesc.setForeground(Color.WHITE);
		lblDesc.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDesc.setBounds(15, 185, 250, 20);
		panelCampos.add(lblDesc);

		txtPane_Descrip_Serv = new JTextPane();
		txtPane_Descrip_Serv.setBackground(INPUT_DARK);
		txtPane_Descrip_Serv.setForeground(Color.WHITE);
		txtPane_Descrip_Serv.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPane_Descrip_Serv.setCaretColor(Color.WHITE);
		txtPane_Descrip_Serv.setBounds(15, 210, 525, 160);
		panelCampos.add(txtPane_Descrip_Serv);

		JLabel lblDetallesAdic = new JLabel("Resumen Económico:");
		lblDetallesAdic.setForeground(BURNT_SIENNA);
		lblDetallesAdic.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDetallesAdic.setBounds(20, 500, 250, 30);
		panelCreacion.add(lblDetallesAdic);

		JPanel panelPrecio = new JPanel();
		panelPrecio.setBackground(NAVY_ALTICE);
		panelPrecio.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelPrecio.setBounds(20, 535, 560, 100);
		panelPrecio.setLayout(null);
		panelCreacion.add(panelPrecio);

		JLabel lblPrecioTit = new JLabel("Costo Mensual del Servicio ($):");
		lblPrecioTit.setForeground(Color.WHITE);
		lblPrecioTit.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPrecioTit.setBounds(155, 15, 267, 20);
		panelPrecio.add(lblPrecioTit);

		txtPrecio_Serv = new JTextField();
		txtPrecio_Serv.setBackground(INPUT_DARK);
		txtPrecio_Serv.setForeground(BURNT_SIENNA);
		txtPrecio_Serv.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrecio_Serv.setFont(new Font("Tahoma", Font.BOLD, 22));
		txtPrecio_Serv.setBounds(130, 45, 300, 45);
		panelPrecio.add(txtPrecio_Serv);
	}

	private void createButtonPane() {
		buttonPane = new JPanel();
		buttonPane.setBackground(INPUT_DARK);
		buttonPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 15));
		contentPane.add(buttonPane, BorderLayout.SOUTH);

		btnListar = new JButton("Ver Listado");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarServicios listServ = new ListarServicios();
				listServ.setModal(true);
				listServ.setVisible(true);
			}
		});

		estiloBoton(btnListar, BURNT_SIENNA, Color.BLACK);
		buttonPane.add(btnListar);

		btnRegServ = new JButton("Registrar Servicio");
		if(miServi != null) {
			btnRegServ.setText("Actualizar datos");
		}
		btnRegServ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ejecutarRegistro();
			}
		});
		estiloBoton(btnRegServ, BURNT_SIENNA, Color.BLACK);
		buttonPane.add(btnRegServ);
		getRootPane().setDefaultButton(btnRegServ);

		btnCancelar = new JButton("Cerrar");
		estiloBoton(btnCancelar, Color.DARK_GRAY, Color.WHITE);
		btnCancelar.addActionListener(e -> dispose());
		buttonPane.add(btnCancelar);
	}

	private void estiloBoton(JButton btn, Color bg, Color fg) {
		btn.setBackground(bg);
		btn.setForeground(fg);
		btn.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn.setFocusPainted(false);
	}

	private void cargarDatos() {
		txtID_Serv.setText(miServi.getIdService());
		txtNombreServ.setText(miServi.getNombre());
		txtPane_Descrip_Serv.setText(miServi.getDescTecnica());
		txtPrecio_Serv.setText(String.valueOf(miServi.getCostoMensualInd()));

		//Ebitar que el CBX de error de seleccion
		ActionListener[] listeners = cbxFiltroServ.getActionListeners();
		for (ActionListener al : listeners) {
			cbxFiltroServ.removeActionListener(al);
		}
		
		if (miServi.getTipo() == Serv.INTERNET) cbxFiltroServ.setSelectedIndex(1);
		if (miServi.getTipo() == Serv.TELEVISION) cbxFiltroServ.setSelectedIndex(2);
		if (miServi.getTipo() == Serv.TELEFONIA) cbxFiltroServ.setSelectedIndex(3);
		
		//El tipo de servicio no se modifica
		cbxFiltroServ.setEnabled(false);
		btnListar.setEnabled(false);
		
	}

	private void ejecutarRegistro() {
		if(cbxFiltroServ.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Error: Debe seleccionar un tipo de servicio.", "Altice", JOptionPane.WARNING_MESSAGE);
			return;
		}

		String seleccion = cbxFiltroServ.getSelectedItem().toString();
		if(!estanLlenos()) {
			JOptionPane.showMessageDialog(null, "Error: El nombre y la descripción son obligatorios.", "Altice", JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {

			if(miServi == null) {
				Serv servEnum = null;
				float precio = Float.parseFloat(txtPrecio_Serv.getText());

				if(seleccion.contains("INTERNET")) {
					servEnum = Serv.INTERNET;
					precio = Float.parseFloat(txtPrecio_Serv.getText());
				} else if (seleccion.contains("TELEVISIÓN")) {
					servEnum = Serv.TELEVISION;
					precio = Float.parseFloat(txtPrecio_Serv.getText());
				} else if (seleccion.contains("TELEFONÍA")) {
					servEnum = Serv.TELEFONIA;
					precio = Float.parseFloat(txtPrecio_Serv.getText());
				}

				Servicio nuevo = new Servicio(txtID_Serv.getText(), txtNombreServ.getText(), txtPane_Descrip_Serv.getText(), servEnum, precio, true);
				Altice.getInstance().guardarServ(nuevo);

				JOptionPane.showMessageDialog(null, "Servicio registrado exitosamente en el sistema.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				clean();
			}else {
				miServi.setNombre(txtNombreServ.getText());
				miServi.setDescTecnica(txtPane_Descrip_Serv.getText());
				miServi.setCostoMensualInd(Float.parseFloat(txtPrecio_Serv.getText()));

				Altice.getInstance().actualizarServicio(miServi);
				ListarServicios.loadServicios();
				ListarServicios.btnModificar.setEnabled(false);
				ListarServicios.btnEliminar.setEnabled(false);
				dispose();
			}

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Error al guardar: " + e2.getMessage());
		}
	}

	private void clean() {
		txtNombreServ.setText("");
		txtPane_Descrip_Serv.setText("");
		txtPrecio_Serv.setText("");
		actualizarSegunSeleccion();
	}

	private void actualizarSegunSeleccion() {
		
		if(miServi != null) return;
		
		int index = cbxFiltroServ.getSelectedIndex();
		switch(index) {

			case 1:
				txtID_Serv.setText("ALT-FIB-" + Altice.getInstance().getIdServFIB());
				break;
			case 2:
				txtID_Serv.setText("ALT-TV-" + Altice.getInstance().getIdServTV());
				break;
			case 3:
				txtID_Serv.setText("ALT-TEL-" + Altice.getInstance().getIdServTEL());
				break;
			default:
				txtID_Serv.setText("");
				txtPrecio_Serv.setText("");
				break;

		}
	}

	private boolean estanLlenos() {
		return !txtNombreServ.getText().trim().isEmpty() && !txtPane_Descrip_Serv.getText().trim().isEmpty();
	}
}