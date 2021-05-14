package ua.com.alevel.controller;

import ua.com.alevel.util.DateParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Task1Controller {

    public void run() throws IOException {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("TASK 3 ");
        System.out.println("--------------------------------------------------------------------\n");

        List<String> dates = DateParser.readFromFile("module_2/task_1/src/main/java/ua.com.alevel/db/dates.txt");
        List<String> datesConverted = new ArrayList<>();
        for (String str : dates) {
            String converted = DateParser.convertDate(str);
            if(converted != null) datesConverted.add(converted);
        }
        System.out.println("INPUT:");
        System.out.println(dates);
        System.out.println("(you can also find it in module_2/task_1/src/main/java/ua.com.alevel/db/dates.txt)");
        System.out.println("OUTPUT: ");
        System.out.println(datesConverted + "\n");
        System.out.println("Dates in wrong format are ignored");
    }
}
