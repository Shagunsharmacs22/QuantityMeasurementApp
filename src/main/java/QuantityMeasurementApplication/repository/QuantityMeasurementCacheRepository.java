package QuantityMeasurementApplication.repository;

import QuantityMeasurementApplication.entity.QuantityMeasurementEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {

    private static QuantityMeasurementCacheRepository instance;

    private List<QuantityMeasurementEntity> cache = new ArrayList<>();

    private QuantityMeasurementCacheRepository() {}

    public static QuantityMeasurementCacheRepository getInstance() {
        if (instance == null) {
            instance = new QuantityMeasurementCacheRepository();
        }
        return instance;
    }

    // ✅ SAVE
    @Override
    public void save(QuantityMeasurementEntity entity) {
        cache.add(entity);
    }

    // ✅ GET ALL
    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {
        return new ArrayList<>(cache);
    }

    // ✅ COUNT
    @Override
    public int getTotalCount() {
        return cache.size();
    }

    // ✅ DELETE ALL
    @Override
    public void deleteAll() {
        cache.clear();
    }

    // ✅ FILTER BY OPERATION
    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByOperation(String operation) {
        return cache.stream()
                .filter(e -> e.getOperation().equalsIgnoreCase(operation))
                .collect(Collectors.toList());
    }

    // ✅ FILTER BY TYPE
    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByType(String type) {
        return cache.stream()
                .filter(e -> e.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }
}