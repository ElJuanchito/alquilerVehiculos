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

public class AlquiladosEnUnaFechaController {

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
	
	@FXML
    void initialize() {
		resources = UtilsProperties.getInstancia().obtenerRecursos();
		
		lblTitle.setText(resources.getString("AlquiladosEnUnaFecha.lblTitle"));
		lblSeleccionaFecha.setText(resources.getString("AlquiladosEnUnaFecha.lblSeleccionaFecha"));
		colPlaca.setText(resources.getString("AlquiladosEnUnaFecha.colPlaca"));
		colNombre.setText(resources.getString("AlquiladosEnUnaFecha.colNombre"));
		colMarca.setText(resources.getString("AlquiladosEnUnaFecha.colMarca"));
		colModelo.setText(resources.getString("AlquiladosEnUnaFecha.colModelo"));
		colKilometraje.setText(resources.getString("AlquiladosEnUnaFecha.colKilometraje"));
		colPrecio.setText(resources.getString("AlquiladosEnUnaFecha.colPrecio"));
		colAutomatico.setText(resources.getString("AlquiladosEnUnaFecha.colAutomatico"));
		colSillas.setText(resources.getString("AlquiladosEnUnaFecha.colSillas"));
		btnBuscar.setText(resources.getString("AlquiladosEnUnaFecha.btnBuscar"));
		
    }

    @FXML
    void buscarEvent(ActionEvent event) {
    	buscarAction();
    }
    
    private void buscarAction() {
		if(dpFecha.getValue() == null) {
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
