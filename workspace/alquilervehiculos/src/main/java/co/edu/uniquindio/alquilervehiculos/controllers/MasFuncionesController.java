package co.edu.uniquindio.alquilervehiculos.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.alquilervehiculos.application.App;
import co.edu.uniquindio.alquilervehiculos.model.EmpresaAlquiler;
import co.edu.uniquindio.alquilervehiculos.model.Marca;
import co.edu.uniquindio.alquilervehiculos.utils.UtilsProperties;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    
    private EmpresaAlquiler empresa = ModelFactoryController.getInstance().getEmpresa();
    
    @FXML
    void initialize() {
    	resources = UtilsProperties.getInstancia().obtenerRecursos();
    	
    	lblTitle.setText(resources.getString("MasFunciones.lblTitle"));
    	btnMarcaMasAlquilada.setText(resources.getString("MasFunciones.btnMarcaMasAlquilada"));
    	btnAlquiladosEnUnaFecha.setText(resources.getString("MasFunciones.btnAlquiladosEnUnaFecha"));
    	btnTotalGanadoEnFechas.setText(resources.getString("MasFunciones.btnTotalGanadoEnFechas"));
    	btnVehiculosDisponiblesEnFechas.setText(resources.getString("MasFunciones.btnVehiculosDisponiblesEnFechas"));
    	btnIdioma.setText(resources.getString("MasFunciones.btnIdioma"));
    }
    
    @FXML
    void cambiarIdiomaEvent(ActionEvent event) {
    	try {
			Node nodo = App.loadFXML("cambiarIdioma");
			MenuPrincipalController.getInstance().setCenter(nodo);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
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
    	Marca marca = empresa.obtenerMarcaMasAlquilada();
    	new Alert(AlertType.CONFIRMATION, "La marca mas alquilada es" + marca.toString()).show();
    }

    @FXML
    void totalGanadoEnFechasEvent(ActionEvent event) {
    	try {
			Node nodo = App.loadFXML("totalGanadoEnFechas");
			MenuPrincipalController.getInstance().setCenter(nodo);
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    @FXML
    void vehiculosDisponiblesEnFechasEvent(ActionEvent event) {
    	try {
			Node nodo = App.loadFXML("vehiculosDisponiblesEnFechas");
			MenuPrincipalController.getInstance().setCenter(nodo);
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

}
