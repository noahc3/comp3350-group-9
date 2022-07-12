package comp3350.cookit.business;

public class StringUtilities {
    public static String toCapitalized(String string) {
        String result = null;

        if (string != null) {
            String[] words = string.split("\\s");
            StringBuilder capitalized = new StringBuilder();

            for (String word : words) {
                if (!word.isEmpty())
                    capitalized.append(word.toUpperCase().charAt(0)).append(word.substring(1)).append(" ");
            }

            result = capitalized.toString().trim();
        }

        return result;
    }
}
