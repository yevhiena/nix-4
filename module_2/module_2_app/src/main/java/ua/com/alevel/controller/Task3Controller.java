package ua.com.alevel.controller;

import ua.com.alevel.graph.WeightedGraph;
import ua.com.alevel.service.AStarPathFinder;
import ua.com.alevel.utils.TxtGraphParser;
import ua.com.alevel.utils.TxtWriter;

import java.io.IOException;
import java.util.List;

public class Task3Controller {

    public void run() throws IOException {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("TASK 3 ");
        System.out.println("--------------------------------------------------------------------\n");
        System.out.println("INPUT: You can find input in module_2/task_3/src/main/java/ua/com/alevel/db/input.txt");
        WeightedGraph weightedGraph = new WeightedGraph();
        AStarPathFinder aStarPathFinder = new AStarPathFinder();

        TxtGraphParser.getWeightedGraph(weightedGraph);
        List<String> paths = TxtGraphParser.getPaths();
        System.out.println("OUTPUT:");
        for (String path : paths) {
            String[] pathSplit = path.split(" ");
            System.out.println("The shortest path from " + pathSplit[0] + " to " + pathSplit[1]);
            double cost = aStarPathFinder.getShortestPath(weightedGraph, pathSplit[0], pathSplit[1]);
            if(cost <= 200000){
                TxtWriter.writeLine(String.valueOf(cost));
                System.out.println("Cost: " + cost + "");
            } else
                System.out.println("There is no shortest path as all of them are greater than 200000 ");
        }
    }
}
