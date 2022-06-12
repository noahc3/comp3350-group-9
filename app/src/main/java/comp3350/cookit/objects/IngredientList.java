package comp3350.cookit.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IngredientList {
    private List<Ingredient> ingredients;

    public IngredientList(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    public static IngredientList Create(Ingredient... args) {
        return new IngredientList(Arrays.asList(args));
    }
}
