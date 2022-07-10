package comp3350.cookit.tests.persistence;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import comp3350.cookit.objects.Author;
import comp3350.cookit.objects.Ingredient;
import comp3350.cookit.objects.IngredientList;
import comp3350.cookit.objects.Recipe;
import comp3350.cookit.objects.Review;
import comp3350.cookit.persistence.IDataStore;

public class IDataStoreTests {
    public IDataStoreTests() {

    }

    @Test
    public void testRecipeList() {
        IDataStore dataStore = initDataStore();

        List<Recipe> recipes = dataStore.getAllRecipes();

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
        Assert.assertEquals("Breakfast", tags.get(0));
        Assert.assertEquals("Comfort Food", tags.get(1));
        Assert.assertEquals("Easy", tags.get(2));
        Assert.assertEquals("Snack", tags.get(3));


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
        Assert.assertEquals(3, tags.size());
        Assert.assertEquals("Dinner", tags.get(0));
        Assert.assertEquals("Chicken", tags.get(1));
        Assert.assertEquals("Slow Cooker", tags.get(2));
    }

    @Test
    public void testAuthorList() {
        IDataStore dataStore = initDataStore();

        List<Author> authors = dataStore.getAllAuthors();

        Assert.assertEquals(2, authors.size());

        Assert.assertEquals("0", authors.get(0).getId());
        Assert.assertEquals("bobpiazza", authors.get(0).getName());
        Assert.assertEquals("I love making muffins. Find me on allrecipes: https://www.allrecipes.com/cook/2955506", authors.get(0).getBio());

        Assert.assertEquals("1", authors.get(1).getId());
        Assert.assertEquals("Myrna", authors.get(1).getName());
        Assert.assertEquals("Find me on allrecipes: https://www.allrecipes.com/cook/2792648?content=recipes", authors.get(1).getBio());
    }

    @Test
    public void testReviewList() {
        IDataStore dataStore = initDataStore();

        List<Review> reviews = dataStore.getAllReviews();

        Assert.assertEquals(3, reviews.size());

        Assert.assertEquals("0", reviews.get(0).getId());
        Assert.assertEquals("0", reviews.get(0).getRecipeId());
        Assert.assertEquals("Neo Colwyn", reviews.get(0).getAuthor());
        Assert.assertEquals("These muffins are really good!", reviews.get(0).getContent());
        Assert.assertEquals(5, reviews.get(0).getRating());

        Assert.assertEquals("1", reviews.get(1).getId());
        Assert.assertEquals("0", reviews.get(1).getRecipeId());
        Assert.assertEquals("Padma Gauthier", reviews.get(1).getAuthor());
        Assert.assertEquals("Should up the cranberry count a little bit, otherwise awesome!", reviews.get(1).getContent());
        Assert.assertEquals(4, reviews.get(1).getRating());

        Assert.assertEquals("2", reviews.get(2).getId());
        Assert.assertEquals("1", reviews.get(2).getRecipeId());
        Assert.assertEquals("Lara Hanna", reviews.get(2).getAuthor());
        Assert.assertEquals("Too much ketchup.", reviews.get(2).getContent());
        Assert.assertEquals(2, reviews.get(2).getRating());
    }

    @Test
    public void testNewRecipeFlow() {
        IDataStore dataStore = initDataStore();

        List<Author> authors = dataStore.getAllAuthors();
        Assert.assertEquals(2, authors.size());

        List<Recipe> recipes = dataStore.getAllRecipes();
        Assert.assertEquals(2, recipes.size());

        IngredientList il = IngredientList.Create(new Ingredient("all-purpose flour", 10.0, "cups"));
        Author a = new Author("5", "An Author", "Hi, I am an author!");
        Recipe r = new Recipe("3", "Title", "5", "Content", il, 2, Arrays.asList("Some", "Tags"));

        dataStore.insertAuthor(a);
        dataStore.insertRecipe(r);

        authors = dataStore.getAllAuthors();
        Assert.assertEquals(3, authors.size());

        Assert.assertEquals(a.getId(), authors.get(2).getId());
        Assert.assertEquals(a.getName(), authors.get(2).getName());
        Assert.assertEquals(a.getBio(), authors.get(2).getBio());

        recipes = dataStore.getAllRecipes();
        Assert.assertEquals(3, recipes.size());

        Assert.assertEquals(r.getId(), recipes.get(2).getId());
        Assert.assertEquals(r.getTitle(), recipes.get(2).getTitle());
        Assert.assertEquals(r.getAuthorId(), recipes.get(2).getAuthorId());
        Assert.assertEquals(r.getContent(), recipes.get(2).getContent());
        Assert.assertEquals(r.getIngredientList(), recipes.get(2).getIngredientList());
        Assert.assertEquals(r.getServingSize(), recipes.get(2).getServingSize());

        Assert.assertEquals(2, recipes.get(2).getTags().size());
        Assert.assertEquals("Some", recipes.get(2).getTags().get(0));
        Assert.assertEquals("Tags", recipes.get(2).getTags().get(1));
    }

