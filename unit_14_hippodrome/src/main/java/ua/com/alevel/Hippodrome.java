package ua.com.alevel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Hippodrome {
    private static final int HORSES = 4;
    private final Map<String, Integer> raceResults;
    private final AtomicInteger placeCounter;

    public Hippodrome() {
        this.placeCounter = new AtomicInteger();
        this.raceResults = new ConcurrentHashMap<>();
    }

    private void performRace() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(HORSES);
        List<Race> races = new ArrayList<>();
        for(int i = 0; i < HORSES; i++){
            races.add(new Race("Horse " + (i + 1), raceResults, placeCounter));
        }
        service.invokeAll(races);
        service.shutdown();
    }

    int getPlace(String horseName) throws InterruptedException {
        performRace();
        return raceResults.get(horseName);
    }
}
