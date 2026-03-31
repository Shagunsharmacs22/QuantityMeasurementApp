package QuantityMeasurementApplication.service;

import QuantityMeasurementApplication.dto.*;

import QuantityMeasurementApplication.dto.QuantityInputDTO;
import QuantityMeasurementApplication.dto.QuantityMeasurementDTO;

import java.util.List;

public interface IQuantityMeasurementService {

	QuantityMeasurementDTO add(QuantityInputDTO input);

	QuantityMeasurementDTO subtract(QuantityInputDTO input);

	Double divide(QuantityInputDTO input);

	QuantityMeasurementDTO convert(QuantityInputDTO input);

	QuantityMeasurementDTO compare(QuantityInputDTO input);

	List<?> getHistory();

	List<?> getByOperation(String operation);
}
