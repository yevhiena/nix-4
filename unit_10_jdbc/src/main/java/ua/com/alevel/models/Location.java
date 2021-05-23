package ua.com.alevel.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location extends AbstractModel{
    private String name;

    public Location(int id, String name) {
        super(id);
        this.name = name;
    }
}
