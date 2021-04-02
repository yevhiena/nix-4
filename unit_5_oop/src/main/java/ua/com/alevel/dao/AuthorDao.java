package ua.com.alevel.dao;

import ua.com.alevel.entity.Author;

import java.util.List;

public interface AuthorDao extends AbstractDao<Author> {
    List<Author> readAuthorsByBook(int id);
    Author findAuthorByName(String firstName, String lastName);

}
