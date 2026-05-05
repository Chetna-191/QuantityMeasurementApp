import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void testSubtractionFeetInch() {
        var q1 = new Quantity<>(10.0, LengthUnit.FEET);
        var q2 = new Quantity<>(6.0, LengthUnit.INCH);

        assertEquals(9.5, q1.subtract(q2).getValue());
    }

    @Test
    void testSubtractionTargetUnit() {
        var q1 = new Quantity<>(10.0, LengthUnit.FEET);
        var q2 = new Quantity<>(6.0, LengthUnit.INCH);

        assertEquals(114.0, q1.subtract(q2, LengthUnit.INCH).getValue());
    }

    @Test
    void testNegativeSubtraction() {
        var q1 = new Quantity<>(5.0, LengthUnit.FEET);
        var q2 = new Quantity<>(10.0, LengthUnit.FEET);

        assertEquals(-5.0, q1.subtract(q2).getValue());
    }

    @Test
    void testZeroSubtraction() {
        var q1 = new Quantity<>(10.0, LengthUnit.FEET);
        var q2 = new Quantity<>(120.0, LengthUnit.INCH);

        assertEquals(0.0, q1.subtract(q2).getValue());
    }

    @Test
    void testDivisionSameUnit() {
        var q1 = new Quantity<>(10.0, LengthUnit.FEET);
        var q2 = new Quantity<>(2.0, LengthUnit.FEET);

        assertEquals(5.0, q1.divide(q2));
    }

    @Test
    void testDivisionCrossUnit() {
        var q1 = new Quantity<>(24.0, LengthUnit.INCH);
        var q2 = new Quantity<>(2.0, LengthUnit.FEET);

        assertEquals(1.0, q1.divide(q2));
    }

    @Test
    void testDivisionLessThanOne() {
        var q1 = new Quantity<>(5.0, LengthUnit.FEET);
        var q2 = new Quantity<>(10.0, LengthUnit.FEET);

        assertEquals(0.5, q1.divide(q2));
    }

    @Test
    void testDivisionByZero() {
        var q1 = new Quantity<>(10.0, LengthUnit.FEET);
        var q2 = new Quantity<>(0.0, LengthUnit.FEET);

        assertThrows(ArithmeticException.class, () -> q1.divide(q2));
    }

    @Test
    void testNullSubtraction() {
        var q1 = new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> q1.subtract(null));
    }

    @Test
    void testNullDivision() {
        var q1 = new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> q1.divide(null));
    }
}