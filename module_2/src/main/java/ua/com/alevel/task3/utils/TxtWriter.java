package ua.com.alevel.task3.utils;

import java.io.FileWriter;
import java.io.IOException;

public class TxtWriter {

    public static void writeLine(String line, String path){
        try(FileWriter writer = new FileWriter(path, true))
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
