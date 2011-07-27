package eu.choreos.analysis.entity;

import java.util.Map;

import edu.uci.ics.jung.graph.DirectedGraph;

/**
 * You can understand these centralities here: http://en.wikipedia.org/wiki/Centrality 
 * 
 * @author leofl
 *
 */
public interface CentralityAnalysis<V, E> {
	
	/**
	 * Returns the graph used to calculate the metrics
	 * @return the analyzed graph
	 */
	public DirectedGraph<V, E> getAnalyzedGraph();
	
	/**
	 * Returns the degree centrality of the whole graph
	 * @return the graph degree centrality
	 */
	public DegreeCentrality getGraphDegreeCentrality();

	/**
	 * Returns the degree centrality of each vertex of the graph
	 * @return a map with the relation between each vertex and its degree centrality
	 */
	public Map<V, DegreeCentrality> getVerticesDegreeCentrality();

	/**
	 * Returns the betweeness centrality of each vertex of the graph
	 * @return a map with the relation between each vertex and its betweeness centrality
	 */
	public Map<V, Double> getVerticesBetweenessCentrality();

	/**
	 * Returns the closeness centrality of each vertex of the graph
	 * @return a map with the relation between each vertex and its closeness centrality
	 */
	public Map<V, Double> getVerticesClosenessCentrality();
	
	/**
	 * Returns the page rank of each vertex of the graph.
	 * The page rank is a variant of the Eigenvector centrality for directed graphs
	 * @return a map with the relation between each vertex and its page rank
	 */	
	public Map<V, Double> getVerticesPageRank();

}
