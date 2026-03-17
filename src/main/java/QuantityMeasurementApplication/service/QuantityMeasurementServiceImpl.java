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

    // 🔥 helper method (VERY IMPORTANT)
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

    @Override
    public QuantityMeasurementEntity compare(QuantityDTO q1, QuantityDTO q2) {

        Quantity qty1 = getQuantity(q1);
        Quantity qty2 = getQuantity(q2);

        boolean result = qty1.equals(qty2);

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("COMPARE", String.valueOf(result));

        repository.save(entity);
        return entity;
    }

    @Override
    public QuantityMeasurementEntity convert(QuantityDTO q, String targetUnit) {

        Quantity qty = getQuantity(q);
        Quantity result;

        switch (q.getMeasurement()) {

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
                throw new QuantityMeasurementException("Invalid measurement type");
        }

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("CONVERT", result.toString());

        repository.save(entity);
        return entity;
    }
    @Override
    public QuantityMeasurementEntity add(QuantityDTO q1, QuantityDTO q2) {

        Quantity qty1 = getQuantity(q1);
        Quantity qty2 = getQuantity(q2);

        Quantity result = qty1.add(qty2);

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("ADD", result.toString());

        repository.save(entity);
        return entity;
    }

    @Override
    public QuantityMeasurementEntity subtract(QuantityDTO q1, QuantityDTO q2) {

        Quantity qty1 = getQuantity(q1);
        Quantity qty2 = getQuantity(q2);

        Quantity result = qty1.subtract(qty2);

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("SUBTRACT", result.toString());

        repository.save(entity);
        return entity;
    }

    @Override
    public QuantityMeasurementEntity divide(QuantityDTO q1, QuantityDTO q2) {

        Quantity qty1 = getQuantity(q1);
        Quantity qty2 = getQuantity(q2);

        if (qty2.getValue() == 0) {
            throw new QuantityMeasurementException("Divide by zero");
        }

        double result = qty1.divide(qty2);

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("DIVIDE", String.valueOf(result));

        repository.save(entity);
        return entity;
    }
}