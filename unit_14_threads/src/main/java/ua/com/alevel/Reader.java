package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader implements Runnable{

    private final String exit = "quit";
    private volatile boolean running;
    private Writer writer;

    public Reader(Writer writer) {
        this.running = true;
        this.writer = writer;
    }

    @Override
    public void run() {
        Thread wr = new Thread(writer);
        wr.start();
        String input = "";
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            while (running) {
                if(!wr.isAlive()){
                    running = false;
                }else{
                    System.out.println("Please, enter a string: ");
                    input = bufferedReader.readLine();

                    if (exit.equals(input)){
                        running = false;
                        writer.setRunning(running);
                    }
                    else {
                        writer.setCurrentText(input);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
