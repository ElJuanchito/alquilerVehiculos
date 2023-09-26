package co.edu.uniquindio.alquilervehiculos.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Factura {
	@EqualsAndHashCode.Include
	private Long id;
	@Setter
	private LocalDateTime fecha;
	@Setter
	private Vehiculo vehiculo;
	@Setter
	private Cliente cliente;
	@Setter
	private Double costo;

	@Builder
	public Factura(LocalDateTime fecha, Vehiculo vehiculo, Cliente cliente, Double costo) {
		super();
		this.id = Alquiler.getLong();
		this.fecha = fecha;
		this.vehiculo = vehiculo;
		this.cliente = cliente;
		this.costo = costo;
	}
	
}
