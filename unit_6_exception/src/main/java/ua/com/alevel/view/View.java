package ua.com.alevel.view;

import ua.com.alevel.controller.Controller;

import java.io.IOException;

public class View {

    private static final Controller CONSOLE_CONTROLLER = new Controller();

    public static void view() throws IOException {
        CONSOLE_CONTROLLER.run();
    }
}
