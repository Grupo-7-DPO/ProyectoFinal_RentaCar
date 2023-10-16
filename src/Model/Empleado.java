package Model;

public class Empleado extends Usuario{

	String sede;
	
	public Empleado(String nombre, String tipo, String user, String password, String sede) {
		super(nombre, tipo, user, password);
		this.sede = sede;
	}

	public String getSede() {
		return this.sede;
	}
	


}
