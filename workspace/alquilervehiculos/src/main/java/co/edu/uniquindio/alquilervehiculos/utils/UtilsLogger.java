package co.edu.uniquindio.alquilervehiculos.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Clase que facilita el manejo e invocacion de los Loggers
 * @author ElJuancho
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UtilsLogger {
	
	private Logger LOGGER;
	
	private static UtilsLogger instancia;
	
	@Getter
	private static String archivo;

	/**
	 * Retorna una instancia de la clase {@link UtilsLogger} utilizando el patron Singleton
	 * @return instancia de la clase {@link UtilsLogger}
	 * @author ElJuancho
	 */
	public static UtilsLogger getInstancia() {
		if(instancia ==  null) {
			instancia = new UtilsLogger();
		}
		archivo = "/META-INF/properties/logger.properties";
		System.out.println(archivo);
		return instancia;
	}
	
	/**
	 * Carga las propiedades para el Logger desde el archivo indicado.
	 * @param nombreArchivo
	 * @author ElJuancho
	 */
	private void cargarPropiedades(String nombreArchivo) {
		try (InputStream config = UtilsLogger.class.getResourceAsStream(nombreArchivo)) {
			LogManager.getLogManager().readConfiguration(config);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Carga las propiedades del Logger y retorna el objeto listo para ser usado.
	 * @param clase es la la Clase a la cual se le va a implementar el Log
	 * @return instancia de Logger
	 * @author ElJuancho
	 */
	public Logger obtenerLogger(Class<? extends Object> clase) {
		cargarPropiedades(archivo);
		if(LOGGER == null) LOGGER = Logger.getLogger(clase.getName());
		return LOGGER;
	}

}
