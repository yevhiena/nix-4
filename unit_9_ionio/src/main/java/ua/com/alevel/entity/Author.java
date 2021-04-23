package ua.com.alevel.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class Author extends AbstractData{
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
