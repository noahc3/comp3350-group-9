package comp3350.cookit.business;

import java.util.List;

import comp3350.cookit.objects.Review;

public class ReviewUtilities {
    public static float calculateAverageReviewScore(List<Review> reviews) {
        float avg = 0;

        if (reviews != null && reviews.size() > 0) {
            for (Review review : reviews) {
                avg += review.getRating();
            }

            avg /= ((float) reviews.size());
        }

        return avg;
    }
}
