package co.edu.uniquindio.alquilervehiculos.model;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Marca {
	TOYOTA("Toyota"), FORD("Ford"), VOLKSWAGEN("Volkswagen"), HONDA("Honda"), CHEVROLET("Chevrolet"), NISSAN("Nissan"),
	BMW("BMW"), MERCEDES_BENZ("Mercedes-Benz"), AUDI("Audi"), HYUNDAI("Hyundai"), KIA("Kia"), SUBARU("Subaru"),
	MAZDA("Mazda"), JEEP("Jeep"), TESLA("Tesla"), PORSCHE("Porsche"), LAND_ROVER("Land Rover"), VOLVO("Volvo");
	
	@Getter
	private String nombre;
	
	public static String[] obtenerTextos() {
		return (String[]) Arrays.stream(values()).map(Marca::getNombre).toArray();
	}

}
