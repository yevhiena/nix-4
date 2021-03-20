package ua.com.alevel.controller;

import org.apache.commons.lang3.math.NumberUtils;
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
                        break;
                    }
                    default:
                        System.out.println("Sorry, incorrect operation");
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
                System.out.println("Sorry, incorrect operation");
        }
    }


    public void readSum(){
        consoleService.writeMessageToConsole("Please, enter first value: ");
        String firstValue = consoleService.getMessageFromConsole();
        consoleService.writeMessageToConsole("Please, enter second value: ");
        String secondValue = consoleService.getMessageFromConsole();
        if(NumberUtils.isCreatable(firstValue) && NumberUtils.isDigits(secondValue)){
            BigDecimal valueLeft = new BigDecimal(firstValue);
            BigDecimal valueRight = new BigDecimal(secondValue);
            consoleService.writeMessageToConsole("The result is " + calcSrvice.sum(valueLeft, valueRight));
        } else {
            System.out.println("Sorry, Entered data is not a number!");
        }
    }
    public void readMultiply(){

        consoleService.writeMessageToConsole("Please, enter first value: ");
        String firstValue = consoleService.getMessageFromConsole();
        consoleService.writeMessageToConsole("Please, enter second value: ");
        String secondValue = consoleService.getMessageFromConsole();
        if(NumberUtils.isCreatable(firstValue) && NumberUtils.isDigits(secondValue)){
            BigDecimal valueLeft = new BigDecimal(firstValue);
            BigDecimal valueRight = new BigDecimal(secondValue);
            consoleService.writeMessageToConsole("The result is " + calcSrvice.multiply(valueLeft, valueRight));
        } else {
            System.out.println("Sorry, Entered data is not a number!");
        }
    }

    public void readDivide(){

        consoleService.writeMessageToConsole("Please, enter first value: ");
        String firstValue = consoleService.getMessageFromConsole();
        consoleService.writeMessageToConsole("Please, enter second value: ");
        String secondValue = consoleService.getMessageFromConsole();
        if(NumberUtils.isCreatable(firstValue) && NumberUtils.isDigits(secondValue)){
            BigDecimal valueLeft = new BigDecimal(firstValue);
            BigDecimal valueRight = new BigDecimal(secondValue);
            if(valueRight.intValue() !=0){
                consoleService.writeMessageToConsole("The result is " + calcSrvice.divide(valueLeft, valueRight));
            } else System.out.println("Sorry, Division by zero is forbidden!");
        } else {
            System.out.println("Sorry, Entered data is not a number!");
        }
    }
    public void readSubtract(){

        consoleService.writeMessageToConsole("Please, enter first value: ");
        String firstValue = consoleService.getMessageFromConsole();
        consoleService.writeMessageToConsole("Please, enter second value: ");
        String secondValue = consoleService.getMessageFromConsole();
        if(NumberUtils.isCreatable(firstValue) && NumberUtils.isDigits(secondValue)){
            BigDecimal valueLeft = new BigDecimal(firstValue);
            BigDecimal valueRight = new BigDecimal(secondValue);
            consoleService.writeMessageToConsole("The result is " + calcSrvice.subtract(valueLeft, valueRight));
        } else {
            System.out.println("Sorry, Entered data is not a number!");
        }
    }
}