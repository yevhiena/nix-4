package ua.com.alevel.util;

import ua.com.alevel.entity.Date;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class DateParser {
    private static final List<Integer> daysInMonthList = Arrays.asList(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);

    public static List<String> readFromFile(String path) throws IOException {
        return Files.readAllLines(Paths.get(path));
    }

    public static String convertDate(String date){
        Date d = findPattern(date);
        if(d == null) return null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(d.getYear());
        if(d.getMonth() < 10) stringBuilder.append("0");
        stringBuilder.append(d.getMonth());
        if(d.getDay() < 10) stringBuilder.append("0");
        if(d.getYear() < 10) stringBuilder.append("000");
        else if (d.getYear() < 100) stringBuilder.append("00");
        else if (d.getYear() < 1000) stringBuilder.append("0");
        stringBuilder.append(d.getDay());

        return stringBuilder.toString();
    }


    private static Date findPattern(String date){
        Date d = new Date();
        if (date.matches("\\d{2}-\\d{2}-\\d{4}")){ d = firstFormat(date);
        }
        else {
            if(date.matches("\\d{2}/\\d{2}/\\d{4}")) d = secondFormat(date);
            else if(date.matches("\\d{4}/\\d{2}/\\d{2}")) d = thirdFormat(date);
        }
        return checkIfDateIsPValid(d);
    }

    private static Date firstFormat(String dateStr){
        Date date = new Date();
        String[] split = dateStr.split("-");
        date.setMonth(Integer.parseInt(split[0]));
        date.setDay(Integer.parseInt(split[1]));
        date.setYear(Integer.parseInt(String.valueOf(split[2])));
        return date;
    }


    private static Date secondFormat(String dateStr){
        Date date = new Date();
        String[] split = dateStr.split("/");
         date.setDay(Integer.parseInt(split[0]));
         date.setMonth(Integer.parseInt(split[1]));
         date.setYear(Integer.parseInt(String.valueOf(split[2])));
        return date;
    }

    private static Date thirdFormat(String dateStr){
        Date date = new Date();
        String[] split = dateStr.split("/");
        date.setDay(Integer.parseInt(split[2]));
        date.setMonth(Integer.parseInt(split[1]));
        date.setYear(Integer.parseInt(String.valueOf(split[0])));
        return date;
    }

    private static Date checkIfDateIsPValid(Date date){
        boolean leap = (date.getYear() % 400 == 0) || ((date.getYear() % 4 == 0) && (date.getYear() % 100 != 0));
        if(date.getYear() < 0) return null;
        else if(date.getMonth() <= 0 || date.getMonth() > 12) return null;
        else if(date.getDay() <= 0) return null;
        else if(date.getMonth() == 2 && leap && date.getDay() > 29) return null;
        else if(date.getMonth() == 2 && !leap && date.getDay() > 28) return null;
        else if(date.getDay() > daysInMonthList.get(date.getMonth() - 1)) return null;
        return date;
    }

}
