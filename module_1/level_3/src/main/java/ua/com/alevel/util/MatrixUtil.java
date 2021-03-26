package ua.com.alevel.util;

import java.util.Random;

public class MatrixUtil {
    public static void printField(int length, int width, int[][] field){
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < width; y++) {
                System.out.print(field[x][y] + " | ");
            }
            System.out.println();
        }
    }

    public static int[][] generateRandomMatrix (int length, int width){
        final Random random = new Random();
        int[][] matrix = new int[length][width];
        for (int x = 0; x < length; x++){
            for (int y = 0; y < width; y++){
                matrix[x][y] = random.nextInt(2);
            }
        }
        return matrix;
    }
}
