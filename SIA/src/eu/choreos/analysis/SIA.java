package eu.choreos.analysis;

import eu.choreos.analysis.entity.CentralityResults;
import eu.choreos.analysis.entity.OverallStabilityResults;
import eu.choreos.middleware.entity.BehaviorProtocolAutomaton;
import eu.choreos.middleware.entity.ChoreographyModel;

public interface SIA {

	public OverallStabilityResults calculateOverallStability(ChoreographyModel 
			choreographyModel);
	
	public OverallStabilityResults calculateOverallStability(
			BehaviorProtocolAutomaton behaviorProtocolAutomaton);
	
	public CentralityResults calculateCentrality(ChoreographyModel 
			choreographyModel);
	
	public CentralityResults calculateCentrality(
			BehaviorProtocolAutomaton behaviorProtocolAutomaton);
	
}
