package comp3350.cookit.objects;

import java.util.List;

public class Recipe {
    private String id;
    private String title;
    private String authorId;
    private String content;
    private IngredientList ingredients;
    private float servingSize;
    private List<String> tags;

    public Recipe(String id, String title, String authorId, String content, IngredientList ingredients, float servingSize, List<String> tags) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.content = content;
        this.ingredients = ingredients;
        this.servingSize = servingSize;
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

    public IngredientList getIngredientList() {
        return ingredients;
    }

    public float getServingSize() {
        return servingSize;
    }

    public List<String> getTags() {
        return tags;
    }
}