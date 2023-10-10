package Consola;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import Loader.LoaderRentaCar;
import Loader.RentaCar;
import Model.Cliente;
import Model.LicenciaConduccion;
import Model.TarjetaCredito;
import Model.Usuario;

public class ConsolaRentaCar {

	
	
	private static RentaCar renta_carros;
	
	public static void main(String[] args) {
		
		try {
	        cargarArchivos();
	    } catch (FileNotFoundException e) {
	        System.out.println("Error: no se encontró el archivo.");
	    } catch (IOException e) {
	        System.out.println("Error de I/O: " + e.getMessage());
	    }
		System.out.println("¡¡¡Bienvenido a nuestra renta de carros!!!");
		Usuario usuario_actual = login();
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

	private static void consolaAdminG() {
		// TODO Auto-generated method stub
		
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
				usuario = encontrarUsuario(username, password);
				if (usuario.equals(null)) {
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
			if (encontrarUsername(username)) {
				System.out.println("\nEste nombre de usuario ya está en uso\n");
			}
			else {
				user_valido = false;
			}
		}
		String password = input("Escribe tu contraseña");
		Usuario nuevo_usuario = new Usuario(nombre, tipo, username, password);
		renta_carros.getUsuarios().add(nuevo_usuario);
		crearCliente(nombre, username);
		try {
			modificarArchivoUsuario(nuevo_usuario);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("\n¡¡Cuenta creada correctamente!!\n");
		return nuevo_usuario;
	}

	private static void crearCliente(String nombre, String username) {
		String contacto = input("\nEscribe tu numero de contacto");
		String fecha_nacimiento = input("\nEscribe tu fecha de nacimiento");
		String nacionalidad = input("\nEscribe tu nacionalidad");
		String imagen_documento = input("\nIngresa la imagen tu documento de identidad en formato .png (nombrearchivo.png)");
		String numero_licencia = input("\nIngresa el numero de tu licencia");
		String pais_licencia = input("\nIngresa el pais de tu licencia");
		String fecha_vencimiento_pase = input("\nIngresa la fecha de vencimiento de tu licencia de conduccion");
		String imagen_pase = input("\nIngresa la imagen de tu licencia en formato .png(nombrearchivo.png)");
		String tipo_tarjeta = input("\nIngresa el tipo de tu tarjeta de credito(visa, mastercard,etc.)");
		
		String numero_tarjeta = input("\nIngrsa el numero de tu tarjeta de credito");
		String fecha_vencimiento_tarjeta = input("\nIngresa la fecha de vencimiento de tu tarjeta de credito");
		
		TarjetaCredito tarjeta = new TarjetaCredito(tipo_tarjeta, numero_tarjeta, fecha_vencimiento_tarjeta);
		LicenciaConduccion licencia = new LicenciaConduccion(numero_licencia, pais_licencia, fecha_vencimiento_pase, imagen_pase);
		
		Cliente nuevo_cliente = new Cliente(nombre, contacto, fecha_nacimiento, nacionalidad, imagen_documento, licencia, tarjeta, username);
		renta_carros.getClientes().add(nuevo_cliente);
		
		try {
			modificarArchivoClientes(nuevo_cliente);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void modificarArchivoClientes(Cliente nuevo_cliente) throws IOException {
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

	private static void modificarArchivoUsuario(Usuario nuevo_usuario) throws IOException { 
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

	private static boolean encontrarUsername(String username_buscado) {
		//esta funcion se encarga de encontrar si ya existe el username que entra por parametro
		boolean se_encontro = false;
		List<Usuario> lista = renta_carros.getUsuarios();
		
		for (Usuario usuario: lista) {
			if(usuario.getUser().equals(username_buscado)) {
				se_encontro = true;
			}
		}
		
		return se_encontro;
	}
	
	private static Usuario encontrarUsuario(String username, String password) {
		// esto busca en la lista general de usuarios y encuentra si un usuario existe o no. Si no retorna null y si si retorna el usuario
		List<Usuario> lista = renta_carros.getUsuarios();
		Usuario usuario_buscado = null;
		
		for(Usuario usuario: lista) {
			if (usuario.getUser().equals(username) && usuario.getPassword().equals(password)) {
				usuario_buscado = usuario;
			}
		}
		return usuario_buscado;
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
