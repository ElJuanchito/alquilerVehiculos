package co.edu.uniquindio.alquilervehiculos.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import co.edu.uniquindio.alquilervehiculos.utils.UtilsProperties;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CambiarIdiomaController implements Initializable, Consumer<ResourceBundle>{

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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		UtilsProperties.getInstancia().addListener(this);
	}
	
	@Override
	public void accept(ResourceBundle t) {
		lblTitulo.setText(t.getString("CambiarIdioma.lblTitulo"));
		btnEspanol.setText(t.getString("CambiarIdioma.btnEspanol"));
		btnIngles.setText(t.getString("CambiarIdioma.btnIngles"));
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
		UtilsProperties.getInstancia().setLanguage("es");
	}

	private void inglesAction() {
		UtilsProperties.getInstancia().setLanguage("en");
	}

}
