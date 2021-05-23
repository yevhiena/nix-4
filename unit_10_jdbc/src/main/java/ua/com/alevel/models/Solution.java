package ua.com.alevel.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Solution extends AbstractModel{
    int cost;


    public Solution(int id, int cost) {
        super(id);
        this.cost = cost;
    }
}
