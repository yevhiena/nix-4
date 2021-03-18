package ua.com.alevel.controller;

import ua.com.alevel.service.CalcSrvice;
import ua.com.alevel.service.ConsoleService;

import ua.com.alevel.service.factory.CalcFactory;
import ua.com.alevel.service.factory.ConsoleFactory;

import java.io.IOException;

import java.math.BigDecimal;

public class Controller {

    private final CalcSrvice calcSrvice = CalcFactory.getInstance().getCalcSrvice();
    private final ConsoleService consoleService = ConsoleFactory.getInstance().getConsoleService();

    public void read() throws Exception {
        try {

            String operation;
            consoleService.writeMessageToConsole("Please, check your operations: 0 - exit, 1 - continue  ");
            while ((operation = String.valueOf(consoleService.getMessageFromConsole())) != null) {

                switch (operation) {
                    case "0" : {
                        System.exit(0);
                    }
                    case "1" : {
                        switchOperation();
                    }
                }
                consoleService.writeMessageToConsole("Please, check your operations: 0 - exit, 1 - continue  ");

            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    private void switchOperation() throws Exception {
        consoleService.writeMessageToConsole("Please, check your operations:  " +
                "\n if you need sum, please enter a 1" +
                "\n if you need multiplication, please enter a 2" +
                "\n if you need division, please enter a 3" +
                "\n if you need subtraction, please enter a 4" );
        String operation;
        operation = String.valueOf(consoleService.getMessageFromConsole());
        switch (operation) {
            case "1":
                readSum();
                break;
            case "2":
                readMultiply();
                break;
            case "3":
                readDivide();
                break;
            case "4":
                readSubtract();
                break;
            default:
                throw new RuntimeException("Incorrect operation");
        }
    }


    public void readSum(){
        consoleService.writeMessageToConsole("Please, enter first value: ");
        BigDecimal valueLeft = consoleService.getMessageFromConsole();
        consoleService.writeMessageToConsole("Please, enter second value: ");
        BigDecimal valueRight = consoleService.getMessageFromConsole();
        consoleService.writeMessageToConsole("The result is " + calcSrvice.sum(valueLeft, valueRight));
    }
    public void readMultiply(){

        consoleService.writeMessageToConsole("Please, enter first value: ");
        BigDecimal valueLeft = consoleService.getMessageFromConsole();
        consoleService.writeMessageToConsole("Please, enter second value: ");
        BigDecimal valueRight = consoleService.getMessageFromConsole();
        consoleService.writeMessageToConsole("The result is " + calcSrvice.multiply(valueLeft, valueRight));
    }

    public void readDivide(){

         consoleService.writeMessageToConsole("Please, enter first value: ");
        BigDecimal valueLeft = consoleService.getMessageFromConsole();
        consoleService.writeMessageToConsole("Please, enter second value: ");
        BigDecimal valueRight = consoleService.getMessageFromConsole();
        consoleService.writeMessageToConsole("The result is " + calcSrvice.divide(valueLeft, valueRight));
    }
    public void readSubtract(){

        consoleService.writeMessageToConsole("Please, enter first value: ");
        BigDecimal valueLeft = consoleService.getMessageFromConsole();
        consoleService.writeMessageToConsole("Please, enter second value: ");
        BigDecimal valueRight = consoleService.getMessageFromConsole();
        consoleService.writeMessageToConsole("The result is " + calcSrvice.subtract(valueLeft, valueRight));
    }
}