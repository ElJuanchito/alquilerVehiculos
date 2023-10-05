package co.edu.uniquindio.alquilervehiculos.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.alquilervehiculos.model.EmpresaAlquiler;
import co.edu.uniquindio.alquilervehiculos.utils.UtilsProperties;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class TotalGanadoEnFechasController implements Initializable{

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnCalcular;

	@FXML
	private DatePicker dpFinal;

	@FXML
	private DatePicker dpInicial;

	@FXML
	private Label lblFechaFinal;

	@FXML
	private Label lblFechaInicial;

	@FXML
	private Label lblResultado;

	@FXML
	private Label lblTitle;

	@FXML
	private Label lblTotalGanado;

	private EmpresaAlquiler empresa = ModelFactoryController.getInstance().getEmpresa();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		UtilsProperties.getInstancia().addListener(bundle -> {
			lblTitle.setText(bundle.getString("TotalGanadoEnFechas.lblTitle"));
			lblFechaInicial.setText(bundle.getString("TotalGanadoEnFechas.lblFechaInicial"));
			lblFechaFinal.setText(bundle.getString("TotalGanadoEnFechas.lblFechaFinal"));
			lblTotalGanado.setText(bundle.getString("TotalGanadoEnFechas.lblTotalGanado"));
			btnCalcular.setText(bundle.getString("TotalGanadoEnFechas.btnCalcular"));
		});
	}

	@FXML
	void calcularEvent(ActionEvent event) {
		calcularAction();
	}

	private void calcularAction() {
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
		String resultado = Double
				.valueOf(empresa.TotalGanadoPorAlquileresEnRangoFechas(dpInicial.getValue(), dpFinal.getValue()))
				.toString();
		lblResultado.setText(resultado);
	}
}
