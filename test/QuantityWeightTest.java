import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityWeightTest {

    @Test
    void testKgToGram() {
        var q = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        assertEquals(1000.0, q.convertTo(WeightUnit.GRAM).convertTo(WeightUnit.GRAM).convertTo(WeightUnit.GRAM).convertTo(WeightUnit.GRAM).convertTo(WeightUnit.GRAM).value, 0.01);
    }

    @Test
    void testGramToKg() {
        var q = new QuantityWeight(1000.0, WeightUnit.GRAM);
        assertEquals(1.0, q.convertTo(WeightUnit.KILOGRAM).value, 0.01);
    }

    @Test
    void testEquality() {
        var q1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        var q2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testAddition() {
        var q1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        var q2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        assertEquals(2.0, q1.add(q2, WeightUnit.KILOGRAM).value, 0.01);
    }

    @Test
    void testNullUnit() {
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityWeight(1.0, null));
    }
}