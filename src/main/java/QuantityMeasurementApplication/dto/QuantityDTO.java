package QuantityMeasurementApplication.dto;

public class QuantityDTO {

    private Double value;
    private String unitName;
    private String measurementType;
    private String operationType;

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