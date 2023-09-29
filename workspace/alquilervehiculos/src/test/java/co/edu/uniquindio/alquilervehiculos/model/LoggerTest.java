package co.edu.uniquindio.alquilervehiculos.model;

import java.util.logging.Logger;

import co.edu.uniquindio.alquilervehiculos.utils.UtilsLogger;

public class LoggerTest {
	private static final Logger LOGGER = UtilsLogger.getInstancia().obtenerLogger(LoggerTest.class);

	public static void main(String[] args) {

		LOGGER.info("Esto es una prueba de guardado");
	}
}
