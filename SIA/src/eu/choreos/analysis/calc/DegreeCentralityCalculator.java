package eu.choreos.analysis.calc;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import edu.uci.ics.jung.graph.DirectedGraph;
import eu.choreos.analysis.entity.DegreeCentrality;

public class DegreeCentralityCalculator<V, E> {

	private final Comparator<DegreeCentrality> inDegreeComparator = new Comparator<DegreeCentrality>() {
		public int compare(DegreeCentrality dc1, DegreeCentrality dc2){
			return dc1.getInDegree().compareTo(dc2.getInDegree());
		}
	};
	
	private final Comparator<DegreeCentrality> outDegreeComparator = new Comparator<DegreeCentrality>() {
		public int compare(DegreeCentrality dc1, DegreeCentrality dc2){
			return dc1.getOutDegree().compareTo(dc2.getOutDegree());
		}
	};
	
	public Map<V, DegreeCentrality> calculateVerticesDegreeCentrality(DirectedGraph<V, E> graph) {
		
		Map<V, DegreeCentrality> vDegCen = new HashMap<V, DegreeCentrality>();
		int n = graph.getVertexCount();
		for (V v: graph.getVertices()) {
			double in = (double) graph.inDegree(v) / (n-1);
			double out = (double) graph.outDegree(v) / (n-1);
			vDegCen.put(v, new DegreeCentrality(in, out));
		}
		
		return vDegCen;
	}	
	
	public DegreeCentrality calculateGraphDegreeCentrality(DirectedGraph<V, E> graph) {
		
		Map<V, DegreeCentrality> centralities = calculateVerticesDegreeCentrality(graph);
		double highestIn = getHighestInDegreeCentrality(graph);
		double highestOut = getHighestOutDegreeCentrality(graph);

		double in = 0, out = 0;
		for (V v: graph.getVertices()) {
			in += highestIn - centralities.get(v).getInDegree();  
			out += highestOut - centralities.get(v).getOutDegree();  
		}
		
		int den = (graph.getVertexCount() - 1) * (graph.getVertexCount() - 2);
		
		return new DegreeCentrality(in/den, out/den);
	}
	
	private double getHighestInDegreeCentrality(DirectedGraph<V, E> graph) {
		
		Collection<DegreeCentrality> centralities = calculateVerticesDegreeCentrality(graph).values();
		DegreeCentrality dc = Collections.max(centralities, inDegreeComparator);
		return dc.getInDegree();
	}

	private double getHighestOutDegreeCentrality(DirectedGraph<V, E> graph) {
		
		Collection<DegreeCentrality> centralities = calculateVerticesDegreeCentrality(graph).values();
		DegreeCentrality dc = Collections.max(centralities, outDegreeComparator);
		return dc.getOutDegree();
	}

}