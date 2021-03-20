package ua.com.alevel.controller;
import ua.com.alevel.impl.ReverseStringUtil;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;


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
                        break;
                    }
                    default:
                        System.out.println("Sorry, incorrect operation");
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
                "\n if you need reverse substring between indexes(indexes are included), please enter a 3" +
                "\n if you need reverse substring between words(words are included) or sequences of letters please enter a 4" +
                "\n if you need reverse substring between symbols(symbols are included), please enter a 5");
        System.out.println("Notice: the punctuation marks are ignored!");
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
            case "4":
                readReverseSubStringBetweenWords();
                break;
            case "5":
                readReverseSubStringBetweenSymbols();
                break;
            default:
                System.out.println("Sorry, incorrect operation");
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
        if(src.contains(substring)){
            String reversedSubstring = ReverseStringUtil.reverse(src, substring);
            System.out.println("reversedSubstring = " + reversedSubstring);
        }
        else {
            System.out.println("Sorry, the substring does not exist");
        }
    }

    public void readReverseSubStringBetweenIndexes() throws IOException {
        System.out.println("Please, enter a string: ");
        String src = buf.readLine();
        System.out.println("Please, enter a start index: ");
        int startIndex = Integer.parseInt(buf.readLine());
        System.out.println("Please, enter an end index: ");
        int endIndex = Integer.parseInt(buf.readLine());
        if(startIndex > 0 && endIndex < src.length()){
            String reversedSubstring = ReverseStringUtil.reverse(src, startIndex, endIndex);
            System.out.println("reversedSubstring = " + reversedSubstring);
        }
        else {
            System.out.println("Sorry, Incorrect index(es)");
        }
    }

    public void readReverseSubStringBetweenWords() throws IOException {
        System.out.println("Please, enter a string: ");
        String src = buf.readLine();
        System.out.println("Please, enter a start word: ");
        String startSymbol = buf.readLine();
        System.out.println("Please, enter an end word: ");
        String endSymbol = buf.readLine();
        if(src.contains(startSymbol) && src.contains(endSymbol)){
            String reversedSubstring = ReverseStringUtil.reverse(src, startSymbol, endSymbol);
            System.out.println("reversedSubstring = " + reversedSubstring);
        }
        else {
            System.out.println("Sorry, main string does not contain entered word(s) or sequence(s) of letters");
        }

    }

    public void readReverseSubStringBetweenSymbols() throws IOException {
        System.out.println("Please, enter a string: ");
        String src = buf.readLine();
        System.out.println("Please, enter a start symbol: ");
        String startSymbol = buf.readLine();
        System.out.println("Please, enter an end symbol: ");
        String endSymbol = buf.readLine();
        if(startSymbol.length() == 1 && endSymbol.length()==1) {
            if (src.contains(startSymbol) && src.contains(endSymbol)) {
                String reversedSubstring = ReverseStringUtil.reverse(src, startSymbol.charAt(0), endSymbol.charAt(0));
                System.out.println("reversedSubstring = " + reversedSubstring);
            } else {
                System.out.println("Sorry, main string does not contain entered symbol(s)");
            }

        } else System.out.println("Sorry, entered data is not separate symbols");
    }
}