package Model;

public class Sede {

	String nombre;
	String hora_apertura;
	String hora_cierre;
	String dias_atencion;
	String ubicacion;
	
	public Sede(String nombre, String hora_apertura, String hora_cierre, String dias_atencion, String ubicacion) {
		this.nombre = nombre;
		this.hora_apertura = hora_apertura;
		this.hora_cierre = hora_cierre;
		this.dias_atencion = dias_atencion;
		this.ubicacion = ubicacion;
	}

}
