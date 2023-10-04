package co.edu.uniquindio.alquilervehiculos.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Vehiculo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	private String placa;
	private String nombre;
	private Marca marca;
	private String modelo;
	private String foto;
	private Double kilometraje;
	private Double precio;
	private Boolean esAutomatico;
	private Integer nSillas;
	
	public String getAutomaticoText() {
		if(esAutomatico) return "Si";
		return "No";
	}
}
