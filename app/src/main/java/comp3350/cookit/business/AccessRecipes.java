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

    public void insertFavoriteRecipe(Recipe r) {
        dataStore.insertFavoriteRecipe(r.getId());
    }

    public void deleteFavoriteRecipe(Recipe r) {
        dataStore.deleteFavoriteRecipe(r.getId());
    }

    public boolean isRecipeFavorited(Recipe r) {
        return getFavoriteRecipes().contains(r);
    }

    public List<Recipe> getRecipesWithTag(String id) {
        return dataStore.getRecipesWithTag(id);
    }

    public List<Recipe> getFavoriteRecipes() {
        return dataStore.getFavoriteRecipes();
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
