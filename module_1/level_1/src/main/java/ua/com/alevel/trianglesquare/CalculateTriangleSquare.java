package ua.com.alevel.trianglesquare;


import java.util.Map;

public class CalculateTriangleSquare {

    public double getTriangleSquare(Map<String, Double> map){
        double square = 0;
        square = Math.abs((map.get("x2") - map.get("x1")) * (map.get("y3")-map.get("y1")) -
                (map.get("x3")-map.get("x1")) * (map.get("y2")-map.get("y1")))/2;
        return square;
    }
}
