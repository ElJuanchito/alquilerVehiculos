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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class GestionarAlquilerController implements Initializable {

	@FXML
	private Button btnAlquilar;

	@FXML
	private Button btnBuscar;

	@FXML
	private Button btnEliminar;

	@FXML
	private TableColumn<Alquiler, String> colCliente;

	@FXML
	private TableColumn<Alquiler, String> colFAlquiler;

	@FXML
	private TableColumn<Alquiler, String> colFRegreso;

	@FXML
	private TableColumn<Alquiler, String> colId;

	@FXML
	private TableColumn<Alquiler, String> colVehiculo;

	@FXML
	private Label lblTitle;

	@FXML
	private TableView<Alquiler> tablaAlquileres;

	@FXML
	private TextField txtBuscar;

	private ObservableList<Alquiler> listaObservable;

	private EmpresaAlquiler empresa = ModelFactoryController.getInstance().getEmpresa();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		FxUtility.setAsNumberTextfield(txtBuscar);
		
		txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.isEmpty()) {
				actualizarTabla();
			}
		});

		UtilsProperties.getInstancia().addListener(bundle -> {
			lblTitle.setText(bundle.getString("GestionarAlquiler.lblTitle"));
			btnBuscar.setText(bundle.getString("GestionarAlquiler.btnBuscar"));
			colId.setText(bundle.getString("GestionarAlquiler.colId"));
			colFAlquiler.setText(bundle.getString("GestionarAlquiler.colFAlquiler"));
			colCliente.setText(bundle.getString("GestionarAlquiler.colCliente"));
			colVehiculo.setText(bundle.getString("GestionarAlquiler.colVehiculo"));
			colFRegreso.setText(bundle.getString("GestionarAlquiler.colFRegreso"));
			btnEliminar.setText(bundle.getString("GestionarAlquiler.btnEliminar"));
			btnAlquilar.setText(bundle.getString("GestionarAlquiler.btnAlquilar"));
		});
		
		actualizarTabla();
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
	void buscarEvent(ActionEvent event) {
		buscarAction();
	}

	private void buscarAction() {
		if (empresa.verificarAlquiler(Long.valueOf(txtBuscar.getText())))
			actualizarTabla(Long.valueOf(txtBuscar.getText()));
		else {
			new Alert(AlertType.WARNING,"El aquiler buscado no existe").show();
			txtBuscar.setText("");
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
			ModelFactoryController.getInstance().guardarAlquileres();
			new Alert(AlertType.CONFIRMATION, "El alquiler de id:" + alquilercito.getId() + "se ha elimiando con exito")
					.show();
			actualizarTabla();
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
		colCliente.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCliente().getCedula()));
		colVehiculo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getVehiculo().getPlaca()));
		colFAlquiler.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getFechaAlquiler().toString()));
		colFRegreso.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getFechaRegreso().toString()));
		tablaAlquileres.refresh();
	}

	private void actualizarTabla() {
		listaObservable = FXCollections.observableList(empresa.obtenerListaAlquileres());
		tablaAlquileres.setItems(listaObservable);
		colId.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getId().toString()));
		colCliente.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCliente().getCedula()));
		colVehiculo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getVehiculo().getPlaca()));
		colFAlquiler.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getFechaAlquiler().toString()));
		colFRegreso.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getFechaRegreso().toString()));
		tablaAlquileres.refresh();
	}
}
