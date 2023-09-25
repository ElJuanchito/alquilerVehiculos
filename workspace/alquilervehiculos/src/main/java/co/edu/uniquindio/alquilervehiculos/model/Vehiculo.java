package co.edu.uniquindio.alquilervehiculos.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Vehiculo {

	@EqualsAndHashCode.Include
	private String placa;
	private String nombre;
	private String marca;
	private String modelo;
	private String foto;
	private Double kilometraje;
	private Double precio;
	private Boolean esAutomatico;
	private Integer nSillas;
}
