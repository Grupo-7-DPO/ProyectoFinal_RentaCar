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
import Model.Reserva;
import Model.Seguro;
import Model.TarjetaCredito;
import Model.Usuario;
import Model.Vehiculo;

public class ConsolaRentaCar {

	
	
	
	
	
	private static RentaCar renta_carros;
	private static Usuario usuario_actual;
	private static JFrame frame = new JFrame();
	
	public static void main(String[] args) throws IOException {
		
		try {
	        cargarArchivos();
	    } catch (FileNotFoundException e) {
	        System.out.println("Error: no se encontró el archivo.");
	    } catch (IOException e) {
	        System.out.println("Error de I/O: " + e.getMessage());
	    }
		login();
		
//		if (usuario_actual.getTipo().equals("AG")) {
//			consolaAdminG();
//		}
//		else if (usuario_actual.getTipo().equals("A")) {
//			consolaAdmin();
//		}
//		else if (usuario_actual.getTipo().equals("E")) {
//			consolaEmpleado();
//		}
//		else if (usuario_actual.getTipo().equals("C")) {
//			consolaCliente();
//		}
//		
		
	}
	
	protected static void consolaCliente(){
//		boolean continuar = true;
//		while(continuar) {  
//			
//			System.out.println("\n0. Cerrar Sesion");
//			System.out.println("1. Crear nueva reserva");
//			System.out.println("2. Verificar tu reserva");
//			String opcion = input("\nSeleccione la opcion que desea");
//			
//			if (opcion.equals("0")){
//				continuar = false;
//				
//			}
//			else if(opcion.equals("1")) {
//				crearReserva(false);
//			}
//			else if (opcion.equals("2")) {
//				consultarReserva();
//			}
//			
//		}
//		
		ConsolaCliente consolaCliente = new ConsolaCliente(frame, usuario_actual, renta_carros);
		
	}

