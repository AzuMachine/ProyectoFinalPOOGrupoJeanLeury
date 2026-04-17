package logico;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Empleado extends Persona implements Serializable{
	private static final long serialVersionUID = 1L;
	private String idEmpleado; 
	private float sueldoBase;
	private float comisiones; //Depende del tipo de trabajador, Administrador-> 15% sueldo base, Comercial ->5% por cada venta, tecnico-> 100$ por ticket resuelto
	private String departamento; //-> Finanzas y ventas|Campo y mantenimiento|Administracion
	private LocalDateTime fechaIngreso; //Fecha de contratacion
	
	public Empleado(String nombre,Usuario user, int edad, String direccion, String sexo, String numeroContacto, String correo,
			String rnc, String idEmpleado) {
		super(nombre, user, edad, direccion, sexo, numeroContacto, correo, rnc);
		this.idEmpleado = idEmpleado;
		this.departamento = null;
		this.sueldoBase = 20000f; //definimos que cada uno gana esto por defecto
		this.comisiones = 0f; //al crearlo no hay comisiones
		this.fechaIngreso = LocalDateTime.now();
	}

	public String getIdEmpleado() {
		return idEmpleado;
	}

	public float getSueldoBase() {
		return sueldoBase;
	}

	public float getComisiones() {
		return comisiones;
	}

	public String getDepartamento() {
		return departamento;
	}

	public LocalDateTime getFechaIngreso() {
		return fechaIngreso;
	}

	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public void setSueldoBase(float sueldoBase) {
		this.sueldoBase = sueldoBase;
	}

	public void setComisiones(float comisiones) {
		this.comisiones = comisiones;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public void setFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
	public void sumarComision(float monto) {
	    this.comisiones += monto;
	}
	
}
