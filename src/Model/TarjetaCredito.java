package Model;

public class TarjetaCredito {

	String tipo_tarjeta;
	String numero_tarjeta;
	String fecha_vencimiento_tarjeta;
	
	public TarjetaCredito(String tipo_tarjeta, String numero_tarjeta, String fecha_vencimiento_tarjeta) {
		this.tipo_tarjeta = tipo_tarjeta;
		this.numero_tarjeta = numero_tarjeta;
		this.fecha_vencimiento_tarjeta = fecha_vencimiento_tarjeta;
	}

	public String getTipo() {
		return this.tipo_tarjeta;
	}
	
	public String getNumero() {
		return this.numero_tarjeta;
	}
	
	public String getFechaVencimiento() {
		return this.fecha_vencimiento_tarjeta;
	}
}
