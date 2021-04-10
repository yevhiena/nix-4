package ua.com.alevel.service.dateparser;

import org.apache.commons.lang3.math.NumberUtils;
import ua.com.alevel.date.Date;
import ua.com.alevel.utils.validator.DateValidator;

public class SecondFormatParser {
    public Date MDYYYYFormat000000(String date) throws RuntimeException {
        if(date.length() == 0) throw new RuntimeException("Empty input");
        String[] splitDateTime = date.split(" ");

        if(splitDateTime.length != 2)
            throw new RuntimeException("Invalid input, does not match to chosen format");

        String[] splitDate = inputDateValidator(splitDateTime);
        String[] splitTime = inputTimeValidator(splitDateTime);

        Date formatDate = new Date();
        parseDate(splitDate, formatDate);
        parseTime(splitTime, formatDate);
        
        DateValidator.checkIfDateIsPValid(formatDate);
        DateValidator.checkIfTimeIsValid(formatDate);

        return formatDate;
    }


    private String[] inputDateValidator(String[] splitDateTime){
        String[] splitDate = splitDateTime[0].split("/");
        if(splitDateTime[0].charAt(splitDateTime[0].length() -1) == '/')
            throw new RuntimeException("Invalid input, you cannot omit year");
        if(splitDate.length > 3)
            throw new RuntimeException("Invalid input, date does not match to chosen format");

        for (String s : splitDate) {
            if (!NumberUtils.isDigits(s) && !s.equals("") && !s.equals(" "))
                throw new RuntimeException("Invalid input, day month year should be digit");
        }

        for (String part: splitDate) {
            if (part.length()>1 && part.charAt(0) == '0')
                throw new RuntimeException("Invalid input, unnecessary zero before number");
        }
        return splitDate;
    }

    private String[] inputTimeValidator(String[] splitDateTime){
        String[] splitTime = splitDateTime[1].split(":");

        if(splitDateTime[1].charAt(0) == ':')
            throw new RuntimeException("Invalid input, you cannot omit hours");
        if(splitTime.length > 4)
            throw new RuntimeException("Invalid input, time does not match to chosen format");

        for (String part: splitTime) {
            if (!NumberUtils.isDigits(part) && !part.equals("") && !part.equals(" "))
                throw new RuntimeException("Invalid input, any part of time should be digit");
        }
        for (String part: splitTime) {
            if (part.length()>1 && part.charAt(0) == '0')
                throw new RuntimeException("Invalid input, unnecessary zero before number");
            if(part.length()>2)  throw new RuntimeException("Invalid input of time");
        }
        return splitTime;
    }


    private void parseDate(String[] splitDate, Date formatDate){

        if(splitDate.length == 1) {
            formatDate.setYear(Integer.parseInt(splitDate[0]));
            formatDate.setMonth(1);
            formatDate.setDay(1);
        }
        else if (splitDate.length == 2){
            if(splitDate[0].equals(" ") || splitDate[0].equals(""))formatDate.setDay(1);
            else formatDate.setDay(Integer.parseInt(splitDate[0]));
            if(splitDate[1].equals(" ") || splitDate[1].equals(""))
                throw new RuntimeException("Invalid input, you cannot omit year");
            else formatDate.setYear(Integer.parseInt(splitDate[1]));
            formatDate.setMonth(1);
        }
        else if (splitDate.length == 3){
            if (splitDate[0].equals(" ") || splitDate[0].equals("")) formatDate.setMonth(1);
            else formatDate.setMonth(Integer.parseInt(splitDate[0]));
            if(splitDate[1].equals(" ") || splitDate[1].equals("")) formatDate.setDay(1);
            else formatDate.setDay(Integer.parseInt(splitDate[1]));
            if(splitDate[2].equals(" ") || splitDate[2].equals(""))
                throw new RuntimeException("Invalid input, you cannot omit year");
            else formatDate.setYear(Integer.parseInt(splitDate[2]));

        }
    }


    private void parseTime(String[] splitTime, Date formatDate){
        if (splitTime.length == 1) {
            formatDate.setHours(Integer.parseInt(splitTime[0]));
            formatDate.setMinutes(0);
            formatDate.setSeconds(0);
            formatDate.setMilliseconds(0);
        }
        else if(splitTime.length == 2) {
            if(splitTime[0].equals(" ") || splitTime[0].equals(""))
                throw new RuntimeException("Invalid input, you cannot omit hours");
            else formatDate.setHours(Integer.parseInt(splitTime[0]));
            if(splitTime[1].equals(" ") || splitTime[1].equals("")) formatDate.setMinutes(0);
            else formatDate.setMinutes(Integer.parseInt(splitTime[1]));
            formatDate.setSeconds(0);
            formatDate.setMilliseconds(0);
        }
        else if(splitTime.length == 3){
            if(splitTime[0].equals(" ") || splitTime[0].equals(""))
                throw new RuntimeException("Invalid input, you cannot omit hours");
            else formatDate.setHours(Integer.parseInt(splitTime[0]));
            if(splitTime[1].equals(" ") || splitTime[1].equals("")) formatDate.setMinutes(0);
            else formatDate.setMinutes(Integer.parseInt(splitTime[1]));
            if(splitTime[2].equals(" ") || splitTime[2].equals("")) formatDate.setSeconds(0);
            else formatDate.setSeconds(Integer.parseInt(splitTime[2]));
            formatDate.setMilliseconds(0);
        }
        else {
            if(splitTime[0].equals(" ") || splitTime[0].equals(""))
                throw new RuntimeException("Invalid input, you cannot omit hours");
            else formatDate.setHours(Integer.parseInt(splitTime[0]));
            if(splitTime[1].equals(" ") || splitTime[1].equals("")) formatDate.setMinutes(0);
            else formatDate.setMinutes(Integer.parseInt(splitTime[1]));
            if(splitTime[2].equals(" ") || splitTime[2].equals("")) formatDate.setSeconds(0);
            else formatDate.setSeconds(Integer.parseInt(splitTime[2]));
            if(splitTime[3].equals(" ") || splitTime[3].equals("")) formatDate.setMilliseconds(0);
            else formatDate.setMilliseconds(Integer.parseInt(splitTime[3]));

        }
    }
}
