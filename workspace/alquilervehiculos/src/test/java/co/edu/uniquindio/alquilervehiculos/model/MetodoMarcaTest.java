package co.edu.uniquindio.alquilervehiculos.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class MetodoMarcaTest {
	public static void main(String[] args) {
		// Crear 10 instancias de la clase Vehiculo
        Vehiculo vehiculo1 = new Vehiculo("ABC123", "Toyota Corolla", Marca.TOYOTA, "2023", "foto1.jpg", 10000.0, 20000.0, true, 5);
        Vehiculo vehiculo2 = new Vehiculo("XYZ456", "Ford Focus", Marca.AUDI, "2023", "foto2.jpg", 8000.0, 18000.0, false, 5);
        Vehiculo vehiculo3 = new Vehiculo("DEF789", "Honda Civic", Marca.FORD, "2023", "foto3.jpg", 9000.0, 19000.0, true, 5);
        Vehiculo vehiculo4 = new Vehiculo("GHI101", "Volkswagen Golf", Marca.TESLA, "2023", "foto4.jpg", 8500.0, 17000.0, true, 5);
        Vehiculo vehiculo5 = new Vehiculo("JKL202", "Chevrolet Malibu", Marca.VOLVO, "2023", "foto5.jpg", 9500.0, 21000.0, false, 5);
        Vehiculo vehiculo6 = new Vehiculo("MNO303", "Nissan Altima", Marca.KIA, "2023", "foto6.jpg", 8800.0, 19500.0, true, 5);
        Vehiculo vehiculo7 = new Vehiculo("PQR404", "BMW 3 Series", Marca.BMW, "2023", "foto7.jpg", 11000.0, 25000.0, true, 5);
        Vehiculo vehiculo8 = new Vehiculo("STU505", "Mercedes-Benz C-Class", Marca.MERCEDES_BENZ, "2023", "foto8.jpg", 12000.0, 28000.0, false, 5);
        Vehiculo vehiculo9 = new Vehiculo("VWX606", "Audi A4", Marca.JEEP, "2023", "foto9.jpg", 11500.0, 26000.0, true, 5);
        Vehiculo vehiculo10 = new Vehiculo("YZA707", "Hyundai Sonata", Marca.HYUNDAI, "2023", "foto10.jpg", 9200.0, 20000.0, true, 5);

        // Crear un Map<Long, Alquiler> y llenarlo con 10 instancias de Alquiler
        Map<Long, Alquiler> mapaAlquileres = new HashMap<>();

        // Crear instancias de Alquiler utilizando los vehículos creados
        Alquiler alquiler1 = new Alquiler(null, vehiculo1, LocalDate.of(2023, 9, 1), LocalDate.of(2023, 9, 5));
        Alquiler alquiler2 = new Alquiler(null, vehiculo2, LocalDate.of(2023, 9, 2), LocalDate.of(2023, 9, 6));
        Alquiler alquiler3 = new Alquiler(null, vehiculo3, LocalDate.of(2023, 9, 3), LocalDate.of(2023, 9, 7));
        Alquiler alquiler4 = new Alquiler(null, vehiculo4, LocalDate.of(2023, 9, 4), LocalDate.of(2023, 9, 8));
        Alquiler alquiler5 = new Alquiler(null, vehiculo5, LocalDate.of(2023, 9, 5), LocalDate.of(2023, 9, 9));
        Alquiler alquiler6 = new Alquiler(null, vehiculo6, LocalDate.of(2023, 9, 6), LocalDate.of(2023, 9, 10));
        Alquiler alquiler7 = new Alquiler(null, vehiculo7, LocalDate.of(2023, 9, 7), LocalDate.of(2023, 9, 11));
        Alquiler alquiler8 = new Alquiler(null, vehiculo8, LocalDate.of(2023, 9, 8), LocalDate.of(2023, 9, 12));
        Alquiler alquiler9 = new Alquiler(null, vehiculo9, LocalDate.of(2023, 9, 9), LocalDate.of(2023, 9, 13));
        Alquiler alquiler10 = new Alquiler(null, vehiculo10, LocalDate.of(2023, 9, 10), LocalDate.of(2023, 9, 14));
        alquiler1.setId(1L);
        alquiler2.setId(2L);
        alquiler3.setId(3L);
        alquiler4.setId(4L);
        alquiler5.setId(5L);
        alquiler6.setId(6L);
        alquiler7.setId(7L);
        alquiler8.setId(8L);
        alquiler9.setId(9L);
        alquiler10.setId(10L);

        // Agregar las instancias de Alquiler al Map
        mapaAlquileres.put(alquiler1.getId(), alquiler1);
        mapaAlquileres.put(alquiler2.getId(), alquiler2);
        mapaAlquileres.put(alquiler3.getId(), alquiler3);
        mapaAlquileres.put(alquiler4.getId(), alquiler4);
        mapaAlquileres.put(alquiler5.getId(), alquiler5);
        mapaAlquileres.put(alquiler6.getId(), alquiler6);
        mapaAlquileres.put(alquiler7.getId(), alquiler7);
        mapaAlquileres.put(alquiler8.getId(), alquiler8);
        mapaAlquileres.put(alquiler9.getId(), alquiler9);
        mapaAlquileres.put(alquiler10.getId(), alquiler10);
        
        Marca marcaMax = obtenerMarcaMasAlquilada(mapaAlquileres);
        System.out.println(marcaMax);
	}
	
	public static Marca obtenerMarcaMasAlquilada(Map<Long, Alquiler> listaAlquileres) {
		Map<Marca, Integer> mapa = new HashMap<Marca, Integer>();
		for (Alquiler alquiler : listaAlquileres.values()) {
			Marca marca = alquiler.getVehiculo().getMarca();
			mapa.put(marca, mapa.getOrDefault(marca, 0) + 1);
			System.out.println("k: " + marca + ", v: " + mapa.get(marca));
		}
		System.out.println(mapa.toString());
		
		if(mapa.values().stream().distinct().limit(2).count() <= 1) return null;
		
		return mapa.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElseGet(null);
	}
	
}
