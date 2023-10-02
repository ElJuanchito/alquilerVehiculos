package co.edu.uniquindio.alquilervehiculos.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class RegistroVehiculoController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnImagen;

	@FXML
	private ComboBox<String> cbTransmision;

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
	private Spinner<Integer> spinSillas;

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
	void selectImageEvent(ActionEvent event) {

	}

	@FXML
	void initialize() {
	}

}
