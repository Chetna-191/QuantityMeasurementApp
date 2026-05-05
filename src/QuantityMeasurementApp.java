class QuantityMeasurementApp {

    // ENUM
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CM(0.393701 / 12.0);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

    // Quantity Class
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

        // UC5: convert
        public Quantity convertTo(LengthUnit target) {
            double feetValue = unit.toFeet(value);
            double converted = target.fromFeet(feetValue);
            return new Quantity(converted, target);
        }

        // UC6: add (default → first unit)
        public Quantity add(Quantity other) {
            if (other == null)
                throw new IllegalArgumentException("Other cannot be null");

            double sumFeet = this.unit.toFeet(this.value)
                    + other.unit.toFeet(other.value);

            double result = this.unit.fromFeet(sumFeet);
            return new Quantity(result, this.unit);
        }

        // 🚀 UC7: add with TARGET UNIT (MAIN FEATURE)
        public Quantity add(Quantity other, LengthUnit targetUnit) {

            if (other == null || targetUnit == null)
                throw new IllegalArgumentException("Invalid input");

            double sumFeet = this.unit.toFeet(this.value)
                    + other.unit.toFeet(other.value);

            double result = targetUnit.fromFeet(sumFeet);

            return new Quantity(result, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;

            return Double.compare(
                    this.unit.toFeet(this.value),
                    other.unit.toFeet(other.value)
            ) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // MAIN METHOD
    public static void main(String[] args) {

        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);

        System.out.println(q1.add(q2, LengthUnit.FEET));   // 2 FEET
        System.out.println(q1.add(q2, LengthUnit.INCH));   // 24 INCH
        System.out.println(q1.add(q2, LengthUnit.YARD));   // ~0.667 YARD

        Quantity q3 = new Quantity(1.0, LengthUnit.YARD);
        Quantity q4 = new Quantity(3.0, LengthUnit.FEET);

        System.out.println(q3.add(q4, LengthUnit.FEET));   // 6 FEET

        Quantity q5 = new Quantity(2.54, LengthUnit.CM);
        Quantity q6 = new Quantity(1.0, LengthUnit.INCH);

        System.out.println(q5.add(q6, LengthUnit.CM));     // ~5.08 CM
    }
}