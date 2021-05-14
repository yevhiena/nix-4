package ua.com.alevel;

import ua.com.alevel.util.DateParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainTask1 {
    public static void main(String[] args) throws IOException {

        List<String> dates = DateParser.readFromFile("module_2/task_1/src/main/java/ua.com.alevel/db/dates.txt");
        List<String> datesConverted = new ArrayList<>();
        for (String str : dates) {
            String converted = DateParser.convertDate(str);
            if(converted != null) datesConverted.add(converted);
        }
        System.out.println("Hello, this is TASK 1");
        System.out.println("INPUT:");
        System.out.println("(you can also find it in module_2/task_1/src/main/java/ua.com.alevel/db/dates.txt)");
        System.out.println(dates);
        System.out.println("OUTPUT: ");
        System.out.println("Dates in wrong format are ignored");
        System.out.println(datesConverted + "\n");
    }
}
