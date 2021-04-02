package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.db.InMemoryDB;
import ua.com.alevel.entity.Author;

import java.util.List;

public class AuthorDaoImpl implements AuthorDao {
    @Override
    public void create(Author data) {
        InMemoryDB.getInstance().createAuthor(data);
    }

    @Override
    public Author read(int id) {
       return InMemoryDB.getInstance().readAuthor(id);
    }

    @Override
    public List<Author> read() {
        return InMemoryDB.getInstance().readAllAuthors();
    }

    @Override
    public void update(Author data) {
        InMemoryDB.getInstance().updateAuthor(data);

    }

    @Override
    public void delete(int id) {
        InMemoryDB.getInstance().deleteAuthor(id);

    }

    @Override
    public boolean exist(int id) {
        return InMemoryDB.getInstance().authorExist(id);
    }

    @Override
    public List<Author> readAuthorsByBook(int id) {
        return InMemoryDB.getInstance().readAuthorsByBook(id);
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return InMemoryDB.getInstance().findAuthorByName(firstName, lastName);
    }
}
