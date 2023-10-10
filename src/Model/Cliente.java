package Model;

public class Cliente {

	String nombre;
	String contacto;
	String fecha_nacimiento;
	String nacionalidad;
	String imagen_cedula;
	LicenciaConduccion licencia;
	TarjetaCredito tarjeta;
	String username;
	
	public Cliente(String nombre, String contacto, String fecha_nacimiento, String nacionalidad, String imagen_cedula, LicenciaConduccion licencia, TarjetaCredito tarjeta, String username) {
		this.nombre = nombre;
		this.contacto = contacto;
		this.fecha_nacimiento = fecha_nacimiento;
		this.nacionalidad = nacionalidad;
		this.imagen_cedula = imagen_cedula;
		this.licencia = licencia;
		this.tarjeta = tarjeta;
		this.username = username;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public String getContacto() {
		return this.contacto;
	}

	public String getNacimiento() {
		return this.fecha_nacimiento;
	}

	public String getNacionalidad() {
		return this.nacionalidad;
	}
	
	public String getImagen_cedula() {
		return this.imagen_cedula;
	}

	public LicenciaConduccion getLicencia() {
		return this.licencia;
	}

	public TarjetaCredito getTarjeta() {
		return this.tarjeta;
	}

	public String getUser() {
		return this.username;
	}

}
