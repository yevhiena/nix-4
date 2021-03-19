package ua.com.alevel.controller;
import ua.com.alevel.impl.ReverseStringUtil;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;


public class Controller {
    BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

    public void read() throws Exception {
        try {

            String operation;

            System.out.println("Please, check your operations: 0 - exit, 1 - continue  ");

            while ((operation = buf.readLine()) != null) {

                switch (operation) {
                    case "0" : {
                        buf.close();
                        System.exit(0);
                    }
                    case "1" : {
                        switchOperation();
                    }
                    default:
                        System.out.println("Incorrect operation");
                }
                System.out.println("Please, check your operations: 0 - exit, 1 - continue  ");

            }

        } catch (IOException e) {
            buf.close();
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    private void switchOperation() throws Exception {
        System.out.println("Please, check your operations:  " +
                "\n if you need reverse string, please enter a 1" +
                "\n if you need reverse a substring, please enter a 2" +
                "\n if you need reverse substring by indexes, please enter a 3");
        String operation;
        operation = buf.readLine();
        switch (operation) {
            case "1":
                readReverseString();
                break;
            case "2":
                readReverseSubString();
                break;
            case "3":
                readReverseSubStringBetweenIndexes();
                break;
            default:
                System.out.println("Incorrect operation");
        }
    }


    public void readReverseString() throws IOException {
        System.out.println("Please, enter a string: ");
        String src = buf.readLine();
        String reversedString = ReverseStringUtil.reverse(src);
        System.out.println("reversedString = " + reversedString);
    }

    public void readReverseSubString() throws IOException {
        System.out.println("Please, enter a string: ");
        String src = buf.readLine();
        System.out.println("Please, enter a substring: ");
        String substring = buf.readLine();
        String reversedSubstring = ReverseStringUtil.reverse(src, substring);
        System.out.println("reversedSubstring = " + reversedSubstring);

    }

    public void readReverseSubStringBetweenIndexes() throws IOException {
        System.out.println("Please, enter a string: ");
        String src = buf.readLine();
        System.out.println("Please, enter a start index: ");
        int startIndex = Integer.parseInt(buf.readLine());
        System.out.println("Please, enter an end index: ");
        int endIndex = Integer.parseInt(buf.readLine());
        String reversedSubstring = ReverseStringUtil.reverse(src, startIndex, endIndex);
        System.out.println("reversedSubstring = " + reversedSubstring);
    }
}