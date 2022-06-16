package comp3350.cookit.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Recipe {
    private final String id;
    private final String title;
    private final String authorId;
    private final String content;
    private final IngredientList ingredients;
    private final int servingSize;
    private final List<String> tags;

    public Recipe(String id, String title, String authorId, String content, IngredientList ingredients, int servingSize, List<String> tags) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.content = content;
        this.ingredients = ingredients;
        this.servingSize = servingSize;
        this.tags = tags;
    }

    public Recipe(String id, String title, String authorId, String content, IngredientList ingredients, int servingSize) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.content = content;
        this.ingredients = ingredients;
        this.servingSize = servingSize;
        this.tags = new ArrayList<String>();
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
        return tags;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Recipe recipe = (Recipe) other;
        return Objects.equals(id, recipe.id);
    }
}
