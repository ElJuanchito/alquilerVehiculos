package co.edu.uniquindio.alquilervehiculos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Estado {
	ALQUILADO("Alquilado"), NO_ALQUILADO("No alquilado");
	
	private String nombre;
}
