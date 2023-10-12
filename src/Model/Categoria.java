package Model;

public class Categoria {

	String tipo;
	int precio_por_dia;
	
	public Categoria(String tipo, int precio_por_dia) {
		this.tipo = tipo;
		this.precio_por_dia = precio_por_dia;
	}

	public int getPrecio() {
		return this.precio_por_dia;
	}

	public String getNombre() {
		// TODO Auto-generated method stub
		return this.tipo;
	}
}
