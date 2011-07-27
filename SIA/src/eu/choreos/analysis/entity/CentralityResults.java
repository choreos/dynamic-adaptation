package eu.choreos.analysis.entity;

import java.util.Collections;
import java.util.Map;

import edu.uci.ics.jung.graph.DirectedGraph;

public class CentralityResults<V, E> implements CentralityAnalysis<V, E> {

	private final DirectedGraph<V, E> graph;
	private DegreeCentrality graphDegreeCentrality;
	private Map<V, DegreeCentrality> verticesDegreeCentrality;
	private Map<V, Double> verticesBetweenessCentrality;
	private Map<V, Double> verticesClosenessCentrality;	
	private Map<V, Double> verticesPageRank;
	
	public CentralityResults(DirectedGraph<V, E> graph) {
		this.graph = graph;
	}
	
	@Override
	public DirectedGraph<V, E> getAnalyzedGraph() {
		return graph;
	}

	@Override
	public DegreeCentrality getGraphDegreeCentrality() {
		return graphDegreeCentrality;
	}

	@Override
	public Map<V, DegreeCentrality> getVerticesDegreeCentrality() {
		return Collections.unmodifiableMap(verticesDegreeCentrality);
	}

	@Override
	public Map<V, Double> getVerticesBetweenessCentrality() {
		return Collections.unmodifiableMap(verticesBetweenessCentrality);
	}

	@Override
	public Map<V, Double> getVerticesClosenessCentrality() {
		return Collections.unmodifiableMap(verticesClosenessCentrality);
	}

	@Override
	public Map<V, Double> getVerticesPageRank() {
		return Collections.unmodifiableMap(verticesPageRank);
	}

	public void setGraphDegreeCentrality(DegreeCentrality graphDegreeCentrality) {
		this.graphDegreeCentrality = graphDegreeCentrality;
	}

	public void setVerticesDegreeCentrality(
			Map<V, DegreeCentrality> verticesDegreeCentrality) {
		this.verticesDegreeCentrality = verticesDegreeCentrality;
	}

	public void setVerticesBetweenessCentrality(
			Map<V, Double> verticesBetweenessCentrality) {
		this.verticesBetweenessCentrality = verticesBetweenessCentrality;
	}

	public void setVerticesClosenessCentrality(
			Map<V, Double> verticesClosenessCentrality) {
		this.verticesClosenessCentrality = verticesClosenessCentrality;
	}
	
	public void setVerticesPageRank(
			Map<V, Double> verticesPageRank) {
		this.verticesPageRank = verticesPageRank;
	}	
}
