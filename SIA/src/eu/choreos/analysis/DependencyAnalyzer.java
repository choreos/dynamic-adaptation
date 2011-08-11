package eu.choreos.analysis;

import edu.uci.ics.jung.graph.DirectedGraph;
import eu.choreos.analysis.entity.CentralityAnalysis;
import eu.choreos.analysis.entity.StabilityAnalysis;

public interface DependencyAnalyzer<V, E> {

	/**
	 * Sets the graph that will be used on further computations
	 * @param graph
	 */
	public void setGraph(DirectedGraph<V, E> graph);
	
	public DirectedGraph<V, E> getGraph();
	
	/**
	 * Calculates stability metrics of the given graph
	 * @return
	 */
	public StabilityAnalysis calculateStabilityAnalysis();
	
	/**
	 * Calculates centrality metrics of the given graph
	 * @return
	 */
	public CentralityAnalysis<V, E> calculateCentralityAnalysis();
}
