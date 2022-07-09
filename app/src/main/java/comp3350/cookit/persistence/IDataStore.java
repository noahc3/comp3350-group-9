package comp3350.cookit.persistence;

import java.util.List;

import comp3350.cookit.objects.Author;
import comp3350.cookit.objects.Recipe;
import comp3350.cookit.objects.Review;

public interface IDataStore {
    void open(String dbPath);

    void close();

    List<Recipe> getAllRecipes();

    Recipe getRecipeById(String id);

    void insertRecipe(Recipe recipe);

    void updateRecipe(Recipe recipe);

    void deleteRecipe(Recipe recipe);

    List<Author> getAllAuthors();

    Author getAuthorById(String id);

    void insertAuthor(Author author);

    void updateAuthor(Author author);

    void deleteAuthor(Author author);

    List<Review> getAllReviews();

    Review getReviewById(String id);

    void insertReview(Review review);

    void updateReview(Review review);

    void deleteReview(Review review);
}