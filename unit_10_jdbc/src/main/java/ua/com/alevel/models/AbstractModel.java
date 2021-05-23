package ua.com.alevel.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractModel {
    protected int id;


    public AbstractModel(int id) {
        this.id = id;
    }
}
