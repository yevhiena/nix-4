package ua.com.alevel.utils;

import java.io.FileWriter;
import java.io.IOException;

public class TxtWriter {

    public static void writeLine(String line){
        try(FileWriter writer = new FileWriter("module_2/task_3/src/main/java/ua/com/alevel/db/output.txt", true))
        {
            writer.write(line);
            writer.write("\n");
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
