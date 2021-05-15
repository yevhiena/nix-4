package ua.com.alevel.view;

import ua.com.alevel.controller.Task1Controller;
import ua.com.alevel.controller.Task2Controller;
import ua.com.alevel.controller.Task3Controller;

public class View {
    private static final Task1Controller TASK_1_CONTROLLER = new Task1Controller();
    private static final Task2Controller TASK_2_CONTROLLER = new Task2Controller();
    private static final Task3Controller TASK_3_CONTROLLER = new Task3Controller();

    public static void view() throws Exception {
        TASK_1_CONTROLLER.run();
        TASK_2_CONTROLLER.run();
        TASK_3_CONTROLLER.run();
    }
}
