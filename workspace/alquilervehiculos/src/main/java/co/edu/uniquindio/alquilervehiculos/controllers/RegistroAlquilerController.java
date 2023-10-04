package co.edu.uniquindio.alquilervehiculos.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.alquilervehiculos.application.App;
import co.edu.uniquindio.alquilervehiculos.exceptions.AlquilerConParametrosNullException;
import co.edu.uniquindio.alquilervehiculos.exceptions.AlquilerYaExistenteException;
import co.edu.uniquindio.alquilervehiculos.exceptions.ClienteNoExistenteException;
import co.edu.uniquindio.alquilervehiculos.exceptions.VehiculoYaAlquiladoException;
import co.edu.uniquindio.alquilervehiculos.exceptions.VerificarFechasException;
import co.edu.uniquindio.alquilervehiculos.model.Alquiler;
import co.edu.uniquindio.alquilervehiculos.model.Cliente;
import co.edu.uniquindio.alquilervehiculos.model.EmpresaAlquiler;
import co.edu.uniquindio.alquilervehiculos.model.Vehiculo;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class RegistroAlquilerController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnBack;

	@FXML
	private Label lblTitle;

	@FXML
	private Label lblCedula;

	@FXML
	private Label lblPlaca;

	@FXML
	private Label lblFechaAlquiler;

	@FXML
	private Label lblFechaRegreso;

	@FXML
	private TextField txtCedula;

	@FXML
	private Label lblPlacaResultado;

	@FXML
	private DatePicker dtFechaAlquiler;

	@FXML
	private DatePicker dtFechaRegreso;

	@FXML
	private Label lblSeleccionaVehiculo;

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
	private Button btnAlquilar;

	private EmpresaAlquiler empresa = ModelFactoryController.getInstance().getEmpresa();

	private ObservableList<Vehiculo> listaObservable;

	@FXML
	void initialize() {
		resources = UtilsProperties.getInstancia().obtenerRecursos();

		lblTitle.setText(resources.getString("RegistroAlquiler.lblTitle"));
		lblCedula.setText(resources.getString("RegistroAlquiler.lblCedula"));
		lblPlaca.setText(resources.getString("RegistroAlquiler.lblPlaca"));
		lblFechaAlquiler.setText(resources.getString("RegistroAlquiler.lblFechaAlquiler"));
		lblFechaRegreso.setText(resources.getString("RegistroAlquiler.lblFechaRegreso"));
		lblSeleccionaVehiculo.setText(resources.getString("RegistroAlquiler.lblSeleccionaVehiculo"));
		colPlaca.setText(resources.getString("RegistroAlquiler.colPlaca"));
		colNombre.setText(resources.getString("RegistroAlquiler.colNombre"));
		colMarca.setText(resources.getString("RegistroAlquiler.colMarca"));
		colModelo.setText(resources.getString("RegistroAlquiler.colModelo"));
		colKilometraje.setText(resources.getString("RegistroAlquiler.colKilometraje"));
		colPrecio.setText(resources.getString("RegistroAlquiler.colPrecio"));
		colAutomatico.setText(resources.getString("RegistroAlquiler.colAutomatico"));
		colSillas.setText(resources.getString("RegistroAlquiler.colSillas"));
		btnAlquilar.setText(resources.getString("RegistroAlquiler.btnAlquilar"));

		FxUtility.setAsNumberTextfield(txtCedula);

		actualizarTabla();
	}

	@FXML
	void AlquilarEvent(ActionEvent event) {
		alquilarAction();
	}

	private void alquilarAction() {
		Cliente cliente;
		try {
			cliente = empresa.buscarCliente(txtCedula.getText().trim());
		} catch (ClienteNoExistenteException e) {
			new Alert(AlertType.ERROR, e.getMessage()).show();
			return;
		}
		Vehiculo vehiculo = tableVehiculos.getSelectionModel().getSelectedItem();
		if (vehiculo == null) {
			new Alert(AlertType.WARNING, "Debe seleccionar un vehiculo de la tabla").show();
			return;
		}
		Alquiler alquiler = Alquiler.builder().cliente(cliente).vehiculo(vehiculo)
				.fechaAlquiler(dtFechaAlquiler.getValue()).fechaRegreso(dtFechaRegreso.getValue()).build();
		try {
			empresa.agregarAlquiler(alquiler);
		} catch (AlquilerYaExistenteException | AlquilerConParametrosNullException | VehiculoYaAlquiladoException
				| VerificarFechasException e) {
			new Alert(AlertType.ERROR, e.getMessage()).show();
		}
	}

	@FXML
	void backEvent(ActionEvent event) {
		backAction();

	}

	private void backAction() {
		try {
			Node nodo = App.loadFXML("gestionarAlquiler");
			MenuPrincipalController.getInstance().setCenter(nodo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void actualizarTabla() {
		listaObservable = FXCollections.observableList(empresa.obtenerListaVehiculos());
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
