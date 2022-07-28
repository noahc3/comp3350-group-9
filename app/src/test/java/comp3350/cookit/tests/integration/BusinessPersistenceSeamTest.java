package comp3350.cookit.tests.integration;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import comp3350.cookit.application.Services;
import comp3350.cookit.business.AccessAuthors;
import comp3350.cookit.business.AccessRecipes;
import comp3350.cookit.business.AccessReviews;
import comp3350.cookit.objects.Author;
import comp3350.cookit.objects.Ingredient;
import comp3350.cookit.objects.IngredientList;
import comp3350.cookit.objects.Recipe;
import comp3350.cookit.objects.Review;
import comp3350.cookit.tests.RunIntegrationTests;
import comp3350.cookit.tests.persistence.StubDataStore;

public class BusinessPersistenceSeamTest {
    private AccessAuthors authors;
    private AccessRecipes recipes;
    private AccessReviews reviews;

    @Test
    public void testBrowseRecipes() {
        initDatabase();

        //User opens the app and lands on the homepage, all recipes are displayed.
        List<Recipe> recipeList = recipes.getRecipes();
        Assert.assertEquals(14, recipeList.size());
        Assert.assertEquals("Lemon Cranberry Muffins", recipeList.get(0).getTitle());
        Assert.assertEquals("Honey-Garlic Slow Cooker Chicken Thighs", recipeList.get(1).getTitle());
        Assert.assertEquals("Sesame Peanut Noodles", recipeList.get(2).getTitle());
        Assert.assertEquals("Grandma's Oatmeal Cookies", recipeList.get(3).getTitle());
        Assert.assertEquals("Spinach Tofu Scramble", recipeList.get(4).getTitle());
        Assert.assertEquals("Shrimp Ceviche", recipeList.get(5).getTitle());
        Assert.assertEquals("Brazilian Cheese Bread (Pão de Queijo)", recipeList.get(6).getTitle());
        Assert.assertEquals("Sweet and Salty Three-Seed Granola", recipeList.get(7).getTitle());
        Assert.assertEquals("Bisquick Apple Coffee Cake", recipeList.get(8).getTitle());
        Assert.assertEquals("Baked Oatmeal with Mixed Berries", recipeList.get(9).getTitle());
        Assert.assertEquals("Philly Cheesesteak Sloppy Joes", recipeList.get(10).getTitle());
        Assert.assertEquals("Corn and Ricotta Bruschetta", recipeList.get(11).getTitle());
        Assert.assertEquals("Crash Hot Potatoes with Smoked Salmon", recipeList.get(12).getTitle());
        Assert.assertEquals("Rice Cake with Dulce de Leche and Dark Chocolate", recipeList.get(13).getTitle());

        //User navigates to Sesame Peanut Noodles
        Recipe recipe = recipes.getRecipeById("2");
        Assert.assertEquals("Sesame Peanut Noodles", recipe.getTitle());
        Assert.assertEquals("2", recipe.getAuthorId());

        Author author = authors.getAuthorById(recipe.getAuthorId());
        Assert.assertEquals("Hannah Zimmerman", author.getName());

        //User scrolls down to view the reviews on the recipe
        List<Review> reviewList = reviews.getReviewsForRecipe(recipe);
        Assert.assertEquals(1, reviewList.size());
        Assert.assertEquals("Sheila M. Higgs-Coulthard", reviewList.get(0).getAuthor());

        resetDatabase();
    }

