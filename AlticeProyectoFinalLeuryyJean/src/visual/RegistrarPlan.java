package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Altice;
import logico.Plan;
import logico.Plan.Estado;
import logico.Plan.Tipo;
import logico.Servicio;
import logico.Servicio.Serv;

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
	private Servicio selected = null;
	private JButton btnEliminarServicio;
	private Plan planActual = null; // Para manejar la actualización
	private JLabel lblTitulo;
	private JButton btnRegistrar;
	private JPanel panelAgregar;

	public static void main(String[] args) {
		try {
			// Pasamos null para modo registro
			RegistrarPlan dialog = new RegistrarPlan(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Constructor modificado para recibir un Plan
	public RegistrarPlan(Plan plan) {
		this.planActual = plan;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrarPlan.class.getResource("/Imagenes/AlticeLogoVentanas.PNG")));
		setResizable(false);
		setBounds(100, 100, 675, 795);
		setLocationRelativeTo(null);
		
		if (planActual == null) {
			setTitle("Registrar plan");
		} else {
			setTitle("Actualizar plan");
		}

		getContentPane().setLayout(new BorderLayout());
		panelAtras.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelAtras, BorderLayout.CENTER);
		panelAtras.setBackground(new Color(29, 41, 59));
		panelAtras.setLayout(null);
		
		{
			lblTitulo = new JLabel("Crear Nuevo Plan");
			if (planActual != null) {
				lblTitulo.setText("Actualizar Plan");
			}
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

		lblIdPlan = new JLabel("PLN-" + Altice.getInstance().getIdPlan());
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
		lblNewLabel_1_2_1.setBounds(316, 206, 185, 16);
		panelAtras.add(lblNewLabel_1_2_1);

		lblPrecioTotal = new JLabel("RD$ 0.00");
		lblPrecioTotal.setForeground(new Color(221, 112, 87));
		lblPrecioTotal.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblPrecioTotal.setBounds(316, 223, 312, 36);
		panelAtras.add(lblPrecioTotal);

		JLabel lblNewLabel_6 = new JLabel("Precio con impuestos (18% ITBIS, 10% ISC, 2% CDT)");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBounds(316, 269, 304, 12);
		panelAtras.add(lblNewLabel_6);

		lblPrecioBase = new JLabel("RD$ 0.00");
		lblPrecioBase.setForeground(Color.WHITE);
		lblPrecioBase.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblPrecioBase.setBounds(40, 223, 245, 36);
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
				actualizarPrecios();
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
				actualizarPrecios();
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
		model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				if (index >= 0) {
					String id = table.getValueAt(index, 0).toString();
					selected = buscarServicioPorIdCrearPlan(id);
					if (selected != null) {
						btnEliminarServicio.setEnabled(true);
					}
				}
			}
		});
		scrollPaneTablaServ.setViewportView(table);

		panelAgregar = new JPanel();
		panelAgregar.setBounds(412, 456, 222, 188);
		panelAtras.add(panelAgregar);
		panelAgregar.setLayout(null);

		txtServicio = new JTextField();
		txtServicio.setEnabled(false);
		txtServicio.setEditable(false);
		txtServicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListarParaAsigServ.selected = null;
		        ListarParaAsigServ list = new ListarParaAsigServ();
		        list.setModal(true);
		        list.setVisible(true);

		        if (ListarParaAsigServ.selected != null) {
		            Servicio s = ListarParaAsigServ.selected;
		            if (buscarServicioPorIdCrearPlan(s.getIdService()) == null) {
		                serviciosTemp.add(s);
		                loadServicios();
		                actualizarPrecios();
		                txtServicio.setText(s.getNombre());
		            } else {
		                JOptionPane.showMessageDialog(null, "Este servicio ya está en el plan.");
		            }
		        }
			}
		});
		txtServicio.setBounds(23, 84, 175, 20);
		panelAgregar.add(txtServicio);

		JLabel lblNewLabel_6_1_1 = new JLabel("Servicio seleccionado");
		lblNewLabel_6_1_1.setForeground(Color.GRAY);
		lblNewLabel_6_1_1.setBounds(46, 106, 130, 12);
		panelAgregar.add(lblNewLabel_6_1_1);

		JLabel lblNewLabel = new JLabel("Haga click aquí para agregar");
		lblNewLabel.setBounds(33, 59, 175, 14);
		panelAgregar.add(lblNewLabel);

		JLabel lblAgregarServicio = new JLabel("Agregar Servicio");
		lblAgregarServicio.setForeground(Color.WHITE);
		lblAgregarServicio.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAgregarServicio.setBounds(412, 399, 213, 40);
		panelAtras.add(lblAgregarServicio);

		JLabel lblNewLabel_6_1 = new JLabel("Doble clic sobre el servicio para ver detalles.");
		lblNewLabel_6_1.setForeground(Color.WHITE);
		lblNewLabel_6_1.setBounds(36, 437, 304, 12);
		panelAtras.add(lblNewLabel_6_1);

		btnEliminarServicio = new JButton("Eliminar Seleccionado");
		btnEliminarServicio.setEnabled(false);
		btnEliminarServicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selected != null) {
					serviciosTemp.remove(selected);
					loadServicios();
					actualizarPrecios();
					selected = null;
					btnEliminarServicio.setEnabled(false);
				}
			}
		});
		btnEliminarServicio.setBounds(36, 654, 174, 23);
		panelAtras.add(btnEliminarServicio);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton("Registrar");
				if (planActual != null) {
					btnRegistrar.setText("Actualizar");
				}
				btnRegistrar.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        
				        if (txtNombreProm.getText().trim().isEmpty()) {
				            JOptionPane.showMessageDialog(null, "Debe ingresar un nombre promocional.", "Advertencia", JOptionPane.WARNING_MESSAGE);
				            return;
				        }
				        if (serviciosTemp.size() == 0) {
				            JOptionPane.showMessageDialog(null, "Error: Añada al menos un servicio al plan.", "Información", JOptionPane.WARNING_MESSAGE);
				            return;
				        }
				        if (!rdbtnResidencial.isSelected() && !rdbtnNegocios.isSelected()) {
				            JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de plan.", "Advertencia", JOptionPane.WARNING_MESSAGE);
				            return;
				        }

				        Tipo tipoSeleccionado;
				        if (rdbtnResidencial.isSelected()) {
				            tipoSeleccionado = Tipo.RESIDENCIAL;
				        } else {
				            tipoSeleccionado = Tipo.NEGOCIOS;
				        }

				        if (planActual != null) {
				            planActual.setState(Estado.DESCONTINUADO);
				        }

				        String nuevoID = "PLN-" + Altice.getInstance().getIdPlan();
				        Plan nuevoPlan = new Plan(nuevoID, txtNombreProm.getText(), tipoSeleccionado);
				        for (Servicio s : serviciosTemp) {
				            nuevoPlan.addServicio(s);
				        }
				        Altice.getInstance().agrPlan(nuevoPlan); 
				        if (planActual != null) {
				            JOptionPane.showMessageDialog(null, "El plan anterior ha sido descontinuado. Se ha generado una nueva versión con el ID: " + nuevoID, "Información", JOptionPane.INFORMATION_MESSAGE);
				        } else {
				            JOptionPane.showMessageDialog(null, "Plan registrado exitosamente con el ID: " + nuevoID, "Información", JOptionPane.INFORMATION_MESSAGE);
				        }
				        ListarPlanes.loadPlanes(); 
				        dispose();
				    }
				});
				buttonPane.add(btnRegistrar);
			}
			{
				JButton btnCancelar = new JButton("Cerrar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(btnCancelar);
			}
		}

		loadHeaders();
		cargarDatos(); // Carga la info si planActual != null
	}

	public void cargarDatos() {
		if (planActual != null) {
			lblIdPlan.setText(planActual.getIdPlan());
			txtNombreProm.setText(planActual.getNombreComercial());
			
			if (planActual.getType() == Tipo.RESIDENCIAL) {
				rdbtnResidencial.setSelected(true);
			} else {
				rdbtnNegocios.setSelected(true);
			}

			// Copiamos los servicios del plan a nuestra lista temporal
			serviciosTemp = new ArrayList<Servicio>(planActual.getPlanServices());
			loadServicios();
			actualizarPrecios();
		}
	}

	public void modoDetalle() {
		setTitle("Detalle del Plan");
		lblTitulo.setText("Detalles de Plan");
		txtNombreProm.setEditable(false);
		rdbtnResidencial.setEnabled(false);
		rdbtnNegocios.setEnabled(false);
		btnEliminarServicio.setVisible(false);
		btnRegistrar.setVisible(false);
		panelAgregar.setVisible(false);
		txtServicio.setEnabled(false);
	}

	public void actualizarPrecios() {
		float sumaBase = 0;
		for (Servicio s : serviciosTemp) {
			sumaBase += s.getCostoMensualInd();
		}
		
		float impuestado = sumaBase * 1.30f;
		float finalTotal = impuestado;
		
		if (rdbtnNegocios.isSelected()) {
			finalTotal = impuestado * 1.50f;
		}

		lblPrecioBase.setText("RD$ " + String.format("%.2f", sumaBase));
		lblPrecioTotal.setText("RD$ " + String.format("%.2f", finalTotal));
	}

	public void loadHeaders() {
		String headersServicios[] = { "ID", "Nombre", "Tipo", "Precio" };
		model.setColumnIdentifiers(headersServicios);
		table.setModel(model);
		loadServicios();
	}

	public void loadServicios() {
		model.setRowCount(0);
		raw = new Object[4];
		for (Servicio temp : serviciosTemp) {
			raw[0] = temp.getIdService();
			raw[1] = temp.getNombre();
			
			String tipo = "";
			if (temp.getTipo() == Serv.INTERNET) { tipo = "Internet"; }
			if (temp.getTipo() == Serv.TELEFONIA) { tipo = "Telefonía"; }
			if (temp.getTipo() == Serv.TELEVISION) { tipo = "Televisión"; }

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