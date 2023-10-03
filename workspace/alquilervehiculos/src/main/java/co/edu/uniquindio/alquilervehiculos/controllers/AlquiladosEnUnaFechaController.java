package co.edu.uniquindio.alquilervehiculos.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.alquilervehiculos.model.Alquiler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AlquiladosEnUnaFechaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblSeleccionaFecha;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private TableView<Alquiler> tablaAlquileres;

    @FXML
    private TableColumn<Alquiler, String> colId;

    @FXML
    private TableColumn<Alquiler, String> colFactura;

    @FXML
    private TableColumn<Alquiler, String> colCliente;

    @FXML
    private TableColumn<Alquiler, String> colVehiculo;

    @FXML
    private TableColumn<Alquiler, String> colAlquiler;

    @FXML
    private TableColumn<Alquiler, String> colRegreso;

    @FXML
    private Button btnBuscar;

    @FXML
    void buscarEvent(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }
}
