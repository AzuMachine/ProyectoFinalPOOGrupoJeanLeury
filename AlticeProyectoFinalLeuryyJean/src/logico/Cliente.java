package logico;

import java.util.ArrayList;

public class Cliente extends Persona {
	public enum Cat {FISICO, JURIDICO}
	private Cat categoria; //Fisico o juridico
	private boolean deuda; //para saber si hay deuda o no
	private float montoDeuda; /*La deuda crece al crear contrato, decrece al hacer un pago de cualquiera de sus servicios, si debe dos meses de algun contrato especifico, se le inhabilita contratar otro*/
	private ArrayList<Pago> misPagos;
	private ArrayList<Contratos> misContratos; //Cliente puede tener record historico, pero solo uno en habilitado/vigente
	public Cliente(String nombre, Usuario user, int edad, String direccion, String sexo, String numeroContacto,
			String correo, String rnc, logico.Cliente.Cat categoria) {
		super(nombre, user, edad, direccion, sexo, numeroContacto, correo, rnc);
		this.categoria = categoria;
		this.deuda = false;
		this.montoDeuda = 0f;
		this.misPagos = new ArrayList<>();
	}

	public boolean isDeuda() {
		return deuda;
	}

	public float getMontoDeuda() {
		return montoDeuda;
	}

	public ArrayList<Pago> getMisPagos() {
		return misPagos;
	}

	public ArrayList<Contratos> getMisContratos() {
		return misContratos;
	}


	public void setDeuda(boolean deuda) {
		this.deuda = deuda;
	}

	public void setMontoDeuda(float montoDeuda) {
		this.montoDeuda = montoDeuda;
	}

	public Cat getCategoria() {
		return categoria;
	}

	public void setCategoria(Cat categoria) {
		this.categoria = categoria;
	}

	
}
