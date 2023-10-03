package co.edu.uniquindio.alquilervehiculos.controllers;

public class ModelFactoryController {
	private static ModelFactoryController instance;

	public static ModelFactoryController getInstance() {
		if (instance == null)
			instance = new ModelFactoryController();
		return instance;
	}
	
	
}
