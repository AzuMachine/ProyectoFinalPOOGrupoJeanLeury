package logico;

public abstract class Persona {
	protected Usuario user; 
	protected String nombre;
	protected boolean estado; //true para activo false para inactivo
	protected int edad; //Si es cero, es una empresa.
	protected String direccion;
	protected String sexo; //Masculino /Femenino
	protected String numeroContacto;
	protected String correo; 
	protected String rnc; //Si es fisico (persona) es cedula, si es una empresa es RNC
	
	
	public Persona(String nombre, Usuario user, int edad, String direccion, String sexo, String numeroContacto, String correo,
			String rnc) {
		super();
		this.nombre = nombre;
		this.user = user;
		this.estado = true;  //estado inicia siempre como activo
		this.edad = edad;
		this.direccion = direccion;
		this.sexo = sexo;
		this.numeroContacto = numeroContacto;
		this.correo = correo;
		this.rnc = rnc;
	}


	public Usuario getUser() {
		return user;
	}


	public boolean isEstado() {
		return estado;
	}


	public int getEdad() {
		return edad;
	}


	public String getDireccion() {
		return direccion;
	}


	public String getSexo() {
		return sexo;
	}


	public String getNumeroContacto() {
		return numeroContacto;
	}


	public String getCorreo() {
		return correo;
	}


	public String getRnc() {
		return rnc;
	}


	public void setUser(Usuario user) {
		this.user = user;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public void setNumeroContacto(String numeroContacto) {
		this.numeroContacto = numeroContacto;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public void setRnc(String rnc) {
		this.rnc = rnc;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
