package comp3350.cookit.objects;

import java.util.Objects;

public class Author {
    private final String id;
    private final String name;
    private final String bio;

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

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Author author = (Author) other;
        return Objects.equals(id, author.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
