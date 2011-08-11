package eu.choreos.analysis;

import java.util.List;

import edu.uci.ics.jung.graph.DirectedGraph;
import eu.choreos.analysis.entity.CentralityAnalysis;
import eu.choreos.analysis.entity.StabilityAnalysis;
import eu.choreos.middleware.entity.ChoreographyModel;
import eu.choreos.middleware.entity.CoordinationDelegate;

public class SIA_Adapter<V, E> implements SIA{

	private JungAnalyzer<V, E> depAnalyzer;
	
	public SIA_Adapter(){
		depAnalyzer = new JungAnalyzer<V, E>();
	}
	
	@Override
	public StabilityAnalysis calculateOverallStability(
			ChoreographyModel choreographyModel) {
		
		//TODO: Convert from a choreographyModel (BPMN2 in memory 
		//representation) to a directed graph		
		
		DirectedGraph<V,E> graph = null;
		StabilityAnalysis results = 
			depAnalyzer.calculateStabilityAnalysis();
		
		return results;
	}

	@Override
	public StabilityAnalysis calculateOverallStability(
			List<CoordinationDelegate> coordinatinoDelegates) {
		
		DirectedGraph<V,E> graph = null;
		StabilityAnalysis results = 
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
			List<CoordinationDelegate> coordinatinoDelegates) {
		
		DirectedGraph<V,E> graph = null;
		CentralityAnalysis results =	
			depAnalyzer.calculateCentralityAnalysis();
		
		return results;
	}

	
}
