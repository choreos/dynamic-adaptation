package eu.choreos.analysis.calc;

import java.util.HashMap;
import java.util.Map;

import edu.uci.ics.jung.algorithms.scoring.BetweennessCentrality;
import edu.uci.ics.jung.graph.DirectedGraph;

public class BetweennessCentralityCalculator<V, E> {
	
	/**
	 * TODO: write closeness centrality equation
	 * @return
	 */
	public Map<V, Double> calculateVerticesBetweennessCentrality(DirectedGraph<V, E> graph){

		Map<V, Double> centralities = new HashMap<V, Double>();
		BetweennessCentrality<V, E> ranker = new BetweennessCentrality<V, E>(graph);
		for(V v : graph.getVertices()){
			centralities.put(v, ranker.getVertexScore(v));
		}

		return centralities;
	}
}
