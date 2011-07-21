package eu.choreos.analysis;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import edu.uci.ics.jung.algorithms.scoring.BetweennessCentrality;
import edu.uci.ics.jung.algorithms.scoring.ClosenessCentrality;
import edu.uci.ics.jung.graph.DirectedGraph;
import eu.choreos.analysis.entity.CentralityAnalysis;
import eu.choreos.analysis.entity.CentralityResults;
import eu.choreos.analysis.entity.DegreeCentrality;
import eu.choreos.analysis.entity.StabilityResults;
import eu.choreos.analysis.graph.Edge;
import eu.choreos.analysis.graph.Vertex;

public class JungAnalyzer implements DependencyAnalyzer {
	
	// TODO:  in this class we have some heavy computations
	// would be nice having some caching...
	// hint: JUNG API provides a Caching interface
	
	private DirectedGraph<Vertex, Edge> graph;
	
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

	
	public StabilityResults calculateStabilityAnalysis(){
		
		if (this.graph == null)
			throw new IllegalStateException("The graph should not be null");

		// this is the overall stability algorithm of the Gustavo's slides
		
		// TODO: impact should be transitive
		// think how to use Distance and ShortestPath JUNG features
		
		int impactAll = 0;
		Map<Vertex, Integer> impact = new HashMap<Vertex, Integer>();
		
		for (Vertex v: this.graph.getVertices()) {
			
			int inpac = this.graph.getPredecessorCount(v);
			impact.put(v, inpac);
			impactAll += inpac;
		}

		int numServices = this.graph.getVertexCount();
		double avgImpact = (double) impactAll / numServices;
		double percAvgImpact = (avgImpact / numServices) * 100;
		double overallStability = 100 - percAvgImpact;
		
		return new StabilityResults(overallStability);
	}
	
	public CentralityAnalysis calculateCentralityAnalysis(){
		
		if (this.graph == null)
			throw new IllegalStateException("The graph should not be null");		
		
		CentralityResults centralityResults = new CentralityResults(graph);
		centralityResults.setVerticesDegreeCentrality(this.calculateVerticesDegreeCentrality());
		centralityResults.setGraphDegreeCentrality(this.calculateGraphDegreeCentrality());
		centralityResults.setVerticesClosenessCentrality(this.calculateVerticesClosenessCentrality());
		centralityResults.setVerticesBetweenessCentrality(this.calculateVerticesBetweennessCentrality());
		
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

	private Map<Vertex, Double> calculateVerticesClosenessCentrality(){
		
		Map<Vertex, Double> centralities = new HashMap<Vertex, Double>();
		ClosenessCentrality<Vertex, Edge> closenessCentrality = new ClosenessCentrality<Vertex, Edge>(graph);
		for(Vertex v : graph.getVertices()){
			centralities.put(v, closenessCentrality.getVertexScore(v));
		}
		
		return centralities;
	}
	
	private Map<Vertex, Double> calculateVerticesBetweennessCentrality(){

		Map<Vertex, Double> centralities = new HashMap<Vertex, Double>();
		BetweennessCentrality<Vertex, Edge> ranker = new BetweennessCentrality<Vertex, Edge>(graph);
		for(Vertex v : graph.getVertices()){
			centralities.put(v, ranker.getVertexScore(v));
		}

		return centralities;
	}
	
}
