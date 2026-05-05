class QuantityMeasurementApp {

    interface IMeasurable {
        double toBase(double value);
        double fromBase(double baseValue);
    }

    // LENGTH ENUM
    enum LengthUnit implements IMeasurable {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CM(0.0328084);

        private final double factor;

        LengthUnit(double factor) {
            this.factor = factor;
        }

        public double toBase(double value) {
            return value * factor;
        }

        public double fromBase(double baseValue) {
            return baseValue / factor;
        }
    }

    // 🚀 Quantity Class (UC13 DRY)
    static class Quantity<U extends IMeasurable> {

        private final double value;
        private final U unit;

        public Quantity(double value, U unit) {
            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid value");

            this.value = value;
            this.unit = unit;
        }

        // 🔥 ENUM FOR OPERATIONS
        private enum Operation {
            ADD((a, b) -> a + b),
            SUBTRACT((a, b) -> a - b),
            DIVIDE((a, b) -> {
                if (b == 0) throw new ArithmeticException("Divide by zero");
                return a / b;
            });

            interface Op {
                double apply(double a, double b);
            }

            private final Op op;

            Operation(Op op) {
                this.op = op;
            }

            public double compute(double a, double b) {
                return op.apply(a, b);
            }
        }

        // 🔒 VALIDATION (DRY)
        private void validate(Quantity<U> other, U target, boolean requireTarget) {

            if (other == null)
                throw new IllegalArgumentException("Other cannot be null");

            if (!unit.getClass().equals(other.unit.getClass()))
                throw new IllegalArgumentException("Different measurement category");

            if (!Double.isFinite(other.value))
                throw new IllegalArgumentException("Invalid value");

            if (requireTarget && target == null)
                throw new IllegalArgumentException("Target unit required");
        }

        // 🔥 CORE HELPER (MAIN DRY LOGIC)
        private double performBaseArithmetic(Quantity<U> other, Operation op) {

            double base1 = unit.toBase(value);
            double base2 = other.unit.toBase(other.value);

            return op.compute(base1, base2);
        }

        private double round(double val) {
            return Math.round(val * 100.0) / 100.0;
        }

        // ✅ ADD
        public Quantity<U> add(Quantity<U> other) {
            validate(other, unit, false);
            double base = performBaseArithmetic(other, Operation.ADD);
            double result = unit.fromBase(base);
            return new Quantity<>(round(result), unit);
        }

        public Quantity<U> add(Quantity<U> other, U target) {
            validate(other, target, true);
            double base = performBaseArithmetic(other, Operation.ADD);
            double result = target.fromBase(base);
            return new Quantity<>(round(result), target);
        }

        // ✅ SUBTRACT
        public Quantity<U> subtract(Quantity<U> other) {
            validate(other, unit, false);
            double base = performBaseArithmetic(other, Operation.SUBTRACT);
            double result = unit.fromBase(base);
            return new Quantity<>(round(result), unit);
        }

        public Quantity<U> subtract(Quantity<U> other, U target) {
            validate(other, target, true);
            double base = performBaseArithmetic(other, Operation.SUBTRACT);
            double result = target.fromBase(base);
            return new Quantity<>(round(result), target);
        }

        // ✅ DIVIDE
        public double divide(Quantity<U> other) {
            validate(other, null, false);
            return performBaseArithmetic(other, Operation.DIVIDE);
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // 🚀 MAIN METHOD
    public static void main(String[] args) {

        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCH);

        System.out.println(q1.add(q2));                    // ADD
        System.out.println(q1.subtract(q2));               // SUBTRACT
        System.out.println(q1.divide(q2));                 // DIVIDE

        System.out.println(q1.subtract(q2, LengthUnit.INCH)); // TARGET
    }
}