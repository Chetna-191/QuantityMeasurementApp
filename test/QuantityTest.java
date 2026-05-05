import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityTest {

    private static final double EPSILON = 1e-6;

    // ========================
    // 🔹 LENGTH TESTS
    // ========================

    @Test
    void testLengthEquality_FootToInch() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FOOT);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCH);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testLengthConversion() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FOOT);

        Quantity<LengthUnit> result = q.convertTo(LengthUnit.INCH);

        assertEquals(12.0, result.getValue(), EPSILON);
    }

    @Test
    void testLengthAddition() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FOOT);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCH);

        Quantity<LengthUnit> result = q1.add(q2);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    // ========================
    // 🔹 WEIGHT TESTS (UC9)
    // ========================

    @Test
    void testWeightEquality_KgToGram() {
        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testWeightConversion_PoundToKg() {
        Quantity<WeightUnit> q = new Quantity<>(2.20462, WeightUnit.POUND);

        Quantity<WeightUnit> result = q.convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), 1e-3); // relaxed precision
    }

    @Test
    void testWeightAddition() {
        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result = q1.add(q2);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    // ========================
    // 🔹 VOLUME TESTS (UC11)
    // ========================

    @Test
    void testVolumeEquality_LitreToMillilitre() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertTrue(v1.equals(v2));
    }

    @Test
    void testVolumeEquality_LitreToGallon() {
        Quantity<VolumeUnit> v1 = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1.0, VolumeUnit.GALLON);

        assertTrue(v1.equals(v2));
    }

    @Test
    void testVolumeConversion() {
        Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.MILLILITRE);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    void testVolumeAddition() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = v1.add(v2);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testVolumeAddition_TargetUnit() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1.0, VolumeUnit.GALLON);

        Quantity<VolumeUnit> result = v1.add(v2, VolumeUnit.MILLILITRE);

        assertEquals(4785.41, result.getValue(), 1e-2);
    }

    // ========================
    // 🔹 EDGE CASES
    // ========================

    @Test
    void testNullComparison() {
        Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);

        assertFalse(v.equals(null));
    }

    @Test
    void testSameReference() {
        Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);

        assertTrue(v.equals(v));
    }

    @Test
    void testInvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(1.0, null);
        });
    }

    @Test
    void testNegativeValues() {
        Quantity<VolumeUnit> v1 = new Quantity<>(-1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(-1000.0, VolumeUnit.MILLILITRE);

        assertTrue(v1.equals(v2));
    }

    // ========================
    // 🔹 CROSS CATEGORY SAFETY
    // ========================

    @Test
    void testVolumeVsWeight_NotEqual() {
        Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<WeightUnit> w = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(v.equals(w));
    }
}