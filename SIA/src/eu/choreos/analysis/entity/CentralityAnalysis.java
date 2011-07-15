package eu.choreos.analysis.entity;

import java.util.Map;

import edu.uci.ics.jung.graph.DirectedGraph;
import eu.choreos.analysis.graph.Edge;
import eu.choreos.analysis.graph.Vertex;

/**
 * You can understand these centralities here: http://en.wikipedia.org/wiki/Centrality 
 * 
 * @author leofl
 *
 */
public interface CentralityAnalysis {
	
	/**
	 * Returns the graph used to calculate the metrics
	 * @return the analyzed graph
	 */
	public DirectedGraph<Vertex, Edge> getAnalyzedGraph();
	
	/**
	 * Returns the degree centrality of the whole graph
	 * @return the graph degree centrality
	 */
	public DegreeCentrality getGraphDegreeCentrality();

	/**
	 * Returns the degree centrality of each vertex of the graph
	 * @return a map with the relation between each vertex and its degree centrality
	 */
	public Map<Vertex, DegreeCentrality> getVerticesDegreeCentrality();

	/**
	 * Returns the betweeness centrality of each vertex of the graph
	 * @return a map with the relation between each vertex and its betweeness centrality
	 */
	public Map<Vertex, Double> getVerticesBetweenessCentrality();

	/**
	 * Returns the closeness centrality of each vertex of the graph
	 * @return a map with the relation between each vertex and its closeness centrality
	 */
	public Map<Vertex, Double> getVerticesClosenessCentrality();

}
