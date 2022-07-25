package comp3350.cookit.objects;

import java.util.Date;
import java.util.Objects;

public class Review {
    private final String id;
    private final String recipeId;
    private final String author;
    private final String content;
    private final int rating;
    private final long timestamp;

    public Review(String id, String recipeId, String author, String content, int rating) {
        this.id = id;
        this.recipeId = recipeId;
        this.author = author;
        this.content = content;
        this.rating = rating;
        this.timestamp = new Date().getTime();
    }

    public Review(String id, String recipeId, String author, String content, int rating, long timestamp) {
        this.id = id;
        this.recipeId = recipeId;
        this.author = author;
        this.content = content;
        this.rating = rating;
        this.timestamp = timestamp;
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

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Review review = (Review) other;
        return Objects.equals(id, review.id);
    }
}
