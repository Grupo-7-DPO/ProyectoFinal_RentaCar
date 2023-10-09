package Consola;

import java.io.FileNotFoundException;
import java.io.IOException;

import Loader.LoaderRentaCar;
import Loader.RentaCar;

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
		boolean continuar = true;
		
	}
	
	private static void cargarArchivos() throws FileNotFoundException, IOException {
		renta_carros = LoaderRentaCar.cargar_archivos("./data/automoviles.txt", "./data/clientes.txt", "./data/sedes.txt", "./data/usuarios.txt");
	}
}
