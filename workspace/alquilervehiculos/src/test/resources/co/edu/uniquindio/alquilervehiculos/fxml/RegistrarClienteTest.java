import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import co.edu.uniquindio.alquilervehiculos.application.App;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RegistrarClienteTest extends Application {

	private static Scene scene;
	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws IOException {
		scene = new Scene(loadFXML("registrarCliente"));
		stage.setScene(scene);
		stage.show();
	}

	public static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(
				App.class.getResource("/co/edu/uniquindio/alquilervehiculos/fxml/" + fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public void cargarFont() {
		Font.loadFont(App.class.getResource("/co/edu/uniquindio/alquilervehiculos/source/fonts/Heebo-Regular.ttf")
				.toExternalForm(), 600);
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
