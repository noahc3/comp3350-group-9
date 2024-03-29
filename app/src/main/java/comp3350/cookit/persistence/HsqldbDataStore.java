package comp3350.cookit.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import comp3350.cookit.objects.Author;
import comp3350.cookit.objects.Ingredient;
import comp3350.cookit.objects.IngredientList;
import comp3350.cookit.objects.Recipe;
import comp3350.cookit.objects.Review;

public class HsqldbDataStore implements IDataStore {
    private Connection db;

    public HsqldbDataStore() {

    }

    @Override
    public void open(String dbPath) {
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            String url = "jdbc:hsqldb:file:" + dbPath; // stored on disk mode
            db = DriverManager.getConnection(url, "SA", "");
        } catch (Exception e) {
            processSQLError(e);
        }

        System.out.println("Opened HSQLDB database " + dbPath);
    }

    @Override
    public void close() {
        try {
            Statement st = db.createStatement();
            st.executeQuery("shutdown compact");
            db.close();
        } catch (Exception e) {
            processSQLError(e);
        }
        System.out.println("Closed HSQLDB database");
    }

    @Override
    public List<Recipe> getAllRecipes() {
        List<Recipe> result = new ArrayList<>();

        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM RECIPES ORDER BY TIMESTAMP");

            while (rs.next()) {
                result.add(parseRecipeFromResult(rs));
            }

            return result;
        } catch (Exception e) {
            processSQLError(e);
        }

        return null;
    }

    @Override
    public List<Recipe> getRecipesWithTag(String tag) {
        List<Recipe> recipes = getAllRecipes();
        List<Recipe> taggedRecipes = new ArrayList<>();

        if (recipes != null) {
            for (Recipe r : recipes) {
                if (r.getTags().contains(tag)) {
                    taggedRecipes.add(r);
                }
            }
        }

        return taggedRecipes;
    }

    @Override
    public Recipe getRecipeById(String id) {
        Recipe result = null;

        try {
            PreparedStatement st = db.prepareStatement("SELECT * FROM RECIPES WHERE ID = ?");
            st.setString(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                result = parseRecipeFromResult(rs);
            }
        } catch (Exception e) {
            processSQLError(e);
        }

        return result;
    }

    @Override
    public void insertRecipe(Recipe recipe) {
        try {
            PreparedStatement st = db.prepareStatement("INSERT INTO RECIPES VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, recipe.getId());
            st.setString(2, recipe.getTitle());
            st.setString(3, recipe.getAuthorId());
            st.setString(4, recipe.getContent());
            st.setObject(5, recipe.getIngredientList().getIngredients().toArray());
            st.setInt(6, recipe.getServingSize());
            st.setString(7, String.join(",", recipe.getTags()));
            st.setInt(8, recipe.getPrepTime());
            st.setInt(9, recipe.getCookTime());
            st.setString(10, recipe.getDifficulty());
            st.setObject(11, recipe.getImages().toArray());
            st.setLong(12, recipe.getTimestamp());

            int updateCount = st.executeUpdate();
            checkWarning(st, updateCount);

            db.commit();
        } catch (Exception e) {
            processSQLError(e);
        }
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        try {
            PreparedStatement st = db.prepareStatement("UPDATE RECIPES SET TITLE = ?, AUTHORID = ?, CONTENT = ?, INGREDIENTS = ?, SERVINGSIZE = ?, TAGS = ?, PREPTIME = ?, COOKTIME = ?, DIFFICULTY = ?, IMAGES = ?, TIMESTAMP = ? WHERE ID = ?");
            st.setString(1, recipe.getTitle());
            st.setString(2, recipe.getAuthorId());
            st.setString(3, recipe.getContent());
            st.setObject(4, recipe.getIngredientList().getIngredients().toArray());
            st.setInt(5, recipe.getServingSize());
            st.setString(6, String.join(",", recipe.getTags()));
            st.setInt(7, recipe.getPrepTime());
            st.setInt(8, recipe.getCookTime());
            st.setString(9, recipe.getDifficulty());
            st.setObject(10, recipe.getImages().toArray());
            st.setLong(11, recipe.getTimestamp());
            st.setString(12, recipe.getId());

            int updateCount = st.executeUpdate();
            checkWarning(st, updateCount);

            db.commit();
        } catch (Exception e) {
            processSQLError(e);
        }
    }

    @Override
    public void deleteRecipe(Recipe recipe) {
        try {
            PreparedStatement st = db.prepareStatement("DELETE FROM RECIPES WHERE ID = ?");
            st.setString(1, recipe.getId());

            int updateCount = st.executeUpdate();
            checkWarning(st, updateCount);

            db.commit();
        } catch (Exception e) {
            processSQLError(e);
        }
    }

    @Override
    public List<Recipe> getFavoriteRecipes() {
        List<Recipe> result = new ArrayList<>();

        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM FAVORITES LEFT JOIN RECIPES ON FAVORITES.ID = RECIPES.ID ORDER BY TIMESTAMP");

            while (rs.next()) {
                result.add(parseRecipeFromResult(rs));
            }

            return result;
        } catch (Exception e) {
            processSQLError(e);
        }

        return null;
    }

    @Override
    public void insertFavoriteRecipe(String recipeId) {
        try {
            PreparedStatement st = db.prepareStatement("INSERT INTO FAVORITES VALUES(?)");
            st.setString(1, recipeId);

            int updateCount = st.executeUpdate();
            checkWarning(st, updateCount);

            db.commit();
        } catch (Exception e) {
            processSQLError(e);
        }
    }

    @Override
    public void deleteFavoriteRecipe(String recipeId) {
        try {
            PreparedStatement st = db.prepareStatement("DELETE FROM FAVORITES WHERE ID = ?");
            st.setString(1, recipeId);

            int updateCount = st.executeUpdate();
            checkWarning(st, updateCount);

            db.commit();
        } catch (Exception e) {
            processSQLError(e);
        }
    }

    @Override
    public boolean anyRecipeWithTag(String tag) {
        List<Recipe> recipes = getAllRecipes();

        if (recipes != null) {
            for (Recipe r : recipes) {
                if (r.getTags().contains(tag)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public List<Author> getAllAuthors() {
        List<Author> result = new ArrayList<>();

        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM AUTHORS ORDER BY TIMESTAMP");

            while (rs.next()) {
                result.add(parseAuthorFromResult(rs));
            }

            return result;
        } catch (Exception e) {
            processSQLError(e);
        }

        return null;
    }

    @Override
    public Author getAuthorById(String id) {
        Author result = null;

        try {
            PreparedStatement st = db.prepareStatement("SELECT * FROM AUTHORS WHERE ID = ?");
            st.setString(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                result = parseAuthorFromResult(rs);
            }
        } catch (Exception e) {
            processSQLError(e);
        }

        return result;
    }

    @Override
    public void insertAuthor(Author author) {
        try {
            PreparedStatement st = db.prepareStatement("INSERT INTO AUTHORS VALUES(?, ?, ?, ?)");
            st.setString(1, author.getId());
            st.setString(2, author.getName());
            st.setString(3, author.getBio());
            st.setLong(4, author.getTimestamp());

            int updateCount = st.executeUpdate();
            checkWarning(st, updateCount);

            db.commit();
        } catch (Exception e) {
            processSQLError(e);
        }
    }

    @Override
    public void updateAuthor(Author author) {
        try {
            PreparedStatement st = db.prepareStatement("UPDATE AUTHORS SET NAME = ?, BIO = ?, TIMESTAMP = ? WHERE ID = ?");
            st.setString(1, author.getName());
            st.setString(2, author.getBio());
            st.setLong(3, author.getTimestamp());
            st.setString(4, author.getId());

            int updateCount = st.executeUpdate();
            checkWarning(st, updateCount);

            db.commit();
        } catch (Exception e) {
            processSQLError(e);
        }
    }

    @Override
    public void deleteAuthor(Author author) {
        try {
            PreparedStatement st = db.prepareStatement("DELETE FROM AUTHORS WHERE ID = ?");
            st.setString(1, author.getId());

            int updateCount = st.executeUpdate();
            checkWarning(st, updateCount);

            db.commit();
        } catch (Exception e) {
            processSQLError(e);
        }
    }

    @Override
    public List<Review> getAllReviews() {
        List<Review> result = new ArrayList<>();

        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM REVIEWS ORDER BY TIMESTAMP DESC");

            while (rs.next()) {
                result.add(parseReviewFromResult(rs));
            }

            return result;
        } catch (Exception e) {
            processSQLError(e);
        }

        return null;
    }

    @Override
    public List<Review> getReviewsForRecipe(String recipeId) {
        List<Review> result = new ArrayList<>();

        try {
            PreparedStatement st = db.prepareStatement("SELECT * FROM REVIEWS WHERE RECIPEID = ? ORDER BY TIMESTAMP DESC");
            st.setString(1, recipeId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                result.add(parseReviewFromResult(rs));
            }

            return result;
        } catch (Exception e) {
            processSQLError(e);
        }

        return null;
    }

    @Override
    public Review getReviewById(String id) {
        Review result = null;

        try {
            PreparedStatement st = db.prepareStatement("SELECT * FROM REVIEWS WHERE ID = ?");
            st.setString(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                result = parseReviewFromResult(rs);
            }
        } catch (Exception e) {
            processSQLError(e);
        }

        return result;
    }

    @Override
    public void insertReview(Review review) {
        try {
            PreparedStatement st = db.prepareStatement("INSERT INTO REVIEWS VALUES(?, ?, ?, ?, ?, ?)");
            st.setString(1, review.getId());
            st.setString(2, review.getRecipeId());
            st.setString(3, review.getAuthor());
            st.setString(4, review.getContent());
            st.setInt(5, review.getRating());
            st.setLong(6, review.getTimestamp());

            int updateCount = st.executeUpdate();
            checkWarning(st, updateCount);

            db.commit();
        } catch (Exception e) {
            processSQLError(e);
        }
    }

    @Override
    public void updateReview(Review review) {
        try {
            PreparedStatement st = db.prepareStatement("UPDATE REVIEWS SET RECIPEID = ?, AUTHOR = ?, CONTENT = ?, RATING = ?, TIMESTAMP = ? WHERE ID = ?");
            st.setString(1, review.getRecipeId());
            st.setString(2, review.getAuthor());
            st.setString(3, review.getContent());
            st.setInt(4, review.getRating());
            st.setLong(5, review.getTimestamp());
            st.setString(6, review.getId());

            int updateCount = st.executeUpdate();
            checkWarning(st, updateCount);

            db.commit();
        } catch (Exception e) {
            processSQLError(e);
        }
    }

    @Override
    public void deleteReview(Review review) {
        try {
            PreparedStatement st = db.prepareStatement("DELETE FROM REVIEWS WHERE ID = ?");
            st.setString(1, review.getId());

            int updateCount = st.executeUpdate();
            checkWarning(st, updateCount);

            db.commit();
        } catch (Exception e) {
            processSQLError(e);
        }
    }

    private Recipe parseRecipeFromResult(ResultSet rs) throws SQLException {
        String id = rs.getString("ID");
        String title = rs.getString("TITLE");
        String authorId = rs.getString("AUTHORID");
        String content = rs.getString("CONTENT");
        int servingSize = rs.getInt("SERVINGSIZE");
        int prepTime = rs.getInt("PREPTIME");
        int cookTime = rs.getInt("COOKTIME");
        String difficulty = rs.getString("DIFFICULTY");
        long timestamp = rs.getLong("TIMESTAMP");

        List<String> tags;
        String tagRaw = rs.getString("TAGS");
        if (tagRaw.contains(",")) {
            tags = new ArrayList<>(Arrays.asList(tagRaw.split(",")));
        } else if (tagRaw.length() > 0) {
            tags = new ArrayList<>(Collections.singleton(tagRaw));
        } else {
            tags = new ArrayList<>();
        }

        Object[] objArr;

        List<Ingredient> ingredients = new ArrayList<>();
        objArr = (Object[]) rs.getObject("INGREDIENTS");
        Ingredient[] ingredientsArr = Arrays.copyOf(objArr, objArr.length, Ingredient[].class);
        Collections.addAll(ingredients, ingredientsArr);

        List<String> images = new ArrayList<>();
        objArr = (Object[]) rs.getObject("IMAGES");
        String[] imagesArr = Arrays.copyOf(objArr, objArr.length, String[].class);
        Collections.addAll(images, imagesArr);

        return new Recipe(id, title, authorId, content, new IngredientList(ingredients), servingSize, tags, prepTime, cookTime, difficulty, images, timestamp);
    }

    private Author parseAuthorFromResult(ResultSet rs) throws SQLException {
        String id = rs.getString("ID");
        String name = rs.getString("NAME");
        String bio = rs.getString("BIO");

        return new Author(id, name, bio);
    }

    private Review parseReviewFromResult(ResultSet rs) throws SQLException {
        String id = rs.getString("ID");
        String recipeId = rs.getString("RECIPEID");
        String author = rs.getString("AUTHOR");
        String content = rs.getString("CONTENT");
        int rating = rs.getInt("RATING");
        long timestamp = rs.getLong("TIMESTAMP");

        return new Review(id, recipeId, author, content, rating, timestamp);
    }

    public String checkWarning(Statement st, int updateCount) {
        String result;

        result = null;
        try {
            SQLWarning warning = st.getWarnings();
            if (warning != null) {
                result = warning.getMessage();
            }
        } catch (Exception e) {
            result = processSQLError(e);
        }
        if (updateCount != 1) {
            result = "Tuple not inserted correctly.";
        }
        return result;
    }

    public String processSQLError(Exception e) {
        String result = "*** SQL Error: " + e.getMessage();

        e.printStackTrace();

        return result;
    }
}
