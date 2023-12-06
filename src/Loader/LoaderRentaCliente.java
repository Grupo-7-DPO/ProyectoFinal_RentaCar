package Loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Model.Usuario;

public class LoaderRentaCliente extends LoaderRentaCar{

	
	public static RentaCliente cargar_archivos(String archivo_automoviles, String archivo_clientes, String archivo_sedes, String archivo_usuarios, String archivo_reservas, String archivo_seguros, String archivo_categorias) throws IOException, FileNotFoundException{
		cargarVehiculos(archivo_automoviles);
		cargarUsuarios(archivo_usuarios);
		cargarSedes(archivo_sedes);
		cargarClientes(archivo_clientes);
		cargarSeguros(archivo_seguros);
		cargarCategorias(archivo_categorias);
		cargarReservas(archivo_reservas);
		
		RentaCliente rentaCliente = new RentaCliente(tipos_vehiculos, total_vehiculos, usuarios, sedes, clientes, reservas, nombre_sedes, seguros, categorias);
		return rentaCliente;
	}
	
	protected static void cargarUsuarios(String archivo_usuarios) throws IOException {
		BufferedReader br_empleados = new BufferedReader(new FileReader(archivo_usuarios));
		br_empleados.readLine();
		String linea_us = br_empleados.readLine();
		while(linea_us != null)
		{
			String[] partes_us = linea_us.split(";");
			String nombre = partes_us[0];
			String tipo = partes_us[1];
			String user = partes_us[2];
			String password = partes_us[3];
			if(tipo.equals("C")) {
				Usuario usuario = new Usuario(nombre, tipo, user, password);
				usuarios.add(usuario);
			}
			
			linea_us = br_empleados.readLine();
		}
		br_empleados.close();
		
	}
}
