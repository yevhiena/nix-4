package ua.com.alevel.chess;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;

public class MovesInChess {

    public boolean isValidMove(Map<String, BigInteger> map){
        ArrayList<BigInteger> difference = new ArrayList<>();
        difference.add(map.get("x2").subtract(map.get("x1")).abs());
        difference.add(map.get("y2").subtract(map.get("y1")).abs());
        BigInteger checkFirst = new BigInteger("1");
        BigInteger checkSecond = new BigInteger("2");
        if(difference.contains(checkFirst) && difference.contains(checkSecond)){
            return true;
        } else return false;
    }
}
