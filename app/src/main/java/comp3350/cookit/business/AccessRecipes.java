package comp3350.cookit.business;

import java.util.List;

import comp3350.cookit.application.Main;
import comp3350.cookit.application.Services;
import comp3350.cookit.objects.Recipe;
import comp3350.cookit.persistence.DataAccessStub;

public class AccessRecipes {
    private DataAccessStub dataAccess;

    public AccessRecipes() {
        dataAccess = Services.getDataAccess(Main.dbName);
    }

    public List<Recipe> getRecipes() {
        return dataAccess.getAllRecipes();
    }

    public Recipe getRecipeById(String id) {
        return dataAccess.getRecipeById(id);
    }

    public void insertRecipe(Recipe recipe) {
        dataAccess.insertRecipe(recipe);
    }

    public void updateRecipe(Recipe recipe) {
        dataAccess.updateRecipe(recipe);
    }

    public void deleteRecipe(Recipe recipe) {
        dataAccess.deleteRecipe(recipe);
    }
}
