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
			this.usuarios.remove(user);
			writeArchivoUsuario();
			return true;
		}
		return false;
			
	}
	
	public List<Vehiculo> getInventarioGeneral(){
		return total_vehiculos;
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
	
	public List<Vehiculo> getInventarioDispSede(String name){
		Sede sede =sedes.get(name);
		List<Vehiculo>invDispo = new ArrayList<>();
		if (!sede.equals(null)) {
			invDispo = sede.getInventarioDisponible();
		}
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
	
	public Vehiculo encontrarVehiculo(String placa) {
		Vehiculo findCar = null;
		List<Vehiculo> cars = this.total_vehiculos;
		
		for (Vehiculo car : cars) {
			if(car.getPlaca().equals(placa)) {
				findCar = car;
			}
		}
		return findCar;
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
		try {
			modificarArchivoUsuario(nuevo_usuario);
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
	
	public void crearUsuarioAdminEmpleado(String nombre, String tipo, String username, String password, String sede) {
		Empleado empleado = new Empleado(nombre, tipo, username, password, sede);
		this.todos_empleados.add(empleado);
		try {
			modificarArchivoUsuarioEmpleadoAdmin(empleado);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void crearReserva(String id, String usuario, Categoria tipo, String sede_recogida, String fecha_recogida, String hora_recogida, String sede_entrega, String fecha_entrega, String hora_entrega, String pago, Seguro seguro) {
		Reserva nueva_reserva = new Reserva(id, usuario, tipo, sede_recogida, fecha_recogida, hora_recogida, sede_entrega, fecha_entrega, hora_entrega, "pagado", seguro);
		this.reservas.add(nueva_reserva);
		
		try {
			modificarArchivoReservas(nueva_reserva);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	private void modificarArchivoReservas(Reserva nueva_reserva) throws IOException {
		FileWriter file = new FileWriter("./data/reservas.txt", true);
		BufferedWriter br = new BufferedWriter(file);
		
		String id = nueva_reserva.getId();
		String usuario = nueva_reserva.getUsername();
		String tipo = nueva_reserva.getTipo().getNombre();
		String sede_recogida = nueva_reserva.getSedeRecogida();
		String fecha_recogida = nueva_reserva.getDiaRecogida();
		String hora_recogida = nueva_reserva.getHoraRecogida();
		String sede_entrega = nueva_reserva.getSedeEntrega();
		String fecha_entrega = nueva_reserva.getDiaEntrega();
		String hora_entrega = nueva_reserva.getHoraEntrega();
		String pago = nueva_reserva.getPago();
		String seguro;
		if (nueva_reserva.getSeguro() == null) {
			seguro = "null";
		}
		else {
			seguro = nueva_reserva.getSeguro().getNombre();
		}
		
		br.write("\n" + id + ";" + usuario + ";" + tipo + ";" + sede_recogida + ";" + fecha_recogida + ";"
				+ hora_recogida + ";" + sede_entrega + ";" + fecha_entrega + ";" + hora_entrega + ";"
				+ pago + ";" + seguro);
		br.close();
		
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
	
	public void modificarArchivoUsuarioEmpleadoAdmin(Empleado nuevo_usuario) throws IOException { 
		//Este metodo va a modificar ek archivo de usuarios, recibiendo como parametro el usuario nuevo
		FileWriter file = new FileWriter("./data/usuarios.txt", true);
		BufferedWriter br = new BufferedWriter(file);
		
		String nombre = nuevo_usuario.getNombre();
		String tipo = nuevo_usuario.getTipo();
		String username = nuevo_usuario.getUser();
		String password = nuevo_usuario.getPassword();
		String sede = nuevo_usuario.getSede();
		
		br.write("\n" + nombre + ";" + tipo + ";" + username + ";" + password + ";" + sede);
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
	
	
	public boolean crearVehiculo(String placa, String marca, String modelo, String tipo, String color, String trans, String capacidad, String estado, String sede) throws IOException {
		Vehiculo newCar = new Vehiculo(placa,marca,modelo,tipo,color,trans,capacidad,estado,sede);
		Sede site = sedes.get(sede);
		if (!site.equals(null))
		{
			site.addVehiculo(newCar);
			this.total_vehiculos.add(newCar);
			writeArchivoVehiculo();
			return true;
		}
		return false;
		
	}
	
	public boolean eliminarVehiculo(String placa) throws IOException {
		Vehiculo elimCar = encontrarVehiculo(placa);
		String sede = elimCar.getSede();
		Sede site = sedes.get(sede);
		if (!elimCar.equals(null)&&!site.equals(null)) {
			total_vehiculos.remove(elimCar);
			site.removeVehiculo(elimCar);
			writeArchivoVehiculo();
			return true;
		}
		return false;
	}
	
	public boolean cambiarSedeVehiculo(String placa,String sede) throws IOException {
		Vehiculo car = encontrarVehiculo(placa);
		Sede sedeOld = sedes.get(car.getSede());
		Sede sedeNew = sedes.get(sede);
		if (!car.equals(null)&&!sedeOld.equals(null)&&!sedeNew.equals(null)) {
			sedeOld.removeVehiculo(car);
			car.setSede(sede);
			sedeNew.addVehiculo(car);
			writeArchivoVehiculo();
			return true;
		}
		return false;
	}
	
	public void writeArchivoVehiculo() throws IOException {
		FileWriter file = new FileWriter("./data/automoviles.txt");
		BufferedWriter br = new BufferedWriter(file);
		br.write("placa;marca;modelo;tipo;color;trasnmision;capacidad;estado;sede");
		for (Vehiculo newCar : this.total_vehiculos) {
			String placa = newCar.getPlaca();
			String marca = newCar.getMarca();
			String modelo = newCar.getModelo();
			String tipo = newCar.getTipo();
			String color = newCar.getColor();
			String trans = newCar.getTrans();
			String capacidad = newCar.getCapacidad();
			String estado = newCar.getEstado();
			String sede = newCar.getSede();
			
			br.write("\n"+ placa +";"+ marca + ";"+ modelo +";"+ tipo +";" + color+";"+ trans +";"+ capacidad + ";" + estado+";" +sede);
		}
		
		br.close();
	}

	public String crearId() {
		Random random = new Random();
		String id = Integer.toString(random.nextInt(9999));
		return id;
	}
	
	
	
	
}
