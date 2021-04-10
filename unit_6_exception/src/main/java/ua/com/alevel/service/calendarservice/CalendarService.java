package ua.com.alevel.service.calendarservice;

import ua.com.alevel.date.Date;

import java.util.*;

import static java.lang.Math.abs;

public class CalendarService {

    private static final int minutesInHour = 60;
    private static final int secInMin = 60;
    private static final int milliSecInSec = 1000;
    private static final int daysInYear = 365;
    private static final int horsInDay = 24;
    private static final int monthInYear = 12;
    private static final List<Integer> daysInMonthSum = Arrays.asList(31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365);
    private static final List<Integer> daysInMonthList = Arrays.asList(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);


    public Date getDifference(Date dateFirst, Date dateSecond){
        int yearDiffer = 0;
        int monthDiffer = 0;
        int daysDiffer = 0;
        int hoursDiffer = 0;
        int minutesDiffer = 0;
        int secDiffer = 0;
        int milliSecDeffer = 0;

        long difference = abs(convertDateToMilliSeconds(dateFirst) - convertDateToMilliSeconds(dateSecond));
        long days = difference/milliSecInSec/secInMin/minutesInHour/horsInDay;

        Date earlier;
        Date later ;

        if(convertDateToMilliSeconds(dateFirst) < convertDateToMilliSeconds(dateSecond)) {
            earlier = dateFirst;
            later = dateSecond;
        }
        else
        {
            later = dateFirst;
            earlier =dateSecond ;
        }

        while (days > daysInYear){
            days-=daysInYear;
            yearDiffer+=1;
            if(isLeapYear(earlier.getYear()) && earlier.getMonth() < 3) days -=1;
        }

        if(earlier.getMilliseconds() <= later.getMilliseconds() ) milliSecDeffer += later.getMilliseconds()  - earlier.getMilliseconds()  ;
        else{
            secDiffer -=1;
            milliSecDeffer += milliSecInSec - earlier.getMilliseconds() + later.getMilliseconds() ;
        }

        if(earlier.getSeconds() <= later.getSeconds() ) secDiffer += later.getSeconds()  - earlier.getSeconds() ;
        else{
            minutesDiffer -=1;
            secDiffer += secInMin- earlier.getSeconds() + later.getSeconds() ;
        }

        if(earlier.getMinutes() <= later.getMinutes()) minutesDiffer += later.getMinutes() - earlier.getMinutes();
        else{
            hoursDiffer  -=1;
            minutesDiffer  += minutesInHour - earlier.getMinutes() + later.getMinutes();
        }

        if(earlier.getHours() <= later.getHours()) hoursDiffer += later.getHours() - earlier.getHours();
        else{
            daysDiffer -=1;
            hoursDiffer += horsInDay - earlier.getHours() + later.getHours();
        }

        if(earlier.getDay() > later.getDay()){
            monthDiffer -=1;
            daysDiffer += daysInMonthList.get(earlier.getMonth() -1) - earlier.getDay() + later.getDay();
        } else {
            daysDiffer += later.getDay() - earlier.getDay();
        }

        if(earlier.getMonth() < later.getMonth()){
            monthDiffer += later.getMonth() - earlier.getMonth();
        } else monthDiffer += monthInYear - earlier.getMonth() + later.getMonth();


        Date newDate = new Date();
        newDate.setYear(yearDiffer);
        newDate.setMonth(monthDiffer);
        newDate.setDay(daysDiffer);
        newDate.setHours(hoursDiffer);
        newDate.setMinutes(minutesDiffer);
        newDate.setSeconds(secDiffer);
        newDate.setMilliseconds(milliSecDeffer);

        return newDate;
    }



    public Date addDate(Date dateFirst, Date timeDifference){
        Date newDate = new Date();
        int millisec = dateFirst.getMilliseconds() + timeDifference.getMilliseconds();
        newDate.setMilliseconds(millisec % milliSecInSec);
        int sec = dateFirst.getSeconds() + timeDifference.getSeconds() + millisec/milliSecInSec;
        newDate.setSeconds(sec % secInMin);
        int min = dateFirst.getMinutes()+timeDifference.getMinutes() + sec/secInMin;
        newDate.setMinutes(min % minutesInHour);
        int hours = dateFirst.getHours() + timeDifference.getHours() + min/minutesInHour;
        newDate.setHours(hours % horsInDay);
        int days = dateFirst.getDay() + timeDifference.getDay() + hours/horsInDay;

        int years = dateFirst.getYear() + timeDifference.getYear();
        int mon = dateFirst.getMonth() + timeDifference.getMonth();
        if(mon > monthInYear){
            int addYears = mon / monthInYear;

            for (int i = 1 ; i <= addYears; i++) {
                if (isLeapYear(years + 1)) days-=1;
            }
            years+=addYears;
            mon = mon % monthInYear;
        }
        if(days <= daysInMonthList.get(mon - 1)){
            newDate.setDay(days);
            newDate.setMonth(mon);
            newDate.setYear(years);
        } else {
            while(days > daysInMonthList.get(mon - 1)){
                if(mon ==2 && isLeapYear(years)) days-=1;
                days -= daysInMonthList.get(mon - 1);
                mon +=1;
                if(mon > monthInYear) {
                    years+=1;
                    mon =1;
                }
            }
            newDate.setDay(days);
            newDate.setMonth(mon);
            newDate.setYear(years);

        }
        return newDate;
    }


