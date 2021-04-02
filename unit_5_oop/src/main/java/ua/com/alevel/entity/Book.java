package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;


@Setter
@Getter
public class Book extends AbstractData{
    private int id;
    private String title;
    private List<Integer> authorId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "Book{" +
                " id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", authorId=" + authorId +
                '}';
    }
}
