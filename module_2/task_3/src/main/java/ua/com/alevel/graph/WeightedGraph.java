package ua.com.alevel.graph;


import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;


public class WeightedGraph {
   public WeightedMultigraph<String, DefaultWeightedEdge> graph = new WeightedMultigraph<>(DefaultWeightedEdge.class);

    public WeightedMultigraph<String, DefaultWeightedEdge> getGraph() {
        return graph;
    }
}
