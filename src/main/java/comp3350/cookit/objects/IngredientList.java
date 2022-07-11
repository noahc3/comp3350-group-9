package comp3350.cookit.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IngredientList {
    private final List<Ingredient> ingredients;

    public IngredientList(List<Ingredient> ingredients) {
        if (ingredients == null) {
            throw new NullPointerException("Ingredients list cannot be null.");
        }

        this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    public static IngredientList Create(Ingredient... args) {
        return new IngredientList(Arrays.asList(args));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        IngredientList that = (IngredientList) other;
        return ingredients.size() == that.getIngredients().size() && ingredients.containsAll(that.getIngredients());
    }
}
