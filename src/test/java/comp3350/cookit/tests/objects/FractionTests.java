package comp3350.cookit.tests.objects;

import org.junit.Assert;
import org.junit.Test;

import comp3350.cookit.objects.Fraction;

public class FractionTests {

    @Test
    public void testTypicalEighthsNoWhole() {
        Fraction f;

        f = new Fraction(0.125);
        Assert.assertEquals("1/8", f.getMixedFraction());

        f = new Fraction(0.25);
        Assert.assertEquals("1/4", f.getMixedFraction());

        f = new Fraction(0.375);
        Assert.assertEquals("3/8", f.getMixedFraction());

        f = new Fraction(0.5);
        Assert.assertEquals("1/2", f.getMixedFraction());

        f = new Fraction(0.625);
        Assert.assertEquals("5/8", f.getMixedFraction());

        f = new Fraction(0.75);
        Assert.assertEquals("3/4", f.getMixedFraction());

        f = new Fraction(0.875);
        Assert.assertEquals("7/8", f.getMixedFraction());

        f = new Fraction(1.0);
        Assert.assertEquals("1", f.getMixedFraction());
    }

    @Test
    public void testTypicalEighthsWithWhole() {
        Fraction f;

        f = new Fraction(1.125);
        Assert.assertEquals("1 1/8", f.getMixedFraction());

        f = new Fraction(2.125);
        Assert.assertEquals("2 1/8", f.getMixedFraction());

        f = new Fraction(3.25);
        Assert.assertEquals("3 1/4", f.getMixedFraction());

        f = new Fraction(4.375);
        Assert.assertEquals("4 3/8", f.getMixedFraction());

        f = new Fraction(5.5);
        Assert.assertEquals("5 1/2", f.getMixedFraction());

        f = new Fraction(6.625);
        Assert.assertEquals("6 5/8", f.getMixedFraction());

        f = new Fraction(7.75);
        Assert.assertEquals("7 3/4", f.getMixedFraction());

        f = new Fraction(8.875);
        Assert.assertEquals("8 7/8", f.getMixedFraction());

        f = new Fraction(9.0);
        Assert.assertEquals("9", f.getMixedFraction());

        f = new Fraction(10.125);
        Assert.assertEquals("10 1/8", f.getMixedFraction());

        f = new Fraction(11.125);
        Assert.assertEquals("11 1/8", f.getMixedFraction());

        f = new Fraction(12.25);
        Assert.assertEquals("12 1/4", f.getMixedFraction());

        f = new Fraction(13.375);
        Assert.assertEquals("13 3/8", f.getMixedFraction());

        f = new Fraction(14.5);
        Assert.assertEquals("14 1/2", f.getMixedFraction());

        f = new Fraction(15.625);
        Assert.assertEquals("15 5/8", f.getMixedFraction());

        f = new Fraction(16.75);
        Assert.assertEquals("16 3/4", f.getMixedFraction());

        f = new Fraction(17.875);
        Assert.assertEquals("17 7/8", f.getMixedFraction());

        f = new Fraction(18.0);
        Assert.assertEquals("18", f.getMixedFraction());
    }

    @Test
    public void testTypicalThirdsNoWhole() {
        Fraction f;

        f = new Fraction(0.33);
        Assert.assertEquals("1/3", f.getMixedFraction());

        f = new Fraction(0.66);
        Assert.assertEquals("2/3", f.getMixedFraction());

        f = new Fraction(0.67);
        Assert.assertEquals("2/3", f.getMixedFraction());
    }

    @Test
    public void testTypicalThirdsWithWhole() {
        Fraction f;

        f = new Fraction(1.33);
        Assert.assertEquals("1 1/3", f.getMixedFraction());

        f = new Fraction(2.66);
        Assert.assertEquals("2 2/3", f.getMixedFraction());

        f = new Fraction(3.67);
        Assert.assertEquals("3 2/3", f.getMixedFraction());

        f = new Fraction(10.33);
        Assert.assertEquals("10 1/3", f.getMixedFraction());

        f = new Fraction(11.66);
        Assert.assertEquals("11 2/3", f.getMixedFraction());

        f = new Fraction(12.67);
        Assert.assertEquals("12 2/3", f.getMixedFraction());
    }

