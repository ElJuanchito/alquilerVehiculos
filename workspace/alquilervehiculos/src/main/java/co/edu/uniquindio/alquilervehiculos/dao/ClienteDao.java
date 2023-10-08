package co.edu.uniquindio.alquilervehiculos.dao;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.uniquindio.alquilervehiculos.model.Cliente;

public class ClienteDao {
	
	private ObjectMapper obMapper;
	
	private final String RUTA = "src/main/resources/META-INF/persistencia/clientes.json";

	public ClienteDao() {
		obMapper = new ObjectMapper();
	}
	
	public void jsonizar(Map<String, Cliente> clientes) {
		try {
			obMapper.writeValue(new File(RUTA), clientes);
		} catch (StreamWriteException e) {
			e.printStackTrace();
		} catch (DatabindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, Cliente> desjsonizar() {
		try {
			return obMapper.readValue(new File(RUTA), new TypeReference<Map<String, Cliente>>(){});
		} catch (StreamReadException e) {
			e.printStackTrace();
		} catch (DatabindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			jsonizar(new HashMap<String, Cliente>());
		}
		return null;
	}
}
