package ua.com.alevel.date;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Date {
    int milliseconds;
    int seconds;
    int minutes;
    int hours;
    int day;
    int month;
    int year;

    @Override
    public String toString(){
        return "Date{" +
                " day='" + day+ '\'' +
                ", month='" + month + '\'' +
                ", year'" + year + '\'' +
                ", hours'" + hours + '\'' +
                ", minutes'" + minutes + '\'' +
                ", seconds'" + seconds + '\'' +
                ", milliseconds'" + milliseconds + '\'' +
                '}';
    }
}
