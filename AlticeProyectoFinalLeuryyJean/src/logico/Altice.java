package logico;

import java.util.ArrayList;

public class Altice {
	public static Altice alt = null;
	private ArrayList<Persona> misHumanos;
	private ArrayList<Plan> misPlanes;
	private ArrayList<Servicio> misServicios;
	private ArrayList<Pago> misPagos;
	private ArrayList<Contrato> misContratos;
	private ArrayList<Ticket> misTickets;


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

	/*1 Inicio metodos busqueda de clases por algun identificador*/
/*
	//1.1 Buscar cliente by Identificador
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
}
