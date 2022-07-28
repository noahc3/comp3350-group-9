package comp3350.cookit.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import comp3350.cookit.R;
import comp3350.cookit.application.Main;
import comp3350.cookit.business.AccessRecipes;
import comp3350.cookit.objects.Recipe;
import comp3350.cookit.presentation.components.RecipeBoxView;

public class HomeListActivity extends Activity {
    private LinearLayout recipeList;
    private LinearLayout tagsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDatabase();
        copyAssetFolderToDevice(Main.getImgAssetsPath());

        Main.startUp();

        setContentView(R.layout.activity_home_list);

        recipeList = findViewById(R.id.recipeList);
        tagsList = findViewById(R.id.tagsSuggestions);

        showRecipeButtons();
        
        showTagSuggestions();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showRecipeButtons();
    }

    public void showRecipeButtons() {
        List<Recipe> recipes = new AccessRecipes().getRecipes();

        recipeList.removeAllViews();

        for (Recipe recipe : recipes) {
            RecipeBoxView view = new RecipeBoxView(this, recipe);
            view.setTag(recipe.getId());
            view.setOnClickListener(this::displayRecipe);
            recipeList.addView(view);
        }

        recipeList.requestLayout();
    }

    public void showTagSuggestions() {
        String[] tagSuggestions = getTagSuggestions();
        if (tagSuggestions.length != 0) {
            tagsList.removeAllViews();
            for (String tag : tagSuggestions) {
                // Create a button programmatically to act as a tag
                Button btnTag = new Button(HomeListActivity.this);

                // Set the parameters for the button programmatically
                btnTag.setText(tag);
                btnTag.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
                btnTag.setTextColor(getResources().getColor(R.color.colorWhite));
                btnTag.getBackground().setColorFilter(btnTag.getContext().getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                btnTag.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Button btn = (Button) v;

                        Intent taggedRecipeListIntent = new Intent(v.getContext(), TaggedListActivity.class);
                        taggedRecipeListIntent.putExtra("recipeTag", tag);
                        startActivity(taggedRecipeListIntent);
                    }
                });
                btnTag.setPadding(1, 1, 1, 1);

                tagsList.addView(btnTag);

                /**
                 * Re-adjust the width after the Button is added to the View
                 *
                 * The LayoutParams approach (below) does not seem to behave as how the
                 * documentation says it would; it does not apply any change at all.
                 * LinearLayout.LayoutParams layoutParams...
                 * ... btnTag.setLayoutParams(layoutParams);
                 *
                 * Instead, for longer strings, we change the width of button to a size relative
                 * to their length; Android Studio seems to resize it accordingly if it is close
                 * enough, thus the hard-coded scaling "22"
                 */
                if(tag.length() >= 8)
                    btnTag.getLayoutParams().width = tag.length() * 22;
            }
            tagsList.requestLayout();
        }
    }

    public String[] getTagSuggestions() {
        // Initialized as list for dynamic length, to be converted to array when finalized
        List<String> suggestions = new ArrayList<>();

        // We need an array of every tag array to provide random (but categorized) tag suggestions
        String[][] tagsArray = new String[7][];
        tagsArray[0] = getResources().getStringArray(R.array.tags_type);
        tagsArray[1] = getResources().getStringArray(R.array.tags_taste);
        tagsArray[2] = getResources().getStringArray(R.array.tags_course);
        tagsArray[3] = getResources().getStringArray(R.array.tags_diet);
        tagsArray[4] = getResources().getStringArray(R.array.tags_region);
        tagsArray[5] = getResources().getStringArray(R.array.tags_occasion);
        tagsArray[6] = getResources().getStringArray(R.array.tags_season);

        // Separate from the rest as we guarantee that a proper time of day tag is suggested
        String[] timeOfDay = getResources().getStringArray(R.array.tags_time_of_day);

        Random rng = new Random(); // for generating random indexes

        // Used to determine time of day to show correct tag (e.g. morning = "Breakfast" tag)
        int currHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        // Suggest time_of_day tag for the time of day
        if(currHour >= 6 && currHour <= 10)
            suggestions.add(timeOfDay[0]); // "Breakfast" Tag
        else if(currHour >= 12 && currHour <= 2)
            suggestions.add(timeOfDay[1]); // "Lunch" Tag
        else if(currHour >= 18 && currHour <= 22)
            suggestions.add(timeOfDay[2]); // "Dinner" Tag
        else // if odd hours
            suggestions.add(timeOfDay[3]); // "Snack" Tag

        // Always suggest "All Day" recipes
        suggestions.add(timeOfDay[4]); // "All Day" Tag

        // Add exactly one random tag from each tag category (apart from Time of Day)
        for(int i = 0; i < tagsArray.length; i++) {
            int randIndex = rng.nextInt(tagsArray[i].length);
            suggestions.add(tagsArray[i][randIndex]);
        }

        return (String[]) suggestions.toArray(new String[0]); // cast Object[] to String[]
    }

    public void seeAllOnClick(View v) {
        Intent newRecipesIntent = new Intent(this, FilterByTagActivity.class);
        startActivity(newRecipesIntent);
    }

    public void newRecipesOnClick(View v) {
        Intent newRecipesIntent = new Intent(this, NewRecipeActivity.class);
        startActivity(newRecipesIntent);
    }

    public void favoriteListOnClick(View v) {
        Intent favoriteList = new Intent(this, FavoriteListActivity.class);
        startActivity(favoriteList);
    }

    public void displayRecipe(View v) {
        Intent displayRecipeIntent = new Intent(this, DisplayRecipeActivity.class);
        displayRecipeIntent.putExtra("recipeId", (String) v.getTag());
        startActivity(displayRecipeIntent);
    }

    private void initDatabase() {
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(Main.getDbAssetsPath(), Context.MODE_PRIVATE);
        Main.setDBPath(dataDirectory.toString() + "/" + Main.getDbName());

        copyAssetFolderToDevice(Main.getDbAssetsPath());
    }

    private void copyAssetFolderToDevice(String assetsPath) {
        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(assetsPath, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {
            assetNames = assetManager.list(assetsPath);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = assetsPath + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];
            byte[] buffer = new byte[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStream in = assetManager.open(asset);
                FileOutputStream out = new FileOutputStream(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }
}
