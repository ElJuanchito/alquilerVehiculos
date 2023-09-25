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
public class Cliente {

	@EqualsAndHashCode.Include
	private String cedula;
	private String nombre;
	private String telefono;
	private String email;
	private String ciudad;
	private String direccion;
}
