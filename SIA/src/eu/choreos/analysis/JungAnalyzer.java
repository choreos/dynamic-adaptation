package eu.choreos.analysis;

import edu.uci.ics.jung.algorithms.importance.BetweennessCentrality;
import edu.uci.ics.jung.algorithms.scoring.ClosenessCentrality;
import edu.uci.ics.jung.graph.DirectedGraph;
import eu.choreos.analysis.entity.CentralityResults;
import eu.choreos.analysis.entity.OverallStabilityResults;
import eu.choreos.analysis.graph.Edge;
import eu.choreos.analysis.graph.Vertex;

public class JungAnalyzer implements DependencyAnalyzer {
	
	public JungAnalyzer(){
		
	}

	public OverallStabilityResults calculateOverallStability(DirectedGraph<Vertex, Edge> graph){
		return null;		
	}
	
	public CentralityResults calculateCentralityMeasures(DirectedGraph<Vertex, Edge> graph){
		CentralityResults centralityResults = new CentralityResults(null);
		return centralityResults;
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
