package co.edu.uniquindio.alquilervehiculos.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.edu.uniquindio.alquilervehiculos.exceptions.AlquilerConParametrosNullException;
import co.edu.uniquindio.alquilervehiculos.exceptions.AlquilerNoExistenteException;
import co.edu.uniquindio.alquilervehiculos.exceptions.AlquilerYaExistenteException;
import co.edu.uniquindio.alquilervehiculos.exceptions.ClienteConParametrosNullException;
import co.edu.uniquindio.alquilervehiculos.exceptions.ClienteNoExistenteException;
import co.edu.uniquindio.alquilervehiculos.exceptions.ClienteYaExistenteException;
import co.edu.uniquindio.alquilervehiculos.exceptions.FacturaNoExistenteException;
import co.edu.uniquindio.alquilervehiculos.exceptions.VehiculoConNumerosNegativosException;
import co.edu.uniquindio.alquilervehiculos.exceptions.VehiculoConParametrosNullException;
import co.edu.uniquindio.alquilervehiculos.exceptions.VehiculoNoExistenteException;
import co.edu.uniquindio.alquilervehiculos.exceptions.VehiculoYaAlquiladoException;
import co.edu.uniquindio.alquilervehiculos.exceptions.VehiculoYaExistenteException;
import co.edu.uniquindio.alquilervehiculos.exceptions.VerificarFechasException;
import lombok.Getter;

/**
 * @author ElJuancho
 * @author Breyner_sq
 */
@Getter
public class EmpresaAlquiler {
	private Map<String, Cliente> listaClientes;
	private Map<String, Vehiculo> listaVehiculos;
	private Map<Long, Alquiler> listaAlquileres;
	private Map<Long, Factura> listaFacturas;


	/**
	 * Constructor de la clase <code>EmpresaAlquiler</code>
	 */
	public EmpresaAlquiler() {
		listaClientes = new HashMap<String, Cliente>();
		listaVehiculos = new HashMap<String, Vehiculo>();
		listaAlquileres = new HashMap<Long, Alquiler>();
		listaFacturas = new HashMap<Long, Factura>();
	}

	/**
	 * Verifica si un cliente ya existe en la lista. Retorna un valor booleano.
	 * 
	 * @param cedula
	 * @return
	 */
	public boolean verificarCliente(String cedula) {
		return listaClientes.containsKey(cedula) && listaClientes.get(cedula) != null;
	}

	/**
	 * Lanza una <code>ClienteYaExistenteException</code> si el cliente ya existe en
	 * la lista.
	 * 
	 * @param cedula
	 * @throws ClienteYaExistenteException
	 */
	private void throwClienteYaExistente(String cedula) throws ClienteYaExistenteException {
		if (verificarCliente(cedula))
			throw new ClienteYaExistenteException(
					"El cliente identificado con la cedula: " + cedula + ", ya existe en la lista.");
	}

	/**
	 * Lanza una <code>ClienteNoExistenteException</code> si el cliente no existe en
	 * la lista.
	 * 
	 * @param cedula
	 * @throws ClienteNoExistenteException
	 */
	private void throwClienteNoExistente(String cedula) throws ClienteNoExistenteException {
		if (verificarCliente(cedula))
			throw new ClienteNoExistenteException(
					"El cliente identificado con la cedula: " + cedula + ", no existe en la lista.");
	}

	/**
	 * Verifica si el <code>Cliente</code> introducido por parametro no tiene ningun
	 * atributo nulo. Lanza una <code>ClienteConParametrosNullException</code> si
	 * algun parametro es null.
	 * 
	 * @param cliente
	 * @throws ClienteConParametrosNullException
	 */
	private void verificarDatosCliente(Cliente cliente) throws ClienteConParametrosNullException {
		if (cliente.getCedula() == null || cliente.getCiudad() == null || cliente.getDireccion() == null
				|| cliente.getNombre() == null || cliente.getTelefono() == null || cliente.getEmail() == null)
			throw new ClienteConParametrosNullException(
					"Se estan introduciendo parametros nulos en la creacion del objeto Cliente");
	}

