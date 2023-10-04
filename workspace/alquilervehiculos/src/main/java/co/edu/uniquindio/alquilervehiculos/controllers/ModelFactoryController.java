package co.edu.uniquindio.alquilervehiculos.controllers;

import co.edu.uniquindio.alquilervehiculos.model.EmpresaAlquiler;

public class ModelFactoryController {
	private static ModelFactoryController instance;
	
	private EmpresaAlquiler empresa;

	public static ModelFactoryController getInstance() {
		if (instance == null)
			instance = new ModelFactoryController();
		return instance;
	}
	
	public EmpresaAlquiler getEmpresa() {
		if(empresa == null) {
			empresa = new EmpresaAlquiler();
		} return empresa;
	}
	
	
	
	
}
