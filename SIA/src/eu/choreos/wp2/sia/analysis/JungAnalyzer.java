package eu.choreos.wp2.sia.analysis;

import java.util.List;

import edu.uci.ics.jung.graph.DirectedGraph;
import eu.choreos.wp2.sia.analysis.entity.AntiPatternReport;
import eu.choreos.wp2.sia.graph.algorithms.StabilityCalculator;
import eu.choreos.wp3.middleware.entity.ChoreographyModel;
import eu.choreos.wp3.middleware.entity.CoordinationDelegate;

public class JungAnalyzer<V, E> implements SIA{
	
	@Override
	public Double calculateOverallStability(
			ChoreographyModel choreographyModel) {
		
		//TODO: Convert from a choreographyModel (BPMN2 in memory 
		//representation) to a directed graph		
		DirectedGraph<V,E> graph = null;
		
		StabilityCalculator<V, E> stabilityCalculator = 
				new StabilityCalculator<V,E>();
		
		Double stability = stabilityCalculator.calculateOverallStability(graph);		
		return stability;
	}

	@Override
	public Double calculateOverallStability(
			List<CoordinationDelegate> coordinationDelegates) {
		
		//TODO: Convert from a Coordination Delegates to a directed graph				
		DirectedGraph<V,E> graph = null;
				
		StabilityCalculator<V, E> stabilityCalculator = 
				new StabilityCalculator<V,E>();
			
		Double stability = stabilityCalculator.calculateOverallStability(graph);
		return stability;
	}
	
	public AntiPatternReport<V,E> findAntiPatterns(DirectedGraph<V, E> graph){
		
		//TODO: Convert from a Coordination Delegates to a directed graph				
		//DirectedGraph<V,E> graph = null;		
		
		AntiPatternReport<V,E> antiPatternsReport = 
				new AntiPatternReport<V,E>(graph);
		
		return antiPatternsReport;
	}
}