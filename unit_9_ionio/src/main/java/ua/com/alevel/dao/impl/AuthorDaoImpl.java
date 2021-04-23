package ua.com.alevel.dao.impl;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.dao.BookDao;
import ua.com.alevel.entity.Author;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorDaoImpl implements AuthorDao {

    private  final String fileAuthors = "src/main/java/ua/com/alevel/db/authors.csv";
    private final BookDao bookDao = new BookDaoImpl();


    @Override
    public void create(Author data){
        List<String[]> csvAuthors = readFromCsv();
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileAuthors, true))) {
            if(csvAuthors.size() == 0){
                data.setId(1);
                String[] header = {"id", "first_name", "last_name", "visible"};
                writer.writeNext(header);
            }
            else if(csvAuthors.size() == 1) data.setId(1);
            else data.setId( Integer.parseInt(csvAuthors.get(csvAuthors.size() - 1)[0]) + 1);
            writer.writeNext(convertToCsv(data));
        } catch (IOException e) {
            System.out.println("Unable to write author to db");
        }
    }

    @Override
    public Author read(int id) {
        return read().stream().filter(b -> b.getId() == id).findFirst().get();
    }


    @Override
    public List<Author> read() {
        List<String[]> csvAuthors = readFromCsv();
        List<Author> authors = new ArrayList<>();
        if(csvAuthors.size() > 1){
            for (int i = 1; i < csvAuthors.size(); i++) {
                if(csvAuthors.get(i)[3].equals("true")){
                    Author author = new Author();
                    author.setId(Integer.parseInt(csvAuthors.get(i)[0]));
                    author.setFirstName(csvAuthors.get(i)[1]);
                    author.setLastName(csvAuthors.get(i)[2]);
                    author.setVisible(Boolean.parseBoolean(csvAuthors.get(i)[3]));
                    authors.add(author);
                }
            }
        }
        return authors;
    }

    @Override
    public void update(Author data){
        List<String[]> csvAuthors = readFromCsv();
        for (int i = 1; i < csvAuthors.size(); i++)  {
            if(csvAuthors.get(i)[0].equals(String.valueOf(data.getId()))){
                csvAuthors.set(i, convertToCsv(data));
            }
        }
        writeAuthorToDb(csvAuthors);
    }

    @Override
    public void delete(int id) {
        List<String[]> csvAuthors = readFromCsv();
        for (String[] string : csvAuthors) {
            if(string[0].equals(String.valueOf(id))){
                string[3] = "false";
            }
        }
        writeAuthorToDb(csvAuthors);
    }

    @Override
    public boolean exist(int id) {
        return  read().stream().anyMatch(author -> author.getId() == id);
    }

    @Override
    public List<Author> readAuthorsByBook(int id) {
        List<Integer> authorsId = bookDao.read().stream().filter(b -> b.getId() == id).findFirst().get().getAuthorId();
        return read().stream().filter(a -> authorsId.contains(a.getId())).collect(Collectors.toList());
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return read().stream().filter(au -> au.getFirstName().equals(firstName)).filter(au -> au.getLastName().equals(lastName))
                .findFirst().orElse(null);
    }


    private void writeAuthorToDb(List<String[]> authors){
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileAuthors))) {
            writer.writeAll(authors);
        } catch (IOException e) {
            System.out.println("Unable to read authors from db");
        }
    }

    private String[] convertToCsv(Author data){
        String[] a = new String[4];
        a[0] = String.valueOf(data.getId());
        a[1] = data.getFirstName();
        a[2] = data.getLastName();
        a[3] = String.valueOf(data.isVisible());
        return a;
    }


    private List<String[]> readFromCsv(){
        List<String[]> fromCsv = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(fileAuthors))) {
            fromCsv = reader.readAll();
        } catch (IOException | CsvException e) {
            System.out.println("Unable to write authors to db");
        }
        return fromCsv;
    }

}
