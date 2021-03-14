package ua.com.alevel.tasks;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EndOfLessons {
    private int startOfLessonsInMinutes = 540;
    private int lessonDurationInMinutes = 45;
    private int smallBreak = 5;
    private int longBreak = 15;

    public String calculateEndOfLesson(int numberOfLesson){

        int endOfLesson = startOfLessonsInMinutes + numberOfLesson * lessonDurationInMinutes
                + (int)((numberOfLesson - 1) / 2) * (smallBreak+longBreak) + ((numberOfLesson - 1)) % 2 * smallBreak;
        String strEndOfLesson = (int)(endOfLesson / 60) + " " + endOfLesson % 60;
        return strEndOfLesson;
    }

}