    @Test
    public void testNewReviewFlow() {
        IDataStore dataStore = initDataStore();

        List<Review> reviews = dataStore.getAllReviews();
        Assert.assertEquals(3, reviews.size());

        Review r = new Review("3", "1", "Richard Hendricks", "tastes like chicken", 5);
        dataStore.insertReview(r);

        reviews = dataStore.getAllReviews();
        Assert.assertEquals(4, reviews.size());

        Assert.assertEquals(r.getId(), reviews.get(3).getId());
        Assert.assertEquals(r.getRecipeId(), reviews.get(3).getRecipeId());
        Assert.assertEquals(r.getAuthor(), reviews.get(3).getAuthor());
        Assert.assertEquals(r.getContent(), reviews.get(3).getContent());
        Assert.assertEquals(r.getRating(), reviews.get(3).getRating());
    }

    @Test
    public void testRecipeUpdateSingleField() {
        IDataStore dataStore = initDataStore();

        Recipe dbRecipe = dataStore.getRecipeById("0");
        Assert.assertEquals("0", dbRecipe.getId());
        Assert.assertEquals("Lemon Cranberry Muffins", dbRecipe.getTitle());
        Assert.assertEquals(12, dbRecipe.getServingSize());

        Recipe modifiedRecipe = new Recipe(dbRecipe.getId(), "Bran Muffins", dbRecipe.getAuthorId(), dbRecipe.getContent(), dbRecipe.getIngredientList(), dbRecipe.getServingSize(), dbRecipe.getTags());
        dataStore.updateRecipe(modifiedRecipe);

        dbRecipe = dataStore.getRecipeById("0");
        Assert.assertEquals("0", dbRecipe.getId());
        Assert.assertEquals("Bran Muffins", dbRecipe.getTitle());
        Assert.assertEquals(12, dbRecipe.getServingSize());

        modifiedRecipe = new Recipe(dbRecipe.getId(), dbRecipe.getTitle(), dbRecipe.getAuthorId(), dbRecipe.getContent(), dbRecipe.getIngredientList(), 3, dbRecipe.getTags());
        dataStore.updateRecipe(modifiedRecipe);

        dbRecipe = dataStore.getRecipeById("0");
        Assert.assertEquals("0", dbRecipe.getId());
        Assert.assertEquals("Bran Muffins", dbRecipe.getTitle());
        Assert.assertEquals(3, dbRecipe.getServingSize());
    }

    @Test
    public void testRecipeUpdateMultiField() {
        IDataStore dataStore = initDataStore();

        Recipe dbRecipe = dataStore.getRecipeById("1");
        Assert.assertEquals("1", dbRecipe.getId());
        Assert.assertEquals("Honey-Garlic Slow Cooker Chicken Thighs", dbRecipe.getTitle());
        Assert.assertEquals(4, dbRecipe.getServingSize());
        Assert.assertEquals("1. Lay chicken thighs into the bottom of a 4-quart slow cooker.\n\n2. Whisk soy sauce, ketchup, honey, garlic, and basil together in a bowl; pour over the chicken.\n\n3. Cook on Low for 6 hours.", dbRecipe.getContent());


        Recipe modifiedRecipe = new Recipe(dbRecipe.getId(), "Blackened Chicken Breast", dbRecipe.getAuthorId(), "Some new content", dbRecipe.getIngredientList(), 1, dbRecipe.getTags());
        dataStore.updateRecipe(modifiedRecipe);

        dbRecipe = dataStore.getRecipeById("1");
        Assert.assertEquals("1", dbRecipe.getId());
        Assert.assertEquals("Blackened Chicken Breast", dbRecipe.getTitle());
        Assert.assertEquals(1, dbRecipe.getServingSize());
        Assert.assertEquals("Some new content", dbRecipe.getContent());
    }

