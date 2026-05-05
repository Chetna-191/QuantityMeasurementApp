public static void main(String[] args) {

    // LENGTH
    Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
    Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCH);

    System.out.println(q1.subtract(q2)); // 9.5 FEET
    System.out.println(q1.subtract(q2, LengthUnit.INCH)); // 114 INCH

    // VOLUME
    Quantity<VolumeUnit> v1 = new Quantity<>(5.0, VolumeUnit.LITRE);
    Quantity<VolumeUnit> v2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);

    System.out.println(v1.subtract(v2)); // 4.5 LITRE

    // DIVISION
    System.out.println(q1.divide(new Quantity<>(2.0, LengthUnit.FEET))); // 5.0
    System.out.println(v1.divide(new Quantity<>(10.0, VolumeUnit.LITRE))); // 0.5
}