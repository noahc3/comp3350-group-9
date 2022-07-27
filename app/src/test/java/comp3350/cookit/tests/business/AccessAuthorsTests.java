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

        Assert.assertEquals(11, authors.size());

        Assert.assertEquals("0", authors.get(0).getId());
        Assert.assertEquals("bobpiazza", authors.get(0).getName());
        Assert.assertEquals("I love making muffins. Find me on allrecipes: https://www.allrecipes.com/cook/2955506", authors.get(0).getBio());

        Assert.assertEquals("1", authors.get(1).getId());
        Assert.assertEquals("Myrna", authors.get(1).getName());
        Assert.assertEquals("Find me on allrecipes: https://www.allrecipes.com/cook/2792648?content=recipes", authors.get(1).getBio());

        Assert.assertEquals("2", authors.get(2).getId());
        Assert.assertEquals("Hannah Zimmerman", authors.get(2).getName());
        Assert.assertEquals("Hannah has been working as a food photographer and recipe developer since 2018, and has worked with brands & publications across the U.S. to create mouth-watering food content. She began contributing to Simply Recipes in 2021. See them on Simply Recipes: https://www.simplyrecipes.com/hannah-zimmerman-5195688", authors.get(2).getBio());

        Assert.assertEquals("5", authors.get(5).getId());
        Assert.assertEquals("Shilpa Uskokovic", authors.get(5).getName());
        Assert.assertEquals("Shilpa Uskokovic is a food editor and recipe developer based in NYC. She was previously a line and pastry cook in some of NYC's top rated restaurants like Marea, The NoMad Hotel, Maialino and Perry Street. A graduate of The Culinary Institute of America, Shilpa loves reading, grocery shopping, and eating a little too much cake . She was born and raised in Chennai, India. Find them on Simply Recipes: https://www.simplyrecipes.com/shilpa-uskokovic-5219561", authors.get(5).getBio());

        Assert.assertEquals("6", authors.get(6).getId());
        Assert.assertEquals("Ariane Resnick", authors.get(6).getName());
        Assert.assertEquals("Ariane is the author of The Bone Broth Miracle (2015), The Thinking Girl's Guide to Drinking (2016), Wake/Sleep (2018), How to Be Well When You're Not (foreword by P!nk, 2018), and Disney Princess Healthy Treats (2021). She has been a contributor since 2020 for Simply Recipes but began professional writing 15 years ago for Provincetown, Massachusetts newspaper and Curve Magazine. Find them on Simply Recipes: https://www.simplyrecipes.com/ariane-resnick-5091819", authors.get(6).getBio());

        Assert.assertEquals("9", authors.get(9).getId());
        Assert.assertEquals("Coco Morante", authors.get(9).getName());
        Assert.assertEquals("Coco started a food blog in 2011, and soon after that began managing social media and developing recipes for Garlic Gold, an organic foods company. She began developing recipes professionally for The Kitchn in 2014, joined Simply Recipes as a contributor in 2016, and published her first cookbook, The Essential Instant Pot Cookbook, with Ten Speed Press in 2017. Coco has written five cookbooks in total, with a new project in the works presently. Find them on Simply Recipes: https://www.simplyrecipes.com/coco-morante-5091788", authors.get(9).getBio());

        Assert.assertEquals("10", authors.get(10).getId());
        Assert.assertEquals("Micah Siva", authors.get(10).getName());
        Assert.assertEquals("Micah Siva is a trained chef, registered dietitian, recipe writer, and food photographer, specializing in modern Jewish cuisine. Find them on Simply Recipes: https://www.simplyrecipes.com/micah-siva-5270458", authors.get(10).getBio());
    }

    @Test
    public void testNewAuthorFlow() {
        AccessAuthors aa = initAccessAuthors();

        List<Author> authors = aa.getAuthors();
        Assert.assertEquals(11, authors.size());

        Author a = new Author("11", "An Author", "Hi, I am an author!");

        aa.insertAuthor(a);

        authors = aa.getAuthors();
        Assert.assertEquals(12, authors.size());

        Assert.assertEquals(a.getId(), authors.get(11).getId());
        Assert.assertEquals(a.getName(), authors.get(11).getName());
        Assert.assertEquals(a.getBio(), authors.get(11).getBio());
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
        Assert.assertEquals(11, authors.size());
        Assert.assertEquals("bobpiazza", authors.get(0).getName());
        Assert.assertEquals("Myrna", authors.get(1).getName());
        Assert.assertEquals("Hannah Zimmerman", authors.get(2).getName());
        Assert.assertEquals("Elise Bauer", authors.get(3).getName());
        Assert.assertEquals("Sara Bir", authors.get(4).getName());
        Assert.assertEquals("Shilpa Uskokovic", authors.get(5).getName());
        Assert.assertEquals("Ariane Resnick", authors.get(6).getName());
        Assert.assertEquals("Nick Evans", authors.get(7).getName());
        Assert.assertEquals("Georgia Freedman", authors.get(8).getName());
        Assert.assertEquals("Coco Morante", authors.get(9).getName());
        Assert.assertEquals("Micah Siva", authors.get(10).getName());

        Author invalid = new Author("37", "thomas the tank engine", "It was time for Thomas to leave. He had seen everything.");
        aa.updateAuthor(invalid);

        authors = aa.getAuthors();
        Assert.assertEquals(11, authors.size());
        Assert.assertEquals("bobpiazza", authors.get(0).getName());
        Assert.assertEquals("Myrna", authors.get(1).getName());
        Assert.assertEquals("Hannah Zimmerman", authors.get(2).getName());
        Assert.assertEquals("Elise Bauer", authors.get(3).getName());
        Assert.assertEquals("Sara Bir", authors.get(4).getName());
        Assert.assertEquals("Shilpa Uskokovic", authors.get(5).getName());
        Assert.assertEquals("Ariane Resnick", authors.get(6).getName());
        Assert.assertEquals("Nick Evans", authors.get(7).getName());
        Assert.assertEquals("Georgia Freedman", authors.get(8).getName());
        Assert.assertEquals("Coco Morante", authors.get(9).getName());
        Assert.assertEquals("Micah Siva", authors.get(10).getName());

        aa.updateAuthor(null);

        authors = aa.getAuthors();
        Assert.assertEquals(11, authors.size());
        Assert.assertEquals("bobpiazza", authors.get(0).getName());
        Assert.assertEquals("Myrna", authors.get(1).getName());
        Assert.assertEquals("Hannah Zimmerman", authors.get(2).getName());
        Assert.assertEquals("Elise Bauer", authors.get(3).getName());
        Assert.assertEquals("Sara Bir", authors.get(4).getName());
        Assert.assertEquals("Shilpa Uskokovic", authors.get(5).getName());
        Assert.assertEquals("Ariane Resnick", authors.get(6).getName());
        Assert.assertEquals("Nick Evans", authors.get(7).getName());
        Assert.assertEquals("Georgia Freedman", authors.get(8).getName());
        Assert.assertEquals("Coco Morante", authors.get(9).getName());
        Assert.assertEquals("Micah Siva", authors.get(10).getName());
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
        Assert.assertEquals(11, authors.size());
        Assert.assertEquals("0", authors.get(0).getId());
        Assert.assertEquals("1", authors.get(1).getId());
        Assert.assertEquals("2", authors.get(2).getId());
        Assert.assertEquals("3", authors.get(3).getId());
        Assert.assertEquals("4", authors.get(4).getId());
        Assert.assertEquals("5", authors.get(5).getId());
        Assert.assertEquals("6", authors.get(6).getId());
        Assert.assertEquals("7", authors.get(7).getId());
        Assert.assertEquals("8", authors.get(8).getId());
        Assert.assertEquals("9", authors.get(9).getId());
        Assert.assertEquals("10", authors.get(10).getId());

        aa.deleteAuthor(authors.get(0));

        authors = aa.getAuthors();
        Assert.assertEquals(10, authors.size());
        Assert.assertEquals("1", authors.get(0).getId());
        Assert.assertEquals("2", authors.get(1).getId());
        Assert.assertEquals("3", authors.get(2).getId());
        Assert.assertEquals("4", authors.get(3).getId());
        Assert.assertEquals("5", authors.get(4).getId());
        Assert.assertEquals("6", authors.get(5).getId());
        Assert.assertEquals("7", authors.get(6).getId());
        Assert.assertEquals("8", authors.get(7).getId());
        Assert.assertEquals("9", authors.get(8).getId());
        Assert.assertEquals("10", authors.get(9).getId());

        aa.deleteAuthor(authors.get(3));

        authors = aa.getAuthors();
        Assert.assertEquals(9, authors.size());
        Assert.assertEquals("1", authors.get(0).getId());
        Assert.assertEquals("2", authors.get(1).getId());
        Assert.assertEquals("3", authors.get(2).getId());
        Assert.assertEquals("5", authors.get(3).getId());
        Assert.assertEquals("6", authors.get(4).getId());
        Assert.assertEquals("7", authors.get(5).getId());
        Assert.assertEquals("8", authors.get(6).getId());
        Assert.assertEquals("9", authors.get(7).getId());
        Assert.assertEquals("10", authors.get(8).getId());

        aa.deleteAuthor(authors.get(8));

        authors = aa.getAuthors();
        Assert.assertEquals(8, authors.size());
        Assert.assertEquals("1", authors.get(0).getId());
        Assert.assertEquals("2", authors.get(1).getId());
        Assert.assertEquals("3", authors.get(2).getId());
        Assert.assertEquals("5", authors.get(3).getId());
        Assert.assertEquals("6", authors.get(4).getId());
        Assert.assertEquals("7", authors.get(5).getId());
        Assert.assertEquals("8", authors.get(6).getId());
        Assert.assertEquals("9", authors.get(7).getId());
    }

    @Test
    public void testInvalidAuthorDelete() {
        AccessAuthors aa = initAccessAuthors();

        List<Author> authors = aa.getAuthors();
        Assert.assertEquals(11, authors.size());
        Assert.assertEquals("0", authors.get(0).getId());
        Assert.assertEquals("1", authors.get(1).getId());
        Assert.assertEquals("2", authors.get(2).getId());
        Assert.assertEquals("3", authors.get(3).getId());
        Assert.assertEquals("4", authors.get(4).getId());
        Assert.assertEquals("5", authors.get(5).getId());
        Assert.assertEquals("6", authors.get(6).getId());
        Assert.assertEquals("7", authors.get(7).getId());
        Assert.assertEquals("8", authors.get(8).getId());
        Assert.assertEquals("9", authors.get(9).getId());
        Assert.assertEquals("10", authors.get(10).getId());

        Author invalid = new Author("26", "perry the platypus", "[platypus noises]");
        aa.deleteAuthor(invalid);

        authors = aa.getAuthors();
        Assert.assertEquals(11, authors.size());
        Assert.assertEquals("0", authors.get(0).getId());
        Assert.assertEquals("1", authors.get(1).getId());
        Assert.assertEquals("2", authors.get(2).getId());
        Assert.assertEquals("3", authors.get(3).getId());
        Assert.assertEquals("4", authors.get(4).getId());
        Assert.assertEquals("5", authors.get(5).getId());
        Assert.assertEquals("6", authors.get(6).getId());
        Assert.assertEquals("7", authors.get(7).getId());
        Assert.assertEquals("8", authors.get(8).getId());
        Assert.assertEquals("9", authors.get(9).getId());
        Assert.assertEquals("10", authors.get(10).getId());

        aa.deleteAuthor(null);

        authors = aa.getAuthors();
        Assert.assertEquals(11, authors.size());
        Assert.assertEquals("0", authors.get(0).getId());
        Assert.assertEquals("1", authors.get(1).getId());
        Assert.assertEquals("2", authors.get(2).getId());
        Assert.assertEquals("3", authors.get(3).getId());
        Assert.assertEquals("4", authors.get(4).getId());
        Assert.assertEquals("5", authors.get(5).getId());
        Assert.assertEquals("6", authors.get(6).getId());
        Assert.assertEquals("7", authors.get(7).getId());
        Assert.assertEquals("8", authors.get(8).getId());
        Assert.assertEquals("9", authors.get(9).getId());
        Assert.assertEquals("10", authors.get(10).getId());
    }

    private AccessAuthors initAccessAuthors() {
        Services.createDataStore(new StubDataStore());
        return new AccessAuthors();
    }
}
