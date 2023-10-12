package Model;

public class Seguro {
	
	String nombre;
	int precio;
	
	public Seguro(String nombre, int precio_por_dia) {
		this.nombre = nombre;
		this.precio = precio_por_dia;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public int getPrecio() {
		return this.precio;
	}
}
