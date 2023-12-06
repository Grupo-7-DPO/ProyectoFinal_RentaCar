package ConsolaClientes;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;

import Consola.ConsolaRentaCar;
import Consola.ConsultarDisponibilidad;
import Consola.LoginPage;
import Loader.LoaderRentaCar;
import Loader.LoaderRentaCliente;
import Loader.RentaCar;
import Loader.RentaCliente;
import Model.Renta;
import Model.Usuario;

public class ConsolaClientesMain extends ConsolaRentaCar {
	
	
	public static void main(String[] args) {
		try {
			cargarArchivos();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		login();

	}
	
	private static void cargarArchivos() throws FileNotFoundException, IOException {
		renta_carros = LoaderRentaCliente.cargar_archivos("./data/automoviles.txt", "./data/clientes.txt", "./data/sedes.txt", "./data/usuarios.txt", "./data/reservas.txt", "./data/seguros.txt", "./data/categorias.txt");
		
	}
	
	public static void consultarDisponibilidad() {
		new ConsultarDisponibilidad(frame, usuario_actual, renta_carros);
	}

}
