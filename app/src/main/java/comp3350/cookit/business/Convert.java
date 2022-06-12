package comp3350.cookit.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.cookit.objects.Fraction;
import comp3350.cookit.objects.Ingredient;
import comp3350.cookit.objects.IngredientList;
import comp3350.cookit.objects.Recipe;

public class Convert
{
    public static Recipe multiplyServingSize(Recipe recipe, int factor) {
        Recipe newRecipe;
        IngredientList newIngredientList;
        List<Ingredient> increasedIngredients = new ArrayList<>();
        for (Ingredient i : recipe.getIngredientList().getIngredients()) {
            Ingredient copy = i.copy();
            copy.setQuantity(new Fraction(copy.getQuantity()).multiply(factor).getDouble());
            increasedIngredients.add(copy);
        }

        newIngredientList = new IngredientList(increasedIngredients);
        newRecipe = new Recipe(recipe.getId(), recipe.getTitle(), recipe.getAuthorId(), recipe.getContent(), newIngredientList, recipe.getServingSize() * factor, recipe.getTags());

        return newRecipe;
    }
}
