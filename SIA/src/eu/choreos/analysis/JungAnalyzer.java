package eu.choreos.analysis;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import edu.uci.ics.jung.algorithms.importance.BetweennessCentrality;
import edu.uci.ics.jung.algorithms.scoring.ClosenessCentrality;
import edu.uci.ics.jung.graph.DirectedGraph;
import eu.choreos.analysis.entity.CentralityAnalysis;
import eu.choreos.analysis.entity.CentralityResults;
import eu.choreos.analysis.entity.DegreeCentrality;
import eu.choreos.analysis.entity.OverallStabilityResults;
import eu.choreos.analysis.graph.Edge;
import eu.choreos.analysis.graph.Vertex;

public class JungAnalyzer implements DependencyAnalyzer {
	
	// TODO:  in this class we have some heavy computations
	// would be nice having some caching...
	
	DirectedGraph<Vertex, Edge> graph;
	
	public JungAnalyzer() {
	}

	public JungAnalyzer(DirectedGraph<Vertex, Edge> graph) {
		this.graph = graph;
	}

	public DirectedGraph<Vertex, Edge> getGraph() {
		return graph;
	}

	public void setGraph(DirectedGraph<Vertex, Edge> graph) {
		this.graph = graph;
	}

	public OverallStabilityResults calculateOverallStability(){
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public CentralityAnalysis calculateCentralityAnalysis(){
		
		CentralityResults centralityResults = new CentralityResults(graph);
		centralityResults.setVerticesDegreeCentrality(this.calculateVerticesDegreeCentrality());
		centralityResults.setGraphDegreeCentrality(this.calculateGraphDegreeCentrality());
		
		return centralityResults;
	}
	
	
	
	private Map<Vertex, DegreeCentrality> calculateVerticesDegreeCentrality() {
		
		Map<Vertex, DegreeCentrality> vDegCen = new HashMap<Vertex, DegreeCentrality>();
		
		int n = graph.getVertexCount();
		for (Vertex v: graph.getVertices()) {
			double in = (double) graph.getInEdges(v).size() / (n-1);
			double out = (double) graph.getOutEdges(v).size() / (n-1);
			vDegCen.put(v, new DegreeCentrality(in, out));
		}
		
		return vDegCen;
	}
	
	private double getHighestInDegreeCentrality() {
		
		Collection<DegreeCentrality> centralities = calculateVerticesDegreeCentrality().values();
		
		double highest = 0;
		for (DegreeCentrality dc: centralities) {
			if (dc.getInCentrality() > highest)
				highest = dc.getInCentrality();
		}
		
		return highest;
	}

	private double getHighestOutDegreeCentrality() {
		
		Collection<DegreeCentrality> centralities = calculateVerticesDegreeCentrality().values();
		
		double highest = 0;
		for (DegreeCentrality dc: centralities) {
			if (dc.getOutCentrality() > highest)
				highest = dc.getOutCentrality();
		}
		
		return highest;
	}

	private DegreeCentrality calculateGraphDegreeCentrality() {
		
		Map<Vertex, DegreeCentrality> centralities = calculateVerticesDegreeCentrality();
		double highestIn = getHighestInDegreeCentrality();
		double highestOut = getHighestOutDegreeCentrality();

		double in = 0, out = 0;
		for (Vertex v: graph.getVertices()) {
			in += highestIn - centralities.get(v).getInCentrality();  
			out += highestOut - centralities.get(v).getOutCentrality();  
		}
		
		int den = (graph.getVertexCount() - 1) * (graph.getVertexCount() - 2);
		
		return new DegreeCentrality(in/den, out/den);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	private void calculateCloseness(DirectedGraph<Vertex, Edge> graph){
		ClosenessCentrality closenessCentrality = new ClosenessCentrality(graph);
		for(Vertex v : graph.getVertices()){
			closenessCentrality.getVertexScore(v);
		}
	}
	
	private void calculateBetweenness(DirectedGraph<Vertex, Edge> graph){
		BetweennessCentrality ranker = new BetweennessCentrality(graph);
		ranker.evaluate();
	}
}
