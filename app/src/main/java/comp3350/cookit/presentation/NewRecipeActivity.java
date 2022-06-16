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
    IngredientList ingredientList = new IngredientList();
    List<Ingredient> list = ingredientList.getIngredients();
    ArrayList<String> ingredientListLayout = new ArrayList<>();

    LinearLayout ingredientLayout;
    Spinner fraction_dropdown;

    EditText recipe_name;
    EditText author;
    EditText serving_size;
    EditText ingredient_name;
    EditText amount_whole;
    EditText units;
    EditText directions;

    String recipeName;
    String authorName;
    String directionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);

        ingredientLayout = findViewById(R.id.ingredientList);
        fraction_dropdown = (Spinner) findViewById(R.id.amountFraction);
        fraction_dropdown.setSelection(0);

        recipe_name = (EditText) findViewById(R.id.recipeName);
        author = (EditText) findViewById(R.id.authorName);
        serving_size = (EditText) findViewById(R.id.servingSize);
        ingredient_name = (EditText) findViewById(R.id.ingredientName);
        amount_whole = (EditText) findViewById(R.id.amountWhole);
        units = (EditText) findViewById(R.id.units);
        directions = (EditText) findViewById(R.id.directionList);
    }

    public void onAddToList(View v) {
        String amountWhole = amount_whole.getText().toString();
        String amountFraction = fraction_dropdown.getSelectedItem().toString();
        String unitsString = units.getText().toString();
        String ingredientName = ingredient_name.getText().toString();
        double amount = (double) Integer.parseInt(amountWhole) + getDouble(amountFraction);

        // Display nothing if selected == "none"
        String fraction = !amountFraction.equals("none")? amountFraction + " " : "";
        String ingredientString = amountWhole + " " + fraction + unitsString.toLowerCase()
                                  + " " + toCapitalized(ingredientName);
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

        int servingSize = Integer.parseInt(serving_size.getText().toString());

        Author newAuthor = new Author(authorId, authorName, "");
        Recipe newRecipe = new Recipe(recipeId, recipeName, authorName, directionList, ingredientList, servingSize);

        accessRecipes.insertRecipe(newRecipe);
        accessAuthors.insertAuthor(newAuthor);
        finish();
    }

    public double getDouble(String selected) {
        double doubleVal = 0;
        int numerator;
        int denominator;

        if(selected != null && !selected.equals("none")){
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
        ingredient_name.getText().clear();
        amount_whole.getText().clear();
        fraction_dropdown.setSelection(0);
        units.getText().clear();
    }

    public String toCapitalized(String string) {
        String[] words = string.split("\\s");
        String capitalized = "";

        for(String word : words) {
            capitalized += word.toUpperCase().charAt(0) + word.substring(1) + " ";
        }

        return capitalized.trim();
    }
}