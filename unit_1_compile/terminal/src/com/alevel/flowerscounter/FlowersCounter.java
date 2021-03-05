package com.alevel.flowerscounter;

import com.alevel.flowers.Flowers;
import org.apache.commons.collections4.MapUtils;


public class FlowersCounter {
    public void printAmountOfFlowers(){
        Flowers fl =new Flowers();
        int amountOfFlowers = MapUtils.size(fl.getFlowers()) ;
        System.out.println("There are " + amountOfFlowers + " flowers in store");
    }
}
