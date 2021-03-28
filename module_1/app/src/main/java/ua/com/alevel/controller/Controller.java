package ua.com.alevel.controller;

import ua.com.alevel.chess.MovesInChess;
import ua.com.alevel.digitsarray.DigitsArray;
import ua.com.alevel.gamelife.GameLifeNextGeneration;
import ua.com.alevel.util.MatrixUtil;
import ua.com.alevel.stringwithparentheses.StringWithParentheses;
import ua.com.alevel.trianglesquare.CalculateTriangleSquare;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;


public class Controller {
    BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

    public void read() throws Exception {
        try {
            String operation;
            System.out.println("Please, check your operations: 0 - exit, 1 - continue  ");

            while ((operation = buf.readLine()) != null) {
                switch (operation) {
                    case "0" : {
                        buf.close();
                        System.exit(0);
                    }
                    case "1" : {
                        switchOperation();
                        break;
                    }
                    default:
                        System.out.println("Sorry, incorrect operation");
                }
                System.out.println("Please, check your operations: 0 - exit, 1 - continue  ");
            }
        } catch (IOException e) {
            buf.close();
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    private void switchOperation() throws Exception {
        System.out.println("Please, check your operations:  " +
                "\n if you want to check first level, please enter a 1" +
                "\n if you want to check second level, please enter a 2" +
                "\n if you want to check third level, please enter a 3");

        String operation;
        operation = buf.readLine();
        switch (operation) {
            case "1":
                readFirstLevel();
                break;
            case "2":
                readSecondLevel();
                break;
            case "3":
                readThirdLevel();
                break;
            default:
                System.out.println("Sorry, incorrect operation");
        }
    }

    private void readFirstLevel() throws IOException {
        System.out.println("Please, check your operations:  " +
                "\n if you want to check unique digits, please enter a 1" +
                "\n if you want to check chess, please enter a 2" +
                "\n if you want to check triangle square, please enter a 3");

        String operation;
        operation = buf.readLine();
        switch (operation) {
            case "1":
                readUniqueDigits();
                break;
            case "2":
                readChess();
                break;
            case "3":
                readTriangleSquare();
                break;
            default:
                System.out.println("Sorry, incorrect operation");
        }
    }


    private void readUniqueDigits() throws IOException {
        DigitsArray digitsArray = new DigitsArray();
        System.out.println("Enter an array of digits, separate every digit by space(example '1 2 3'): ");
        String str = buf.readLine();
        if(str.replaceAll("[. ]", "").chars().allMatch(x -> Character.isDigit(x)) == true){
            System.out.println("The number of unique digits is(not numbers) " + digitsArray.getUniqueSymbols(str));
        } else System.out.println("Input is invalid, you should input only numbers");

    }

    private void readChess() throws IOException {
        System.out.println("Check if the move is possible");
        HashMap<String, BigInteger> positionMap = new HashMap<>();
        System.out.println("Enter a current position(x1,y1) as two numbers");
        System.out.println("Enter X1 coordinate: ");
        BigInteger x1 = new BigInteger(buf.readLine());
        positionMap.put("x1", x1);
        System.out.println("Enter Y1 coordinate: ");
        BigInteger y1 = new BigInteger(buf.readLine());
        positionMap.put("y1", y1);
        System.out.println("Enter a destination position(x1,y1) as two numbers");
        System.out.println("Enter X2 coordinate: ");
        BigInteger x2 = new BigInteger(buf.readLine());
        positionMap.put("x2", x2);
        System.out.println("Enter Y2 coordinate: ");
        BigInteger y2 = new BigInteger(buf.readLine());
        positionMap.put("y2", y2);

        MovesInChess checkMovesInChess = new MovesInChess();
        boolean result = checkMovesInChess.isValidMove(positionMap);
        System.out.println("The answer is " + result);
    }

    private void readTriangleSquare() throws IOException {
        HashMap<String, Double> map = new HashMap<>();
        System.out.println(" enter the coordinates(x1,y1) of the first point");
        System.out.println("Enter X2 coordinate: ");
        map.put("x1", Double.parseDouble(buf.readLine()));
        System.out.println("Enter Y1 coordinate: ");
        map.put("y1", Double.parseDouble(buf.readLine()));
        System.out.println(" enter the coordinates(x2,y2) of the second point");
        System.out.println("Enter X2 coordinate: ");
        map.put("x2", Double.parseDouble(buf.readLine()));
        System.out.println("Enter Y1 coordinate: ");
        map.put("y2", Double.parseDouble(buf.readLine()));
        System.out.println(" enter the coordinates(x3,y3) of the third point");
        System.out.println("Enter X2 coordinate: ");
        map.put("x3", Double.parseDouble(buf.readLine()));
        System.out.println("Enter Y1 coordinate: ");
        map.put("y3", Double.parseDouble(buf.readLine()));
        CalculateTriangleSquare calculateTriangleSquare = new CalculateTriangleSquare();
        double square= calculateTriangleSquare.getTriangleSquare(map);
        System.out.println("result = " + square);
    }


    private void readSecondLevel() throws IOException {
        System.out.println("Enter a string with brackets to check if the string is valid" +
                "(the order of brackets is correct): ");
        String string = buf.readLine();
        StringWithParentheses stringWithParentheses = new StringWithParentheses();
        boolean result = stringWithParentheses.isValidString(string);
        System.out.println("The answer is: " + result);
    }

    private void readThirdLevel() throws IOException {
        GameLifeNextGeneration gameLifeNextGeneration = new GameLifeNextGeneration();
        System.out.println("The game field will be generated randomly");
        System.out.println("Enter the length of the gaming field: ");
        int length = Integer.parseInt(buf.readLine());
        System.out.println("Enter the width of the gaming field: ");
        int width = Integer.parseInt(buf.readLine());

        int[][] field = MatrixUtil.generateRandomMatrix(length, width);
        System.out.println("Initial random matrix: ");
        MatrixUtil.printField(length, width, field);

        System.out.println("First generation of game ");
        MatrixUtil.printField(length, width, gameLifeNextGeneration.getNextGeneration(length, width, field));
    }

}