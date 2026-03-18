package QuantityMeasurementApplication;

import QuantityMeasurementApplication.controller.QuantityMeasurementController;
import QuantityMeasurementApplication.dto.QuantityDTO;
import QuantityMeasurementApplication.repository.*;
import QuantityMeasurementApplication.service.QuantityMeasurementServiceImpl;
import QuantityMeasurementApplication.util.DatabaseInitializer;

public class QuantityMeasurementApp {

	public static void main(String[] args) {

		// 🔥 INIT TABLE
		DatabaseInitializer.initialize();

		IQuantityMeasurementRepository repo = new QuantityMeasurementDatabaseRepository();

		QuantityMeasurementServiceImpl service = new QuantityMeasurementServiceImpl(repo);

		QuantityMeasurementController controller = new QuantityMeasurementController(service);

		// ================= BASIC OPERATIONS =================

		QuantityDTO t1 = new QuantityDTO(0.0, "CELSIUS", "TEMPERATURE");
		QuantityDTO t2 = new QuantityDTO(32.0, "FAHRENHEIT", "TEMPERATURE");

		System.out.println("🔹 Equality:");
		controller.performComparison(t1, t2);

		System.out.println("\n🔹 Conversion:");
		controller.performConversion(new QuantityDTO(100.0, "CELSIUS", "TEMPERATURE"), "FAHRENHEIT");

		// Unsupported operation
		System.out.println("\n🔹 Unsupported Operation:");
		try {
			controller.performAddition(new QuantityDTO(100.0, "CELSIUS", "TEMPERATURE"),
					new QuantityDTO(50.0, "CELSIUS", "TEMPERATURE"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// ================= DATABASE FEATURES =================

		System.out.println("\n🔹 Total Records:");
		System.out.println(repo.getTotalCount());

		System.out.println("\n🔹 All Records:");
		repo.getAllMeasurements().forEach(System.out::println);

		System.out.println("\n🔹 Filter by Operation = COMPARE:");
		repo.getMeasurementsByOperation("COMPARE").forEach(System.out::println);

		System.out.println("\n🔹 Filter by Type = TEMPERATURE:");
		repo.getMeasurementsByType("TEMPERATURE").forEach(System.out::println);

		// ================= DELETE =================

//		System.out.println("\n🔹 Deleting All Records...");
//		repo.deleteAll();

		System.out.println("After Delete Count:");
		System.out.println(repo.getTotalCount());
		System.out.println("\n🔹 DB DATA CHECK:");
		repo.getAllMeasurements().forEach(System.out::println);
	}
}