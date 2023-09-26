package co.edu.uniquindio.alquilervehiculos.model;

import java.util.HashMap;
import java.util.Map;

import co.edu.uniquindio.alquilervehiculos.exceptions.ClienteConParametrosNullException;
import co.edu.uniquindio.alquilervehiculos.exceptions.ClienteNoExistenteException;
import co.edu.uniquindio.alquilervehiculos.exceptions.ClienteYaExistenteException;
import co.edu.uniquindio.alquilervehiculos.exceptions.VehiculoConNumerosNegativosException;
import co.edu.uniquindio.alquilervehiculos.exceptions.VehiculoConParametrosNullException;
import co.edu.uniquindio.alquilervehiculos.exceptions.VehiculoNoExistenteException;
import co.edu.uniquindio.alquilervehiculos.exceptions.VehiculoYaExistenteException;

/**
 * @author ElJuancho
 * @author Breyner_sq
 */
public class EmpresaAlquiler {
	private Map<String, Cliente> listaClientes;
	private Map<String, Vehiculo> listaVehiculos;

	public EmpresaAlquiler() {
		listaClientes = new HashMap<String, Cliente>();
		listaVehiculos = new HashMap<String, Vehiculo>();
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
	 * Lanza una <code>VehiculoYaExistenteException</code> si el vehiculo ya existe en
	 * la lista.
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
	 * Lanza una <code>VehiculoNoExistenteException</code> si el vehiculo ya existe en
	 * la lista.
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
	 * <code>Vehiculo</code> tiene algun atributo nulo o una <code>VehiculoConNumerosNegativosException<code>
	 * si el <code>Vehiculo<code> tiene algun atributo menor a cero.
	 * 
	 * @param vehiculo
	 * @return
	 * @throws VehiculoYaExistenteException
	 * @throws VehiculoConNumerosNegativosException
	 * @throws VehiculoConParametrosNullException
	 */
	public Vehiculo agregarVehiculo (Vehiculo vehiculo) 
			throws VehiculoYaExistenteException, VehiculoConNumerosNegativosException, VehiculoConParametrosNullException{
		throwVehiculoYaExistente(vehiculo.getPlaca());
		throwVerificarDatosVehiculo(vehiculo);
		throwVerificarNumerosVehiculo(vehiculo);
		return listaVehiculos.put(vehiculo.getPlaca(), vehiculo);
	}
	
	/**
	 * Elimina un <code>Vehiculo</code> de la lista y lo retorna. Lanza una
	 * <code>VehiculoNoExistenteException</code> si el <code>Vehiculo</code> no existe
	 * en la lista.
	 * 
	 * @param placa
	 * @return
	 * @throws VehiculoNoExistenteException
	 */
	public Vehiculo eliminarVehiculo (String placa) throws VehiculoNoExistenteException {
		throwVehiculoNoExistente(placa);
		return listaVehiculos.remove(placa);
	}
	
	/**
	 * Actualiza un <code>Vehiculo<code> de la lista y lo retorna. Lanza una <code>VehiculoNoExistenteException<code>
	 * si el <code>Vehiculo<code> no existe en la lista o una <code>VehiculoConParametrosNullException</code> si el
	 * <code>Vehiculo</code> tiene algun atributo nulo o una <code>VehiculoConNumerosNegativosException<code>
	 * si el <code>Vehiculo<code> tiene algun atributo menor a cero.
	 * 
	 * @param vehiculo
	 * @return
	 * @throws VehiculoNoExistenteException
	 * @throws VehiculoConNumerosNegativosException
	 * @throws VehiculoConParametrosNullException
	 */
	public Vehiculo actualizarVehiculo(Vehiculo vehiculo) 
			throws VehiculoNoExistenteException, VehiculoConNumerosNegativosException, VehiculoConParametrosNullException {
		throwVerificarDatosVehiculo(vehiculo);
		throwVerificarNumerosVehiculo(vehiculo);
		throwVehiculoNoExistente(vehiculo.getPlaca());
		return listaVehiculos.put(vehiculo.getPlaca(), vehiculo);
	}

	/**
	 * Verifica si el <code>Vehiculo</code> introducido por parametro no tiene ningun
	 * atributo nulo. Lanza una <code>VehiculoConParametrosNullException</code> si
	 * algun parametro es null.
	 * 
	 * @param vehiculo
	 * @throws VehiculoConParametrosNullException
	 */
	private void throwVerificarDatosVehiculo (Vehiculo vehiculo) throws VehiculoConParametrosNullException {
		if(vehiculo.getPlaca() == null || vehiculo.getNombre() == null ||  vehiculo.getMarca()== null || vehiculo.getModelo()== null || 
				vehiculo.getFoto()== null || vehiculo.getKilometraje()== null || vehiculo.getPrecio()== null || vehiculo.getEsAutomatico()== null || vehiculo.getNSillas()== null) 
			throw new VehiculoConParametrosNullException( "Se estan introduciendo parametros nulos en la creacion del objeto Vehiculo");	
		}
	
	/**
	 * Verifica si el <code>Vehiculo</code> introducido por parametro no tiene ningun
	 * atributo menor que cero. Lanza una <code>VehiculoConNumerosNegativosException</code> si
	 * algun parametro es null.
	 * 
	 * 
	 * @param vehiculo
	 * @throws VehiculoConNumerosNegativosException
	 */
	private void throwVerificarNumerosVehiculo (Vehiculo vehiculo) throws VehiculoConNumerosNegativosException {
		if(vehiculo.getKilometraje()<0 || vehiculo.getPrecio()<0 || vehiculo.getNSillas()<0)
			throw new VehiculoConNumerosNegativosException("Se estan ingresando valores menores que 0 en la creacion del objeto vehiculo");
	}
	
	//Factura
	
	/**
	 * Crea un codigo libre para la Factura
	 * 
	 * @param codigo
	 * @throws AlquilerExistenteException
	 * @author ElJuancho
	 */
	
	/*
	private void crearCodigoLibreAlquiler() {
		while (verificarAlquiler(Alquiler.getLong()))
			Factura.incrementLong();
	}
	*/
}
