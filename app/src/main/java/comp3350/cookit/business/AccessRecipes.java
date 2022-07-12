package comp3350.cookit.business;

import java.util.List;

import comp3350.cookit.application.Main;
import comp3350.cookit.application.Services;
import comp3350.cookit.objects.Recipe;
import comp3350.cookit.persistence.IDataStore;

public class AccessRecipes {
    private final IDataStore dataStore;

    public AccessRecipes() {
        dataStore = Services.getDataStore();
    }

    public List<Recipe> getRecipes() {
        return dataStore.getAllRecipes();
    }

    public List<Recipe> getRecipesWithTag(String tag) {
        return dataStore.getRecipesWithTag(tag);
    }

    public Recipe getRecipeById(String id) {
        return dataStore.getRecipeById(id);
    }

    public void insertRecipe(Recipe recipe) {
        dataStore.insertRecipe(recipe);
    }

    public void updateRecipe(Recipe recipe) {
        dataStore.updateRecipe(recipe);
    }

    public void deleteRecipe(Recipe recipe) {
        dataStore.deleteRecipe(recipe);
    }
}
