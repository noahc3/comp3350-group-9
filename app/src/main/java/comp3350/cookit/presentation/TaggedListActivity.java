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

        taggedRecipeList.removeAllViews();

        for (Recipe recipe : recipes) {
            Button button = new Button(this);
            button.setText(recipe.getTitle());
            button.setTag(recipe.getId());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayRecipe(v);
                }
            });
            taggedRecipeList.addView(button);
        }

        taggedRecipeList.requestLayout();
    }

    public void displayRecipe(View v) {
        Intent displayRecipeIntent = new Intent(this, DisplayRecipeActivity.class);
        displayRecipeIntent.putExtra("recipeId", (String) v.getTag());
        startActivity(displayRecipeIntent);
    }
}
