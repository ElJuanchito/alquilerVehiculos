package co.edu.uniquindio.alquilervehiculos.model;

import java.time.LocalDate;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Factura {
	@EqualsAndHashCode.Include
	private Long id;
	@Setter
	private LocalDate fecha;
	@Setter
	private Vehiculo vehiculo;
	@Setter
	private Cliente cliente;
	@Setter
	private Double costo;

	@Builder
	public Factura(Long id, LocalDate fecha, Vehiculo vehiculo, Cliente cliente, Double costo) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.vehiculo = vehiculo;
		this.cliente = cliente;
		this.costo = costo;
	}
	
	public boolean empiezaPor(Long id) {
		return getId().toString().startsWith(id.toString());
	}
	
}