    @Test
    public void testAddRecipe() {
        initDatabase();

        //User opens the app and lands on the homepage, all recipes are displayed.
        List<Recipe> recipeList = recipes.getRecipes();
        Assert.assertEquals(14, recipeList.size());
        Assert.assertEquals("Lemon Cranberry Muffins", recipeList.get(0).getTitle());
        Assert.assertEquals("Honey-Garlic Slow Cooker Chicken Thighs", recipeList.get(1).getTitle());
        Assert.assertEquals("Sesame Peanut Noodles", recipeList.get(2).getTitle());
        Assert.assertEquals("Grandma's Oatmeal Cookies", recipeList.get(3).getTitle());
        Assert.assertEquals("Spinach Tofu Scramble", recipeList.get(4).getTitle());
        Assert.assertEquals("Shrimp Ceviche", recipeList.get(5).getTitle());
        Assert.assertEquals("Brazilian Cheese Bread (Pão de Queijo)", recipeList.get(6).getTitle());
        Assert.assertEquals("Sweet and Salty Three-Seed Granola", recipeList.get(7).getTitle());
        Assert.assertEquals("Bisquick Apple Coffee Cake", recipeList.get(8).getTitle());
        Assert.assertEquals("Baked Oatmeal with Mixed Berries", recipeList.get(9).getTitle());
        Assert.assertEquals("Philly Cheesesteak Sloppy Joes", recipeList.get(10).getTitle());
        Assert.assertEquals("Corn and Ricotta Bruschetta", recipeList.get(11).getTitle());
        Assert.assertEquals("Crash Hot Potatoes with Smoked Salmon", recipeList.get(12).getTitle());
        Assert.assertEquals("Rice Cake with Dulce de Leche and Dark Chocolate", recipeList.get(13).getTitle());

        //User navigates to recipe publish interface and tries to insert a valid recipe
        Author author = new Author("823ae61c-9679-4acf-9cbe-5845a240be5d", "Max Mayfield", "I am a totally new chef tryin things out!");
        Recipe recipe = new Recipe(
                "233483cc-b646-4e0b-9e6d-31a3a09cadd8",
                "Easy Sugar Cookies",
                "823ae61c-9679-4acf-9cbe-5845a240be5d",
                "Some recipe instructions.",
                IngredientList.Create(
                        new Ingredient("unsalted butter", 1, "cups"),
                        new Ingredient("granulated sugar", 0.66, "cups"),
                        new Ingredient("all-purpose flour", 2, "cups")
                ),
                8,
                Arrays.asList("Culinary", "Savory", "Appetizer", "Dinner"),
                10,
                15,
                "Easy",
                Arrays.asList("img1", "img2", "img3")
        );

        authors.insertAuthor(author);
        recipes.insertRecipe(recipe);

        //Another user browses the app and now sees the new recipe
        recipeList = recipes.getRecipes();
        Assert.assertEquals(15, recipeList.size());
        Assert.assertTrue(recipeList.contains(recipe));

        //And that user navigates to the recipe page
        recipe = recipes.getRecipeById("233483cc-b646-4e0b-9e6d-31a3a09cadd8");
        Assert.assertEquals("Easy Sugar Cookies", recipe.getTitle());
        Assert.assertEquals("823ae61c-9679-4acf-9cbe-5845a240be5d", recipe.getAuthorId());

        author = authors.getAuthorById(recipe.getAuthorId());
        Assert.assertEquals("Max Mayfield", author.getName());

        resetDatabase();
    }

    @Test
    public void testFavoritesList() {
        initDatabase();

        List<Recipe> recipeList = recipes.getFavoriteRecipes();

        Assert.assertEquals(3, recipeList.size());
        Assert.assertEquals("Honey-Garlic Slow Cooker Chicken Thighs", recipeList.get(0).getTitle());
        Assert.assertEquals("Grandma's Oatmeal Cookies", recipeList.get(1).getTitle());
        Assert.assertEquals("Sweet and Salty Three-Seed Granola", recipeList.get(2).getTitle());

        Assert.assertTrue(recipes.isRecipeFavorited(recipes.getRecipeById("1")));
        Assert.assertTrue(recipes.isRecipeFavorited(recipes.getRecipeById("3")));
        Assert.assertTrue(recipes.isRecipeFavorited(recipes.getRecipeById("7")));
        Assert.assertFalse(recipes.isRecipeFavorited(recipes.getRecipeById("0")));
        Assert.assertFalse(recipes.isRecipeFavorited(recipes.getRecipeById("2")));
        Assert.assertFalse(recipes.isRecipeFavorited(recipes.getRecipeById("9")));

        recipes.insertFavoriteRecipe(recipes.getRecipeById("9"));

        recipeList = recipes.getFavoriteRecipes();
        Assert.assertEquals(4, recipeList.size());
        Assert.assertEquals("Honey-Garlic Slow Cooker Chicken Thighs", recipeList.get(0).getTitle());
        Assert.assertEquals("Grandma's Oatmeal Cookies", recipeList.get(1).getTitle());
        Assert.assertEquals("Sweet and Salty Three-Seed Granola", recipeList.get(2).getTitle());
        Assert.assertEquals("Baked Oatmeal with Mixed Berries", recipeList.get(3).getTitle());

        Assert.assertTrue(recipes.isRecipeFavorited(recipes.getRecipeById("1")));
        Assert.assertTrue(recipes.isRecipeFavorited(recipes.getRecipeById("3")));
        Assert.assertTrue(recipes.isRecipeFavorited(recipes.getRecipeById("7")));
        Assert.assertFalse(recipes.isRecipeFavorited(recipes.getRecipeById("0")));
        Assert.assertTrue(recipes.isRecipeFavorited(recipes.getRecipeById("9")));
        Assert.assertFalse(recipes.isRecipeFavorited(recipes.getRecipeById("2")));

        recipes.deleteFavoriteRecipe(recipeList.get(1));

        recipeList = recipes.getFavoriteRecipes();
        Assert.assertEquals(3, recipeList.size());
        Assert.assertEquals("Honey-Garlic Slow Cooker Chicken Thighs", recipeList.get(0).getTitle());
        Assert.assertEquals("Sweet and Salty Three-Seed Granola", recipeList.get(1).getTitle());
        Assert.assertEquals("Baked Oatmeal with Mixed Berries", recipeList.get(2).getTitle());

        Assert.assertTrue(recipes.isRecipeFavorited(recipes.getRecipeById("1")));
        Assert.assertTrue(recipes.isRecipeFavorited(recipes.getRecipeById("7")));
        Assert.assertTrue(recipes.isRecipeFavorited(recipes.getRecipeById("9")));
        Assert.assertFalse(recipes.isRecipeFavorited(recipes.getRecipeById("0")));
        Assert.assertFalse(recipes.isRecipeFavorited(recipes.getRecipeById("3")));
        Assert.assertFalse(recipes.isRecipeFavorited(recipes.getRecipeById("2")));

        resetDatabase();
    }

