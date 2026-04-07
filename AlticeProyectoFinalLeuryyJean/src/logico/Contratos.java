package logico;

import java.time.LocalDateTime;

public class Contratos {
	public enum Estado {PENDIENTE, VIGENTE, SUSPENDIDO, CANCELADO} /*PENDIENDTE (ya se hizo el contrato, 
	pero aun no se ha instalado), VIGENTE (paga a tiempo y el servicio funciona), 
	SUSPENDIDO (cuando hay corte por deuda), CANCELADO (aplica al cambiar de plan, se cancela y se crea otro contrato nuevo)*/
	private String numeroContrato;
	private LocalDateTime inicio;
	private LocalDateTime corte; //fecha del proximo mes de pago, se actualiza al hacer pagos
	private Estado estado;
	private Plan plan;
	private Cliente cli;
	private Empleado emp; //id del empleado que hizo el contrato
	private String direccionInstalacion; //obtenido a traves del cliente
	
	public Contratos(String numeroContrato, Plan plan, Cliente cli, Empleado emp, String direccionInstalacion) {
		super();
		this.numeroContrato = numeroContrato;
		this.inicio = LocalDateTime.now();
		this.corte = inicio.plusMonths(1);
		this.estado = Estado.PENDIENTE; //creamos el estado como pendiente para esperar mensaje de confirmacion de instalacion
		this.plan = plan;
		this.cli = cli;
		this.emp = emp;
		this.direccionInstalacion = cli.direccion; //Asumimos que la direccion del cliente es la misma de su casa
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public LocalDateTime getCorte() {
		return corte;
	}

	public Estado getEstado() {
		return estado;
	}

	public String getDireccionInstalacion() {
		return direccionInstalacion;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public void setCorte(LocalDateTime corte) {
		this.corte = corte;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Plan getPlan() {
		return plan;
	}

	public Cliente getCli() {
		return cli;
	}

	public Empleado getEmp() {
		return emp;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public void setCli(Cliente cli) {
		this.cli = cli;
	}

	public void setEmp(Empleado emp) {
		this.emp = emp;
	}
}
