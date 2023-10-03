package co.edu.uniquindio.alquilervehiculos.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.alquilervehiculos.model.Factura;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class GestionarFacturaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblTitle;

    @FXML
    private TextField txtBuscar;

    @FXML
    private TableView<Factura> tablaAlquileres;

    @FXML
    private TableColumn<Factura, String> colId;

    @FXML
    private TableColumn<Factura, String> colFecha;

    @FXML
    private TableColumn<Factura, String> colVehiculo;

    @FXML
    private TableColumn<Factura, String> colCliente;

    @FXML
    private TableColumn<Factura, String> colCosto;

    @FXML
    void initialize() {

    }
}
