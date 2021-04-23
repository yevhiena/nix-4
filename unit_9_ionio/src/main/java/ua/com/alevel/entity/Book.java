package ua.com.alevel.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@EqualsAndHashCode
public class Book extends AbstractData{
    private String title;
    private List<Integer> authorId;

    @Override
    public String toString() {
        return "Book{" +
                " id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", authorId=" + authorId +
                '}';
    }
}
