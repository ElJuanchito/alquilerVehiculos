package co.edu.uniquindio.alquilervehiculos.model;

public class SerializacionPrueba {

	public static void main(String[] args) {
		var cliente = Cliente.builder().cedula("100").ciudad("Armenia").direccion("Mi casa").email("mi correo")
				.nombre("Juancho").telefono("mi telefono").build();
		//UtilsPersistence.getInstancia().guardar(cliente, Archivo.CLIENTES);

		//Cliente c2 = (Cliente) UtilsPersistence.getInstancia().leer(Archivo.CLIENTES);
	}
}
