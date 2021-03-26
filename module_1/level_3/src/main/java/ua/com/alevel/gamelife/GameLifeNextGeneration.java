package ua.com.alevel.gamelife;


public class GameLifeNextGeneration {

    public int[][] getNextGeneration(int length, int width, int[][] field){
        int[][] nextGeneration = new int[length][width];
        for (int x = 0; x < length; x++){
            for (int y = 0; y < width; y++){

                int aliveNeighbors = countAliveNeighbors(field, x, y,length, width);

                if (field[x][y] == 0) {
                    if (aliveNeighbors == 3) {
                        nextGeneration[x][y] = 1;
                    }
                } else if (field[x][y] == 1) {

                    if (aliveNeighbors < 2 || aliveNeighbors > 3) {
                        nextGeneration[x][y] = 0;
                    } else nextGeneration[x][y] = 1;
                }
            }
        }
        return nextGeneration;
    }


    private int countAliveNeighbors(int[][] field, int x, int y, int length, int width){
        int count = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i == x && j == y) {
                    continue;
                }
                if (i < 0 || j < 0 ) {
                    continue;
                }
                if (i >= length || j >= width) {
                    continue;
                }
                if (field[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

}
