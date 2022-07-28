package comp3350.cookit.tests.business;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import comp3350.cookit.application.Services;
import comp3350.cookit.business.AccessRecipes;
import comp3350.cookit.objects.Ingredient;
import comp3350.cookit.objects.IngredientList;
import comp3350.cookit.objects.Recipe;
import comp3350.cookit.tests.RunIntegrationTests;
import comp3350.cookit.tests.persistence.StubDataStore;

public class AccessRecipesTests {
    private static AccessRecipes ar;

    @BeforeClass
    public static void resetDatabaseBeforeTesting() {
        // Reset the database once before running tests. This will fix the database
        // in case a test which modifies the database failed mid-run without reaching
        // the cleanup action.
        resetDatabase();
    }

    @Test
    public void testRecipeList() {
        initDatabase();

        List<Recipe> recipes = ar.getRecipes();

        Assert.assertEquals(14, recipes.size());

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

        resetDatabase();
    }

    @Test
    public void testNewRecipe() {
        initDatabase();

        List<Recipe> recipes = ar.getRecipes();
        Assert.assertEquals(14, recipes.size());

        IngredientList il = IngredientList.Create(new Ingredient("all-purpose flour", 10.0, "cups"));
        Recipe r = new Recipe("14", "Title", "5", "Content", il, 2, Arrays.asList("Some", "Tags"), 10, 20, "Easy", Arrays.asList("img1", "img2"));

        ar.insertRecipe(r);

        recipes = ar.getRecipes();
        Assert.assertEquals(15, recipes.size());

        Assert.assertEquals(r.getId(), recipes.get(14).getId());
        Assert.assertEquals(r.getTitle(), recipes.get(14).getTitle());
        Assert.assertEquals(r.getAuthorId(), recipes.get(14).getAuthorId());
        Assert.assertEquals(r.getContent(), recipes.get(14).getContent());
        Assert.assertEquals(r.getIngredientList(), recipes.get(14).getIngredientList());
        Assert.assertEquals(r.getServingSize(), recipes.get(14).getServingSize());
        Assert.assertEquals(r.getPrepTime(), recipes.get(14).getPrepTime());
        Assert.assertEquals(r.getCookTime(), recipes.get(14).getCookTime());
        Assert.assertEquals(r.getDifficulty(), recipes.get(14).getDifficulty());

        Assert.assertTrue(ar.anyRecipeWithTag("Some"));
        Assert.assertTrue(ar.anyRecipeWithTag("Tags"));
        Assert.assertEquals(2, recipes.get(14).getTags().size());
        Assert.assertEquals("Some", recipes.get(14).getTags().get(0));
        Assert.assertEquals("Tags", recipes.get(14).getTags().get(1));

        resetDatabase();
    }

    @Test
    public void testRecipeUpdateSingleField() {
        initDatabase();

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

        resetDatabase();
    }

    @Test
    public void testRecipeUpdateMultiField() {
        initDatabase();

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

        resetDatabase();
    }