    @Test
    public void testInvalidRecipeUpdate() {
        IDataStore dataStore = initDataStore();

        List<Recipe> recipes = dataStore.getAllRecipes();
        Assert.assertEquals(2, recipes.size());
        Assert.assertEquals("Lemon Cranberry Muffins", recipes.get(0).getTitle());
        Assert.assertEquals("Honey-Garlic Slow Cooker Chicken Thighs", recipes.get(1).getTitle());

        Recipe invalid = new Recipe("3", "Recipe with non-existant ID", "0", "Some content", recipes.get(0).getIngredientList(), 1, Arrays.asList("Some", "tags"));
        dataStore.updateRecipe(invalid);

        recipes = dataStore.getAllRecipes();
        Assert.assertEquals(2, recipes.size());
        Assert.assertEquals("Lemon Cranberry Muffins", recipes.get(0).getTitle());
        Assert.assertEquals("Honey-Garlic Slow Cooker Chicken Thighs", recipes.get(1).getTitle());

        dataStore.updateRecipe(null);

        recipes = dataStore.getAllRecipes();
        Assert.assertEquals(2, recipes.size());
        Assert.assertEquals("Lemon Cranberry Muffins", recipes.get(0).getTitle());
        Assert.assertEquals("Honey-Garlic Slow Cooker Chicken Thighs", recipes.get(1).getTitle());
    }

    @Test
    public void testInvalidRecipeIdLookup() {
        IDataStore dataStore = initDataStore();

        Assert.assertNull(dataStore.getRecipeById("37"));
        Assert.assertNull(dataStore.getRecipeById(null));
    }

    @Test
    public void testRecipeDelete() {
        IDataStore dataStore = initDataStore();

        List<Recipe> recipes = dataStore.getAllRecipes();
        Assert.assertEquals(2, recipes.size());
        Assert.assertEquals("0", recipes.get(0).getId());
        Assert.assertEquals("1", recipes.get(1).getId());

        dataStore.deleteRecipe(recipes.get(0));

        recipes = dataStore.getAllRecipes();
        Assert.assertEquals(1, recipes.size());
        Assert.assertEquals("1", recipes.get(0).getId());

        dataStore.deleteRecipe(recipes.get(0));

        recipes = dataStore.getAllRecipes();
        Assert.assertEquals(0, recipes.size());
    }

    @Test
    public void testInvalidRecipeDelete() {
        IDataStore dataStore = initDataStore();

        List<Recipe> recipes = dataStore.getAllRecipes();
        Assert.assertEquals(2, recipes.size());
        Assert.assertEquals("0", recipes.get(0).getId());
        Assert.assertEquals("1", recipes.get(1).getId());

        Recipe invalid = new Recipe("3", "Recipe with non-existant ID", "0", "Some content", recipes.get(0).getIngredientList(), 1, Arrays.asList("Some", "tags"));
        dataStore.deleteRecipe(invalid);

        recipes = dataStore.getAllRecipes();
        Assert.assertEquals(2, recipes.size());
        Assert.assertEquals("0", recipes.get(0).getId());
        Assert.assertEquals("1", recipes.get(1).getId());

        dataStore.deleteRecipe(null);

        recipes = dataStore.getAllRecipes();
        Assert.assertEquals(2, recipes.size());
        Assert.assertEquals("0", recipes.get(0).getId());
        Assert.assertEquals("1", recipes.get(1).getId());
    }

    @Test
    public void testAuthorUpdateSingleField() {
        IDataStore dataStore = initDataStore();

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

    }

    @Test
    public void testAuthorUpdateMultiField() {
        IDataStore dataStore = initDataStore();

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
    }

