package comp3350.cookit.objects;

import java.util.List;
import java.util.UUID;

public class Recipe {
    private String id;
    private String title;
    private String authorId;
    private String content;
    private IngredientList ingredients;
    private float baseServingSize;
    private List<String> tags;

    public Recipe(String id, String title, String authorId, String content, IngredientList ingredients, float baseServingSize, List<String> tags) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.content = content;
        this.ingredients = ingredients;
        this.baseServingSize = baseServingSize;
        this.tags = tags;
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

    public IngredientList getIngredients() {
        return ingredients;
    }

    public float getBaseServingSize() {
        return baseServingSize;
    }

    public List<String> getTags() {
        return tags;
    }
}