	/**
	 * Busca y retorna un <code>Cliente</code> en la lista. Lanza una
	 * <code>ClienteNoExistenteException</code> si la instancia no existe en la
	 * lista.
	 * 
	 * @param cedula
	 * @return
	 * @throws ClienteNoExistenteException
	 */
	public Cliente buscarCliente(String cedula) throws ClienteNoExistenteException {
		throwClienteNoExistente(cedula);
		return listaClientes.get(cedula);
	}

	/**
	 * Agrega un <code>Cliente</code> y retorna la instancia. Lanza una
	 * <code>ClienteYaExistenteException</code> si el cliente ya existe en la lista
	 * o una <code>ClienteConParametrosNullException</code> si el
	 * <code>Cliente</code> tiene algun atributo nulo.
	 * 
	 * @param cliente
	 * @return
	 * @throws ClienteYaExistenteException
	 * @throws ClienteConParametrosNullException
	 */
	public Cliente agregarCliente(Cliente cliente)
			throws ClienteYaExistenteException, ClienteConParametrosNullException {
		throwClienteYaExistente(cliente.getCedula());
		verificarDatosCliente(cliente);
		return listaClientes.put(cliente.getCedula(), cliente);
	}

	/**
	 * Elimina un <code>Cliente</code> de la lista y lo retorna. Lanza una
	 * <code>ClienteNoExistenteException</code> si el <code>Cliente</code> no existe
	 * en la lista.
	 * 
	 * @param id
	 * @return
	 * @throws ClienteNoExistenteException
	 */
	public Cliente eliminarCliente(String id) throws ClienteNoExistenteException {
		throwClienteNoExistente(id);
		return listaClientes.remove(id);
	}

	/**
	 * Actualiza un <code>Cliente</code> de la lista con la nueva isntancia recibida
	 * por parametro. Lanza una <code>ClienteConParametrosNullException</code> si la
	 * nueva instancia posee atributos nulos o una
	 * <code>ClienteNoExistenteException</code> si el cliente no existe en la lista.
	 * 
	 * @param cliente
	 * @return
	 * @throws ClienteConParametrosNullException
	 * @throws ClienteNoExistenteException
	 */
	public Cliente actualizarCliente(Cliente cliente)
			throws ClienteConParametrosNullException, ClienteNoExistenteException {
		throwClienteNoExistente(cliente.getCedula());
		verificarDatosCliente(cliente);
		return listaClientes.compute(cliente.getCedula(), (k, v) -> v = cliente);
	}

	// CRUD VEHICULOS:

	/**
	 * Verifica que un vehiculo ya exite en la lista.
	 * 
	 * @param placa
	 * @return
	 */
	public boolean verificarVehiculo(String placa) {
		return listaVehiculos.containsKey(placa) && listaVehiculos.get(placa) != null;
	}

	/**
	 * Lanza una <code>VehiculoYaExistenteException</code> si el vehiculo ya existe
	 * en la lista.
	 * 
	 * 
	 * @param placa
	 * @throws VehiculoYaExistenteException
	 */
	private void throwVehiculoYaExistente(String placa) throws VehiculoYaExistenteException {
		if (verificarVehiculo(placa))
			throw new VehiculoYaExistenteException("El vehiculo con la placa: " + placa + " ya existe en la lista.");
	}

	/**
	 * Lanza una <code>VehiculoNoExistenteException</code> si el vehiculo ya existe
	 * en la lista.
	 * 
	 * @param placa
	 * @throws VehiculoNoExistenteException
	 */
	private void throwVehiculoNoExistente(String placa) throws VehiculoNoExistenteException {
		if (verificarVehiculo(placa))
			throw new VehiculoNoExistenteException("El vehiculo con la placa: " + placa + " no existe en la lista.");
	}

	/**
	 * Busca y retorna un <code>Vehiculo</code> en la lista. Lanza una
	 * <code>VehiucloNoExistenteException</code> si la instancia no existe en la
	 * lista.
	 * 
	 * @param placa
	 * @return
	 */
	public Vehiculo buscarVehiculo(String placa) {
		return listaVehiculos.get(placa);
	}

