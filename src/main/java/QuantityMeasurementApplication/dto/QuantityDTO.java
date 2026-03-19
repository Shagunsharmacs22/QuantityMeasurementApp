package QuantityMeasurementApplication.dto;
import jakarta.validation.constraints.*;
import lombok.Data;
public class QuantityDTO {

    @NotNull(message = "Value cannot be null")
    @Positive(message = "Value must be positive")
    private Double value;

    @NotEmpty(message = "Unit name cannot be empty")
    private String unitName;

    @NotEmpty(message = "Measurement type cannot be empty")
    @Pattern(
        regexp = "LengthUnit|VolumeUnit|WeightUnit|TemperatureUnit",
        message = "Measurement type must be one of: LengthUnit, VolumeUnit, WeightUnit, TemperatureUnit"
    )
    private String measurementType;

    @NotEmpty(message = "Operation type cannot be empty")
    @Pattern(
        regexp = "Addition|Subtraction|Comparison",
        message = "Operation type must be one of: Addition, Subtraction, Comparison"
    )
    private String operationType;
    
    @AssertTrue(message = "Invalid combination of value and unitName for the specified measurement type")
    public boolean isValidQuantity() {
        if (value == null || unitName == null || measurementType == null) return true; // @NotNull will handle nulls
        return true;
    }

    public Double getValue() {
        return value;
    }

    public String getUnitName() {
        return unitName;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setValue(Double value) {
        this.value = value;
    }
    

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
    
}