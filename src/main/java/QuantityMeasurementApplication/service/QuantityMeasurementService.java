package QuantityMeasurementApplication.service;

import org.springframework.stereotype.Service;
import java.util.List;

import QuantityMeasurementApplication.dto.QuantityDTO;
import QuantityMeasurementApplication.entity.QuantityMeasurementEntity;
import QuantityMeasurementApplication.repository.QuantityMeasurementRepository;

@Service
public class QuantityMeasurementService {

    private final QuantityMeasurementRepository repository;

    public QuantityMeasurementService(QuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public QuantityMeasurementEntity save(QuantityDTO dto) {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setValue(dto.getValue());
        entity.setUnitName(dto.getUnitName());
        entity.setMeasurementType(dto.getMeasurementType());
        entity.setOperation(dto.getOperationType());
        return repository.save(entity);
    }

    // GET ALL
    public List<QuantityMeasurementEntity> getAll() {
        return repository.findAll();
    }

    // GET BY ID
    public QuantityMeasurementEntity getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quantity not found with id: " + id));
    }

    // GET BY OPERATION
    public List<QuantityMeasurementEntity> getByOperation(String op) {
        return repository.findByOperation(op);
    }

    // FULL UPDATE (PUT)
    public QuantityMeasurementEntity update(Long id, QuantityDTO dto) {
        QuantityMeasurementEntity entity = getById(id); // fetch existing
        entity.setValue(dto.getValue());
        entity.setUnitName(dto.getUnitName());
        entity.setMeasurementType(dto.getMeasurementType());
        entity.setOperation(dto.getOperationType());
        return repository.save(entity);
    }

    // PARTIAL UPDATE (PATCH)
    public QuantityMeasurementEntity partialUpdate(Long id, QuantityDTO dto) {
        QuantityMeasurementEntity entity = getById(id); // fetch existing
        if (dto.getValue() != null) entity.setValue(dto.getValue());
        if (dto.getUnitName() != null) entity.setUnitName(dto.getUnitName());
        if (dto.getMeasurementType() != null) entity.setMeasurementType(dto.getMeasurementType());
        if (dto.getOperationType() != null) entity.setOperation(dto.getOperationType());
        return repository.save(entity);
    }

    // DELETE
    public void delete(Long id) {
        repository.deleteById(id);
    }
}