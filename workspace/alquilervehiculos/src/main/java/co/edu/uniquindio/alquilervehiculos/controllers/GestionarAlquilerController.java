package co.edu.uniquindio.alquilervehiculos.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.alquilervehiculos.application.App;
import co.edu.uniquindio.alquilervehiculos.exceptions.AlquilerNoExistenteException;
import co.edu.uniquindio.alquilervehiculos.model.Alquiler;
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

public class GestionarAlquilerController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label lblTitle;

	@FXML
	private TextField txtBuscar;

	@FXML
	private TableView<Alquiler> tablaAlquileres;

	@FXML
	private TableColumn<Alquiler, String> colId;

	@FXML
	private TableColumn<Alquiler, String> colFactura;

	@FXML
	private TableColumn<Alquiler, String> colCliente;

	@FXML
	private TableColumn<Alquiler, String> colVehiculo;

	@FXML
	private TableColumn<Alquiler, String> colAlquiler;

	@FXML
	private TableColumn<Alquiler, String> colRegreso;

	@FXML
	private Button btnEliminar;

	@FXML
	private Button btnAlquilar;

	private EmpresaAlquiler empresa = ModelFactoryController.getInstance().getEmpresa();

	private ObservableList<Alquiler> listaObservable;

	@FXML
	void initialize() {
		resources = UtilsProperties.getInstancia().obtenerRecursos();
		
		FxUtility.setAsNumberTextfield(txtBuscar);

		lblTitle.setText(resources.getString("GestionarAlquiler.lblTitle"));
		txtBuscar.setText(resources.getString("GestionarAlquiler.txtBuscar"));
		colId.setText(resources.getString("GestionarAlquiler.colId"));
		colFactura.setText(resources.getString("GestionarAlquiler.colFactura"));
		colCliente.setText(resources.getString("GestionarAlquiler.colCliente"));
		colVehiculo.setText(resources.getString("GestionarAlquiler.colVehiculo"));
		colAlquiler.setText(resources.getString("GestionarAlquiler.colAlquiler"));
		btnEliminar.setText(resources.getString("GestionarAlquiler.btnEliminar"));
		btnAlquilar.setText(resources.getString("GestionarAlquiler.btnAlquilar"));

		txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.isEmpty()) {
				actualizarTabla(Long.getLong(newValue));
				return;
			}
			actualizarTabla(Long.getLong(newValue));
		});

	}

	@FXML
	void alquilarEvent(ActionEvent event) {
		try {
			Node nodo = App.loadFXML("validarCliente");
			MenuPrincipalController.getInstance().setCenter(nodo);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void eliminarEvent(ActionEvent event) {
		eliminarAction();
	}

	private void eliminarAction() {
		Alquiler alquilercito = tablaAlquileres.getSelectionModel().getSelectedItem();
		if (alquilercito == null) {
			new Alert(AlertType.INFORMATION, "Debe seleccionar un alquiler en la tabla para eliminarlo").show();
			return;
		}

		try {
			empresa.EliminarAlquiler(alquilercito.getId());
			new Alert(AlertType.CONFIRMATION, "El alquiler de id:" + alquilercito.getId() + "se ha elimiando con exito")
					.show();
		} catch (AlquilerNoExistenteException e) {
			new Alert(AlertType.ERROR, e.getMessage()).show();
		} finally {
			txtBuscar.setText("");
		}

	}

	private void actualizarTabla(Long id) {
		listaObservable = FXCollections.observableList(empresa.obtenerAlquileresFiltrados(id));
		tablaAlquileres.setItems(listaObservable);
		colId.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getId().toString()));
		colFactura.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getId().toString()));
		colCliente.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCliente().getCedula()));
		colVehiculo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getVehiculo().getPlaca()));
		colAlquiler.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getFechaAlquiler().toString()));
		colRegreso.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getFechaRegreso().toString()));
		tablaAlquileres.refresh();
	}
}
