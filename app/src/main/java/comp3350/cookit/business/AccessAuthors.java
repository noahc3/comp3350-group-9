package comp3350.cookit.business;

import java.util.List;

import comp3350.cookit.application.Services;
import comp3350.cookit.objects.Author;
import comp3350.cookit.persistence.IDataStore;

public class AccessAuthors {
    private final IDataStore dataStore;

    public AccessAuthors() {
        dataStore = Services.getDataStore();
    }

    public List<Author> getAuthors() {
        return dataStore.getAllAuthors();
    }

    public Author getAuthorById(String id) {
        return dataStore.getAuthorById(id);
    }

    public void insertAuthor(Author author) {
        dataStore.insertAuthor(author);
    }

    public void updateAuthor(Author author) {
        dataStore.updateAuthor(author);
    }

    public void deleteAuthor(Author author) {
        dataStore.deleteAuthor(author);
    }
}
