package ua.com.alevel.utils.validator;

import ua.com.alevel.date.Date;

import java.util.Arrays;
import java.util.List;

public class DateValidator {
    private static final List<Integer> daysInMonthList = Arrays.asList(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);

    public static int getMonthNumberFromText(String month) throws RuntimeException {

        int m = 0;
        switch (month){
            case "January": m = 1; break;
            case "February": m = 2; break;
            case "March": m = 3; break;
            case "April": m = 4; break;
            case "May": m = 5; break;
            case "June": m = 6; break;
            case "July": m = 7; break;
            case "August": m = 8; break;
            case "September": m = 9; break;
            case "October": m = 10; break;
            case "November": m = 11; break;
            case "December": m = 12; break;
            default: throw new RuntimeException("Incorrect name of Month, choose from 'January, February, March, April, " +
                    "May, \n June, July, August, September, October, November, December' ");
        }
        return m;
    }

    public static void checkIfDateIsPValid(Date date) throws RuntimeException{
        boolean leap = (date.getYear() % 400 == 0) || ((date.getYear() % 4 == 0) && (date.getYear() % 100 != 0));

        if(date.getYear() < 0)
            throw new RuntimeException("Year should be a positive number");
        if(date.getMonth() <= 0 || date.getMonth() > 12)
            throw new RuntimeException("Number of month should be between 1 and 12");
        if(date.getDay() <= 0)
            throw new RuntimeException("Day of a month cannot be less than 1");
        if(date.getMonth() == 2 && leap && date.getDay() > 29)
            throw new RuntimeException("Only 29 days exit in February this year");
        if(date.getMonth() == 2 && !leap && date.getDay() > 28)
            throw new RuntimeException("Only 28 days exit in February this year");
        if(date.getDay() > daysInMonthList.get(date.getMonth() - 1))
            throw new RuntimeException("You have entered a day, which is greater than amount of days in the month this year");

    }

    public static void checkIfTimeIsValid(Date date) throws RuntimeException{
        if(date.getHours() >= 24 || date.getHours() <0)
            throw new RuntimeException("Enter a number between 0 and 23 for hours");
        if(date.getMinutes() >= 60 || date.getMinutes() <0)
            throw new RuntimeException("Enter a number between 0 and 59 for minutes");
        if(date.getSeconds() >= 60 || date.getSeconds() <0)
            throw new RuntimeException("Enter a number between 0 and 59 for seconds");
        if(date.getMilliseconds() >= 1000 || date.getMilliseconds() <0)
            throw new RuntimeException("Enter a number between 0 and 999 for milly seconds");

    }
}
