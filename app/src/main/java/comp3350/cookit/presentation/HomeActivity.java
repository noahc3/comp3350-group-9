package comp3350.cookit.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import comp3350.cookit.R;

public class HomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void newRecipesOnClick(View v) {
        Intent newRecipesIntent = new Intent(HomeActivity.this, NewRecipeActivity.class);
        startActivity(newRecipesIntent);
    }
}