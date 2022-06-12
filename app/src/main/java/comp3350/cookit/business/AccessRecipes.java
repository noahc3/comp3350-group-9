package comp3350.cookit.business;

import java.util.List;

import comp3350.cookit.application.Main;
import comp3350.cookit.application.Services;
import comp3350.cookit.objects.Recipe;
import comp3350.cookit.persistence.DataAccessStub;

public class AccessRecipes
{
    private DataAccessStub dataAccess;
    private List<Recipe> recipes;
    private Recipe recipe;
    private int currentRecipe;

    public AccessRecipes()
    {
        dataAccess = Services.getDataAccess(Main.dbName);
        recipes = null;
    }

    public String getRecipes(List<Recipe> recipes)
    {
        recipes.clear();
        return dataAccess.getAllRecipes(recipes);
    }

    public String insertRecipe(Recipe recipe)
    {
        return dataAccess.insertRecipe(recipe);
    }

    public String updateRecipe(Recipe recipe)
    {
        return dataAccess.updateRecipe(recipe);
    }

    public String deleteRecipe(Recipe recipe)
    {
        return dataAccess.deleteRecipe(recipe);
    }
}
