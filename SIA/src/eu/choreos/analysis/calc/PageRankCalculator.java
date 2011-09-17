package eu.choreos.analysis.calc;

import java.util.HashMap;
import java.util.Map;

import edu.uci.ics.jung.algorithms.scoring.PageRank;
import edu.uci.ics.jung.graph.DirectedGraph;

public class PageRankCalculator<V, E> {

	public Map<V, Double> calculatePageRank(DirectedGraph<V, E> graph){

		Map<V, Double> pageRank = new HashMap<V, Double>();
		
		PageRank<V,E> ranker = new PageRank<V,E>(graph, 1);
		ranker.initialize();
		ranker.evaluate();
		for (V v: graph.getVertices()) {
			pageRank.put(v, ranker.getVertexScore(v));
		}
		return pageRank;
	}
	
}
