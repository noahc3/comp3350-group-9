package comp3350.cookit.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import comp3350.cookit.R;
import comp3350.cookit.application.Main;
import comp3350.cookit.application.Services;
import comp3350.cookit.business.Convert;
import comp3350.cookit.objects.Author;
import comp3350.cookit.objects.Fraction;
import comp3350.cookit.objects.Ingredient;
import comp3350.cookit.objects.Recipe;
import comp3350.cookit.persistence.DataAccessStub;

public class DisplayRecipeActivity extends Activity {
    private final int defaultServingSize = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Main.startUp();
        setContentView(R.layout.activity_display_recipe);

        displayRecipe(defaultServingSize);

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                displayRecipe(position + 1);
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void displayRecipe(int servingSize) {
        DataAccessStub dataAccessStub = Services.getDataAccess(Main.dbName);
        String recipeId = getIntent().getExtras().getString("recipeId");
        Recipe recipe = dataAccessStub.getRecipeById(recipeId);
        Author author = dataAccessStub.getAuthorById(recipe.getAuthorId());
        recipe = Convert.multiplyServingSize(recipe, servingSize);

        ((TextView) findViewById(R.id.recipeTitle)).setText(recipe.getTitle());
        ((TextView) findViewById(R.id.recipeAuthor)).setText(getString(R.string.written_by, author.getName()));
        ((TextView) findViewById(R.id.servingsText)).setText(getString(R.string.creates_servings, recipe.getServingSize()));
        ((TextView) findViewById(R.id.recipeInstructions)).setText(recipe.getContent());

        LinearLayout ingredientLayout = findViewById(R.id.ingredientList);
        ingredientLayout.removeAllViews();
        for (Ingredient i : recipe.getIngredientList().getIngredients()) {
            TextView text = new TextView(DisplayRecipeActivity.this);
            text.setText(getString(R.string.ingredient_format, new Fraction(i.getQuantity()).getMixedFraction(), i.getMeasurement(), i.getName()));
            ingredientLayout.addView(text);
            text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        }

        ingredientLayout.requestLayout();
    }


}
