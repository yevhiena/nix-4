package ua.com.alevel.service;

import ua.com.alevel.entity.Author;
import ua.com.alevel.service.AbstractService;

import java.util.List;

public interface AuthorService extends AbstractService<Author> {
    List<Author> readAuthorsByBook(int id);
    Author findAuthorByName(String firstName, String lastName);

}
