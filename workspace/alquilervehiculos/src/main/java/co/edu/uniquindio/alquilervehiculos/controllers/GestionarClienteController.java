package co.edu.uniquindio.alquilervehiculos.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.alquilervehiculos.application.App;
import co.edu.uniquindio.alquilervehiculos.model.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class GestionarClienteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblTitle;

    @FXML
    private TextField txtBuscar;

    @FXML
    private TableView<Cliente> tableClientes;

    @FXML
    private TableColumn<Cliente, String> colCedula;

    @FXML
    private TableColumn<Cliente, String> colNombre;

    @FXML
    private TableColumn<Cliente, String> colTelefono;

    @FXML
    private TableColumn<Cliente, String> colEmail;

    @FXML
    private TableColumn<Cliente, String> colCiudad;

    @FXML
    private TableColumn<Cliente, String> colDireccion;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnRegistrar;

    @FXML
    void eliminarEvent(ActionEvent event) {

    }

    @FXML
    void registrarEvent(ActionEvent event) {
    	try {
			Node nodo = App.loadFXML("registroCliente");
			MenuPrincipalController.getInstance().setCenter(nodo);
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    @FXML
    void initialize() {
    }
}

