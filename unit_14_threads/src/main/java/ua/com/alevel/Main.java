package ua.com.alevel;

import java.io.File;

public class Main {

    private static final String path = "output.txt";

    public static void main(String[] args) {
        Writer writer = new Writer(new File(path));
        Reader reader = new Reader(writer);
        Thread r = new Thread(reader);
        r.start();
    }
}
