package Model;

public class Vehiculo {

	String placa;
	String marca;
	String modelo;
	String tipo;
	String color;
	String transmision;
	String capacidad;
	String estado;
	String sede;
	
	public Vehiculo(String placa, String marca, String modelo, String tipo, String color, String transmision, String capacidad, String estado, String sede){

		this.modelo = modelo;
		this.placa = placa;
		this.tipo = tipo;
		this.color = color;
		this.transmision = transmision;
		this.capacidad = capacidad;
		this.estado = estado;
		this.sede = sede;
	}
	
	public String getEstado() {
		return this.estado;
	}
	
	public String getCar() {
		return placa +" "+ tipo + " "+ capacidad +" "+ marca +" " + modelo+" "+ estado ;
	}

	public String getPlaca() {
		// TODO Auto-generated method stub
		return this.placa;
	}

	public String getMarca() {
		// TODO Auto-generated method stub
		return this.marca;
	}

	public String getModelo() {
		// TODO Auto-generated method stub
		return this.modelo;
	}

	public String getTipo() {
		// TODO Auto-generated method stub
		return this.tipo;
	}

	public String getColor() {
		// TODO Auto-generated method stub
		return this.color;
	}

	public String getTrans() {
		// TODO Auto-generated method stub
		return this.transmision;
	}

	public String getCapacidad() {
		// TODO Auto-generated method stub
		return this.capacidad;
	}
	
	public String getSede(){
		return this.sede;
	}
}
