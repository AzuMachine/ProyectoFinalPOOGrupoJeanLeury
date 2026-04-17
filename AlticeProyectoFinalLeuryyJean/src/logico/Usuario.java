package logico;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	public enum rolEmp{ADMINISTRADOR, COMERCIAL, TECNICO, CLIENTE}
	private rolEmp rol; //rol de la persona para
	private String userName;
	private String password;
	private LocalDateTime ultimoIngreso;
	
	
	public Usuario(logico.Usuario.rolEmp rol, String userName, String password) {
		super();
		this.rol = rol;
		this.userName = userName;
		this.password = password;
		this.ultimoIngreso = null;
	}


	public rolEmp getRol() {
		return rol;
	}


	public String getUserName() {
		return userName;
	}


	public String getPassword() {
		return password;
	}


	public LocalDateTime getUltimoIngreso() {
		return ultimoIngreso;
	}


	public void setRol(rolEmp rol) {
		this.rol = rol;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setUltimoIngreso(LocalDateTime ultimoIngreso) {
		this.ultimoIngreso = ultimoIngreso;
	}
	
}
