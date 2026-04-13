package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Altice;
import logico.Empleado;
import logico.Usuario;
import logico.Usuario.rolEmp;

import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class RegistrarEmpleado extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel panelReg = new JPanel();
	private Empleado emp = null;
	private JButton btnRegistrar;
	private JButton btnCancelar;
	private JTextField txtNombre;
	private JTextField txtRNC;
	private JTextField txtTelefono;
	private JTextField txtCorreo;
	private JTextField txtDireccion;
	public JRadioButton rdbtnMasc;
	public JRadioButton rdbtnFem;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtDepartamento;
	private JLabel lblIdEmpleado;
	private JSpinner spnEdad;
	public JComboBox cbxRol;
	private JButton btnGenerar;
	private boolean detalle = false;
	private JLabel lblTitulo;
	/**
	 * Launch the application.
	 */
	 public static void main(String[] args) {
		 try {
			 RegistrarEmpleado dialog = new RegistrarEmpleado(null);
			 dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			 dialog.setVisible(true);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
	 }

	 /**
	  * Create the dialog.
	  */
	 public RegistrarEmpleado(Empleado empleado){
		 setTitle("Registrar Empleado");
		 setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrarEmpleado.class.getResource("/Imagenes/AlticeLogoVentanas.PNG")));
		 emp = empleado;
		 if(emp == null) {
			 setTitle("Registrar Empleado");
		 }else {
			 setTitle("Actualizar Empleado");
		 }
		 setResizable(false);
		 setBounds(100, 100, 687, 669);
		 setLocationRelativeTo(null);
		 getContentPane().setLayout(new BorderLayout());
		 panelReg.setBorder(new EmptyBorder(0, 0, 0, 0));
		 getContentPane().add(panelReg, BorderLayout.CENTER);
		 panelReg.setLayout(null);
		 {
			 JPanel panel = new JPanel();
			 panel.setBackground(new Color(44,51,70));
			 panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			 panel.setBounds(345, 92, 280, 452);
			 panelReg.add(panel);
			 panel.setLayout(null);

			 JPanel panelUsuario = new JPanel();
			 panelUsuario.setBackground(new Color(255, 91, 36));
			 panelUsuario.setBounds(17, 17, 48, 48);
			 panel.add(panelUsuario);
			 panelUsuario.setLayout(null);

			 JLabel lblNewLabel_5 = new JLabel("");
			 lblNewLabel_5.setIcon(new ImageIcon(RegistrarEmpleado.class.getResource("/Imagenes/usuario.png")));
			 lblNewLabel_5.setBounds(8, 1, 38, 47);
			 panelUsuario.add(lblNewLabel_5);

			 JLabel lblNewLabel_6 = new JLabel("ID Empleado");
			 lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
			 lblNewLabel_6.setForeground(Color.WHITE);
			 lblNewLabel_6.setBounds(75, 20, 82, 14);
			 panel.add(lblNewLabel_6);

			 lblIdEmpleado = new JLabel("EMP-" + Altice.getInstance().getIdEmpleado());
			 lblIdEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 30));
			 lblIdEmpleado.setForeground(Color.WHITE);
			 lblIdEmpleado.setBounds(74, 33, 188, 32);
			 panel.add(lblIdEmpleado);

			 txtUsername = new JTextField();
			 txtUsername.setBounds(17, 108, 246, 20);
			 panel.add(txtUsername);
			 txtUsername.setColumns(10);

			 txtDepartamento = new JTextField();
			 txtDepartamento.setEditable(false);
			 txtDepartamento.setColumns(10);
			 txtDepartamento.setBounds(17, 349, 246, 20);

			 panel.add(txtDepartamento);

			 JLabel lblNewLabel_8 = new JLabel("Username");
			 lblNewLabel_8.setForeground(Color.WHITE);
			 lblNewLabel_8.setBounds(19, 88, 75, 14);
			 panel.add(lblNewLabel_8);

			 JLabel lblNewLabel_8_2 = new JLabel("Rol");
			 lblNewLabel_8_2.setForeground(Color.WHITE);
			 lblNewLabel_8_2.setBounds(17, 268, 46, 14);
			 panel.add(lblNewLabel_8_2);

			 JLabel lblNewLabel_8_2_1 = new JLabel("Departamento");
			 lblNewLabel_8_2_1.setForeground(Color.WHITE);
			 lblNewLabel_8_2_1.setBounds(17, 324, 95, 14);
			 panel.add(lblNewLabel_8_2_1);

			 JLabel lblNewLabel_8_1 = new JLabel("Contraseña");
			 lblNewLabel_8_1.setBounds(17, 139, 77, 14);
			 panel.add(lblNewLabel_8_1);
			 lblNewLabel_8_1.setForeground(Color.WHITE);

			 txtPassword = new JTextField();
			 txtPassword.setBounds(17, 160, 246, 20);
			 panel.add(txtPassword);
			 txtPassword.setColumns(10);

			 btnGenerar = new JButton("Generar Contraseña");
			 btnGenerar.addActionListener(new ActionListener() {
				 private String pass;

				 public void actionPerformed(ActionEvent e) {
					 pass = generarPassword();
					 txtPassword.setText(pass);
				 }
			 });
			 btnGenerar.setBounds(17, 201, 162, 23);
			 panel.add(btnGenerar);

			 cbxRol = new JComboBox();
			 cbxRol.addActionListener(new ActionListener() {
				 public void actionPerformed(ActionEvent e) {
					 if (cbxRol.getSelectedItem().toString().equalsIgnoreCase("Administrador")) {
						 txtDepartamento.setText("Administración");
					 }
					 if (cbxRol.getSelectedItem().toString().equalsIgnoreCase("Comercial")) {
						 txtDepartamento.setText("Ventas y A.C.");
					 }
					 if (cbxRol.getSelectedItem().toString().equalsIgnoreCase("Técnico")) {
						 txtDepartamento.setText("Infra. y Redes");
					 }
				 }
			 });
			 cbxRol.setModel(new DefaultComboBoxModel(new String[] {"<<Seleccione>>", "Administrador", "Comercial", "Técnico"}));
			 cbxRol.setBounds(17, 292, 246, 22);
			 panel.add(cbxRol);
			 panelReg.setBackground(new Color(29,41,59));
		 }

		 JLabel lblNewLabel = new JLabel("Nombre Completo");
		 lblNewLabel.setForeground(Color.WHITE);
		 lblNewLabel.setBounds(38, 95, 147, 14);
		 panelReg.add(lblNewLabel);

		 JLabel lblNewLabel_1 = new JLabel("RNC");
		 lblNewLabel_1.setForeground(Color.WHITE);
		 lblNewLabel_1.setBounds(38, 151, 80, 14);
		 panelReg.add(lblNewLabel_1);

		 JLabel lblNewLabel_2 = new JLabel("Edad");
		 lblNewLabel_2.setForeground(Color.WHITE);
		 lblNewLabel_2.setBounds(38, 207, 80, 14);
		 panelReg.add(lblNewLabel_2);

		 JLabel lblNewLabel_3 = new JLabel("Info. de Usuario y Sistema");
		 lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		 lblNewLabel_3.setForeground(Color.WHITE);
		 lblNewLabel_3.setBounds(348, 68, 190, 14);
		 panelReg.add(lblNewLabel_3);

		 lblTitulo = new JLabel("Crear Nuevo Empleado");

		 if(emp == null) {
			 lblTitulo.setText("Crear Nuevo Empleado");
		 }else {
			 lblTitulo.setText("Actualizar Empleado");				 
		 }

		 lblTitulo.setForeground(Color.WHITE);
		 lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		 lblTitulo.setBounds(38, 23, 280, 36);
		 panelReg.add(lblTitulo);

		 JLabel lblNewLabel_3_1 = new JLabel("Datos Personales");
		 lblNewLabel_3_1.setForeground(Color.WHITE);
		 lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		 lblNewLabel_3_1.setBounds(38, 70, 190, 14);
		 panelReg.add(lblNewLabel_3_1);

		 JLabel lblNewLabel_4 = new JLabel("Sexo");
		 lblNewLabel_4.setForeground(Color.WHITE);
		 lblNewLabel_4.setBounds(38, 262, 80, 14);
		 panelReg.add(lblNewLabel_4);

		 JLabel lblNewLabel_1_1 = new JLabel("Teléfono de Contacto");
		 lblNewLabel_1_1.setForeground(Color.WHITE);
		 lblNewLabel_1_1.setBounds(38, 327, 123, 14);
		 panelReg.add(lblNewLabel_1_1);

		 JLabel lblNewLabel_2_1 = new JLabel("Correo Electrónico");
		 lblNewLabel_2_1.setForeground(Color.WHITE);
		 lblNewLabel_2_1.setBounds(38, 383, 123, 14);
		 panelReg.add(lblNewLabel_2_1);

		 JLabel lblNewLabel_2_1_1 = new JLabel("Dirección");
		 lblNewLabel_2_1_1.setForeground(Color.WHITE);
		 lblNewLabel_2_1_1.setBounds(38, 438, 80, 14);
		 panelReg.add(lblNewLabel_2_1_1);

		 txtNombre = new JTextField();
		 txtNombre.setBounds(38, 120, 266, 20);
		 panelReg.add(txtNombre);
		 txtNombre.setColumns(10);

		 txtRNC = new JTextField();
		 txtRNC.addFocusListener(new FocusAdapter() {
			 @Override
			 public void focusLost(FocusEvent e) {
				 if(emp == null && txtUsername.getText().isEmpty()) {
					 txtUsername.setText(txtRNC.getText());
				 }
			 }
			 @Override
			 public void focusGained(FocusEvent e) {
				 if(emp == null) {
					 txtUsername.setText("");
				 }
			 }
		 });
		 txtRNC.setColumns(10);
		 txtRNC.setBounds(38, 176, 266, 20);
		 panelReg.add(txtRNC);
		 txtTelefono = new JTextField();
		 txtTelefono.setColumns(10);
		 txtTelefono.setBounds(38, 352, 266, 20);
		 panelReg.add(txtTelefono);

		 txtCorreo = new JTextField();
		 txtCorreo.setColumns(10);
		 txtCorreo.setBounds(38, 407, 266, 20);
		 panelReg.add(txtCorreo);

		 txtDireccion = new JTextField();
		 txtDireccion.setColumns(10);
		 txtDireccion.setBounds(38, 463, 266, 20);
		 panelReg.add(txtDireccion);

		 rdbtnMasc = new JRadioButton("Masculino");
		 rdbtnMasc.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 rdbtnFem.setSelected(false);
			 }
		 });
		 rdbtnMasc.setForeground(Color.WHITE);
		 rdbtnMasc.setBackground(new Color(29,41,59));
		 rdbtnMasc.setBounds(38, 285, 109, 23);
		 panelReg.add(rdbtnMasc);

		 rdbtnFem = new JRadioButton("Femenino");
		 rdbtnFem.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 rdbtnMasc.setSelected(false);
			 }
		 });
		 rdbtnFem.setForeground(Color.WHITE);
		 rdbtnFem.setBackground(new Color(29,41,59));
		 rdbtnFem.setBounds(168, 285, 109, 23);
		 panelReg.add(rdbtnFem);

		 spnEdad = new JSpinner();
		 spnEdad.setModel(new SpinnerNumberModel(0, 0, 80, 1));
		 spnEdad.setBounds(38, 231, 92, 20);
		 panelReg.add(spnEdad);
		 {
			 JPanel buttonPane = new JPanel();
			 buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			 buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			 getContentPane().add(buttonPane, BorderLayout.SOUTH);
			 {
				 btnRegistrar = new JButton("Registrar");
				 btnRegistrar.addActionListener(new ActionListener() {
					 public void actionPerformed(ActionEvent e) {
						 if(!camposLlenos()) {
							 JOptionPane.showMessageDialog(null, "Error: Todos los campos son obligatorios.","Información", JOptionPane.INFORMATION_MESSAGE);
							 return;
						 }

						 if(emp == null) {
							 Usuario us= crearUsuario();
							 rolEmp rolUser = us.getRol();
							 Empleado aux = new Empleado(txtNombre.getText(), us, new Integer(spnEdad.getValue().toString()),txtDireccion.getText(),getSexo(), txtTelefono.getText(), txtCorreo.getText(), txtRNC.getText(),lblIdEmpleado.getText());

							 if(rolUser !=null) {
								 if (rolUser == rolEmp.ADMINISTRADOR) {
									 aux.setDepartamento("Administración");
								 } else if (rolUser == rolEmp.COMERCIAL) {
									 aux.setDepartamento("Ventas y A.C."); 
								 } else if (rolUser == rolEmp.TECNICO) {
									 aux.setDepartamento("Infra. y Redes");
								 }
							 }
							 Altice.getInstance().regPersona(aux);
							 JOptionPane.showMessageDialog(null, "Operación exitosa", "Información", JOptionPane.INFORMATION_MESSAGE);
							 clean();
						 }

						 else {
							 //Actualizar contenido Empleado
							 emp.setNombre(txtNombre.getText());
							 //emp.setRnc(txtRNC.getText()); -> EXCEPTO EL RNC (que como ya lo teniamos, era la cedula si es una persona), no se cambia nunca, ni el ID (por eso no lo puse)
							 emp.setEdad((Integer)spnEdad.getValue());
							 emp.setSexo(getSexo());
							 emp.setNumeroContacto(txtTelefono.getText()); 
							 emp.setDireccion(txtDireccion.getText());   
							 emp.setCorreo(txtCorreo.getText());

							 // Actualizamos los datos de Usuario asociados al empleado
							 emp.getUser().setUserName(txtUsername.getText());
							 emp.getUser().setPassword(txtPassword.getText());

							 //Rol y el Departamento
							 String selectedRol = cbxRol.getSelectedItem().toString();
							 if (selectedRol.equalsIgnoreCase("Administrador")) {
								 emp.getUser().setRol(rolEmp.ADMINISTRADOR);
								 emp.setDepartamento("Administración");
							 } else if (selectedRol.equalsIgnoreCase("Comercial")) {
								 emp.getUser().setRol(rolEmp.COMERCIAL);
								 emp.setDepartamento("Ventas y A.C.");
							 } else if (selectedRol.equalsIgnoreCase("Técnico")) {
								 emp.getUser().setRol(rolEmp.TECNICO);
								 emp.setDepartamento("Infra. y Redes");
							 }

							 //Que la controladora guarde la nueva info
							 Altice.getInstance().actualizarEmpleado(emp);
							 JOptionPane.showMessageDialog(null, "Empleado actualizado correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
							 ListarEmpleados.loadEmpleados(); //cargar datos
							 ListarEmpleados.btnDespedir.setEnabled(false); 
							 ListarEmpleados.btnActualizar.setEnabled(false); 
							 dispose();
						 }

					 }
				 });
				 btnRegistrar.setActionCommand("OK");
				 buttonPane.add(btnRegistrar);
				 getRootPane().setDefaultButton(btnRegistrar);
			 }
			 {
				 btnCancelar = new JButton("Cancelar");
				 btnCancelar.addActionListener(new ActionListener() {
					 public void actionPerformed(ActionEvent e) {
						 dispose();
					 }
				 });
				 btnCancelar.setActionCommand("Cancel");
				 buttonPane.add(btnCancelar);
			 }
		 }
		 cargarDatosActualizar();
	 }

	 public String generarPassword() {
		 String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		 Random random = new Random();
		 String password = ""; //String vacio   
		 for (int i = 0; i < 8; i++) {
			 int index = random.nextInt(caracteres.length());
			 //pegar al final 
			 password = password + caracteres.charAt(index);
		 }
		 return password;
	 }

	 public void clean() {
		 lblIdEmpleado.setText("EMP-" + Altice.getInstance().getIdEmpleado());
		 txtNombre.setText("");
		 txtRNC.setText("");
		 spnEdad.setValue(new Integer(0));
		 rdbtnFem.setSelected(false);
		 rdbtnMasc.setSelected(false);
		 txtTelefono.setText("");
		 txtCorreo.setText("");
		 txtDireccion.setText("");
		 txtUsername.setText("");
		 txtPassword.setText("");
		 cbxRol.setSelectedIndex(new Integer(0));
		 txtDepartamento.setText("");
	 }

	 public boolean camposLlenos() {
		 boolean lleno = true;
		 if (txtNombre.getText().trim().isEmpty() ||  txtRNC.getText().trim().isEmpty() || txtTelefono.getText().trim().isEmpty() || txtCorreo.getText().trim().isEmpty() || 
				 txtDireccion.getText().trim().isEmpty() || txtUsername.getText().trim().isEmpty() || txtPassword.getText().trim().isEmpty()) {
			 lleno = false;
		 }
		 if ((int) spnEdad.getValue() <= 0) {
			 lleno = false;
		 }
		 if (!rdbtnMasc.isSelected() && !rdbtnFem.isSelected()) {
			 lleno = false;
		 }

		 if (cbxRol.getSelectedIndex() == 0) {
			 lleno = false;
		 }

		 return lleno;
	 }


	 public Usuario crearUsuario(){
		 String selected = cbxRol.getSelectedItem().toString();

		 rolEmp rolElegido = null;
		 if (selected.equalsIgnoreCase("Administrador")) {
			 rolElegido = rolEmp.ADMINISTRADOR;
		 } else if (selected.equalsIgnoreCase("Comercial")) {
			 rolElegido = rolEmp.COMERCIAL;
		 } else if (selected.equalsIgnoreCase("Técnico")) {
			 rolElegido = rolEmp.TECNICO;
		 }

		 Usuario user = new Usuario(rolElegido, txtUsername.getText(),txtPassword.getText());
		 return user;
	 }

	 public String getSexo() {
		 String sexo=null;
		 if (rdbtnMasc.isSelected()) {
			 sexo = "Masculino";
		 }

		 if (rdbtnFem.isSelected()) {
			 sexo = "Femenino";
		 }

		 return sexo;
	 }
	 public void cargarDatosActualizar() {
		 if (emp != null) {
			 lblIdEmpleado.setText(emp.getIdEmpleado());
			 txtRNC.setText(emp.getRnc());
			 txtRNC.setEditable(false); 

			 // Datos Personales
			 txtNombre.setText(emp.getNombre());
			 spnEdad.setValue(emp.getEdad());
			 txtTelefono.setText(emp.getNumeroContacto());
			 txtCorreo.setText(emp.getCorreo());
			 txtDireccion.setText(emp.getDireccion());

			 if (emp.getSexo().equalsIgnoreCase("Masculino")) {
				 rdbtnMasc.setSelected(true);
			 } else {
				 rdbtnFem.setSelected(true);
			 }

			 // Datos de Usuario y Sistema
			 txtUsername.setText(emp.getUser().getUserName());
			 txtPassword.setText(emp.getUser().getPassword());
			 txtDepartamento.setText(emp.getDepartamento());

			 rolEmp elRol = emp.getUser().getRol();
			 if (elRol == rolEmp.ADMINISTRADOR) {
				 cbxRol.setSelectedItem("Administrador");
			 } else if (elRol == rolEmp.COMERCIAL) {
				 cbxRol.setSelectedItem("Comercial");
			 } else if (elRol == rolEmp.TECNICO) {
				 cbxRol.setSelectedItem("Técnico");
			 }

			 btnRegistrar.setText("Actualizar");
		 }
	 }

	 public String usuarioAuto() {
		 String usuario = null; 
		 if(!txtRNC.getText().trim().isEmpty()) {
			 usuario = txtRNC.getText();				
		 }
		 return usuario; 
	 }

	 public void modoDetalle() {
		 detalle = true;
		 setTitle("Detalle del Empleado");
		 lblTitulo.setText("Detalles de Empleado");				 
		 txtNombre.setEditable(false);
		 txtRNC.setEditable(false);
		 txtTelefono.setEditable(false);
		 txtCorreo.setEditable(false);
		 txtDireccion.setEditable(false);
		 txtUsername.setEditable(false);
		 txtPassword.setEditable(false);
		 spnEdad.setEnabled(false);
		 cbxRol.setEnabled(false);
		 rdbtnMasc.setEnabled(false);
		 rdbtnFem.setEnabled(false);
		 btnRegistrar.setVisible(false); 
		 btnGenerar.setVisible(false); 
	 }


}
