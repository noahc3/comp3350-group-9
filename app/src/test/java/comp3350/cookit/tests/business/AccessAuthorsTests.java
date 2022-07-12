package comp3350.cookit.tests.business;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import comp3350.cookit.application.Services;
import comp3350.cookit.business.AccessAuthors;
import comp3350.cookit.objects.Author;
import comp3350.cookit.tests.persistence.StubDataStore;

public class AccessAuthorsTests {

    @Test
    public void testAuthorList() {
        AccessAuthors aa = initAccessAuthors();

        List<Author> authors = aa.getAuthors();

        Assert.assertEquals(2, authors.size());

        Assert.assertEquals("0", authors.get(0).getId());
        Assert.assertEquals("bobpiazza", authors.get(0).getName());
        Assert.assertEquals("I love making muffins. Find me on allrecipes: https://www.allrecipes.com/cook/2955506", authors.get(0).getBio());

        Assert.assertEquals("1", authors.get(1).getId());
        Assert.assertEquals("Myrna", authors.get(1).getName());
        Assert.assertEquals("Find me on allrecipes: https://www.allrecipes.com/cook/2792648?content=recipes", authors.get(1).getBio());
    }

    @Test
    public void testNewAuthorFlow() {
        AccessAuthors aa = initAccessAuthors();

        List<Author> authors = aa.getAuthors();
        Assert.assertEquals(2, authors.size());

        Author a = new Author("5", "An Author", "Hi, I am an author!");

        aa.insertAuthor(a);

        authors = aa.getAuthors();
        Assert.assertEquals(3, authors.size());

        Assert.assertEquals(a.getId(), authors.get(2).getId());
        Assert.assertEquals(a.getName(), authors.get(2).getName());
        Assert.assertEquals(a.getBio(), authors.get(2).getBio());
    }

    @Test
    public void testAuthorUpdateSingleField() {
        AccessAuthors aa = initAccessAuthors();

        Author dbAuthor = aa.getAuthorById("0");
        Assert.assertEquals("0", dbAuthor.getId());
        Assert.assertEquals("bobpiazza", dbAuthor.getName());
        Assert.assertEquals("I love making muffins. Find me on allrecipes: https://www.allrecipes.com/cook/2955506", dbAuthor.getBio());

        Author modifiedAuthor = new Author(dbAuthor.getId(), "some other person", dbAuthor.getBio());
        aa.updateAuthor(modifiedAuthor);

        dbAuthor = aa.getAuthorById("0");
        Assert.assertEquals("0", dbAuthor.getId());
        Assert.assertEquals("some other person", dbAuthor.getName());
        Assert.assertEquals("I love making muffins. Find me on allrecipes: https://www.allrecipes.com/cook/2955506", dbAuthor.getBio());


        modifiedAuthor = new Author(dbAuthor.getId(), dbAuthor.getName(), "This is a new bio!");
        aa.updateAuthor(modifiedAuthor);

        dbAuthor = aa.getAuthorById("0");
        Assert.assertEquals("0", dbAuthor.getId());
        Assert.assertEquals("some other person", dbAuthor.getName());
        Assert.assertEquals("This is a new bio!", dbAuthor.getBio());

    }

    @Test
    public void testAuthorUpdateMultiField() {
        AccessAuthors aa = initAccessAuthors();

        Author dbAuthor = aa.getAuthorById("0");
        Assert.assertEquals("0", dbAuthor.getId());
        Assert.assertEquals("bobpiazza", dbAuthor.getName());
        Assert.assertEquals("I love making muffins. Find me on allrecipes: https://www.allrecipes.com/cook/2955506", dbAuthor.getBio());

        Author modifiedAuthor = new Author(dbAuthor.getId(), "bob the builder", "can we fix it?");
        aa.updateAuthor(modifiedAuthor);

        dbAuthor = aa.getAuthorById("0");
        Assert.assertEquals("0", dbAuthor.getId());
        Assert.assertEquals("bob the builder", dbAuthor.getName());
        Assert.assertEquals("can we fix it?", dbAuthor.getBio());
    }

    @Test
    public void testInvalidAuthorUpdate() {
        AccessAuthors aa = initAccessAuthors();

        List<Author> authors = aa.getAuthors();
        Assert.assertEquals(2, authors.size());
        Assert.assertEquals("bobpiazza", authors.get(0).getName());
        Assert.assertEquals("Myrna", authors.get(1).getName());

        Author invalid = new Author("3", "thomas the tank engine", "It was time for Thomas to leave. He had seen everything.");
        aa.updateAuthor(invalid);

        authors = aa.getAuthors();
        Assert.assertEquals(2, authors.size());
        Assert.assertEquals("bobpiazza", authors.get(0).getName());
        Assert.assertEquals("Myrna", authors.get(1).getName());

        aa.updateAuthor(null);

        authors = aa.getAuthors();
        Assert.assertEquals(2, authors.size());
        Assert.assertEquals("bobpiazza", authors.get(0).getName());
        Assert.assertEquals("Myrna", authors.get(1).getName());
    }

    @Test
    public void testInvalidAuthorIdLookup() {
        AccessAuthors aa = initAccessAuthors();

        Assert.assertNull(aa.getAuthorById("42"));
        Assert.assertNull(aa.getAuthorById(null));
    }

    @Test
    public void testAuthorDelete() {
        AccessAuthors aa = initAccessAuthors();

        List<Author> authors = aa.getAuthors();
        Assert.assertEquals(2, authors.size());
        Assert.assertEquals("0", authors.get(0).getId());
        Assert.assertEquals("1", authors.get(1).getId());

        aa.deleteAuthor(authors.get(0));

        authors = aa.getAuthors();
        Assert.assertEquals(1, authors.size());
        Assert.assertEquals("1", authors.get(0).getId());

        aa.deleteAuthor(authors.get(0));

        authors = aa.getAuthors();
        Assert.assertEquals(0, authors.size());
    }

    @Test
    public void testInvalidAuthorDelete() {
        AccessAuthors aa = initAccessAuthors();

        List<Author> authors = aa.getAuthors();
        Assert.assertEquals(2, authors.size());
        Assert.assertEquals("0", authors.get(0).getId());
        Assert.assertEquals("1", authors.get(1).getId());

        Author invalid = new Author("6", "perry the platypus", "[platypus noises]");
        aa.deleteAuthor(invalid);

        authors = aa.getAuthors();
        Assert.assertEquals(2, authors.size());
        Assert.assertEquals("0", authors.get(0).getId());
        Assert.assertEquals("1", authors.get(1).getId());

        aa.deleteAuthor(null);

        authors = aa.getAuthors();
        Assert.assertEquals(2, authors.size());
        Assert.assertEquals("0", authors.get(0).getId());
        Assert.assertEquals("1", authors.get(1).getId());
    }

    private AccessAuthors initAccessAuthors() {
        Services.createDataStore(new StubDataStore());
        return new AccessAuthors();
    }
}
