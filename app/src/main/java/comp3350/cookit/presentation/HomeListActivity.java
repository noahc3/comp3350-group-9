package comp3350.cookit.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import comp3350.cookit.R;
import comp3350.cookit.application.Main;
import comp3350.cookit.business.AccessRecipes;
import comp3350.cookit.objects.Recipe;
import comp3350.cookit.presentation.components.RecipeBoxView;

public class HomeListActivity extends Activity {
    private LinearLayout recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDatabase();
        copyAssetFolderToDevice(Main.getImgAssetsPath());

        Main.startUp();

        setContentView(R.layout.activity_home_list);

        recipeList = findViewById(R.id.recipeList);

        showRecipeButtons();
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
