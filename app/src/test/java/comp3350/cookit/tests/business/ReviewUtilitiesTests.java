package comp3350.cookit.tests.business;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.cookit.business.ReviewUtilities;
import comp3350.cookit.objects.Review;

public class ReviewUtilitiesTests {
    public ReviewUtilitiesTests() {

    }

    @Test
    public void testAverageTypical() {
        List<Review> reviews = new ArrayList<>();

        reviews.add(new Review("0", "0", "A", "Some Content", 5));
        reviews.add(new Review("1", "0", "B", "Some Content", 3));
        reviews.add(new Review("2", "0", "C", "Some Content", 1));
        reviews.add(new Review("3", "0", "D", "Some Content", 2));
        reviews.add(new Review("4", "0", "E", "Some Content", 5));
        reviews.add(new Review("5", "0", "F", "Some Content", 4));
        reviews.add(new Review("6", "0", "G", "Some Content", 3));

        Assert.assertEquals(3.28f, ReviewUtilities.calculateAverageReviewScore(reviews), 0.05f);

        reviews = new ArrayList<>();
    }

    @Test
    public void testAverageSameScore() {
        List<Review> reviews = new ArrayList<>();

        reviews.add(new Review("0", "0", "A", "Some Content", 4));
        reviews.add(new Review("1", "0", "B", "Some Content", 4));
        reviews.add(new Review("2", "0", "C", "Some Content", 4));
        reviews.add(new Review("3", "0", "D", "Some Content", 4));
        reviews.add(new Review("4", "0", "E", "Some Content", 4));
        reviews.add(new Review("5", "0", "F", "Some Content", 4));
        reviews.add(new Review("6", "0", "G", "Some Content", 4));

        Assert.assertEquals(4f, ReviewUtilities.calculateAverageReviewScore(reviews), 0.05f);

        reviews = new ArrayList<>();

        reviews.add(new Review("0", "0", "A", "Some Content", 1));
        reviews.add(new Review("1", "0", "B", "Some Content", 1));
        reviews.add(new Review("2", "0", "C", "Some Content", 1));
        reviews.add(new Review("3", "0", "D", "Some Content", 1));

        Assert.assertEquals(1f, ReviewUtilities.calculateAverageReviewScore(reviews), 0.05f);

        reviews = new ArrayList<>();

        reviews.add(new Review("0", "0", "A", "Some Content", 5));
        reviews.add(new Review("1", "0", "B", "Some Content", 5));
        reviews.add(new Review("2", "0", "C", "Some Content", 5));
        reviews.add(new Review("3", "0", "D", "Some Content", 5));
        reviews.add(new Review("4", "0", "E", "Some Content", 5));
        reviews.add(new Review("5", "0", "F", "Some Content", 5));
        reviews.add(new Review("6", "0", "G", "Some Content", 5));
        reviews.add(new Review("7", "0", "H", "Some Content", 5));
        reviews.add(new Review("8", "0", "I", "Some Content", 5));

        Assert.assertEquals(5f, ReviewUtilities.calculateAverageReviewScore(reviews), 0.05f);
    }

    @Test
    public void testAverageSingle() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review("0", "0", "A", "Some Content", 2));
        Assert.assertEquals(2f, ReviewUtilities.calculateAverageReviewScore(reviews), 0.05f);

        reviews = new ArrayList<>();
        reviews.add(new Review("0", "0", "A", "Some Content", 5));
        Assert.assertEquals(5f, ReviewUtilities.calculateAverageReviewScore(reviews), 0.05f);
    }

    @Test
    public void testAverageEmpty() {
        List<Review> reviews = new ArrayList<>();
        Assert.assertEquals(0f, ReviewUtilities.calculateAverageReviewScore(reviews), 0.05f);
    }

    @Test
    public void testAverageNull() {
        Assert.assertEquals(0f, ReviewUtilities.calculateAverageReviewScore(null), 0.05f);
    }
}
