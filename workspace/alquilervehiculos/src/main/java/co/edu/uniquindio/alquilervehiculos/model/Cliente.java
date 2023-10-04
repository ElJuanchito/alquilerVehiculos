package co.edu.uniquindio.alquilervehiculos.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
