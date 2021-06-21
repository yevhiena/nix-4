package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Hippodrome hippodrome = new Hippodrome();
        System.out.println("Choose a horse from list by entering a number: 1, 2, 3, 4 ");
        String horseName = "Horse " + bufferedReader.readLine();
        System.out.println("Starting the race...");
        int place = hippodrome.getPlace(horseName);
        System.out.println("Your horse finished on place: ");
        System.out.println(place);
    }
}
