package QuantityMeasurementApplication.service;

import QuantityMeasurementApplication.dto.QuantityDTO;
import QuantityMeasurementApplication.entity.QuantityMeasurementEntity;

public interface IQuantityMeasurementService {

    QuantityMeasurementEntity compare(QuantityDTO q1, QuantityDTO q2);

    QuantityMeasurementEntity convert(QuantityDTO q, String targetUnit);

    QuantityMeasurementEntity add(QuantityDTO q1, QuantityDTO q2);

    QuantityMeasurementEntity subtract(QuantityDTO q1, QuantityDTO q2);

    QuantityMeasurementEntity divide(QuantityDTO q1, QuantityDTO q2);
}