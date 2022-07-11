package comp3350.cookit.application;

import java.util.ArrayList;
import java.util.Arrays;

import comp3350.cookit.business.AccessRecipes;
import comp3350.cookit.objects.Ingredient;
import comp3350.cookit.objects.IngredientList;
import comp3350.cookit.objects.Recipe;

public class Main {
    public static final String dbName = "main";
    private static final String imgAssetsPath = "img";
    private static final String dbAssetsPath = "db";
    private static String dbPath = "database/main";

    public static void main(String[] args) {
        startUp();
        shutDown();
        System.out.println("All done");
    }

    public static void startUp() {
        Services.createDataStore(dbName);
    }

    public static void shutDown() {
        Services.closeDataAccess();
    }

    public static String getDBPath() {
        if (dbPath == null)
            return dbName;
        else
            return dbPath;
    }

    public static String getDbAssetsPath() {
        return dbAssetsPath;
    }

    public static String getImgAssetsPath() {
        return imgAssetsPath;
    }

    public static void setDBPath(String path) {
        System.out.println("Setting DB path to: " + path);
        dbPath = path;
    }
}
