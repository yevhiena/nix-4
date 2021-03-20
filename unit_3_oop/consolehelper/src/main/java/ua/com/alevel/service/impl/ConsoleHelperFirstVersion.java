package ua.com.alevel.service.impl;

import ua.com.alevel.service.ConsoleService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class ConsoleHelperFirstVersion implements ConsoleService {
   private final BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public String getMessageFromConsole() {
        String message;

        try {
            message = buf.readLine();
            return message;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("e + " + e.getMessage());
        }
    }

    @Override
    public void writeMessageToConsole(String message) {
        System.out.println(message);
    }
}
