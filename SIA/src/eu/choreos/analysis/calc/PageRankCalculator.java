package eu.choreos.analysis.calc;

import java.util.HashMap;
import java.util.Map;

import edu.uci.ics.jung.algorithms.scoring.ClosenessCentrality;
import edu.uci.ics.jung.graph.DirectedGraph;

public class PageRankCalculator<V, E> {

	/**
	 * TODO: write pagerank centrality equation
	 * @return
	 */
	public Map<V, Double> calculateVerticesPageRankCentrality(DirectedGraph<V, E> graph){
	
		Map<V, Double> centralities = new HashMap<V, Double>();
		ClosenessCentrality<V, E> ranker = new ClosenessCentrality<V, E>(graph);
		for(V v : graph.getVertices()){
			centralities.put(v, ranker.getVertexScore(v));
		}

		return centralities;
	}
	
	public Double calculateVertexPageRankCentrality(DirectedGraph<V, E> graph, V vertex){
		
		ClosenessCentrality<V, E> ranker = new ClosenessCentrality<V, E>(graph);
		Double vertexPageRank = ranker.getVertexScore(vertex);
		
		return vertexPageRank;
	}
	
}
