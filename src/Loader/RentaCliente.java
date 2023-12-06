package Loader;

import java.util.HashMap;
import java.util.List;

import Model.Categoria;
import Model.Cliente;
import Model.Empleado;
import Model.Renta;
import Model.Reserva;
import Model.Sede;
import Model.Seguro;
import Model.Usuario;
import Model.Vehiculo;

public class RentaCliente extends RentaCar implements Renta{

	HashMap <String, List<Vehiculo>> tipos_vehiculos;
	List<Vehiculo> total_vehiculos;
	List<Empleado> todos_empleados;
	List<Usuario> usuarios;
	HashMap <String, Sede> sedes;
	List<Cliente> clientes;
	List<Reserva> reservas;
	List<String> nombre_sedes;
	List<Seguro> seguros;
	List<Categoria> categorias;
	
	public RentaCliente(HashMap<String, List<Vehiculo>> tipos_vehiculos, List<Vehiculo> total_vehiculos,
			List<Usuario> usuarios, HashMap<String, Sede> sedes, List<Cliente> clientes,
			List<Reserva> reservas, List<String> nombre_sedes, List<Seguro> seguros, List<Categoria> categorias) {
		
		super(tipos_vehiculos, total_vehiculos, usuarios, sedes, clientes, reservas, nombre_sedes, seguros, categorias);
		
		this.tipos_vehiculos = tipos_vehiculos;
		this.total_vehiculos = total_vehiculos;
		this.usuarios = usuarios;
		this.sedes = sedes;
		this.clientes = clientes;
		this.reservas = reservas;
		this.nombre_sedes = nombre_sedes;
		this.seguros = seguros;
		this.categorias = categorias;
		
		
		
	}

}
