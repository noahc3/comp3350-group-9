package comp3350.cookit.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

import comp3350.cookit.R;
import comp3350.cookit.application.Main;
import comp3350.cookit.application.Services;
import comp3350.cookit.objects.Recipe;

public class HomeListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Main.startUp();

        setContentView(R.layout.activity_home_list);

        LinearLayout ll = findViewById(R.id.recipeList);
        List<Recipe> recipes = Services.getDataAccess(Main.dbName).getAllRecipes();

        for (Recipe r : recipes) {
            Button button = new Button(this);
            button.setText(r.getTitle());
            button.setTag(r.getId());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayRecipe(v);
                }
            });
            ll.addView(button);
        }
    }

    public void newRecipesOnClick(View v) {
        Intent newRecipesIntent = new Intent(this, NewRecipeActivity.class);
        startActivity(newRecipesIntent);
    }

    public void displayRecipe(View v) {
        Intent displayRecipeIntent = new Intent(this, DisplayRecipeActivity.class);
        displayRecipeIntent.putExtra("recipeId", (String) v.getTag());
        startActivity(displayRecipeIntent);
    }
}
