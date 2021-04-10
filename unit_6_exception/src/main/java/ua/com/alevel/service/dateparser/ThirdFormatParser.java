package ua.com.alevel.service.dateparser;

import org.apache.commons.lang3.math.NumberUtils;
import ua.com.alevel.date.Date;
import ua.com.alevel.utils.validator.DateValidator;


public class ThirdFormatParser {

    public Date MMMDDYYFormat(String date) throws RuntimeException {
        String[] splitDate = date.split(" ");
        if(splitDate.length != 3)
            throw new RuntimeException("Invalid date for this format, incorrect amount of details");
        Date formatDate = new Date();
        for (int i = 1; i < splitDate.length; i++) {
            if (!NumberUtils.isDigits(splitDate[i])) throw new RuntimeException("Invalid input");
        }
        String month = splitDate[0];
        formatDate.setMonth(DateValidator.getMonthNumberFromText(month));

        if(splitDate[1].charAt(0) == '0' && splitDate[1].length() >1 )
            throw new RuntimeException("Invalid format, zero before number should be omitted");
        else formatDate.setDay(Integer.parseInt(splitDate[1]));

        int year;
        if(splitDate[2].charAt(0) == '0' && splitDate[2].length()>1)
            throw new RuntimeException("Invalid format, zero before number should be omitted");
        else year = Integer.parseInt(splitDate[2]);
        if(year <= 21 && year>= 0) year += 2000;
        formatDate.setYear(year);


        DateValidator.checkIfDateIsPValid(formatDate);
        return formatDate;
    }
}
