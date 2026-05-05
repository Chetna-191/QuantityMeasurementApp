import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    private static final double EPSILON = 1e-6;

    public Quantity(double value, U unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (Double.isNaN(value) || Double.isInfinite(value))
            throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // 🔹 Convert to another unit
    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(converted, targetUnit);
    }

    // 🔹 Add (default → first unit)
    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    // 🔹 Add (explicit unit)
    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        if (other == null || targetUnit == null)
            throw new IllegalArgumentException("Invalid input");

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sumBase = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new Quantity<>(result, targetUnit);
    }

    // 🔹 Equality
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Quantity<?> other = (Quantity<?>) obj;

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < EPSILON;
    }

    @Override
    public int hashCode() {
        double baseValue = unit.convertToBaseUnit(value);
        return Objects.hash(Math.round(baseValue / EPSILON));
    }

    @Override
    public String toString() {
        return String.format("Quantity(%.5f, %s)", value, unit.getUnitName());
    }
}
public Quantity<U> subtract(Quantity<U> other) {

    if (other == null)
        throw new IllegalArgumentException("Other cannot be null");

    if (!this.unit.getClass().equals(other.unit.getClass()))
        throw new IllegalArgumentException("Different measurement categories");

    double base1 = unit.convertToBaseUnit(value);
    double base2 = other.unit.convertToBaseUnit(other.value);

    double resultBase = base1 - base2;

    double finalValue = unit.convertFromBaseUnit(resultBase);

    return new Quantity<>(round(finalValue), unit);
}
public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

    if (other == null || targetUnit == null)
        throw new IllegalArgumentException("Invalid input");

    if (!this.unit.getClass().equals(other.unit.getClass()))
        throw new IllegalArgumentException("Different categories");

    double base1 = unit.convertToBaseUnit(value);
    double base2 = other.unit.convertToBaseUnit(other.value);

    double resultBase = base1 - base2;

    double finalValue = targetUnit.convertFromBaseUnit(resultBase);

    return new Quantity<>(round(finalValue), targetUnit);
}
public double divide(Quantity<U> other) {

    if (other == null)
        throw new IllegalArgumentException("Other cannot be null");

    if (!this.unit.getClass().equals(other.unit.getClass()))
        throw new IllegalArgumentException("Different categories");

    double base1 = unit.convertToBaseUnit(value);
    double base2 = other.unit.convertToBaseUnit(other.value);

    if (base2 == 0)
        throw new ArithmeticException("Division by zero");

    return base1 / base2;
}
private double round(double value) {
    return Math.round(value * 100.0) / 100.0;
}