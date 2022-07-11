package comp3350.cookit.objects;

import java.io.Serializable;
import java.util.Objects;

public class Ingredient implements Serializable {
    private final String name;
    private final String measurement;
    private double quantity;

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

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Ingredient copy() {
        return new Ingredient(name, quantity, measurement);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Ingredient that = (Ingredient) other;
        return Double.compare(that.quantity, quantity) == 0 &&
                Objects.equals(name, that.name) &&
                Objects.equals(measurement, that.measurement);
    }
}
