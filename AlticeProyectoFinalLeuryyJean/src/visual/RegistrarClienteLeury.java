package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import logico.Altice;
import logico.Cliente;
import logico.Cliente.Cat;
import logico.Usuario;
import logico.Usuario.rolEmp;

public class RegistrarClienteLeury extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel panelReg = new JPanel();
    private Cliente cli = null;
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
    private JLabel lblIdCliente;
    private JSpinner spnEdad;
    public JComboBox<String> cbxCategoria;
    private JButton btnGenerar;
    private JLabel lblTitulo;
    private JLabel lblSexo;


    public RegistrarClienteLeury(Cliente cliente) {
        cli = cliente;
        setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrarClienteLeury.class.getResource("/Imagenes/AlticeLogoVentanas.PNG")));
        
        if (cli == null) {
            setTitle("Registrar Cliente");
        } else {
            setTitle("Actualizar Cliente");
        }
        
        setResizable(false);
        setBounds(100, 100, 687, 669);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        panelReg.setBackground(new Color(29, 41, 59));
        panelReg.setBorder(new EmptyBorder(0, 0, 0, 0));
        getContentPane().add(panelReg, BorderLayout.CENTER);
        panelReg.setLayout(null);

        JPanel panelDerecho = new JPanel();
        panelDerecho.setBackground(new Color(44, 51, 70));
        panelDerecho.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelDerecho.setBounds(345, 92, 280, 452);
        panelReg.add(panelDerecho);
        panelDerecho.setLayout(null);

        JPanel panelIcono = new JPanel();
        panelIcono.setBackground(new Color(255, 91, 36));
        panelIcono.setBounds(17, 17, 48, 48);
        panelDerecho.add(panelIcono);
        panelIcono.setLayout(null);

        JLabel lblIcono = new JLabel("");
        lblIcono.setIcon(new ImageIcon(RegistrarClienteLeury.class.getResource("/Imagenes/usuario.png")));
        lblIcono.setBounds(8, 1, 38, 47);
        panelIcono.add(lblIcono);

        JLabel lblTxtId = new JLabel("ID Cliente");
        lblTxtId.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblTxtId.setForeground(Color.WHITE);
        lblTxtId.setBounds(75, 20, 82, 14);
        panelDerecho.add(lblTxtId);

        lblIdCliente = new JLabel("CLI-" + Altice.getInstance().getIdCliente());
        lblIdCliente.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblIdCliente.setForeground(Color.WHITE);
        lblIdCliente.setBounds(74, 33, 188, 32);
        panelDerecho.add(lblIdCliente);

        JLabel lblUser = new JLabel("Username");
        lblUser.setForeground(Color.WHITE);
        lblUser.setBounds(19, 88, 75, 14);
        panelDerecho.add(lblUser);

        txtUsername = new JTextField();
        txtUsername.setBounds(17, 108, 246, 20);
        panelDerecho.add(txtUsername);

        JLabel lblPass = new JLabel("Contraseña");
        lblPass.setForeground(Color.WHITE);
        lblPass.setBounds(17, 139, 77, 14);
        panelDerecho.add(lblPass);

        txtPassword = new JTextField();
        txtPassword.setBounds(17, 160, 246, 20);
        panelDerecho.add(txtPassword);

        btnGenerar = new JButton("Generar Contraseña");
        btnGenerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtPassword.setText(generarPassword());
            }
        });
        btnGenerar.setBounds(17, 201, 162, 23);
        panelDerecho.add(btnGenerar);

        JLabel lblCat = new JLabel("Categoría de Cliente");
        lblCat.setForeground(Color.WHITE);
        lblCat.setBounds(17, 268, 150, 14);
        panelDerecho.add(lblCat);

        cbxCategoria = new JComboBox<>();
        cbxCategoria.setModel(new DefaultComboBoxModel<>(new String[] {"<<Seleccione>>", "Físico", "Jurídico"}));
        cbxCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajustarPorCategoria();
            }
        });
        cbxCategoria.setBounds(17, 292, 246, 22);
        panelDerecho.add(cbxCategoria);

        // --- Panel Izquierdo ---
        if (cli == null) {
            lblTitulo = new JLabel("Crear Nuevo Cliente");
        } else {
            lblTitulo = new JLabel("Actualizar Cliente");
        }
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setBounds(38, 23, 280, 36);
        panelReg.add(lblTitulo);

        JLabel lblDatosP = new JLabel("Datos del Cliente");
        lblDatosP.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDatosP.setForeground(Color.WHITE);
        lblDatosP.setBounds(38, 70, 190, 14);
        panelReg.add(lblDatosP);

        JLabel lblNom = new JLabel("Nombre Completo / Razón Social");
        lblNom.setForeground(Color.WHITE);
        lblNom.setBounds(38, 95, 250, 14);
        panelReg.add(lblNom);

        txtNombre = new JTextField();
        txtNombre.setBounds(38, 120, 266, 20);
        panelReg.add(txtNombre);

        JLabel lblRncTit = new JLabel("Cédula / RNC");
        lblRncTit.setForeground(Color.WHITE);
        lblRncTit.setBounds(38, 151, 80, 14);
        panelReg.add(lblRncTit);

        txtRNC = new JTextField();
        txtRNC.addFocusListener(new FocusAdapter() {
             @Override
             public void focusLost(FocusEvent e) {
                 if (cli == null && txtUsername.getText().isEmpty()) {
                     txtUsername.setText(txtRNC.getText());
                 }
             }
             @Override
             public void focusGained(FocusEvent e) {
                 if (cli == null) {
                     txtUsername.setText("");
                 }
             }
        });
        txtRNC.setBounds(38, 176, 266, 20);
        panelReg.add(txtRNC);

        JLabel lblEd = new JLabel("Edad (0 si es Empresa)");
        lblEd.setForeground(Color.WHITE);
        lblEd.setBounds(38, 207, 150, 14);
        panelReg.add(lblEd);

        spnEdad = new JSpinner(new SpinnerNumberModel(0, 0, 120, 1));
        spnEdad.setBounds(38, 231, 92, 20);
        panelReg.add(spnEdad);

        lblSexo = new JLabel("Sexo");
        lblSexo.setForeground(Color.WHITE);
        lblSexo.setBounds(38, 262, 80, 14);
        panelReg.add(lblSexo);

        rdbtnMasc = new JRadioButton("Masculino");
        rdbtnMasc.setBackground(new Color(29, 41, 59));
        rdbtnMasc.setForeground(Color.WHITE);
        rdbtnMasc.setBounds(38, 285, 100, 23);
        rdbtnMasc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rdbtnFem.setSelected(false);
            }
        });
        panelReg.add(rdbtnMasc);

        rdbtnFem = new JRadioButton("Femenino");
        rdbtnFem.setBackground(new Color(29, 41, 59));
        rdbtnFem.setForeground(Color.WHITE);
        rdbtnFem.setBounds(150, 285, 100, 23);
        rdbtnFem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rdbtnMasc.setSelected(false);
            }
        });
        panelReg.add(rdbtnFem);

        JLabel lblTel = new JLabel("Teléfono");
        lblTel.setForeground(Color.WHITE);
        lblTel.setBounds(38, 327, 123, 14);
        panelReg.add(lblTel);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(38, 352, 266, 20);
        panelReg.add(txtTelefono);

        JLabel lblCor = new JLabel("Correo");
        lblCor.setForeground(Color.WHITE);
        lblCor.setBounds(38, 383, 123, 14);
        panelReg.add(lblCor);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(38, 407, 266, 20);
        panelReg.add(txtCorreo);

        JLabel lblDir = new JLabel("Dirección");
        lblDir.setForeground(Color.WHITE);
        lblDir.setBounds(38, 438, 80, 14);
        panelReg.add(lblDir);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(38, 463, 266, 20);
        panelReg.add(txtDireccion);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        btnRegistrar = new JButton("Registrar");
        if (cli == null) {
            btnRegistrar.setText("Registrar");
        } else {
            btnRegistrar.setText("Actualizar");            
        }
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarAccion();
            }
        });
        buttonPane.add(btnRegistrar);
        getRootPane().setDefaultButton(btnRegistrar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPane.add(btnCancelar);

        cargarDatosActualizar();
    }

    private void ajustarPorCategoria() {
        String sel = cbxCategoria.getSelectedItem().toString();
        if (sel.equalsIgnoreCase("Jurídico")) {
            spnEdad.setValue(0);
            spnEdad.setEnabled(false);
            rdbtnMasc.setSelected(false);
            rdbtnFem.setSelected(false);
            rdbtnMasc.setEnabled(false);
            rdbtnFem.setEnabled(false);
        } else {
            spnEdad.setEnabled(true);
            rdbtnMasc.setEnabled(true);
            rdbtnFem.setEnabled(true);
        }
    }

    private void ejecutarAccion() {
        if (camposLlenos() == false) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Cat cat;
        if (cbxCategoria.getSelectedItem().toString().equalsIgnoreCase("Físico")) {
            cat = Cat.FISICO;
        } else {
            cat = Cat.JURIDICO;
        }
        
        Usuario user = new Usuario(rolEmp.CLIENTE, txtUsername.getText(), txtPassword.getText());

        if (cli == null) {
            Cliente nuevoCli = new Cliente(txtNombre.getText(), user, (Integer) spnEdad.getValue(), 
                    txtDireccion.getText(), getSexo(), txtTelefono.getText(), txtCorreo.getText(), txtRNC.getText(), cat);
            Altice.getInstance().regPersona(nuevoCli);
            JOptionPane.showMessageDialog(null, "Cliente registrado.");
            clean();
        } else {
            cli.setNombre(txtNombre.getText());
            cli.setEdad((Integer) spnEdad.getValue());
            cli.setDireccion(txtDireccion.getText());
            cli.setSexo(getSexo());
            cli.setNumeroContacto(txtTelefono.getText());
            cli.setCorreo(txtCorreo.getText());
            cli.setCategoria(cat);
            cli.getUser().setUserName(txtUsername.getText());
            cli.getUser().setPassword(txtPassword.getText());

            JOptionPane.showMessageDialog(null, "Datos actualizados.");
            dispose();
        }
    }

    public boolean camposLlenos() {
        if (txtNombre.getText().trim().isEmpty() || txtRNC.getText().trim().isEmpty() || 
                txtTelefono.getText().trim().isEmpty() || txtCorreo.getText().trim().isEmpty() || 
                txtDireccion.getText().trim().isEmpty() || txtUsername.getText().trim().isEmpty() || 
                txtPassword.getText().trim().isEmpty() || cbxCategoria.getSelectedIndex() == 0) {
            return false;
        }
        if (cbxCategoria.getSelectedItem().toString().equalsIgnoreCase("Físico")) {
            if ((int) spnEdad.getValue() <= 0 || (rdbtnMasc.isSelected() == false && rdbtnFem.isSelected() == false)) {
                return false;
            }
        }
        return true;
    }

    public void clean() {
        lblIdCliente.setText("CLI-" + Altice.getInstance().getIdCliente());
        txtNombre.setText("");
        txtRNC.setText("");
        spnEdad.setValue(0);
        rdbtnFem.setSelected(false);
        rdbtnMasc.setSelected(false);
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        cbxCategoria.setSelectedIndex(0);
    }

    public String getSexo() {
        if (rdbtnMasc.isSelected()) {
            return "Masculino";
        }
        if (rdbtnFem.isSelected()) {
            return "Femenino";
        }
        return "N/A";
    }

    public String generarPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public void cargarDatosActualizar() {
        if (cli != null) {
            lblIdCliente.setText("CLI-" + cli.getRnc());
            txtRNC.setText(cli.getRnc());
            txtRNC.setEditable(false);
            txtNombre.setText(cli.getNombre());
            spnEdad.setValue(cli.getEdad());
            txtTelefono.setText(cli.getNumeroContacto());
            txtCorreo.setText(cli.getCorreo());
            txtDireccion.setText(cli.getDireccion());
            txtUsername.setText(cli.getUser().getUserName());
            txtPassword.setText(cli.getUser().getPassword());
            
            if (cli.getCategoria() == Cat.FISICO) {
                cbxCategoria.setSelectedItem("Físico");
            } else {
                cbxCategoria.setSelectedItem("Jurídico");
            }
            
            if (cli.getSexo().equalsIgnoreCase("Masculino")) {
                rdbtnMasc.setSelected(true);
            } else if (cli.getSexo().equalsIgnoreCase("Femenino")) {
                rdbtnFem.setSelected(true);
            }
        }
    }

    public void modoDetalle() {
        setTitle("Detalle del Cliente");
        lblTitulo.setText("Detalles de Cliente");  
        txtNombre.setEditable(false);
        txtRNC.setEditable(false);
        txtTelefono.setEditable(false);
        txtCorreo.setEditable(false);
        txtDireccion.setEditable(false);
        txtUsername.setEditable(false);
        txtPassword.setEditable(false);
        spnEdad.setEnabled(false);
        cbxCategoria.setEnabled(false);
        rdbtnMasc.setEnabled(false);
        rdbtnFem.setEnabled(false);
        btnGenerar.setVisible(false);
        btnCancelar.setText("Cerrar");
    }
}