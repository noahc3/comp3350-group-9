package comp3350.cookit.presentation;

import comp3350.cookit.R;
import comp3350.cookit.application.Main;
import comp3350.cookit.application.Services;
import comp3350.cookit.objects.Author;
import comp3350.cookit.objects.Fraction;
import comp3350.cookit.objects.Ingredient;
import comp3350.cookit.objects.Recipe;
import comp3350.cookit.persistence.DataAccessStub;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class DisplayRecipeActivity extends Activity {
    private Spinner spinner;
    private TextView d_result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Main.startUp();

        setContentView(R.layout.activity_display_recipe);

        DataAccessStub dataAccessStub = Services.getDataAccess(Main.dbName);
        String recipeId = getIntent().getExtras().getString("recipeId");
        LinearLayout ingredientLayout = findViewById(R.id.ingredientList);
        Recipe recipe = dataAccessStub.getRecipeById(recipeId);
        Author author = dataAccessStub.getAuthorById(recipe.getAuthorId());
        ((TextView)findViewById(R.id.recipeTitle)).setText(recipe.getTitle());
        ((TextView)findViewById(R.id.recipeAuthor)).setText(getString(R.string.written_by, author.getName()));
        ((TextView)findViewById(R.id.recipeInstructions)).setText(recipe.getContent());

        for (Ingredient i : recipe.getIngredientList().getIngredients()) {
            TextView text = new TextView(this);
            text.setText(getString(R.string.ingredient_format, new Fraction(i.getQuantity()).getMixedFraction(), i.getMeasurement(), i.getName()));
            ingredientLayout.addView(text);
        }



        spinner = (Spinner)findViewById(R.id.spinner);
        d_result = (TextView)findViewById(R.id.d_result);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

//            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                d_result.setText(parent.getItemAtPosition(position).toString());
            }

//            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }



}
