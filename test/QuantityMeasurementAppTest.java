import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPS = 1e-2;

    @Test
    void testLitreToMillilitreEquality() {
        var v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        var v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertTrue(v1.equals(v2));
    }

    @Test
    void testLitreToGallonEquality() {
        var v1 = new Quantity<>(3.78541, VolumeUnit.LITRE);
        var v2 = new Quantity<>(1.0, VolumeUnit.GALLON);

        assertTrue(v1.equals(v2));
    }

    @Test
    void testConversion_LitreToML() {
        var v = new Quantity<>(1.0, VolumeUnit.LITRE);

        var result = v.convertTo(VolumeUnit.MILLILITRE);

        assertEquals(1000.0, result.getValue(), EPS);
    }

    @Test
    void testConversion_GallonToLitre() {
        var v = new Quantity<>(1.0, VolumeUnit.GALLON);

        var result = v.convertTo(VolumeUnit.LITRE);

        assertEquals(3.78541, result.getValue(), EPS);
    }

    @Test
    void testAddition_DefaultUnit() {
        var v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        var v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        var result = v1.add(v2);

        assertEquals(2.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_TargetUnit() {
        var v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        var v2 = new Quantity<>(1.0, VolumeUnit.GALLON);

        var result = v1.add(v2, VolumeUnit.MILLILITRE);

        assertEquals(4785.41, result.getValue(), EPS);
    }

    @Test
    void testNullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(1.0, null);
        });
    }

    @Test
    void testVolumeVsWeight_NotEqual() {
        var v = new Quantity<>(1.0, VolumeUnit.LITRE);
        var w = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(v.equals(w));
    }
}