package comp3350.cookit.business;

import java.util.List;

import comp3350.cookit.application.Main;
import comp3350.cookit.application.Services;
import comp3350.cookit.objects.Review;
import comp3350.cookit.persistence.IDataStore;

public class AccessReviews {
    private final IDataStore dataStore;

    public AccessReviews() {
        dataStore = Services.getDataStore();
    }

    public List<Review> getReviews() {
        return dataStore.getAllReviews();
    }

    public Review getReviewById(String id) {
        return dataStore.getReviewById(id);
    }

    public void insertReview(Review review) {
        dataStore.insertReview(review);
    }

    public void updateReview(Review review) {
        dataStore.updateReview(review);
    }

    public void deleteReview(Review review) {
        dataStore.deleteReview(review);
    }
}
