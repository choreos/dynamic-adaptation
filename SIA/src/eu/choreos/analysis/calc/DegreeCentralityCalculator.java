package eu.choreos.analysis.calc;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import edu.uci.ics.jung.graph.DirectedGraph;
import eu.choreos.analysis.entity.DegreeCentrality;

public class DegreeCentralityCalculator<V, E> {

	DirectedGraph<V, E> graph;
	
	public DegreeCentralityCalculator(DirectedGraph<V, E> graph) {
		
		this.graph = graph;
	}
	
	public Map<V, DegreeCentrality> calculateVerticesDegreeCentrality() {
		
		Map<V, DegreeCentrality> vDegCen = new HashMap<V, DegreeCentrality>();
		int n = graph.getVertexCount();
		for (V v: graph.getVertices()) {
			double in = (double) graph.getInEdges(v).size() / (n-1);
			double out = (double) graph.getOutEdges(v).size() / (n-1);
			vDegCen.put(v, new DegreeCentrality(in, out));
		}
		
		return vDegCen;
	}	
	
	public DegreeCentrality calculateGraphDegreeCentrality() {
		
		Map<V, DegreeCentrality> centralities = calculateVerticesDegreeCentrality();
		double highestIn = getHighestInDegreeCentrality();
		double highestOut = getHighestOutDegreeCentrality();

		double in = 0, out = 0;
		for (V v: graph.getVertices()) {
			in += highestIn - centralities.get(v).getInCentrality();  
			out += highestOut - centralities.get(v).getOutCentrality();  
		}
		
		int den = (graph.getVertexCount() - 1) * (graph.getVertexCount() - 2);
		
		return new DegreeCentrality(in/den, out/den);
	}
	
	private double getHighestInDegreeCentrality() {
		
		Collection<DegreeCentrality> centralities = calculateVerticesDegreeCentrality().values();
		double highest = 0;
		for (DegreeCentrality dc: centralities) {
			if (dc.getInCentrality() > highest)
				highest = dc.getInCentrality();
		}
		
		return highest;
	}

	private double getHighestOutDegreeCentrality() {
		
		Collection<DegreeCentrality> centralities = calculateVerticesDegreeCentrality().values();
		double highest = 0;
		for (DegreeCentrality dc: centralities) {
			if (dc.getOutCentrality() > highest)
				highest = dc.getOutCentrality();
		}
		
		return highest;
	}

}