	protected static void consolaEmpleado(){
		ConsolaEmpleado consolaEmpleado = new ConsolaEmpleado(frame, usuario_actual, renta_carros);
		
		/*
		boolean continuar = true;
		
		while(continuar) {
			System.out.println("\n0. Cerrar Sesion");
			System.out.println("1. Nueva reserva"); //Cuando un cliente no tiene reserva y llega al lugar por un carro
			System.out.println("2. Nueva reserva especial"); //Para llevar el carro de una sede a otra
			System.out.println("3. Comenzar reserva");
			System.out.println("4. Recibir carro reservado");//Recibir un auto que fue reservado y ponerlo en limpieza
			System.out.println("5. Recibir un auto de limpieza");
			System.out.println("6. Poner un auto en mantenimiento");
			String opcion = input("\nSelecciona la opcion que desea");
			
			if (opcion.equals("0")){
				continuar = false;
				main(null);
			}
			else if(opcion.equals("1")) {
				crearReserva(false);
			}
			else if (opcion.equals("2")) {
				crearReserva(true);
			}
			else if (opcion.equals("3")) {
				System.out.println("\n¿En que sede te encuentras?");
				List<String> lista_nombre_sedes = renta_carros.getNombreSedes();
				int numero = 1;
				for (String nombre_sede : lista_nombre_sedes) {
					System.out.println(numero + ". " + nombre_sede);
					numero++;
				}
				int opcion_elegida_sede = Integer.parseInt(input("\nEliga la opción que deseas"))-1;
				String sede = lista_nombre_sedes.get(opcion_elegida_sede);
				
				comenzarReserva(sede);
			}
			else if (opcion.equals("4")) {
				recibirCarroReservado();
			}
			else if(opcion.equals("5")) {
				String placa = input("Escribe la placa del carro");
				cambiarEstadoCarro("disponible", placa);
			}
			else if(opcion.equals("6")) {
				String placa = input("Escribe la placa del carro");
				cambiarEstadoCarro("mantenimiento", placa);
			}
		}
		*/

	}

	
	static void consolaAdmin(){
		// TODO Auto-generated method stub
		/*boolean continuar = true;
		while (continuar) {
			System.out.println("\n1. Revisar carros del inventario general");
			System.out.println("2. Revisar carros disponibles");
			System.out.println("3. Revisar carros disponibles en una sede");
			System.out.println("4. Nueva reserva"); //Cuando un cliente no tiene reserva y llega al lugar por un carro
			System.out.println("5. Nueva reserva especial"); //Para llevar el carro de una sede a otra
			System.out.println("6. Comenzar reserva");
			System.out.println("7. Recibir carro reservado");//Recibir un auto que fue reservado y ponerlo en limpieza
			System.out.println("8. Recibir un auto de limpieza");
			System.out.println("9. Poner un auto en mantenimiento");
			System.out.println("10. Crear empleado");
			System.out.println("11. Eliminar empleado");
			System.out.println("0. Log Out");
			String choice = input("Elige una opcion");
			
			
			
			if (choice.equals("0")) {
				continuar = false;
				main(null);
			}
			
			else if (choice.equals("1")) {
				List<Vehiculo>inv = renta_carros.getInventarioGeneral();
				System.out.println("PLACA  TIPO  CAPACIDAD  MODELO  MARCA  ESTADO");
				for (Vehiculo car : inv)
					System.out.println(car.getCar());
			}
			
			else if (choice.equals("2")) {
				List<Vehiculo>inv = renta_carros.getInventarioDisponible();
				System.out.println("PLACA  TIPO  CAPACIDAD  MODELO  MARCA  ESTADO");
				for (Vehiculo car : inv)
					System.out.println(car.getCar());
			}
			
			else if (choice.equals("3")) {
				String name = input("Ingresa el nombre de la sede");
				List<Vehiculo>inv = renta_carros.getInventarioDispSede(name);
				if (inv.size()>1) {
					System.out.println("PLACA  TIPO  CAPACIDAD  MODELO  MARCA  ESTADO");
					for (Vehiculo car : inv)
						System.out.println(car.getCar());
				}
				else { System.out.println("No se econtro la sede");}
				
			}
			
			else if(choice.equals("4")) {
				crearReserva(false);
			}
			else if (choice.equals("5")) {
				crearReserva(true);
			}
			else if (choice.equals("6")) {
				System.out.println("\n¿En que sede te encuentras?");
				List<String> lista_nombre_sedes = renta_carros.getNombreSedes();
				int numero = 1;
				for (String nombre_sede : lista_nombre_sedes) {
					System.out.println(numero + ". " + nombre_sede);
					numero++;
				}
				int opcion_elegida_sede = Integer.parseInt(input("\nEliga la opción que deseas"))-1;
				String sede = lista_nombre_sedes.get(opcion_elegida_sede);
				
				comenzarReserva(sede);
			}
			else if (choice.equals("7")) {
				recibirCarroReservado();
			}
			else if(choice.equals("8")) {
				String placa = input("Escribe la placa del carro");
				cambiarEstadoCarro("disponible", placa);
			}
			else if(choice.equals("9")) {
				String placa = input("Escribe la placa del carro");
				cambiarEstadoCarro("mantenimiento", placa);
			}
			else if (choice.equals("10")) {
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
				String sede = input("Escribe la sede a la que va a pertenecer");
				renta_carros.crearUsuarioAdminEmpleado(nombre, tipo, username, password, sede);
				System.out.println("\n¡¡Cuenta creada correctamente!!\n");
			}
			
			else if (choice.equals("11")) {
				String user = input("\nEscribe el usuario del empleado");
				String pass = input("Escribe la contraseña");
				boolean resp = renta_carros.elimEmpleado(user,pass); 
				if (resp == true) {
					System.out.println("El empleado fue elimnado");
				}
				else {System.out.println("No se pudo elimnar el empleado");}
			}
			else {
				System.out.println("Ingrese una opcion valida");
			}
		}
		*/
	}

