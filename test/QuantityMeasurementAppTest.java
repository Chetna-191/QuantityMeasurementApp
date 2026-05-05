import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    // FEET TESTS
    @Test
    void testFeet_SameValue() {
        assertTrue(QuantityMeasurementApp.checkFeetEquality(1.0, 1.0));
    }

    @Test
    void testFeet_DifferentValue() {
        assertFalse(QuantityMeasurementApp.checkFeetEquality(1.0, 2.0));
    }

    // INCH TESTS
    @Test
    void testInch_SameValue() {
        assertTrue(QuantityMeasurementApp.checkInchEquality(1.0, 1.0));
    }

    @Test
    void testInch_DifferentValue() {
        assertFalse(QuantityMeasurementApp.checkInchEquality(1.0, 2.0));
    }

    @Test
    void testInch_NullComparison() {
        QuantityMeasurementApp.Inches i = new QuantityMeasurementApp.Inches(1.0);
        assertFalse(i.equals(null));
    }

    @Test
    void testInch_SameReference() {
        QuantityMeasurementApp.Inches i = new QuantityMeasurementApp.Inches(1.0);
        assertTrue(i.equals(i));
    }

    @Test
    void testInch_NonNumeric() {
        QuantityMeasurementApp.Inches i = new QuantityMeasurementApp.Inches(1.0);
        assertFalse(i.equals("test"));
    }
}