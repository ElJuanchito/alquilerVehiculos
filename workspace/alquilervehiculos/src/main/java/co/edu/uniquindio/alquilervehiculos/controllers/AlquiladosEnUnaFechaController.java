package co.edu.uniquindio.alquilervehiculos.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import co.edu.uniquindio.alquilervehiculos.model.EmpresaAlquiler;
import co.edu.uniquindio.alquilervehiculos.model.Vehiculo;
import co.edu.uniquindio.alquilervehiculos.utils.UtilsProperties;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AlquiladosEnUnaFechaController implements Initializable {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label lblTitle;

	@FXML
	private Label lblSeleccionaFecha;

	@FXML
	private DatePicker dpFecha;

	@FXML
	private TableView<Vehiculo> tableVehiculos;

	@FXML
	private TableColumn<Vehiculo, String> colAutomatico;

	@FXML
	private TableColumn<Vehiculo, String> colKilometraje;

	@FXML
	private TableColumn<Vehiculo, String> colMarca;

	@FXML
	private TableColumn<Vehiculo, String> colModelo;

	@FXML
	private TableColumn<Vehiculo, String> colNombre;

	@FXML
	private TableColumn<Vehiculo, String> colPlaca;

	@FXML
	private TableColumn<Vehiculo, String> colPrecio;

	@FXML
	private TableColumn<Vehiculo, String> colSillas;

	@FXML
	private Button btnBuscar;

	private EmpresaAlquiler empresa = ModelFactoryController.getInstance().getEmpresa();

	private ObservableList<Vehiculo> listaObservable;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		UtilsProperties.getInstancia().addListener(bundle -> {
			lblTitle.setText(bundle.getString("AlquiladosEnUnaFecha.lblTitle"));
			lblSeleccionaFecha.setText(bundle.getString("AlquiladosEnUnaFecha.lblSeleccionaFecha"));
			colPlaca.setText(bundle.getString("AlquiladosEnUnaFecha.colPlaca"));
			colNombre.setText(bundle.getString("AlquiladosEnUnaFecha.colNombre"));
			colMarca.setText(bundle.getString("AlquiladosEnUnaFecha.colMarca"));
			colModelo.setText(bundle.getString("AlquiladosEnUnaFecha.colModelo"));
			colKilometraje.setText(bundle.getString("AlquiladosEnUnaFecha.colKilometraje"));
			colPrecio.setText(bundle.getString("AlquiladosEnUnaFecha.colPrecio"));
			colAutomatico.setText(bundle.getString("AlquiladosEnUnaFecha.colAutomatico"));
			colSillas.setText(bundle.getString("AlquiladosEnUnaFecha.colSillas"));
			btnBuscar.setText(bundle.getString("AlquiladosEnUnaFecha.btnBuscar"));
		});

	}

	@FXML
	void buscarEvent(ActionEvent event) {
		buscarAction();
	}

	private void buscarAction() {
		if (dpFecha.getValue() == null) {
			new Alert(AlertType.WARNING, "Ingrese una fecha valida").show();
			return;
		}
		actualizarTabla(dpFecha.getValue());

	}

	private void actualizarTabla(LocalDate fecha) {
		listaObservable = FXCollections.observableList(empresa.vehiculosAlquiladosEnFecha(fecha));
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
