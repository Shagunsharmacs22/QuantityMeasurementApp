package QuantityMeasurementApplication.service;

import QuantityMeasurementApplication.dto.QuantityDTO;
import QuantityMeasurementApplication.entity.QuantityMeasurementEntity;
import QuantityMeasurementApplication.exception.QuantityMeasurementException;
import QuantityMeasurementApplication.model.Quantity;
import QuantityMeasurementApplication.repository.IQuantityMeasurementRepository;
import QuantityMeasurementApplication.units.*;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    // ✅ Helper method
    private Quantity getQuantity(QuantityDTO dto) {

        switch (dto.getMeasurement()) {

            case "LENGTH":
                return new Quantity(dto.getValue(),
                        LengthUnit.valueOf(dto.getUnit()));

            case "WEIGHT":
                return new Quantity(dto.getValue(),
                        WeightUnit.valueOf(dto.getUnit()));

            case "VOLUME":
                return new Quantity(dto.getValue(),
                        VolumeUnit.valueOf(dto.getUnit()));

            case "TEMPERATURE":
                return new Quantity(dto.getValue(),
                        TemperatureUnit.valueOf(dto.getUnit()));

            default:
                throw new QuantityMeasurementException("Invalid measurement type");
        }
    }

    // ✅ COMPARE
    @Override
    public QuantityMeasurementEntity compare(QuantityDTO dto1, QuantityDTO dto2) {

        Quantity qty1 = getQuantity(dto1);
        Quantity qty2 = getQuantity(dto2);
        boolean isEqual = qty1.equals(qty2); 

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setValue(isEqual ? 1 : 0); // 1 = equal, 0 = not equal
        entity.setUnit(dto1.getUnit());
        entity.setType(dto1.getMeasurement());
        entity.setOperation("COMPARE");

        repository.save(entity);

        return entity;
    }

    // ✅ CONVERT
    @Override
    public QuantityMeasurementEntity convert(QuantityDTO dto, String targetUnit) {

        Quantity qty = getQuantity(dto);

        Quantity result;

        switch (dto.getMeasurement()) {

            case "LENGTH":
                result = qty.convertTo(LengthUnit.valueOf(targetUnit));
                break;

            case "WEIGHT":
                result = qty.convertTo(WeightUnit.valueOf(targetUnit));
                break;

            case "VOLUME":
                result = qty.convertTo(VolumeUnit.valueOf(targetUnit));
                break;

            case "TEMPERATURE":
                result = qty.convertTo(TemperatureUnit.valueOf(targetUnit));
                break;

            default:
                throw new QuantityMeasurementException("Invalid conversion type");
        }

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setValue(result.getValue());
        entity.setUnit(targetUnit);
        entity.setType(dto.getMeasurement());
        entity.setOperation("CONVERT");

        repository.save(entity);

        return entity;
    }

    // ✅ ADD
    @Override
    public QuantityMeasurementEntity add(QuantityDTO q1, QuantityDTO q2) {

        Quantity qty1 = getQuantity(q1);
        Quantity qty2 = getQuantity(q2);

        Quantity result = qty1.add(qty2);

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setValue(result.getValue());
        entity.setUnit(result.getUnit().toString());
        entity.setType(q1.getMeasurement());
        entity.setOperation("ADD");

        repository.save(entity);

        return entity;
    }

    // ✅ SUBTRACT
    @Override
    public QuantityMeasurementEntity subtract(QuantityDTO q1, QuantityDTO q2) {

        Quantity qty1 = getQuantity(q1);
        Quantity qty2 = getQuantity(q2);

        Quantity result = qty1.subtract(qty2);

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setValue(result.getValue());
        entity.setUnit(result.getUnit().toString());
        entity.setType(q1.getMeasurement());
        entity.setOperation("SUBTRACT");

        repository.save(entity);

        return entity;
    }

    // ✅ DIVIDE
    @Override
    public QuantityMeasurementEntity divide(QuantityDTO q1, QuantityDTO q2) {

        Quantity qty1 = getQuantity(q1);
        Quantity qty2 = getQuantity(q2);

        if (qty2.getValue() == 0) {
            throw new QuantityMeasurementException("Divide by zero");
        }

        double result = qty1.divide(qty2);

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setValue(result);
        entity.setUnit(q1.getUnit());
        entity.setType(q1.getMeasurement());
        entity.setOperation("DIVIDE");

        repository.save(entity);

        return entity;
    }
}