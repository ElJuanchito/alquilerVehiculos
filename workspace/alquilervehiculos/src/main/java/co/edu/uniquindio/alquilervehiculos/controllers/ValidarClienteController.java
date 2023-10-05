package co.edu.uniquindio.alquilervehiculos.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.alquilervehiculos.application.App;
import co.edu.uniquindio.alquilervehiculos.model.EmpresaAlquiler;
import co.edu.uniquindio.alquilervehiculos.utils.FxUtility;
import co.edu.uniquindio.alquilervehiculos.utils.UtilsProperties;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ValidarClienteController implements Initializable {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnBack;

	@FXML
	private Button btnSiguiente;

	@FXML
	private Button btnValidar;

	@FXML
	private Label lblCedula;

	@FXML
	private Label lblTitulo;

	@FXML
	private TextField txtCedula;

	private EmpresaAlquiler empresa = ModelFactoryController.getInstance().getEmpresa();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		FxUtility.setAsNumberTextfield(txtCedula);

		UtilsProperties.getInstancia().addListener(bundle -> {
			lblTitulo.setText(bundle.getString("ValidarCliente.lblTitulo"));
			lblCedula.setText(bundle.getString("ValidarCliente.lblCedula"));
			btnValidar.setText(bundle.getString("ValidarCliente.btnValidar"));
			btnValidar.setText(bundle.getString("ValidarCliente.btnSiguiente"));
		});
	}

	@FXML
	void backEvent(ActionEvent event) {
		backAction();
	}

	@FXML
	void siguienteEvent(ActionEvent event) {
		siguienteAction();
	}

	@FXML
	void validarEvent(ActionEvent event) {
		validarAction();
	}

	private void validarAction() {
		if (empresa.verificarCliente(txtCedula.getText().trim())) {
			new Alert(AlertType.CONFIRMATION, "El cliente si existe").show();
			btnSiguiente.setVisible(true);
			return;
		}
		new Alert(AlertType.CONFIRMATION, "El cliente no existe").show();
	}

	private void backAction() {
		try {
			Node nodo = App.loadFXML("gestionarAlquiler");
			MenuPrincipalController.getInstance().setCenter(nodo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void siguienteAction() {
		try {
			Node nodo = App.loadFXML("registroAlquiler");
			MenuPrincipalController.getInstance().setCenter(nodo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
