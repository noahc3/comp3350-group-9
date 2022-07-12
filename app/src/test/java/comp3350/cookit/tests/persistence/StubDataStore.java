package comp3350.cookit.tests.persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import comp3350.cookit.objects.Author;
import comp3350.cookit.objects.Ingredient;
import comp3350.cookit.objects.IngredientList;
import comp3350.cookit.objects.Recipe;
import comp3350.cookit.objects.Review;
import comp3350.cookit.persistence.IDataStore;

public class StubDataStore implements IDataStore {
    private final String dbName;
    private final String dbType = "stub";

    private List<Author> authors;
    private List<Recipe> recipes;
    private List<Review> reviews;

    public StubDataStore(String dbName) {
        this.dbName = dbName;
    }

    @Override
    public void open(String dbPath) {
        authors = new ArrayList<>(Arrays.asList(
                new Author("0", "bobpiazza", "I love making muffins. Find me on allrecipes: https://www.allrecipes.com/cook/2955506"),
                new Author("1", "Myrna", "Find me on allrecipes: https://www.allrecipes.com/cook/2792648?content=recipes")
        ));

        recipes = new ArrayList<>(Arrays.asList(
                new Recipe(
                        "0",
                        "Lemon Cranberry Muffins",
                        "0",
                        "1. Preheat oven to 400F. Grease 12 muffin cups, or line with paper muffin liners.\n\n2. Combine flour, sugar, baking powder, and salt in a large bowl. Mix lemon juice and milk in a measuring cup, to sour milk; beat eggs, oil, and milk mixture in a bowl. Stir egg mixture into flour mixture until just moistened; fold in cranberries. Fill prepared muffin cups two-thirds full; sprinkle with almonds.\n\n3. Bake in preheated oven until a toothpick inserted into a muffin comes out clean, 18 to 20 minutes. Cool for 5 minutes before removing from pan to wire rack.",
                        IngredientList.Create(
                                new Ingredient("all-purpose flour", 2, "cups"),
                                new Ingredient("white sugar", 1.25, "cups"),
                                new Ingredient("baking powder", 0.5, "tbsp"),
                                new Ingredient("salt", 0.5, "tsp"),
                                new Ingredient("lemon juice", 0.25, "cups"),
                                new Ingredient("milk", 0.75, "cups"),
                                new Ingredient("eggs", 2, "whole"),
                                new Ingredient("vegetable oil", 0.5, "cups"),
                                new Ingredient("cranberries, halved", 1, "cups"),
                                new Ingredient("toasted slivered almonds", 0.33, "cups")
                        ),
                        12,
                        Arrays.asList("Pastry", "Sweet", "Snack", "All Day"),
                        30,
                        20,
                        "Medium",
                        Arrays.asList("muffin0", "muffin1")
                ),
                new Recipe(
                        "1",
                        "Honey-Garlic Slow Cooker Chicken Thighs",
                        "1",
                        "1. Lay chicken thighs into the bottom of a 4-quart slow cooker.\n\n2. Whisk soy sauce, ketchup, honey, garlic, and basil together in a bowl; pour over the chicken.\n\n3. Cook on Low for 6 hours.",
                        IngredientList.Create(
                                new Ingredient("boneless, skinless chicken thighs", 4, "whole"),
                                new Ingredient("soy sauce", 0.5, "cups"),
                                new Ingredient("ketchup", 0.5, "cups"),
                                new Ingredient("honey", 0.33, "cups"),
                                new Ingredient("garlic, minced", 3, "cloves"),
                                new Ingredient("dried basil", 1, "tsp")
                        ),
                        4,
                        Arrays.asList("Culinary", "Savory", "Entree", "Dinner"),
                        30,
                        360,
                        "Easy",
                        Arrays.asList("chicken0", "chicken1")
                )
        ));

        reviews = new ArrayList<>(Arrays.asList(
                new Review("0", "0", "Neo Colwyn", "These muffins are really good!", 5),
                new Review("1", "0", "Padma Gauthier", "Should up the cranberry count a little bit, otherwise awesome!", 4),
                new Review("2", "1", "Lara Hanna", "Too much ketchup.", 2)
        ));

        System.out.println("Opened stub database " + dbPath);
    }

    @Override
    public void close() {
        System.out.println("Closed " + dbType + " database " + dbName);
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return new ArrayList<>(this.recipes);
    }

    @Override
    public List<Recipe> getRecipesWithTag(String tag) {
        List<Recipe> recipes = getAllRecipes();
        List<Recipe> taggedRecipes = new ArrayList<>();

        if (recipes != null) {
            for (Recipe r : recipes) {
                if (r.getTags().contains(tag)) {
                    taggedRecipes.add(r);
                }
            }
        }

        return taggedRecipes;
    }

    public Recipe getRecipeById(String id) {
        Recipe result = null;

        for (Recipe r : recipes) {
            if (r.getId().equals(id)) {
                result = r;
                break;
            }
        }

        return result;
    }

    @Override
    public void insertRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        if (recipe != null) {
            for (int i = 0; i < recipes.size(); i++) {
                if (recipes.get(i).getId().equals(recipe.getId())) {
                    recipes.set(i, recipe);
                    break;
                }
            }
        }
    }

    @Override
    public void deleteRecipe(Recipe recipe) {
        if (recipe != null) {
            for (int i = 0; i < recipes.size(); i++) {
                if (recipes.get(i).getId().equals(recipe.getId())) {
                    recipes.remove(i);
                    break;
                }
            }
        }
    }

    @Override
    public List<Author> getAllAuthors() {
        return new ArrayList<>(this.authors);
    }

    @Override
    public Author getAuthorById(String id) {
        Author result = null;

        for (Author a : authors) {
            if (a.getId().equals(id)) {
                result = a;
                break;
            }
        }

        return result;
    }

    @Override
    public void insertAuthor(Author author) {
        authors.add(author);
    }

    @Override
    public void updateAuthor(Author author) {
        if (author != null) {
            for (int i = 0; i < authors.size(); i++) {
                if (authors.get(i).getId().equals(author.getId())) {
                    authors.set(i, author);
                    break;
                }
            }
        }
    }

    @Override
    public void deleteAuthor(Author author) {
        if (author != null) {
            for (int i = 0; i < authors.size(); i++) {
                if (authors.get(i).getId().equals(author.getId())) {
                    authors.remove(i);
                    break;
                }
            }
        }
    }

    @Override
    public List<Review> getAllReviews() {
        return new ArrayList<>(this.reviews);
    }

    @Override
    public Review getReviewById(String id) {
        Review result = null;

        for (Review r : reviews) {
            if (r.getId().equals(id)) {
                result = r;
                break;
            }
        }

        return result;
    }

    @Override
    public void insertReview(Review review) {
        reviews.add(review);
    }

    @Override
    public void updateReview(Review review) {
        if (review != null) {
            for (int i = 0; i < reviews.size(); i++) {
                if (reviews.get(i).getId().equals(review.getId())) {
                    reviews.set(i, review);
                    break;
                }
            }
        }
    }

    @Override
    public void deleteReview(Review review) {
        if (review != null) {
            for (int i = 0; i < authors.size(); i++) {
                if (reviews.get(i).getId().equals(review.getId())) {
                    reviews.remove(i);
                    break;
                }
            }
        }
    }


}