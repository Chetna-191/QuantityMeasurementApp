import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void testAdd() {
        var q1 = new QuantityMeasurementApp.Quantity<>(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var q2 = new QuantityMeasurementApp.Quantity<>(12.0,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(2.0, q1.add(q2).value);
    }

    @Test
    void testSubtract() {
        var q1 = new QuantityMeasurementApp.Quantity<>(10.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var q2 = new QuantityMeasurementApp.Quantity<>(6.0,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(9.5, q1.subtract(q2).value);
    }

    @Test
    void testDivide() {
        var q1 = new QuantityMeasurementApp.Quantity<>(10.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var q2 = new QuantityMeasurementApp.Quantity<>(2.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(5.0, q1.divide(q2));
    }

    @Test
    void testTargetUnit() {
        var q1 = new QuantityMeasurementApp.Quantity<>(10.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var q2 = new QuantityMeasurementApp.Quantity<>(6.0,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(114.0,
                q1.subtract(q2, QuantityMeasurementApp.LengthUnit.INCH).value);
    }

    @Test
    void testDivideByZero() {
        var q1 = new QuantityMeasurementApp.Quantity<>(10.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var q2 = new QuantityMeasurementApp.Quantity<>(0.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertThrows(ArithmeticException.class, () -> q1.divide(q2));
    }

    @Test
    void testNull() {
        var q1 = new QuantityMeasurementApp.Quantity<>(10.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> q1.add(null));
    }
}