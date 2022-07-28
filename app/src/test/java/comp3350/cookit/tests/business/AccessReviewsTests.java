package comp3350.cookit.tests.business;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import comp3350.cookit.application.Services;
import comp3350.cookit.business.AccessRecipes;
import comp3350.cookit.business.AccessReviews;
import comp3350.cookit.objects.Review;
import comp3350.cookit.tests.RunIntegrationTests;
import comp3350.cookit.tests.persistence.StubDataStore;

public class AccessReviewsTests {

    AccessReviews ar;

    @Test
    public void testNewReview() {
        initDatabase();

        List<Review> reviews = ar.getReviews();
        Assert.assertEquals(4, reviews.size());

        Review r = new Review("4", "1", "Richard Hendricks", "tastes like chicken", 5);
        ar.insertReview(r);

        reviews = ar.getReviews();
        Assert.assertEquals(5, reviews.size());

        Assert.assertEquals(r.getId(), reviews.get(0).getId());
        Assert.assertEquals(r.getRecipeId(), reviews.get(0).getRecipeId());
        Assert.assertEquals(r.getAuthor(), reviews.get(0).getAuthor());
        Assert.assertEquals(r.getContent(), reviews.get(0).getContent());
        Assert.assertEquals(r.getRating(), reviews.get(0).getRating());

        resetDatabase();
    }

    @Test
    public void testReviewUpdateSingleField() {
        initDatabase();

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

        resetDatabase();
    }

    @Test
    public void testReviewUpdateMultiField() {
        initDatabase();

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

        resetDatabase();
    }

    @Test
    public void testInvalidReviewUpdate() {
        initDatabase();

        List<Review> reviews = ar.getReviews();
        Assert.assertEquals(4, reviews.size());
        Assert.assertEquals("Sheila M. Higgs-Coulthard", reviews.get(0).getAuthor());
        Assert.assertEquals("Lara Hanna", reviews.get(1).getAuthor());
        Assert.assertEquals("Padma Gauthier", reviews.get(2).getAuthor());
        Assert.assertEquals("Neo Colwyn", reviews.get(3).getAuthor());

        Review invalid = new Review("86", "0", "Vladilena Milize", "this is a good recipe", 5);
        ar.updateReview(invalid);

        reviews = ar.getReviews();
        Assert.assertEquals(4, reviews.size());
        Assert.assertEquals("Sheila M. Higgs-Coulthard", reviews.get(0).getAuthor());
        Assert.assertEquals("Lara Hanna", reviews.get(1).getAuthor());
        Assert.assertEquals("Padma Gauthier", reviews.get(2).getAuthor());
        Assert.assertEquals("Neo Colwyn", reviews.get(3).getAuthor());

        ar.updateReview(null);

        reviews = ar.getReviews();
        Assert.assertEquals(4, reviews.size());
        Assert.assertEquals("Sheila M. Higgs-Coulthard", reviews.get(0).getAuthor());
        Assert.assertEquals("Lara Hanna", reviews.get(1).getAuthor());
        Assert.assertEquals("Padma Gauthier", reviews.get(2).getAuthor());
        Assert.assertEquals("Neo Colwyn", reviews.get(3).getAuthor());

        resetDatabase();
    }

    @Test
    public void testInvalidReviewIdLookup() {
        initDatabase();

        Assert.assertNull(ar.getReviewById("58"));
        Assert.assertNull(ar.getReviewById(null));

        resetDatabase();
    }

    @Test
    public void testReviewDelete() {
        initDatabase();

        List<Review> reviews = ar.getReviews();
        Assert.assertEquals(4, reviews.size());
        Assert.assertEquals("3", reviews.get(0).getId());
        Assert.assertEquals("2", reviews.get(1).getId());
        Assert.assertEquals("1", reviews.get(2).getId());
        Assert.assertEquals("0", reviews.get(3).getId());

        ar.deleteReview(reviews.get(1));

        reviews = ar.getReviews();
        Assert.assertEquals(3, reviews.size());
        Assert.assertEquals("3", reviews.get(0).getId());
        Assert.assertEquals("1", reviews.get(1).getId());
        Assert.assertEquals("0", reviews.get(2).getId());

        ar.deleteReview(reviews.get(0));

        reviews = ar.getReviews();
        Assert.assertEquals(2, reviews.size());
        Assert.assertEquals("1", reviews.get(0).getId());
        Assert.assertEquals("0", reviews.get(1).getId());

        ar.deleteReview(reviews.get(0));

        reviews = ar.getReviews();
        Assert.assertEquals(1, reviews.size());
        Assert.assertEquals("0", reviews.get(0).getId());

        resetDatabase();
    }

    @Test
    public void testInvalidReviewDelete() {
        initDatabase();

        List<Review> reviews = ar.getReviews();
        Assert.assertEquals(4, reviews.size());
        Assert.assertEquals("3", reviews.get(0).getId());
        Assert.assertEquals("2", reviews.get(1).getId());
        Assert.assertEquals("1", reviews.get(2).getId());
        Assert.assertEquals("0", reviews.get(3).getId());

        Review invalid = new Review("94", "0", "franklin", "[turtle noises]", 5);
        ar.deleteReview(invalid);

        reviews = ar.getReviews();
        Assert.assertEquals(4, reviews.size());
        Assert.assertEquals("3", reviews.get(0).getId());
        Assert.assertEquals("2", reviews.get(1).getId());
        Assert.assertEquals("1", reviews.get(2).getId());
        Assert.assertEquals("0", reviews.get(3).getId());

        ar.deleteReview(null);

        reviews = ar.getReviews();
        Assert.assertEquals(4, reviews.size());
        Assert.assertEquals("3", reviews.get(0).getId());
        Assert.assertEquals("2", reviews.get(1).getId());
        Assert.assertEquals("1", reviews.get(2).getId());
        Assert.assertEquals("0", reviews.get(3).getId());

        resetDatabase();
    }

    private void initDatabase() {
        if (RunIntegrationTests.USE_STUBDATASTORE) {
            Services.createDataStore(new StubDataStore());
        } else {
            Services.createDataStore();
        }

        ar = new AccessReviews();
    }

    private void resetDatabase() {
        try {
            if (!RunIntegrationTests.USE_STUBDATASTORE) {
                Services.closeDataStore();

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
