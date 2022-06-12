package comp3350.cookit.objects;

public class Author {
    private String id;
    private String name;
    private String bio;

    public Author(String id, String name, String bio) {
        this.id = id;
        this.name = name;
        this.bio = bio;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }
}
