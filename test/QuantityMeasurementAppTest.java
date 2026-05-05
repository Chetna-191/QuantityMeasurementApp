import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void testFeetToInch() {
        var q = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.FEET);
        assertEquals(12.0, q.convertTo(LengthUnit.INCH).value);
    }

    @Test
    void testInchToFeet() {
        var q = new QuantityMeasurementApp.Quantity(12.0, LengthUnit.INCH);
        assertEquals(1.0, q.convertTo(LengthUnit.FEET).value);
    }

    @Test
    void testYardToFeet() {
        var q = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.YARD);
        assertEquals(3.0, q.convertTo(LengthUnit.FEET).value);
    }

    @Test
    void testCmToFeet() {
        var q = new QuantityMeasurementApp.Quantity(30.48, LengthUnit.CM);
        double result = q.convertTo(LengthUnit.FEET).value;
        assertTrue(Math.abs(result - 1.0) < 0.01);
    }

    @Test
    void testEquality() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(12.0, LengthUnit.INCH);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testAdditionFeet() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(12.0, LengthUnit.INCH);

        assertEquals(2.0, q1.add(q2, LengthUnit.FEET).value);
    }

    @Test
    void testAdditionYard() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(12.0, LengthUnit.INCH);

        double result = q1.add(q2, LengthUnit.YARD).value;
        assertTrue(Math.abs(result - 0.6667) < 0.01);
    }

    @Test
    void testNullUnit() {
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityMeasurementApp.Quantity(1.0, null));
    }
}