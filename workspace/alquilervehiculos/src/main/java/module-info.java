module co.edu.uniquindio.alquilervehiculos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires lombok;
	requires java.logging;

    opens co.edu.uniquindio.alquilervehiculos.application to javafx.fxml, javafx.controls; 
    opens co.edu.uniquindio.alquilervehiculos.controllers to javafx.fxml;
    
    exports co.edu.uniquindio.alquilervehiculos.application;
}
