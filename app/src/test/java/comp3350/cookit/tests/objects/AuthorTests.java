package comp3350.cookit.tests.objects;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import comp3350.cookit.objects.Author;

public class AuthorTests {

    @Test
    public void testTypical() {
        long timestampBeforeInstantiation = new Date().getTime();
        Author a1 = new Author("0", "John Doe", "Example bio");
        Assert.assertEquals("0", a1.getId());
        Assert.assertEquals("John Doe", a1.getName());
        Assert.assertEquals("Example bio", a1.getBio());
        Assert.assertTrue(a1.getTimestamp() >= timestampBeforeInstantiation);

        Author a2 = new Author("0", "John Doe", "Example bio");
        Assert.assertEquals(a1, a2);

        a1 = new Author("0", "John Doe", "Example bio");
        a2 = new Author("0", "Jane Smith", "Example bio");
        Assert.assertEquals(a1, a2);

        a1 = a2;

        Assert.assertEquals(a1, a1);
        Assert.assertEquals(a1, a2);

        a1 = new Author("0", "John Doe", "I have a fixed timestamp.", 1800L);
        Assert.assertEquals(1800L, a1.getTimestamp());
    }

    @Test
    public void testFalse() {
        Author a1 = new Author("0", "John Doe", "Example bio");
        Author a2 = new Author("1", "Jane Smith", "Example bio");
        Assert.assertNotEquals(a1, a2);

        a1 = new Author("0", "Jane Smith", "Example bio");
        a2 = new Author("1", "Jane Smith", "Example bio");
        Assert.assertNotEquals(a1, a2);
    }

    @Test
    public void testNull() {
        Author a = new Author("0", "Jane Smith", "Example bio");
        Assert.assertNotEquals(a, null);

        a = new Author(null, null, null);
        Assert.assertNull(a.getId());
        Assert.assertNull(a.getName());
        Assert.assertNull(a.getBio());
    }
}
