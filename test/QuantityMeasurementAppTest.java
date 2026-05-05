import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void testFeetResult() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(2.0, q1.add(q2, QuantityMeasurementApp.LengthUnit.FEET).value);
    }

    @Test
    void testInchResult() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(24.0, q1.add(q2, QuantityMeasurementApp.LengthUnit.INCH).value);
    }

    @Test
    void testYardResult() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        double result = q1.add(q2, QuantityMeasurementApp.LengthUnit.YARD).value;

        assertTrue(Math.abs(result - 0.6667) < 0.01);
    }

    @Test
    void testCmResult() {
        var q1 = new QuantityMeasurementApp.Quantity(2.54, QuantityMeasurementApp.LengthUnit.CM);
        var q2 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.INCH);

        double result = q1.add(q2, QuantityMeasurementApp.LengthUnit.CM).value;

        assertTrue(Math.abs(result - 5.08) < 0.01);
    }

    @Test
    void testNullTarget() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () ->
                q1.add(q1, null));
    }
}