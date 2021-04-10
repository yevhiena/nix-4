package ua.com.alevel.service.dateparser;

import org.apache.commons.lang3.math.NumberUtils;
import ua.com.alevel.date.Date;
import ua.com.alevel.utils.validator.DateValidator;

public class FirstFormatParser {

    public Date DDMMYYFormat(String date) throws RuntimeException{

        String[] splitDate = inputValidator(date);
        Date formatDate = getDateFromString(splitDate);
        DateValidator.checkIfDateIsPValid(formatDate);

        return formatDate;
    }


    private String[] inputValidator(String date) throws RuntimeException {
        if(date.length() == 0) throw new RuntimeException("Empty input");

        String[] splitDate = date.split("/");

        for (int i = 0; i < splitDate.length -1; i++) {
            if(splitDate[i].length() != 2 && !splitDate[i].equals("") && !splitDate[i].equals(" "))
                throw new RuntimeException("Invalid input");
        }
        if(date.length() == 2 && date.contentEquals("//")){
            splitDate = " / / ".split("/");
        }
        if(date.charAt(date.length() -1) =='/') splitDate = date.concat(" ").split("/");
        if(splitDate.length != 3) throw new RuntimeException("Invalid input");

        for (String part: splitDate) {
            if(part.length()<2 && !part.equals("") && !part.equals(" "))
                throw new RuntimeException("Invalid input, zero before number is missing");
        }
        for (String s : splitDate) {
            if (!NumberUtils.isDigits(s)  && !s.equals("") && !s.equals(" "))
                throw new RuntimeException("Invalid input, day and year should be digit");
        }

        return splitDate;
    }

    private Date getDateFromString(String[] splitDate){
        Date formatDate = new Date();
        if (splitDate[0].equals(" ") || splitDate[0].equals("")) formatDate.setDay(1);
        else if(splitDate[0].charAt(0) == '0') formatDate.setDay(Integer.parseInt(splitDate[0].replaceFirst("0", "")));
        else formatDate.setDay(Integer.parseInt(splitDate[0]));

        if(splitDate[1].equals(" ") || splitDate[1].equals(""))formatDate.setMonth(1);
        else if(splitDate[1].charAt(0) == '0') formatDate.setMonth(Integer.parseInt(splitDate[1].replaceFirst("0", "")));
        else formatDate.setMonth(Integer.parseInt(splitDate[1]));

        int year;
        if(splitDate[2].equals(" ") || splitDate[2].equals("")) year = 2021;
        else {
            if(splitDate[2].charAt(0) == '0') year = Integer.parseInt(splitDate[2].replaceFirst("0", ""));
            else year = Integer.parseInt(splitDate[2]);
            if (year > 21 && year < 100) year += 1900;
            else if(year <= 21) year += 2000;
        }
        formatDate.setYear(year);

        return formatDate;

    }

}
