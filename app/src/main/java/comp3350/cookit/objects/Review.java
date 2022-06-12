package comp3350.cookit.objects;

public class Review {
    private String id;
    private String recipeId;
    private String author;
    private String content;
    private int rating;

    public Review(String id, String recipeId, String author, String content, int rating) {
        this.id = id;
        this.recipeId = recipeId;
        this.author = author;
        this.content = content;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public int getRating() {
        return rating;
    }
}
