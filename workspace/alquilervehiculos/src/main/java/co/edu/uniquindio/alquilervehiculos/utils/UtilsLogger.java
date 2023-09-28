package co.edu.uniquindio.alquilervehiculos.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

public class UtilsLogger {

	public static void cargarPropiedades(String nombreArchivo) {
		try (FileInputStream config = new FileInputStream(nombreArchivo+".properties")) {
			LogManager.getLogManager().readConfiguration(config);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}

}
