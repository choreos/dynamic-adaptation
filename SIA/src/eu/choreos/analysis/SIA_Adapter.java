package eu.choreos.analysis;

import edu.uci.ics.jung.graph.DirectedGraph;
import eu.choreos.analysis.entity.CentralityAnalysis;
import eu.choreos.analysis.entity.OverallStabilityResults;
import eu.choreos.analysis.graph.Edge;
import eu.choreos.analysis.graph.Vertex;
import eu.choreos.middleware.entity.BehaviorProtocolAutomaton;
import eu.choreos.middleware.entity.ChoreographyModel;

public class SIA_Adapter implements SIA{

	private JungAnalyzer depAnalyzer;
	
	public SIA_Adapter(){
		depAnalyzer = new JungAnalyzer();
	}
	
	@Override
	public OverallStabilityResults calculateOverallStability(
			ChoreographyModel choreographyModel) {
		
		//TODO: Convert from a choreographyModel (BPMN2 in memory 
		//representation) to a directed graph		
		
		DirectedGraph<Vertex,Edge> graph = null;
		OverallStabilityResults results = 
			depAnalyzer.calculateOverallStability();
		
		return results;
	}

	@Override
	public OverallStabilityResults calculateOverallStability(
			BehaviorProtocolAutomaton behaviorProtocolAutomaton) {
		
		DirectedGraph<Vertex,Edge> graph = null;
		OverallStabilityResults results = 
			depAnalyzer.calculateOverallStability();
		
		return results;
	}

	@Override
	public CentralityAnalysis calculateCentrality(
			ChoreographyModel choreographyModel) {

		DirectedGraph<Vertex,Edge> graph = null;
		CentralityAnalysis results =	
			depAnalyzer.calculateCentralityAnalysis();
		
		return results;
	}

	@Override
	public CentralityAnalysis calculateCentrality(
			BehaviorProtocolAutomaton behaviorProtocolAutomaton) {
		
		DirectedGraph<Vertex,Edge> graph = null;
		CentralityAnalysis results =	
			depAnalyzer.calculateCentralityAnalysis();
		
		return results;
	}

	
}
