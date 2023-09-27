package co.edu.uniquindio.alquilervehiculos.model;

import java.io.Serializable;
import java.time.LocalDateTime;
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
	private LocalDateTime fechaAlquiler;
	private LocalDateTime fechaRegreso;

	public Alquiler(Cliente cliente, Vehiculo vehiculo, LocalDateTime fechaAlquiler, LocalDateTime fechaRegreso) {
		super();
		this.setId(ai.get());
		this.cliente = cliente;
		this.vehiculo = vehiculo;
		this.fechaAlquiler = fechaAlquiler;
		this.fechaRegreso = fechaRegreso;
	}

	private Double calcularPrecio() {
		Integer dias = (int) ChronoUnit.DAYS.between(fechaRegreso, fechaAlquiler);
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
}