	/**
	 * Agrega un <code>Vehiculo</code> y retorna la instancia. Lanza una
	 * <code>VehiculoYaExistenteException</code> si el cliente ya existe en la lista
	 * o una <code>VehiculoConParametrosNullException</code> si el
	 * <code>Vehiculo</code> tiene algun atributo nulo o una
	 * <code>VehiculoConNumerosNegativosException<code> si el <code>Vehiculo<code>
	 * tiene algun atributo menor a cero.
	 * 
	 * @param vehiculo
	 * @return
	 * @throws VehiculoYaExistenteException
	 * @throws VehiculoConNumerosNegativosException
	 * @throws VehiculoConParametrosNullException
	 */
	public Vehiculo agregarVehiculo(Vehiculo vehiculo) throws VehiculoYaExistenteException,
			VehiculoConNumerosNegativosException, VehiculoConParametrosNullException {
		throwVehiculoYaExistente(vehiculo.getPlaca());
		throwVerificarDatosVehiculo(vehiculo);
		throwVerificarNumerosVehiculo(vehiculo);
		return listaVehiculos.put(vehiculo.getPlaca(), vehiculo);
	}

	/**
	 * Elimina un <code>Vehiculo</code> de la lista y lo retorna. Lanza una
	 * <code>VehiculoNoExistenteException</code> si el <code>Vehiculo</code> no
	 * existe en la lista.
	 * 
	 * @param placa
	 * @return
	 * @throws VehiculoNoExistenteException
	 */
	public Vehiculo eliminarVehiculo(String placa) throws VehiculoNoExistenteException {
		throwVehiculoNoExistente(placa);
		return listaVehiculos.remove(placa);
	}

	/**
	 * Actualiza un
	 * <code>Vehiculo<code> de la lista y lo retorna. Lanza una <code>VehiculoNoExistenteException<code>
	 * si el <code>Vehiculo<code> no existe en la lista o una <code>VehiculoConParametrosNullException</code>
	 * si el <code>Vehiculo</code> tiene algun atributo nulo o una
	 * <code>VehiculoConNumerosNegativosException<code> si el <code>Vehiculo<code>
	 * tiene algun atributo menor a cero.
	 * 
	 * @param vehiculo
	 * @return
	 * @throws VehiculoNoExistenteException
	 * @throws VehiculoConNumerosNegativosException
	 * @throws VehiculoConParametrosNullException
	 */
	public Vehiculo actualizarVehiculo(Vehiculo vehiculo) throws VehiculoNoExistenteException,
			VehiculoConNumerosNegativosException, VehiculoConParametrosNullException {
		throwVerificarDatosVehiculo(vehiculo);
		throwVerificarNumerosVehiculo(vehiculo);
		throwVehiculoNoExistente(vehiculo.getPlaca());
		return listaVehiculos.put(vehiculo.getPlaca(), vehiculo);
	}

	/**
	 * Verifica si el <code>Vehiculo</code> introducido por parametro no tiene
	 * ningun atributo nulo. Lanza una
	 * <code>VehiculoConParametrosNullException</code> si algun parametro es null.
	 * 
	 * @param vehiculo
	 * @throws VehiculoConParametrosNullException
	 */
	private void throwVerificarDatosVehiculo(Vehiculo vehiculo) throws VehiculoConParametrosNullException {
		if (vehiculo.getPlaca() == null || vehiculo.getNombre() == null || vehiculo.getMarca() == null
				|| vehiculo.getModelo() == null || vehiculo.getFoto() == null || vehiculo.getKilometraje() == null
				|| vehiculo.getPrecio() == null || vehiculo.getEsAutomatico() == null || vehiculo.getNSillas() == null)
			throw new VehiculoConParametrosNullException(
					"Se estan introduciendo parametros nulos en la creacion del objeto Vehiculo");
	}

