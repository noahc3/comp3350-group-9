package comp3350.cookit.tests.objects;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import comp3350.cookit.objects.Ingredient;
import comp3350.cookit.objects.IngredientList;

public class IngredientListTests {

    @Test
    public void testTypical() {
        List<Ingredient> ingredients = Arrays.asList(new Ingredient("all-purpose flour", 1.0, "cups"), new Ingredient("baking soda", 2.0, "tsp"));
        IngredientList il1 = new IngredientList(ingredients);
        Assert.assertEquals(2, il1.getIngredients().size());
        Assert.assertTrue(il1.getIngredients().containsAll(ingredients));

        IngredientList il2 = IngredientList.Create(new Ingredient("all-purpose flour", 1.0, "cups"), new Ingredient("baking soda", 2.0, "tsp"));
        Assert.assertEquals(il1, il2);
    }

    @Test
    public void testExpectedMismatches() {
        List<Ingredient> ingredients = Arrays.asList(new Ingredient("all-purpose flour", 1.0, "cups"), new Ingredient("baking soda", 2.0, "tsp"));
        IngredientList il1 = new IngredientList(ingredients);
        Assert.assertNotSame(ingredients, il1.getIngredients());

        IngredientList il2 = IngredientList.Create(new Ingredient("all-purpose flour", 1.0, "cups"), new Ingredient("chocolate chips", 2.0, "cups"));
        Assert.assertNotEquals(il1, il2);

        il2 = IngredientList.Create(new Ingredient("all-purpose flour", 1.0, "cups"));
        Assert.assertNotEquals(il1, il2);

        il2 = IngredientList.Create(new Ingredient("all-purpose flour", 1.0, "cups"), new Ingredient("baking soda", 2.0, "tsp"), new Ingredient("baking powder", 1.0, "tbsp"));
        Assert.assertNotEquals(il1, il2);
    }

    @Test(expected = NullPointerException.class)
    public void testNull() {
        IngredientList il = new IngredientList(null);

    }
}
