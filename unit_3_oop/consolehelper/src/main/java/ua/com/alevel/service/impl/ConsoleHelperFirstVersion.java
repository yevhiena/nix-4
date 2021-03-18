package ua.com.alevel.service.impl;

import ua.com.alevel.service.ConsoleService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;



public class ConsoleHelperFirstVersion implements ConsoleService {
    BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public BigDecimal getMessageFromConsole() {
        BigDecimal value;

        try {
            value = new BigDecimal(String.valueOf(buf.readLine()));
            return value;
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
