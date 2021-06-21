package ua.com.alevel;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class Writer implements Runnable{
    private volatile boolean running;
    private String currentText;
    private String previous;

    private final File file;

    public Writer(File file) {
        this.file = file;
        this.running = true;
        this.currentText = "";
        this.previous = "";
    }

    public void setCurrentText(String currentText) {
        this.currentText = currentText;
    }
    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        while (running) {

            try {
                Thread.sleep(1000);
                if(!this.currentText.equals(this.previous)){
                    writeTextInFile(this.currentText);
                    previous = currentText;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void writeTextInFile(String text)  {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
            StringBuilder textBuilder = new StringBuilder(text);
            textBuilder.append(" ".repeat(Math.max(0, previous.length() - textBuilder.length())));
            text = textBuilder.toString();

            randomAccessFile.write(text.getBytes(StandardCharsets.UTF_8), 0, text.length());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
