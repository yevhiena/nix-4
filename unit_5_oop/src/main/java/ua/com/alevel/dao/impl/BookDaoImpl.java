package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.BookDao;
import ua.com.alevel.db.InMemoryDB;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.List;

public class BookDaoImpl implements BookDao {
    @Override
    public void create(Book data) {
        InMemoryDB.getInstance().createBook(data);
    }

    @Override
    public Book read(int id) {
        return InMemoryDB.getInstance().readBook(id);
    }

    @Override
    public List<Book> read() {
        return InMemoryDB.getInstance().readAllBooks();
    }

    @Override
    public void update(Book data) {
        InMemoryDB.getInstance().updateBook(data);
    }

    @Override
    public void delete(int id) {
        InMemoryDB.getInstance().deleteBook(id);

    }

    @Override
    public boolean exist(int id) {
        return InMemoryDB.getInstance().bookExist(id);
    }

    @Override
    public List<Book> readBooksByAuthor(int id) {
        return InMemoryDB.getInstance().readBooksByAuthor(id);
    }

    @Override
    public Book findBookByName(String title) {
        return InMemoryDB.getInstance().findBookByName(title);
    }
}
