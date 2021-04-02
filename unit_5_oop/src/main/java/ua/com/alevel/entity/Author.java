package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Author extends AbstractData{
    private int id;
    private String firstName;
    private String lastName;


    @Override
    public String toString() {
        return "Author{" +
                " id='" + id + '\'' +
                ", first name='" + firstName + '\'' +
                ", last name='" + lastName + '\'' +
                '}';
    }

}
