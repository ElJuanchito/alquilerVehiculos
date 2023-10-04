package co.edu.uniquindio.alquilervehiculos.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.alquilervehiculos.model.Vehiculo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class VehiculosDisponiblesEnFechasController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblFechaInicial;

    @FXML
    private Label lblFechaFinal;

    @FXML
    private Button btnBuscar;

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
    void buscarEvent(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }
}
