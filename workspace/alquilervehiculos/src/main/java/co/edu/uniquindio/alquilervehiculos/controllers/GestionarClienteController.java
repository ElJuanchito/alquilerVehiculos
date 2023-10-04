package co.edu.uniquindio.alquilervehiculos.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.alquilervehiculos.application.App;
import co.edu.uniquindio.alquilervehiculos.exceptions.ClienteNoExistenteException;
import co.edu.uniquindio.alquilervehiculos.model.Cliente;
import co.edu.uniquindio.alquilervehiculos.model.EmpresaAlquiler;
import co.edu.uniquindio.alquilervehiculos.utils.FxUtility;
import co.edu.uniquindio.alquilervehiculos.utils.UtilsProperties;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

	private EmpresaAlquiler empresa = ModelFactoryController.getInstance().getEmpresa();

	private ObservableList<Cliente> listaObservable;

	@FXML
	void initialize() {
		resources = UtilsProperties.getInstancia().obtenerRecursos();

		lblTitle.setText(resources.getString("GestionarCliente.lblTitle"));
		txtBuscar.setPromptText(resources.getString("GestionarCliente.txtBuscar"));
		colCedula.setText(resources.getString("GestionarCliente.colCedula"));
		colNombre.setText(resources.getString("GestionarCliente.colNombre"));
		colTelefono.setText(resources.getString("GestionarCliente.colTelefono"));
		colEmail.setText(resources.getString("GestionarCliente.colEmail"));
		colCiudad.setText(resources.getString("GestionarCliente.colCiudad"));
		colDireccion.setText(resources.getString("GestionarCliente.colDireccion"));
		btnEliminar.setText(resources.getString("GestionarCliente.btnEliminar"));
		btnRegistrar.setText(resources.getString("GestionarCliente.btnRegistrar"));

		txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.isEmpty()) {
				actualizarTabla(newValue);
				return;
			}
			actualizarTabla(newValue);
		});
		FxUtility.setAsNumberTextfield(txtBuscar);
	}

	@FXML
	void eliminarEvent(ActionEvent event) {
		eliminarAction();
	}

	private void eliminarAction() {
		Cliente clientecito = tableClientes.getSelectionModel().getSelectedItem();
		if (clientecito == null) {
			new Alert(AlertType.INFORMATION, "Debe seleccionar un cliente en la tabla para eliminarlo").show();
			return;
		}

		try {
			empresa.eliminarCliente(clientecito.getCedula());
			new Alert(AlertType.CONFIRMATION,
					"El cliente de cedula:" + clientecito.getCedula() + "se ha elimiando con exito").show();
		} catch (ClienteNoExistenteException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		} finally {
			txtBuscar.setText("");
		}

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

	private void actualizarTabla(String cedula) {
		listaObservable = FXCollections.observableList(empresa.obtenerClientesFiltrados(cedula));
		tableClientes.setItems(listaObservable);
		colCedula.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCedula()));
		colNombre.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getNombre()));
		colTelefono.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getTelefono()));
		colEmail.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getEmail()));
		colCiudad.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCiudad()));
		colDireccion.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getDireccion()));
		tableClientes.refresh();
	}
}
