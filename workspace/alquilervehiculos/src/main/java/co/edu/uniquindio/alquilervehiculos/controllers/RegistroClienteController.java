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
import javafx.scene.control.TextField;

public class RegistroClienteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Label lblCedula;

    @FXML
    private Label lblCiudad;

    @FXML
    private Label lblDireccion;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblTelefono;

    @FXML
    private Label lblTitulo;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtCiudad;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    void backEvent(ActionEvent event) {
    	try {
			Node nodo = App.loadFXML("gestionarCliente");
			MenuPrincipalController.getInstance().setCenter(nodo);
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    @FXML
    void registrarEvent(ActionEvent event) {

    }


    @FXML
    void initialize() {

    }
}
