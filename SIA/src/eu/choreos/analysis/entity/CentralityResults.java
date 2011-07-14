package eu.choreos.analysis.entity;

import java.util.Collections;
import java.util.Map;

import edu.uci.ics.jung.graph.DirectedGraph;
import eu.choreos.analysis.graph.Edge;
import eu.choreos.analysis.graph.Vertex;

public class CentralityResults implements CentralityAnalysis {

	private final DirectedGraph<Vertex, Edge> graph;
	private DegreeCentrality graphDegreeCentrality;
	private Map<Vertex, DegreeCentrality> verticesDegreeCentrality;
	private Map<Vertex, Double> verticesBetweenessCentrality;
	private Map<Vertex, Double> verticesClosenessCentrality;
	
	public CentralityResults(DirectedGraph<Vertex, Edge> graph) {
		this.graph = graph;
	}
	
	@Override
	public DirectedGraph<Vertex, Edge> getAnalyzedGraph() {
		return graph;
	}

	@Override
	public DegreeCentrality getGraphDegreeCentrality() {
		return graphDegreeCentrality;
	}

	@Override
	public Map<Vertex, DegreeCentrality> getVerticesDegreeCentrality() {
		return Collections.unmodifiableMap(verticesDegreeCentrality);
	}

	@Override
	public Map<Vertex, Double> getVerticesBetweenessCentrality() {
		return Collections.unmodifiableMap(verticesBetweenessCentrality);
	}

	@Override
	public Map<Vertex, Double> getVerticesClosenessCentrality() {
		return Collections.unmodifiableMap(verticesClosenessCentrality);
	}

	
	public void setGraphDegreeCentrality(DegreeCentrality graphDegreeCentrality) {
		this.graphDegreeCentrality = graphDegreeCentrality;
	}

	public void setVerticesDegreeCentrality(
			Map<Vertex, DegreeCentrality> verticesDegreeCentrality) {
		this.verticesDegreeCentrality = verticesDegreeCentrality;
	}

	public void setVerticesBetweenessCentrality(
			Map<Vertex, Double> verticesBetweenessCentrality) {
		this.verticesBetweenessCentrality = verticesBetweenessCentrality;
	}

	public void setVerticesClosenessCentrality(
			Map<Vertex, Double> verticesClosenessCentrality) {
		this.verticesClosenessCentrality = verticesClosenessCentrality;
	}
}
