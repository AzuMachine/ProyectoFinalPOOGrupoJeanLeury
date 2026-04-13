package logico;

import java.io.Serializable;
import java.security.KeyStore.TrustedCertificateEntry;
import java.time.LocalDateTime;
import java.util.ArrayList;

import logico.Contratos.Estado;
import logico.Servicio.Serv;

public class Altice implements Serializable{
	private static final long serialVersionUID = 1L;

	public static Altice alt = null;
	private ArrayList<Persona> misHumanos;
	private ArrayList<Plan> misPlanes;
	private ArrayList<Servicio> misServicios;
	private ArrayList<Pago> misPagos;
	private ArrayList<Contratos> misContratos;
	private ArrayList<Ticket> misTickets;
	public static Usuario loginUser;

	private int idPlan =1;
	private int idEmpleado =0;
	private int idCliente =1;
	private int idServFIB = 1;
	private int idServTV = 1;
	private int idServTEL = 1;



	public Altice() {
		super();
		misHumanos = new ArrayList<>();
		misPlanes = new ArrayList<>();
		misServicios = new ArrayList<>();
		misPagos = new ArrayList<>();
		misContratos = new ArrayList<>();
		misTickets = new ArrayList<>();
		this.idEmpleado =0;
		this.idPlan =1;
		this.idCliente =1;
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

	public ArrayList<Contratos> getMisContratos() {
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

	public int getIdPlan() {
		return idPlan;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdPlan(int idPlan) {
		this.idPlan = idPlan;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdServFIB() {
		return idServFIB;
	}

	public int getIdServTV() {
		return idServTV;
	}

	public int getIdServTEL() {
		return idServTEL;
	}

	public void setIdServFIB(int idServFIB) {
		this.idServFIB = idServFIB;
	}

	public void setIdServTV(int idServTV) {
		this.idServTV = idServTV;
	}

	public void setIdServTEL(int idServTEL) {
		this.idServTEL = idServTEL;
	}

	public static Altice getAlt() {
		return alt;
	}

	public static void setAlt(Altice alt) {
		Altice.alt = alt;
	}

	//<<<<<<<<<<<<<<<<<<<<<<INICIO METODOS DE BUSQUEDA>>>>>>>>>>>>>>>>>>>>
	//1.Plan.1 Buscar Plan by ID
	public Plan buscarPlanByID(String idPlan) {
		for (Plan planes : misPlanes) {
			if(planes.getIdPlan().equalsIgnoreCase(idPlan)) { return planes;}
		}
		return null;
	}

	//1.Plan.2 Agregar plan
	public void agrPlan(Plan plan) {
		misPlanes.add(plan);
		idPlan++;
	}

	//1.Plan.3 Eliminar
	public void eliminarPlan(Plan selectedPlan) {
		misPlanes.remove(selectedPlan);

		if(selectedPlan != null) {
			selectedPlan.setState(logico.Plan.Estado.DESCONTINUADO);
		}
	}

	//Fin codigos planes

	//1.Servicios.1 Buscar By ID

	public Servicio buscarServicioByID(String idServi) {
		for (Servicio services : misServicios) {
			if(services.getIdService().equalsIgnoreCase(idServi)) {return services;}
		}
		return null;
	}


	//1.Servicios.2 GuardarServ

	public void guardarServ(Servicio serv) {
		misServicios.add(serv);
		if(serv.getTipo().equals(Serv.INTERNET)) {
			idServFIB++;
		}else if (serv.getTipo().equals(Serv.TELEFONIA)) {
			idServTEL++;
		}else if (serv.getTipo().equals(Serv.TELEVISION)) {
			idServTV++;
		}
	}

	//1.Servicio.3 Eliminar
	public void eliminarServicio(Servicio selectedServ) {
		if(selectedServ != null) {
			selectedServ.setHabilitado(false);
		}
	}


	//1.Servicios. 4 Actualizar
	public void actualizarServicio(Servicio miServi) {
		int index = buscarIndexServicioByID(miServi.getIdService());

		if(index != -1) {
			misServicios.set(index, miServi);
		}

	}

	//1. Servicios. 5 Buscar Index serv
	private int buscarIndexServicioByID(String idService) {
		int auxServices = -1;
		boolean encontrado = false;
		int ind = 0;

		while (!encontrado && ind < misServicios.size()) {
			if(misServicios.get(ind).getIdService().equalsIgnoreCase(idService)) {
				encontrado = true; auxServices = ind;
			}
			ind++;
		}

		return auxServices;
	}

	//Fin codigo servicios

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


	//Fin codigo Persona

	//1.Empleado.1 Actualizar empleado
	public void actualizarEmpleado(Empleado emp) {
		int index = buscarIndiceEmpleadoId(emp.getIdEmpleado());
		if(index!=-1) {
			misHumanos.set(index, emp);
		}
	}
	//1.Empleado.2 Buscar Empleado por ID
	public Persona buscarEmpleadoByID(String idEmp) {
		for (Persona p : misHumanos) {
			if(p instanceof Empleado) {
				Empleado emp = (Empleado)p;
				if (emp.getIdEmpleado().equalsIgnoreCase(idEmp)) {
					return emp;
				}
			}
		}
		return null;
	}

	//1.Empleado.3 Buscar IndiceEmpleadoId
	public int buscarIndiceEmpleadoId(String idEmp) {
		int  aux = -1;
		boolean encontrado = false; 
		int i =0;

		while(!encontrado && i<misHumanos.size()){
			if(misHumanos.get(i) instanceof Empleado) {
				if(((Empleado)misHumanos.get(i)).getIdEmpleado().equalsIgnoreCase(idEmp)) {
					encontrado = true;
					aux = i;
				}
			}
			i++;
		}
		return aux;
	}
	//1.Empleado.4 despedir Empleado
	public void despedirEmpleado(Empleado emp) {
		int index = buscarIndiceEmpleadoId(emp.getIdEmpleado());
		if(index !=-1) {
			emp.setEstado(false);
			misHumanos.set(index, emp);
		}
	}

	//Fin codigo Empleado

	//1.Contrato. 1 HacerCancelar

	public boolean changeContratoStateCancelado(Contratos seletedContrato) {
		if(seletedContrato != null && seletedContrato.getEstado() != Estado.SUSPENDIDO) {
			seletedContrato.setEstado(Estado.CANCELADO);
			return true;
		}
		return false;
	}

	//1.Contrato. 2 Suspendido

	public boolean changeContratoStateSuspendido(Contratos seletedContrato) {
		if(seletedContrato != null && seletedContrato.getEstado() != Estado.CANCELADO) {
			seletedContrato.setEstado(Estado.SUSPENDIDO);
			return true;
		}
		return false;
	}

	//1.Contrado. 3 buscarContratoByNum

	public Contratos buscarContratoByNumero(String numeroContratoID) {
		
		for (Contratos contrato : misContratos) {
			if(contrato.getNumeroContrato().equalsIgnoreCase(numeroContratoID)) {
				return contrato;
			}
		}
		
		return null;
	}

	//Fin codigo Contrato
	
	//1.Tiket. 1 CambiarEstado
	
	public boolean tiketTomado(Ticket seletedTicket) {
		
		if(seletedTicket != null && seletedTicket.getState().equals(logico.Ticket.Estado.ABIERTO)) {
			seletedTicket.setState(logico.Ticket.Estado.EN_PROCESO);
			return true;
		}
		
		return false;
	}
	
	public boolean tiketResuelto(Ticket seletedTicket) {
		
		if(seletedTicket != null && seletedTicket.getState().equals(logico.Ticket.Estado.EN_PROCESO)) {
			seletedTicket.setState(logico.Ticket.Estado.RESUELTO);
		}
		
		return false;
	}
	
	//Fin codigo Tikets
}

