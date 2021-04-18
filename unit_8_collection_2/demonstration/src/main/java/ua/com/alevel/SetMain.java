package ua.com.alevel;

import ua.com.alevel.demonstrationtest.DemoTest;

public class SetMain {
    public static void main(String[] args) {
        DemoTest demoTest = new DemoTest();
        System.out.println("Hello, this ia a demonstration of main functions from MathSet ");
        demoTest.createSet();
        demoTest.add();
        demoTest.join();
        demoTest.get();
        demoTest.getStatisticParams();
        demoTest.sort();
        demoTest.squash();
        demoTest.toArray();
        demoTest.clear();
        System.out.println("To use lib add dependency in your module ");

    }
}
