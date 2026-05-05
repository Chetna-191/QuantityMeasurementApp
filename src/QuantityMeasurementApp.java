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

        // UC6: ADD METHOD (IMPORTANT)
        public Quantity add(Quantity other) {

            if (other == null)
                throw new IllegalArgumentException("Other quantity cannot be null");

            double thisFeet = this.unit.toFeet(this.value);
            double otherFeet = other.unit.toFeet(other.value);

            double sumFeet = thisFeet + otherFeet;

            double resultValue = this.unit.fromFeet(sumFeet);

            return new Quantity(resultValue, this.unit);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            Quantity other = (Quantity) obj;

            double thisFeet = this.unit.toFeet(this.value);
            double otherFeet = other.unit.toFeet(other.value);

            return Double.compare(thisFeet, otherFeet) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // UC5: static convert
    public static double convert(double value, LengthUnit source, LengthUnit target) {

        if (source == null || target == null)
            throw new IllegalArgumentException("Units cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        double feetValue = source.toFeet(value);
        return target.fromFeet(feetValue);
    }

    // MAIN METHOD
    public static void main(String[] args) {

        // UC6 ADDITION TESTS

        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);

        System.out.println("1 ft + 12 inch = " + q1.add(q2)); // 2 FEET

        Quantity q3 = new Quantity(12.0, LengthUnit.INCH);
        Quantity q4 = new Quantity(1.0, LengthUnit.FEET);

        System.out.println("12 inch + 1 ft = " + q3.add(q4)); // 24 INCH

        Quantity q5 = new Quantity(1.0, LengthUnit.YARD);
        Quantity q6 = new Quantity(3.0, LengthUnit.FEET);

        System.out.println("1 yard + 3 ft = " + q5.add(q6)); // 2 YARD

        Quantity q7 = new Quantity(2.54, LengthUnit.CM);
        Quantity q8 = new Quantity(1.0, LengthUnit.INCH);

        System.out.println("cm + inch = " + q7.add(q8));
    }
}