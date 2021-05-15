package ua.com.alevel.task1.entity;


public class Date {
    int day;
    int month;
    int year;

    @Override
    public String toString(){
        return "Date{" +
                " day='" + day+ '\'' +
                ", month='" + month + '\'' +
                ", year'" + year + '\'' +
                '}';
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
