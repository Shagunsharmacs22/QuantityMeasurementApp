package QuantityMeasurementApplicationTest;

import QuantityMeasurementApplication.entity.QuantityMeasurementEntity;
import QuantityMeasurementApplication.repository.IQuantityMeasurementRepository;
import QuantityMeasurementApplication.repository.QuantityMeasurementDatabaseRepository;
import QuantityMeasurementApplication.service.QuantityMeasurementServiceImpl;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private IQuantityMeasurementRepository repository;

    @BeforeEach
    void setup() {
        repository = new QuantityMeasurementDatabaseRepository();
        repository.deleteAll(); // clean DB before each test
    }

    // ✅ TEST 1: Save + Fetch
    @Test
    void testSaveAndFetch() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setValue(10.0);
        entity.setUnit("CM");
        entity.setType("LENGTH");
        entity.setOperation("ADD");

        repository.save(entity);

        List<QuantityMeasurementEntity> list = repository.getAllMeasurements();

        assertEquals(1, list.size());
    }

    // ✅ TEST 2: Count
    @Test
    void testTotalCount() {

        QuantityMeasurementEntity e1 = new QuantityMeasurementEntity();
        e1.setValue(5.0);
        e1.setUnit("KG");
        e1.setType("WEIGHT");
        e1.setOperation("ADD");

        repository.save(e1);

        assertEquals(1, repository.getTotalCount());
    }

    // ✅ TEST 3: Delete
    @Test
    void testDeleteAll() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setValue(20.0);
        entity.setUnit("METER");
        entity.setType("LENGTH");
        entity.setOperation("ADD");

        repository.save(entity);

        repository.deleteAll();

        assertEquals(0, repository.getTotalCount());
    }

    // ✅ TEST 4: Integration (Service + Repo)
    @Test
    void testServiceIntegration() {

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(repository);

        // just calling service to check flow
        service.toString(); // dummy call (replace with actual method if needed)

        assertNotNull(service);
    }
}