package QuantityMeasurementApplication;

import QuantityMeasurementApplication.controller.QuantityMeasurementController;
import QuantityMeasurementApplication.dto.QuantityDTO;
import QuantityMeasurementApplication.repository.QuantityMeasurementCacheRepository;
import QuantityMeasurementApplication.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // 🔥 Setup (Dependency Injection)
        QuantityMeasurementCacheRepository repo =
                QuantityMeasurementCacheRepository.getInstance();

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(repo);

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        // ================= TEMPERATURE =================

        QuantityDTO t1 =
                new QuantityDTO(0.0, "CELSIUS", "TEMPERATURE");

        QuantityDTO t2 =
                new QuantityDTO(32.0, "FAHRENHEIT", "TEMPERATURE");

        System.out.println("Equality:");
        controller.performComparison(t1, t2);

        System.out.println("\nConversion:");
        controller.performConversion(
                new QuantityDTO(100.0, "CELSIUS", "TEMPERATURE"),
                "FAHRENHEIT"
        );

        System.out.println("\nUnsupported operation:");

        try {
            controller.performAddition(
                    new QuantityDTO(100.0, "CELSIUS", "TEMPERATURE"),
                    new QuantityDTO(50.0, "CELSIUS", "TEMPERATURE")
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}