import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {

    @Test
    void testFeetToInch() {
        Quantity q = new Quantity(1.0, LengthUnit.FEET);
        assertEquals(12.0, q.convertTo(LengthUnit.INCH).value);
    }

    @Test
    void testEquality() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testAddition() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);
        assertEquals(2.0, q1.add(q2, LengthUnit.FEET).value);
    }
}