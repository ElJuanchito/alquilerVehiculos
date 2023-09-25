package co.edu.uniquindio.alquilervehiculos.model;

import java.util.HashMap;
import java.util.Map;

import co.edu.uniquindio.alquilervehiculos.exceptions.ClienteConParametrosNullException;
import co.edu.uniquindio.alquilervehiculos.exceptions.ClienteNoExistenteException;
import co.edu.uniquindio.alquilervehiculos.exceptions.ClienteYaExistenteException;

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

}
