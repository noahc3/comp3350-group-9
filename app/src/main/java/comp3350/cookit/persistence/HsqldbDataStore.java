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
    private final String dbName;

    public HsqldbDataStore(String dbName) {
        this.dbName = dbName;
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
        System.out.println("Closed HSQLDB database " + dbName);
    }

    @Override
    public List<Recipe> getAllRecipes() {
        List<Recipe> result = new ArrayList<>();

        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM RECIPES");

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
            PreparedStatement st = db.prepareStatement("INSERT INTO RECIPES VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, recipe.getId());
            st.setString(2, recipe.getTitle());
            st.setString(3, recipe.getAuthorId());
            st.setString(4, recipe.getContent());
            st.setObject(5, recipe.getIngredientList().getIngredients().toArray());
            st.setInt(6, recipe.getServingSize());
            st.setObject(7, recipe.getTags().toArray());
            st.setInt(8, recipe.getPrepTime());
            st.setInt(9, recipe.getCookTime());
            st.setString(10, recipe.getDifficulty());

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
            PreparedStatement st = db.prepareStatement("UPDATE RECIPES SET TITLE = ?, AUTHORID = ?, CONTENT = ?, INGREDIENTS = ?, SERVINGSIZE = ?, TAGS = ?, PREPTIME = ?, COOKTIME = ?, DIFFICULTY = ? WHERE ID = ?");
            st.setString(1, recipe.getTitle());
            st.setString(2, recipe.getAuthorId());
            st.setString(3, recipe.getContent());
            st.setObject(4, recipe.getIngredientList().getIngredients().toArray());
            st.setInt(5, recipe.getServingSize());
            st.setObject(6, recipe.getTags().toArray());
            st.setInt(7, recipe.getPrepTime());
            st.setInt(8, recipe.getCookTime());
            st.setString(9, recipe.getDifficulty());
            st.setString(10, recipe.getId());

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
    public List<Author> getAllAuthors() {
        List<Author> result = new ArrayList<>();

        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM AUTHORS");

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
            PreparedStatement st = db.prepareStatement("INSERT INTO AUTHORS VALUES(?, ?, ?)");
            st.setString(1, author.getId());
            st.setString(2, author.getName());
            st.setString(3, author.getBio());

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
            PreparedStatement st = db.prepareStatement("UPDATE AUTHORS SET NAME = ?, BIO = ? WHERE ID = ?");
            st.setString(1, author.getName());
            st.setString(2, author.getBio());
            st.setString(3, author.getId());

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
            ResultSet rs = st.executeQuery("SELECT * FROM REVIEWS");

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
            PreparedStatement st = db.prepareStatement("INSERT INTO REVIEWS VALUES(?, ?, ?, ?, ?)");
            st.setString(1, review.getId());
            st.setString(2, review.getRecipeId());
            st.setString(3, review.getAuthor());
            st.setString(4, review.getContent());
            st.setInt(5, review.getRating());

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
            PreparedStatement st = db.prepareStatement("UPDATE REVIEWS SET RECIPEID = ?, AUTHOR = ?, CONTENT = ?, RATING = ? WHERE ID = ?");
            st.setString(1, review.getRecipeId());
            st.setString(2, review.getAuthor());
            st.setString(3, review.getContent());
            st.setInt(4, review.getRating());
            st.setString(5, review.getId());

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

        Object[] objArr;

        List<Ingredient> ingredients = new ArrayList<>();
        objArr = (Object[]) rs.getObject("INGREDIENTS");
        Ingredient[] ingredientsArr = Arrays.copyOf(objArr, objArr.length, Ingredient[].class);
        Collections.addAll(ingredients, ingredientsArr);

        List<String> tags = new ArrayList<>();
        objArr = (Object[]) rs.getObject("TAGS");
        String[] tagsArr = Arrays.copyOf(objArr, objArr.length, String[].class);
        Collections.addAll(tags, tagsArr);

        return new Recipe(id, title, authorId, content, new IngredientList(ingredients), servingSize, tags, prepTime, cookTime, difficulty);
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

        return new Review(id, recipeId, author, content, rating);
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
