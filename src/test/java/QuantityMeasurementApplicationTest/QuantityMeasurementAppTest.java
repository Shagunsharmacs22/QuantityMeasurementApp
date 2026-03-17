package QuantityMeasurementApplicationTest;

import QuantityMeasurementApplication.dto.QuantityDTO;
import QuantityMeasurementApplication.model.Quantity;
import QuantityMeasurementApplication.service.QuantityMeasurementServiceImpl;
import QuantityMeasurementApplication.repository.QuantityMeasurementCacheRepository;
import QuantityMeasurementApplication.units.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private QuantityMeasurementServiceImpl service;

    @BeforeEach
    public void setup() {
    	service = new QuantityMeasurementServiceImpl(
    	        QuantityMeasurementCacheRepository.getInstance());
    }

    // ===============================
    // MODEL TESTS (UC10–UC14)
    // ===============================

    @Test
    public void testEquality_CrossUnit() {

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        assertEquals(inches, feet);
    }

    @Test
    public void testConversion_FeetToInches() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                feet.convertTo(LengthUnit.INCHES);

        assertEquals(new Quantity<>(12.0, LengthUnit.INCHES), result);
    }

    @Test
    public void testAddition_CrossUnit() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = feet.add(inches);

        assertEquals(new Quantity<>(2.0, LengthUnit.FEET), result);
    }

    @Test
    public void testSubtraction_CrossUnit() {

        Quantity<LengthUnit> feet =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = feet.subtract(inches);

        assertEquals(new Quantity<>(9.5, LengthUnit.FEET), result);
    }

    @Test
    public void testDivision_ByZero() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(0.0, LengthUnit.FEET);

        assertThrows(ArithmeticException.class,
                () -> q1.divide(q2));
    }

    // ===============================
    // UC14 TEMPERATURE TESTS
    // ===============================

    @Test
    public void testTemperatureEquality() {

        Quantity<TemperatureUnit> c =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> f =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertEquals(c, f);
    }

    @Test
    public void testTemperatureConversion() {

        Quantity<TemperatureUnit> c =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result =
                c.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT), result);
    }

    @Test
    public void testTemperatureUnsupportedOperation() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(UnsupportedOperationException.class,
                () -> t1.add(t2));
    }

    // ===============================
    // UC15 SERVICE TESTS (IMPORTANT)
    // ===============================

   
  

   
    @Test
    public void testService_Compare() {

        QuantityDTO q1 = new QuantityDTO(12.0, "INCHES", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(1.0, "FEET", "LENGTH");

        var result = service.compare(q1, q2);

        assertEquals("true", result.getResult());
    }

    @Test
    public void testService_DivideByZero() {

        QuantityDTO q1 = new QuantityDTO(10.0, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(0.0, "FEET", "LENGTH");

        assertThrows(RuntimeException.class,
                () -> service.divide(q1, q2));
    }
}