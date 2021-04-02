package ua.com.alevel.service;


import org.apache.commons.collections.CollectionUtils;
import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.impl.AuthorServiceImpl;
import ua.com.alevel.service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.List;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookServiceTest {
    private static final AuthorService authorService = new AuthorServiceImpl();
    private static final BookService bookService = new BookServiceImpl();

    private static final String firstName = "Name";
    private static final String lastName = "Surname";
    private static final int initialSize = 10;
    private static final String title = "Title";


    @BeforeAll
    public static void initBook() {
        for (int i = 0; i < initialSize; i++) {
            String firstName = "Name" + i;
            String lastName = "Surname" + i;
            Author author = new Author();
            author.setFirstName(firstName);
            author.setLastName(lastName);

            authorService.create(author);

            List<Integer> authorId = new ArrayList<>();
            authorId.add(authorService.findAuthorByName(firstName,lastName).getId());
            String title = "Title" + i;
            Book book = new Book();
            book.setTitle(title);
            book.setAuthorId(authorId);

            bookService.create(book);
        }

        List<Book> allBooks = bookService.read();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(allBooks));
        Assertions.assertEquals(initialSize, allBooks.size());

    }

    @Test
    @Order(1)
    public void readAll() {
        List<Book> books = bookService.read();

        Assertions.assertTrue(CollectionUtils.isNotEmpty(books));
        Assertions.assertEquals(initialSize, books.size());
    }

    @Test
    @Order(2)
    public void createBook() {

        List<Book> books= bookService.read();
        int previous = books.size();

        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorService.create(author);

        Book book = new Book();
        book.setTitle(title);

        List<Integer> authorId = new ArrayList<>();
        authorId.add(authorService.findAuthorByName(firstName,lastName).getId());
        book.setAuthorId(authorId);
        bookService.create(book);

        book = bookService.findBookByName(title);

        books = bookService.read();
        int last = books.size();

        Assertions.assertNotNull(book);
        Assertions.assertEquals(previous + 1, last);
    }


    @Test
    @Order(3)
    public void readBookById() {
        int bookId = bookService.findBookByName(title).getId();

        Book book = bookService.read(bookId);

        Assertions.assertEquals(title, book.getTitle());
    }


    @Test
    @Order(4)
    public void findBookByName(){
        Book book;

        book = bookService.findBookByName(title);

        Assertions.assertNotNull(book);
        Assertions.assertEquals(title, book.getTitle());

    }


    @Test
    @Order(5)
    public void readBooksByAuthor(){
        int authorId = authorService.findAuthorByName(firstName, lastName).getId();
        List<Book> books = new ArrayList<>();
        books.add(bookService.findBookByName(title));

        Assertions.assertNotNull(bookService.readBooksByAuthor(authorId));
        Assertions.assertEquals(books, bookService.readBooksByAuthor(authorId) );
    }

    @Test
    @Order(6)
    public void existBook(){
        int bookId = bookService.findBookByName(title).getId();

        Assertions.assertTrue(bookService.exist(bookId));

    }


    @Test
    @Order(7)
    public void updateBook() {
        int bookId = 1;
        Book bookInDB = bookService.read(bookId);
        String updName = bookInDB.getTitle() + " updated";
        bookInDB.setTitle(updName);
        bookService.update(bookInDB);

        bookInDB = bookService.read(bookId);

        Assertions.assertEquals(updName, bookInDB.getTitle());
    }


    @Test
    @Order(8)
    public void deleteBook(){

        List<Book> books = bookService.read();
        int previous = books.size();


        Book bookInDB = bookService.findBookByName(title);
        bookService.delete(bookInDB.getId());

        Book bookDeleted = bookService.findBookByName(title);

        books = bookService.read();
        int last = books.size();


        Assertions.assertNull(bookDeleted);
        Assertions.assertEquals(previous - 1, last);
    }

    @AfterAll
    public static void cleanList(){
        List<Author> authors = new ArrayList<>(authorService.read());
        for (Author a: authors){
            authorService.delete(a.getId());
        }

        List<Book> books = new ArrayList<>(bookService.read());
        for (Book a: books){
            bookService.delete(a.getId());
        }

        Assertions.assertEquals(0, authorService.read().size());
        Assertions.assertEquals(0, bookService.read().size());
    }

}
