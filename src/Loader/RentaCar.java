package Loader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import Model.Cliente;
import Model.Empleado;
import Model.LicenciaConduccion;
import Model.Sede;
import Model.TarjetaCredito;
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

	public List<Usuario> getUsuarios(){
		return this.usuarios;
	}
	
	public List<Cliente> getClientes(){
		return this.clientes;
	}
	
	public List<Vehiculo> getVehiculo(){
		return this.total_vehiculos;
	}
	
	public Usuario encontrarUsuario(String username, String password) {
		// esto busca en la lista general de usuarios y encuentra si un usuario existe o no. Si no retorna null y si si retorna el usuario
		List<Usuario> lista = this.usuarios;
		Usuario usuario_buscado = null;
		
		for(Usuario usuario: lista) {
			if (usuario.getUser().equals(username) && usuario.getPassword().equals(password)) {
				usuario_buscado = usuario;
			}
		}
		return usuario_buscado;
	}
	
	public boolean encontrarUsername(String username_buscado) {
		//esta funcion se encarga de encontrar si ya existe el username que entra por parametro
		boolean se_encontro = false;
		List<Usuario> lista = this.getUsuarios();
		
		for (Usuario usuario: lista) {
			if(usuario.getUser().equals(username_buscado)) {
				se_encontro = true;
			}
		}
		
		return se_encontro;
	}
	public void crearCliente(String nombre, String contacto, String fecha_nacimiento, String nacionalidad, String imagen_documento, String numero_licencia, String pais_licencia,
			String fecha_vencimiento_pase, String imagen_pase, String tipo_tarjeta, String numero_tarjeta, String fecha_vencimiento_tarjeta, String username) {
		TarjetaCredito tarjeta = new TarjetaCredito(tipo_tarjeta, numero_tarjeta, fecha_vencimiento_tarjeta);
		LicenciaConduccion licencia = new LicenciaConduccion(numero_licencia, pais_licencia, fecha_vencimiento_pase, imagen_pase);
		
		Cliente nuevo_cliente = new Cliente(nombre, contacto, fecha_nacimiento, nacionalidad, imagen_documento, licencia, tarjeta, username);
		this.clientes.add(nuevo_cliente);
		try {
			modificarArchivoClientes(nuevo_cliente);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void modificarArchivoClientes(Cliente nuevo_cliente) throws IOException {
		FileWriter file = new FileWriter("./data/clientes.txt", true);
		BufferedWriter br = new BufferedWriter(file);
			
		String nombre = nuevo_cliente.getNombre();
		String contacto = nuevo_cliente.getContacto();
		String fecha_nacimiento = nuevo_cliente.getNacimiento();
		String nacionalidad = nuevo_cliente.getNacionalidad();
		String imagen_documento = nuevo_cliente.getImagen_cedula();
			
		LicenciaConduccion licencia = nuevo_cliente.getLicencia();
		String numero_licencia = licencia.getNumeroLicencia();
		String pais_licencia = licencia.getPaisLicencia();
		String fecha_vencimiento_licencia = licencia.getFechaVencimiento();
		String imagen_licencia = licencia.getImagen();
			
		TarjetaCredito tarjeta = nuevo_cliente.getTarjeta();
		String tipo_tarjeta = tarjeta.getTipo();
		String numero_tarjeta = tarjeta.getNumero();
		String fecha_vencimiento_tarjeta = tarjeta.getFechaVencimiento();
			
		String username = nuevo_cliente.getUser();
			
		br.write("\n" + nombre + ";" + contacto + ";" + fecha_nacimiento + ";" + nacionalidad + ";" + imagen_documento + ";" + numero_licencia + ";" + imagen_documento + ";" + 
						numero_licencia + ";" + pais_licencia + ";" + fecha_vencimiento_licencia + ";" + imagen_licencia + ";" + tipo_tarjeta + ";" + numero_tarjeta + ";" + 
						fecha_vencimiento_tarjeta + ";" + username);
		br.close();
			
		}
	
	public void crearUsuarioCliente(String nombre, String tipo, String username, String password) {
		Usuario nuevo_usuario = new Usuario(nombre, tipo, username, password);
		this.usuarios.add(nuevo_usuario);
		try {
			modificarArchivoUsuario(nuevo_usuario);
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
	
	private void modificarArchivoUsuario(Usuario nuevo_usuario) throws IOException { 
		//Este metodo va a modificar ek archivo de usuarios, recibiendo como parametro el usuario nuevo
		FileWriter file = new FileWriter("./data/usuarios.txt", true);
		BufferedWriter br = new BufferedWriter(file);
		
		String nombre = nuevo_usuario.getNombre();
		String tipo = nuevo_usuario.getTipo();
		String username = nuevo_usuario.getUser();
		String password = nuevo_usuario.getPassword();
		
		br.write("\n" + nombre + ";" + tipo + ";" + username + ";" + password);
		br.close();
	}
}
