package comp3350.cookit.tests.business;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import comp3350.cookit.application.Services;
import comp3350.cookit.business.AccessRecipes;
import comp3350.cookit.objects.Ingredient;
import comp3350.cookit.objects.IngredientList;
import comp3350.cookit.objects.Recipe;
import comp3350.cookit.tests.persistence.StubDataStore;

public class AccessRecipesTests {

    @Test
    public void testRecipeList() {
        AccessRecipes ar = initAccessRecipes();

        List<Recipe> recipes = ar.getRecipes();

        Assert.assertEquals(2, recipes.size());

        Assert.assertEquals("0", recipes.get(0).getId());
        Assert.assertEquals("Lemon Cranberry Muffins", recipes.get(0).getTitle());
        Assert.assertEquals("0", recipes.get(0).getAuthorId());
        Assert.assertEquals("1. Preheat oven to 400F. Grease 12 muffin cups, or line with paper muffin liners.\n\n2. Combine flour, sugar, baking powder, and salt in a large bowl. Mix lemon juice and milk in a measuring cup, to sour milk; beat eggs, oil, and milk mixture in a bowl. Stir egg mixture into flour mixture until just moistened; fold in cranberries. Fill prepared muffin cups two-thirds full; sprinkle with almonds.\n\n3. Bake in preheated oven until a toothpick inserted into a muffin comes out clean, 18 to 20 minutes. Cool for 5 minutes before removing from pan to wire rack.", recipes.get(0).getContent());
        Assert.assertEquals(12, recipes.get(0).getServingSize());

        List<Ingredient> ingredients = recipes.get(0).getIngredientList().getIngredients();
        Assert.assertEquals(10, ingredients.size());
        Assert.assertEquals(new Ingredient("all-purpose flour", 2, "cups"), ingredients.get(0));
        Assert.assertEquals(new Ingredient("white sugar", 1.25, "cups"), ingredients.get(1));
        Assert.assertEquals(new Ingredient("baking powder", 0.5, "tbsp"), ingredients.get(2));
        Assert.assertEquals(new Ingredient("salt", 0.5, "tsp"), ingredients.get(3));
        Assert.assertEquals(new Ingredient("lemon juice", 0.25, "cups"), ingredients.get(4));
        Assert.assertEquals(new Ingredient("milk", 0.75, "cups"), ingredients.get(5));
        Assert.assertEquals(new Ingredient("eggs", 2, "whole"), ingredients.get(6));
        Assert.assertEquals(new Ingredient("vegetable oil", 0.5, "cups"), ingredients.get(7));
        Assert.assertEquals(new Ingredient("cranberries, halved", 1, "cups"), ingredients.get(8));
        Assert.assertEquals(new Ingredient("toasted slivered almonds", 0.33, "cups"), ingredients.get(9));

        List<String> tags = recipes.get(0).getTags();
        Assert.assertEquals(4, tags.size());
        Assert.assertEquals("Pastry", tags.get(0));
        Assert.assertEquals("Sweet", tags.get(1));
        Assert.assertEquals("Snack", tags.get(2));
        Assert.assertEquals("All Day", tags.get(3));

        List<String> images = recipes.get(0).getImages();
        Assert.assertEquals(2, images.size());
        Assert.assertEquals("muffin0", images.get(0));
        Assert.assertEquals("muffin1", images.get(1));


        Assert.assertEquals("1", recipes.get(1).getId());
        Assert.assertEquals("Honey-Garlic Slow Cooker Chicken Thighs", recipes.get(1).getTitle());
        Assert.assertEquals("1", recipes.get(1).getAuthorId());
        Assert.assertEquals("1. Lay chicken thighs into the bottom of a 4-quart slow cooker.\n\n2. Whisk soy sauce, ketchup, honey, garlic, and basil together in a bowl; pour over the chicken.\n\n3. Cook on Low for 6 hours.", recipes.get(1).getContent());
        Assert.assertEquals(4, recipes.get(1).getServingSize());

        ingredients = recipes.get(1).getIngredientList().getIngredients();
        Assert.assertEquals(6, ingredients.size());
        Assert.assertEquals(new Ingredient("boneless, skinless chicken thighs", 4, "whole"), ingredients.get(0));
        Assert.assertEquals(new Ingredient("soy sauce", 0.5, "cups"), ingredients.get(1));
        Assert.assertEquals(new Ingredient("ketchup", 0.5, "cups"), ingredients.get(2));
        Assert.assertEquals(new Ingredient("honey", 0.33, "cups"), ingredients.get(3));
        Assert.assertEquals(new Ingredient("garlic, minced", 3, "cloves"), ingredients.get(4));
        Assert.assertEquals(new Ingredient("dried basil", 1, "tsp"), ingredients.get(5));

        tags = recipes.get(1).getTags();
        Assert.assertEquals(4, tags.size());
        Assert.assertEquals("Culinary", tags.get(0));
        Assert.assertEquals("Savory", tags.get(1));
        Assert.assertEquals("Entree", tags.get(2));
        Assert.assertEquals("Dinner", tags.get(3));

        images = recipes.get(1).getImages();
        Assert.assertEquals(2, images.size());
        Assert.assertEquals("chicken0", images.get(0));
        Assert.assertEquals("chicken1", images.get(1));
    }

