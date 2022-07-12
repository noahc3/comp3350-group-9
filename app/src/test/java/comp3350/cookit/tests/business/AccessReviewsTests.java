package comp3350.cookit.tests.business;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import comp3350.cookit.application.Services;
import comp3350.cookit.business.AccessReviews;
import comp3350.cookit.objects.Review;
import comp3350.cookit.tests.persistence.StubDataStore;

public class AccessReviewsTests {
    @Test
    public void testNewReview() {
        AccessReviews ar = initAccessReviews();

        List<Review> reviews = ar.getReviews();
        Assert.assertEquals(3, reviews.size());

        Review r = new Review("3", "1", "Richard Hendricks", "tastes like chicken", 5);
        ar.insertReview(r);

        reviews = ar.getReviews();
        Assert.assertEquals(4, reviews.size());

        Assert.assertEquals(r.getId(), reviews.get(3).getId());
        Assert.assertEquals(r.getRecipeId(), reviews.get(3).getRecipeId());
        Assert.assertEquals(r.getAuthor(), reviews.get(3).getAuthor());
        Assert.assertEquals(r.getContent(), reviews.get(3).getContent());
        Assert.assertEquals(r.getRating(), reviews.get(3).getRating());
    }

    @Test
    public void testReviewUpdateSingleField() {
        AccessReviews ar = initAccessReviews();

        Review dbReview = ar.getReviewById("0");
        Assert.assertEquals("0", dbReview.getId());
        Assert.assertEquals("Neo Colwyn", dbReview.getAuthor());
        Assert.assertEquals(5, dbReview.getRating());

        Review modifiedReview = new Review(dbReview.getId(), dbReview.getRecipeId(), "New author", dbReview.getContent(), dbReview.getRating());
        ar.updateReview(modifiedReview);

        dbReview = ar.getReviewById("0");
        Assert.assertEquals("0", dbReview.getId());
        Assert.assertEquals("New author", dbReview.getAuthor());
        Assert.assertEquals(5, dbReview.getRating());

        modifiedReview = new Review(dbReview.getId(), dbReview.getRecipeId(), dbReview.getAuthor(), dbReview.getContent(), 3);
        ar.updateReview(modifiedReview);

        dbReview = ar.getReviewById("0");
        Assert.assertEquals("0", dbReview.getId());
        Assert.assertEquals("New author", dbReview.getAuthor());
        Assert.assertEquals(3, dbReview.getRating());
    }

    @Test
    public void testReviewUpdateMultiField() {
        AccessReviews ar = initAccessReviews();

        Review dbReview = ar.getReviewById("0");
        Assert.assertEquals("0", dbReview.getId());
        Assert.assertEquals("Neo Colwyn", dbReview.getAuthor());
        Assert.assertEquals(5, dbReview.getRating());

        Review modifiedReview = new Review(dbReview.getId(), dbReview.getRecipeId(), "Different author", dbReview.getContent(), 2);
        ar.updateReview(modifiedReview);

        dbReview = ar.getReviewById("0");
        Assert.assertEquals("0", dbReview.getId());
        Assert.assertEquals("Different author", dbReview.getAuthor());
        Assert.assertEquals(2, dbReview.getRating());
    }

    @Test
    public void testInvalidReviewUpdate() {
        AccessReviews ar = initAccessReviews();

        List<Review> reviews = ar.getReviews();
        Assert.assertEquals(3, reviews.size());
        Assert.assertEquals("Neo Colwyn", reviews.get(0).getAuthor());
        Assert.assertEquals("Padma Gauthier", reviews.get(1).getAuthor());
        Assert.assertEquals("Lara Hanna", reviews.get(2).getAuthor());

        Review invalid = new Review("86", "0", "Vladilena Milize", "this is a good recipe", 5);
        ar.updateReview(invalid);

        reviews = ar.getReviews();
        Assert.assertEquals(3, reviews.size());
        Assert.assertEquals("Neo Colwyn", reviews.get(0).getAuthor());
        Assert.assertEquals("Padma Gauthier", reviews.get(1).getAuthor());
        Assert.assertEquals("Lara Hanna", reviews.get(2).getAuthor());

        ar.updateReview(null);

        reviews = ar.getReviews();
        Assert.assertEquals(3, reviews.size());
        Assert.assertEquals("Neo Colwyn", reviews.get(0).getAuthor());
        Assert.assertEquals("Padma Gauthier", reviews.get(1).getAuthor());
        Assert.assertEquals("Lara Hanna", reviews.get(2).getAuthor());
    }

    @Test
    public void testInvalidReviewIdLookup() {
        AccessReviews ar = initAccessReviews();

        Assert.assertNull(ar.getReviewById("58"));
        Assert.assertNull(ar.getReviewById(null));
    }

    @Test
    public void testReviewDelete() {
        AccessReviews ar = initAccessReviews();

        List<Review> reviews = ar.getReviews();
        Assert.assertEquals(3, reviews.size());
        Assert.assertEquals("0", reviews.get(0).getId());
        Assert.assertEquals("1", reviews.get(1).getId());
        Assert.assertEquals("2", reviews.get(2).getId());

        ar.deleteReview(reviews.get(1));

        reviews = ar.getReviews();
        Assert.assertEquals(2, reviews.size());
        Assert.assertEquals("0", reviews.get(0).getId());
        Assert.assertEquals("2", reviews.get(1).getId());

        ar.deleteReview(reviews.get(0));

        reviews = ar.getReviews();
        Assert.assertEquals(1, reviews.size());
        Assert.assertEquals("2", reviews.get(0).getId());

        ar.deleteReview(reviews.get(0));

        reviews = ar.getReviews();
        Assert.assertEquals(0, reviews.size());
    }

    @Test
    public void testInvalidReviewDelete() {
        AccessReviews ar = initAccessReviews();

        List<Review> reviews = ar.getReviews();
        Assert.assertEquals(3, reviews.size());
        Assert.assertEquals("0", reviews.get(0).getId());
        Assert.assertEquals("1", reviews.get(1).getId());
        Assert.assertEquals("2", reviews.get(2).getId());

        Review invalid = new Review("94", "0", "franklin", "[turtle noises]", 5);
        ar.deleteReview(invalid);

        reviews = ar.getReviews();
        Assert.assertEquals(3, reviews.size());
        Assert.assertEquals("0", reviews.get(0).getId());
        Assert.assertEquals("1", reviews.get(1).getId());
        Assert.assertEquals("2", reviews.get(2).getId());

        ar.deleteReview(null);

        reviews = ar.getReviews();
        Assert.assertEquals(3, reviews.size());
        Assert.assertEquals("0", reviews.get(0).getId());
        Assert.assertEquals("1", reviews.get(1).getId());
        Assert.assertEquals("2", reviews.get(2).getId());
    }

    private AccessReviews initAccessReviews() {
        Services.createDataStore(new StubDataStore("stub"));
        return new AccessReviews();
    }
}
