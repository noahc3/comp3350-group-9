package comp3350.cookit.objects;

public class Review {
    private String author;
    private String summary;
    private String content;
    private int rating;

    public Review(String author, String summary, String content, int rating) {
        this.author = author;
        this.summary = summary;
        this.content = content;
        this.rating = rating;
    }

    public String getAuthor() {
        return author;
    }

    public String getSummary() {
        return summary;
    }

    public String getContent() {
        return content;
    }

    public int getRating() {
        return rating;
    }
}
