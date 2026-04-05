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
	
	//Funciones del metodo plan
	//1.Plan.1 - Agregar servicio
	public void addServicio(Servicio ser) {
		if(ser != null) {
			planServices.add(ser);
			
			calcularPrecios();
		}
	}
	
	//1.Plan.2 - Quitar servicio
	//desarrollar esto
	public void quitarServicio(Servicio ser) {
		boolean eliminado = eliminarPlanServicio(ser);
		
		if(eliminado == true) {
			calcularPrecios();
		}
	}
	
	//1.Plan.3 - Calcular precio
	private void calcularPrecios() { 
	    precioBase = 0.0f;
	    
	    for (Servicio servicio : planServices) {
			precioBase = precioBase + servicio.getCostoMensualInd();
		}
	    
	    float precio_Con_Impuesto = (float) (precioBase * 1.30);
	    
	    if(type.equals(Tipo.NEGOCIOS)) {
	    	precioTotal = (float) (precio_Con_Impuesto * 1.50);
	    }else {
			precioTotal = precio_Con_Impuesto;
		}
		
		
	}
	
	public boolean eliminarPlanServicio(Servicio ser) {
		if(ser != null) {
			return planServices.remove(ser);
		}
		return false;
	}
	
	//Final de funciones creadas
	
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

	public float getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(float precioBase) {
		this.precioBase = precioBase;
	}

}
