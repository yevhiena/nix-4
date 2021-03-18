package ua.com.alevel.view;

import ua.com.alevel.controller.Controller;



public class View{

    private static final Controller CONSOLE_CONTROLLER = new Controller();

    public static void view() throws Exception {
        CONSOLE_CONTROLLER.read();
    }
}