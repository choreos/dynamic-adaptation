package eu.choreos.analysis;

import eu.choreos.analysis.entity.CentralityAnalysis;
import eu.choreos.analysis.entity.OverallStabilityResults;
import eu.choreos.middleware.entity.BehaviorProtocolAutomaton;
import eu.choreos.middleware.entity.ChoreographyModel;

public interface SIA {

	public OverallStabilityResults calculateOverallStability(ChoreographyModel 
			choreographyModel);
	
	public OverallStabilityResults calculateOverallStability(
			BehaviorProtocolAutomaton behaviorProtocolAutomaton);
	
	public CentralityAnalysis calculateCentrality(ChoreographyModel 
			choreographyModel);
	
	public CentralityAnalysis calculateCentrality(
			BehaviorProtocolAutomaton behaviorProtocolAutomaton);
	
}
