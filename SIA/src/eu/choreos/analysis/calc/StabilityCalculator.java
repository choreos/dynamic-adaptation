package eu.choreos.analysis.calc;

import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import eu.choreos.analysis.util.GraphUtils;

public class StabilityCalculator<V,E> {
	
	public double calculateOverallStability(DirectedGraph<V, E> graph) {
		
		if (graph == null)
			throw new IllegalStateException("The graph should not be null");

		// This is the algorithm described in 
		// www.ime.usp.br/~yoshi/2007i/mac328/Slides/2007.04.20/lecture.pdf
			
		DirectedSparseGraph<V, String> transitiveClosure = 
				new GraphUtils<V, E>().computeTransitiveClosure(graph); 
		
		int sumImpact = 0;
		for (V v : transitiveClosure.getVertices()){
			int impacted = transitiveClosure.getPredecessorCount(v);
			sumImpact += impacted;
		}
		
		int n = graph.getVertexCount();
		double avgImpact = (double) sumImpact / (n * n);
		double overallStability = 1 - avgImpact;
		
		return overallStability;
	}
	

}