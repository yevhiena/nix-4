package ua.com.alevel.task3.service;

import org.jgrapht.alg.shortestpath.ALTAdmissibleHeuristic;
import org.jgrapht.alg.shortestpath.AStarShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import ua.com.alevel.task3.graph.WeightedGraph;


public class AStarPathFinder {

    public double getShortestPath(WeightedGraph weightedGraph, String source, String dest){
        AStarShortestPath<String, DefaultWeightedEdge> aStarShortestPath =
                new AStarShortestPath<>(weightedGraph.getGraph(),
                        new ALTAdmissibleHeuristic<>(weightedGraph.getGraph(), weightedGraph.getGraph().vertexSet()));

        return aStarShortestPath.getPathWeight(source,dest);
    }
}
