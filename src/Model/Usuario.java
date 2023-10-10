package Model;

public class Usuario {
	String nombre;
	String tipo;
	String user;
	String password;
	
	public Usuario(String nombre, String tipo, String user, String password) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.user = user;
		this.password = password;
	}

	public String getTipo() {
		return this.tipo;
	}
	
	public String getUser() {
		return this.user;
	}
	
	public String getPassword() {
		return this.password;
	}

	public String getNombre() {
		return this.nombre;
	}
}
