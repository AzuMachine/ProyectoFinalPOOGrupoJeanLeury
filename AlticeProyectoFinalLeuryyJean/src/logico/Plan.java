package logico;

import java.util.ArrayList;

public class Plan {
	
	public enum Tipo {RESIDENCIAL,NEGOCIOS}
	public enum Estado {VIGENTE, DESCONTINUADO} //al eliminar los planes simplemente los descontinuamos, no los eliminamos del registro
	
	private String idPlan;
	private String nombreComercial; //nombre que se usa para promocionar el plan
	private float precioBase; //Se calcula sumando los precios de los servicios del plan
	private float precioTotal; //(despues de impuestos 18% ITBIS, 10% ISC, 2% CDT) -> precio * 1.30 (aplicar 30%)
	private Tipo type; //si es para negocios pongamos 50% mas caro -> preciototal * 1.5
	private Estado state; 
	private ArrayList<Servicio> planServices;
	
	public Plan(String idPlan, String nombreComercial, Tipo type) {
		super();
		this.idPlan = idPlan;
		this.nombreComercial = nombreComercial;
		this.type = type;
		this.state = Estado.VIGENTE;
		this.planServices = new ArrayList<>();
	}

	public void addServicio(Servicio ser) {
		planServices.add(ser);
	}
	
	//desarrollar esto
	public void quitarServicio(Servicio ser) {
		planServices.remove(ser);
	}

	public String getIdPlan() {
		return idPlan;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public float getPrecioTotal() {
		return precioTotal;
	}

	public Tipo getType() {
		return type;
	}

	public Estado getState() {
		return state;
	}

	public ArrayList<Servicio> getPlanServices() {
		return planServices;
	}

	public void setIdPlan(String idPlan) {
		this.idPlan = idPlan;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public void setPrecioTotal(float precioTotal) {
		this.precioTotal = precioTotal;
	}

	public void setType(Tipo type) {
		this.type = type;
	}

	public void setState(Estado state) {
		this.state = state;
	}
	
	

}