    @Test
    public void testNewRecipe() {
        AccessRecipes ar = initAccessRecipes();

        List<Recipe> recipes = ar.getRecipes();
        Assert.assertEquals(2, recipes.size());

        IngredientList il = IngredientList.Create(new Ingredient("all-purpose flour", 10.0, "cups"));
        Recipe r = new Recipe("3", "Title", "5", "Content", il, 2, Arrays.asList("Some", "Tags"), 10, 20, "Easy", Arrays.asList("img1", "img2"));

        ar.insertRecipe(r);

        recipes = ar.getRecipes();
        Assert.assertEquals(3, recipes.size());

        Assert.assertEquals(r.getId(), recipes.get(2).getId());
        Assert.assertEquals(r.getTitle(), recipes.get(2).getTitle());
        Assert.assertEquals(r.getAuthorId(), recipes.get(2).getAuthorId());
        Assert.assertEquals(r.getContent(), recipes.get(2).getContent());
        Assert.assertEquals(r.getIngredientList(), recipes.get(2).getIngredientList());
        Assert.assertEquals(r.getServingSize(), recipes.get(2).getServingSize());
        Assert.assertEquals(r.getPrepTime(), recipes.get(2).getPrepTime());
        Assert.assertEquals(r.getCookTime(), recipes.get(2).getCookTime());
        Assert.assertEquals(r.getDifficulty(), recipes.get(2).getDifficulty());

        Assert.assertEquals(2, recipes.get(2).getTags().size());
        Assert.assertEquals("Some", recipes.get(2).getTags().get(0));
        Assert.assertEquals("Tags", recipes.get(2).getTags().get(1));
    }

    @Test
    public void testRecipeUpdateSingleField() {
        AccessRecipes ar = initAccessRecipes();

        Recipe dbRecipe = ar.getRecipeById("0");
        Assert.assertEquals("0", dbRecipe.getId());
        Assert.assertEquals("Lemon Cranberry Muffins", dbRecipe.getTitle());
        Assert.assertEquals(12, dbRecipe.getServingSize());

        Recipe modifiedRecipe = new Recipe(dbRecipe.getId(), "Bran Muffins", dbRecipe.getAuthorId(), dbRecipe.getContent(), dbRecipe.getIngredientList(), dbRecipe.getServingSize(), dbRecipe.getTags(), dbRecipe.getPrepTime(), dbRecipe.getCookTime(), dbRecipe.getDifficulty(), dbRecipe.getImages());
        ar.updateRecipe(modifiedRecipe);

        dbRecipe = ar.getRecipeById("0");
        Assert.assertEquals("0", dbRecipe.getId());
        Assert.assertEquals("Bran Muffins", dbRecipe.getTitle());
        Assert.assertEquals(12, dbRecipe.getServingSize());

        modifiedRecipe = new Recipe(dbRecipe.getId(), dbRecipe.getTitle(), dbRecipe.getAuthorId(), dbRecipe.getContent(), dbRecipe.getIngredientList(), 3, dbRecipe.getTags(), dbRecipe.getPrepTime(), dbRecipe.getCookTime(), dbRecipe.getDifficulty(), dbRecipe.getImages());
        ar.updateRecipe(modifiedRecipe);

        dbRecipe = ar.getRecipeById("0");
        Assert.assertEquals("0", dbRecipe.getId());
        Assert.assertEquals("Bran Muffins", dbRecipe.getTitle());
        Assert.assertEquals(3, dbRecipe.getServingSize());
    }

    @Test
    public void testRecipeUpdateMultiField() {
        AccessRecipes ar = initAccessRecipes();

        Recipe dbRecipe = ar.getRecipeById("1");
        Assert.assertEquals("1", dbRecipe.getId());
        Assert.assertEquals("Honey-Garlic Slow Cooker Chicken Thighs", dbRecipe.getTitle());
        Assert.assertEquals(4, dbRecipe.getServingSize());
        Assert.assertEquals("1. Lay chicken thighs into the bottom of a 4-quart slow cooker.\n\n2. Whisk soy sauce, ketchup, honey, garlic, and basil together in a bowl; pour over the chicken.\n\n3. Cook on Low for 6 hours.", dbRecipe.getContent());


        List<String> newTags = dbRecipe.getTags();
        newTags.add("Breakfast");
        Recipe modifiedRecipe = new Recipe(dbRecipe.getId(), "Blackened Chicken Breast", dbRecipe.getAuthorId(), "Some new content", dbRecipe.getIngredientList(), 1, newTags, dbRecipe.getPrepTime(), dbRecipe.getCookTime(), dbRecipe.getDifficulty(), dbRecipe.getImages());
        ar.updateRecipe(modifiedRecipe);

        dbRecipe = ar.getRecipeById("1");
        Assert.assertEquals("1", dbRecipe.getId());
        Assert.assertEquals("Blackened Chicken Breast", dbRecipe.getTitle());
        Assert.assertEquals(1, dbRecipe.getServingSize());
        Assert.assertEquals("Some new content", dbRecipe.getContent());
        Assert.assertTrue(dbRecipe.getTags().contains("Breakfast"));

        List<Recipe> taggedRecipes = ar.getRecipesWithTag("Breakfast");
        Assert.assertTrue(taggedRecipes.contains(dbRecipe));
    }