    @Test
    public void testInvalidRecipeUpdate() {
        initDatabase();

        List<Recipe> recipes = ar.getRecipes();
        Assert.assertEquals(14, recipes.size());
        Assert.assertEquals("Lemon Cranberry Muffins", recipes.get(0).getTitle());
        Assert.assertEquals("Honey-Garlic Slow Cooker Chicken Thighs", recipes.get(1).getTitle());
        Assert.assertEquals("Sesame Peanut Noodles", recipes.get(2).getTitle());
        Assert.assertEquals("Grandma's Oatmeal Cookies", recipes.get(3).getTitle());
        Assert.assertEquals("Spinach Tofu Scramble", recipes.get(4).getTitle());
        Assert.assertEquals("Shrimp Ceviche", recipes.get(5).getTitle());
        Assert.assertEquals("Brazilian Cheese Bread (Pão de Queijo)", recipes.get(6).getTitle());
        Assert.assertEquals("Sweet and Salty Three-Seed Granola", recipes.get(7).getTitle());
        Assert.assertEquals("Bisquick Apple Coffee Cake", recipes.get(8).getTitle());
        Assert.assertEquals("Baked Oatmeal with Mixed Berries", recipes.get(9).getTitle());
        Assert.assertEquals("Philly Cheesesteak Sloppy Joes", recipes.get(10).getTitle());
        Assert.assertEquals("Corn and Ricotta Bruschetta", recipes.get(11).getTitle());
        Assert.assertEquals("Crash Hot Potatoes with Smoked Salmon", recipes.get(12).getTitle());
        Assert.assertEquals("Rice Cake with Dulce de Leche and Dark Chocolate", recipes.get(13).getTitle());

        Recipe invalid = new Recipe("37", "Recipe with non-existant ID", "0", "Some content", recipes.get(0).getIngredientList(), 1, Arrays.asList("Some", "tags"), 120, 30, "Hard", Arrays.asList("img1", "img2"));
        ar.updateRecipe(invalid);

        recipes = ar.getRecipes();
        Assert.assertEquals(14, recipes.size());
        Assert.assertEquals("Lemon Cranberry Muffins", recipes.get(0).getTitle());
        Assert.assertEquals("Honey-Garlic Slow Cooker Chicken Thighs", recipes.get(1).getTitle());
        Assert.assertEquals("Sesame Peanut Noodles", recipes.get(2).getTitle());
        Assert.assertEquals("Grandma's Oatmeal Cookies", recipes.get(3).getTitle());
        Assert.assertEquals("Spinach Tofu Scramble", recipes.get(4).getTitle());
        Assert.assertEquals("Shrimp Ceviche", recipes.get(5).getTitle());
        Assert.assertEquals("Brazilian Cheese Bread (Pão de Queijo)", recipes.get(6).getTitle());
        Assert.assertEquals("Sweet and Salty Three-Seed Granola", recipes.get(7).getTitle());
        Assert.assertEquals("Bisquick Apple Coffee Cake", recipes.get(8).getTitle());
        Assert.assertEquals("Baked Oatmeal with Mixed Berries", recipes.get(9).getTitle());
        Assert.assertEquals("Philly Cheesesteak Sloppy Joes", recipes.get(10).getTitle());
        Assert.assertEquals("Corn and Ricotta Bruschetta", recipes.get(11).getTitle());
        Assert.assertEquals("Crash Hot Potatoes with Smoked Salmon", recipes.get(12).getTitle());
        Assert.assertEquals("Rice Cake with Dulce de Leche and Dark Chocolate", recipes.get(13).getTitle());

        ar.updateRecipe(null);

        recipes = ar.getRecipes();
        Assert.assertEquals(14, recipes.size());
        Assert.assertEquals("Lemon Cranberry Muffins", recipes.get(0).getTitle());
        Assert.assertEquals("Honey-Garlic Slow Cooker Chicken Thighs", recipes.get(1).getTitle());
        Assert.assertEquals("Sesame Peanut Noodles", recipes.get(2).getTitle());
        Assert.assertEquals("Grandma's Oatmeal Cookies", recipes.get(3).getTitle());
        Assert.assertEquals("Spinach Tofu Scramble", recipes.get(4).getTitle());
        Assert.assertEquals("Shrimp Ceviche", recipes.get(5).getTitle());
        Assert.assertEquals("Brazilian Cheese Bread (Pão de Queijo)", recipes.get(6).getTitle());
        Assert.assertEquals("Sweet and Salty Three-Seed Granola", recipes.get(7).getTitle());
        Assert.assertEquals("Bisquick Apple Coffee Cake", recipes.get(8).getTitle());
        Assert.assertEquals("Baked Oatmeal with Mixed Berries", recipes.get(9).getTitle());
        Assert.assertEquals("Philly Cheesesteak Sloppy Joes", recipes.get(10).getTitle());
        Assert.assertEquals("Corn and Ricotta Bruschetta", recipes.get(11).getTitle());
        Assert.assertEquals("Crash Hot Potatoes with Smoked Salmon", recipes.get(12).getTitle());
        Assert.assertEquals("Rice Cake with Dulce de Leche and Dark Chocolate", recipes.get(13).getTitle());

        resetDatabase();
    }

    @Test
    public void testInvalidRecipeIdLookup() {
        initDatabase();

        Assert.assertNull(ar.getRecipeById("37"));
        Assert.assertNull(ar.getRecipeById(null));

        resetDatabase();
    }

    @Test
    public void testRecipeDelete() {
        initDatabase();

        List<Recipe> recipes = ar.getRecipes();
        Assert.assertEquals(14, recipes.size());
        Assert.assertEquals("0", recipes.get(0).getId());
        Assert.assertEquals("1", recipes.get(1).getId());
        Assert.assertEquals("2", recipes.get(2).getId());
        Assert.assertEquals("3", recipes.get(3).getId());
        Assert.assertEquals("4", recipes.get(4).getId());
        Assert.assertEquals("5", recipes.get(5).getId());
        Assert.assertEquals("6", recipes.get(6).getId());
        Assert.assertEquals("7", recipes.get(7).getId());
        Assert.assertEquals("8", recipes.get(8).getId());
        Assert.assertEquals("9", recipes.get(9).getId());
        Assert.assertEquals("10", recipes.get(10).getId());
        Assert.assertEquals("11", recipes.get(11).getId());
        Assert.assertEquals("12", recipes.get(12).getId());
        Assert.assertEquals("13", recipes.get(13).getId());

        List<Recipe> taggedRecipes = ar.getRecipesWithTag("Sweet");
        Assert.assertEquals(6, taggedRecipes.size());
        taggedRecipes = ar.getRecipesWithTag("Savory");
        Assert.assertEquals(8, taggedRecipes.size());

        ar.deleteRecipe(recipes.get(0));

        recipes = ar.getRecipes();
        Assert.assertEquals(13, recipes.size());
        Assert.assertEquals("1", recipes.get(0).getId());

        taggedRecipes = ar.getRecipesWithTag("Sweet");
        Assert.assertEquals(5, taggedRecipes.size());
        taggedRecipes = ar.getRecipesWithTag("Savory");
        Assert.assertEquals(8, taggedRecipes.size());

        ar.deleteRecipe(recipes.get(0));

        recipes = ar.getRecipes();
        Assert.assertEquals(12, recipes.size());

        taggedRecipes = ar.getRecipesWithTag("Sweet");
        Assert.assertEquals(5, taggedRecipes.size());
        taggedRecipes = ar.getRecipesWithTag("Savory");
        Assert.assertEquals(7, taggedRecipes.size());

        resetDatabase();
    }