	/**
	 * Verifica si el <code>Vehiculo</code> introducido por parametro no tiene
	 * ningun atributo menor que cero. Lanza una
	 * <code>VehiculoConNumerosNegativosException</code> si algun parametro es null.
	 * 
	 * 
	 * @param vehiculo
	 * @throws VehiculoConNumerosNegativosException
	 */
	private void throwVerificarNumerosVehiculo(Vehiculo vehiculo) throws VehiculoConNumerosNegativosException {
		if (vehiculo.getKilometraje() < 0 || vehiculo.getPrecio() < 0 || vehiculo.getNSillas() < 0)
			throw new VehiculoConNumerosNegativosException(
					"Se estan ingresando valores menores que 0 en la creacion del objeto vehiculo");
	}

	/**
	 * Verifica si un <code>Alquiler</code> ya se encuentra en la lista mediante su
	 * Id. Retorna un valor booleano segun la busqueda.
	 * 
	 * @param id
	 * @return
	 */
	public boolean verificarAlquiler(Long id) {
		return listaAlquileres.containsKey(id) && listaAlquileres.get(id) != null;
	}

	/**
	 * Lanza una <code>VehiculoYaAlquiladoException</code> si el vehiculo tiene el
	 * valor del atributo estado como <code>Estado.ALQUILADO</code> al momento de
	 * querer asociarlo con una nueva instancia de <code>Alquiler</code>.
	 * 
	 * @param placa
	 * @throws VehiculoYaAlquiladoException
	 */
	private void throwVehiculoYaAlquilado(String placa, LocalDate fechaAlquiler, LocalDate fechaRetorno)
			throws VehiculoYaAlquiladoException {
		if (listaAlquileres.values().stream().anyMatch(
				a -> a.enRangoDeFechas(fechaAlquiler, fechaRetorno) && a.getVehiculo().getPlaca().equals(placa)))
			throw new VehiculoYaAlquiladoException(
					"El vehiculo de placas: " + placa + ", ya se encuentra alquilado en esas fechas.");
	}

	/**
	 * Lanza una <code>AlquilerYaExistenteException</code> si el alquiler ya existe.
	 * en la lista.
	 * 
	 * @param id
	 * @throws AlquilerYaExistenteException
	 */
	private void throwAlquilerYaExistente(Long id) throws AlquilerYaExistenteException {
		if (verificarAlquiler(id))
			throw new AlquilerYaExistenteException("El alquiler de id: " + id.toString() + ", ya existe en la lista");
	}

	/**
	 * Lanza una <code>AlquilerNoExistenteException</code> si la instancia de
	 * alquiler con el id introducido por parametro no existe en la lista.
	 * 
	 * @param id
	 * @throws AlquilerNoExistenteException
	 */
	private void throwAlquilerNoExistente(Long id) throws AlquilerNoExistenteException {
		if (!verificarAlquiler(id))
			throw new AlquilerNoExistenteException("El alquiler de id: " + id.toString() + ", no existe en la lista");
	}

	/**
	 * Lanza una <code>AlquilerConParametrosNullException</code> si alguno de los
	 * parametros de la instancia de <code>Alquiler</code> comprobados en el metodo
	 * es nulo.
	 * 
	 * @param alquiler
	 * @throws AlquilerConParametrosNullException
	 */
	private void throwAlquilerConParametrosNull(Alquiler alquiler) throws AlquilerConParametrosNullException {
		if (alquiler.getCliente() == null || alquiler.getVehiculo() == null || alquiler.getFechaAlquiler() == null
				|| alquiler.getFechaRegreso() == null)
			throw new AlquilerConParametrosNullException(
					"La instancia de Alquiler ingresada por paramtro, contiene valores nulos");
	}

	/**
	 * Verifica cual es el siguiente codigo disponible para asignarle a una
	 * instancia de <code>Alquiler</code> al momento de agregarla a la lista.
	 */
	private void crearCodigoLibreAlquiler() {
		while (verificarAlquiler(Alquiler.getLong()))
			Alquiler.incrementLong();
	}