    @Test
    public void testInvalidRecipeUpdate() {
        AccessRecipes ar = initAccessRecipes();

        List<Recipe> recipes = ar.getRecipes();
        Assert.assertEquals(2, recipes.size());
        Assert.assertEquals("Lemon Cranberry Muffins", recipes.get(0).getTitle());
        Assert.assertEquals("Honey-Garlic Slow Cooker Chicken Thighs", recipes.get(1).getTitle());

        Recipe invalid = new Recipe("3", "Recipe with non-existant ID", "0", "Some content", recipes.get(0).getIngredientList(), 1, Arrays.asList("Some", "tags"), 120, 30, "Hard", Arrays.asList("img1", "img2"));
        ar.updateRecipe(invalid);

        recipes = ar.getRecipes();
        Assert.assertEquals(2, recipes.size());
        Assert.assertEquals("Lemon Cranberry Muffins", recipes.get(0).getTitle());
        Assert.assertEquals("Honey-Garlic Slow Cooker Chicken Thighs", recipes.get(1).getTitle());

        ar.updateRecipe(null);

        recipes = ar.getRecipes();
        Assert.assertEquals(2, recipes.size());
        Assert.assertEquals("Lemon Cranberry Muffins", recipes.get(0).getTitle());
        Assert.assertEquals("Honey-Garlic Slow Cooker Chicken Thighs", recipes.get(1).getTitle());
    }

    @Test
    public void testInvalidRecipeIdLookup() {
        AccessRecipes ar = initAccessRecipes();

        Assert.assertNull(ar.getRecipeById("37"));
        Assert.assertNull(ar.getRecipeById(null));
    }

    @Test
    public void testRecipeDelete() {
        AccessRecipes ar = initAccessRecipes();

        List<Recipe> recipes = ar.getRecipes();
        Assert.assertEquals(2, recipes.size());
        Assert.assertEquals("0", recipes.get(0).getId());
        Assert.assertEquals("1", recipes.get(1).getId());

        List<Recipe> taggedRecipes = ar.getRecipesWithTag("Sweet");
        Assert.assertEquals(1, taggedRecipes.size());
        taggedRecipes = ar.getRecipesWithTag("Savory");
        Assert.assertEquals(1, taggedRecipes.size());

        ar.deleteRecipe(recipes.get(0));

        recipes = ar.getRecipes();
        Assert.assertEquals(1, recipes.size());
        Assert.assertEquals("1", recipes.get(0).getId());

        taggedRecipes = ar.getRecipesWithTag("Sweet");
        Assert.assertEquals(0, taggedRecipes.size());
        taggedRecipes = ar.getRecipesWithTag("Savory");
        Assert.assertEquals(1, taggedRecipes.size());

        ar.deleteRecipe(recipes.get(0));

        recipes = ar.getRecipes();
        Assert.assertEquals(0, recipes.size());

        taggedRecipes = ar.getRecipesWithTag("Sweet");
        Assert.assertEquals(0, taggedRecipes.size());
        taggedRecipes = ar.getRecipesWithTag("Savory");
        Assert.assertEquals(0, taggedRecipes.size());
    }

    @Test
    public void testInvalidRecipeDelete() {
        AccessRecipes ar = initAccessRecipes();

        List<Recipe> recipes = ar.getRecipes();
        Assert.assertEquals(2, recipes.size());
        Assert.assertEquals("0", recipes.get(0).getId());
        Assert.assertEquals("1", recipes.get(1).getId());

        Recipe invalid = new Recipe("3", "Recipe with non-existant ID", "0", "Some content", recipes.get(0).getIngredientList(), 1, Arrays.asList("Some", "tags"), 40, 360, "Easy", Arrays.asList("img1", "img2"));
        ar.deleteRecipe(invalid);

        recipes = ar.getRecipes();
        Assert.assertEquals(2, recipes.size());
        Assert.assertEquals("0", recipes.get(0).getId());
        Assert.assertEquals("1", recipes.get(1).getId());

        ar.deleteRecipe(null);

        recipes = ar.getRecipes();
        Assert.assertEquals(2, recipes.size());
        Assert.assertEquals("0", recipes.get(0).getId());
        Assert.assertEquals("1", recipes.get(1).getId());
    }

    private AccessRecipes initAccessRecipes() {
        Services.createDataStore(new StubDataStore());
        return new AccessRecipes();
    }
}