	static void consolaAdminG(){
		// TODO Auto-generated method stub
		ConsolaAdminG consolaAdminG = new ConsolaAdminG(frame, usuario_actual, renta_carros);
		/*
		boolean continuar = true;
		while (continuar) {
			System.out.println("\n1. Revisar carros del inventario general");
			System.out.println("2. Revisar carros disponibles");
			System.out.println("3. Revisar carros disponibles en una sede");
			System.out.println("4. Nueva reserva"); //Cuando un cliente no tiene reserva y llega al lugar por un carro
			System.out.println("5. Nueva reserva especial"); //Para llevar el carro de una sede a otra
			System.out.println("6. Comenzar reserva");
			System.out.println("7. Recibir carro reservado");//Recibir un auto que fue reservado y ponerlo en limpieza
			System.out.println("8. Recibir un auto de limpieza");
			System.out.println("9. Poner un auto en mantenimiento");
			System.out.println("10. Añadir carro al inventario");
			System.out.println("11. Elminar carro del inventario");
			System.out.println("12. Crear seguro");
			System.out.println("13. Crear empleado");
			System.out.println("14. Eliminar empleado");
			System.out.println("15. Cambiar estado automovil");
			System.out.println("0. Log Out");
			String choice = input("Elige una opcion");
			
			if (choice.equals("0")) {
				continuar = false;
				main(null);
			}
			
			else if (choice.equals("1")) {
				List<Vehiculo>inv = renta_carros.getInventarioGeneral();
				System.out.println("PLACA  TIPO  CAPACIDAD  MODELO  MARCA  ESTADO");
				for (Vehiculo car : inv)
					System.out.println(car.getCar());
			}
			
			else if (choice.equals("2")) {
				List<Vehiculo>inv = renta_carros.getInventarioDisponible();
				System.out.println("PLACA  TIPO  CAPACIDAD  MODELO  MARCA  ESTADO");
				for (Vehiculo car : inv)
					System.out.println(car.getCar());
			}
			
			else if (choice.equals("3")) {
				String name = input("Ingresa el nombre de la sede");
				List<Vehiculo>inv = renta_carros.getInventarioDispSede(name);
				if (inv.size()>1) {
					System.out.println("PLACA  TIPO  CAPACIDAD  MODELO  MARCA  ESTADO");
					for (Vehiculo car : inv)
						System.out.println(car.getCar());
				}
				else { System.out.println("No se econtro la sede");}
				
			}
			
			else if(choice.equals("4")) {
				crearReserva(false);
			}
			else if (choice.equals("5")) {
				crearReserva(true);
			}
			else if (choice.equals("6")) {
				System.out.println("\n¿En que sede te encuentras?");
				List<String> lista_nombre_sedes = renta_carros.getNombreSedes();
				int numero = 1;
				for (String nombre_sede : lista_nombre_sedes) {
					System.out.println(numero + ". " + nombre_sede);
					numero++;
				}
				int opcion_elegida_sede = Integer.parseInt(input("\nEliga la opción que deseas"))-1;
				String sede = lista_nombre_sedes.get(opcion_elegida_sede);
				
				comenzarReserva(sede);
			}
			else if (choice.equals("7")) {
				recibirCarroReservado();
			}
			else if(choice.equals("8")) {
				String placa = input("Escribe la placa del carro");
				cambiarEstadoCarro("disponible", placa);
			}
			else if(choice.equals("9")) {
				String placa = input("Escribe la placa del carro");
				cambiarEstadoCarro("mantenimiento", placa);
			}
			
			else if (choice.equals("10"))
			{
				
				xString placa = input("Escribe la placa");
				xString marca = input("Escibe la marca");
				xString modelo = input("Escribe el modelo");
				xString tipo = input("Escribe el tipo");
				xString color = input("Escribe el color");
				xString trans = input("Escribe la transmision");
				xString capacidad = input("Escribe la capacidad");
				xString estado = input("Escribe el estado");
				xString sede = input("Escriba la sede");
				
				renta_carros.crearVehiculo(placa,marca,modelo,tipo,color,trans,capacidad,estado,sede);
				
			}
			
			
			else if (choice.equals("11"))
			{
				String placa = input("Escribe la placa");
				renta_carros.eliminarVehiculo(placa);
				
			}
			else if (choice.equals("12"))
			{
				String nombre = input("Escribe el nombre del seguro");
				int precio = Integer.parseInt(input("Escribe el precio del seguro"));
				renta_carros.crearSeguro(nombre, precio);
				
			}
			else if (choice.equals("13")) {
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
				String sede = input("Escribe la sede a la que va a pertenecer");
				renta_carros.crearUsuarioAdminEmpleado(nombre, tipo, username, password, sede);
				System.out.println("\n¡¡Cuenta creada correctamente!!\n");
			}
			
			else if (choice.equals("14")) {
				String user = input("\nEscribe el usuario del empleado");
				String pass = input("Escribe la contraseña");
				boolean resp = renta_carros.elimEmpleado(user,pass); 
				if (resp == true) {
					System.out.println("El empleado fue elimnado");
				}
				else {System.out.println("No se pudo elimnar el empleado");}
			}
			
			else if(choice.equals("15")) {
				String placa = input("Ingrese la placa");
				String estado = input("Ingrese el nuevo estado");
				cambiarEstadoCarro(estado, placa);
			}
			
			else {
				System.out.println("Ingrese una opcion valida");
			}
			
			
		}
		*/
		
		
	}

	
	protected static void crearReserva(boolean especial) {
		CrearReserva crearReserva = new CrearReserva(renta_carros, usuario_actual, frame, especial);
		/*
		String id = renta_carros.crearId();
		String usuario;
		if (usuario_actual.getTipo().equals("C")) {
			usuario = usuario_actual.getUser();
		}
		else {
			usuario = input("¿A nombre de quien es la reserva(username)?");
		}
			
		System.out.println("\nEstas son las opciones de categorias de vehiculos que tenemos por el momento\n");
		List<Categoria> lista_categorias = renta_carros.getCategorias();
		int numero = 1;
		for (Categoria categoria_iteracion : lista_categorias) {
			System.out.println(numero + ". " + categoria_iteracion.getNombre());
			numero++;
		}
		
		int opcion_elegida_tipo = Integer.parseInt(input("\nElige la opción que deseas"))-1;
		Categoria tipo = lista_categorias.get(opcion_elegida_tipo);
		
		System.out.println("\n¿En que sede deseas recoger tu carro?");
		List<String> lista_nombre_sedes = renta_carros.getNombreSedes();
		numero = 1;
		for (String nombre_sede : lista_nombre_sedes) {
			System.out.println(numero + ". " + nombre_sede);
			numero++;
		}
		int opcion_elegida_sede_rec = Integer.parseInt(input("\nEliga la opción que deseas"))-1;
		String sede_recogida = lista_nombre_sedes.get(opcion_elegida_sede_rec);
		
		String fecha_recogida = input("Ingrese la fecha en que va a recoger el vehiculo(dd/mm/aaaa)");
		String hora_recogida = input("Ingrese la hora a la que va a recoger el vehiculo(hh:mm)");
		
		System.out.println("\n¿En que sede deseas entregar el vehiculo?");
		numero = 1;
		for (String nombre_sede : lista_nombre_sedes) {
			System.out.println(numero + ". " + nombre_sede);
			numero++;
		}
		int opcion_elegida_sede_ent = Integer.parseInt(input("\nEliga la opción que deseas"))-1;
		String sede_entrega= lista_nombre_sedes.get(opcion_elegida_sede_ent);
		
		
		String fecha_entrega = input("Ingrese la fecha en que va a entregar el vehiculo(dd/mm/aaaa)");
		String hora_entrega = input("Ingrese la hora a la que va a entregar el vehiculo(hh:mm)");
		
		Seguro seguro = null;
		System.out.println("\n¿Deseas agregar algun seguro?");
		System.out.println("\n1. Si\n2. No");
		String elegir_seguro = input("\nElige alguna opcion");
		if(elegir_seguro.equals("1") | elegir_seguro.equals("Si")) {
			System.out.println("\nEstos son los seguros que tenemos disponibles por el momento\n");
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
		if(especial) {
			System.out.println("\n¡Reserva especial creada correctamente con id " + id + "!");
			System.out.println("\nTransladar el carro de la " + sede_recogida + " a la " + sede_entrega);
		}
		else {
			System.out.println("\n¡Reserva creada correctamente con id " + id + "!");
			System.out.println("\nEsta tendria un valor total de " + precio + "$");
		}
		
		if (usuario_actual.getTipo().equals("C")) {
			System.out.println("\nEl valor que debes cancelar a continuacion es de " + Math.round(precio*0.3 * 100.0) / 100.0 + "$");
			System.out.println("\nCuando reclames tu reserva deberás cancelar el 70% restante");
		}
		
		renta_carros.crearReserva(id, usuario, tipo, sede_recogida, fecha_recogida, hora_recogida, sede_entrega, fecha_entrega, hora_entrega, "pagado", seguro);
		*/
		
	}
	
