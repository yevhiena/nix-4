package ua.com.alevel.view;

import ua.com.alevel.controller.BasicController;

public class View {

    private static final BasicController CONSOLE_CONTROLLER = new BasicController();

    public static void view() throws Exception {
        CONSOLE_CONTROLLER.run();
    }
}
