package ua.com.alevel.controller;

import lombok.SneakyThrows;
import ua.com.alevel.entity.Author;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.impl.AuthorServiceImpl;

import java.io.BufferedReader;


public class AuthorController {


    private final AuthorService authorService = new AuthorServiceImpl();

    @SneakyThrows
    public void create(BufferedReader reader) {

        System.out.println("please enter the author of the book(example: 'firstname lastname')");
        String name = reader.readLine();
        String[] firstLastName = name.split(" ");

        Author author = new Author();
        author.setFirstName(firstLastName[0]);
        author.setLastName(firstLastName[1]);
        authorService.create(author);

    }

    @SneakyThrows
    public void update(BufferedReader reader) {
        System.out.println("please enter author id ...");
        int id = Integer.parseInt(reader.readLine());
        System.out.println("please enter authors first name...");
        String firstName= reader.readLine();
        System.out.println("please enter authors last name...");
        String lastName= reader.readLine();

        Author author = authorService.read(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorService.update(author);
    }

    @SneakyThrows
    public void delete(BufferedReader reader) {
        System.out.println("please enter author id ...");
        int id = Integer.parseInt(reader.readLine());
        authorService.delete(id);
    }


    public void readAllAuthors(BufferedReader reader) {
        System.out.println("authors = " + authorService.read());
    }

    @SneakyThrows
    public void readAuthorById(BufferedReader reader){
        System.out.println("please enter author id ...");
        int id = Integer.parseInt(reader.readLine());
        System.out.println("author = " + authorService.read(id));
    }


    @SneakyThrows
    public void readAuthorByBook(BufferedReader reader){
        System.out.println("please enter book id ...");
        int id = Integer.parseInt(reader.readLine());
        System.out.println("authors who wrote the book " + authorService.readAuthorsByBook(id));
    }
}
