package QuantityMeasurementApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

import QuantityMeasurementApplication.entity.QuantityMeasurementEntity;

@Repository
public interface QuantityMeasurementRepository 
        extends JpaRepository<QuantityMeasurementEntity, Long> {
	 List<QuantityMeasurementEntity> findByOperation(String operation);
}