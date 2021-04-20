package ua.com.alevel.service.impl;

import org.apache.log4j.Logger;
import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.dao.BookDao;
import ua.com.alevel.dao.impl.AuthorDaoImpl;
import ua.com.alevel.dao.impl.BookDaoImpl;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private final BookDao bookDao = new BookDaoImpl();
    private final AuthorDao authorDao = new AuthorDaoImpl();

    private final Logger logger = Logger.getLogger(BookServiceImpl.class);

    @Override
    public void create(Book data) throws RuntimeException {
        logger.info("Start creating book");
        Book book = bookDao.findBookByName(data.getTitle());

        if (book != null && book.getAuthorId().containsAll(data.getAuthorId())){
            logger.info("Book with the same title and authors already exist " + book);
            throw new RuntimeException("Book with the same title and authors already exist " + book);
        }
        bookDao.create(data);
        logger.info("Created book in db " + data);

    }

    @Override
    public Book read(int id) throws RuntimeException{
        logger.info("Start reading book");
        if(!exist(id)) {
            logger.error("book does not exist, id = " + id);
            throw new RuntimeException("book does not exist");
        }
        logger.info("Read book from db" + bookDao.read(id));
        return bookDao.read(id);
    }


    @Override
    public List<Book> read() {
        logger.info("Read all books from db");
        return bookDao.read();
    }

    @Override
    public void update(Book data) throws RuntimeException {

        logger.info("Start updating book, id=" + data.getId());
        if(!exist(data.getId())) {
            logger.error("Book does not exist, id=" + data.getId());
            throw new RuntimeException("book does not exist");
        }
        bookDao.update(data);
        logger.info("Finish updating book " + bookDao.read(data.getId()));
    }


    @Override
    public void delete(int id) throws RuntimeException {
        logger.info("Start deleting book, id=" + id);
        if(!exist(id)) {
            logger.error("book does not exist, id= " + id);
            throw new RuntimeException("book does not exist");
        }
        bookDao.delete(id);
        logger.info("Finish deleting book, id=" + id);

    }

    @Override
    public boolean exist(int id) {
        logger.info("Check if book exists, id=" + id);
        return bookDao.exist(id);
    }

    @Override
    public List<Book> readBooksByAuthor(int id) throws RuntimeException {

        logger.info("Read books by author, author id=" + id);
        if(!authorDao.exist(id)){
            logger.error("Author does not exist, id=" + id);
            throw new RuntimeException("Author does not exist, id=" + id);
        }
        return bookDao.readBooksByAuthor(id);
    }

    @Override
    public Book findBookByName(String title) {
        logger.info("Find book by name: " + title);
        return bookDao.findBookByName(title);
    }
}
