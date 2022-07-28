package comp3350.cookit.tests.integration;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import comp3350.cookit.application.Main;
import comp3350.cookit.application.Services;
import comp3350.cookit.business.AccessAuthors;
import comp3350.cookit.business.AccessRecipes;
import comp3350.cookit.business.AccessReviews;
import comp3350.cookit.objects.Author;
import comp3350.cookit.objects.Ingredient;
import comp3350.cookit.objects.IngredientList;
import comp3350.cookit.objects.Recipe;
import comp3350.cookit.objects.Review;
import comp3350.cookit.persistence.HsqldbDataStore;
import comp3350.cookit.persistence.IDataStore;
import comp3350.cookit.tests.RunIntegrationTests;
import comp3350.cookit.tests.persistence.StubDataStore;

public class IDataStoreTests {
    IDataStore dataStore;

    public IDataStoreTests() {

    }

    @Test
    public void testRecipeList() {
        initDataStore();

        List<Recipe> recipes = dataStore.getAllRecipes();

        Assert.assertEquals(14, recipes.size());

        Assert.assertEquals("0", recipes.get(0).getId());
        Assert.assertEquals("Lemon Cranberry Muffins", recipes.get(0).getTitle());
        Assert.assertEquals("0", recipes.get(0).getAuthorId());
        Assert.assertEquals("1. Preheat oven to 400F. Grease 12 muffin cups, or line with paper muffin liners.\n\n2. Combine flour, sugar, baking powder, and salt in a large bowl. Mix lemon juice and milk in a measuring cup, to sour milk; beat eggs, oil, and milk mixture in a bowl. Stir egg mixture into flour mixture until just moistened; fold in cranberries. Fill prepared muffin cups two-thirds full; sprinkle with almonds.\n\n3. Bake in preheated oven until a toothpick inserted into a muffin comes out clean, 18 to 20 minutes. Cool for 5 minutes before removing from pan to wire rack.", recipes.get(0).getContent());
        Assert.assertEquals(12, recipes.get(0).getServingSize());
        Assert.assertEquals(30, recipes.get(0).getPrepTime());
        Assert.assertEquals(20, recipes.get(0).getCookTime());
        Assert.assertEquals("Medium", recipes.get(0).getDifficulty());

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
        Assert.assertEquals(30, recipes.get(1).getPrepTime());
        Assert.assertEquals(360, recipes.get(1).getCookTime());
        Assert.assertEquals("Easy", recipes.get(1).getDifficulty());

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
    public void testFavoritesList() {
        initDataStore();

        List<Recipe> recipes = dataStore.getFavoriteRecipes();

        for (Recipe r : recipes) {
            System.out.println(r.getTitle());
        }

        Assert.assertEquals(3, recipes.size());
        Assert.assertEquals("Honey-Garlic Slow Cooker Chicken Thighs", recipes.get(0).getTitle());
        Assert.assertEquals("Grandma's Oatmeal Cookies", recipes.get(1).getTitle());
        Assert.assertEquals("Sweet and Salty Three-Seed Granola", recipes.get(2).getTitle());

        dataStore.insertFavoriteRecipe("9");

        recipes = dataStore.getFavoriteRecipes();
        Assert.assertEquals(4, recipes.size());
        Assert.assertEquals("Honey-Garlic Slow Cooker Chicken Thighs", recipes.get(0).getTitle());
        Assert.assertEquals("Grandma's Oatmeal Cookies", recipes.get(1).getTitle());
        Assert.assertEquals("Sweet and Salty Three-Seed Granola", recipes.get(2).getTitle());
        Assert.assertEquals("Baked Oatmeal with Mixed Berries", recipes.get(3).getTitle());

        dataStore.deleteFavoriteRecipe("3");

        recipes = dataStore.getFavoriteRecipes();
        Assert.assertEquals(3, recipes.size());
        Assert.assertEquals("Honey-Garlic Slow Cooker Chicken Thighs", recipes.get(0).getTitle());
        Assert.assertEquals("Sweet and Salty Three-Seed Granola", recipes.get(1).getTitle());
        Assert.assertEquals("Baked Oatmeal with Mixed Berries", recipes.get(2).getTitle());

        resetDatabase();
    }

    @Test
    public void testAuthorList() {
        initDataStore();

        List<Author> authors = dataStore.getAllAuthors();

        Assert.assertEquals(11, authors.size());

        Assert.assertEquals("0", authors.get(0).getId());
        Assert.assertEquals("bobpiazza", authors.get(0).getName());
        Assert.assertEquals("I love making muffins. Find me on allrecipes: https://www.allrecipes.com/cook/2955506", authors.get(0).getBio());

        Assert.assertEquals("1", authors.get(1).getId());
        Assert.assertEquals("Myrna", authors.get(1).getName());
        Assert.assertEquals("Find me on allrecipes: https://www.allrecipes.com/cook/2792648?content=recipes", authors.get(1).getBio());

        Assert.assertEquals("2", authors.get(2).getId());
        Assert.assertEquals("Hannah Zimmerman", authors.get(2).getName());
        Assert.assertEquals("Hannah has been working as a food photographer and recipe developer since 2018, and has worked with brands & publications across the U.S. to create mouth-watering food content. She began contributing to Simply Recipes in 2021. See them on Simply Recipes: https://www.simplyrecipes.com/hannah-zimmerman-5195688", authors.get(2).getBio());

        Assert.assertEquals("5", authors.get(5).getId());
        Assert.assertEquals("Shilpa Uskokovic", authors.get(5).getName());
        Assert.assertEquals("Shilpa Uskokovic is a food editor and recipe developer based in NYC. She was previously a line and pastry cook in some of NYC's top rated restaurants like Marea, The NoMad Hotel, Maialino and Perry Street. A graduate of The Culinary Institute of America, Shilpa loves reading, grocery shopping, and eating a little too much cake . She was born and raised in Chennai, India. Find them on Simply Recipes: https://www.simplyrecipes.com/shilpa-uskokovic-5219561", authors.get(5).getBio());

        Assert.assertEquals("6", authors.get(6).getId());
        Assert.assertEquals("Ariane Resnick", authors.get(6).getName());
        Assert.assertEquals("Ariane is the author of The Bone Broth Miracle (2015), The Thinking Girl's Guide to Drinking (2016), Wake/Sleep (2018), How to Be Well When You're Not (foreword by P!nk, 2018), and Disney Princess Healthy Treats (2021). She has been a contributor since 2020 for Simply Recipes but began professional writing 15 years ago for Provincetown, Massachusetts newspaper and Curve Magazine. Find them on Simply Recipes: https://www.simplyrecipes.com/ariane-resnick-5091819", authors.get(6).getBio());

        Assert.assertEquals("9", authors.get(9).getId());
        Assert.assertEquals("Coco Morante", authors.get(9).getName());
        Assert.assertEquals("Coco started a food blog in 2011, and soon after that began managing social media and developing recipes for Garlic Gold, an organic foods company. She began developing recipes professionally for The Kitchn in 2014, joined Simply Recipes as a contributor in 2016, and published her first cookbook, The Essential Instant Pot Cookbook, with Ten Speed Press in 2017. Coco has written five cookbooks in total, with a new project in the works presently. Find them on Simply Recipes: https://www.simplyrecipes.com/coco-morante-5091788", authors.get(9).getBio());

        Assert.assertEquals("10", authors.get(10).getId());
        Assert.assertEquals("Micah Siva", authors.get(10).getName());
        Assert.assertEquals("Micah Siva is a trained chef, registered dietitian, recipe writer, and food photographer, specializing in modern Jewish cuisine. Find them on Simply Recipes: https://www.simplyrecipes.com/micah-siva-5270458", authors.get(10).getBio());

        resetDatabase();
    }

    @Test
    public void testReviewList() {
        initDataStore();

        List<Review> reviews = dataStore.getAllReviews();

        Assert.assertEquals(4, reviews.size());

        Assert.assertEquals("3", reviews.get(0).getId());
        Assert.assertEquals("2", reviews.get(0).getRecipeId());
        Assert.assertEquals("Sheila M. Higgs-Coulthard", reviews.get(0).getAuthor());
        Assert.assertEquals("I am restricted with my intake of starch, also various vegetables, this recipe meets all my restrictions yet leaving you happy and satisfied, thank you very much.", reviews.get(0).getContent());
        Assert.assertEquals(5, reviews.get(0).getRating());

        Assert.assertEquals("2", reviews.get(1).getId());
        Assert.assertEquals("1", reviews.get(1).getRecipeId());
        Assert.assertEquals("Lara Hanna", reviews.get(1).getAuthor());
        Assert.assertEquals("Too much ketchup.", reviews.get(1).getContent());
        Assert.assertEquals(2, reviews.get(1).getRating());

        Assert.assertEquals("1", reviews.get(2).getId());
        Assert.assertEquals("0", reviews.get(2).getRecipeId());
        Assert.assertEquals("Padma Gauthier", reviews.get(2).getAuthor());
        Assert.assertEquals("Should up the cranberry count a little bit, otherwise awesome!", reviews.get(2).getContent());
        Assert.assertEquals(4, reviews.get(2).getRating());

        Assert.assertEquals("0", reviews.get(3).getId());
        Assert.assertEquals("0", reviews.get(3).getRecipeId());
        Assert.assertEquals("Neo Colwyn", reviews.get(3).getAuthor());
        Assert.assertEquals("These muffins are really good!", reviews.get(3).getContent());
        Assert.assertEquals(5, reviews.get(3).getRating());

        resetDatabase();
    }

    @Test
    public void testNewRecipeFlow() {
        initDataStore();

        List<Author> authors = dataStore.getAllAuthors();
        Assert.assertEquals(11, authors.size());

        List<Recipe> recipes = dataStore.getAllRecipes();
        Assert.assertEquals(14, recipes.size());

        IngredientList il = IngredientList.Create(new Ingredient("all-purpose flour", 10.0, "cups"));
        Author a = new Author("11", "An Author", "Hi, I am an author!");
        Recipe r = new Recipe("14", "Title", "5", "Content", il, 2, Arrays.asList("Some", "Tags"), 10, 20, "Easy", Arrays.asList("Image1", "Image2"));

        dataStore.insertAuthor(a);
        dataStore.insertRecipe(r);

        authors = dataStore.getAllAuthors();
        Assert.assertEquals(12, authors.size());

        Assert.assertEquals(a.getId(), authors.get(11).getId());
        Assert.assertEquals(a.getName(), authors.get(11).getName());
        Assert.assertEquals(a.getBio(), authors.get(11).getBio());

        recipes = dataStore.getAllRecipes();
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

        Assert.assertEquals(2, recipes.get(14).getTags().size());
        Assert.assertEquals("Some", recipes.get(14).getTags().get(0));
        Assert.assertEquals("Tags", recipes.get(14).getTags().get(1));

        Assert.assertEquals(2, recipes.get(14).getImages().size());
        Assert.assertEquals("Image1", recipes.get(14).getImages().get(0));
        Assert.assertEquals("Image2", recipes.get(14).getImages().get(1));

        List<Recipe> taggedRecipes = dataStore.getRecipesWithTag("Some");
        Assert.assertEquals(1, taggedRecipes.size());
        Assert.assertTrue(taggedRecipes.contains(r));

        taggedRecipes = dataStore.getRecipesWithTag("Tags");
        Assert.assertEquals(1, taggedRecipes.size());
        Assert.assertTrue(taggedRecipes.contains(r));

        resetDatabase();
    }

    @Test
    public void testNewReviewFlow() {
        initDataStore();

        List<Review> reviews = dataStore.getAllReviews();
        Assert.assertEquals(4, reviews.size());

        Review r = new Review("4", "1", "Richard Hendricks", "tastes like chicken", 5);
        dataStore.insertReview(r);

        reviews = dataStore.getAllReviews();
        Assert.assertEquals(5, reviews.size());

        Assert.assertEquals(r.getId(), reviews.get(0).getId());
        Assert.assertEquals(r.getRecipeId(), reviews.get(0).getRecipeId());
        Assert.assertEquals(r.getAuthor(), reviews.get(0).getAuthor());
        Assert.assertEquals(r.getContent(), reviews.get(0).getContent());
        Assert.assertEquals(r.getRating(), reviews.get(0).getRating());

        resetDatabase();
    }

    @Test
    public void testTypicalRecipeUpdateSingleField() {
        initDataStore();

        Recipe dbRecipe = dataStore.getRecipeById("0");
        Assert.assertEquals("0", dbRecipe.getId());
        Assert.assertEquals("Lemon Cranberry Muffins", dbRecipe.getTitle());
        Assert.assertEquals(12, dbRecipe.getServingSize());

        Recipe modifiedRecipe = new Recipe(dbRecipe.getId(), "Bran Muffins", dbRecipe.getAuthorId(), dbRecipe.getContent(), dbRecipe.getIngredientList(), dbRecipe.getServingSize(), dbRecipe.getTags(), dbRecipe.getPrepTime(), dbRecipe.getCookTime(), dbRecipe.getDifficulty(), dbRecipe.getImages());
        dataStore.updateRecipe(modifiedRecipe);

        dbRecipe = dataStore.getRecipeById("0");
        Assert.assertEquals("0", dbRecipe.getId());
        Assert.assertEquals("Bran Muffins", dbRecipe.getTitle());
        Assert.assertEquals(12, dbRecipe.getServingSize());

        modifiedRecipe = new Recipe(dbRecipe.getId(), dbRecipe.getTitle(), dbRecipe.getAuthorId(), dbRecipe.getContent(), dbRecipe.getIngredientList(), 3, dbRecipe.getTags(), dbRecipe.getPrepTime(), dbRecipe.getCookTime(), dbRecipe.getDifficulty(), dbRecipe.getImages());
        dataStore.updateRecipe(modifiedRecipe);

        dbRecipe = dataStore.getRecipeById("0");
        Assert.assertEquals("0", dbRecipe.getId());
        Assert.assertEquals("Bran Muffins", dbRecipe.getTitle());
        Assert.assertEquals(3, dbRecipe.getServingSize());

        resetDatabase();
    }

    @Test
    public void testTypicalRecipeUpdateMultiField() {
        initDataStore();

        Recipe dbRecipe = dataStore.getRecipeById("1");
        Assert.assertEquals("1", dbRecipe.getId());
        Assert.assertEquals("Honey-Garlic Slow Cooker Chicken Thighs", dbRecipe.getTitle());
        Assert.assertEquals(4, dbRecipe.getServingSize());
        Assert.assertEquals("1. Lay chicken thighs into the bottom of a 4-quart slow cooker.\n\n2. Whisk soy sauce, ketchup, honey, garlic, and basil together in a bowl; pour over the chicken.\n\n3. Cook on Low for 6 hours.", dbRecipe.getContent());


        List<String> newTags = dbRecipe.getTags();
        newTags.add("Breakfast");
        Recipe modifiedRecipe = new Recipe(dbRecipe.getId(), "Blackened Chicken Breast", dbRecipe.getAuthorId(), "Some new content", dbRecipe.getIngredientList(), 1, newTags, dbRecipe.getPrepTime(), dbRecipe.getCookTime(), dbRecipe.getDifficulty(), dbRecipe.getImages());
        dataStore.updateRecipe(modifiedRecipe);

        dbRecipe = dataStore.getRecipeById("1");
        Assert.assertEquals("1", dbRecipe.getId());
        Assert.assertEquals("Blackened Chicken Breast", dbRecipe.getTitle());
        Assert.assertEquals(1, dbRecipe.getServingSize());
        Assert.assertEquals("Some new content", dbRecipe.getContent());
        Assert.assertTrue(dbRecipe.getTags().contains("Breakfast"));

        List<Recipe> taggedRecipes = dataStore.getRecipesWithTag("Breakfast");
        Assert.assertTrue(taggedRecipes.contains(dbRecipe));

        resetDatabase();
    }

    @Test
    public void testInvalidRecipeUpdate() {
        initDataStore();

        List<Recipe> recipes = dataStore.getAllRecipes();
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

        Recipe invalid = new Recipe("96", "Recipe with non-existent ID", "0", "Some content", recipes.get(0).getIngredientList(), 1, Arrays.asList("Some", "tags"), 20, 40, "Hard", Arrays.asList("img1", "img2"));
        dataStore.updateRecipe(invalid);

        recipes = dataStore.getAllRecipes();
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

        dataStore.updateRecipe(null);

        recipes = dataStore.getAllRecipes();
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
        initDataStore();

        Assert.assertNull(dataStore.getRecipeById("37"));
        Assert.assertNull(dataStore.getRecipeById(null));

        resetDatabase();
    }

    @Test
    public void testTypicalRecipeDelete() {
        initDataStore();

        List<Recipe> recipes = dataStore.getAllRecipes();
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

        List<Recipe> taggedRecipes = dataStore.getRecipesWithTag("Sweet");
        Assert.assertEquals(6, taggedRecipes.size());
        taggedRecipes = dataStore.getRecipesWithTag("Savory");
        Assert.assertEquals(8, taggedRecipes.size());

        dataStore.deleteRecipe(recipes.get(0));

        recipes = dataStore.getAllRecipes();
        Assert.assertEquals(13, recipes.size());
        Assert.assertEquals("1", recipes.get(0).getId());

        taggedRecipes = dataStore.getRecipesWithTag("Sweet");
        Assert.assertEquals(5, taggedRecipes.size());
        taggedRecipes = dataStore.getRecipesWithTag("Savory");
        Assert.assertEquals(8, taggedRecipes.size());

        dataStore.deleteRecipe(recipes.get(0));

        recipes = dataStore.getAllRecipes();
        Assert.assertEquals(12, recipes.size());

        taggedRecipes = dataStore.getRecipesWithTag("Sweet");
        Assert.assertEquals(5, taggedRecipes.size());
        taggedRecipes = dataStore.getRecipesWithTag("Savory");
        Assert.assertEquals(7, taggedRecipes.size());

        resetDatabase();
    }

    @Test
    public void testInvalidRecipeDelete() {
        initDataStore();

        List<Recipe> recipes = dataStore.getAllRecipes();
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

        Recipe invalid = new Recipe("36", "Recipe with non-existant ID", "0", "Some content", recipes.get(0).getIngredientList(), 1, Arrays.asList("Some", "tags"), 15, 5, "Impossible", Arrays.asList("img1", "img2"));
        dataStore.deleteRecipe(invalid);

        recipes = dataStore.getAllRecipes();
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

        dataStore.deleteRecipe(null);

        recipes = dataStore.getAllRecipes();
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

    @Test
    public void testTypicalAuthorUpdateSingleField() {
        initDataStore();

        Author dbAuthor = dataStore.getAuthorById("0");
        Assert.assertEquals("0", dbAuthor.getId());
        Assert.assertEquals("bobpiazza", dbAuthor.getName());
        Assert.assertEquals("I love making muffins. Find me on allrecipes: https://www.allrecipes.com/cook/2955506", dbAuthor.getBio());

        Author modifiedAuthor = new Author(dbAuthor.getId(), "some other person", dbAuthor.getBio());
        dataStore.updateAuthor(modifiedAuthor);

        dbAuthor = dataStore.getAuthorById("0");
        Assert.assertEquals("0", dbAuthor.getId());
        Assert.assertEquals("some other person", dbAuthor.getName());
        Assert.assertEquals("I love making muffins. Find me on allrecipes: https://www.allrecipes.com/cook/2955506", dbAuthor.getBio());


        modifiedAuthor = new Author(dbAuthor.getId(), dbAuthor.getName(), "This is a new bio!");
        dataStore.updateAuthor(modifiedAuthor);

        dbAuthor = dataStore.getAuthorById("0");
        Assert.assertEquals("0", dbAuthor.getId());
        Assert.assertEquals("some other person", dbAuthor.getName());
        Assert.assertEquals("This is a new bio!", dbAuthor.getBio());

        resetDatabase();
    }

    @Test
    public void testTypicalAuthorUpdateMultiField() {
        initDataStore();

        Author dbAuthor = dataStore.getAuthorById("0");
        Assert.assertEquals("0", dbAuthor.getId());
        Assert.assertEquals("bobpiazza", dbAuthor.getName());
        Assert.assertEquals("I love making muffins. Find me on allrecipes: https://www.allrecipes.com/cook/2955506", dbAuthor.getBio());

        Author modifiedAuthor = new Author(dbAuthor.getId(), "bob the builder", "can we fix it?");
        dataStore.updateAuthor(modifiedAuthor);

        dbAuthor = dataStore.getAuthorById("0");
        Assert.assertEquals("0", dbAuthor.getId());
        Assert.assertEquals("bob the builder", dbAuthor.getName());
        Assert.assertEquals("can we fix it?", dbAuthor.getBio());

        resetDatabase();
    }

    @Test
    public void testInvalidAuthorUpdate() {
        initDataStore();

        List<Author> authors = dataStore.getAllAuthors();
        Assert.assertEquals(11, authors.size());
        Assert.assertEquals("bobpiazza", authors.get(0).getName());
        Assert.assertEquals("Myrna", authors.get(1).getName());
        Assert.assertEquals("Hannah Zimmerman", authors.get(2).getName());
        Assert.assertEquals("Elise Bauer", authors.get(3).getName());
        Assert.assertEquals("Sara Bir", authors.get(4).getName());
        Assert.assertEquals("Shilpa Uskokovic", authors.get(5).getName());
        Assert.assertEquals("Ariane Resnick", authors.get(6).getName());
        Assert.assertEquals("Nick Evans", authors.get(7).getName());
        Assert.assertEquals("Georgia Freedman", authors.get(8).getName());
        Assert.assertEquals("Coco Morante", authors.get(9).getName());
        Assert.assertEquals("Micah Siva", authors.get(10).getName());

        Author invalid = new Author("33", "thomas the tank engine", "It was time for Thomas to leave. He had seen everything.");
        dataStore.updateAuthor(invalid);

        authors = dataStore.getAllAuthors();
        Assert.assertEquals(11, authors.size());
        Assert.assertEquals("bobpiazza", authors.get(0).getName());
        Assert.assertEquals("Myrna", authors.get(1).getName());
        Assert.assertEquals("Hannah Zimmerman", authors.get(2).getName());
        Assert.assertEquals("Elise Bauer", authors.get(3).getName());
        Assert.assertEquals("Sara Bir", authors.get(4).getName());
        Assert.assertEquals("Shilpa Uskokovic", authors.get(5).getName());
        Assert.assertEquals("Ariane Resnick", authors.get(6).getName());
        Assert.assertEquals("Nick Evans", authors.get(7).getName());
        Assert.assertEquals("Georgia Freedman", authors.get(8).getName());
        Assert.assertEquals("Coco Morante", authors.get(9).getName());
        Assert.assertEquals("Micah Siva", authors.get(10).getName());
        dataStore.updateAuthor(null);

        authors = dataStore.getAllAuthors();
        Assert.assertEquals(11, authors.size());
        Assert.assertEquals("bobpiazza", authors.get(0).getName());
        Assert.assertEquals("Myrna", authors.get(1).getName());
        Assert.assertEquals("Hannah Zimmerman", authors.get(2).getName());
        Assert.assertEquals("Elise Bauer", authors.get(3).getName());
        Assert.assertEquals("Sara Bir", authors.get(4).getName());
        Assert.assertEquals("Shilpa Uskokovic", authors.get(5).getName());
        Assert.assertEquals("Ariane Resnick", authors.get(6).getName());
        Assert.assertEquals("Nick Evans", authors.get(7).getName());
        Assert.assertEquals("Georgia Freedman", authors.get(8).getName());
        Assert.assertEquals("Coco Morante", authors.get(9).getName());
        Assert.assertEquals("Micah Siva", authors.get(10).getName());

        resetDatabase();
    }

    @Test
    public void testInvalidAuthorIdLookup() {
        initDataStore();

        Assert.assertNull(dataStore.getAuthorById("42"));
        Assert.assertNull(dataStore.getAuthorById(null));

        resetDatabase();
    }

    @Test
    public void testTypicalAuthorDelete() {
        initDataStore();

        List<Author> authors = dataStore.getAllAuthors();
        Assert.assertEquals(11, authors.size());
        Assert.assertEquals("0", authors.get(0).getId());
        Assert.assertEquals("1", authors.get(1).getId());
        Assert.assertEquals("2", authors.get(2).getId());
        Assert.assertEquals("3", authors.get(3).getId());
        Assert.assertEquals("4", authors.get(4).getId());
        Assert.assertEquals("5", authors.get(5).getId());
        Assert.assertEquals("6", authors.get(6).getId());
        Assert.assertEquals("7", authors.get(7).getId());
        Assert.assertEquals("8", authors.get(8).getId());
        Assert.assertEquals("9", authors.get(9).getId());
        Assert.assertEquals("10", authors.get(10).getId());

        dataStore.deleteAuthor(authors.get(0));

        authors = dataStore.getAllAuthors();
        Assert.assertEquals(10, authors.size());
        Assert.assertEquals("1", authors.get(0).getId());
        Assert.assertEquals("2", authors.get(1).getId());
        Assert.assertEquals("3", authors.get(2).getId());
        Assert.assertEquals("4", authors.get(3).getId());
        Assert.assertEquals("5", authors.get(4).getId());
        Assert.assertEquals("6", authors.get(5).getId());
        Assert.assertEquals("7", authors.get(6).getId());
        Assert.assertEquals("8", authors.get(7).getId());
        Assert.assertEquals("9", authors.get(8).getId());
        Assert.assertEquals("10", authors.get(9).getId());

        dataStore.deleteAuthor(authors.get(3));

        authors = dataStore.getAllAuthors();
        Assert.assertEquals(9, authors.size());
        Assert.assertEquals("1", authors.get(0).getId());
        Assert.assertEquals("2", authors.get(1).getId());
        Assert.assertEquals("3", authors.get(2).getId());
        Assert.assertEquals("5", authors.get(3).getId());
        Assert.assertEquals("6", authors.get(4).getId());
        Assert.assertEquals("7", authors.get(5).getId());
        Assert.assertEquals("8", authors.get(6).getId());
        Assert.assertEquals("9", authors.get(7).getId());
        Assert.assertEquals("10", authors.get(8).getId());

        dataStore.deleteAuthor(authors.get(8));

        authors = dataStore.getAllAuthors();
        Assert.assertEquals(8, authors.size());
        Assert.assertEquals("1", authors.get(0).getId());
        Assert.assertEquals("2", authors.get(1).getId());
        Assert.assertEquals("3", authors.get(2).getId());
        Assert.assertEquals("5", authors.get(3).getId());
        Assert.assertEquals("6", authors.get(4).getId());
        Assert.assertEquals("7", authors.get(5).getId());
        Assert.assertEquals("8", authors.get(6).getId());
        Assert.assertEquals("9", authors.get(7).getId());

        resetDatabase();
    }

    @Test
    public void testInvalidAuthorDelete() {
        initDataStore();

        List<Author> authors = dataStore.getAllAuthors();
        Assert.assertEquals(11, authors.size());
        Assert.assertEquals("0", authors.get(0).getId());
        Assert.assertEquals("1", authors.get(1).getId());
        Assert.assertEquals("2", authors.get(2).getId());
        Assert.assertEquals("3", authors.get(3).getId());
        Assert.assertEquals("4", authors.get(4).getId());
        Assert.assertEquals("5", authors.get(5).getId());
        Assert.assertEquals("6", authors.get(6).getId());
        Assert.assertEquals("7", authors.get(7).getId());
        Assert.assertEquals("8", authors.get(8).getId());
        Assert.assertEquals("9", authors.get(9).getId());
        Assert.assertEquals("10", authors.get(10).getId());

        Author invalid = new Author("66", "perry the platypus", "[platypus noises]");
        dataStore.deleteAuthor(invalid);

        authors = dataStore.getAllAuthors();
        Assert.assertEquals(11, authors.size());
        Assert.assertEquals("0", authors.get(0).getId());
        Assert.assertEquals("1", authors.get(1).getId());
        Assert.assertEquals("2", authors.get(2).getId());
        Assert.assertEquals("3", authors.get(3).getId());
        Assert.assertEquals("4", authors.get(4).getId());
        Assert.assertEquals("5", authors.get(5).getId());
        Assert.assertEquals("6", authors.get(6).getId());
        Assert.assertEquals("7", authors.get(7).getId());
        Assert.assertEquals("8", authors.get(8).getId());
        Assert.assertEquals("9", authors.get(9).getId());
        Assert.assertEquals("10", authors.get(10).getId());

        dataStore.deleteAuthor(null);

        authors = dataStore.getAllAuthors();
        Assert.assertEquals(11, authors.size());
        Assert.assertEquals("0", authors.get(0).getId());
        Assert.assertEquals("1", authors.get(1).getId());
        Assert.assertEquals("2", authors.get(2).getId());
        Assert.assertEquals("3", authors.get(3).getId());
        Assert.assertEquals("4", authors.get(4).getId());
        Assert.assertEquals("5", authors.get(5).getId());
        Assert.assertEquals("6", authors.get(6).getId());
        Assert.assertEquals("7", authors.get(7).getId());
        Assert.assertEquals("8", authors.get(8).getId());
        Assert.assertEquals("9", authors.get(9).getId());
        Assert.assertEquals("10", authors.get(10).getId());

        resetDatabase();
    }

    @Test
    public void testTypicalReviewUpdateSingleField() {
        initDataStore();

        Review dbReview = dataStore.getReviewById("0");
        Assert.assertEquals("0", dbReview.getId());
        Assert.assertEquals("Neo Colwyn", dbReview.getAuthor());
        Assert.assertEquals(5, dbReview.getRating());

        Review modifiedReview = new Review(dbReview.getId(), dbReview.getRecipeId(), "New author", dbReview.getContent(), dbReview.getRating());
        dataStore.updateReview(modifiedReview);

        dbReview = dataStore.getReviewById("0");
        Assert.assertEquals("0", dbReview.getId());
        Assert.assertEquals("New author", dbReview.getAuthor());
        Assert.assertEquals(5, dbReview.getRating());

        modifiedReview = new Review(dbReview.getId(), dbReview.getRecipeId(), dbReview.getAuthor(), dbReview.getContent(), 3);
        dataStore.updateReview(modifiedReview);

        dbReview = dataStore.getReviewById("0");
        Assert.assertEquals("0", dbReview.getId());
        Assert.assertEquals("New author", dbReview.getAuthor());
        Assert.assertEquals(3, dbReview.getRating());

        resetDatabase();
    }

    @Test
    public void testTypicalReviewUpdateMultiField() {
        initDataStore();

        Review dbReview = dataStore.getReviewById("0");
        Assert.assertEquals("0", dbReview.getId());
        Assert.assertEquals("Neo Colwyn", dbReview.getAuthor());
        Assert.assertEquals(5, dbReview.getRating());

        Review modifiedReview = new Review(dbReview.getId(), dbReview.getRecipeId(), "Different author", dbReview.getContent(), 2);
        dataStore.updateReview(modifiedReview);

        dbReview = dataStore.getReviewById("0");
        Assert.assertEquals("0", dbReview.getId());
        Assert.assertEquals("Different author", dbReview.getAuthor());
        Assert.assertEquals(2, dbReview.getRating());

        resetDatabase();
    }

    @Test
    public void testInvalidReviewUpdate() {
        initDataStore();

        List<Review> reviews = dataStore.getAllReviews();
        Assert.assertEquals(4, reviews.size());
        Assert.assertEquals("Sheila M. Higgs-Coulthard", reviews.get(0).getAuthor());
        Assert.assertEquals("Lara Hanna", reviews.get(1).getAuthor());
        Assert.assertEquals("Padma Gauthier", reviews.get(2).getAuthor());
        Assert.assertEquals("Neo Colwyn", reviews.get(3).getAuthor());

        Review invalid = new Review("86", "0", "Vladilena Milize", "this is a good recipe", 5);
        dataStore.updateReview(invalid);

        reviews = dataStore.getAllReviews();
        Assert.assertEquals(4, reviews.size());
        Assert.assertEquals("Sheila M. Higgs-Coulthard", reviews.get(0).getAuthor());
        Assert.assertEquals("Lara Hanna", reviews.get(1).getAuthor());
        Assert.assertEquals("Padma Gauthier", reviews.get(2).getAuthor());
        Assert.assertEquals("Neo Colwyn", reviews.get(3).getAuthor());

        dataStore.updateReview(null);

        reviews = dataStore.getAllReviews();
        Assert.assertEquals(4, reviews.size());
        Assert.assertEquals("Sheila M. Higgs-Coulthard", reviews.get(0).getAuthor());
        Assert.assertEquals("Lara Hanna", reviews.get(1).getAuthor());
        Assert.assertEquals("Padma Gauthier", reviews.get(2).getAuthor());
        Assert.assertEquals("Neo Colwyn", reviews.get(3).getAuthor());

        resetDatabase();
    }

    @Test
    public void testInvalidReviewIdLookup() {
        initDataStore();

        Assert.assertNull(dataStore.getReviewById("58"));
        Assert.assertNull(dataStore.getReviewById(null));

        resetDatabase();
    }

    @Test
    public void testTypicalReviewDelete() {
        initDataStore();

        List<Review> reviews = dataStore.getAllReviews();
        Assert.assertEquals(4, reviews.size());
        Assert.assertEquals("3", reviews.get(0).getId());
        Assert.assertEquals("2", reviews.get(1).getId());
        Assert.assertEquals("1", reviews.get(2).getId());
        Assert.assertEquals("0", reviews.get(3).getId());

        dataStore.deleteReview(reviews.get(1));

        reviews = dataStore.getAllReviews();
        Assert.assertEquals(3, reviews.size());
        Assert.assertEquals("3", reviews.get(0).getId());
        Assert.assertEquals("1", reviews.get(1).getId());
        Assert.assertEquals("0", reviews.get(2).getId());

        dataStore.deleteReview(reviews.get(0));

        reviews = dataStore.getAllReviews();
        Assert.assertEquals(2, reviews.size());
        Assert.assertEquals("1", reviews.get(0).getId());
        Assert.assertEquals("0", reviews.get(1).getId());

        dataStore.deleteReview(reviews.get(0));

        reviews = dataStore.getAllReviews();
        Assert.assertEquals(1, reviews.size());
        Assert.assertEquals("0", reviews.get(0).getId());

        resetDatabase();
    }

    @Test
    public void testInvalidReviewDelete() {
        initDataStore();

        List<Review> reviews = dataStore.getAllReviews();
        Assert.assertEquals(4, reviews.size());
        Assert.assertEquals("3", reviews.get(0).getId());
        Assert.assertEquals("2", reviews.get(1).getId());
        Assert.assertEquals("1", reviews.get(2).getId());
        Assert.assertEquals("0", reviews.get(3).getId());

        Review invalid = new Review("94", "0", "franklin", "[turtle noises]", 5);
        dataStore.deleteReview(invalid);

        reviews = dataStore.getAllReviews();
        Assert.assertEquals(4, reviews.size());
        Assert.assertEquals("3", reviews.get(0).getId());
        Assert.assertEquals("2", reviews.get(1).getId());
        Assert.assertEquals("1", reviews.get(2).getId());
        Assert.assertEquals("0", reviews.get(3).getId());

        dataStore.deleteReview(null);

        reviews = dataStore.getAllReviews();
        Assert.assertEquals(4, reviews.size());
        Assert.assertEquals("3", reviews.get(0).getId());
        Assert.assertEquals("2", reviews.get(1).getId());
        Assert.assertEquals("1", reviews.get(2).getId());
        Assert.assertEquals("0", reviews.get(3).getId());

        resetDatabase();
    }

    private IDataStore initDataStore() {
        if (RunIntegrationTests.USE_STUBDATASTORE) {
            dataStore = new StubDataStore();
            dataStore.open("stub");

        } else {
            dataStore = new HsqldbDataStore();
            dataStore.open(Main.getDBPath());
        }

        return dataStore;
    }

    private void resetDatabase() {
        try {
            if (dataStore != null) {
                dataStore.close();
            }

            if (!RunIntegrationTests.USE_STUBDATASTORE) {
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
