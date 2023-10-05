package co.edu.uniquindio.alquilervehiculos.utils;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.beans.property.SimpleObjectProperty;

public class UtilsProperties {
	
	private final SimpleObjectProperty<ResourceBundle> bdProperty = new SimpleObjectProperty<>();

	private static UtilsProperties instancia;
	
	private ResourceBundle recursos;

	private final static String RUTA = "/META-INF/properties/textos";
	
	private UtilsProperties() {
		bdProperty.setValue(ResourceBundle.getBundle(RUTA));
	}

	public static UtilsProperties getInstancia() {
		if (instancia == null) {
			instancia = new UtilsProperties();
		}
		return instancia;
	}

	public ResourceBundle obtenerRecursos() {
		return recursos;
	}
	
	public void setLanguage(Locale locale) {
		if (!locale.equals(bdProperty.getValue().getLocale()))
			bdProperty.setValue(ResourceBundle.getBundle(RUTA, locale));
	}

	public void setLanguage(String localeString) {
		setLanguage(new Locale.Builder().setLanguage(localeString).build());
	}

	public void addListener(Consumer<ResourceBundle> listener) {
		listener.accept(bdProperty.getValue());
		bdProperty.addListener((observable, oldValue, newValue) -> listener.accept(newValue));
	}

	public void setProperties(Consumer<ResourceBundle> listener) {
		listener.accept(bdProperty.getValue());
	}

}