	protected static void comenzarReserva(String sede_empleado) {
		ComenzarReserva comenzarReserva = new ComenzarReserva(sede_empleado,frame, renta_carros, usuario_actual);
		/*
		String id_reserva = input("Escribe el id de la reserva");
		String nombre = input("Escribe a nombre de quien está la reserva");
		
		Usuario usuario_reserva = renta_carros.encontrarUsuarioConNombre(nombre);
		Reserva reserva = renta_carros.encontrarReserva(id_reserva);
		
		if (reserva != null && usuario_reserva != null) {
			if (usuario_reserva.getUser().equals(reserva.getUsername()) && reserva.getSedeRecogida().equals(sede_empleado)) {
				String sede = reserva.getSedeRecogida();
				Categoria tipo = reserva.getTipo();
				long precio = renta_carros.calcularTarifaReserva(reserva.getDiaRecogida(), reserva.getDiaEntrega() , reserva.getTipo(), reserva.getSeguro());
				
				System.out.println("\nDebe pagar el saldo restante de " + Math.round(precio*0.7 * 100.0) / 100.0 + "$");
				
				Vehiculo carro_asignado = renta_carros.comenzarReserva(sede, tipo, reserva);
				
				if (carro_asignado != null) {
					System.out.println("Carro asignado: "+ carro_asignado.getMarca() + " " + carro_asignado.getModelo());
					System.out.println("Placa: " + carro_asignado.getPlaca());
					System.out.println("Color: " + carro_asignado.getColor());
					System.out.println("Tipo: " + carro_asignado.getTipo());
					cambiarEstadoCarro("reservado", carro_asignado.getPlaca());
				}
				else {
					System.out.println("\nLo sentimos, no tenemos carros disponibles por el momento");
				}
				
			}
			else if (!reserva.getSedeRecogida().equals(sede_empleado)) {
				System.out.println("El carro debe ser recogido en la sede " + reserva.getSedeRecogida());
				System.out.println("No en la sede " + sede_empleado);
			}
		}
		else {
			System.out.println("ERROR: No existe una reserva con id " + id_reserva + " a nombre de " + nombre);
		}
		*/
		
	}
	
	
	protected static void recibirCarroReservado() {
		RecibirCarroReservado recibirCarro = new RecibirCarroReservado(frame, renta_carros,usuario_actual);
		
		/*
		String id = input("Escriba el id de la reserva");
		Reserva reserva = renta_carros.encontrarReserva(id);
		String placa = reserva.getCarro().getPlaca();
		renta_carros.terminarReserva(reserva);
		
		System.out.println("\nReserva cerrada correctamente");
		System.out.println("Carro " + placa + " puesto en limpieza correctamente");
		*/
	}
	
