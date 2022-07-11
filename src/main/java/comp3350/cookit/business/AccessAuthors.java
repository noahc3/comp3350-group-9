package comp3350.cookit.business;

import java.util.List;

import comp3350.cookit.application.Main;
import comp3350.cookit.application.Services;
import comp3350.cookit.objects.Author;
import comp3350.cookit.persistence.DataAccessStub;

public class AccessAuthors {
    private final DataAccessStub dataAccess;

    public AccessAuthors() {
        dataAccess = Services.getDataAccess(Main.dbName);
    }

    public List<Author> getAuthors() {
        return dataAccess.getAllAuthors();
    }

    public Author getAuthorById(String id) {
        return dataAccess.getAuthorById(id);
    }

    public void insertAuthor(Author author) {
        dataAccess.insertAuthor(author);
    }

    public void updateAuthor(Author author) {
        dataAccess.updateAuthor(author);
    }

    public void deleteAuthor(Author author) {
        dataAccess.deleteAuthor(author);
    }
}
