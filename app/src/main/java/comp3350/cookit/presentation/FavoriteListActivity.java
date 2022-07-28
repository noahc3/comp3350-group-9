package comp3350.cookit.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import comp3350.cookit.R;
import comp3350.cookit.business.AccessRecipes;
import comp3350.cookit.objects.Recipe;
import comp3350.cookit.presentation.components.RecipeBoxView;

public class FavoriteListActivity extends Activity {

    private TextView header;
    private LinearLayout favoriteRecipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_favorite_list);

        header = findViewById(R.id.favoriteListHeader);
        favoriteRecipeList = findViewById(R.id.favoriteRecipeList);

        showRecipeButtons();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showRecipeButtons();
    }

    public void showRecipeButtons() {
        List<Recipe> recipes = new AccessRecipes().getFavoriteRecipes();

        favoriteRecipeList.removeAllViews();

        for (Recipe recipe : recipes) {
            RecipeBoxView view = new RecipeBoxView(this, recipe);
            view.setTag(recipe.getId());
            view.setOnClickListener(this::displayRecipe);
            favoriteRecipeList.addView(view);
        }

        favoriteRecipeList.requestLayout();
    }

    public void displayRecipe(View v) {
        Intent displayRecipeIntent = new Intent(this, DisplayRecipeActivity.class);
        displayRecipeIntent.putExtra("recipeId", (String) v.getTag());
        startActivity(displayRecipeIntent);
    }
}
