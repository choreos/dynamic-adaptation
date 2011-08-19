package eu.choreos.analysis.calc;

import java.util.HashMap;
import java.util.Map;

import edu.uci.ics.jung.algorithms.scoring.ClosenessCentrality;
import edu.uci.ics.jung.graph.DirectedGraph;

public class ClosenessCentralityCalculator<V, E> {

	/**
	 * TODO: write closeness centrality equation
	 * @return
	 */
	public Map<V, Double> calculateVerticesClosenessCentrality(DirectedGraph<V, E> graph){
	
		Map<V, Double> centralities = new HashMap<V, Double>();
		ClosenessCentrality<V, E> ranker = new ClosenessCentrality<V, E>(graph);
		for(V v : graph.getVertices()){
			centralities.put(v, ranker.getVertexScore(v));
		}

		return centralities;
	}
	
}
