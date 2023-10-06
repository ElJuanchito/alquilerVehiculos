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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class GestionarFacturaController implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBuscar;

    @FXML
    private TableColumn<Factura, String> colCliente;

    @FXML
    private TableColumn<Factura, String> colCosto;

    @FXML
    private TableColumn<Factura, String> colFecha;

    @FXML
    private TableColumn<Factura, String> colId;

    @FXML
    private TableColumn<Factura, String> colVehiculo;

    @FXML
    private Label lblTitulo;

    @FXML
    private TableView<Factura> tablaFacturas;

    @FXML
    private TextField txtBuscar;
    
    private EmpresaAlquiler empresa = ModelFactoryController.getInstance().getEmpresa();

	private ObservableList<Factura> listaObservable;

    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	
    	actualizarTabla();
    	
		FxUtility.setAsNumberTextfield(txtBuscar);

		txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.isEmpty()) {
				actualizarTabla();
			}
		});

		UtilsProperties.getInstancia().addListener(bundle -> {
			lblTitulo.setText(bundle.getString("GestionarFactura.lblTitulo"));
			txtBuscar.setPromptText(bundle.getString("GestionarFactura.txtBuscar"));
			btnBuscar.setText(bundle.getString("GestionarFactura.btnBuscar"));
			colId.setText(bundle.getString("GestionarFactura.colId"));
			colFecha.setText(bundle.getString("GestionarFactura.colFecha"));
			colCliente.setText(bundle.getString("GestionarFactura.colCliente"));
			colVehiculo.setText(bundle.getString("GestionarFactura.colVehiculo"));
			colCosto.setText(bundle.getString("GestionarFactura.colCosto"));
		});
	}
    
    @FXML
    void buscarEvent(ActionEvent event) {
    	buscarAction();
    }
    
    private void buscarAction() {
    	if (empresa.verificarFactura(Long.valueOf(txtBuscar.getText())))
			actualizarTabla(Long.valueOf(txtBuscar.getText()));
		else {
			new Alert(AlertType.WARNING,"La factura buscada no existe").show();
			txtBuscar.setText("");
		}
    	System.out.println(empresa.getListaFacturas());
    	System.out.println(empresa.obtenerListaFacturas());
		
	}

	private void actualizarTabla(Long id) {
		listaObservable = FXCollections.observableList(empresa.obtenerFacturasFiltradas(id));
		tablaFacturas.setItems(listaObservable);
		colId.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getId().toString()));
		colFecha.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getFecha().toString()));
		colCliente.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCliente().getCedula()));
		colVehiculo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getVehiculo().getPlaca()));
		colCosto.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCosto().toString()));
		tablaFacturas.refresh();
	}
	
	private void actualizarTabla() {
		listaObservable = FXCollections.observableList(empresa.obtenerListaFacturas());
		tablaFacturas.setItems(listaObservable);
		colId.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getId().toString()));
		colFecha.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getFecha().toString()));
		colCliente.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCliente().getCedula()));
		colVehiculo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getVehiculo().getPlaca()));
		colCosto.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCosto().toString()));
		tablaFacturas.refresh();
	}

}
