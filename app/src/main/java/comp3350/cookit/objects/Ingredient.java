package comp3350.cookit.objects;

public class Ingredient {
    private String name;
    private double quantity;
    private String measurement;

    public Ingredient(String name, double quantity, String measurement) {
        this.name = name;
        this.quantity = quantity;
        this.measurement = measurement;
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getMeasurement() {
        return measurement;
    }
}
