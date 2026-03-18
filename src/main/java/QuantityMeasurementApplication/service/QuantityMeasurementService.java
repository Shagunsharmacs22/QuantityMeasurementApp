package QuantityMeasurementApplication.service;

import org.springframework.stereotype.Service;
import java.util.List;

import QuantityMeasurementApplication.dto.QuantityDTO;
import QuantityMeasurementApplication.entity.QuantityMeasurementEntity;
import QuantityMeasurementApplication.repository.QuantityMeasurementRepository;

@Service
public class QuantityMeasurementService {

    private QuantityMeasurementRepository repository;

    public QuantityMeasurementService(QuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    public QuantityMeasurementEntity save(QuantityDTO dto) {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        entity.setValue(dto.getValue());
        entity.setUnitName(dto.getUnitName());
        entity.setMeasurementType(dto.getMeasurementType());
        entity.setOperation(dto.getOperationType());

        return repository.save(entity);
    }

    public List<QuantityMeasurementEntity> getByOperation(String op) {
        return repository.findByOperation(op);
    }
}