package Loader;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import Model.Categoria;
import Model.Cliente;
import Model.Empleado;
import Model.LicenciaConduccion;
import Model.Reserva;
import Model.Sede;
import Model.Seguro;
import Model.TarjetaCredito;
import Model.Usuario;
import Model.Vehiculo;
import Model.Admin;

public class RentaCar {

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
	
	public RentaCar(HashMap<String, List<Vehiculo>> tipos_vehiculos, List<Vehiculo> total_vehiculos,
			List<Empleado> todos_empleados, List<Usuario> usuarios, HashMap<String, Sede> sedes, List<Cliente> clientes, List<Reserva> reservas, List<String> nombre_sedes, List<Seguro> seguros, List<Categoria> categorias) {
		
		this.tipos_vehiculos = tipos_vehiculos;
		this.total_vehiculos = total_vehiculos;
		this.todos_empleados = todos_empleados;
		this.usuarios = usuarios;
		this.sedes = sedes;
		this.clientes = clientes;
		this.reservas = reservas;
		this.nombre_sedes = nombre_sedes;
		this.seguros = seguros;
		this.categorias = categorias;
	}

	//Getters
	public List<Usuario> getUsuarios(){
		return this.usuarios;
	}
	
	public List<Categoria> getCategorias(){
		return this.categorias;
	}
	
	public List<Seguro> getSeguros(){
		return this.seguros;
	}
	
	public List<Cliente> getClientes(){
		return this.clientes;
	}
	
	public List<String> getNombreSedes(){
		return this.nombre_sedes;
	}
	
	public List<Vehiculo> getVehiculo(){
		return this.total_vehiculos;
	}
	
	//Setters
	public boolean elimEmpleado(String username, String password) throws IOException {
		Usuario user = encontrarUsuario(username,password);
		if (!user.equals(null)) {
			usuarios.remove(user);
			writeArchivoUsuario();
			return true;
		}
		return false;
			
	}
	
	public List<Vehiculo> getInventarioDisponible(){
		List<Vehiculo> invDispo = new ArrayList<>(total_vehiculos);
		for (Vehiculo car : total_vehiculos)
			if (!car.getEstado().equals("disponible"))
			{
				invDispo.remove(car);
			}
		//System.out.println(total_vehiculos);
		return invDispo;
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
	
	public Usuario encontrarUsuarioConUsername(String username) {
		List<Usuario> lista = this.usuarios;
		Usuario usuario_buscado = null;
		
		for(Usuario usuario: lista) {
			if (usuario.getUser().equals(username)) {
				usuario_buscado = usuario;
			}
		}
		return usuario_buscado;
	}
	
	public Reserva encontrarReserva(String id_reserva){
		Reserva reserva_buscada = null;
		List<Reserva> lista = this.reservas;
		
		for(Reserva reserva_iteracion : lista) {
			if(reserva_iteracion.getId().equals(id_reserva)) {
				reserva_buscada = reserva_iteracion;
			}
		}
		
		return reserva_buscada;
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
	
	public long calcularTarifaReserva(String fecha_recogida, String fecha_entrega, Categoria tipo_carro, Seguro seguro) {
		long cantidad_dias = calcularDiferenciaDias(fecha_recogida, fecha_entrega);
		int tarifa_dia = 0;
		
		if (seguro == null) {
			tarifa_dia = tipo_carro.getPrecio();
		}
		else {
			tarifa_dia = tipo_carro.getPrecio() + seguro.getPrecio();
		}
		
		return tarifa_dia * cantidad_dias;
	}
	
	public long calcularDiferenciaDias(String fecha1Str, String fecha2Str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        LocalDate fecha1 = LocalDate.parse(fecha1Str, formatter);
        LocalDate fecha2 = LocalDate.parse(fecha2Str, formatter);

        // Calcula la diferencia en días
        return ChronoUnit.DAYS.between(fecha1, fecha2);
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
		if(tipo.equals("AG") | tipo.equals("A") | tipo.equals("E")) {
			Empleado empleado = new Empleado(nombre, tipo, username, password);
			todos_empleados.add(empleado);
			//System.out.println(todos_empleados.toString());
		}
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
	
	private void writeArchivoUsuario() throws IOException {
		FileWriter file = new FileWriter("./data/usuarios.txt");
		BufferedWriter br = new BufferedWriter(file);
		for (Usuario nuevo_usuario : usuarios) {
			String nombre = nuevo_usuario.getNombre();
			String tipo = nuevo_usuario.getTipo();
			String username = nuevo_usuario.getUser();
			String password = nuevo_usuario.getPassword();
			
			br.write("\n" + nombre + ";" + tipo + ";" + username + ";" + password);
		}
		
		br.close();
	}
	
	
	public void crearVehiculo(String placa, String marca, String modelo, String tipo, String trans, String capacidad, String estado) {
		Vehiculo newCar = new Vehiculo(placa,marca,modelo,tipo,trans,capacidad,estado);
	}

	public String crearId() {
		Random random = new Random();
		String id = Integer.toString(random.nextInt(9999));
		return id;
	}
}
