package co.edu.uniquindio.alquilervehiculos.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.alquilervehiculos.model.EmpresaAlquiler;
import co.edu.uniquindio.alquilervehiculos.model.Factura;
import co.edu.uniquindio.alquilervehiculos.utils.FxUtility;
import co.edu.uniquindio.alquilervehiculos.utils.UtilsProperties;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class GestionarFacturaController implements Initializable {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label lblTitle;

	@FXML
	private TextField txtBuscar;

	@FXML
	private TableView<Factura> tablaAlquileres;

	@FXML
	private TableColumn<Factura, String> colId;

	@FXML
	private TableColumn<Factura, String> colFecha;

	@FXML
	private TableColumn<Factura, String> colVehiculo;

	@FXML
	private TableColumn<Factura, String> colCliente;

	@FXML
	private TableColumn<Factura, String> colCosto;

	private EmpresaAlquiler empresa = ModelFactoryController.getInstance().getEmpresa();

	private ObservableList<Factura> listaObservable;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		FxUtility.setAsNumberTextfield(txtBuscar);

		txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.isEmpty()) {
				actualizarTabla(Long.getLong(newValue));
				return;
			}
			actualizarTabla(Long.getLong(newValue));
		});

		UtilsProperties.getInstancia().addListener(bundle -> {
			lblTitle.setText(bundle.getString("GestionarFactura.lblTitle"));
			txtBuscar.setPromptText(bundle.getString("GestionarFactura.txtBuscar"));
			colId.setText(bundle.getString("GestionarFactura.colId"));
			colFecha.setText(bundle.getString("GestionarFactura.colFecha"));
			colVehiculo.setText(bundle.getString("GestionarFactura.colVehiculo"));
			colCliente.setText(bundle.getString("GestionarFactura.colCliente"));
			colCosto.setText(bundle.getString("GestionarFactura.colCosto"));
		});
	}

	private void actualizarTabla(Long id) {
		listaObservable = FXCollections.observableList(empresa.obtenerFacturasFiltradas(id));
		tablaAlquileres.setItems(listaObservable);
		colId.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getId().toString()));
		colFecha.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getFecha().toString()));
		colCliente.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCliente().getCedula()));
		colVehiculo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getVehiculo().getPlaca()));
		colCosto.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCosto().toString()));
		tablaAlquileres.refresh();
	}
}
