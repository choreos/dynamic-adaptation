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
	
	public DirectedGraph<Vertex, Edge> getAnalyzedGraph();
	
	public DegreeCentrality getGraphDegreeCentrality();

	public Map<Vertex, DegreeCentrality> getVerticesDegreeCentrality();

	public Map<Vertex, Double> getVerticesBetweenessCentrality();

	public Map<Vertex, Double> getVerticesClosenessCentrality();

}
