package Model;

public class LicenciaConduccion {

	String numero_licencia;
	String pais_licencia;
	String fecha_vencimiento_pase;
	String imagen_pase;
	
	public LicenciaConduccion(String numero_licencia, String pais_licencia, String fecha_vencimiento_pase,
			String imagen_pase) {
		this.numero_licencia = numero_licencia;
		this.pais_licencia = pais_licencia;
		this.fecha_vencimiento_pase = fecha_vencimiento_pase;
		this.imagen_pase = imagen_pase;
	}

	public String getNumeroLicencia() {
		return this.numero_licencia;
	}
	
	public String getPaisLicencia() {
		return this.pais_licencia;
	}
	
	public String getFechaVencimiento() {
		return this.fecha_vencimiento_pase;
	}
	
	public String getImagen() {
		return this.imagen_pase;
	}
}
