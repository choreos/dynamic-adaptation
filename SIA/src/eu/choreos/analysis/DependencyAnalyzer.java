package eu.choreos.analysis;

import edu.uci.ics.jung.graph.DirectedGraph;
import eu.choreos.analysis.entity.CentralityAnalysis;
import eu.choreos.analysis.entity.OverallStabilityResults;
import eu.choreos.analysis.graph.Edge;
import eu.choreos.analysis.graph.Vertex;

public interface DependencyAnalyzer {

	/**
	 * Sets the graph that will be used on further computations
	 * @param graph
	 */
	public void setGraph(DirectedGraph<Vertex, Edge> graph);
	
	public DirectedGraph<Vertex, Edge> getGraph();
	
	/**
	 * Calculates stability metrics of the given graph
	 * @return
	 */
	public OverallStabilityResults calculateOverallStability();
	
	/**
	 * Calculates centrality metrics of the given graph
	 * @return
	 */
	public CentralityAnalysis calculateCentralityAnalysis();
}