    @Test
    public void testInvalidAuthorUpdate() {
        IDataStore dataStore = initDataStore();

        List<Author> authors = dataStore.getAllAuthors();
        Assert.assertEquals(2, authors.size());
        Assert.assertEquals("bobpiazza", authors.get(0).getName());
        Assert.assertEquals("Myrna", authors.get(1).getName());

        Author invalid = new Author("3", "thomas the tank engine", "It was time for Thomas to leave. He had seen everything.");
        dataStore.updateAuthor(invalid);

        authors = dataStore.getAllAuthors();
        Assert.assertEquals(2, authors.size());
        Assert.assertEquals("bobpiazza", authors.get(0).getName());
        Assert.assertEquals("Myrna", authors.get(1).getName());

        dataStore.updateAuthor(null);

        authors = dataStore.getAllAuthors();
        Assert.assertEquals(2, authors.size());
        Assert.assertEquals("bobpiazza", authors.get(0).getName());
        Assert.assertEquals("Myrna", authors.get(1).getName());
    }

    @Test
    public void testInvalidAuthorIdLookup() {
        IDataStore dataStore = initDataStore();

        Assert.assertNull(dataStore.getAuthorById("42"));
        Assert.assertNull(dataStore.getAuthorById(null));
    }

    @Test
    public void testAuthorDelete() {
        IDataStore dataStore = initDataStore();

        List<Author> authors = dataStore.getAllAuthors();
        Assert.assertEquals(2, authors.size());
        Assert.assertEquals("0", authors.get(0).getId());
        Assert.assertEquals("1", authors.get(1).getId());

        dataStore.deleteAuthor(authors.get(0));

        authors = dataStore.getAllAuthors();
        Assert.assertEquals(1, authors.size());
        Assert.assertEquals("1", authors.get(0).getId());

        dataStore.deleteAuthor(authors.get(0));

        authors = dataStore.getAllAuthors();
        Assert.assertEquals(0, authors.size());
    }

    @Test
    public void testInvalidAuthorDelete() {
        IDataStore dataStore = initDataStore();

        List<Author> authors = dataStore.getAllAuthors();
        Assert.assertEquals(2, authors.size());
        Assert.assertEquals("0", authors.get(0).getId());
        Assert.assertEquals("1", authors.get(1).getId());

        Author invalid = new Author("6", "perry the platypus", "[platypus noises]");
        dataStore.deleteAuthor(invalid);

        authors = dataStore.getAllAuthors();
        Assert.assertEquals(2, authors.size());
        Assert.assertEquals("0", authors.get(0).getId());
        Assert.assertEquals("1", authors.get(1).getId());

        dataStore.deleteAuthor(null);

        authors = dataStore.getAllAuthors();
        Assert.assertEquals(2, authors.size());
        Assert.assertEquals("0", authors.get(0).getId());
        Assert.assertEquals("1", authors.get(1).getId());
    }

    @Test
    public void testReviewUpdateSingleField() {
        IDataStore dataStore = initDataStore();

        Review dbReview = dataStore.getReviewById("0");
        Assert.assertEquals("0", dbReview.getId());
        Assert.assertEquals("Neo Colwyn", dbReview.getAuthor());
        Assert.assertEquals(5, dbReview.getRating());

        Review modifiedReview = new Review(dbReview.getId(), dbReview.getRecipeId(),"New author", dbReview.getContent(), dbReview.getRating());
        dataStore.updateReview(modifiedReview);

        dbReview = dataStore.getReviewById("0");
        Assert.assertEquals("0", dbReview.getId());
        Assert.assertEquals("New author", dbReview.getAuthor());
        Assert.assertEquals(5, dbReview.getRating());

        modifiedReview = new Review(dbReview.getId(), dbReview.getRecipeId(),dbReview.getAuthor(), dbReview.getContent(), 3);
        dataStore.updateReview(modifiedReview);

        dbReview = dataStore.getReviewById("0");
        Assert.assertEquals("0", dbReview.getId());
        Assert.assertEquals("New author", dbReview.getAuthor());
        Assert.assertEquals(3, dbReview.getRating());
    }

    @Test
    public void testReviewUpdateMultiField() {
        IDataStore dataStore = initDataStore();

        Review dbReview = dataStore.getReviewById("0");
        Assert.assertEquals("0", dbReview.getId());
        Assert.assertEquals("Neo Colwyn", dbReview.getAuthor());
        Assert.assertEquals(5, dbReview.getRating());

        Review modifiedReview = new Review(dbReview.getId(), dbReview.getRecipeId(),"Different author", dbReview.getContent(), 2);
        dataStore.updateReview(modifiedReview);

        dbReview = dataStore.getReviewById("0");
        Assert.assertEquals("0", dbReview.getId());
        Assert.assertEquals("Different author", dbReview.getAuthor());
        Assert.assertEquals(2, dbReview.getRating());
    }

