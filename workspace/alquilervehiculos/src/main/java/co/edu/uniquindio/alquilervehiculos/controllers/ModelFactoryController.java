package co.edu.uniquindio.alquilervehiculos.controllers;

import java.util.Map;

import co.edu.uniquindio.alquilervehiculos.model.Alquiler;
import co.edu.uniquindio.alquilervehiculos.model.Cliente;
import co.edu.uniquindio.alquilervehiculos.model.EmpresaAlquiler;
import co.edu.uniquindio.alquilervehiculos.model.Factura;
import co.edu.uniquindio.alquilervehiculos.model.Vehiculo;
import co.edu.uniquindio.alquilervehiculos.utils.UtilsPersistence;
import co.edu.uniquindio.alquilervehiculos.utils.UtilsPersistence.Archivo;

public class ModelFactoryController {
	private static ModelFactoryController instance;

	private EmpresaAlquiler empresa;

	private UtilsPersistence serializador;
	
	private ModelFactoryController() {
		serializador = UtilsPersistence.getInstancia();
	}

	public static ModelFactoryController getInstance() {
		if (instance == null)
			instance = new ModelFactoryController();
		return instance;
	}

	public EmpresaAlquiler getEmpresa() {
		if (empresa == null) {
			empresa = new EmpresaAlquiler();
		}
		return empresa;
	}
	
	public void inicializarDatos() {
		guardarClientes();
		guardarVehiculos();
		guardarAlquileres();
		guardarFacturas();
	}
	
	public void leerDatos() {
		leerClientes();
		leerVehiculos();
		leerAlquileres();
		leerFacturas();
	}

	public void guardarClientes() {
		serializador.guardar(getEmpresa().getListaClientes(), Archivo.CLIENTES);
	}

	public void guardarVehiculos() {
		serializador.guardar(getEmpresa().getListaVehiculos(), Archivo.VEHICULOS);
	}

	public void guardarAlquileres() {
		serializador.guardar(getEmpresa().getListaAlquileres(), Archivo.ALQUILERES);
	}

	public void guardarFacturas() {
		serializador.guardar(getEmpresa().getListaFacturas(), Archivo.FACTURAS);
	}
 
	public void leerClientes() {
		getEmpresa().setListaClientes((Map<String, Cliente>) serializador.leer(Archivo.CLIENTES));
	}

	public void leerVehiculos() {
		getEmpresa().setListaVehiculos((Map<String, Vehiculo>) serializador.leer(Archivo.VEHICULOS));
	}

	public void leerAlquileres() {
		getEmpresa().setListaAlquileres((Map<Long, Alquiler>) serializador.leer(Archivo.ALQUILERES));
	}

	public void leerFacturas() {
		getEmpresa().setListaFacturas((Map<Long, Factura>) serializador.leer(Archivo.FACTURAS));
	}

}
