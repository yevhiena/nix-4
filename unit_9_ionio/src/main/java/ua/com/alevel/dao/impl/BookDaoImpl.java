package ua.com.alevel.dao.impl;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import ua.com.alevel.dao.BookDao;
import ua.com.alevel.entity.Book;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookDaoImpl implements BookDao {

    private  final File fileBooks = new File("unit_9_ionio/src/main/java/ua/com/alevel/db/books.csv");


    @Override
    public void create(Book data) {
        List<String[]> csvBooks = readFromCsv();
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileBooks, true))) {
            if(csvBooks.size() == 0) {
                data.setId(1);
                String[] header = {"id", "title", "author_id", "visible"};
                writer.writeNext(header);
            }
            else if(csvBooks.size() == 1) data.setId(1);
            else data.setId(Integer.parseInt(csvBooks.get(csvBooks.size() - 1)[0]) + 1);
            writer.writeNext(convertToCsv(data));
        } catch (IOException e) {
            System.out.println("Unable to write book to db");
        }
    }

    @Override
    public Book read(int id) {
        return read().stream().filter(b -> b.getId() == id).findFirst().get();
    }


    @Override
    public List<Book> read() {
        List<String[]> csvBooks = readFromCsv();
        List<Book> books = new ArrayList<>();
        if(csvBooks.size() > 1){
            for (int i = 1; i < csvBooks.size(); i++) {
                if(csvBooks.get(i)[3].equals("true")){
                    Book book  = new Book();
                    book.setId(Integer.parseInt(csvBooks.get(i)[0]));
                    book.setTitle(csvBooks.get(i)[1]);
                    List<Integer> authorId = new ArrayList<>();
                    String[] aId = csvBooks.get(i)[2].split(" ");
                    for (String id: aId){
                        authorId.add(Integer.parseInt(id));
                    }
                    book.setAuthorId(authorId);
                    book.setVisible(Boolean.parseBoolean(csvBooks.get(i)[3]));
                    books.add(book);
                }
            }
        }
        return books;
    }

    @Override
    public void update(Book data) {
        List<String[]> csvBooks = readFromCsv();
        for (int i = 1; i < csvBooks.size(); i++)  {
            if(csvBooks.get(i)[0].equals(String.valueOf(data.getId()))){
                csvBooks.set(i, convertToCsv(data));
            }
        }
        writeBookToDb(csvBooks);
    }

    @Override
    public void delete(int id) {
        List<String[]> csvBooks = readFromCsv();
        for (String[] string : csvBooks) {
            if(string[0].equals(String.valueOf(id))){
                string[3] = "false";
            }
        }
        writeBookToDb(csvBooks);
    }

    @Override
    public boolean exist(int id) {
        return  read().stream().anyMatch(book -> book.getId() == id);
    }

    @Override
    public List<Book> readBooksByAuthor(int id) {
        return read().stream().filter(data -> data.getAuthorId().contains(id)).collect(Collectors.toList());
    }

    @Override
    public Book findBookByName(String title) {
        return read().stream().filter(b -> b.getTitle().equals(title))
                .findFirst().orElse(null);
    }


    private void writeBookToDb(List<String[]> books){
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileBooks))) {
            writer.writeAll(books);
        } catch (IOException e) {
            System.out.println("Unable to write book to db");
        }
    }

    private String[] convertToCsv(Book data){
        String[] b = new String[4];
        b[0] = String.valueOf(data.getId());
        b[1] = data.getTitle();
        StringBuilder authorId = new StringBuilder();
        for (Integer i : data.getAuthorId()) {
            authorId.append(i);
            authorId.append(" ");
        }
        authorId.deleteCharAt(authorId.lastIndexOf(" "));
        b[2] = authorId.toString();
        b[3] = String.valueOf(data.isVisible());
        return b;
    }


    private List<String[]> readFromCsv(){
        List<String[]> fromCsv = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(fileBooks))) {
            fromCsv = reader.readAll();
        } catch (IOException | CsvException e) {
            System.out.println("Unable to read books from db");
        }
        return fromCsv;
    }
}
