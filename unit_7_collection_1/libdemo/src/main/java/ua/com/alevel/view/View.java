package ua.com.alevel.view;

import ua.com.alevel.controller.BasicController;

import java.io.IOException;

public class View {

    private static final BasicController CONSOLE_CONTROLLER = new BasicController();

    public static void view() throws IOException {
            CONSOLE_CONTROLLER.run();
    }

}
