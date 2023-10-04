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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class VehiculosDisponiblesEnFechasController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblFechaInicial;

    @FXML
    private Label lblFechaFinal;

    @FXML
    private Button btnBuscar;

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
	private DatePicker dpFinal;

	@FXML
	private DatePicker dpInicial;
    
    private EmpresaAlquiler empresa = ModelFactoryController.getInstance().getEmpresa();

	private ObservableList<Vehiculo> listaObservable;
    
    @FXML
    void initialize() {
    	resources = UtilsProperties.getInstancia().obtenerRecursos();
    	
    	lblTitle.setText(resources.getString("VehiculosDiponiblesEnFechas.lblTitle"));
    	lblFechaInicial.setText(resources.getString("VehiculosDiponiblesEnFechas.lblFechaInicial"));
    	lblFechaFinal.setText(resources.getString("VehiculosDiponiblesEnFechas.lblFechaFinal"));
    	btnBuscar.setText(resources.getString("VehiculosDiponiblesEnFechas.btnBuscar"));
    	colPlaca.setText(resources.getString("VehiculosDiponiblesEnFechas.colPlaca"));
		colNombre.setText(resources.getString("VehiculosDiponiblesEnFechas.colNombre"));
		colMarca.setText(resources.getString("VehiculosDiponiblesEnFechas.colMarca"));
		colModelo.setText(resources.getString("VehiculosDiponiblesEnFechas.colModelo"));
		colKilometraje.setText(resources.getString("VehiculosDiponiblesEnFechas.colKilometraje"));
		colPrecio.setText(resources.getString("VehiculosDiponiblesEnFechas.colPrecio"));
		colAutomatico.setText(resources.getString("VehiculosDiponiblesEnFechas.colAutomatico"));
		colSillas.setText(resources.getString("VehiculosDiponiblesEnFechas.colSillas"));
    	
    }

    @FXML
    void buscarEvent(ActionEvent event) {
    	buscarAction();
    }

	private void buscarAction() {
		if (dpInicial.getValue() == null) {
			new Alert(AlertType.WARNING, "Ingrese una fecha inicial").show();
			return;
		}
		if (dpFinal.getValue() == null) {
			new Alert(AlertType.WARNING, "Ingrese una fecha final").show();
			return;
		}
		if (dpInicial.getValue().isBefore(dpFinal.getValue())) {
			new Alert(AlertType.WARNING, "La fecha final es menor a la fecha inicial").show();
			return;
		}
		actualizarTabla(dpInicial.getValue(), dpFinal.getValue());
	}
	
	private void actualizarTabla(LocalDate inicio, LocalDate fin) {
		listaObservable = FXCollections.observableList(empresa.vehiculosDisponiblesEnRangoFechas(inicio, fin));
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
