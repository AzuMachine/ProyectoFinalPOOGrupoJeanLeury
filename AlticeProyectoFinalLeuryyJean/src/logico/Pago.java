package logico;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Pago implements Serializable{
	private static final long serialVersionUID = 1L;
	public enum Metodo {EFECTIVO, TRANSFERENCIA, TARJETA, PUNTO_ALTICE}
	private String numeroRecibo;
	private Contrato con;
	private float monto;
	private LocalDateTime fechaPago; 
	private String numCompFis; //en caso de ser una persona, solo poner N/A
	
	public Pago(String numeroRecibo, Contrato con, float monto, String numCompFis) {
		super();
		this.numeroRecibo = numeroRecibo;
		this.con = con;
		this.monto = monto;
		this.fechaPago = LocalDateTime.now();
		this.numCompFis = numCompFis;
	}

	public String getNumeroRecibo() {
		return numeroRecibo;
	}

	public Contrato getCon() {
		return con;
	}

	public float getMonto() {
		return monto;
	}

	public LocalDateTime getFechaPago() {
		return fechaPago;
	}

	public String getNumCompFis() {
		return numCompFis;
	}

	public void setNumeroRecibo(String numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}

	public void setCon(Contrato con) {
		this.con = con;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public void setFechaPago(LocalDateTime fechaPago) {
		this.fechaPago = fechaPago;
	}

	public void setNumCompFis(String numCompFis) {
		this.numCompFis = numCompFis;
	}
	
}