    @Test
    public void testMultiplyEighthsWithWhole() {
        Fraction f;

        f = new Fraction(1.125).multiply(2);
        Assert.assertEquals("2 1/4", f.getMixedFraction());
        Assert.assertEquals(2.25, f.getDouble(), 0.001);

        f = new Fraction(2.125).multiply(3);
        Assert.assertEquals("6 3/8", f.getMixedFraction());
        Assert.assertEquals(6.375, f.getDouble(), 0.001);

        f = new Fraction(3.125).multiply(4);
        Assert.assertEquals("12 1/2", f.getMixedFraction());
        Assert.assertEquals(12.5, f.getDouble(), 0.001);

        f = new Fraction(4.125).multiply(8);
        Assert.assertEquals("33", f.getMixedFraction());
        Assert.assertEquals(33.0, f.getDouble(), 0.001);

        f = new Fraction(5.125).multiply(18);
        Assert.assertEquals("92 1/4", f.getMixedFraction());
        Assert.assertEquals(92.25, f.getDouble(), 0.001);

        f = new Fraction(1.25).multiply(2);
        Assert.assertEquals("2 1/2", f.getMixedFraction());
        Assert.assertEquals(2.5, f.getDouble(), 0.001);

        f = new Fraction(2.25).multiply(3);
        Assert.assertEquals("6 3/4", f.getMixedFraction());
        Assert.assertEquals(6.75, f.getDouble(), 0.001);

        f = new Fraction(3.25).multiply(4);
        Assert.assertEquals("13", f.getMixedFraction());
        Assert.assertEquals(13.0, f.getDouble(), 0.001);

        f = new Fraction(4.25).multiply(5);
        Assert.assertEquals("21 1/4", f.getMixedFraction());
        Assert.assertEquals(21.25, f.getDouble(), 0.001);
    }

    @Test
    public void testMultiplyEighthsNoWhole() {
        Fraction f;

        f = new Fraction(0.125).multiply(2);
        Assert.assertEquals("1/4", f.getMixedFraction());
        Assert.assertEquals(0.25, f.getDouble(), 0.001);

        f = new Fraction(0.125).multiply(3);
        Assert.assertEquals("3/8", f.getMixedFraction());
        Assert.assertEquals(0.375, f.getDouble(), 0.001);

        f = new Fraction(0.125).multiply(4);
        Assert.assertEquals("1/2", f.getMixedFraction());
        Assert.assertEquals(0.5, f.getDouble(), 0.001);

        f = new Fraction(0.125).multiply(8);
        Assert.assertEquals("1", f.getMixedFraction());
        Assert.assertEquals(1.0, f.getDouble(), 0.001);

        f = new Fraction(0.125).multiply(18);
        Assert.assertEquals("2 1/4", f.getMixedFraction());
        Assert.assertEquals(2.25, f.getDouble(), 0.001);

        f = new Fraction(0.25).multiply(2);
        Assert.assertEquals("1/2", f.getMixedFraction());
        Assert.assertEquals(0.5, f.getDouble(), 0.001);

        f = new Fraction(0.25).multiply(3);
        Assert.assertEquals("3/4", f.getMixedFraction());
        Assert.assertEquals(0.75, f.getDouble(), 0.001);

        f = new Fraction(0.25).multiply(4);
        Assert.assertEquals("1", f.getMixedFraction());
        Assert.assertEquals(1.0, f.getDouble(), 0.001);

        f = new Fraction(0.25).multiply(5);
        Assert.assertEquals("1 1/4", f.getMixedFraction());
        Assert.assertEquals(1.25, f.getDouble(), 0.001);
    }

    @Test
    public void testMultiplyThirdsNoWhole() {
        Fraction f;

        f = new Fraction(0.33).multiply(2);
        Assert.assertEquals("2/3", f.getMixedFraction());
        Assert.assertEquals(0.66, f.getDouble(), 0.01);

        f = new Fraction(0.33).multiply(3);
        Assert.assertEquals("1", f.getMixedFraction());
        Assert.assertEquals(1.0, f.getDouble(), 0.005);

        f = new Fraction(0.33).multiply(5);
        Assert.assertEquals("1 2/3", f.getMixedFraction());
        Assert.assertEquals(1.66, f.getDouble(), 0.01);

        f = new Fraction(0.33).multiply(6);
        Assert.assertEquals("2", f.getMixedFraction());
        Assert.assertEquals(2.0, f.getDouble(), 0.005);

        f = new Fraction(0.66).multiply(2);
        Assert.assertEquals("1 1/3", f.getMixedFraction());
        Assert.assertEquals(1.33, f.getDouble(), 0.005);

        f = new Fraction(0.66).multiply(3);
        Assert.assertEquals("2", f.getMixedFraction());
        Assert.assertEquals(2.0, f.getDouble(), 0.005);

        f = new Fraction(0.66).multiply(5);
        Assert.assertEquals("3 1/3", f.getMixedFraction());
        Assert.assertEquals(3.33, f.getDouble(), 0.005);

        f = new Fraction(0.66).multiply(6);
        Assert.assertEquals("4", f.getMixedFraction());
        Assert.assertEquals(4.0, f.getDouble(), 0.005);
    }

