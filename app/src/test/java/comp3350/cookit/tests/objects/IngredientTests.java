package comp3350.cookit.tests.objects;

import org.junit.Assert;
import org.junit.Test;

import comp3350.cookit.objects.Ingredient;

public class IngredientTests {

    @Test
    public void testTypical() {
        Ingredient i1 = new Ingredient("all-purpose flour", 10.0, "cups");
        Assert.assertEquals("all-purpose flour", i1.getName());
        Assert.assertEquals(10.0, i1.getQuantity(), 0.0001);
        Assert.assertEquals("cups", i1.getMeasurement());

        Ingredient i2 = new Ingredient("all-purpose flour", 10.0, "cups");
        Assert.assertEquals(i1, i2);
    }

    @Test
    public void testExpectedMismatches() {
        Ingredient i1 = new Ingredient("all-purpose flour", 10.0, "cups");
        Ingredient i2 = new Ingredient("all-purpose flour", 10.0, "grams");
        Assert.assertNotEquals(i1, i2);

        i2 = new Ingredient("all-purpose flour", 20.0, "cups");
        Assert.assertNotEquals(i1, i2);

        i2 = new Ingredient("baking powder", 10.0, "cups");
        Assert.assertNotEquals(i1, i2);
    }

    @Test
    public void testNull() {
        Ingredient i = new Ingredient("all-purpose flour", 10.0, "cups");
        Assert.assertNotEquals(i, null);

        i = new Ingredient(null, 0.0, null);
        Assert.assertNull(i.getName());
        Assert.assertNull(i.getMeasurement());
    }
}
