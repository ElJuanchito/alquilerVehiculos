module co.edu.uniquindio.alquilervehiculos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires lombok;
	requires java.logging;
	requires com.fasterxml.jackson.databind;

    opens co.edu.uniquindio.alquilervehiculos.application to javafx.fxml, javafx.controls; 
    opens co.edu.uniquindio.alquilervehiculos.controllers to javafx.fxml;
    opens co.edu.uniquindio.alquilervehiculos.model to com.fasterxml.jackson.databind;
    
    exports co.edu.uniquindio.alquilervehiculos.application;
}
