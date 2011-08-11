package eu.choreos.analysis.calc;

import java.util.HashMap;
import java.util.Map;

import edu.uci.ics.jung.algorithms.shortestpath.DijkstraDistance;
import edu.uci.ics.jung.algorithms.shortestpath.Distance;
import edu.uci.ics.jung.graph.DirectedGraph;

public class ClosenessCentralityCalculator<V, E> {

	DirectedGraph<V, E> graph;
	
	public ClosenessCentralityCalculator(DirectedGraph<V, E> graph) {
		
		this.graph = graph;
	}
	
	public Map<V, Double> calculateVerticesClosenessCentrality(){
		
		Map<V, Double> centralities = new HashMap<V, Double>();
		for(V v : graph.getVertices()){
			centralities.put(v, calculateVertexClosenessCentrality(v));
		}
		
		return centralities;
	}
	
	public double calculateVertexClosenessCentrality(V vertex){
	
		double jv = 0; // the number of reachable vertices from vertex
		double n = graph.getVertexCount(); // number of vertices in the graph
		double sum = 0; // sum of the shortest paths from vertex to each reachable vertex
		
		Distance<V> distance = new DijkstraDistance<V, E>(this.graph);
		for (V v: graph.getVertices()) {
			
			Number d = distance.getDistance(vertex, v);
			if (!vertex.equals(v) && d != null) {
				sum += d.doubleValue();
				jv++;
			}
		}
		
		return jv*jv / ((n-1) * sum);
	}
}
