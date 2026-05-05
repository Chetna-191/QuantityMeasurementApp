class QuantityMeasurementApp {

    // ENUM for all units
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CM(0.393701 / 12.0); // 1 cm → inch → feet

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }
    }

    // Generic Quantity class
    static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            Quantity other = (Quantity) obj;

            double thisInFeet = this.unit.toFeet(this.value);
            double otherInFeet = other.unit.toFeet(other.value);

            return Double.compare(thisInFeet, otherInFeet) == 0;
        }
    }

    // Main method for demo
    public static void main(String[] args) {

        Quantity q1 = new Quantity(1.0, LengthUnit.YARD);
        Quantity q2 = new Quantity(3.0, LengthUnit.FEET);

        System.out.println("Yard vs Feet: " + q1.equals(q2));

        Quantity q3 = new Quantity(1.0, LengthUnit.YARD);
        Quantity q4 = new Quantity(36.0, LengthUnit.INCH);

        System.out.println("Yard vs Inch: " + q3.equals(q4));

        Quantity q5 = new Quantity(1.0, LengthUnit.CM);
        Quantity q6 = new Quantity(0.393701, LengthUnit.INCH);

        System.out.println("CM vs Inch: " + q5.equals(q6));

        Quantity q7 = new Quantity(2.0, LengthUnit.YARD);
        Quantity q8 = new Quantity(6.0, LengthUnit.FEET);

        System.out.println("2 Yard vs 6 Feet: " + q7.equals(q8));
    }
}