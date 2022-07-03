package comp3350.cookit.presentation;

import android.app.Activity;
import android.os.Bundle;
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
    }

    public void onAddToList(View v) {
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
        AccessRecipes accessRecipes = new AccessRecipes();
        AccessAuthors accessAuthors = new AccessAuthors();
        String recipeId = UUID.randomUUID().toString();
        String authorId = UUID.randomUUID().toString();

        int servingSize = Integer.parseInt(this.servingSize.getText().toString());

        Author newAuthor = new Author(authorId, author.getText().toString(), "");
        Recipe newRecipe = new Recipe(recipeId, recipeName.getText().toString(), authorId, directions.getText().toString(), new IngredientList(list), servingSize, new ArrayList<String>());

        accessAuthors.insertAuthor(newAuthor);
        accessRecipes.insertRecipe(newRecipe);
        finish();
    }

    public double getDouble(String selected) {
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

    public void displayIngredients() {
        ingredientLayout.removeAllViews();
        for (String i : ingredientListLayout) {
            TextView text = new TextView(NewRecipeActivity.this);
            text.setText(i);
            ingredientLayout.addView(text);
            text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        }

        ingredientLayout.requestLayout();
    }

    public void clearIngredientFields() {
        ingredientName.getText().clear();
        amountWhole.getText().clear();
        fractionDropdown.setSelection(0);
        units.getText().clear();
    }

    public String toCapitalized(String string) {
        String[] words = string.split("\\s");
        StringBuilder capitalized = new StringBuilder();

        for (String word : words) {
            capitalized.append(word.toUpperCase().charAt(0)).append(word.substring(1)).append(" ");
        }

        return capitalized.toString().trim();
    }
}