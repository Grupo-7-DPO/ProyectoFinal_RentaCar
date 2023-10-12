package Consola;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import Loader.LoaderRentaCar;
import Loader.RentaCar;
import Model.Cliente;
import Model.LicenciaConduccion;
import Model.TarjetaCredito;
import Model.Usuario;
import Model.Vehiculo;

public class ConsolaRentaCar {

	
	
	private static RentaCar renta_carros;
	private static Usuario usuario_actual;
	
	public static void main(String[] args) throws IOException {
		
		try {
	        cargarArchivos();
	    } catch (FileNotFoundException e) {
	        System.out.println("Error: no se encontró el archivo.");
	    } catch (IOException e) {
	        System.out.println("Error de I/O: " + e.getMessage());
	    }
		System.out.println("¡¡¡Bienvenido a nuestra renta de carros!!!");
		usuario_actual = login();
		System.out.println("\nBienvenido " + usuario_actual.getNombre());
		
		if (usuario_actual.getTipo().equals("AG")) {
			consolaAdminG();
		}
		else if (usuario_actual.getTipo().equals("A")) {
			consolaAdmin();
		}
		else if (usuario_actual.getTipo().equals("E")) {
			consolaEmpleado();
		}
		else if (usuario_actual.getTipo().equals("C")) {
			consolaCliente();
		}
		
		
	}
	
	private static void consolaCliente() {
		// TODO Auto-generated method stub
		
	}

	private static void consolaEmpleado() {
		// TODO Auto-generated method stub
		
	}

	private static void consolaAdmin() {
		// TODO Auto-generated method stub
		
	}

	private static void consolaAdminG() throws IOException {
		// TODO Auto-generated method stub
		boolean continuar = true;
		while (continuar) {
			System.out.println("\n1. Revisar carros del inventario general");
			System.out.println("2. Revisar carros disponibles");
			System.out.println("3. Revisar carros disponibles en una sede");
			System.out.println("4. Crear una reserva");
			System.out.println("5. Gestionar/Eliminar una reserva");
			System.out.println("6. Añadir carro al inventario");
			System.out.println("7. Elminar carro del inventario");
			System.out.println("8. Crear seguro");
			System.out.println("9. Crear empleado");
			System.out.println("10. Eliminar empleado");
			System.out.println("11. Alquiler");
			System.out.println("12. Recibir automovil");
			System.out.println("13. Cambiar estado automovil");
			System.out.println("0. Log Out");
			String choice = input("Elige una opcion");
			
			if (choice.equals("1")) {
				List<Vehiculo>inv = renta_carros.getInventarioGeneral();
				System.out.println("PLACA  TIPO  CAPACIDAD  MODELO  MARCA  ESTADO");
				for (Vehiculo car : inv)
					System.out.println(car.getCar());
			}
			
			if (choice.equals("2")) {
				List<Vehiculo>inv = renta_carros.getInventarioDisponible();
				System.out.println("PLACA  TIPO  CAPACIDAD  MODELO  MARCA  ESTADO");
				for (Vehiculo car : inv)
					System.out.println(car.getCar());
			}
			if (choice.equals("6"))
			{
				
				String placa = input("Escribe la placa");
				String marca = input("Escibe la marca");
				String modelo = input("Escribe el modelo");
				String tipo = input("Escribe el tipo");
				String trans = input("Escribe la transmision");
				String capacidad = input("Escribe la capacidad");
				String estado = input("Escribe el estado");
				
				renta_carros.crearVehiculo(placa,marca,modelo,tipo,trans,capacidad,estado);
				
			}
			
			if (choice.equals("9")) {
				boolean user_valido = true;
				String nombre = input("\nEscribe el nombre del empleado");
				String tipo = "E";
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
				String password = input("Escribe su contraseña");
				renta_carros.crearUsuarioCliente(nombre, tipo, username, password);
				System.out.println("\n¡¡Cuenta creada correctamente!!\n");
			}
			
			if (choice.equals("10")) {
				String user = input("\nEscribe el usuario del empleado");
				String pass = input("Escribe la contraseña");
				boolean resp = renta_carros.elimEmpleado(user,pass); 
				if (resp == true) {
					System.out.println("El empleado fue elimnado");
				}
				else {System.out.println("No se pudo elimnar el empleado");}
			}
			
			if (choice.equals("0")) {
				continuar = false;
			}
		}
		
		
		
	}

	private static void cargarArchivos() throws FileNotFoundException, IOException {
		renta_carros = LoaderRentaCar.cargar_archivos("./data/automoviles.txt", "./data/clientes.txt", "./data/sedes.txt", "./data/usuarios.txt");
	}
	
	private static Usuario login() {
		boolean continuar = true;
		Usuario usuario = null;
		
		while (continuar) {
			System.out.println("\n1. Iniciar Sesión");
			System.out.println("2. Crear una cuenta\n");
			String eleccion = input("Elige una opción");
			if (eleccion.equals("1")) {
				String username = input("Usuario");
				String password = input("Contraseña");
				usuario = renta_carros.encontrarUsuario(username, password);
				if (usuario == null) {
					System.out.println("\nERROR: Usuario o contraseña incorrectos\n Si no tienes una cuenta creada, elige la opcion 2\n");
				}
				else {
					continuar = false;
				}
			}
			else if (eleccion.equals("2")) {
				usuario = crearUsuarioCliente();
				continuar = false;
			}
			else {
				System.out.println("Elige una opción valida");
			}
		}
		
		return usuario;
	}
	
	
	private static Usuario crearUsuarioCliente() {
		boolean user_valido = true;
		
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
		return nuevo_usuario;
	}

	private static void crearCliente(String nombre, String username) {
		String contacto = input("\nEscribe tu numero de contacto");
		String fecha_nacimiento = input("\nEscribe tu fecha de nacimiento(dd/mm/aaaa)");
		String nacionalidad = input("\nEscribe tu nacionalidad");
		String imagen_documento = input("\nIngresa la imagen tu documento de identidad en formato .png (nombrearchivo.png)");
		String numero_licencia = input("\nIngresa el numero de tu licencia");
		String pais_licencia = input("\nIngresa el pais de tu licencia");
		String fecha_vencimiento_pase = input("\nIngresa la fecha de vencimiento de tu licencia de conduccion(dd/mm/aaaa)");
		String imagen_pase = input("\nIngresa la imagen de tu licencia en formato .png(nombrearchivo.png)");
		String tipo_tarjeta = input("\nIngresa el tipo de tu tarjeta de credito(visa, mastercard,etc.)");
		
		String numero_tarjeta = input("\nIngresa el numero de tu tarjeta de credito");
		String fecha_vencimiento_tarjeta = input("\nIngresa la fecha de vencimiento de tu tarjeta de credito(mm/aaaa)");
		
		renta_carros.crearCliente(nombre, contacto, fecha_nacimiento, nacionalidad, imagen_documento, numero_licencia, pais_licencia,
									fecha_vencimiento_pase, imagen_pase, tipo_tarjeta, numero_tarjeta, fecha_vencimiento_tarjeta, username);
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
}
