public class Main {

    public static void main(String[] args) {

        // 🔹 LENGTH
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FOOT);
        Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCH);

        System.out.println("Length Equal: " + l1.equals(l2));

        // 🔹 WEIGHT
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        System.out.println("Weight Equal: " + w1.equals(w2));

        // 🔹 VOLUME
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        System.out.println("Volume Equal: " + v1.equals(v2));

        // 🔹 Conversion
        System.out.println(v1.convertTo(VolumeUnit.GALLON));

        // 🔹 Addition
        System.out.println(v1.add(v2));
        System.out.println(v1.add(v2, VolumeUnit.MILLILITRE));
    }
}