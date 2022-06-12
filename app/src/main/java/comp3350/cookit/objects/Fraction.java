package comp3350.cookit.objects;

public class Fraction {
    private static final double delta = 0.011;

    private int numerator;
    private int denominator;

    public Fraction(double value) {
        if (value < 0) {
            throw new NumberFormatException("Negative values are not supported.");
        }

        numerator = 0;
        denominator = 0;
        double decimal = value - Math.floor(value);
        if (decimal < delta) {
            numerator = 0;
            denominator = 1;
        } else if (Math.abs(decimal - 0.33) < delta) {
            numerator = 1;
            denominator = 3;
        } else if (Math.abs(decimal - 0.66) < delta) {
            numerator = 2;
            denominator = 3;
        } else if (Math.abs(value / 0.125 - Math.floor(value / 0.125)) < delta) {
            // if its not thirds, we can find the nearest eighth.
            numerator = (int) Math.round(decimal / 0.125);
            denominator = 8;

            // reduce fraction to quarters or halves if possible
            while(numerator % 2 == 0 && denominator % 2 == 0) {
                numerator /= 2;
                denominator /= 2;
            }
        } else {
            // the decimal cannot be expressed as wholes, thirds, or eighths, therefore it is unsupported.
            throw new NumberFormatException("Double value must be expressible as a whole, thirds, or eighths.");
        }

        numerator += ((int) Math.floor(value)) * denominator;
    }

    public Fraction multiply(int factor) {
        if (factor < 0) {
            throw new NumberFormatException("Negative values are not supported.");
        }

        numerator *= factor;

        // reduce fraction to quarters, halves, or whole if possible
        while(numerator % 2 == 0 && denominator % 2 == 0) {
            numerator /= 2;
            denominator /= 2;
        }

        // try reduce a fraction of thirds to whole if possible
        while(numerator % 3 == 0 && denominator % 3 == 0) {
            numerator /= 3;
            denominator /= 3;
        }

        return this;
    }

    public double getDouble() {
        return (double) numerator / (double) denominator;
    }

    public String getMixedFraction() {
        int whole = (int) Math.floor(this.getDouble());
        int mixedNumerator = numerator - (whole * denominator);
        String result = "";
        if (whole > 0 || mixedNumerator < delta) {
            result += whole + " ";
        }

        if (mixedNumerator > delta) {
            result += mixedNumerator + "/" + denominator;
        }

        return result.trim();
    }
}
