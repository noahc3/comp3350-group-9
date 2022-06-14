package comp3350.cookit.presentation;

import comp3350.cookit.R;
import comp3350.cookit.application.Main;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Main.startUp();

        setContentView(R.layout.activity_home);
    }


    public void imageOnClick(View v) {
        Intent recipeIntent = new Intent(HomeActivity.this, RecipesDetail.class);
        HomeActivity.this.startActivity(recipeIntent);
    }

    public void newRecipesOnClick(View v) {
        Intent newRecipesIntent = new Intent(this, NewRecipeActivity.class);
        startActivity(newRecipesIntent);
    }
}
