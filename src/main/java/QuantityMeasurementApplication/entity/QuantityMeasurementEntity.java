package QuantityMeasurementApplication.entity;
public class QuantityMeasurementEntity {

    private double value;
    private String unit;
    private String type;
    private String operation;

    public QuantityMeasurementEntity() {
		// TODO Auto-generated constructor stub
	}

	// ✅ GETTERS
    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public String getType() {
        return type;
    }

    public String getOperation() {
        return operation;
    }

    // ✅ SETTERS
    public void setValue(double value) {
        this.value = value;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
    @Override
    public String toString() {
        return "QuantityMeasurementEntity{" +
                "value=" + value +
                ", unit='" + unit + '\'' +
                ", type='" + type + '\'' +
                ", operation='" + operation + '\'' +
                '}';
    }
}