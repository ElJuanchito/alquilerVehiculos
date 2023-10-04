package co.edu.uniquindio.alquilervehiculos.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.alquilervehiculos.utils.UtilsProperties;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CambiarIdiomaController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnEspanol;

	@FXML
	private Button btnIngles;

	@FXML
	private Label lblTitulo;

	@FXML
	void initialize() {
		resources = UtilsProperties.getInstancia().obtenerRecursos();
		
		lblTitulo.setText(resources.getString("CambiarIdioma.lblTitulo"));
		btnEspanol.setText(resources.getString("CambiarIdioma.btnEspanol"));
		btnIngles.setText(resources.getString("CambiarIdioma.btnIngles"));

	}

	@FXML
	void espanolEvent(ActionEvent event) {
		espanolAction();
	}

	@FXML
	void inglesEvent(ActionEvent event) {
		inglesAction();
	}
	
	private void espanolAction() {
		UtilsProperties.getInstancia().cambiarEspanol();
	}

	private void inglesAction() {
		UtilsProperties.getInstancia().cambiarIngles();
	}

}
