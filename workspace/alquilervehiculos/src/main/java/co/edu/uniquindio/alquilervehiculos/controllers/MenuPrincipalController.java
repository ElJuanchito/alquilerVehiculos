package co.edu.uniquindio.alquilervehiculos.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.alquilervehiculos.application.App;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MenuPrincipalController {
	
	private static MenuPrincipalController instance;

	public MenuPrincipalController() {
		instance = this;
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
    private HBox btnGestionAlquileres;

    @FXML
    private HBox btnGestionClientes;

    @FXML
    private HBox btnGestionFacturas;

    @FXML
    private HBox btnGestionVehiculos;

    @FXML
    private BorderPane centerPane;
    
    @FXML
	void gestionarClientesEvent(MouseEvent event) {
		gestionarClientesAction();
	}
    
    @FXML
    void gestionarAlquileresEvent(MouseEvent event) {

    }

    @FXML
    void gestionarFacturasEvent(MouseEvent event) {

    }

    @FXML
    void gestionarVehiculosEvent(MouseEvent event) {
    	gestionarVehiculosAction();
    }
	
	private void gestionarClientesAction() {
		cambiarVentana("gestionarCliente");
	}
	
	private void gestionarVehiculosAction() {
		cambiarVentana("registroVehiculo");
	}

	private void cambiarVentana(String fxmlname) {
		try {
			Node nodo = App.loadFXML(fxmlname);
			setCenter(nodo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
    void initialize() {

    }

	public void setCenter(Node node) {
		centerPane.setCenter(node);
	}
	
	public static MenuPrincipalController getInstance() {
		return instance;
	}
}
