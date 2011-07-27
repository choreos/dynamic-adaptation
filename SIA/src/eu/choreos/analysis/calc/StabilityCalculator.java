package eu.choreos.analysis.calc;

import java.util.HashMap;
import java.util.Map;

import edu.uci.ics.jung.algorithms.shortestpath.DijkstraDistance;
import edu.uci.ics.jung.algorithms.shortestpath.Distance;
import edu.uci.ics.jung.graph.DirectedGraph;

public class StabilityCalculator<V,E> {

	DirectedGraph<V, E> graph;
	
	public StabilityCalculator(DirectedGraph<V, E> graph) {
		
		this.graph = graph;
	}
	
	public double calculateOverallStability() {
		
		if (this.graph == null)
			throw new IllegalStateException("The graph should not be null");

		// this is the overall stability algorithm of the Gustavo's slides
		
		int impactAll = 0;
		Map<V, Integer> impact = new HashMap<V, Integer>();
		
		for (V v: this.graph.getVertices()) {
			
			int inpac = this.calculateImpact(v);
			impact.put(v, inpac);
			impactAll += inpac;
		}

		int numServices = this.graph.getVertexCount();
		double avgImpact = (double) impactAll / (numServices*numServices);
		double overallStability = 1 - avgImpact;
		
		return overallStability;
	}
	
	// impact is transitive
	private int calculateImpact(V vertex) {
		
		int impact = 0;
		Distance<V> distance = new DijkstraDistance<V, E>(this.graph);
		for (V v: this.graph.getVertices()) {
			Number d = distance.getDistance(v, vertex);
			if (d != null && d.doubleValue() > 0)
				impact++;
		}
		return impact;
	}	
}
