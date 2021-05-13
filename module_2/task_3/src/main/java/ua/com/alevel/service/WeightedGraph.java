package ua.com.alevel.service;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;

public class WeightedGraph {
    MutableValueGraph<Integer, Double> weightedGraph = ValueGraphBuilder.directed().build();

    weightedGraph.addNode(1);
    weightedGraph.putEdgeValue(2, 3, 1.5);  // also adds nodes 2 and 3 if not already present
    weightedGraph.putEdgeValue(3, 5, 1.5);  // edge values (like Map values) need not be unique

    weightedGraph.putEdgeValue(2, 3, 2.0);  // updates the value for (2,3) to 2.0


}
