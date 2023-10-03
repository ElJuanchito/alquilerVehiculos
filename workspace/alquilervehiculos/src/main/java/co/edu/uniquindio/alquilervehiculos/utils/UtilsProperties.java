package co.edu.uniquindio.alquilervehiculos.utils;

import java.util.Locale;
import java.util.ResourceBundle;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UtilsProperties {

	private static UtilsProperties instancia;
	
	private ResourceBundle recursos;

	private static String ruta;

	public static UtilsProperties getInstancia() {
		if (instancia == null) {
			instancia = new UtilsProperties();
		}
		ruta = "/META-INF/properties/textos";
		System.out.println(ruta);
		instancia.cambiarEspanol();
		return instancia;
	}

	public ResourceBundle obtenerRecursos() {
		return recursos;
	}
	
	public void cambiarIngles() {
		recursos = ResourceBundle.getBundle(ruta, new Locale("en"));
	}
	
	public void cambiarEspanol() {
		recursos = ResourceBundle.getBundle(ruta, new Locale("es"));
	}

}
