package comp3350.cookit.business;

import java.util.List;

import comp3350.cookit.application.Main;
import comp3350.cookit.application.Services;
import comp3350.cookit.objects.Recipe;
import comp3350.cookit.objects.Review;
import comp3350.cookit.persistence.DataAccessStub;

public class AccessReviews
{
    private DataAccessStub dataAccess;

    public AccessReviews()
    {
        dataAccess = Services.getDataAccess(Main.dbName);
    }

    public List<Review> getReviews()
    {
        return dataAccess.getAllReviews();
    }

    public Review getReviewById(String id) {
        return dataAccess.getReviewById(id);
    }

    public void insertReview(Review review)
    {
        dataAccess.insertReview(review);
    }

    public void updateRecipe(Review review)
    {
        dataAccess.updateReview(review);
    }

    public void deleteRecipe(Review review)
    {
        dataAccess.deleteReview(review);
    }
}
