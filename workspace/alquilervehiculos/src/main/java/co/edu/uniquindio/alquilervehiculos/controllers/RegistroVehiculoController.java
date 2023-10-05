package co.edu.uniquindio.alquilervehiculos.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.alquilervehiculos.application.App;
import co.edu.uniquindio.alquilervehiculos.exceptions.VehiculoConNumerosNegativosException;
import co.edu.uniquindio.alquilervehiculos.exceptions.VehiculoConParametrosNullException;
import co.edu.uniquindio.alquilervehiculos.exceptions.VehiculoYaExistenteException;
import co.edu.uniquindio.alquilervehiculos.model.EmpresaAlquiler;
import co.edu.uniquindio.alquilervehiculos.model.Marca;
import co.edu.uniquindio.alquilervehiculos.model.Vehiculo;
import co.edu.uniquindio.alquilervehiculos.utils.FxUtility;
import co.edu.uniquindio.alquilervehiculos.utils.UtilsProperties;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

public class RegistroVehiculoController implements Initializable{

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private RadioButton btnAutomatico;

	@FXML
	private Button btnBack;

	@FXML
	private Button btnImagen;

	@FXML
	private Button btnRegistrar;

	@FXML
	private Label lblAutomatico;

	@FXML
	private Label lblFoto;

	@FXML
	private Label lblKilometraje;

	@FXML
	private Label lblMarca;

	@FXML
	private Label lblModelo;

	@FXML
	private Label lblNombre;

	@FXML
	private Label lblPlaca;

	@FXML
	private Label lblPrecio;

	@FXML
	private Label lblSillas;

	@FXML
	private Label lblTitulo;

	@FXML
	private Label lblTransmision;

	@FXML
	private Spinner<Integer> spinSillas;

	@FXML
	private TextField txtKilometraje;

	@FXML
	private ComboBox<Marca> cbMarca;

	@FXML
	private TextField txtModelo;

	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtPlaca;

	@FXML
	private TextField txtPrecio;

	private String ruta;
	
	private EmpresaAlquiler empresa = ModelFactoryController.getInstance().getEmpresa();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 1);
		spinSillas.setValueFactory(valueFactory);
		
		FxUtility.setAsIntegerTextfield(txtModelo);
		FxUtility.setAsNumberTextfield(txtKilometraje);
		FxUtility.setAsNumberTextfield(txtPrecio);
		
		
		cbMarca.setItems(FXCollections.observableArrayList(Marca.values()));
		
		UtilsProperties.getInstancia().addListener(bundle -> {
			lblTitulo.setText(bundle.getString("RegistroVehiculo.lblTitulo"));
			lblPlaca.setText(bundle.getString("RegistroVehiculo.lblPlaca"));
			lblNombre.setText(bundle.getString("RegistroVehiculo.lblNombre"));
			lblMarca.setText(bundle.getString("RegistroVehiculo.lblMarca"));
			cbMarca.setPromptText(bundle.getString("RegistroVehiculo.cbMarca"));
			lblModelo.setText(bundle.getString("RegistroVehiculo.lblModelo"));
			lblFoto.setText(bundle.getString("RegistroVehiculo.lblFoto"));
			btnImagen.setText(bundle.getString("RegistroVehiculo.btnImagen"));
			lblKilometraje.setText(bundle.getString("RegistroVehiculo.lblKilometraje"));
			lblPrecio.setText(bundle.getString("RegistroVehiculo.lblPrecio"));
			lblTransmision.setText(bundle.getString("RegistroVehiculo.lblTransmision"));
			lblAutomatico.setText(bundle.getString("RegistroVehiculo.lblAutomatico"));
			lblSillas.setText(bundle.getString("RegistroVehiculo.lblSillas"));
			btnRegistrar.setText(bundle.getString("RegistroVehiculo.btnRegistrar"));
		});
	}

	@FXML
	void registrarEvent(ActionEvent event) {
		registrarAction();
	}


	private void registrarAction() {
		Vehiculo vehiculo = Vehiculo.builder()
				.placa(txtPlaca.getText().trim())
				.nombre(txtNombre.getText().trim())
				.marca(cbMarca.getValue())
				.modelo(txtModelo.getText().trim())
				.foto(ruta)
				.kilometraje(Double.valueOf(txtKilometraje.getText().trim()))
				.precio(Double.valueOf(txtPrecio.getText().trim()))
				.esAutomatico(btnAutomatico.isSelected())
				.nSillas(spinSillas.getValue())
				.build();
		
		try {
			empresa.agregarVehiculo(vehiculo);
			ModelFactoryController.getInstance().guardarVehiculos();
			new Alert(AlertType.CONFIRMATION, "vehiculo registrado con exito").show();
		} catch (VehiculoYaExistenteException e) {
			new Alert(AlertType.ERROR, e.getMessage()).show();
		} catch (VehiculoConNumerosNegativosException e) {
			new Alert(AlertType.ERROR, e.getMessage()).show();
		} catch (VehiculoConParametrosNullException e) {
			new Alert(AlertType.ERROR, e.getMessage()).show();
		}
		
		backAction();
	}

	@FXML
	void backEvent(ActionEvent event) {
		backAction();

	}


	private void backAction() {
		try {
			Node nodo = App.loadFXML("gestionarVehiculo");
			MenuPrincipalController.getInstance().setCenter(nodo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void selectImageEvent(ActionEvent event) {
		selectImageAction();
	}

	private void selectImageAction() {
		FileChooser fileChooser = new FileChooser();
		java.io.File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            ruta = file.getAbsolutePath();
        }
	}
}
