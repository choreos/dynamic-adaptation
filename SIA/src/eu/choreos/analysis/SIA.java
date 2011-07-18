package eu.choreos.analysis;

import eu.choreos.analysis.entity.CentralityAnalysis;
import eu.choreos.analysis.entity.StabilityResults;
import eu.choreos.middleware.entity.BehaviorProtocolAutomaton;
import eu.choreos.middleware.entity.ChoreographyModel;

public interface SIA {

	public StabilityResults calculateOverallStability(ChoreographyModel 
			choreographyModel);
	
	public StabilityResults calculateOverallStability(
			BehaviorProtocolAutomaton behaviorProtocolAutomaton);
	
	public CentralityAnalysis calculateCentrality(ChoreographyModel 
			choreographyModel);
	
	public CentralityAnalysis calculateCentrality(
			BehaviorProtocolAutomaton behaviorProtocolAutomaton);
	
}