    @Test
    public void testInvalidReviewUpdate() {
        IDataStore dataStore = initDataStore();

        List<Review> reviews = dataStore.getAllReviews();
        Assert.assertEquals(3, reviews.size());
        Assert.assertEquals("Neo Colwyn", reviews.get(0).getAuthor());
        Assert.assertEquals("Padma Gauthier", reviews.get(1).getAuthor());
        Assert.assertEquals("Lara Hanna", reviews.get(2).getAuthor());

        Review invalid = new Review("86", "0", "Vladilena Milize", "this is a good recipe", 5);
        dataStore.updateReview(invalid);

        reviews = dataStore.getAllReviews();
        Assert.assertEquals(3, reviews.size());
        Assert.assertEquals("Neo Colwyn", reviews.get(0).getAuthor());
        Assert.assertEquals("Padma Gauthier", reviews.get(1).getAuthor());
        Assert.assertEquals("Lara Hanna", reviews.get(2).getAuthor());

        dataStore.updateReview(null);

        reviews = dataStore.getAllReviews();
        Assert.assertEquals(3, reviews.size());
        Assert.assertEquals("Neo Colwyn", reviews.get(0).getAuthor());
        Assert.assertEquals("Padma Gauthier", reviews.get(1).getAuthor());
        Assert.assertEquals("Lara Hanna", reviews.get(2).getAuthor());
    }

    @Test
    public void testInvalidReviewIdLookup() {
        IDataStore dataStore = initDataStore();

        Assert.assertNull(dataStore.getReviewById("58"));
        Assert.assertNull(dataStore.getReviewById(null));
    }

    @Test
    public void testReviewDelete() {
        IDataStore dataStore = initDataStore();

        List<Review> reviews = dataStore.getAllReviews();
        Assert.assertEquals(3, reviews.size());
        Assert.assertEquals("0", reviews.get(0).getId());
        Assert.assertEquals("1", reviews.get(1).getId());
        Assert.assertEquals("2", reviews.get(2).getId());

        dataStore.deleteReview(reviews.get(1));

        reviews = dataStore.getAllReviews();
        Assert.assertEquals(2, reviews.size());
        Assert.assertEquals("0", reviews.get(0).getId());
        Assert.assertEquals("2", reviews.get(1).getId());

        dataStore.deleteReview(reviews.get(0));

        reviews = dataStore.getAllReviews();
        Assert.assertEquals(1, reviews.size());
        Assert.assertEquals("2", reviews.get(0).getId());

        dataStore.deleteReview(reviews.get(0));

        reviews = dataStore.getAllReviews();
        Assert.assertEquals(0, reviews.size());
    }

    @Test
    public void testInvalidReviewDelete() {
        IDataStore dataStore = initDataStore();

        List<Review> reviews = dataStore.getAllReviews();
        Assert.assertEquals(3, reviews.size());
        Assert.assertEquals("0", reviews.get(0).getId());
        Assert.assertEquals("1", reviews.get(1).getId());
        Assert.assertEquals("2", reviews.get(2).getId());

        Review invalid = new Review("94", "0", "franklin", "[turtle noises]", 5);
        dataStore.deleteReview(invalid);

        reviews = dataStore.getAllReviews();
        Assert.assertEquals(3, reviews.size());
        Assert.assertEquals("0", reviews.get(0).getId());
        Assert.assertEquals("1", reviews.get(1).getId());
        Assert.assertEquals("2", reviews.get(2).getId());

        dataStore.deleteReview(null);

        reviews = dataStore.getAllReviews();
        Assert.assertEquals(3, reviews.size());
        Assert.assertEquals("0", reviews.get(0).getId());
        Assert.assertEquals("1", reviews.get(1).getId());
        Assert.assertEquals("2", reviews.get(2).getId());
    }

    private IDataStore initDataStore() {
        IDataStore dataStore = new StubDataStore("stub");
        dataStore.open("stub");
        return dataStore;
    }
}
