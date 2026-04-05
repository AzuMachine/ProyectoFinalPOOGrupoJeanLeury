package logico;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Altice implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public static Altice alt = null;
	private ArrayList<Persona> misHumanos;
	private ArrayList<Plan> misPlanes;
	private ArrayList<Servicio> misServicios;
	private ArrayList<Pago> misPagos;
	private ArrayList<Contrato> misContratos;
	private ArrayList<Ticket> misTickets;
	public static Usuario loginUser;
	public static int idPlan =1;
	public static int idEmpleado =1;
	public static int idCliente =1;
	

	public Altice() {
		super();
		misHumanos = new ArrayList<>();
		misPlanes = new ArrayList<>();
		misServicios = new ArrayList<>();
		misPagos = new ArrayList<>();
		misContratos = new ArrayList<>();
		misTickets = new ArrayList<>();
	}

	public static Altice getInstance() {
		if(alt == null) {
			alt = new Altice();
		}
		return alt;
	}

	public ArrayList<Persona> getMisHumanos() {
		return misHumanos;
	}

	public ArrayList<Plan> getMisPlanes() {
		return misPlanes;
	}

	public ArrayList<Servicio> getMisServicios() {
		return misServicios;
	}

	public ArrayList<Pago> getMisPagos() {
		return misPagos;
	}

	public ArrayList<Contrato> getMisContratos() {
		return misContratos;
	}

	public ArrayList<Ticket> getMisTickets() {
		return misTickets;
	}

	public static Usuario getLoginUser() {
		return loginUser;
	}
	
	public static void setLoginUser(Usuario loginUser) {
		Altice.loginUser = loginUser;
	}

	public static int getIdPlan() {
		return idPlan;
	}

	public static void setIdPlan(int idPlan) {
		Altice.idPlan = idPlan;
	}
	
	public static Altice getAlt() {
		return alt;
	}

	public static void setAlt(Altice alt) {
		Altice.alt = alt;
	}
	/*1 Inicio metodos busqueda de clases por algun identificador*/
/*
	//1.Contrato.1 Buscar cliente by Identificador
	public Persona buscarClienteById(String idCliente) {
		for (Cliente c : misHumanos) {
			if (c.getId().equalsIgnoreCase(idCliente) && c instanceof Cliente) {
				return c;
			}
		}
		return null;
	}
	//1.2
	public Cliente buscarClienteId(String idCliente) {
		for (Cliente c : misClientes) {
			if (c.getId().equalsIgnoreCase(idCliente) && c.isEstado()==true) {
				return c;
			}
		}
		return null;
	}
	*/
	
	

	//1.Contraro.1 Buscar Plan by ID
	public Plan buscarPlanByID(String idPlan) {
		for (Plan planes : misPlanes) {
			if(planes.getIdPlan().equalsIgnoreCase(idPlan)) { return planes;}
		}
		return null;
	}
	
	
	
	//1.LogIn.1 Confirmar el Log In
	
	public boolean confirmarIngreso(String username, String pass) {
		boolean login = false;
		for (Persona p: misHumanos ) {
			if(p.getUser().getUserName().equals(username) && p.getUser().getPassword().equals(pass)){
				loginUser = p.getUser();
				login = true;
				p.getUser().setUltimoIngreso(LocalDateTime.now());
			}
		}
		return login;
	}
	
	//1.Persona.1 Agregar persona
	
	public void regPersona(Persona p) {
		misHumanos.add(p);
		if(p instanceof Empleado) {
			idEmpleado++;
		}
		if(p instanceof Cliente) {
			idCliente++;
		} 
	}
	
	//1.Persona.2 buscarPersonaByRNC
	public Persona buscarPersonaByRNC(String RNC) {
		for (Persona p : misHumanos) {
			if(p.getRnc().equalsIgnoreCase(RNC)) {
			return p;
			}
		}
		return null;
	}
	
	//1.Empleado.1 Actualizar empleado
	public void actualizarEmpleado(Empleado emp) {
		// TODO Auto-generated method stub
		
	}
	//1.Empleado.2 Buscar Empleado por ID
	public Persona buscarPersonaByID(String id) {
		for (Persona p : misHumanos) {
			if(p instanceof Empleado) {
				Empleado emp = (Empleado)p;
				if (emp.getIdEmpleado().equalsIgnoreCase(id)) {
					return emp;
				}
			}
		}
		return null;
	}
}
