package comp3350.cookit.tests.business;

import android.annotation.SuppressLint;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import comp3350.cookit.business.Convert;
import comp3350.cookit.objects.Ingredient;
import comp3350.cookit.objects.IngredientList;
import comp3350.cookit.objects.Recipe;

public class multiplyServingSizeTests {
    @Test
    public void testTypical() {
        Recipe recipe = createRecipeFromTemplate(
                Arrays.asList(
                        new Ingredient("unsalted butter", 1, "cups"),
                        new Ingredient("all-purpose flour", 3, "cups"),
                        new Ingredient("granulated sugar", 0.75, "cups")
                ),
                34
        );

        Recipe x2 = Convert.multiplyServingSize(recipe, 2);
        List<Ingredient> newIngredients = x2.getIngredientList().getIngredients();

        Assert.assertEquals(68.0, x2.getServingSize(), 0.001);
        Assert.assertEquals(3, newIngredients.size());
        Assert.assertEquals("unsalted butter", newIngredients.get(0).getName());
        Assert.assertEquals("all-purpose flour", newIngredients.get(1).getName());
        Assert.assertEquals("granulated sugar", newIngredients.get(2).getName());
        Assert.assertEquals(2, newIngredients.get(0).getQuantity(), 0.001);
        Assert.assertEquals(6, newIngredients.get(1).getQuantity(), 0.001);
        Assert.assertEquals(1.50, newIngredients.get(2).getQuantity(), 0.001);

        Recipe x3 = Convert.multiplyServingSize(recipe, 3);
        newIngredients = x3.getIngredientList().getIngredients();

        Assert.assertEquals(102.0, x3.getServingSize(), 0.001);
        Assert.assertEquals(3, newIngredients.size());
        Assert.assertEquals("unsalted butter", newIngredients.get(0).getName());
        Assert.assertEquals("all-purpose flour", newIngredients.get(1).getName());
        Assert.assertEquals("granulated sugar", newIngredients.get(2).getName());
        Assert.assertEquals(3, newIngredients.get(0).getQuantity(), 0.001);
        Assert.assertEquals(9, newIngredients.get(1).getQuantity(), 0.001);
        Assert.assertEquals(2.25, newIngredients.get(2).getQuantity(), 0.001);

        Recipe x4 = Convert.multiplyServingSize(recipe, 4);
        newIngredients = x4.getIngredientList().getIngredients();

        Assert.assertEquals(136.0, x4.getServingSize(), 0.001);
        Assert.assertEquals(3, newIngredients.size());
        Assert.assertEquals("unsalted butter", newIngredients.get(0).getName());
        Assert.assertEquals("all-purpose flour", newIngredients.get(1).getName());
        Assert.assertEquals("granulated sugar", newIngredients.get(2).getName());
        Assert.assertEquals(4, newIngredients.get(0).getQuantity(), 0.001);
        Assert.assertEquals(12, newIngredients.get(1).getQuantity(), 0.001);
        Assert.assertEquals(3.0, newIngredients.get(2).getQuantity(), 0.001);
    }

    @Test
    public void testThirds() {
        Recipe recipe = createRecipeFromTemplate(
                Arrays.asList(
                        new Ingredient("all-purpose flour", 0.33, "cups"),
                        new Ingredient("granulated sugar", 0.66, "cups")
                ),
                10
        );

        Recipe x2 = Convert.multiplyServingSize(recipe, 2);
        List<Ingredient> newIngredients = x2.getIngredientList().getIngredients();

        Assert.assertEquals(20.0, x2.getServingSize(), 0.001);
        Assert.assertEquals(2, newIngredients.size());
        Assert.assertEquals("all-purpose flour", newIngredients.get(0).getName());
        Assert.assertEquals("granulated sugar", newIngredients.get(1).getName());
        Assert.assertEquals(0.66, newIngredients.get(0).getQuantity(), 0.01);
        Assert.assertEquals(1.33, newIngredients.get(1).getQuantity(), 0.01);

        Recipe x3 = Convert.multiplyServingSize(recipe, 3);
        newIngredients = x3.getIngredientList().getIngredients();

        Assert.assertEquals(30.0, x3.getServingSize(), 0.001);
        Assert.assertEquals(2, newIngredients.size());
        Assert.assertEquals("all-purpose flour", newIngredients.get(0).getName());
        Assert.assertEquals("granulated sugar", newIngredients.get(1).getName());
        Assert.assertEquals(1.0, newIngredients.get(0).getQuantity(), 0.01);
        Assert.assertEquals(2.0, newIngredients.get(1).getQuantity(), 0.01);

        Recipe x4 = Convert.multiplyServingSize(recipe, 4);
        newIngredients = x4.getIngredientList().getIngredients();

        Assert.assertEquals(40.0, x4.getServingSize(), 0.001);
        Assert.assertEquals(2, newIngredients.size());
        Assert.assertEquals("all-purpose flour", newIngredients.get(0).getName());
        Assert.assertEquals("granulated sugar", newIngredients.get(1).getName());
        Assert.assertEquals(1.33, newIngredients.get(0).getQuantity(), 0.01);
        Assert.assertEquals(2.66, newIngredients.get(1).getQuantity(), 0.01);
    }

