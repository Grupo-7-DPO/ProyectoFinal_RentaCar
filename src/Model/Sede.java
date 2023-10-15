package Model;

import java.util.ArrayList;
import java.util.List;

public class Sede {

	String nombre;
	String hora_apertura;
	String hora_cierre;
	String dias_atencion;
	String ubicacion;
	List<Vehiculo> carros_sede;
	
	public Sede(String nombre, String hora_apertura, String hora_cierre, String dias_atencion, String ubicacion, List<Vehiculo> carros_sede) {
		this.nombre = nombre;
		this.hora_apertura = hora_apertura;
		this.hora_cierre = hora_cierre;
		this.dias_atencion = dias_atencion;
		this.ubicacion = ubicacion;
		this.carros_sede = carros_sede;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public List<Vehiculo>getInventario() {
		return this.carros_sede;
	}
	
	public List<Vehiculo> getInventarioDisponible(){
		List<Vehiculo> invDispo = new ArrayList<>(carros_sede);
		for (Vehiculo car : carros_sede)
			if (!car.getEstado().equals("disponible"))
			{
				invDispo.remove(car);
			}
		return invDispo;
	}
	
	public void addVehiculo(Vehiculo car) {
		carros_sede.add(car);
	}
	
	public void removeVehiculo(Vehiculo car) {
		carros_sede.remove(car);
	}

}
