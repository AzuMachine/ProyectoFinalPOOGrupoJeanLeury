package logico;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Altice implements Serializable{
	public static Altice alt = null;
	
	private ArrayList<Persona> misHumanos;
	private ArrayList<Plan> misPlanes;
	private ArrayList<Servicio> misServicios;
	private ArrayList<Pago> misPagos;
	private ArrayList<Contrato> misContratos;
	private ArrayList<Ticket> misTickets;
	private ArrayList<Usuario> misUsers;
	public static Usuario loginUser;
	public static int idPlan =1;

	public Altice() {
		super();
		misHumanos = new ArrayList<>();
		misPlanes = new ArrayList<>();
		misServicios = new ArrayList<>();
		misPagos = new ArrayList<>();
		misContratos = new ArrayList<>();
		misTickets = new ArrayList<>();
		misUsers = new ArrayList<>();
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
	
	
	public ArrayList<Usuario> getMisUsers() {
		return misUsers;
	}

	public static int getIdPlan() {
		return idPlan;
	}

	public static void setIdPlan(int idPlan) {
		Altice.idPlan = idPlan;
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
	
	//1.Usuario.1 Registrar Usuario
	public void registrarUsuario(Usuario user) {
		misUsers.add(user);
	}
	
}
