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
import Model.Categoria;
import Model.Cliente;
import Model.LicenciaConduccion;
import Model.Reserva;
import Model.Seguro;
import Model.TarjetaCredito;
import Model.Usuario;

public class ConsolaRentaCar {

	
	
	private static RentaCar renta_carros;
	private static Usuario usuario_actual;
	
	public static void main(String[] args) {
		
		try {
	        cargarArchivos();
	    } catch (FileNotFoundException e) {
	        System.out.println("Error: no se encontró el archivo.");
	    } catch (IOException e) {
	        System.out.println("Error de I/O: " + e.getMessage());
	    }
		System.out.println("\n¡¡¡Bienvenido a nuestra renta de carros!!!");
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
		boolean continuar = true;
		
		while(continuar) {
			System.out.println("0. Cerrar Sesion");
			System.out.println("1. Crear nueva reserva");
			System.out.println("2. Verificar tu reserva");
			String opcion = input("\nSeleccione la opcion que desea");
			
			if (opcion.equals("0")){
				continuar = false;
				main(null);
			}
			else if(opcion.equals("1")) {
				crearReserva();
			}
			else if (opcion.equals("2")) {
				consultarReserva();
			}
			
		}
		
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

	
	private static void crearReserva() {
		String id = renta_carros.crearId();
		String usuario = usuario_actual.getUser();
		
		
		List<Categoria> lista_categorias = renta_carros.getCategorias();
		int numero = 1;
		for (Categoria categoria_iteracion : lista_categorias) {
			System.out.println(numero + ". " + categoria_iteracion.getNombre() + "\n");
			numero++;
		}
		
		int opcion_elegida_tipo = Integer.parseInt(input("\nElige la opción que deseas"))-1;
		Categoria tipo = lista_categorias.get(opcion_elegida_tipo);
		
		System.out.println("\n¿En que sede deseas recoger tu carro?");
		List<String> lista_nombre_sedes = renta_carros.getNombreSedes();
		numero = 1;
		for (String nombre_sede : lista_nombre_sedes) {
			System.out.println(numero + ". " + nombre_sede + "\n");
			numero++;
		}
		int opcion_elegida_sede_rec = Integer.parseInt(input("\nEliga la opción que deseas"))-1;
		String sede_recogida = lista_nombre_sedes.get(opcion_elegida_sede_rec);
		
		String fecha_recogida = input("Ingrese la fecha en que va a recoger el vehiculo(dd/mm/aaaa)");
		String hora_recogida = input("Ingrese la hora a la que va a recoger el vehiculo(hh:mm)");
		
		System.out.println("\n¿En que sede deseas entregar el vehiculo?");
		numero = 1;
		for (String nombre_sede : lista_nombre_sedes) {
			System.out.println(numero + ". " + nombre_sede + "\n");
			numero++;
		}
		int opcion_elegida_sede_ent = Integer.parseInt(input("\nEliga la opción que deseas"))-1;
		String sede_entrega= lista_nombre_sedes.get(opcion_elegida_sede_ent);
		
		
		String fecha_entrega = input("Ingrese la fecha en que va a entregar el vehiculo(dd/mm/aaaa)");
		String hora_entrega = input("Ingrese la hora a la que va a entregar el vehiculo(hh:mm)");
		
		System.out.println("\n¿Deseas agregar algun seguro?");
		System.out.println("\n1. Si\n2.No");
		String elegir_seguro = input("\nElige alguna opcion");
		Seguro seguro = null;
		if(elegir_seguro.equals("1") | elegir_seguro.equals("Si")) {
			List<Seguro> lista_seguros = renta_carros.getSeguros();
			numero = 1;
			for(Seguro seguro_iteracion : lista_seguros) {
				System.out.println(numero + ". " + seguro_iteracion.getNombre());
				numero++;
			}
			int opcion_elegida_seguro = Integer.parseInt(input("\nEliga la opción que deseas"))-1;
			seguro = lista_seguros.get(opcion_elegida_seguro);
		}
		long precio = renta_carros.calcularTarifaReserva(fecha_recogida, fecha_entrega, tipo, seguro);
		
		System.out.println("Tu reserva tendria un valor de " + precio);
		
		
	}
	
	private static void consultarReserva() {
		String id = input("\nEscribe el id de tu reserva");
		Reserva reserva_buscada = renta_carros.encontrarReserva(id);
		
		
		if(reserva_buscada == null) {
			System.out.println("\nLo sentimos, tu reserva con id " + id + " no fue encontrada\n");
		}
		else {
			Usuario usuario_reserva = renta_carros.encontrarUsuarioConUsername(reserva_buscada.getUsername());
			System.out.println("\nLa reserva con id " + id + " a nombre de " + usuario_reserva.getNombre() + " tiene asignada\nun vehiculo tipo"
								+ reserva_buscada.getTipo() + " para ser recogido en la " + reserva_buscada.getSedeRecogida() + "\nel dia " + reserva_buscada.getDiaRecogida()
								+ " a la hora " + reserva_buscada.getHoraRecogida() + "\ny ser entregado el dia " + reserva_buscada.getDiaEntrega() 
								+ " a la hora " + reserva_buscada.getHoraEntrega() + "\n");
		}
		
	}
	
	private static void cargarArchivos() throws FileNotFoundException, IOException {
		renta_carros = LoaderRentaCar.cargar_archivos("./data/automoviles.txt", "./data/clientes.txt", "./data/sedes.txt", "./data/usuarios.txt", "./data/reservas.txt", "./data/seguros.txt", "./data/categorias.txt");
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
