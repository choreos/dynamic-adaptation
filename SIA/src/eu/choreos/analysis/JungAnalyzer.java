package eu.choreos.analysis;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import edu.uci.ics.jung.algorithms.scoring.BetweennessCentrality;
import edu.uci.ics.jung.algorithms.scoring.ClosenessCentrality;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraDistance;
import edu.uci.ics.jung.algorithms.shortestpath.Distance;
import edu.uci.ics.jung.graph.DirectedGraph;
import eu.choreos.analysis.entity.CentralityAnalysis;
import eu.choreos.analysis.entity.CentralityResults;
import eu.choreos.analysis.entity.DegreeCentrality;
import eu.choreos.analysis.entity.StabilityResults;

public class JungAnalyzer<V, E> implements DependencyAnalyzer<V, E> {
	
	// TODO:  in this class we have some heavy computations
	// would be nice having some caching...
	// hint: JUNG API provides a Caching interface
	
	private DirectedGraph<V, E> graph;
	
	public JungAnalyzer() {
	}

	public JungAnalyzer(DirectedGraph<V, E> graph) {
		this.graph = graph;
	}

	public DirectedGraph<V, E> getGraph() {
		return graph;
	}

	public void setGraph(DirectedGraph<V, E> graph) {
		this.graph = graph;
	}

	
	public StabilityResults calculateStabilityAnalysis(){
		
		if (this.graph == null)
			throw new IllegalStateException("The graph should not be null");

		// this is the overall stability algorithm of the Gustavo's slides
		
		int impactAll = 0;
		Map<V, Integer> impact = new HashMap<V, Integer>();
		
		for (V v: this.graph.getVertices()) {
			
			int inpac = this.calculateImpact(v);
			impact.put(v, inpac);
			impactAll += inpac;
		}

		int numServices = this.graph.getVertexCount();
		double avgImpact = (double) impactAll / numServices;
		double percAvgImpact = (avgImpact / numServices) * 100;
		double overallStability = 100 - percAvgImpact;
		
		return new StabilityResults(overallStability);
	}
	
	// impact is transitive
	private int calculateImpact(V vertex) {
		
		int impact = 0;
		Distance<V> distance = new DijkstraDistance<V, E>(this.graph);
		for (V v: this.graph.getVertices()) {
			Number d = distance.getDistance(v, vertex);
			if (d != null && d.doubleValue() > 0)
				impact++;
		}
		return impact;
	}
	
	public CentralityAnalysis<V, E> calculateCentralityAnalysis(){
		
		if (this.graph == null)
			throw new IllegalStateException("The graph should not be null");		
		
		CentralityResults<V, E> centralityResults = new CentralityResults<V, E>(graph);
		centralityResults.setVerticesDegreeCentrality(this.calculateVerticesDegreeCentrality());
		centralityResults.setGraphDegreeCentrality(this.calculateGraphDegreeCentrality());
		centralityResults.setVerticesClosenessCentrality(this.calculateVerticesClosenessCentrality());
		centralityResults.setVerticesBetweenessCentrality(this.calculateVerticesBetweennessCentrality());
		
		return centralityResults;
	}
	
	
	
	private Map<V, DegreeCentrality> calculateVerticesDegreeCentrality() {
		
		Map<V, DegreeCentrality> vDegCen = new HashMap<V, DegreeCentrality>();
		int n = graph.getVertexCount();
		for (V v: graph.getVertices()) {
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
		
		Map<V, DegreeCentrality> centralities = calculateVerticesDegreeCentrality();
		double highestIn = getHighestInDegreeCentrality();
		double highestOut = getHighestOutDegreeCentrality();

		double in = 0, out = 0;
		for (V v: graph.getVertices()) {
			in += highestIn - centralities.get(v).getInCentrality();  
			out += highestOut - centralities.get(v).getOutCentrality();  
		}
		
		int den = (graph.getVertexCount() - 1) * (graph.getVertexCount() - 2);
		
		return new DegreeCentrality(in/den, out/den);
	}

	private Map<V, Double> calculateVerticesClosenessCentrality(){
		
		Map<V, Double> centralities = new HashMap<V, Double>();
		ClosenessCentrality<V, E> closenessCentrality = new ClosenessCentrality<V, E>(graph);
		for(V v : graph.getVertices()){
			centralities.put(v, closenessCentrality.getVertexScore(v));
		}
		
		return centralities;
	}
	
	private Map<V, Double> calculateVerticesBetweennessCentrality(){

		Map<V, Double> centralities = new HashMap<V, Double>();
		BetweennessCentrality<V, E> ranker = new BetweennessCentrality<V, E>(graph);
		for(V v : graph.getVertices()){
			centralities.put(v, ranker.getVertexScore(v));
		}

		return centralities;
	}
	
}
