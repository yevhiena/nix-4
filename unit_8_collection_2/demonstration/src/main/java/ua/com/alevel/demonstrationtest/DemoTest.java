package ua.com.alevel.demonstrationtest;


import ua.com.alevel.service.MathSet;
import ua.com.alevel.service.impl.MathSetImpl;

import java.util.Arrays;

public class DemoTest {
    private MathSet<Integer> set;


    public void createSet(){
        System.out.println("Create set: ");
        Integer[] integers1 = new Integer[]{2,3,8};
        Integer[] integers2 = new Integer[]{1,9,12};
        MathSet<Integer> set1 = new MathSetImpl<>(integers1);
        System.out.println("Set1 created from array: " + set1);
        MathSet<Integer> set2 = new MathSetImpl<>(integers2);
        System.out.println("Set2 created from array: " + set2);
        set = new MathSetImpl<>(set1, set2);
        System.out.println("Basic set created from 2 MathSets: " + set);
        System.out.println("Next functions will be demonstrated on last set \n");
    }

    public void add(){
        System.out.println("Adding numbers: 2, 4 (only unique will be added): ");
        set.add(2,4);
        System.out.println(set + "\n");
    }

    public void join(){
        Integer[] integers1 = new Integer[]{1,15,16};
        MathSet<Integer> setForJoin1 = new MathSetImpl<>(integers1);
        Integer[] integers2 = new Integer[]{0, 13};
        MathSet<Integer> setForJoin2 = new MathSetImpl<>(integers2);
        System.out.println("Joining with " + setForJoin1 + " and " + setForJoin2);
        set.join(setForJoin1, setForJoin2);
        System.out.println("Result " + set + "\n");
    }

    public void get(){
        System.out.println("Get number, index 3: ");
        System.out.println("Number is " + set.get(3) + "\n");
    }

    public void getStatisticParams(){
        System.out.println("Get max: " + set.getMax());
        System.out.println("Get min: " + set.getMin());
        System.out.println("Get average: " + set.getAverage());
        System.out.println("Get median: " + set.getMedian() + "\n");
    }


    public void sort(){
        System.out.println("Sort in Desc order between index 3 and 8(the last is not included)");
        set.sortDesc(3, 8);
        System.out.println(set + "\n");
    }

    public void squash(){
        System.out.println("squash(delete) between 5 and 9 index(9 is not included) " + set.squash(5,9) );
        System.out.println("notice: original set was not changed,  it is just returned value of a method"+ "\n");
    }
    
    public void toArray(){
        System.out.println("Get an array from 1 to 5 index (5 is not included): " + Arrays.toString(set.toArray(1, 5)) );
        System.out.println("notice: original set was not changed, it is just returned value of a method"+ "\n");
    }
    
    public void clear(){
        System.out.println("Clear set:");
        set.clear();
        System.out.println(set + "\n");
    }
    
}
