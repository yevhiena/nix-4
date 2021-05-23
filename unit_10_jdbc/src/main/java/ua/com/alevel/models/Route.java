package ua.com.alevel.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Route extends AbstractModel{
    int fromId;
    int toId;
    int cost;

    public Route(int id, int fromId, int toId, int cost) {
        super(id);
        this.fromId = fromId;
        this.toId = toId;
        this.cost = cost;
    }
}
