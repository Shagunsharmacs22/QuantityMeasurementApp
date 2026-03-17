package QuantityMeasurementApplication.model;

import QuantityMeasurementApplication.units.IMeasurable;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // 🔥 COMMON BASE CONVERSION
    private double toBase() {
        return unit.convertToBaseUnit(value);
    }

    // ================= CONVERSION =================

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double base = toBase();
        double converted = targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(converted, targetUnit);
    }

    // ================= ADD =================

    public Quantity<U> add(Quantity<U> other) {

        validate(other);
        unit.validateOperationSupport("addition");

        double result = this.toBase() + other.toBase();

        return new Quantity<>(unit.convertFromBaseUnit(result), unit);
    }

    // 🔥 ADD WITH TARGET UNIT (NEW)
    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validate(other);
        unit.validateOperationSupport("addition");

        double result = this.toBase() + other.toBase();

        return new Quantity<>(targetUnit.convertFromBaseUnit(result), targetUnit);
    }

    // ================= SUBTRACT =================

    public Quantity<U> subtract(Quantity<U> other) {

        validate(other);
        unit.validateOperationSupport("subtraction");

        double result = this.toBase() - other.toBase();

        return new Quantity<>(unit.convertFromBaseUnit(result), unit);
    }

    // 🔥 SUBTRACT WITH TARGET UNIT (NEW)
    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validate(other);
        unit.validateOperationSupport("subtraction");

        double result = this.toBase() - other.toBase();

        return new Quantity<>(targetUnit.convertFromBaseUnit(result), targetUnit);
    }

    // ================= DIVIDE =================

    public double divide(Quantity<U> other) {

        validate(other);
        unit.validateOperationSupport("division");

        double base1 = this.toBase();
        double base2 = other.toBase();

        if (base2 == 0) {
            throw new ArithmeticException("Division by zero");
        }

        return base1 / base2;
    }

    // ================= EQUALITY =================

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof Quantity<?> other)) return false;

        if (!unit.getClass().equals(other.unit.getClass())) return false;

        double base1 = this.toBase();
        double base2 = ((IMeasurable) other.unit)
                .convertToBaseUnit(other.value);

        double epsilon = 0.0001;

        return Math.abs(base1 - base2) < epsilon;
    }

    // ================= VALIDATION =================

    private void validate(Quantity<U> other) {

        if (other == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }

        if (!this.unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException(
                    "Different measurement types not allowed"
            );
        }
    }

    // ================= STRING =================

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }
}