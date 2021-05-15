package ua.com.alevel.controller;

import ua.com.alevel.task2.service.FindUniqueName;
import ua.com.alevel.task2.util.TxtParser;

import java.io.IOException;
import java.util.List;

public class Task2Controller {

    public void run() throws IOException {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("TASK 2 ");
        System.out.println("--------------------------------------------------------------------\n");
        System.out.println("Each list contains 3 unique names");
        System.out.println("See full input there: src/main/java/ua/com/alevel/task2/db/\n");

        List<String> names = TxtParser.readData("src/main/java/ua/com/alevel/task2/db/names1.txt");

        System.out.println("Number of names: " + names.size());
        FindUniqueName findUniqueName = new FindUniqueName();
        long startTime = System.nanoTime();
        String unique = findUniqueName.findUniqueName(names);
        long resultTime = System.nanoTime() - startTime;
        System.out.println("First unique name in list: " + unique);
        System.out.println("Time: " + resultTime + "\n");

        names = TxtParser.readData("src/main/java/ua/com/alevel/task2/db/names2.txt");
        System.out.println("Number of names: " + names.size());
        startTime= System.nanoTime();
        unique = findUniqueName.findUniqueName(names);
        resultTime = System.nanoTime() - startTime;
        System.out.println("First unique name in list: " + unique);
        System.out.println("Time: " + resultTime + "\n\n");
    }
}
