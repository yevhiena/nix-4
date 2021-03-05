package com.alevel.flowers;


import org.apache.commons.lang3.RandomUtils;
import java.util.HashMap;

public class Flowers {
    HashMap<String, Integer> flowers = new HashMap<>();

    public Flowers(){
        flowers.put("roses", RandomUtils.nextInt(10, 30));
        flowers.put("violets", RandomUtils.nextInt(10, 30));
        flowers.put("sunflowers", RandomUtils.nextInt(10, 30));
    }

    public HashMap<String, Integer> getFlowers(){
        return flowers;
    }
}
