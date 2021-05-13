package ua.com.alevel;


import ua.com.alevel.graph.WeightedGraph;
import ua.com.alevel.service.AStarPathFinder;
import ua.com.alevel.utils.TxtGraphParser;
import ua.com.alevel.utils.TxtWriter;

import java.io.IOException;
import java.util.List;

public class MainGraph {
    public static void main(String[] args) throws IOException {
        WeightedGraph weightedGraph = new WeightedGraph();
        AStarPathFinder aStarPathFinder = new AStarPathFinder();

        TxtGraphParser.getWeightedGraph(weightedGraph);
        List<String> paths = TxtGraphParser.getPaths();

        for (String path : paths) {
            String[] pathSplit = path.split(" ");
            double cost = aStarPathFinder.getShortestPath(weightedGraph, pathSplit[0], pathSplit[1]);
            TxtWriter.writeLine(String.valueOf(cost));
            System.out.println(cost);
        }
    }
}