    @Test
    public void testInvalidRecipeDelete() {
        initDatabase();

        List<Recipe> recipes = ar.getRecipes();
        Assert.assertEquals(14, recipes.size());
        Assert.assertEquals("0", recipes.get(0).getId());
        Assert.assertEquals("1", recipes.get(1).getId());
        Assert.assertEquals("2", recipes.get(2).getId());
        Assert.assertEquals("3", recipes.get(3).getId());
        Assert.assertEquals("4", recipes.get(4).getId());
        Assert.assertEquals("5", recipes.get(5).getId());
        Assert.assertEquals("6", recipes.get(6).getId());
        Assert.assertEquals("7", recipes.get(7).getId());
        Assert.assertEquals("8", recipes.get(8).getId());
        Assert.assertEquals("9", recipes.get(9).getId());
        Assert.assertEquals("10", recipes.get(10).getId());
        Assert.assertEquals("11", recipes.get(11).getId());
        Assert.assertEquals("12", recipes.get(12).getId());
        Assert.assertEquals("13", recipes.get(13).getId());

        Recipe invalid = new Recipe("18", "Recipe with non-existant ID", "0", "Some content", recipes.get(0).getIngredientList(), 1, Arrays.asList("Some", "tags"), 40, 360, "Easy", Arrays.asList("img1", "img2"));
        ar.deleteRecipe(invalid);

        recipes = ar.getRecipes();
        Assert.assertEquals(14, recipes.size());
        Assert.assertEquals("0", recipes.get(0).getId());
        Assert.assertEquals("1", recipes.get(1).getId());
        Assert.assertEquals("2", recipes.get(2).getId());
        Assert.assertEquals("3", recipes.get(3).getId());
        Assert.assertEquals("4", recipes.get(4).getId());
        Assert.assertEquals("5", recipes.get(5).getId());
        Assert.assertEquals("6", recipes.get(6).getId());
        Assert.assertEquals("7", recipes.get(7).getId());
        Assert.assertEquals("8", recipes.get(8).getId());
        Assert.assertEquals("9", recipes.get(9).getId());
        Assert.assertEquals("10", recipes.get(10).getId());
        Assert.assertEquals("11", recipes.get(11).getId());
        Assert.assertEquals("12", recipes.get(12).getId());
        Assert.assertEquals("13", recipes.get(13).getId());

        ar.deleteRecipe(null);

        recipes = ar.getRecipes();
        Assert.assertEquals(14, recipes.size());
        Assert.assertEquals("0", recipes.get(0).getId());
        Assert.assertEquals("1", recipes.get(1).getId());
        Assert.assertEquals("2", recipes.get(2).getId());
        Assert.assertEquals("3", recipes.get(3).getId());
        Assert.assertEquals("4", recipes.get(4).getId());
        Assert.assertEquals("5", recipes.get(5).getId());
        Assert.assertEquals("6", recipes.get(6).getId());
        Assert.assertEquals("7", recipes.get(7).getId());
        Assert.assertEquals("8", recipes.get(8).getId());
        Assert.assertEquals("9", recipes.get(9).getId());
        Assert.assertEquals("10", recipes.get(10).getId());
        Assert.assertEquals("11", recipes.get(11).getId());
        Assert.assertEquals("12", recipes.get(12).getId());
        Assert.assertEquals("13", recipes.get(13).getId());

        resetDatabase();
    }


    private static void initDatabase() {
        if (RunIntegrationTests.USE_STUBDATASTORE) {
            Services.createDataStore(new StubDataStore());
        } else {
            Services.createDataStore();
        }

        ar = new AccessRecipes();
    }

    private static void resetDatabase() {
        try {
            if (!RunIntegrationTests.USE_STUBDATASTORE) {
                Services.closeDataStore();

                for (Object o : Files.list(Paths.get("db")).toArray()) {
                    Path p = (Path) o;
                    Files.delete(p);
                }

                Files.copy(Paths.get("src/main/assets/db/main.script"), Paths.get("db/main.script"));
            }
        } catch (IOException ioe) {
            System.out.println("Failed to reset database.");
            System.out.println(ioe.getMessage());
        }
    }
}
