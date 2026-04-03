package logico;

public class Servicio {
	
	public enum Serv {TELEFONIA,INTERNET,TELEVISION}
	private String idService;
	private String nombre;
	private String descTecnica; //dice las caracteristicas del servicio
	private Serv tipo;
	private float costoMensualInd; //costo base individual por servicio, en el plan se aplican los impuestos tras haber sumado cada servicio
	private boolean habilitado;
	
	public Servicio(String idService, String nombre, String descTecnica, Serv tipo, float costoMensualInd,
			boolean habilitado) {
		super();
		this.idService = idService;
		this.nombre = nombre;
		this.descTecnica = descTecnica;
		this.tipo = tipo;
		this.setCostoMensualInd(costoMensualInd);
		this.habilitado = true;
	}

	public String getIdService() {
		return idService;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescTecnica() {
		return descTecnica;
	}

	public Serv getTipo() {
		return tipo;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setIdService(String idService) {
		this.idService = idService;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescTecnica(String descTecnica) {
		this.descTecnica = descTecnica;
	}

	public void setTipo(Serv tipo) {
		this.tipo = tipo;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public float getCostoMensualInd() {
		return costoMensualInd;
	}

	public void setCostoMensualInd(float costoMensualInd) {
		this.costoMensualInd = costoMensualInd;
	} 
	
}
