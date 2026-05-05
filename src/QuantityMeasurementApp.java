public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // 🔍 Equality
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        System.out.println(w1.equals(w2)); // true

        // 🔁 Conversion
        System.out.println(w1.convertTo(WeightUnit.GRAM)); // 1000

        // ➕ Addition
        QuantityWeight w3 = new QuantityWeight(2.0, WeightUnit.POUND);

        System.out.println(w1.add(w3, WeightUnit.KILOGRAM));
    }
}