	protected static void cambiarEstadoCarro() {
		
		CambiarEstadoCarro cambiarEstado = new CambiarEstadoCarro(frame, renta_carros, usuario_actual);
		
		/*
		renta_carros.cambiarEstadoCarro(estado, placa);
		System.out.println("\nCarro " + placa + " puesto en " + estado + " correctamente");
		*/
	}
	
	protected static void consultarReserva() {
		ConsultarReserva consultarReserva = new ConsultarReserva(frame, usuario_actual, renta_carros);
//		String id = input("\nEscribe el id de tu reserva");
//		Reserva reserva_buscada = renta_carros.encontrarReserva(id);
//		
//		
//		if(reserva_buscada == null) {
//			System.out.println("\nLo sentimos, tu reserva con id " + id + " no fue encontrada\n");
//		}
//		else {
//			Usuario usuario_reserva = renta_carros.encontrarUsuarioConUsername(reserva_buscada.getUsername());
//			
//			System.out.println("\nReserva a nombre de " + usuario_reserva.getNombre() + " con id " + id + "\n");
//			System.out.println("Tipo de Carro: " + reserva_buscada.getTipo().getNombre());
//			if (reserva_buscada.getSeguro() != null) {
//				System.out.println("Seguro: " + reserva_buscada.getSeguro().getNombre() + "\n");
//			}
//			else {
//				System.out.println("Seguro: ninguno");
//			}
//			System.out.println("Sede para ser recogido: " + reserva_buscada.getSedeRecogida());
//			System.out.println("Fecha: " + reserva_buscada.getDiaRecogida());
//			System.out.println("Hora: " + reserva_buscada.getHoraRecogida());
//			System.out.println("\nSede para ser entregado: " + reserva_buscada.getSedeEntrega());
//			System.out.println("Fecha: " + reserva_buscada.getDiaEntrega());
//			System.out.println("Hora: " + reserva_buscada.getHoraEntrega() + "\n");
//			if (reserva_buscada.getCarro() != null) {
//				System.out.println("Carro asignado: " + reserva_buscada.getCarro().getMarca() + " " + reserva_buscada.getCarro().getModelo());
//				System.out.println("Placa: " + reserva_buscada.getCarro().getPlaca());
//			}
//			else {
//				System.out.println("Carro asignado: ninguno");
//			}
//			
//		}
		
	}
	
	private static void cargarArchivos() throws FileNotFoundException, IOException {
		renta_carros = LoaderRentaCar.cargar_archivos("./data/automoviles.txt", "./data/clientes.txt", "./data/sedes.txt", "./data/usuarios.txt", "./data/reservas.txt", "./data/seguros.txt", "./data/categorias.txt", "./data/historial_reservas.csv");
	}
	
	protected static void login() {
		LoginPage loginPage = new LoginPage(renta_carros, frame);
	}
	
	protected static void verOcupacion() {
		ConsolaOcupacion ocupacion = new ConsolaOcupacion(renta_carros, frame, usuario_actual);
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
