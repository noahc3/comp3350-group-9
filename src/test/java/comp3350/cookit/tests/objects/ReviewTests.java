package comp3350.cookit.tests.objects;

import org.junit.Assert;
import org.junit.Test;

import comp3350.cookit.objects.Review;

public class ReviewTests {

    @Test
    public void testTypical() {
        Review r1 = new Review("0", "1", "Author", "This is a review!", 10);
        Assert.assertEquals("0", r1.getId());
        Assert.assertEquals("1", r1.getRecipeId());
        Assert.assertEquals("Author", r1.getAuthor());
        Assert.assertEquals("This is a review!", r1.getContent());
        Assert.assertEquals(10, r1.getRating());

        Assert.assertEquals(r1, r1);

        Review r2 = new Review("0", "1", "Author", "This is a review!", 10);

        Assert.assertEquals(r1, r2);

        r2 = new Review("0", "6", "Other Author", "This is a review but the content is changed!", 9);

        Assert.assertEquals(r1, r2);
    }

    @Test
    public void testFalse() {
        Review r1 = new Review("0", "1", "Author", "This is a review!", 10);
        Review r2 = new Review("1", "1", "Author", "This is a review!", 10);

        Assert.assertNotEquals(r1, r2);
    }

    @Test
    public void testNull() {
        Review r1 = new Review(null, null, null, null, 0);
        Assert.assertNull(r1.getId());
        Assert.assertNull(r1.getRecipeId());
        Assert.assertNull(r1.getAuthor());
        Assert.assertNull(r1.getContent());
    }
}
