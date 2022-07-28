package comp3350.cookit.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import comp3350.cookit.R;
import comp3350.cookit.business.AccessRecipes;
import comp3350.cookit.objects.Recipe;
import comp3350.cookit.presentation.components.RecipeBoxView;

public class TaggedListActivity extends Activity {
    private TextView header;
    private LinearLayout taggedRecipeList;
    private String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tagged_list);

        tag = getIntent().getExtras().getString("recipeTag");
        header = findViewById(R.id.taggedListHeader);
        taggedRecipeList = findViewById(R.id.taggedRecipeList);

        header.setText(getString(R.string.title_activity_tagged_list, tag));
        showRecipeButtons();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showRecipeButtons();
    }

    public void showRecipeButtons() {
        List<Recipe> recipes = new AccessRecipes().getRecipesWithTag(tag);

        if(!recipes.isEmpty()) {
            taggedRecipeList.removeAllViews();

            for (Recipe recipe : recipes) {
                RecipeBoxView view = new RecipeBoxView(this, recipe);
                view.setTag(recipe.getId());
                view.setOnClickListener(this::displayRecipe);
                taggedRecipeList.addView(view);
            }

            taggedRecipeList.requestLayout();
        } // else retain no tags attached message
    }

    public void displayRecipe(View v) {
        Intent displayRecipeIntent = new Intent(this, DisplayRecipeActivity.class);
        displayRecipeIntent.putExtra("recipeId", (String) v.getTag());
        startActivity(displayRecipeIntent);
    }
}
