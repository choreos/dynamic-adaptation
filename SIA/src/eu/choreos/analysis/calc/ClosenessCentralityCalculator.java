package eu.choreos.analysis.calc;

import java.util.HashMap;
import java.util.Map;

import edu.uci.ics.jung.algorithms.scoring.ClosenessCentrality;
import edu.uci.ics.jung.graph.DirectedGraph;

public class ClosenessCentralityCalculator<V, E> {

	DirectedGraph<V, E> graph;
	
	public ClosenessCentralityCalculator(DirectedGraph<V, E> graph) {
		
		this.graph = graph;
	}
	
	public Map<V, Double> calculateVerticesClosenessCentrality(){
		
		Map<V, Double> centralities = new HashMap<V, Double>();
		ClosenessCentrality<V, E> closenessCentrality = new ClosenessCentrality<V, E>(graph);
		for(V v : graph.getVertices()){
			centralities.put(v, closenessCentrality.getVertexScore(v));
		}
		
		return centralities;
	}
}
