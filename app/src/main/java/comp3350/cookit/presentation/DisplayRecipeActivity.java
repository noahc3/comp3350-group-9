package comp3350.cookit.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import comp3350.cookit.R;
import comp3350.cookit.application.Main;
import comp3350.cookit.business.AccessAuthors;
import comp3350.cookit.business.AccessRecipes;
import comp3350.cookit.business.ServingSizeUtilities;
import comp3350.cookit.objects.Author;
import comp3350.cookit.objects.Fraction;
import comp3350.cookit.objects.Ingredient;
import comp3350.cookit.objects.Recipe;

public class DisplayRecipeActivity extends Activity {
    private static final int defaultServingSize = 1;

    private TextView recipeTitle;
    private TextView recipeAuthor;
    private TextView difficultyText;
    private TextView prepTimeText;
    private TextView cookTimeText;
    private TextView servingsText;
    private TextView recipeInstructions;
    private LinearLayout ingredientsList;
    private LinearLayout tagsList;
    private Spinner servingsDropdown;
    private LinearLayout imageViewLayout;
    private LinearLayout imageButtonsLayout;
    private ImageView imageView;

    private int selectedImage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Main.startUp();
        setContentView(R.layout.activity_display_recipe);

        recipeTitle = findViewById(R.id.recipeTitle);
        recipeAuthor = findViewById(R.id.recipeAuthor);
        difficultyText = findViewById(R.id.difficulty_display);
        prepTimeText = findViewById(R.id.prep_time_display);
        cookTimeText = findViewById(R.id.cook_time_display);
        servingsText = findViewById(R.id.servingsText);
        recipeInstructions = findViewById(R.id.recipeInstructions);
        ingredientsList = findViewById(R.id.ingredientList);
        tagsList = findViewById(R.id.tagsList);
        servingsDropdown = findViewById(R.id.servingsDropdown);
        imageViewLayout = findViewById(R.id.recipeImageLayout);
        imageButtonsLayout = findViewById(R.id.recipeImageButtonsLayout);
        imageView = findViewById(R.id.recipeImageView);

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
        recipe = ServingSizeUtilities.multiplyServingSize(recipe, servingSize);

        recipeTitle.setText(recipe.getTitle());
        recipeAuthor.setText(getString(R.string.written_by, author.getName()));
        difficultyText.setText(getString(R.string.difficulty_display, recipe.getDifficulty()));
        prepTimeText.setText(getString(R.string.prep_time_display, recipe.getPrepTime()));
        cookTimeText.setText(getString(R.string.cook_time_display, recipe.getCookTime()));
        displayTags(recipe);
        servingsText.setText(getString(R.string.creates_servings, recipe.getServingSize()));
        recipeInstructions.setText(recipe.getContent());

        if (recipe.getImages().size() == 0) {
            imageViewLayout.removeAllViews();
        } else {
            if (recipe.getImages().size() == 1) {
                imageViewLayout.removeView(imageButtonsLayout);
            }

            displaySelectedImage(recipe);
        }

        ingredientsList.removeAllViews();
        for (Ingredient i : recipe.getIngredientList().getIngredients()) {
            TextView text = new TextView(DisplayRecipeActivity.this);
            text.setText(getString(R.string.ingredient_format, new Fraction(i.getQuantity()).getMixedFraction(), i.getMeasurement(), i.getName()));
            ingredientsList.addView(text);
            text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        }
        ingredientsList.requestLayout();


    }

    public void prevPhoto(View view) {
        AccessRecipes recipes = new AccessRecipes();
        String recipeId = getIntent().getExtras().getString("recipeId");
        Recipe recipe = recipes.getRecipeById(recipeId);

        selectedImage = (selectedImage + recipe.getImages().size() - 1) % recipe.getImages().size();
        imageView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left));
        displaySelectedImage(recipe);
    }

    public void nextPhoto(View view) {
        AccessRecipes recipes = new AccessRecipes();
        String recipeId = getIntent().getExtras().getString("recipeId");
        Recipe recipe = recipes.getRecipeById(recipeId);

        selectedImage = (selectedImage + 1) % recipe.getImages().size();
        imageView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right));
        displaySelectedImage(recipe);
    }

    public void navigateToTaggedRecipeList(View view) {
        Button btn = (Button) view;
        String tag = btn.getText().toString();

        Intent taggedRecipeListIntent = new Intent(this, TaggedListActivity.class);
        taggedRecipeListIntent.putExtra("recipeTag", tag);
        startActivity(taggedRecipeListIntent);
    }

    private void displaySelectedImage(Recipe recipe) {
        //I would prefer to query the images through the persistence package, but we need the app context
        //to resolve the file paths, similar to the SQL script init. Android docs strongly discourage
        //passing contexts outside of activity classes, so we have to load the image files here.
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(Main.getImgAssetsPath(), Context.MODE_PRIVATE);
        String imageName = recipe.getImages().get(selectedImage);
        try {
            InputStream in = getContentResolver().openInputStream(Uri.fromFile(new File(dataDirectory.getPath(), imageName)));
            imageView.setImageBitmap(BitmapFactory.decodeStream(in));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayTags(Recipe recipe) {
        if (!recipe.getTags().isEmpty()) {
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
                btnTag.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        navigateToTaggedRecipeList(v);
                    }
                });
                btnTag.setPadding(1, 1, 1, 1);

                tagsList.addView(btnTag);
            }
            tagsList.requestLayout();
        }
        // else do nothing and retain the "no tags have been attached" message
    }
}
