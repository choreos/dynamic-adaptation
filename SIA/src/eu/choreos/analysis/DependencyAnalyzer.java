package eu.choreos.analysis;

import java.util.Set;

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
	
	/**
	 * A local butterfly node owns a high in-degree
	 * @return
	 */
	public Set<V> getButterflyNodes(double inDegreeThreshold);

	/**
	 * A local sensitive node owns a high out-degree
	 * @return
	 */
	public Set<V> getSensitiveNodes(double outDegreeThreshold);
	
	/**
	 * A local hub node owns a high in-degree and a high out-degree at the same time
	 * @return
	 */
	public Set<V> getHubNodes(double degreeThreshold);
}
