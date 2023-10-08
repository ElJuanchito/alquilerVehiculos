package co.edu.uniquindio.alquilervehiculos.controllers;

import java.util.HashMap;
import java.util.Map;

import co.edu.uniquindio.alquilervehiculos.dao.ClienteDao;
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

	private ClienteDao cDao;

	private ModelFactoryController() {
		serializador = UtilsPersistence.getInstancia();
		cDao = new ClienteDao();
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

//	public void guardarClientes() {
//		serializador.guardar(getEmpresa().getListaClientes(), Archivo.CLIENTES);
//	}

	public void guardarClientes() {
		cDao.jsonizar(getEmpresa().getListaClientes());
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

//	public void leerClientes() {
//		getEmpresa().setListaClientes((Map<String, Cliente>) serializador.leer(Archivo.CLIENTES));
//	}

	public void leerClientes() {
		getEmpresa().setListaClientes(cDao.desjsonizar());
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

	public void verificarClientes() {
		if (!getEmpresa().verificarListaClientes())
			getEmpresa().setListaClientes(new HashMap<String, Cliente>());
	}

	public void verificarVehiculos() {
		if (!getEmpresa().verificarListaVehiculos())
			getEmpresa().setListaVehiculos(new HashMap<String, Vehiculo>());
	}

	public void verificarAlquileres() {
		if (!getEmpresa().verificarListaAlquileres())
			getEmpresa().setListaAlquileres(new HashMap<Long, Alquiler>());
	}

	public void verificarFacturas() {
		if (!getEmpresa().verificarListaFacturas())
			getEmpresa().setListaFacturas(new HashMap<Long, Factura>());
	}

}