    @Test
    public void testMultiplyThirdsWithWhole() {
        Fraction f;

        f = new Fraction(1.33).multiply(2);
        Assert.assertEquals("2 2/3", f.getMixedFraction());
        Assert.assertEquals(2.66, f.getDouble(), 0.01);

        f = new Fraction(2.33).multiply(3);
        Assert.assertEquals("7", f.getMixedFraction());
        Assert.assertEquals(7.0, f.getDouble(), 0.005);

        f = new Fraction(3.33).multiply(5);
        Assert.assertEquals("16 2/3", f.getMixedFraction());
        Assert.assertEquals(16.66, f.getDouble(), 0.01);

        f = new Fraction(4.33).multiply(6);
        Assert.assertEquals("26", f.getMixedFraction());
        Assert.assertEquals(26.0, f.getDouble(), 0.005);

        f = new Fraction(5.66).multiply(2);
        Assert.assertEquals("11 1/3", f.getMixedFraction());
        Assert.assertEquals(11.33, f.getDouble(), 0.005);

        f = new Fraction(6.66).multiply(3);
        Assert.assertEquals("20", f.getMixedFraction());
        Assert.assertEquals(20.0, f.getDouble(), 0.005);

        f = new Fraction(7.66).multiply(5);
        Assert.assertEquals("38 1/3", f.getMixedFraction());
        Assert.assertEquals(38.33, f.getDouble(), 0.005);

        f = new Fraction(8.66).multiply(6);
        Assert.assertEquals("52", f.getMixedFraction());
        Assert.assertEquals(52.0, f.getDouble(), 0.005);
    }

    @Test
    public void testZeroInput() {
        Fraction f;

        f = new Fraction(0);
        Assert.assertEquals("0", f.getMixedFraction());
        Assert.assertEquals(0.0, f.getDouble(), 0.005);
    }

    @Test
    public void testMultiplyByZero() {
        Fraction f;

        f = new Fraction(1.5).multiply(0);
        Assert.assertEquals("0", f.getMixedFraction());
        Assert.assertEquals(0.0, f.getDouble(), 0.005);

        f = new Fraction(0.33).multiply(0);
        Assert.assertEquals("0", f.getMixedFraction());
        Assert.assertEquals(0.0, f.getDouble(), 0.005);

        f = new Fraction(1.66).multiply(0);
        Assert.assertEquals("0", f.getMixedFraction());
        Assert.assertEquals(0.0, f.getDouble(), 0.005);
    }

    @Test(expected = ArithmeticException.class)
    public void testNegativeValue() {
        new Fraction(-0.50);
    }

    @Test(expected = ArithmeticException.class)
    public void testMultiplyByNegative() {
        new Fraction(1.5).multiply(-2);
    }

    @Test
    public void testUncleanFraction() {
        try {
            new Fraction(0.429); // 3/7
            Assert.fail("Expected NumberFormatException");
        } catch (ArithmeticException ignored) {

        }

        try {
            new Fraction(0.6); // 3/5
            Assert.fail("Expected NumberFormatException");
        } catch (ArithmeticException ignored) {

        }

        try {
            new Fraction(0.454545); // 5/11
            Assert.fail("Expected NumberFormatException");
        } catch (ArithmeticException ignored) {

        }

        try {
            new Fraction(0.0625); // 1/16
            Assert.fail("Expected NumberFormatException");
        } catch (ArithmeticException ignored) {

        }

        try {
            new Fraction(0.1875); // 3/16
            Assert.fail("Expected NumberFormatException");
        } catch (ArithmeticException ignored) {

        }

        try {
            new Fraction(0.11111111111); // 1/9
            Assert.fail("Expected NumberFormatException");
        } catch (ArithmeticException ignored) {

        }

        try {
            new Fraction(0.22222222222); // 2/9
            Assert.fail("Expected NumberFormatException");
        } catch (ArithmeticException ignored) {

        }
    }
}
