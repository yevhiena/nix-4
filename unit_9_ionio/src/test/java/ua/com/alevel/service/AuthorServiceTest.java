package ua.com.alevel.service;


import com.opencsv.CSVWriter;
import org.apache.commons.collections.CollectionUtils;
import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.impl.AuthorServiceImpl;
import ua.com.alevel.service.impl.BookServiceImpl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthorServiceTest {

    private static final AuthorService authorService = new AuthorServiceImpl();
    private static final BookService bookService = new BookServiceImpl();
    private static final String fileAuthors = "src/main/java/ua/com/alevel/db/authors.csv";
    private static final String fileBooks = "src/main/java/ua/com/alevel/db/books.csv";
    private static final String firstName = "Name";
    private static final String lastName = "Surname";
    private static final int initialSize = 10;
    private static final String title = "Title";


    @BeforeAll
    public static void initAuthor() throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileAuthors))) {
            String[] header = {"id", "first_name", "last_name", "visible"};
            writer.writeNext(header);
        }
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileBooks))) {
            String[] header = {"id", "title", "author_id", "visible"};
            writer.writeNext(header);
        }
        for (int i = 0; i < initialSize; i++) {
            String firstName = "Name" + i;
            String lastName = "Surname" + i;
            Author author = new Author();
            author.setFirstName(firstName);
            author.setLastName(lastName);
           
            authorService.create(author);
        }
       Assertions.assertTrue(CollectionUtils.isNotEmpty(authorService.read()));
    }

    @Test
    @Order(1)
    public void readAll() {
        List<Author> authors = authorService.read();

        Assertions.assertTrue(CollectionUtils.isNotEmpty(authors));
        Assertions.assertEquals(initialSize, authors.size());

    }

    @Test
    @Order(2)
    public void createAuthor() {

        int previous = authorService.read().size();

        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorService.create(author);

        author = authorService.findAuthorByName(firstName, lastName);

        int last = authorService.read().size();

        Assertions.assertNotNull(author);
        Assertions.assertEquals(previous + 1, last);
    }


    @Test
    @Order(3)
    public void readAuthorById() {

        int authorId = authorService.findAuthorByName(firstName, lastName).getId();
        Author author = authorService.read(authorId);

        Assertions.assertEquals(firstName, author.getFirstName());
        Assertions.assertEquals(lastName, author.getLastName());
    }

    @Test
    @Order(4)
    public void findAuthorByName(){
        Author author = authorService.findAuthorByName(firstName, lastName);

        Assertions.assertNotNull(author);
        Assertions.assertEquals(firstName, author.getFirstName());
        Assertions.assertEquals(lastName, author.getLastName());

    }


    @Test
    @Order(5)
    public void readAuthorsByBook(){
        Author author = authorService.findAuthorByName(firstName, lastName);
        List<Author> authors = new ArrayList<>();
        authors.add(author);

        List<Integer> authorId = new ArrayList<>();
        authorId.add(author.getId());

        Book book = new Book();
        book.setTitle(title);
        book.setAuthorId(authorId);
        bookService.create(book);

        book = bookService.findBookByName(title);

        Assertions.assertEquals(authorService.readAuthorsByBook(book.getId()).size(), 1);
        Assertions.assertEquals(authors, authorService.readAuthorsByBook(book.getId()));
    }


    @Test
    @Order(6)
    public void existAuthor(){
        int authorId = authorService.findAuthorByName(firstName, lastName).getId();

        Assertions.assertTrue(authorService.exist(authorId));

    }

    @Test
    @Order(7)
    public void updateAuthor() {
        int authorId = 1;

        Author authorInDB = authorService.read(authorId);

        String updName = lastName + " updated";
        authorInDB.setLastName(updName);
        authorService.update(authorInDB);

        authorInDB = authorService.read(authorId);

        Assertions.assertEquals(updName, authorInDB.getLastName());
    }


    @Test
    @Order(8)
    public void deleteAuthor(){
        int previous  = authorService.read().size();

        Author author = authorService.findAuthorByName(firstName, lastName);
        authorService.delete(author.getId());

        author = authorService.findAuthorByName(firstName, lastName);

        int last = authorService.read().size();

        Assertions.assertNull(author);
        Assertions.assertEquals(previous - 1, last);
    }

    @AfterAll
    static void cleanList() throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileAuthors))) {
            String[] header = {"id", "first_name", "last_name", "visible"};
            writer.writeNext(header);
        }
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileBooks))) {
            String[] header = {"id", "title", "author_id", "visible"};
            writer.writeNext(header);
        }
        Assertions.assertEquals(0, authorService.read().size());
        Assertions.assertEquals(0, bookService.read().size());

    }
}
