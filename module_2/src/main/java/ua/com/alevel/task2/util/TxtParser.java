package ua.com.alevel.task2.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TxtParser {

    public static List<String> readData(String path) throws IOException {

        return  Files.readAllLines(Paths.get(path));
    }
}
