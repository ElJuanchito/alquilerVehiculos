package co.edu.uniquindio.alquilervehiculos.model;

import java.util.Map;

import co.edu.uniquindio.alquilervehiculos.exceptions.ClienteNoExistenteException;
import co.edu.uniquindio.alquilervehiculos.exceptions.ClienteYaExistenteException;
import co.edu.uniquindio.alquilervehiculos.exceptions.VehiculoNoExistenteException;
import co.edu.uniquindio.alquilervehiculos.exceptions.VehiculoYaExistenteException;

public class Alquiler {
	private Map<String, Cliente> listaClientes;
	private Map<String, Vehiculo> listaVehiculos;

	public Alquiler() {

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

	private void throwClienteYaExistente(String cedula) throws ClienteYaExistenteException {
		if (verificarCliente(cedula))
			throw new ClienteYaExistenteException(
					"El cliente identificado con la cedula: " + cedula + ", ya existe en la lista.");
	}
	
	private void throwClienteNoExistente(String cedula) throws ClienteNoExistenteException {
		if (verificarCliente(cedula))
			throw new ClienteNoExistenteException(
					"El cliente identificado con la cedula: " + cedula + ", no existe en la lista.");
	}

	public Cliente buscarCliente(String cedula) {
		return listaClientes.get(cedula);
	}
	
	
	// CRUD VEHICULOS:
	
	/**
	 * 
	 * @param placa
	 * @return
	 */
	public boolean verificarVehiculo(String placa) {
		return listaVehiculos.containsKey(placa) && listaVehiculos.get(placa) != null;
	}
	
	public void throwVehiculoYaExistente(String placa) throws VehiculoYaExistenteException {
		if(verificarVehiculo(placa))
			throw new VehiculoYaExistenteException("El vehiculo con la placa: " + placa + " ya existe en la lista.");
		}
	
	public void throwVehiculoNoExistente(String placa) throws VehiculoNoExistenteException{
		if(verificarVehiculo(placa))
			throw new VehiculoNoExistenteException("El vehiculo con la placa: " + placa + " no existe en la lista.");	
		}
	
	public Vehiculo buscarVehiculo(String placa) {
		return listaVehiculos.get(placa);
	}
}
