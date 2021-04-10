package ua.com.alevel.controller;

import ua.com.alevel.date.Date;
import ua.com.alevel.service.calendarservice.CalendarService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Controller {
    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private final InputDateFormatController input = new InputDateFormatController();
    private final OutputDateFormatController output = new OutputDateFormatController();
    private final CalendarService calendarService = new CalendarService();


        public void run() throws IOException {
            System.out.println("Hello!");
            System.out.println("Select you event by entering a number");
            System.out.println("0 - exit");
            System.out.println("1 - calculate time difference between two dates");
            System.out.println("2 - add time to date");
            System.out.println("3 - subtract time from date");
            System.out.println("4 - sort dates by Asc and Desc order");


            String position;
            while ((position = bufferedReader.readLine()) != null) {
                switch (position) {
                    case "0" : {
                        System.exit(0);
                    }
                    case "1" : timeDifference(); break;
                    case "2" : addTime(); break;
                    case "3" : subtractTime(); break;
                    case "4" : orderListOfDates(); break;
                    default:
                        System.out.println("Invalid operation, try again");
                }
                System.out.println("Your variant: if you want exit, please input 0, else, repeat logic");
            }
            bufferedReader.close();
        }


        private void timeDifference(){
            System.out.println("Choose input format of first date: ");
            String inputFormatFirst= chooseInputOutputFormat();

            System.out.println("Enter first date in a chosen format: ");
            Date first = input.run(inputFormatFirst);

            System.out.println("Choose input format of second date: ");
            String inputFormatSecond= chooseInputOutputFormat();
            System.out.println("Enter second date in a chosen format: ");
            Date second = input.run(inputFormatSecond);

            Date newDate = calendarService.getDifference(first, second);
            outputDifferenceBetweenDates(newDate);

            }

        private void addTime() throws IOException {
            System.out.println("Choose input format of dates: ");
            String inputFormat= chooseInputOutputFormat();
            System.out.println("Enter a date in a chosen format: ");
            Date date = input.run(inputFormat);
            System.out.println("Enter a time to add from the date: ");
            Date diff = inputDifferenceBetweenDates();
            Date newDate = calendarService.addDate(date, diff);
            System.out.println("Choose output format of dates: ");
            String outputFormat= chooseInputOutputFormat();
            System.out.println("New date is: ");
            output.run(outputFormat, newDate);

        }

        private void subtractTime() throws IOException {
            System.out.println("Choose input format of dates: ");
            String inputFormat= chooseInputOutputFormat();
            System.out.println("Enter a date in a chosen format: ");
            Date date = input.run(inputFormat);
            System.out.println("Enter a time to subtract from the date: ");
            Date diff = inputDifferenceBetweenDates();
            Date newDate = calendarService.subtractDate(date, diff);

            System.out.println("Choose output format of dates: ");
            String outputFormat= chooseInputOutputFormat();
            System.out.println("New date is: ");
            output.run(outputFormat, newDate);

        }


        private void orderListOfDates() throws IOException {
            List<Date> dates = new ArrayList<>();

            System.out.println("Enter number of dates to sort");
            int amountOfDates = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Choose input format of dates");

            String inputFormat= chooseInputOutputFormat();

            for (int i = 0; i < amountOfDates; i++) {
                System.out.println("Enter a date in a chosen format");
               dates.add(input.run(inputFormat));
            }

            List <Date> desc = calendarService.compareDatesDesc(dates);
            List <Date> asc = calendarService.compareDatesAsc(dates);

            System.out.println("Choose output format for Desc ordered list of dates");
            String outputFormatDesc = chooseInputOutputFormat();

            System.out.println("List of dates in Desc order: ");
            for (Date d: desc) {
                output.run(outputFormatDesc, d);
            }
            System.out.println("Choose output format for Asc ordered list of dates");
            String outputFormatAsc = chooseInputOutputFormat();

            System.out.println("List of dates in Asc order: ");
            for (Date d: asc) {
                output.run(outputFormatAsc, d);
            }

        }



    private Date inputDifferenceBetweenDates() throws IOException {
        Date date = new Date();
        System.out.println("Enter years : ");
        date.setYear(Integer.parseInt(bufferedReader.readLine()));
        System.out.println("Enter months(number): ");
        date.setMonth(Integer.parseInt(bufferedReader.readLine()));
        System.out.println("Enter days: ");
        date.setDay(Integer.parseInt(bufferedReader.readLine()));
        System.out.println("Enter hours : ");
        date.setHours(Integer.parseInt(bufferedReader.readLine()));
        System.out.println("Enter minutes: ");
        date.setMinutes(Integer.parseInt(bufferedReader.readLine()));
        System.out.println("Enter seconds : ");
        date.setSeconds(Integer.parseInt(bufferedReader.readLine()));
        System.out.println("Enter milly seconds : ");
        date.setMilliseconds(Integer.parseInt(bufferedReader.readLine()));

        return date;

    }

    private void outputDifferenceBetweenDates(Date date){

        System.out.println("Difference between dates is: ");
        System.out.println(date.getYear() + " years, " +
                date.getMonth() + " months, " +
                date.getDay() + " days, " +
                date.getHours() + " hours, " +
                date.getMinutes() + " minutes, " +
                date.getSeconds() + " seconds, " +
                date.getMilliseconds() + " milly seconds");

    }


    private String chooseInputOutputFormat()  {
        System.out.println("To get detailed info about input requirements of each format please look into README.MD file ");
        System.out.println("Select  date format");
        System.out.println("1 - dd/mm/yy - 01/12/21 format");
        System.out.println("2 - m/d/yyyy h:m:s:ms - 3/4/2021 8:23:15:10 format, ");
        System.out.println("3 - mmm-d-yy - March 4 21 format");
        System.out.println("4 - dd-mmm-yyyy hh:mm:ss - 09 April 789 08:23:15 format");

        String format;
        while (true){
            try {
                format = format();
                break;
            }
            catch (RuntimeException e) {
                System.out.println(e.getMessage());
                System.out.println("Input valid operation: ");
            }
        }

        return format;
    }


    private String format() throws  RuntimeException{
        String position = null;
        try {
            position = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String format;
        switch (Objects.requireNonNull(position)) {

            case "1": format = "1";
                break;
            case "2": format = "2";
                break;
            case "3": format = "3";
                break;
            case "4": format = "4";
                break;
            default: throw new RuntimeException("Invalid operation for input/output format");

        }
        return format;
    }


}
