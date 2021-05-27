package ua.com.alevel;

import ua.com.alevel.model.CsvData;
import ua.com.alevel.util.CsvMapper;

import java.util.List;

public class CSVParserMain {
    private static final String filePath = "taxables.csv";
    public static void main(String[] args) throws IllegalAccessException {
        List<CsvData> objects = CsvMapper.initialize(filePath, CsvData.class);
        System.out.println("List of objects based on data from csv file:\n ");
        for (CsvData object : objects) {
            System.out.println(object);
        }
    }
}
