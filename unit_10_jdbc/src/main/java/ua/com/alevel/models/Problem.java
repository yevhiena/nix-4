package ua.com.alevel.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Problem extends AbstractModel{
    int fromId;
    int toId;

    public Problem(int id, int fromId, int toId) {
        super(id);
        this.fromId = fromId;
        this.toId = toId;
    }
}
