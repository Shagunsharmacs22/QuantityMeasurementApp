package QuantityMeasurementApplication.controller;

import QuantityMeasurementApplication.dto.QuantityDTO;
import QuantityMeasurementApplication.service.IQuantityMeasurementService;

public class QuantityMeasurementController {

    private IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    public void performAddition(QuantityDTO q1, QuantityDTO q2) {
        System.out.println(service.add(q1, q2));
    }

    public void performSubtraction(QuantityDTO q1, QuantityDTO q2) {
        System.out.println(service.subtract(q1, q2));
    }

    public void performComparison(QuantityDTO q1, QuantityDTO q2) {
        System.out.println(service.compare(q1, q2));
    }

    public void performConversion(QuantityDTO q, String targetUnit) {
        System.out.println(service.convert(q, targetUnit));
    }

    public void performDivision(QuantityDTO q1, QuantityDTO q2) {
        System.out.println(service.divide(q1, q2));
    }
}