package ua.com.alevel.db;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class InMemoryDB {

        private final List<Author> authors = new ArrayList<>();
        private final List<Book> books = new ArrayList<>();


        private static InMemoryDB instance;

        private InMemoryDB() {}

        public static InMemoryDB getInstance() {
            if (instance == null) {
                instance = new InMemoryDB();
            }
            return instance;
        }


        public void createAuthor(Author data) {
            int id;
            if(authors.size() == 0) id =1 ;
            else id = authors.get(authors.size() - 1).getId() + 1;

            data.setId(id);
            authors.add(data);
        }

        public void createBook(Book data) {
            int id;
            if(books.size() == 0) id =1 ;
            else id = books.get(books.size() - 1).getId() + 1;

            data.setId(id);
            books.add(data);
        }

        public List<Author> readAllAuthors() {
            return authors;
        }

        public List<Book> readAllBooks() {
            return books;
        }



        public Author readAuthor(int id) {
            return authors.stream().filter(data -> data.getId() == id).findFirst().get();
        }

        public Book readBook(int id) {
            return books.stream().filter(data -> data.getId() == id).findFirst().get();
        }
        
        

        public List<Author> readAuthorsByBook(int bookId) {
            List<Integer> authorsId = books.stream().filter(b -> b.getId() == bookId).findFirst().get().getAuthorId();
            
            return authors.stream().filter(a -> authorsId.contains(a.getId())).collect(Collectors.toList());
        }



        public List<Book> readBooksByAuthor(int authorId) {
            return books.stream().filter(data -> data.getAuthorId().contains(authorId)).collect(Collectors.toList());
        }



        public void updateAuthor(Author data) {
            Author current = readAuthor(data.getId());
            current.setFirstName(data.getFirstName());
            current.setLastName(data.getLastName());

        }

        public void updateBook(Book data) {
            Book current = readBook(data.getId());
            current.setTitle(data.getTitle());
            current.setAuthorId(data.getAuthorId());
        }

        public void deleteAuthor(int id) {
            authors.removeIf(author -> author.getId() == id);
        }

        public void deleteBook(int id) {
            books.removeIf(book -> book.getId() == id);
        }

        public boolean authorExist(int id){
            return  authors.stream().anyMatch(author -> author.getId() == id);
        }
        public boolean bookExist(int id){
            return  books.stream().anyMatch(book -> book.getId() == id);
        }


        public Author findAuthorByName(String firstName, String lastName){
            return authors.stream().filter(au -> au.getFirstName().equals(firstName)).filter(au -> au.getLastName().equals(lastName))
                    .findFirst().orElse(null);
        }

        public Book findBookByName(String title){
            return books.stream().filter(b -> b.getTitle().equals(title))
                    .findFirst().orElse(null);
        }
}
