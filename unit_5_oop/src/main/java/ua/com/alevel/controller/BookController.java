package ua.com.alevel.controller;

import lombok.SneakyThrows;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.BookService;
import ua.com.alevel.service.impl.AuthorServiceImpl;
import ua.com.alevel.service.impl.BookServiceImpl;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class BookController {


        private final AuthorService authorService = new AuthorServiceImpl();
        private final BookService bookService = new BookServiceImpl();

        @SneakyThrows
        public void create(BufferedReader reader) {

            List<Integer> authorId = new ArrayList<>();

            System.out.println("please enter title of the book");
            String title = reader.readLine();
            System.out.println("Enter number of authors");
            int numberOfAuthors = Integer.parseInt(reader.readLine());
            List<String> namesOfAuthors = new ArrayList<>();


            for(int i = 0; i < numberOfAuthors; i++){
                System.out.println("please enter next author of the book(example: 'firstname lastname')");
                namesOfAuthors.add(reader.readLine());
            }


            for (String s : namesOfAuthors) {
                Author author = new Author();
                String[] fistLastName = s.split(" ");

                author.setFirstName(fistLastName[0]);
                author.setLastName(fistLastName[1]);

                Author authorInDB = authorService.findAuthorByName(author.getFirstName(), author.getLastName());

                if (authorInDB == null) {
                    authorService.create(author);
                    authorId.add(authorService.findAuthorByName(author.getFirstName(), author.getLastName()).getId());
                } else authorId.add(authorInDB.getId());

            }


            Book book = new Book();
            book.setTitle(title);
            book.setAuthorId(authorId);
            bookService.create(book);

        }



        @SneakyThrows
        public void update(BufferedReader reader) {
            System.out.println("please enter book id ...");
            int id = Integer.parseInt(reader.readLine());
            System.out.println("please enter book title...");
            String title = reader.readLine();

            Book book = bookService.read(id);
            book.setTitle(title);
            bookService.update(book);
        }

        @SneakyThrows
        public void delete(BufferedReader reader) {
            System.out.println("please enter book id ...");
            int id = Integer.parseInt(reader.readLine());
            bookService.delete(id);

        }

        public void readAllBooks(BufferedReader reader) {
            System.out.println("books = " + bookService.read());
        }

        @SneakyThrows
        public  void readBookById(BufferedReader reader){
            System.out.println("please enter book id ...");
            int id = Integer.parseInt(reader.readLine());
            System.out.println("book = " + bookService.read(id));
        }


        @SneakyThrows
        public void readBooksByAuthor(BufferedReader reader){
            System.out.println("please enter author id ...");
            int id = Integer.parseInt(reader.readLine());
            System.out.println("Books written by author " + bookService.readBooksByAuthor(id));

        }
}
