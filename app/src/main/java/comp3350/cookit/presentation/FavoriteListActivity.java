package comp3350.cookit.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;
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
        favoriteRecipeList = findViewById(R.id.taggedRecipeList);

        showRecipeButtons();
    }

    public void showRecipeButtons() {
        List<Recipe> recipes = new AccessRecipes().getFavoriteRecipes();

//        favoriteRecipeList.removeAllViews();

        for (Recipe recipe : recipes) {
            Button button = new Button(this);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayRecipe(v);
                }
            });
            favoriteRecipeList.addView(button);
        }

        favoriteRecipeList.requestLayout();
    }

    public void displayRecipe(View v) {
        Intent displayRecipeIntent = new Intent(this, DisplayRecipeActivity.class);
        displayRecipeIntent.putExtra("recipeId", (String) v.getTag());
        startActivity(displayRecipeIntent);
    }
}
