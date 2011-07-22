package eu.choreos.analysis;

import edu.uci.ics.jung.graph.DirectedGraph;
import eu.choreos.analysis.entity.CentralityAnalysis;
import eu.choreos.analysis.entity.StabilityResults;
import eu.choreos.middleware.entity.BehaviorProtocolAutomaton;
import eu.choreos.middleware.entity.ChoreographyModel;

public class SIA_Adapter<V, E> implements SIA{

	private JungAnalyzer<V, E> depAnalyzer;
	
	public SIA_Adapter(){
		depAnalyzer = new JungAnalyzer<V, E>();
	}
	
	@Override
	public StabilityResults calculateOverallStability(
			ChoreographyModel choreographyModel) {
		
		//TODO: Convert from a choreographyModel (BPMN2 in memory 
		//representation) to a directed graph		
		
		DirectedGraph<V,E> graph = null;
		StabilityResults results = 
			depAnalyzer.calculateStabilityAnalysis();
		
		return results;
	}

	@Override
	public StabilityResults calculateOverallStability(
			BehaviorProtocolAutomaton behaviorProtocolAutomaton) {
		
		DirectedGraph<V,E> graph = null;
		StabilityResults results = 
			depAnalyzer.calculateStabilityAnalysis();
		
		return results;
	}

	@Override
	public CentralityAnalysis<V, E> calculateCentrality(
			ChoreographyModel choreographyModel) {

		DirectedGraph<V,E> graph = null;
		CentralityAnalysis results =	
			depAnalyzer.calculateCentralityAnalysis();
		
		return results;
	}

	@Override
	public CentralityAnalysis<V, E> calculateCentrality(
			BehaviorProtocolAutomaton behaviorProtocolAutomaton) {
		
		DirectedGraph<V,E> graph = null;
		CentralityAnalysis results =	
			depAnalyzer.calculateCentralityAnalysis();
		
		return results;
	}

	
}
