package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {

        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("TASK 1: Sum of numbers in random string");
        System.out.println("INPUT a string:");

        String randomStringWithNumbers = buf.readLine();





        EndOfLessons end = new EndOfLessons();
        System.out.println("TASK 3: End of lessons");
        System.out.println("INPUT a number of lessons from 1 to 10:");
        int inputNumberOfLessons = Integer.parseInt(buf.readLine());

        System.out.println("OUTPUT: \n Lessons start at 9 00 \n " +
                "The " + inputNumberOfLessons + " lesson ends at: \n " + end.calculateEndOfLesson(inputNumberOfLessons));


    }
}