    public Date subtractDate(Date dateFirst, Date timeDifference){
        Date newDate = new Date();
        int millisec = dateFirst.getMilliseconds() - timeDifference.getMilliseconds();
        newDate.setMilliseconds(abs(millisec % milliSecInSec) == 0 ? 0:(milliSecInSec + millisec) % milliSecInSec);

        int sec = dateFirst.getSeconds() - timeDifference.getSeconds() + millisec/milliSecInSec ;
        if (millisec<0) sec-=1;
        newDate.setSeconds(abs(sec % secInMin) == 0? 0: (secInMin + sec) % secInMin);

        int min = dateFirst.getMinutes() - timeDifference.getMinutes() + sec/secInMin;
        if (sec<0) min-=1;
        newDate.setMinutes(abs(min % minutesInHour) ==0 ? 0: (minutesInHour + min) % minutesInHour);

        int hours = dateFirst.getHours() - timeDifference.getHours() + min/minutesInHour;
        if(min<0)hours-=1;
        newDate.setHours(abs(hours % horsInDay) == 0 ? 0: (horsInDay + hours) % horsInDay);


        int days = dateFirst.getDay() - timeDifference.getDay() + hours/horsInDay;
        if(hours<0) days-=1;

        int years = dateFirst.getYear() - timeDifference.getYear();
        int mon = dateFirst.getMonth() - timeDifference.getMonth();

        if(mon <= 0 ){
            int subYears = mon / monthInYear - 1;

            for (int i = -subYears ; i >=1; i--) {
                if (isLeapYear(years - 1)) days+=1;
            }

            years+=mon / monthInYear - 1;
            mon =abs(mon % monthInYear) == 0 ? 1: monthInYear - abs(mon % monthInYear);
        }
        if(days > 0){
            newDate.setDay(days);
            newDate.setMonth(mon);
            newDate.setYear(years);
        } else {
            while(days <= 0){
                if(mon ==2 && isLeapYear(years)) days+=1;
                days += daysInMonthList.get(mon - 1);
                mon -=1;
                if(mon <= 0) {
                    years-=1;
                    mon =12;
                }
            }
            newDate.setDay(days);
            newDate.setMonth(mon);
            newDate.setYear(years);

        }
        return newDate;

    }



    public List<Date> compareDatesAsc(List<Date> dates){
        Map<Long, Date> datesInMilliSec = new HashMap<>();
        for (Date d: dates){
            datesInMilliSec.put(convertDateToMilliSeconds(d), d);
        }
        List<Date> dateAsc = new ArrayList<>();
        for (int d = 0; d < datesInMilliSec.size(); d++) {
            long min = datesInMilliSec.keySet().stream().min(Long::compare).get();
            dateAsc.add(datesInMilliSec.get(min));
            datesInMilliSec.remove(min);
        }
        long last = datesInMilliSec.keySet().stream().findFirst().get();
        dateAsc.add(datesInMilliSec.get(last));

        return dateAsc;
    }


    public List<Date> compareDatesDesc(List<Date> dates){
        Map<Long, Date> datesInMilliSec = new HashMap<>();
        for (Date d: dates){
            datesInMilliSec.put(convertDateToMilliSeconds(d), d);
        }
        List<Date> dateDesc = new ArrayList<>();
        for (int d = 0; d < datesInMilliSec.size(); d++) {
            long max = datesInMilliSec.keySet().stream().max(Long::compare).get();
            dateDesc.add(datesInMilliSec.get(max));
            datesInMilliSec.remove(max);
        }
        long last = datesInMilliSec.keySet().stream().findFirst().get();
        dateDesc.add(datesInMilliSec.get(last));

        return dateDesc;
    }




    private int numberOfLeapYears(int yearEnd) {
        int numberOfLeapYears = 0;

        for (int y = 0; y < yearEnd; y++) {
            if(y == 0) continue;
            if((y % 400 == 0) || ((y % 4 == 0) && (y % 100 != 0))){
                numberOfLeapYears +=1;
            }
        }
        return numberOfLeapYears;
    }

    private boolean isLeapYear(int year) {
        return (year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0));
    }


    private int countDaysInCurrentYear(Date date){
        int days = 0;
        switch (date.getMonth() - 1){
            case 1: days = daysInMonthSum.get(0); break;
            case 2: days = daysInMonthSum.get(1); break;
            case 3: days = daysInMonthSum.get(2); break;
            case 4: days = daysInMonthSum.get(3); break;
            case 5: days = daysInMonthSum.get(4); break;
            case 6: days = daysInMonthSum.get(5); break;
            case 7: days = daysInMonthSum.get(6); break;
            case 8: days = daysInMonthSum.get(7); break;
            case 9: days = daysInMonthSum.get(8); break;
            case 10: days = daysInMonthSum.get(9); break;
            case 11: days = daysInMonthSum.get(10); break;
            case 12: days = daysInMonthSum.get(11); break;

        }

        if (isLeapYear(date.getYear()) && date.getMonth() > 2) days+=1;
        return days;
    }


    private long convertDateToMilliSeconds(Date date){

        long days = (long) date.getYear() * daysInYear + numberOfLeapYears( date.getYear()) + countDaysInCurrentYear(date) + date.getDay() - 1;
        long min = ((days) * horsInDay + date.getHours()) * minutesInHour + date.getMinutes();
        long dateInMilliseconds = ((min) * secInMin + date.getSeconds()) * milliSecInSec + date.getMilliseconds();
        return dateInMilliseconds;
    }

}
