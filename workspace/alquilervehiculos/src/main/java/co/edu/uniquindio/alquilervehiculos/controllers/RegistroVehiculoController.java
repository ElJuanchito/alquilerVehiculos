package co.edu.uniquindio.alquilervehiculos.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.alquilervehiculos.application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class RegistroVehiculoController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private RadioButton btnAutomatico;

	@FXML
	private Button btnBack;

	@FXML
	private Button btnImagen;

	@FXML
	private Button btnRegistrar;

	@FXML
	private Label lblAutomatico;

	@FXML
	private Label lblFoto;

	@FXML
	private Label lblKilometraje;

	@FXML
	private Label lblMarca;

	@FXML
	private Label lblModelo;

	@FXML
	private Label lblNombre;

	@FXML
	private Label lblPlaca;

	@FXML
	private Label lblPrecio;

	@FXML
	private Label lblSillas;

	@FXML
	private Label lblTitulo;

	@FXML
	private Label lblTransmision;

	@FXML
	private Spinner<?> spinSillas;

	@FXML
	private TextField txtKilometraje;

	@FXML
	private TextField txtMarca;

	@FXML
	private TextField txtModelo;

	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtPlaca;

	@FXML
	private TextField txtPrecio;


	@FXML
	void registrarEvent(ActionEvent event) {

	}


	@FXML
	void backEvent(ActionEvent event) {
		try {
			Node nodo = App.loadFXML("gestionarVehiculo");
			MenuPrincipalController.getInstance().setCenter(nodo);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void selectImageEvent(ActionEvent event) {

	}

	@FXML
	void initialize() {

	}
}
