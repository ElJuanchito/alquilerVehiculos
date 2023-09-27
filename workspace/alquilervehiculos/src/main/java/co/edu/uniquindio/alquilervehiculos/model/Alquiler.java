package co.edu.uniquindio.alquilervehiculos.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicLong;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Alquiler implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static AtomicLong ai = new AtomicLong(0);
	@EqualsAndHashCode.Include
	private Long id;
	private Factura factura;
	private Cliente cliente;
	private Vehiculo vehiculo;
	private LocalDate fechaAlquiler;
	private LocalDate fechaRegreso;

	public Alquiler(Cliente cliente, Vehiculo vehiculo, LocalDate fechaAlquiler, LocalDate fechaRegreso) {
		super();
		this.setId(ai.get());
		this.cliente = cliente;
		this.vehiculo = vehiculo;
		this.fechaAlquiler = fechaAlquiler;
		this.fechaRegreso = fechaRegreso;
	}

	private Double calcularPrecio() {
		Long dias = ChronoUnit.DAYS.between(fechaAlquiler, fechaRegreso);
		return dias * (vehiculo.getPrecio());
	}

	public static void incrementLong() {
		ai.incrementAndGet();
	}

	public static Long getLong() {
		return ai.get();
	}
	public Factura generarFactura() {
		this.factura = Factura.builder().id(id).fecha(fechaAlquiler).vehiculo(vehiculo).cliente(cliente)
				.costo(calcularPrecio()).build();
		return factura;
	}
	
	public boolean enRangoDeFechas(LocalDate alquiler, LocalDate regreso) {
		return this.fechaAlquiler.isBefore(fechaAlquiler) && fechaRegreso.isAfter(regreso);
	}
}
