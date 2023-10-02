package co.edu.uniquindio.alquilervehiculos.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class GestionarVehiculoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblTitle;

    @FXML
    private TextField txtBuscar;

    @FXML
    private TableView<?> tableVehiculos;

    @FXML
    private TableColumn<?, ?> colCedula;

    @FXML
    private TableColumn<?, ?> colNombre;

    @FXML
    private TableColumn<?, ?> colTelefono;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colCiudad;

    @FXML
    private TableColumn<?, ?> colDireccion;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnRegistrar;

    @FXML
    void eliminarEvent(ActionEvent event) {

    }

    @FXML
    void registrarEvent(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }
}
