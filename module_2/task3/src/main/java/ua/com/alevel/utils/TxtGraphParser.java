package ua.com.alevel.utils;


import org.jgrapht.graph.DefaultWeightedEdge;
import ua.com.alevel.graph.WeightedGraph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TxtGraphParser {
    private static final List<String> names = new ArrayList<>();
    private static final List<String> paths = new ArrayList<>();
    private static final Map<String, List<String>> neighbours = new HashMap<>();

    private static void parseTxt() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("module/task3/src/main/java/ua/com/alevel/db/input.txt"));

        int numberOfCities = Integer.parseInt(lines.get(0));
        int indexOfLine = 1;
        for (int i = 0; i<numberOfCities; i++){
            names.add(lines.get(indexOfLine));
            indexOfLine++;
            int numberOfNeighbours = Integer.parseInt(lines.get(indexOfLine));
            indexOfLine++;
            List<String> neigh = new ArrayList<>();
            for (int j = 0; j < numberOfNeighbours; j++) {
                neigh.add(lines.get(indexOfLine));
                indexOfLine++;
            }
            neighbours.put(names.get(i), neigh);
        }
        int numberOfPaths = Integer.parseInt(lines.get(indexOfLine));
        indexOfLine++;
        for (int i = 0; i < numberOfPaths; i++) {
            paths.add(lines.get(indexOfLine));
            indexOfLine++;
        }
    }

    public static List<String> getPaths(){
        return paths;
    }

    public static void getWeightedGraph(WeightedGraph weightedGraph) throws IOException {
        parseTxt();
        for (String name : names) {
            weightedGraph.getGraph().addVertex(name);
        }
        for (int i = 0; i < neighbours.size(); i++) {
            String name = names.get(i);
            List<String> neigh = neighbours.get(name);
            for (String str : neigh) {
                String[] sp = str.split(" ");

                DefaultWeightedEdge e1 = weightedGraph.getGraph().addEdge(name, names.get(Integer.parseInt(sp[0]) - 1));
                weightedGraph.getGraph().setEdgeWeight(e1, Integer.parseInt(sp[0]));
            }
        }
    }
}
