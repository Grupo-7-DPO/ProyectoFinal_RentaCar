package Consola;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import Loader.LoaderRentaCar;
import Loader.RentaCar;
import Model.Categoria;
import Model.Cliente;
import Model.LicenciaConduccion;
import Model.Renta;
import Model.Reserva;
import Model.Seguro;
import Model.TarjetaCredito;
import Model.Usuario;
import Model.Vehiculo;

public class ConsolaRentaCar{

	protected static RentaCar renta_carros;
	protected static Usuario usuario_actual;
	protected static JFrame frame = new JFrame();
	
	public static void main(String[] args) throws IOException {
		
		try {
	        cargarArchivos();
	    } catch (FileNotFoundException e) {
	        System.out.println("Error: no se encontró el archivo.");
	    } catch (IOException e) {
	        System.out.println("Error de I/O: " + e.getMessage());
	    }
		login();
	
	}
	
	protected static void consolaCliente(){
		ConsolaCliente consolaCliente = new ConsolaCliente(frame, usuario_actual, renta_carros);
	}

	protected static void consolaEmpleado(){
		ConsolaEmpleado consolaEmpleado = new ConsolaEmpleado(frame, usuario_actual, renta_carros);
		
	}

	
	static void consolaAdmin(){
		// TODO Auto-generated method stub
		ConsolaAdmin consolaAdmin = new ConsolaAdmin(frame, usuario_actual, renta_carros); 
		
	}

	static void consolaAdminG(){
		// TODO Auto-generated method stub
		ConsolaAdminG consolaAdminG = new ConsolaAdminG(frame, usuario_actual, renta_carros);
		
	}

	
	protected static void crearReserva(boolean especial) {
		CrearReserva crearReserva = new CrearReserva(renta_carros, usuario_actual, frame, especial);
	}

	
	protected static void comenzarReserva(String sede_empleado) {
		ComenzarReserva comenzarReserva = new ComenzarReserva(sede_empleado,frame, renta_carros, usuario_actual);
		
	}
	
	
	protected static void recibirCarroReservado() {
		RecibirCarroReservado recibirCarro = new RecibirCarroReservado(frame, renta_carros,usuario_actual);
		
	}
	
	protected static void cambiarEstadoCarro() {
		
		CambiarEstadoCarro cambiarEstado = new CambiarEstadoCarro(frame, renta_carros, usuario_actual);
		
	}
	
	protected static void consultarReserva() {
		ConsultarReserva consultarReserva = new ConsultarReserva(frame, usuario_actual, renta_carros);
		
	}
	
	private static void cargarArchivos() throws FileNotFoundException, IOException {
		renta_carros = LoaderRentaCar.cargar_archivos("./data/automoviles.txt", "./data/clientes.txt", "./data/sedes.txt", "./data/usuarios.txt", "./data/reservas.txt", "./data/seguros.txt", "./data/categorias.txt", "./data/historial_reservas.csv");
	}
	
	protected static void login() {
		LoginPage loginPage = new LoginPage(renta_carros, frame);
	}
	
	public static void verOcupacion(String sede) {
		ConsolaOcupacion ocupacion = new ConsolaOcupacion(sede,renta_carros, frame, usuario_actual);
	}
	
	protected static void crearUsuarioCliente() {
		new CrearUsuarioCliente(frame, renta_carros);
		/*boolean user_valido = true;
		
		String nombre = input("\nEscribe tu nombre completo");
		String tipo = "C";
		String username = null;
		while (user_valido) {
			username = input("\nEscribe tu nombre de usuario");
			if (renta_carros.encontrarUsername(username)) {
				System.out.println("\nEste nombre de usuario ya está en uso\n");
			}
			else {
				user_valido = false;
			}
		}
		String password = input("Escribe tu contraseña");
		renta_carros.crearUsuarioCliente(nombre, tipo, username, password);
		crearCliente(nombre, username);
		System.out.println("\n¡¡Cuenta creada correctamente!!\n");
		Usuario nuevo_usuario = renta_carros.encontrarUsuario(username, password);
		return nuevo_usuario;*/
	}

	protected static void crearCliente(String nombre, String username, String tipo, String password) {
		new CrearCliente(frame, renta_carros, nombre, username, tipo, password);
		/*String contacto = input("\nEscribe tu numero de contacto");
		String fecha_nacimiento = input("\nEscribe tu fecha de nacimiento(dd/mm/aaaa)");
		String nacionalidad = input("\nEscribe tu nacionalidad");
		String imagen_documento = input("\nIngresa la imagen tu documento de identidad en formato .png (nombrearchivo.png)");
	/	String numero_licencia = input("\nIngresa el numero de tu licencia");
	/	String pais_licencia = input("\nIngresa el pais de tu licencia");
		String fecha_vencimiento_pase = input("\nIngresa la fecha de vencimiento de tu licencia de conduccion(dd/mm/aaaa)");
		String imagen_pase = input("\nIngresa la imagen de tu licencia en formato .png(nombrearchivo.png)");
		String tipo_tarjeta = input("\nIngresa el tipo de tu tarjeta de credito(visa, mastercard,etc.)");
		
		String numero_tarjeta = input("\nIngresa el numero de tu tarjeta de credito");
		String fecha_vencimiento_tarjeta = input("\nIngresa la fecha de vencimiento de tu tarjeta de credito(mm/aaaa)");
		
		renta_carros.crearCliente(nombre, contacto, fecha_nacimiento, nacionalidad, imagen_documento, numero_licencia, pais_licencia,
									fecha_vencimiento_pase, imagen_pase, tipo_tarjeta, numero_tarjeta, fecha_vencimiento_tarjeta, username);*/
	}


	public static String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	
	}

	protected static void setUsuario(Usuario usuario) {
		usuario_actual = usuario;
	}
	
	public static Usuario getUsuario() {
		return usuario_actual;
	}
	
	public static boolean crearVehiculo(String placa, String marca, String modelo, String tipo, String color, String trans, String capacidad, String estado, String sede) throws IOException {
		return renta_carros.crearVehiculo(placa, marca, modelo, tipo, color, trans, capacidad, estado, sede);
	}
	
	public static boolean eliminarVehiculo(String placa) throws IOException {
		return renta_carros.eliminarVehiculo(placa);
	}
	
	public static boolean crearSeguro(String name, int price) {
		try {
			renta_carros.crearSeguro(name, price);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	
	public static boolean crearEmpleado(String name, String user, String pass, String sede) {
		return renta_carros.crearUsuarioAdminEmpleado(name, "E", user, pass, sede);
	}
	
	public static boolean eliminarEmpleado(String user) {
		try {
			return renta_carros.elimEmpleado(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	
	
}