	/**
	 * Busca y retorna una instancia de <code>Alquiler</code> de la lista mediante
	 * el id recibido por parametro. Lanza una
	 * <code>AlquilerNoExistenteException</code> si la isntancia buscada no existe
	 * en la lista.
	 * 
	 * @param id
	 * @return
	 * @throws AlquilerNoExistenteException
	 */
	public Alquiler buscarAlquiler(Long id) throws AlquilerNoExistenteException {
		throwAlquilerNoExistente(id);
		return listaAlquileres.get(id);
	}

	/**
	 * Agrega una instancia de <code>Alquiler</code> a la lista, luego crea una
	 * <code>Factura</code> a base del <b>Alquiler</b> y la agrega a la lista. Lanza
	 * <code>AlquilerYaExistenteException</code> si el <code>Alquiler</code> ya
	 * existe en la lista, una <code>AlquilerConParametrosNullException</code> si la
	 * instancia posee atributos nulos o una
	 * <code>VehiculoYaAlquiladoException</code> si el <code>Vehiculo</code> al cual
	 * esta asociado el <b>Alquiler</b> ya se encuentra en uso durante esas fechas.
	 * 
	 * @param alquiler
	 * @return
	 * @throws AlquilerYaExistenteException
	 * @throws AlquilerConParametrosNullException
	 * @throws VehiculoYaAlquiladoException
	 * @throws VehiculoNoExistenteException
	 */

	public Alquiler agregarAlquiler(Alquiler alquiler) throws AlquilerYaExistenteException,
			AlquilerConParametrosNullException, VehiculoYaAlquiladoException, VerificarFechasException {
		crearCodigoLibreAlquiler();
		alquiler.setId(Alquiler.getLong());
		throwVerificarFechas(alquiler);
		throwAlquilerYaExistente(alquiler.getId());
		throwAlquilerConParametrosNull(alquiler);
		throwVehiculoYaAlquilado(alquiler.getVehiculo().getPlaca(), alquiler.getFechaAlquiler(),
				alquiler.getFechaRegreso());
		alquiler.generarFactura();
		listaFacturas.put(alquiler.getFactura().getId(), alquiler.getFactura());
		return listaAlquileres.put(alquiler.getId(), alquiler);
	}

	private void throwVerificarFechas(Alquiler alquiler) throws VerificarFechasException {
		if (!alquiler.enRangoDeFechaActual())
			throw new VerificarFechasException(
					"La fecha de alquiler no puedes ser anterior a la actual y la de regreso no puede ser anterior a la de alquiler.");
	}

	/**
	 * Elimina y retorna un <code>Alquiler</code> de la lista. Lanza una
	 * <code>AlquilerNoExistenteException</code> si el <code>Alquiler</code> a
	 * eliminar mediante su id no existe en la lista.
	 * 
	 * @param id
	 * @return
	 * @throws AlquilerNoExistenteException
	 */
	public Alquiler EliminarAlquiler(Long id) throws AlquilerNoExistenteException {
		throwAlquilerNoExistente(id);
		return listaAlquileres.remove(id);
	}

	/**
	 * Busca y verifica que una <b>Factura</b> se encuentre en la lista. Retorna un
	 * valor booleano segun la busqueda.
	 * 
	 * @param id
	 * @return
	 */
	public boolean verificarFactura(Long id) {
		return listaFacturas.containsKey(id) && listaFacturas.get(id) != null;
	}

	/**
	 * Lanza una <code>FacturaNoExistenteException</code> si la <b>Factura</b> no
	 * existe en la lista.
	 * 
	 * @param id
	 * @throws FacturaNoExistenteException
	 */
	private void throwFacturaNoExistenteException(Long id) throws FacturaNoExistenteException {
		if (!verificarFactura(id))
			throw new FacturaNoExistenteException("La factura con id: " + id.toString() + ", no existe en la lista.");
	}

	/**
	 * Busca y retorna una <code>Factura</code> de la lista. Lanza una
	 * <code>FacturaNoExistenteException</code> si no encuentra el objeto en la
	 * lista.
	 * 
	 * @param id
	 * @return
	 * @throws FacturaNoExistenteException
	 */
	public Factura buscarFactura(Long id) throws FacturaNoExistenteException {
		throwFacturaNoExistenteException(id);
		return listaFacturas.get(id);
	}
	
