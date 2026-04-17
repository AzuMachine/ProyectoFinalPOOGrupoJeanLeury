package logico;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Ticket implements Serializable{
	private static final long serialVersionUID = 1L;
	public enum Tipo{INSTALACION, AVERIA}
	public enum Prioridad {ALTA, MEDIA, BAJA}
	public enum Estado {ABIERTO, EN_PROCESO, RESUELTO}
	private String idTicket;
	private Cliente cli;
	private Contrato con; //Para saber el servicio/o cual de ellos falla
	private Tipo type;
	private Prioridad pri;
	private String desc; // detalle del ticket
	private Estado state;
	private Empleado tecnico; //tecnico al que se le asigna (hacer verificaciones de lugar) 
	private LocalDateTime fechaCreacion;
	
	public Ticket(String idTicket, Cliente cli, Contrato con, Tipo type, Prioridad pri, String desc, Empleado tecnico) {
		super();
		this.idTicket = idTicket;
		this.cli = cli;
		this.con = con;
		this.type = type;
		this.pri = pri;
		this.desc = desc;
		this.tecnico = tecnico;
		this.fechaCreacion = LocalDateTime.now();
		this.state = Estado.ABIERTO;
	}

	public String getIdTicket() {
		return idTicket;
	}

	public Cliente getCli() {
		return cli;
	}

	public Contrato getCon() {
		return con;
	}

	public Tipo getType() {
		return type;
	}

	public Prioridad getPri() {
		return pri;
	}

	public String getDesc() {
		return desc;
	}

	public Estado getState() {
		return state;
	}

	public Empleado getTecnico() {
		return tecnico;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setIdTicket(String idTicket) {
		this.idTicket = idTicket;
	}

	public void setCli(Cliente cli) {
		this.cli = cli;
	}

	public void setCon(Contrato con) {
		this.con = con;
	}

	public void setType(Tipo type) {
		this.type = type;
	}

	public void setPri(Prioridad pri) {
		this.pri = pri;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setState(Estado state) {
		this.state = state;
	}

	public void setTecnico(Empleado tecnico) {
		this.tecnico = tecnico;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	
	
}
