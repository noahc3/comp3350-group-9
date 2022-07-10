package comp3350.cookit.presentation;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import comp3350.cookit.R;
import comp3350.cookit.business.AccessAuthors;
import comp3350.cookit.business.AccessRecipes;
import comp3350.cookit.objects.Author;
import comp3350.cookit.objects.Ingredient;
import comp3350.cookit.objects.IngredientList;
import comp3350.cookit.objects.Recipe;

public class NewRecipeActivity extends Activity {
    private final List<Ingredient> list = new ArrayList<>();
    private final ArrayList<String> ingredientListLayout = new ArrayList<>();

    private LinearLayout ingredientLayout;
    private Spinner fractionDropdown;
    private EditText recipeName;
    private EditText author;
    private EditText servingSize;
    private EditText ingredientName;
    private EditText amountWhole;
    private EditText units;
    private EditText directions;
    private EditText prepTime;
    private EditText cookTime;
    private EditText difficulty;

    private AccessRecipes accessRecipes = new AccessRecipes();
    private AccessAuthors accessAuthors = new AccessAuthors();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);

        ingredientLayout = findViewById(R.id.ingredientList);
        fractionDropdown = findViewById(R.id.amountFraction);
        fractionDropdown.setSelection(0);

        recipeName = findViewById(R.id.recipeName);
        author = findViewById(R.id.authorName);
        servingSize = findViewById(R.id.servingSize);
        ingredientName = findViewById(R.id.ingredientName);
        amountWhole = findViewById(R.id.amountWhole);
        units = findViewById(R.id.units);
        directions = findViewById(R.id.directionList);
        prepTime = findViewById(R.id.prepTime);
        cookTime = findViewById(R.id.cookTime);
        difficulty = findViewById(R.id.difficulty);

    }

    public void onAddToList(View v) {
        // Will only be entered if we are inserting into an "empty" list with an error message:
        // Clear the ingredient list to get rid of the error message
        if(ingredientListLayout.get(0).contains("You must"))
            ingredientListLayout.clear();

        String amountWhole = this.amountWhole.getText().toString();
        String amountFraction = fractionDropdown.getSelectedItem().toString();
        String unitsString = units.getText().toString();
        String ingredientName = this.ingredientName.getText().toString();
        double amount = (double) Integer.parseInt(amountWhole) + getDouble(amountFraction);

        // Display nothing if selected == "none"
        String fraction = !amountFraction.equals("none") ? amountFraction + " " : "";
        String ingredientString = amountWhole + " " + fraction + unitsString.toLowerCase() + " " + toCapitalized(ingredientName);
        ingredientListLayout.add(ingredientString);

        Ingredient ingredient = new Ingredient(ingredientName, amount, unitsString);
        list.add(ingredient);

        displayIngredients();
        clearIngredientFields();
    }

    public void onCancel(View v) {
        finish();
    }

    public void onSubmit(View v) {
        if(validateInput()) {
            String recipeId = UUID.randomUUID().toString();
            String authorId = UUID.randomUUID().toString();

            int servingSize = Integer.parseInt(this.servingSize.getText().toString());

            Author newAuthor = new Author(authorId, author.getText().toString(), "");
            Recipe newRecipe = new Recipe(recipeId, recipeName.getText().toString(), authorId, directions.getText().toString(), new IngredientList(list), servingSize, new ArrayList<String>(), prepTime, cookTime, difficulty);

            accessAuthors.insertAuthor(newAuthor);
            accessRecipes.insertRecipe(newRecipe);
            finish();
        }
        else {
            showTextInputErrors();
        }
    }

    // private as these are helper methods

    private boolean validateInput() { // verify if every required field is filled
        boolean isValid = true; // initially true

        if (TextUtils.isEmpty(recipeName.getText()) || TextUtils.isEmpty(author.getText())
                || TextUtils.isEmpty(servingSize.getText()) || TextUtils.isEmpty(directions.getText())
                || ingredientListLayout.isEmpty())
            isValid = false; // change to false if one of the fields are empty

        return isValid;
    }

    private void showTextInputErrors() {
        // Show an error message for each text input that is missing
        if(TextUtils.isEmpty(recipeName.getText()))
            recipeName.setError("You must provide a name for the recipe");

        if(TextUtils.isEmpty(author.getText()))
            author.setError("You must provide an author");

        if(TextUtils.isEmpty(servingSize.getText()))
            servingSize.setError("You must provide a serving size");

        if(TextUtils.isEmpty(directions.getText()))
            directions.setError("You must give directions");

        if(ingredientListLayout.isEmpty()) {
            ingredientListLayout.add("You must add at least one (1) ingredient");
            displayIngredients();
        }
    }

    private double getDouble(String selected) {
        double doubleVal = 0;
        int numerator;
        int denominator;

        if (selected != null && !selected.equals("none")) {
            numerator = selected.charAt(0) - '0';
            denominator = selected.charAt(2) - '0';
            doubleVal = (double) numerator / (double) denominator;
        }

        return doubleVal;
    }

    private void displayIngredients() {
        ingredientLayout.removeAllViews();
        for (String i : ingredientListLayout) {
            TextView text = new TextView(NewRecipeActivity.this);
            text.setText(i);

            if(i.contains("You must")) // i is an error message
                text.setTextColor(Color.RED);

            ingredientLayout.addView(text);
            text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        }

        ingredientLayout.requestLayout();
    }

    private void clearIngredientFields() {
        ingredientName.getText().clear();
        amountWhole.getText().clear();
        fractionDropdown.setSelection(0);
        units.getText().clear();
    }

    private String toCapitalized(String string) {
        String[] words = string.split("\\s");
        StringBuilder capitalized = new StringBuilder();

        for (String word : words) {
            capitalized.append(word.toUpperCase().charAt(0)).append(word.substring(1)).append(" ");
        }

        return capitalized.toString().trim();
    }
}