    @Test
    public void testZeroValues() {
        Recipe recipe = createRecipeFromTemplate(
                Arrays.asList(
                        new Ingredient("unsalted butter", 0, "cups"),
                        new Ingredient("all-purpose flour", 0, "cups"),
                        new Ingredient("granulated sugar", 0, "cups")
                ),
                0
        );

        Recipe x2 = Convert.multiplyServingSize(recipe, 2);
        List<Ingredient> newIngredients = x2.getIngredientList().getIngredients();

        Assert.assertEquals(0, x2.getServingSize(), 0.001);
        Assert.assertEquals(3, newIngredients.size());
        Assert.assertEquals("unsalted butter", newIngredients.get(0).getName());
        Assert.assertEquals("all-purpose flour", newIngredients.get(1).getName());
        Assert.assertEquals("granulated sugar", newIngredients.get(2).getName());
        Assert.assertEquals(0, newIngredients.get(0).getQuantity(), 0.001);
        Assert.assertEquals(0, newIngredients.get(1).getQuantity(), 0.001);
        Assert.assertEquals(0, newIngredients.get(2).getQuantity(), 0.001);
    }

    @Test
    public void testEmptyList() {
        Recipe recipe = createRecipeFromTemplate(
                new ArrayList<Ingredient>(),
                10
        );

        Recipe x2 = Convert.multiplyServingSize(recipe, 2);
        List<Ingredient> newIngredients = x2.getIngredientList().getIngredients();

        Assert.assertEquals(20.0, x2.getServingSize(), 0.000001);
        Assert.assertEquals(0, newIngredients.size());
    }

    @Test
    public void testSingleItemList() {
        Recipe recipe = createRecipeFromTemplate(
                Collections.singletonList(
                        new Ingredient("unsalted butter", 1, "cups")
                ),
                10
        );

        Recipe x2 = Convert.multiplyServingSize(recipe, 2);
        List<Ingredient> newIngredients = x2.getIngredientList().getIngredients();

        Assert.assertEquals(20.0, x2.getServingSize(), 0.000001);
        Assert.assertEquals(1, newIngredients.size());
        Assert.assertEquals("unsalted butter", newIngredients.get(0).getName());
        Assert.assertEquals(2, newIngredients.get(0).getQuantity(), 0.01);
    }

    @Test
    public void testOriginalIsUnmodified() {
        Recipe recipe = createRecipeFromTemplate(
                Arrays.asList(
                        new Ingredient("unsalted butter", 1, "cups"),
                        new Ingredient("all-purpose flour", 3, "cups"),
                        new Ingredient("granulated sugar", 0.75, "cups")
                ),
                34
        );

        Convert.multiplyServingSize(recipe, 2);

        List<Ingredient> oldIngredients = recipe.getIngredientList().getIngredients();
        Assert.assertEquals(34, recipe.getServingSize(), 0.001);
        Assert.assertEquals(1, oldIngredients.get(0).getQuantity(), 0.001);
        Assert.assertEquals(3, oldIngredients.get(1).getQuantity(), 0.001);
        Assert.assertEquals(0.75, oldIngredients.get(2).getQuantity(), 0.001);
    }

    @Test(expected = NullPointerException.class)
    public void testNullIngredients() {
        Recipe recipe = createRecipeFromTemplate(
                null,
                10
        );

        Convert.multiplyServingSize(recipe, 2);
    }

    @Test(expected = NullPointerException.class)
    public void testNullIngredientList() {
        Recipe recipe = new Recipe("0", "Test", "0", "1. This is an example recipe.\n2. It has no real content.", null, 10.0, new ArrayList<String>());

        Convert.multiplyServingSize(recipe, 2);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void testNullRecipe() {
        Convert.multiplyServingSize(null, 2);
    }

    private Recipe createRecipeFromTemplate(List<Ingredient> ingredients, double servingCount) {
        return new Recipe(
                "0",
                "Test",
                "0",
                "1. This is an example recipe.\n2. It has no real content.",
                new IngredientList(ingredients),
                servingCount,
                new ArrayList<String>());
    }

}
