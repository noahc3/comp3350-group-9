package comp3350.cookit.persistence;

import java.util.List;

import comp3350.cookit.objects.Author;
import comp3350.cookit.objects.Recipe;
import comp3350.cookit.objects.Review;

public interface IDataStore {
    void open(String dbPath);

    void close();

    List<Recipe> getAllRecipes();

    List<Recipe> getRecipesWithTag(String tag);

    Recipe getRecipeById(String id);

    void insertRecipe(Recipe recipe);

    void updateRecipe(Recipe recipe);

    void deleteRecipe(Recipe recipe);

    List<Recipe> getFavoriteRecipes();

    void insertFavoriteRecipe(String recipeId);

    void deleteFavoriteRecipe(String recipeId);

    boolean anyRecipeWithTag(String tag);

    List<Author> getAllAuthors();

    Author getAuthorById(String id);

    void insertAuthor(Author author);

    void updateAuthor(Author author);

    void deleteAuthor(Author author);

    List<Review> getAllReviews();

    List<Review> getReviewsForRecipe(String recipeId);

    Review getReviewById(String id);

    void insertReview(Review review);

    void updateReview(Review review);

    void deleteReview(Review review);
}