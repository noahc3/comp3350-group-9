package comp3350.cookit.presentation;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import comp3350.cookit.R;
import comp3350.cookit.application.Main;
import comp3350.cookit.business.AccessAuthors;
import comp3350.cookit.business.AccessRecipes;
import comp3350.cookit.business.Convert;
import comp3350.cookit.objects.Author;
import comp3350.cookit.objects.Fraction;
import comp3350.cookit.objects.Ingredient;
import comp3350.cookit.objects.Recipe;

public class DisplayRecipeActivity extends Activity {
    private static final int defaultServingSize = 1;

    TextView recipeTitle;
    TextView recipeAuthor;
    TextView servingsText;
    TextView recipeInstructions;
    LinearLayout ingredientsList;
    LinearLayout tagsList;
    Spinner servingsDropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Main.startUp();
        setContentView(R.layout.activity_display_recipe);

        recipeTitle = findViewById(R.id.recipeTitle);
        recipeAuthor = findViewById(R.id.recipeAuthor);
        servingsText = findViewById(R.id.servingsText);
        recipeInstructions = findViewById(R.id.recipeInstructions);
        ingredientsList = findViewById(R.id.ingredientList);
        tagsList = findViewById(R.id.tagsList);
        servingsDropdown = findViewById(R.id.servingsDropdown);

        displayRecipe(defaultServingSize);

        servingsDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                displayRecipe(position + 1);
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void displayRecipe(int servingSize) {
        AccessRecipes recipes = new AccessRecipes();
        AccessAuthors authors = new AccessAuthors();
        String recipeId = getIntent().getExtras().getString("recipeId");
        Recipe recipe = recipes.getRecipeById(recipeId);
        Author author = authors.getAuthorById(recipe.getAuthorId());
        recipe = Convert.multiplyServingSize(recipe, servingSize);

        recipeTitle.setText(recipe.getTitle());
        recipeAuthor.setText(getString(R.string.written_by, author.getName()));
        displayTags(recipe);
        servingsText.setText(getString(R.string.creates_servings, recipe.getServingSize()));
        recipeInstructions.setText(recipe.getContent());

        ingredientsList.removeAllViews();
        for (Ingredient i : recipe.getIngredientList().getIngredients()) {
            TextView text = new TextView(DisplayRecipeActivity.this);
            text.setText(getString(R.string.ingredient_format, new Fraction(i.getQuantity()).getMixedFraction(), i.getMeasurement(), i.getName()));
            ingredientsList.addView(text);
            text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        }
        ingredientsList.requestLayout();
    }

    public void displayTags(Recipe recipe) {
        if(!recipe.getTags().isEmpty()) {
            tagsList.removeAllViews();
            for (String tag : recipe.getTags()) {
                // Create a button programmatically to act as a tag
                Button btnTag = new Button(DisplayRecipeActivity.this);

                // Set the parameters for the button programmatically
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMarginEnd(6);
                btnTag.setLayoutParams(layoutParams);
                btnTag.setText(tag);
                btnTag.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
                btnTag.setTextColor(getResources().getColor(R.color.colorWhite));
                btnTag.getBackground().setColorFilter(btnTag.getContext().getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                btnTag.setClickable(false);
                btnTag.setPadding(1, 1, 1, 1);

                tagsList.addView(btnTag);
            }
            tagsList.requestLayout();
        }
        // else do nothing and retain the "no tags have been attached" message
    }
}
