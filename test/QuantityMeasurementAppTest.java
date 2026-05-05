import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void testFeetToInch() {
        assertEquals(12.0,
                QuantityMeasurementApp.convert(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH));
    }

    @Test
    void testYardToFeet() {
        assertEquals(9.0,
                QuantityMeasurementApp.convert(3.0,
                        QuantityMeasurementApp.LengthUnit.YARD,
                        QuantityMeasurementApp.LengthUnit.FEET));
    }

    @Test
    void testInchToYard() {
        assertEquals(1.0,
                QuantityMeasurementApp.convert(36.0,
                        QuantityMeasurementApp.LengthUnit.INCH,
                        QuantityMeasurementApp.LengthUnit.YARD));
    }

    @Test
    void testCmToInch() {
        double result = QuantityMeasurementApp.convert(2.54,
                QuantityMeasurementApp.LengthUnit.CM,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(Math.abs(result - 1.0) < 0.0001);
    }

    @Test
    void testZero() {
        assertEquals(0.0,
                QuantityMeasurementApp.convert(0.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH));
    }

    @Test
    void testNegative() {
        assertEquals(-12.0,
                QuantityMeasurementApp.convert(-1.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH));
    }

    @Test
    void testInvalidUnit() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.convert(1.0, null,
                        QuantityMeasurementApp.LengthUnit.FEET));
    }
}