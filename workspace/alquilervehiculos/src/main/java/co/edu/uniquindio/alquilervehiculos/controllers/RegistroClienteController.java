package co.edu.uniquindio.alquilervehiculos.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.alquilervehiculos.application.App;
import co.edu.uniquindio.alquilervehiculos.exceptions.ClienteConParametrosNullException;
import co.edu.uniquindio.alquilervehiculos.exceptions.ClienteYaExistenteException;
import co.edu.uniquindio.alquilervehiculos.model.Cliente;
import co.edu.uniquindio.alquilervehiculos.model.EmpresaAlquiler;
import co.edu.uniquindio.alquilervehiculos.utils.FxUtility;
import co.edu.uniquindio.alquilervehiculos.utils.UtilsProperties;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

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

	private EmpresaAlquiler empresa = ModelFactoryController.getInstance().getEmpresa();

	@FXML
	void initialize() {
		resources = UtilsProperties.getInstancia().obtenerRecursos();

		lblTitulo.setText(resources.getString("RegistroCliente.lblTitulo"));
		lblCedula.setText(resources.getString("RegistroCliente.lblCedula"));
		lblNombre.setText(resources.getString("RegistroCliente.lblNombre"));
		lblTelefono.setText(resources.getString("RegistroCliente.lblTelefono"));
		lblEmail.setText(resources.getString("RegistroCliente.lblEmail"));
		lblCiudad.setText(resources.getString("RegistroCliente.lblCiudad"));
		lblDireccion.setText(resources.getString("RegistroCliente.lblDireccion"));
		btnRegistrar.setText(resources.getString("RegistroCliente.btnRegistrar"));

		FxUtility.setAsNumberTextfield(txtCedula);
		FxUtility.setAsNameTextField(txtNombre);
		FxUtility.setAsNumberTextfield(txtTelefono);
		FxUtility.setAsNameTextField(txtCiudad);
	}

	@FXML
	void backEvent(ActionEvent event) {
		backAction();

	}

	private void backAction() {
		try {
			Node nodo = App.loadFXML("gestionarCliente");
			MenuPrincipalController.getInstance().setCenter(nodo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void registrarEvent(ActionEvent event) {
		registrarAction();
	}

	private void registrarAction() {
		Cliente cliente = Cliente.builder()
				.cedula(txtCedula.getText().trim())
				.nombre(txtNombre.getText().trim())
				.telefono(txtTelefono.getText().trim())
				.email(txtEmail.getText().trim())
				.ciudad(txtCiudad.getText().trim())
				.direccion(txtDireccion.getText().trim())
				.build();
		try {
			empresa.agregarCliente(cliente);
			new Alert(AlertType.CONFIRMATION, "Cliente agregado con exito").show();
			backAction();
		} catch (ClienteYaExistenteException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		} catch (ClienteConParametrosNullException e) {
			new Alert(AlertType.ERROR, e.getMessage()).show();
		}
	}
}
