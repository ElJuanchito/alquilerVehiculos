package co.edu.uniquindio.alquilervehiculos.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {

	@EqualsAndHashCode.Include
	@NonNull
	private String cedula;
	@NonNull
	private String nombre;
	@NonNull
	private String telefono;
	@NonNull
	private String email;
	@NonNull
	private String ciudad;
	@NonNull
	private String direccion;
}
