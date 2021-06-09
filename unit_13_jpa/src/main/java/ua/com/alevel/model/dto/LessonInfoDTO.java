package ua.com.alevel.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class LessonInfoDTO {
    private long id;
    private LocalDate date;
    private LocalTime time;
    private String topic;
    private String teacherName;
    private String type;

    public LessonInfoDTO(){
    }

    public LessonInfoDTO(long id, LocalDate date, LocalTime time, String topic, String teacherName, String type) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.topic = topic;
        this.teacherName = teacherName;
        this.type = type;
    }

    @Override
    public String toString() {
        return "LessonInfoDTO{" +
                "date=" + date +
                ", time=" + time +
                ", topic='" + topic + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getTopic() {
        return topic;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getType() {
        return type;
    }


}
