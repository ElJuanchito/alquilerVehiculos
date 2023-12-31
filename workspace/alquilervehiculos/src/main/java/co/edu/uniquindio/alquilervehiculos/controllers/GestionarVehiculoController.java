package co.edu.uniquindio.alquilervehiculos.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.alquilervehiculos.application.App;
import co.edu.uniquindio.alquilervehiculos.exceptions.VehiculoNoExistenteException;
import co.edu.uniquindio.alquilervehiculos.model.EmpresaAlquiler;
import co.edu.uniquindio.alquilervehiculos.model.Vehiculo;
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

public class GestionarVehiculoController implements Initializable {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label lblTitle;

	@FXML
	private TextField txtBuscar;

	@FXML
	private TableView<Vehiculo> tableVehiculos;

	@FXML
	private TableColumn<Vehiculo, String> colPlaca;

	@FXML
	private TableColumn<Vehiculo, String> colNombre;

	@FXML
	private TableColumn<Vehiculo, String> colMarca;

	@FXML
	private TableColumn<Vehiculo, String> colModelo;

	@FXML
	private TableColumn<Vehiculo, String> colKilometraje;

	@FXML
	private TableColumn<Vehiculo, String> colPrecio;

	@FXML
	private TableColumn<Vehiculo, String> colAutomatico;

	@FXML
	private TableColumn<Vehiculo, String> colSillas;

	@FXML
	private Button btnEliminar;

	@FXML
	private Button btnRegistrar;

	private EmpresaAlquiler empresa = ModelFactoryController.getInstance().getEmpresa();

	private ObservableList<Vehiculo> listaObservable;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		actualizarTabla("");
		
		txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.isEmpty()) {
				actualizarTabla(newValue);
				return;
			}
			actualizarTabla(newValue);
		});

		UtilsProperties.getInstancia().addListener(bundle -> {
			lblTitle.setText(bundle.getString("GestionarVehiculo.lblTitle"));
			txtBuscar.setPromptText(bundle.getString("GestionarVehiculo.txtBuscar"));
			colPlaca.setText(bundle.getString("GestionarVehiculo.colPlaca"));
			colNombre.setText(bundle.getString("GestionarVehiculo.colNombre"));
			colMarca.setText(bundle.getString("GestionarVehiculo.colMarca"));
			colModelo.setText(bundle.getString("GestionarVehiculo.colModelo"));
			colKilometraje.setText(bundle.getString("GestionarVehiculo.colKilometraje"));
			colPrecio.setText(bundle.getString("GestionarVehiculo.colPrecio"));
			colAutomatico.setText(bundle.getString("GestionarVehiculo.colAutomatico"));
			colSillas.setText(bundle.getString("GestionarVehiculo.colSillas"));
			btnEliminar.setText(bundle.getString("GestionarVehiculo.btnEliminar"));
			btnRegistrar.setText(bundle.getString("GestionarVehiculo.btnRegistrar"));
		});
	}

	@FXML
	void eliminarEvent(ActionEvent event) {
		eliminarAction();
	}

	private void eliminarAction() {
		Vehiculo vehiculito = tableVehiculos.getSelectionModel().getSelectedItem();
		if (vehiculito == null) {
			new Alert(AlertType.INFORMATION, "Debe seleccionar un vehiculo en la tabla para eliminarlo").show();
			return;
		}

		try {
			empresa.eliminarVehiculo(vehiculito.getPlaca());
			ModelFactoryController.getInstance().guardarVehiculos();
			new Alert(AlertType.CONFIRMATION,
					"El vehiculo de placa:" + vehiculito.getPlaca() + "se ha elimiando con exito").show();
		} catch (VehiculoNoExistenteException e) {
			new Alert(AlertType.ERROR, e.getMessage()).show();
		} finally {
			txtBuscar.setText("");
		}

	}

	@FXML
	void registrarEvent(ActionEvent event) {
		try {
			Node nodo = App.loadFXML("registroVehiculo");
			MenuPrincipalController.getInstance().setCenter(nodo);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void actualizarTabla(String placa) {
		listaObservable = FXCollections.observableList(empresa.obtenerVehiculosFiltrados(placa));
		tableVehiculos.setItems(listaObservable);
		colPlaca.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getPlaca()));
		colNombre.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getNombre()));
		colMarca.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getMarca().toString()));
		colModelo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getModelo()));
		colKilometraje.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getKilometraje().toString()));
		colPrecio.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getPrecio().toString()));
		colAutomatico.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getAutomaticoText()));
		colSillas.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getNSillas().toString()));
		tableVehiculos.refresh();
	}
}
