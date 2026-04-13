package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Altice;
import logico.Servicio;
import logico.Servicio.Serv;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class RegistrarPlan extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel panelAtras = new JPanel();
	private JTextField txtNombreProm;
	private JLabel lblPrecioTotal;
	private JLabel lblPrecioBase;
	private JLabel lblIdPlan;
	private JRadioButton rdbtnResidencial;
	private JRadioButton rdbtnNegocios;
	private JTable table;
	private JTextField txtServicio;
	private ArrayList<Servicio> serviciosTemp = new ArrayList<>();
	private DefaultTableModel model;
	private Object[] raw;
	private Servicio selected =null;
	private JButton btnEliminarServicio;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarPlan dialog = new RegistrarPlan();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarPlan() {
		setTitle("Registrar plan");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestionEmpleados.class.getResource("/Imagenes/AlticeLogoVentanas.PNG")));
		setResizable(false);
		setBounds(100, 100, 675, 795);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		panelAtras.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelAtras, BorderLayout.CENTER);
		panelAtras.setBackground(new Color(29, 41, 59));
		panelAtras.setLayout(null);
		{
			JLabel lblTitulo = new JLabel("Crear Nuevo Plan");
			lblTitulo.setForeground(Color.WHITE);
			lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblTitulo.setBounds(36, 21, 213, 40);

			panelAtras.add(lblTitulo);
		}

		JLabel lblNewLabel_1 = new JLabel("ID Plan");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(40, 72, 72, 16);
		panelAtras.add(lblNewLabel_1);

		lblIdPlan = new JLabel("PLN-1");
		lblIdPlan.setForeground(Color.WHITE);
		lblIdPlan.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblIdPlan.setBounds(40, 87, 184, 36);
		panelAtras.add(lblIdPlan);

		JLabel lblNewLabel_1_1 = new JLabel("Nombre Promocional");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setBounds(380, 72, 254, 14);
		panelAtras.add(lblNewLabel_1_1);

		txtNombreProm = new JTextField();
		txtNombreProm.setColumns(10);
		txtNombreProm.setBounds(380, 97, 254, 20);
		panelAtras.add(txtNombreProm);

		JLabel lblNewLabel_1_2_1 = new JLabel("Precio Total Mensual");
		lblNewLabel_1_2_1.setForeground(Color.WHITE);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_2_1.setBounds(295, 206, 185, 16);
		panelAtras.add(lblNewLabel_1_2_1);

		lblPrecioTotal = new JLabel("$");
		lblPrecioTotal.setForeground(new Color(221,112,87));
		lblPrecioTotal.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblPrecioTotal.setBounds(295, 223, 184, 36);
		panelAtras.add(lblPrecioTotal);

		JLabel lblNewLabel_6 = new JLabel("Precio con impuestos \r\n(18% ITBIS, 10% ISC, 2% CDT)");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBounds(295, 269, 304, 12);
		panelAtras.add(lblNewLabel_6);

		lblPrecioBase = new JLabel("$");
		lblPrecioBase.setForeground(Color.WHITE);
		lblPrecioBase.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblPrecioBase.setBounds(40, 223, 184, 36);
		panelAtras.add(lblPrecioBase);

		JLabel lblNewLabel_1_2 = new JLabel("Precio Base (Antes de impuestos)");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(40, 206, 185, 16);
		panelAtras.add(lblNewLabel_1_2);

		JLabel lblResumenDePrecios = new JLabel("Resumen de Precios");
		lblResumenDePrecios.setForeground(Color.WHITE);
		lblResumenDePrecios.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblResumenDePrecios.setBounds(36, 159, 213, 40);
		panelAtras.add(lblResumenDePrecios);

		JLabel lblConfiguracin = new JLabel("Configuración");
		lblConfiguracin.setForeground(Color.WHITE);
		lblConfiguracin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblConfiguracin.setBounds(36, 301, 213, 40);
		panelAtras.add(lblConfiguracin);

		JLabel lblNewLabel_4 = new JLabel("Tipo");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(36, 347, 158, 14);
		panelAtras.add(lblNewLabel_4);

		rdbtnResidencial = new JRadioButton("Residencial");
		rdbtnResidencial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnNegocios.setSelected(false);
			}
		});
		rdbtnResidencial.setForeground(Color.WHITE);
		rdbtnResidencial.setBackground(new Color(29, 41, 59));
		rdbtnResidencial.setBounds(36, 369, 109, 23);
		panelAtras.add(rdbtnResidencial);

		rdbtnNegocios = new JRadioButton("Negocios");
		rdbtnNegocios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnResidencial.setSelected(false);
			}
		});
		rdbtnNegocios.setForeground(Color.WHITE);
		rdbtnNegocios.setBackground(new Color(29, 41, 59));
		rdbtnNegocios.setBounds(141, 368, 110, 23);
		panelAtras.add(rdbtnNegocios);

		JLabel lblServiciosDelPlan = new JLabel("Lista de Servicios");
		lblServiciosDelPlan.setForeground(Color.WHITE);
		lblServiciosDelPlan.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblServiciosDelPlan.setBounds(36, 399, 213, 40);
		panelAtras.add(lblServiciosDelPlan);

		JScrollPane scrollPaneTablaServ = new JScrollPane();
		scrollPaneTablaServ.setBounds(36, 456, 366, 188);
		panelAtras.add(scrollPaneTablaServ);

		table = new JTable();
		selected = null;
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
		        if(index >= 0) {
		            String id = table.getValueAt(index, 0).toString();
		            
		            selected = buscarServicioPorIdCrearPlan(id);
		            if(selected != null) {
		                btnEliminarServicio.setEnabled(true);
		            }
		        }
			}


		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		model = new DefaultTableModel();
		scrollPaneTablaServ.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBounds(412, 456, 222, 188);
		panelAtras.add(panel);
		panel.setLayout(null);

		txtServicio = new JTextField();
		txtServicio.setEnabled(false);
		txtServicio.setEditable(false);
		txtServicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListarParaAsigServ.selected = null;
				ListarParaAsigServ list = new ListarParaAsigServ();
				list.loadHeaders();
				list.setModal(true);
				list.setVisible(true);

				if (ListarParaAsigServ.selected != null) {
		            Servicio s = ListarParaAsigServ.selected;
		            
		            // Validamos si ya existe en este plan
		            if (buscarServicioPorIdCrearPlan(s.getIdService()) == null) {
		                serviciosTemp.add(s);     
		                loadServicios();           
		                actualizarPrecios();       
		                txtServicio.setText(s.getNombre());
		            } else {
		                JOptionPane.showMessageDialog(null, "Este servicio ya está en el plan.");
		            }
		        }
				else {
					txtServicio.setText("");;
				}
			}
		});
		txtServicio.setBounds(23, 84, 175, 20);
		panel.add(txtServicio);
		txtServicio.setColumns(10);

		JLabel lblNewLabel_6_1_1 = new JLabel("Servicio seleccionado");
		lblNewLabel_6_1_1.setForeground(Color.GRAY);
		lblNewLabel_6_1_1.setBounds(46, 106, 130, 12);
		panel.add(lblNewLabel_6_1_1);

		JLabel lblNewLabel = new JLabel("Haga click aquí para agregar");
		lblNewLabel.setBounds(33, 59, 175, 14);
		panel.add(lblNewLabel);

		JLabel lblAgregarServicio = new JLabel("Agregar Servicio");
		lblAgregarServicio.setForeground(Color.WHITE);
		lblAgregarServicio.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAgregarServicio.setBounds(412, 399, 213, 40);
		panelAtras.add(lblAgregarServicio);

		JLabel lblNewLabel_6_1 = new JLabel("Dar doble click sobre el servicio para ver detalles.");
		lblNewLabel_6_1.setForeground(Color.WHITE);
		lblNewLabel_6_1.setBounds(36, 437, 304, 12);
		panelAtras.add(lblNewLabel_6_1);
		
		btnEliminarServicio = new JButton("Eliminar Seleccionado");
		btnEliminarServicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selected != null) {
		            serviciosTemp.remove(selected);
		            loadServicios();
		            actualizarPrecios();
		            selected = null;
		        }
			}
		});
		btnEliminarServicio.setBounds(36, 654, 158, 23);
		panelAtras.add(btnEliminarServicio);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						ListarParaAsigServ.selected = null;
						selected = null;
						
						if(!(serviciosTemp.size()>0)) {
							 JOptionPane.showMessageDialog(null, "Error: Añada servicios al plan.","Información", JOptionPane.WARNING_MESSAGE);
							 return;
						 }
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		loadHeaders();
	}
	public void cleanServicio() {
		txtServicio.setText("");
	}

	public void Clean() {
		txtNombreProm.setText("");
		lblIdPlan.setText("PLN-" + Altice.getInstance().getIdPlan());
		rdbtnNegocios.setSelected(false);
		rdbtnResidencial.setSelected(false);
		lblPrecioBase.setText("RD$ ");
		lblPrecioTotal.setText("RD$ ");
	}

	public void actualizarPrecios() {
		float sumaBase = 0;
		for (Servicio s : serviciosTemp) {
			sumaBase += s.getCostoMensualInd();
		}
		float precioConImpuestos = sumaBase * 1.30f; 

		lblPrecioBase.setText("RD$ " + String.format("%.2f", sumaBase));
		lblPrecioTotal.setText("RD$ " + String.format("%.2f", precioConImpuestos));
	}

	public void loadHeaders() {
		String headersServicios[] = {"ID", "Nombre", "Tipo", "Precio"};
		model.setColumnIdentifiers(headersServicios);
		table.setModel(model);
		loadServicios();

	}

	public void loadServicios() {
		model.setRowCount(0);

		if (btnEliminarServicio != null) {
			btnEliminarServicio.setEnabled(false);
		}

		raw = new Object[table.getColumnCount()]; 
		for(Servicio temp: serviciosTemp) {
			Serv tipoServicio = temp.getTipo(); 
			raw[0] = temp.getIdService();
			raw[1] = temp.getNombre();
			
			String tipo= "";
			if(tipoServicio == Serv.INTERNET) {tipo = "Internet";}
			if(tipoServicio == Serv.TELEFONIA) {tipo = "Telefonía";}
			if(tipoServicio == Serv.TELEVISION) {tipo = "Televisión";}

			raw[2] = tipo;
			raw[3] = temp.getCostoMensualInd();
			model.addRow(raw);

		}

	}
	
	public Servicio buscarServicioPorIdCrearPlan(String id) {
	    for (Servicio s : serviciosTemp) {
	        if (s.getIdService().equals(id)) {
	            return s;
	        }
	    }
	    return null;
	}

}
