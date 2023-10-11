package Loader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import Model.Vehiculo;
import Model.Cliente;
import Model.Empleado;
import Model.LicenciaConduccion;
import Model.Sede;
import Model.TarjetaCredito;
import Model.Usuario;

public class LoaderRentaCar {

	public static RentaCar cargar_archivos(String archivo_automoviles, String archivo_clientes, String archivo_sedes, String archivo_usuarios) throws IOException, FileNotFoundException{
		HashMap <String, List<Vehiculo>> tipos_vehiculos = new HashMap<>();
		List<Vehiculo> total_vehiculos = new ArrayList<>();
		List<Empleado> todos_empleados = new ArrayList<>();
		List<Usuario> usuarios = new ArrayList<>();
		HashMap <String, Sede> sedes = new HashMap<>();
		List<Cliente> clientes = new ArrayList<>();
		
		BufferedReader br_vehiculos = new BufferedReader(new FileReader(archivo_automoviles));
		br_vehiculos.readLine();
		String linea_vh = br_vehiculos.readLine();
		while(linea_vh != null)
		{
			String[] partes_vh = linea_vh.split(";");
			String id = partes_vh[0];
			String placa = partes_vh[1];
			String marca = partes_vh[2];
			String modelo = partes_vh[3];
			String tipo = partes_vh[4];
			String color = partes_vh[5];
			String transmision = partes_vh[6];
			String capacidad = partes_vh[7];
			String estado = partes_vh[8];
			
			Vehiculo vehiculo = new Vehiculo(id, placa, marca, modelo, tipo, color, transmision, capacidad, estado);
			total_vehiculos.add(vehiculo);
			linea_vh = br_vehiculos.readLine();
			
			List<Vehiculo> lista = new ArrayList<>();
			
			if(tipos_vehiculos.containsKey(tipo)) {
				lista = tipos_vehiculos.get(tipo);
				lista.add(vehiculo);
				tipos_vehiculos.put(tipo, lista);
			}
			else {
				lista.add(vehiculo);
				tipos_vehiculos.put(tipo, lista);
			}
			linea_vh = br_vehiculos.readLine();
		}
		
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
			
			Usuario usuario = new Usuario(nombre, tipo, user, password);
			usuarios.add(usuario);
			if(tipo.equals("AG") | tipo.equals("A") | tipo.equals("E")) {
				Empleado empleado = new Empleado(nombre, tipo);
				todos_empleados.add(empleado);
			}
			linea_us = br_empleados.readLine();
		}
		
		BufferedReader br_sedes= new BufferedReader(new FileReader(archivo_sedes));
		br_sedes.readLine();
		String linea_sd = br_sedes.readLine();
		while(linea_sd != null)
		{
			String[] partes_sd = linea_sd.split(";");
			String nombre = partes_sd[0];
			String hora_apertura = partes_sd[1];
			String hora_cierre = partes_sd[2];
			String dias_atencion = partes_sd[3];
			String ubicacion = partes_sd[4];
			
			Sede sede = new Sede(nombre, hora_apertura, hora_cierre, dias_atencion, ubicacion);
			sedes.put(nombre, sede);
			linea_sd = br_sedes.readLine();
		}
		
		BufferedReader br_clientes= new BufferedReader(new FileReader(archivo_clientes));
		br_clientes.readLine();
		String linea_cl = br_clientes.readLine();
		while(linea_cl != null)
		{
			String[] partes_cl = linea_cl.split(";");
			String nombre = partes_cl[0];
			String contacto = partes_cl[1];
			String fecha_nacimiento = partes_cl[2];
			String nacionalidad = partes_cl[3];
			String imagen_cedula = partes_cl[4];
			String numero_licencia = partes_cl[5];
			String pais_licencia = partes_cl[6];
			String fecha_vencimiento_pase = partes_cl[7];
			String imagen_pase = partes_cl[8];
			String tipo_tarjeta = partes_cl[9];
			String numero_tarjeta = partes_cl[10];
			String fecha_vencimiento_tarjeta = partes_cl[11];
			String username = partes_cl[12];
			
			LicenciaConduccion licencia = new LicenciaConduccion(numero_licencia, pais_licencia, fecha_vencimiento_pase, imagen_pase);
			TarjetaCredito tarjeta = new TarjetaCredito(tipo_tarjeta, numero_tarjeta, fecha_vencimiento_tarjeta);
			
			Cliente cliente = new Cliente(nombre, contacto, fecha_nacimiento, nacionalidad, imagen_cedula, licencia, tarjeta, username);
			clientes.add(cliente);
			linea_cl = br_clientes.readLine();
		}
		br_clientes.close();
		br_sedes.close();
		br_empleados.close();
		br_vehiculos.close();
		
		RentaCar renta_car = new RentaCar(tipos_vehiculos, total_vehiculos, todos_empleados, usuarios, sedes, clientes);
		return renta_car;
	}
}
