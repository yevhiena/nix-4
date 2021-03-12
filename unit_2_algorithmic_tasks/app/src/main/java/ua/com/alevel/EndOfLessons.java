package ua.com.alevel;


import lombok.Getter;
import lombok.Setter;

import java.util.StringJoiner;

@Getter
@Setter

public class EndOfLessons {
    private int startOfLessonsInMinutes = 540;


    public String calculateEndOfLesson(int numberOfLesson){

        int endOfLesson = startOfLessonsInMinutes + numberOfLesson*45 + (int)((numberOfLesson-1)/2)*20 + ((numberOfLesson-1))%2*5;
        String str = (int)(endOfLesson/60) + " " + endOfLesson%60;
        return str;
    }



}
