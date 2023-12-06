package Model;

public interface Renta {

    Usuario encontrarUsuarioConNombre(String nombre);
    Seguro encontrarSeguro(String nombre_seguro);
    Categoria encontrarCategoria(String nombre_categoria);
    Usuario encontrarUsuario(String username, String password);
    Usuario encontrarUsuarioConUsername(String username);
    Reserva encontrarReserva(String id_reserva);
    boolean encontrarUsername(String username_buscado);
    Empleado encontrarEmpleado(String username);
    Vehiculo encontrarVehiculo(String placa);
    void crearCliente(String nombre, String contacto, String fecha_nacimiento, String nacionalidad, String imagen_documento, String numero_licencia, String pais_licencia, String fecha_vencimiento_pase, String imagen_pase, String tipo_tarjeta, String numero_tarjeta, String fecha_vencimiento_tarjeta, String username);
    long calcularTarifaReserva(String fecha_recogida, String fecha_entrega, Categoria tipo_carro, Seguro seguro);
    long calcularDiferenciaDias(String fecha1Str, String fecha2Str);
    void crearUsuarioCliente(String nombre, String tipo, String username, String password);
    boolean crearUsuarioAdminEmpleado(String nombre, String tipo, String username, String password, String sede);
    void crearReserva(String id, String usuario, Categoria tipo, String sede_recogida, String fecha_recogida, String hora_recogida, String sede_entrega, String fecha_entrega, String hora_entrega, String pago, Seguro seguro);
    String crearId();
}
