package co.edu.uniquindio.alquilervehiculos.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MasFuncionesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAlquiladosEnUnaFecha;

    @FXML
    private Button btnIdioma;

    @FXML
    private Button btnMarcaMasAlquilada;

    @FXML
    private Button btnTotalGanadoEnFechas;

    @FXML
    private Button btnVehiculosDisponiblesEnFechas;

    @FXML
    private Label lblTitle;
    @FXML
    void cambiarIdiomaEvent(ActionEvent event) {

    }

    void alquiladosEnUnaFechaEvent(ActionEvent event) {
    	try {
			Node nodo = App.loadFXML("alquiladosEnUnaFecha");
			MenuPrincipalController.getInstance().setCenter(nodo);
		} catch (IOException e) {
			e.printStackTrace();
		}


    }

    @FXML
    void marcaMasAlquiladaEvent(ActionEvent event) {
    }

    @FXML
    void totalGanadoEnFechasEvent(ActionEvent event) {

    }

    @FXML
    void vehiculosDisponiblesEnFechasEvent(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

}
