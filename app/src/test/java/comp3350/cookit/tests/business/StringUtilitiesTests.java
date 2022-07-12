package comp3350.cookit.tests.business;

import org.junit.Assert;
import org.junit.Test;

import comp3350.cookit.business.StringUtilities;

public class StringUtilitiesTests {
    public StringUtilitiesTests() {

    }

    @Test
    public void testTypical() {
        String result = StringUtilities.toCapitalized("this is a simple test case");
        Assert.assertEquals("This Is A Simple Test Case", result);

        result = StringUtilities.toCapitalized("Something that already has a few Capitals.");
        Assert.assertEquals("Something That Already Has A Few Capitals.", result);

        result = StringUtilities.toCapitalized("NOW WITH ALL CAPITALS.");
        Assert.assertEquals("NOW WITH ALL CAPITALS.", result);

        result = StringUtilities.toCapitalized("oneword.");
        Assert.assertEquals("Oneword.", result);
    }

    @Test
    public void testSpacesAtEnds() {
        String result = StringUtilities.toCapitalized("Here is a space at the end. ");
        Assert.assertEquals("Here Is A Space At The End.", result);

        result = StringUtilities.toCapitalized(" Here is a space at the start.");
        Assert.assertEquals("Here Is A Space At The Start.", result);

        result = StringUtilities.toCapitalized(" Here is a space at the start and end. ");
        Assert.assertEquals("Here Is A Space At The Start And End.", result);
    }

    @Test
    public void testClumpedSpaces() {
        String result = StringUtilities.toCapitalized("many spaces  in   between.");
        Assert.assertEquals("Many Spaces In Between.", result);

        result = StringUtilities.toCapitalized("   clumped  spaces at    the  ends.   ");
        Assert.assertEquals("Clumped Spaces At The Ends.", result);

        result = StringUtilities.toCapitalized("          "); //just spaces
        Assert.assertEquals("", result);
    }

    @Test
    public void testNull() {
        String result = StringUtilities.toCapitalized(null);
        Assert.assertNull(result);
    }
}
