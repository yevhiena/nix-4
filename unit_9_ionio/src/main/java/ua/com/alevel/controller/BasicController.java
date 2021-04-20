package ua.com.alevel.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BasicController {

    private final BookController bookController = new BookController();
    private final AuthorController authorController = new AuthorController();


    public void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Hello!");
        System.out.println("Select you event by entering a number");
        System.out.println("0 - exit");
        System.out.println("1 - create author");
        System.out.println("2 - create book");
        System.out.println("3 - read author by id");
        System.out.println("4 - read book by id");
        System.out.println("5 - read all authors");
        System.out.println("6 - read all books");
        System.out.println("7 - read authors by book");
        System.out.println("8 - read books by author");
        System.out.println("9 - update author");
        System.out.println("10 - update book");
        System.out.println("11 - delete author");
        System.out.println("12 - delete book");


        String position;
        while ((position = reader.readLine()) != null) {
            switch (position) {
                case "0" -> {
                    System.exit(0);
                }
                case "1" -> authorController.create(reader);
                case "2" -> bookController.create(reader);
                case "3" -> authorController.readAuthorById(reader);
                case "4" -> bookController.readBookById(reader);
                case "5" -> authorController.readAllAuthors();
                case "6" -> bookController.readAllBooks();
                case "7" -> authorController.readAuthorByBook(reader);
                case "8" -> bookController.readBooksByAuthor(reader);
                case "9" -> authorController.update(reader);
                case "10" -> bookController.update(reader);
                case "11" -> authorController.delete(reader);
                case "12" -> bookController.delete(reader);
            }
            System.out.println("Your variant: if you want exit, please input 0, else, repeat logic");
        }
        reader.close();
    }
}
