package ua.com.alevel.controller;

import ua.com.alevel.data.UserTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BasicController {
    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public void run() throws IOException {
        printOperations();

        String position;
        while ((position = bufferedReader.readLine()) != null) {
            switch (position) {
                case "0" : {
                    System.exit(0);
                }
                case "1" : selectString(); break;
                case "2" : selectInteger(); break;
                case "3" : selectUserTest(); break;

                default:
                    System.out.println("Invalid operation, try again");
            }
        }
        bufferedReader.close();
    }

    private void selectString() throws IOException {
        OperationsController<String> operationsController = new OperationsController<>("String");
        System.out.println("New empty OrderedList<String> was successfully created ");
        operationsController.run();
    }

    private void selectInteger() throws IOException {
        OperationsController<Integer> operationsController = new OperationsController<>("Integer");
        System.out.println("New empty OrderedList<Integer> was successfully created ");
        operationsController.run();
    }

    private void selectUserTest() throws IOException {
        OperationsController<UserTest> operationsController = new OperationsController<>("UserTest");
        System.out.println("New empty OrderedList<UserTest> was successfully created ");
        operationsController.run();
    }

        static void printOperations(){
        System.out.println("Hello! This is a demo for OrderedList lib, for the additional info go to the readme file");
        System.out.println("Select the type of list elements to create from mentioned above");
        System.out.println("Select type entering a number");
        System.out.println("1 - String: will be sorted in alphabet order");
        System.out.println("2 - Integer: will be sorted in ascending order");
        System.out.println("3 - UserTest(String name): will be sorted in alphabet order for field name");
    }

}


