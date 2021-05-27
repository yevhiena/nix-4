package ua.com.alevel.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import ua.com.alevel.csvtable.CsvTable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {

    public static CsvTable readFromCsv(InputStream input) {
        List<String[]> fromCsv = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(input))) {
            fromCsv = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return new CsvTable(fromCsv);
    }
}
