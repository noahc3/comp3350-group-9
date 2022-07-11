package comp3350.cookit.tests.objects;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import comp3350.cookit.objects.Ingredient;
import comp3350.cookit.objects.IngredientList;
import comp3350.cookit.objects.Recipe;

public class RecipeTests {

    @Test
    public void testTypical() {
        IngredientList il = IngredientList.Create(new Ingredient("all-purpose flour", 10.0, "cups"));
        Recipe r1 = new Recipe("0", "Recipe", "2", "This is a recipe", il, 5, Arrays.asList("tag1", "tag2"),null);
        Assert.assertEquals("0", r1.getId());
        Assert.assertEquals("Recipe", r1.getTitle());
        Assert.assertEquals("2", r1.getAuthorId());
        Assert.assertEquals("This is a recipe", r1.getContent());
        Assert.assertEquals(il, r1.getIngredientList());
        Assert.assertEquals(5, r1.getServingSize());
        Assert.assertEquals(2, r1.getTags().size());
        Assert.assertEquals("tag1", r1.getTags().get(0));
        Assert.assertEquals("tag2", r1.getTags().get(1));

        Assert.assertEquals(r1, r1);

        Recipe r2 = new Recipe("0", "Recipe", "2", "This is a recipe", il, 5, Arrays.asList("tag1", "tag2"),null);

        Assert.assertEquals(r1, r2);

        il = IngredientList.Create(new Ingredient("white granulated sugar", 6.0, "tbsp"));
        r2 = new Recipe("0", "Other Recipe?", "95", "Different recipe content but same ID", il, 7, Arrays.asList("tag6", "tag5"),null);

        Assert.assertEquals(r1, r2);
    }

    @Test
    public void testFalse() {
        IngredientList il = IngredientList.Create(new Ingredient("all-purpose flour", 10.0, "cups"));
        Recipe r1 = new Recipe("0", "Recipe", "2", "This is a recipe", il, 5, Arrays.asList("tag1", "tag2"),null);
        Recipe r2 = new Recipe("1", "Recipe", "2", "This is a recipe", il, 5, Arrays.asList("tag1", "tag2"),null);

        Assert.assertNotEquals(r1, r2);
    }

    @Test
    public void testNull() {
        Recipe r1 = new Recipe(null, null, null, null, null, 0, null,null);
        Assert.assertNull(r1.getId());
        Assert.assertNull(r1.getTitle());
        Assert.assertNull(r1.getAuthorId());
        Assert.assertNull(r1.getContent());
        Assert.assertNull(r1.getIngredientList());
        Assert.assertNull(r1.getTags());
    }
}
