class QuantityMeasurementApp {

    static class Quantity {

        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid value");

            this.value = value;
            this.unit = unit;
        }

        // Convert
        public Quantity convertTo(LengthUnit targetUnit) {
            double base = unit.convertToBaseUnit(value);
            double converted = targetUnit.convertFromBaseUnit(base);
            return new Quantity(converted, targetUnit);
        }

        // Add (default → first unit)
        public Quantity add(Quantity other) {
            return add(other, this.unit);
        }

        // UC7 + UC8 add
        public Quantity add(Quantity other, LengthUnit targetUnit) {

            if (other == null || targetUnit == null)
                throw new IllegalArgumentException("Invalid input");

            double sumBase =
                    this.unit.convertToBaseUnit(this.value) +
                            other.unit.convertToBaseUnit(other.value);

            double result = targetUnit.convertFromBaseUnit(sumBase);

            return new Quantity(result, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;

            return Double.compare(
                    this.unit.convertToBaseUnit(this.value),
                    other.unit.convertToBaseUnit(other.value)
            ) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    public static void main(String[] args) {

        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);

        System.out.println(q1.convertTo(LengthUnit.INCH)); // 12
        System.out.println(q1.add(q2, LengthUnit.FEET));   // 2 FEET
        System.out.println(q1.add(q2, LengthUnit.YARD));   // 0.667

        Quantity q3 = new Quantity(36.0, LengthUnit.INCH);
        Quantity q4 = new Quantity(1.0, LengthUnit.YARD);

        System.out.println(q3.equals(q4)); // true
    }
}