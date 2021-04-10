package ua.com.alevel.controller;

import ua.com.alevel.date.Date;
import ua.com.alevel.service.dateparser.FirstFormatParser;
import ua.com.alevel.service.dateparser.FourthFormatParser;
import ua.com.alevel.service.dateparser.SecondFormatParser;
import ua.com.alevel.service.dateparser.ThirdFormatParser;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InputDateFormatController {

    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static final FirstFormatParser firstFormatParser = new FirstFormatParser();
    private static final SecondFormatParser secondFormatParser = new SecondFormatParser();
    private static final ThirdFormatParser thirdFormatParser = new ThirdFormatParser();
    private static final FourthFormatParser fourthFormatParser = new FourthFormatParser();

    public Date run(String inputFormat) {


        Date date = new Date();
        switch (inputFormat) {

            case "1": date = parseFirstFormat();
                break;
            case "2": date =  parseSecondFormat();
                break;
            case "3": date = parseThirdFormat();
                break;
            case "4": date =  parseFourthFormat();
                break;

        }
        return date;
    }


    private Date parseFirstFormat(){
        System.out.println("Enter next date");

        boolean repeat = true;
        Date date = new Date();
        while(repeat){
        try {
            String dateStr  = bufferedReader.readLine();
            date = firstFormatParser.DDMMYYFormat(dateStr);
            repeat = false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Try again, enter a valid date in a chosen format: ");
        }
        }
        return date;
    }


    private Date parseSecondFormat(){
        System.out.println("Enter next date");

        boolean repeat = true;
        Date date = new Date();
        while(repeat){
        try {
            String dateStr = bufferedReader.readLine();
            date = secondFormatParser.MDYYYYFormat000000(dateStr);
            repeat = false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Try again, enter a valid date in a chosen format: ");
        }
        }
        return  date;
    }


    private Date parseThirdFormat(){
        System.out.println("Enter next date");

        boolean repeat = true;
        Date date = new Date();
        while(repeat){
            try {
                String dateStr = bufferedReader.readLine();
                date = thirdFormatParser.MMMDDYYFormat(dateStr);
                repeat = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Try again, enter a valid date in a chosen format: ");
            }
        }
        return  date;
    }


    private Date parseFourthFormat(){
        System.out.println("Enter next date");

        boolean repeat = true;
        Date date = new Date();
        while(repeat){
            try {
                String dateStr = bufferedReader.readLine();
                date = fourthFormatParser.DDMMMYYYY0000Format(dateStr);
                repeat = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Try again, enter a valid date in a chosen format: ");
            }
        }
        return  date;

    }


}
