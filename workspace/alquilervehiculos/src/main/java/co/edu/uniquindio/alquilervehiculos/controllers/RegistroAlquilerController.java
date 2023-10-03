package co.edu.uniquindio.alquilervehiculos.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.alquilervehiculos.application.App;
import co.edu.uniquindio.alquilervehiculos.model.Vehiculo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class RegistroAlquilerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblCedula;

    @FXML
    private Label lblPlaca;

    @FXML
    private Label lblFechaAlquiler;

    @FXML
    private Label lblFechaRegreso;

    @FXML
    private TextField txtCedula;

    @FXML
    private Label lblPlacaResultado;

    @FXML
    private DatePicker dtFechaAlquiler;

    @FXML
    private DatePicker dtFechaRegreso;

    @FXML
    private Label lblSeleccionaVehiculo;

    @FXML
    private TableView<Vehiculo> tableVehiculos;

    @FXML
    private TableColumn<Vehiculo, String> colPlaca;

    @FXML
    private TableColumn<Vehiculo, String> colNombre;

    @FXML
    private TableColumn<Vehiculo, String> colMarca;

    @FXML
    private TableColumn<Vehiculo, String> colModelo;

    @FXML
    private TableColumn<Vehiculo, String> colKilometraje;

    @FXML
    private TableColumn<Vehiculo, String> colPrecio;

    @FXML
    private TableColumn<Vehiculo, String> colAutomatico;

    @FXML
    private TableColumn<Vehiculo, String> colSillas;

    @FXML
    private Button btnAlquilar;

    @FXML
    void AlquilarEvent(ActionEvent event) {

    }

    @FXML
    void backEvent(ActionEvent event) {
    	try {
			Node nodo = App.loadFXML("gestionarAlquiler");
			MenuPrincipalController.getInstance().setCenter(nodo);
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    @FXML
    void initialize() {

    }
}
