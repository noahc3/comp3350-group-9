package comp3350.cookit.presentation;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import android.media.Image;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

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
    private static final int defaultServingSize = 1;

    TextView recipeTitle;
    TextView recipeAuthor;
    TextView servingsText;
    TextView recipeInstructions;
    LinearLayout ingredientsList;
    Spinner servingsDropdown;
    List<Uri> images;
    ImageSwitcher slider;
    int position=0;
    Recipe recipe;

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
        servingsDropdown = findViewById(R.id.servingsDropdown);
        displayRecipe(defaultServingSize);
        slider=findViewById(R.id.switcher);
        slider.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                return imageView;
            }
        });

        servingsDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                displayRecipe(position + 1);
            }
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        slider.setImageURI(recipe.getPhotos().get(0));
    }

    public void displayRecipe(int servingSize) {
        DataAccessStub dataAccessStub = Services.getDataAccess(Main.dbName);
        String recipeId = getIntent().getExtras().getString("recipeId");
        recipe = dataAccessStub.getRecipeById(recipeId);
        Author author = dataAccessStub.getAuthorById(recipe.getAuthorId());
        recipe = Convert.multiplyServingSize(recipe, servingSize);
        recipeTitle.setText(recipe.getTitle());
        recipeAuthor.setText(getString(R.string.written_by, author.getName()));
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

    public void prevPhoto(View view) throws FileNotFoundException {
        if (position < 0) {
            position = images.size() - 1;
        }
        else {
            position--;
        }
        slider.setImageURI(recipe.getPhotos().get(position));
    }

    public void nextPhoto(View view) {
        if (position >= images.size()) {
            position = 0;
        }
        else{
            position++;
        }
        slider.setImageURI(recipe.getPhotos().get(position));
    }
}
