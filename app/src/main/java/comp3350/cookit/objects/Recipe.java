package comp3350.cookit.objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Recipe {
    private final String id;
    private final String title;
    private final String authorId;
    private final String content;
    private final IngredientList ingredients;
    private final int servingSize;
    private final List<String> tags;
    private final int prepTime;
    private final int cookTime;
    private final String difficulty;
    private final List<String> images;
    private final long timestamp;

    public Recipe(String id, String title, String authorId, String content, IngredientList ingredients, int servingSize, List<String> tags, int prepTime, int cookTime, String difficulty, List<String> images) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.content = content;
        this.ingredients = ingredients;
        this.servingSize = servingSize;
        this.tags = tags;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.difficulty = difficulty;
        this.images = images;
        this.timestamp = new Date().getTime();
    }

    public Recipe(String id, String title, String authorId, String content, IngredientList ingredients, int servingSize, List<String> tags, int prepTime, int cookTime, String difficulty, List<String> images, long timestamp) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.content = content;
        this.ingredients = ingredients;
        this.servingSize = servingSize;
        this.tags = tags;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.difficulty = difficulty;
        this.images = images;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getContent() {
        return content;
    }

    public IngredientList getIngredientList() {
        return ingredients;
    }

    public int getServingSize() {
        return servingSize;
    }

    public List<String> getTags() {
        return tags == null ? new ArrayList<String>() : new ArrayList<>(tags);
    }

    public int getPrepTime() {
        return prepTime;
    }

    public int getCookTime() {
        return cookTime;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public List<String> getImages() {
        return images == null ? new ArrayList<String>() : new ArrayList<>(images);
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Recipe recipe = (Recipe) other;
        return Objects.equals(id, recipe.id);
    }
}