	/**
	 * Verifica si un vehiculo esta dispobile en el rango de fechas especificado.
	 * 
	 * @param vehiculo
	 * @param fechaInicio
	 * @param fechaFin
	 * @return <b>true</b> si el vehiculo no esta en la listaAlquileres o
	 *         <b>false</b> si lo esta.
	 */
	private boolean vehiculoDisponibleEnRangoFechas(Vehiculo vehiculo, LocalDate fechaInicio, LocalDate fechaFin) {
		return !listaAlquileres.values().stream()
				.anyMatch(a -> a.getVehiculo().equals(vehiculo) && a.enRangoDeFechas(fechaInicio, fechaFin));
	}

	/**
	 * Retorna una <b>List</b> <b>Vehiculo</b> con los vehiculos que estaba
	 * disponibles en un rango de fechas.
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @return <code>List<{@link Vehiculo}> </code>
	 */
	public List<Vehiculo> vehiculosDisponiblesEnRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
		List<Vehiculo> lista = new ArrayList<Vehiculo>();

		for (Map.Entry<String, Vehiculo> entrada : listaVehiculos.entrySet()) {
			Vehiculo v = entrada.getValue();
			if (vehiculoDisponibleEnRangoFechas(v, fechaInicio, fechaFin))
				lista.add(v);
		}
		return lista;
	}
	
	/**
	 * Retorna una <code>List<code> <code>Vehiculo<code> con los vehiculos que fueron
	 * alquilados en la <code>fecha<code> ingresada por parametro.
	 * 
	 * @param fecha
	 * @return
	 */
	public List<Vehiculo> vehiculosAlquiladosEnFecha(LocalDate fecha){
		List<Vehiculo> lista= new ArrayList<Vehiculo>();
		
		for(Map.Entry<Long, Alquiler> entrada: listaAlquileres.entrySet()) {
			Alquiler v= entrada.getValue();
			if(fecha.equals(v.getFechaAlquiler()))
				lista.add(v.getVehiculo());
		}
		return lista;
	}
	
	/**
	 * Halla una <code>sumaCostos<code> de todos los costos almacenados en <b>listaFcaturas<b> 
	 * en un rango de fechas que entran por parametros.
	 *  
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	public double TotalGanadoPorAlquileresEnRangoFechas (LocalDate fechaInicio, LocalDate fechaFin) {
		double sumaCostos=0;
		for (Map.Entry<Long, Factura> entrada: listaFacturas.entrySet()) {
			Factura f= entrada.getValue();
			
			if (f.getFecha().isAfter(fechaInicio) && f.getFecha().isBefore(fechaFin)) {
	            sumaCostos += f.getCosto(); 
	        }
		}
		return sumaCostos;
	}
	
	
	public String VehiculoMasAlquilado () {
		Map<Marca, Integer> listaMarcas;
		listaMarcas = new HashMap<Marca, Integer>();
		
		for (Map.Entry<Marca, Integer> entrada: listaMarcas.entrySet()) {
			Marca f= entrada.getValue();
			
			if ()
			
		}
	}

	/**
	 * Busca en <b>listaAlquileres</b> la marca de vehiculo mas alquilada y la retorna.
	 * @param listaAlquileres
	 * @return la <b>Marca</b> mas alquilada o null si todas las marcas se alquilaron en mismo numero.
	 */
	public Marca obtenerMarcaMasAlquilada(Map<Long, Alquiler> listaAlquileres) {
		Map<Marca, Integer> mapa = new HashMap<Marca, Integer>();
		for (Alquiler alquiler : listaAlquileres.values()) {
			Marca marca = alquiler.getVehiculo().getMarca();
			mapa.put(marca, mapa.getOrDefault(marca, 0) + 1);
			System.out.println("k: " + marca + ", v: " + mapa.get(marca));
		}
		System.out.println(mapa.toString());

		if (mapa.values().stream().distinct().limit(2).count() <= 1)
			return null;

		return mapa.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElseGet(null);
	}
}
