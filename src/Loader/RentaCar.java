package Loader;

import java.util.HashMap;
import java.util.List;

import Model.Cliente;
import Model.Empleado;
import Model.Sede;
import Model.Usuario;
import Model.Vehiculo;

public class RentaCar {

	HashMap <String, List<Vehiculo>> tipos_vehiculos;
	List<Vehiculo> total_vehiculos;
	List<Empleado> todos_empleados;
	List<Usuario> usuarios;
	HashMap <String, Sede> sedes;
	List<Cliente> clientes;
	
	public RentaCar(HashMap<String, List<Vehiculo>> tipos_vehiculos, List<Vehiculo> total_vehiculos,
			List<Empleado> todos_empleados, List<Usuario> usuarios, HashMap<String, Sede> sedes, List<Cliente> clientes) {
		
		this.tipos_vehiculos = tipos_vehiculos;
		this.total_vehiculos = total_vehiculos;
		this.todos_empleados = todos_empleados;
		this.usuarios = usuarios;
		this.sedes = sedes;
		this.clientes = clientes;
	}

}
