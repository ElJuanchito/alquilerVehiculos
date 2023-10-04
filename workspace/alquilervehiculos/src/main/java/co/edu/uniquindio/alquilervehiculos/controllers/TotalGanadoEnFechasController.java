package co.edu.uniquindio.alquilervehiculos.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TotalGanadoEnFechasController {

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
    private Label lblTotalGanado;

    @FXML
    private Label lblResultado;

    @FXML
    private Button btnCalcular;

    @FXML
    void calcularEvent(ActionEvent event) {

    }

    @FXML
    void initialize() {
    }
}
