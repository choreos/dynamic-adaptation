package eu.choreos.analysis;

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
	
	public JungAnalyzer(){
		
	}

	public OverallStabilityResults calculateOverallStability(DirectedGraph<Vertex, Edge> graph){
		return null;		
	}
	
	public CentralityAnalysis calculateCentralityAnalysis(DirectedGraph<Vertex, Edge> graph){
		
		CentralityResults centralityResults = new CentralityResults(graph);
		centralityResults.setVerticesDegreeCentrality(this.calculateVerticesDegreeCentrality(graph));
		
		return centralityResults;
	}
	
	
	
	private Map<Vertex, DegreeCentrality> calculateVerticesDegreeCentrality(DirectedGraph<Vertex, Edge> graph) {
		
		Map<Vertex, DegreeCentrality> vDegCen = new HashMap<Vertex, DegreeCentrality>();
		
		int n = graph.getVertexCount();
		for (Vertex v: graph.getVertices()) {
			double in = (double) graph.getInEdges(v).size() / (n-1);
			double out = (double) graph.getOutEdges(v).size() / (n-1);
			vDegCen.put(v, new DegreeCentrality(in, out));
		}
		
		return vDegCen;
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
