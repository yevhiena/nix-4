package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class App 
{
    public static void main( String[] args ) throws IOException {

        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("TASK 1: Sum of numbers in random string");
        System.out.println("INPUT a string:");

        String randomStringWithNumbers = buf.readLine();
        System.out.println("The sum of numbers in random string is: \n"
                + SumOfNumbersInRandomString.sumOfNumbers(randomStringWithNumbers));



        System.out.println("\n\nTASK 2: Sort of symbols");
        System.out.println("INPUT a string:");

        String stringForSorting = buf.readLine();
        SortSymbolsInRandomString.sort(stringForSorting);


        EndOfLessons end = new EndOfLessons();
        System.out.println("\n\nTASK 3: End of lessons");
        System.out.println("INPUT a number of lessons from 1 to 10:");
        int inputNumberOfLessons = Integer.parseInt(buf.readLine());

        System.out.println("OUTPUT: \n Lessons start at 9 00 \n " +
                "The " + inputNumberOfLessons + " lesson ends at: \n " + end.calculateEndOfLesson(inputNumberOfLessons));



        buf.close();
    }
}
