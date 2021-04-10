package ua.com.alevel.service.dateparser;

import org.apache.commons.lang3.math.NumberUtils;
import ua.com.alevel.date.Date;
import ua.com.alevel.utils.validator.DateValidator;

public class FourthFormatParser {

    public Date DDMMMYYYY0000Format(String date) throws RuntimeException{
        if(date.length() == 0) throw new RuntimeException("Empty input");
        String[] splitDate = date.split(" ");
        if(splitDate.length != 4) throw new RuntimeException("Invalid input, date does not match to chosen format");

        Date formatDate = new Date();

        inputDateParserAndValidator(splitDate, formatDate);

        String[] minAndSec = inputTimeValidator(splitDate);
        parseTime(formatDate, minAndSec);

        DateValidator.checkIfTimeIsValid(formatDate);
        DateValidator.checkIfDateIsPValid(formatDate);


        return formatDate;
    }



    private  String[] inputTimeValidator(String[] splitDate){
        String[] minAndSec = splitDate[3].split(":");
        if(minAndSec.length != 3) throw new RuntimeException("Invalid input, time does not match to chosen format");
        for (String part: minAndSec) {
            if(!NumberUtils.isDigits(part)) throw new RuntimeException("Incorrect input: any part of time should be digit");
        }
        for (String part: minAndSec) {
            if(part.length()<2 && !part.equals("") && !part.equals(" ")) throw new RuntimeException("Invalid input, zero before number is missing");
        }
        return  minAndSec;
    }

    private void parseTime(Date formatDate, String[] minAndSec ){
        if(minAndSec[0].charAt(0) == '0') formatDate.setHours(Integer.parseInt(minAndSec[0].replaceFirst("0", "")));
        else formatDate.setHours(Integer.parseInt(minAndSec[0]));
        if(minAndSec[1].charAt(0) == '0') formatDate.setMinutes(Integer.parseInt(minAndSec[1].replaceFirst("0", "")));
        else formatDate.setMinutes(Integer.parseInt(minAndSec[1]));
        if(minAndSec[2].charAt(0) == '0') formatDate.setSeconds(Integer.parseInt(minAndSec[2].replaceFirst("0", "")));
        else formatDate.setSeconds(Integer.parseInt(minAndSec[2]));
    }

    private void inputDateParserAndValidator(String[] splitDate, Date formatDate){
        if(splitDate[0].charAt(0) == '0' && splitDate[0].length() > 1)
            formatDate.setDay(Integer.parseInt(splitDate[0].replaceFirst("0", "")));
        else if(splitDate[0].length() == 1 || !NumberUtils.isDigits(splitDate[0]))
            throw new RuntimeException("Invalid input, day is not a digit or zero before number is missing");
        else formatDate.setDay(Integer.parseInt(splitDate[0]));

        String month = splitDate[1];
        formatDate.setMonth(DateValidator.getMonthNumberFromText(month));

        if(!NumberUtils.isDigits(splitDate[2]))  throw new RuntimeException("Invalid input, year should be a digit");
        else if(splitDate[2].length() > 1 && splitDate[2].charAt(0) == '0')
            throw new RuntimeException("Invalid input, zero before year should be omitted ");
        else formatDate.setYear(Integer.parseInt(splitDate[2]));
    }
}
