package co.edu.uniquindio.alquilervehiculos.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import co.edu.uniquindio.alquilervehiculos.model.Alquiler;
import co.edu.uniquindio.alquilervehiculos.model.Cliente;
import co.edu.uniquindio.alquilervehiculos.model.Factura;
import co.edu.uniquindio.alquilervehiculos.model.Vehiculo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UtilsPersistence {

	private final String RUTA = "src/main/resources/META-INF/persistencia/datos_";

	private static UtilsPersistence instancia;

	@AllArgsConstructor
	public enum Archivo {
		CLIENTES("clientes.txt"), VEHICULOS("vehiculos.txt"), ALQUILERES("alquileres.txt"), FACTURAS("facturas.txt");

		@Getter
		private final String texto;
	}

	public static UtilsPersistence getInstancia() {
		if (instancia == null)
			instancia = new UtilsPersistence();
		return instancia;
	}

	public void guardar(Object ob, Archivo archivo) {

		try (FileOutputStream out = new FileOutputStream(RUTA + archivo.getTexto());
				ObjectOutputStream obOut = new ObjectOutputStream(out)) {
			obOut.writeObject(ob);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Object leer(Archivo archivo) {
		try (FileInputStream fileIn = new FileInputStream(RUTA + archivo.getTexto());
				ObjectInputStream obOut = new ObjectInputStream(fileIn)) {
			return obOut.readObject();
		} catch (FileNotFoundException e) {
			switch (archivo) {
			case CLIENTES:
				guardar(new HashMap<String, Cliente>(), Archivo.CLIENTES);

			case VEHICULOS:
				guardar(new HashMap<String, Vehiculo>(), Archivo.VEHICULOS);

			case ALQUILERES:
				guardar(new HashMap<Long, Alquiler>(), Archivo.ALQUILERES);

			case FACTURAS:
				guardar(new HashMap<Long, Factura>(), Archivo.FACTURAS);
			default:
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
