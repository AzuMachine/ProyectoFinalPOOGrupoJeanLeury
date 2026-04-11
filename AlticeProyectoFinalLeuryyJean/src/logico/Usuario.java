package logico;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public enum rol{ADMINISTRADOR, COMERCIAL, TECNICO, CLIENTE}
	private rol rol; //rol de la persona para
	private String userName;
	private String password;
	private LocalDateTime ultimoIngreso;
	
	
	public Usuario(logico.Usuario.rol rol, String userName, String password) {
		super();
		this.rol = rol;
		this.userName = userName;
		this.password = password;
		this.ultimoIngreso = null;
	}


	public rol getRol() {
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


	public void setRol(rol rol) {
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
