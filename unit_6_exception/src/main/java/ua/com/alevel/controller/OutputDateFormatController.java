package ua.com.alevel.controller;

import ua.com.alevel.date.Date;

public class OutputDateFormatController {

    public void run(String outputFormat, Date date) {

        switch (outputFormat) {

            case "1": DDMMYYFormat(date);
                break;
            case "2": MDYYYYFormat(date);
                break;
            case "3": MMMDDYYFormat(date);
                break;
            case "4": DDMMMYYYY0000Format(date);
                break;

        }

    }

    private void DDMMYYFormat(Date date){
        StringBuilder stringBuilder = new StringBuilder();
        if(date.getDay() < 10) stringBuilder.append("0");
        stringBuilder.append(date.getDay());
        stringBuilder.append("/");
        if(date.getMonth() < 10) stringBuilder.append("0");
        stringBuilder.append(date.getMonth());
        stringBuilder.append("/");
        if(date.getYear()< 10) stringBuilder.append("0");
        else if (date.getYear() > 1921 && date.getYear() < 2022){
            String year = String.valueOf(date.getYear()).substring(2);
            stringBuilder.append(year);
        }
        else stringBuilder.append(date.getYear());

        System.out.println(stringBuilder);
    }


    private void MDYYYYFormat(Date date){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(date.getMonth());
        stringBuilder.append("/");
        stringBuilder.append(date.getDay());
        stringBuilder.append("/");
        stringBuilder.append(date.getYear());

        stringBuilder.append(" ");
        stringBuilder.append(date.getHours());
        stringBuilder.append(":");
        stringBuilder.append(date.getMinutes());
        stringBuilder.append(":");
        stringBuilder.append(date.getSeconds());
        stringBuilder.append(":");
        stringBuilder.append(date.getMilliseconds());

        System.out.println(stringBuilder);

    }


    private void MMMDDYYFormat(Date date){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getMonthFromNumber(date.getMonth()));
        stringBuilder.append(" ");
        stringBuilder.append(date.getDay());
        stringBuilder.append(" ");
        if (date.getYear() > 1921 && date.getYear() < 2022){
            String year = String.valueOf(date.getYear()).substring(2);
            stringBuilder.append(year);
        }
        else stringBuilder.append(date.getYear());

        System.out.println(stringBuilder);
    }


    private void DDMMMYYYY0000Format(Date date){
        StringBuilder stringBuilder = new StringBuilder();
        if(date.getDay() < 10) stringBuilder.append("0");
        stringBuilder.append(date.getDay());
        stringBuilder.append(" ");
        stringBuilder.append(getMonthFromNumber(date.getMonth()));
        stringBuilder.append(" ");
        stringBuilder.append(date.getYear());
        stringBuilder.append(" ");
        if(date.getHours() < 10) stringBuilder.append("0");
        stringBuilder.append(date.getHours());
        stringBuilder.append(":");
        if(date.getMinutes() < 10) stringBuilder.append("0");
        stringBuilder.append(date.getMinutes());
        stringBuilder.append(":");
        if(date.getSeconds() < 10) stringBuilder.append("0");
        stringBuilder.append(date.getSeconds());

        System.out.println(stringBuilder);
    }

    private String getMonthFromNumber(int month){
        String m ="";
        switch (month){
            case 1: m = "January"; break;
            case 2: m = "February"; break;
            case 3: m = "March"; break;
            case 4: m = "April"; break;
            case 5: m = "May"; break;
            case 6: m = "June"; break;
            case 7: m = "July"; break;
            case 8: m = "August"; break;
            case 9: m = "September"; break;
            case 10: m = "October"; break;
            case 11: m = "November"; break;
            case 12: m = "December"; break;
        }
        return m;
    }

}
