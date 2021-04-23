package ua.com.alevel.controller;

import ua.com.alevel.entity.Author;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.impl.AuthorServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;


public class AuthorController {


    private final AuthorService authorService = new AuthorServiceImpl();

    public void create(BufferedReader reader) throws IOException {
        try {
            System.out.println("please enter the author of the book(example: 'firstname lastname')");
            String name = reader.readLine();
            String[] firstLastName = name.split(" ");
            Author author = new Author();
            author.setFirstName(firstLastName[0]);
            author.setLastName(firstLastName[1]);
            authorService.create(author);
        } catch (IndexOutOfBoundsException e){
            System.out.println("Invalid input");
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public void update(BufferedReader reader) throws IOException {
        try {
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
        }catch (NumberFormatException e){
            System.out.println("Invalid input for id");
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(BufferedReader reader) throws IOException {
        try {
            System.out.println("please enter author id ...");
            int id = Integer.parseInt(reader.readLine());
            authorService.delete(id);
        }catch (NumberFormatException e){
            System.out.println("Invalid input for id");
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public void readAllAuthors() {
        System.out.println("authors = " + authorService.read());
    }


    public void readAuthorById(BufferedReader reader) throws IOException {
        try {
            System.out.println("please enter author id ...");
            int id = Integer.parseInt(reader.readLine());
            System.out.println("author = " + authorService.read(id));
        }catch (NumberFormatException e){
            System.out.println("Invalid input for id");
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public void readAuthorByBook(BufferedReader reader) throws IOException {
        try {
            System.out.println("please enter book id ...");
            int id = Integer.parseInt(reader.readLine());
            System.out.println("authors who wrote the book " + authorService.readAuthorsByBook(id));
        } catch (NumberFormatException e){
            System.out.println("Invalid input for id");
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
}
