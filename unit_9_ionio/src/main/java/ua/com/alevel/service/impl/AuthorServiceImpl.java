package ua.com.alevel.service.impl;

import org.apache.log4j.Logger;
import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.dao.impl.AuthorDaoImpl;
import ua.com.alevel.entity.Author;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.BookService;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao = new AuthorDaoImpl();
    private final BookService bookDao= new BookServiceImpl();

    private final Logger logger = Logger.getLogger(AuthorServiceImpl.class);


    @Override
    public void create(Author data) throws RuntimeException {
        logger.info("Start creating author");
        Author author = authorDao.findAuthorByName(data.getFirstName(), data.getLastName());
        if( author == null){
            authorDao.create(data);
            logger.info("Created author in db " + data);
        }
        else {
            logger.error("Author already exists " + author);
            throw new RuntimeException("Author already exists " + author);
        }
    }

    @Override
    public Author read(int id) throws RuntimeException{
        logger.info("Start author read, id=" + id);

        if(!exist(id)) {
            logger.error("author does not exist, id = " + id);
            throw new RuntimeException("author does not exist");
        }
        logger.info("Read author from db " + authorDao.read(id));
        return authorDao.read(id);
    }

    @Override
    public List<Author> read() {
        logger.info("Read all authors from db");
        return authorDao.read();
    }

    @Override
    public void update(Author data) throws RuntimeException{
        logger.info("start updating author, id=" + data.getId());
        if(!exist(data.getId())) {
            logger.error("Author does not exist, id=" + data.getId());
            throw new RuntimeException("author does not exist");
        }
        authorDao.update(data);
        logger.info("Finish updating author " + authorDao.read(data.getId()));
    }

    @Override
    public void delete(int id) throws RuntimeException{
        logger.info("Start deleting author, id=" + id);
        if(!exist(id)) {
            logger.error("Author does not exist, id= " + id);
            throw new RuntimeException("author does not exist");
        }
        authorDao.delete(id);
        logger.info("Finish deleting author, id=" + id);
    }

    @Override
    public boolean exist(int id) {
        logger.info("Check if author exists, id=" + id);
        return authorDao.exist(id);
    }

    @Override
    public List<Author> readAuthorsByBook(int id) throws RuntimeException {
        logger.info("Read authors by book, id=" + id);
        if(!bookDao.exist(id)){
            logger.error("Book does not exist, id=" + id);
            throw new RuntimeException("Book does not exist");
        }
        return authorDao.readAuthorsByBook(id);
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        logger.info("Find author by name: " + firstName + " " + lastName);
        return authorDao.findAuthorByName(firstName, lastName);
    }

}
