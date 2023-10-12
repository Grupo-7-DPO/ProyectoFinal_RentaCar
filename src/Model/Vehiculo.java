package Model;

public class Vehiculo {

	String placa;
	String modelo;
	String tipo;
	String color;
	String transmision;
	String capacidad;
	String estado;
	
	public Vehiculo(String placa, String marca, String modelo, String tipo, String color, String transmision, String capacidad, String estado){

		this.modelo = modelo;
		this.placa = placa;
		this.tipo = tipo;
		this.color = color;
		this.transmision = transmision;
		this.capacidad = capacidad;
		this.estado = estado;
	}
}
