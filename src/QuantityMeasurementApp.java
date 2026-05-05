class QuantityMeasurementApp {

    // STEP A: ENUM (all units)
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

    // STEP B: Quantity Class
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

        // Convert object to another unit
        public Quantity convertTo(LengthUnit target) {
            double feetValue = unit.toFeet(value);
            double converted = target.fromFeet(feetValue);
            return new Quantity(converted, target);
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

    // STEP C: STATIC CONVERT METHOD (UC5 MAIN FEATURE)
    public static double convert(double value, LengthUnit source, LengthUnit target) {

        if (source == null || target == null)
            throw new IllegalArgumentException("Units cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        double feetValue = source.toFeet(value);
        return target.fromFeet(feetValue);
    }

    // STEP D: MAIN METHOD (testing)
    public static void main(String[] args) {

        System.out.println(convert(1.0, LengthUnit.FEET, LengthUnit.INCH));   // 12
        System.out.println(convert(3.0, LengthUnit.YARD, LengthUnit.FEET));   // 9
        System.out.println(convert(36.0, LengthUnit.INCH, LengthUnit.YARD));  // 1
        System.out.println(convert(1.0, LengthUnit.CM, LengthUnit.INCH));     // 0.393701

        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);

        System.out.println("Equality: " + q1.equals(q2));
    }
}