    @Test
    public void testReviewSubmissionOnRecipeWithReviews() {
        initDatabase();

        // Load the recipe list
        List<Recipe> recipeList = recipes.getRecipes();

        // Pick a recipe from the list with some reviews
        Recipe recipe = recipeList.get(0);

        Assert.assertEquals("Lemon Cranberry Muffins", recipe.getTitle());

        // Look at the avg review score
        Assert.assertEquals(4.5, reviews.getAverageReviewScoreForRecipe(recipe), 0.01);

        // Look at the reviews
        List<Review> reviewList = reviews.getReviewsForRecipe(recipe);

        Assert.assertEquals(2, reviewList.size());
        Assert.assertEquals("Padma Gauthier", reviewList.get(0).getAuthor());
        Assert.assertEquals("Should up the cranberry count a little bit, otherwise awesome!", reviewList.get(0).getContent());
        Assert.assertEquals(4, reviewList.get(0).getRating());

        Assert.assertEquals("Neo Colwyn", reviewList.get(1).getAuthor());
        Assert.assertEquals("These muffins are really good!", reviewList.get(1).getContent());
        Assert.assertEquals(5, reviewList.get(1).getRating());

        // Submit a new review
        Review review = new Review("4", recipe.getId(), "thomas the tank engine", "choo choo very bad muffins for a tank engine like me", 1);
        reviews.insertReview(review);

        // Look at the avg review score again
        Assert.assertEquals(3.3, reviews.getAverageReviewScoreForRecipe(recipe), 0.1);

        // Look at the again
        reviewList = reviews.getReviewsForRecipe(recipe);

        Assert.assertEquals(3, reviewList.size());
        Assert.assertEquals("thomas the tank engine", reviewList.get(0).getAuthor());
        Assert.assertEquals("choo choo very bad muffins for a tank engine like me", reviewList.get(0).getContent());
        Assert.assertEquals(1, reviewList.get(0).getRating());

        Assert.assertEquals("Padma Gauthier", reviewList.get(1).getAuthor());
        Assert.assertEquals("Should up the cranberry count a little bit, otherwise awesome!", reviewList.get(1).getContent());
        Assert.assertEquals(4, reviewList.get(1).getRating());

        Assert.assertEquals("Neo Colwyn", reviewList.get(2).getAuthor());
        Assert.assertEquals("These muffins are really good!", reviewList.get(2).getContent());
        Assert.assertEquals(5, reviewList.get(2).getRating());

        resetDatabase();
    }

    @Test
    public void testReviewSubmissionOnRecipeWithoutReviews() {
        initDatabase();

        // Load the recipe list
        List<Recipe> recipeList = recipes.getRecipes();

        // Pick a recipe from the list with no reviews
        Recipe recipe = recipeList.get(8);

        Assert.assertEquals("Bisquick Apple Coffee Cake", recipe.getTitle());

        // Look at the avg review score
        Assert.assertEquals(0.0, reviews.getAverageReviewScoreForRecipe(recipe), 0.01);

        // Look at the reviews
        List<Review> reviewList = reviews.getReviewsForRecipe(recipe);

        Assert.assertEquals(0, reviewList.size());

        // Submit a new review
        Review review = new Review("4", recipe.getId(), "ruby bunny", "apples are very yum", 5);
        reviews.insertReview(review);

        // Look at the avg review score again
        Assert.assertEquals(5.0, reviews.getAverageReviewScoreForRecipe(recipe), 0.1);

        // Look at the again
        reviewList = reviews.getReviewsForRecipe(recipe);

        Assert.assertEquals(1, reviewList.size());
        Assert.assertEquals("ruby bunny", reviewList.get(0).getAuthor());
        Assert.assertEquals("apples are very yum", reviewList.get(0).getContent());
        Assert.assertEquals(5, reviewList.get(0).getRating());

        resetDatabase();
    }

    private void initDatabase() {
        if (RunIntegrationTests.USE_STUBDATASTORE) {
            Services.createDataStore(new StubDataStore());
        } else {
            Services.createDataStore();
        }

        authors = new AccessAuthors();
        recipes = new AccessRecipes();
        reviews = new AccessReviews();
    }

    private void resetDatabase() {
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
