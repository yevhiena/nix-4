package ua.com.alevel.service;

import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AbstractService;

import java.util.List;

public interface BookService extends AbstractService<Book> {
    List<Book> readBooksByAuthor(int id);
    Book findBookByName(String title);

}
