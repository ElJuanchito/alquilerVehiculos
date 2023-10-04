package co.edu.uniquindio.alquilervehiculos.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.alquilervehiculos.application.App;
import co.edu.uniquindio.alquilervehiculos.utils.UtilsProperties;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
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
    private HBox btnMasFunciones;
    
    @FXML
    private Label lblGestionarAlquileres;
    
    @FXML
    private Label lblGestionarClientes;
    
    @FXML
    private Label lblGestionarFacturas;
    
    @FXML
    private Label lblGestionarVehiculos;
    
    @FXML
    private Label lblMasFunciones;

    @FXML
    private BorderPane centerPane;
    
    @FXML
    void initialize() {
    	resources = UtilsProperties.getInstancia().obtenerRecursos();
    	
    	lblGestionarClientes.setText(resources.getString("MenuPrincipal.lblGestionClientes"));
    	lblGestionarVehiculos.setText(resources.getString("MenuPrincipal.lblGestionVehiculos"));
    	lblGestionarAlquileres.setText(resources.getString("MenuPrincipal.lblGestionAlquileres"));
    	lblGestionarFacturas.setText(resources.getString("MenuPrincipal.lblGestionFacturas"));
    	lblMasFunciones.setText(resources.getString("MenuPrincipal.lblMasFunciones"));
    }
    
    @FXML
	void gestionarClientesEvent(MouseEvent event) {
		gestionarClientesAction();
	}
    
    @FXML
    void gestionarAlquileresEvent(MouseEvent event) {
    	gestionarAlquileresAction();

    }
    
    @FXML
    void masFuncionesEvent(MouseEvent event) {
    	masFuncionesAction();
   
    }

    @FXML
    void gestionarFacturasEvent(MouseEvent event) {
    	gestionarFacturaAction();

    }

    @FXML
    void gestionarVehiculosEvent(MouseEvent event) {
    	gestionarVehiculosAction();
    }
	
	private void gestionarClientesAction() {
		cambiarVentana("gestionarCliente");
	}
	
	private void gestionarVehiculosAction() {
		cambiarVentana("gestionarVehiculo");
	}
	
	private void gestionarAlquileresAction() {
		cambiarVentana("gestionarAlquiler");
	}
	
	private void gestionarFacturaAction() {
		cambiarVentana("gestionarFactura");
	}
	
	private void masFuncionesAction() {
		cambiarVentana("masFunciones");
	}

	private void cambiarVentana(String fxmlname) {
		try {
			Node nodo = App.loadFXML(fxmlname);
			setCenter(nodo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setCenter(Node node) {
		centerPane.setCenter(node);
	}
	
	public static MenuPrincipalController getInstance() {
		return instance;
	}
}
