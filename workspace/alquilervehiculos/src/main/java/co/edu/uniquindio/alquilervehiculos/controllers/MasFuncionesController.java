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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MasFuncionesController implements Initializable{

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
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	UtilsProperties.getInstancia().addListener(bundle -> {
    		lblTitle.setText(bundle.getString("MasFunciones.lblTitle"));
        	btnMarcaMasAlquilada.setText(bundle.getString("MasFunciones.btnMarcaMasAlquilada"));
        	btnAlquiladosEnUnaFecha.setText(bundle.getString("MasFunciones.btnAlquiladosEnUnaFecha"));
        	btnTotalGanadoEnFechas.setText(bundle.getString("MasFunciones.btnTotalGanadoEnFechas"));
        	btnVehiculosDisponiblesEnFechas.setText(bundle.getString("MasFunciones.btnVehiculosDisponiblesEnFechas"));
        	btnIdioma.setText(bundle.getString("MasFunciones.btnIdioma"));
		});
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
    	marcaMasAlquiladaAction();
    }

	private void marcaMasAlquiladaAction() {
		Marca marca = empresa.obtenerMarcaMasAlquilada();
		if(marca != null) {
			new Alert(AlertType.CONFIRMATION, "La marca mas alquilada es" + marca.toString()).show();
		}
    	new Alert(AlertType.WARNING, "No existe ningun registro de alquiler").show();
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
