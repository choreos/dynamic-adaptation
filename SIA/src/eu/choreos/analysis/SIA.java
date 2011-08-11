package eu.choreos.analysis;

import java.util.List;

import eu.choreos.analysis.entity.CentralityAnalysis;
import eu.choreos.analysis.entity.StabilityResults;
import eu.choreos.middleware.entity.CoordinationDelegate;
import eu.choreos.middleware.entity.ChoreographyModel;

public interface SIA {

	public StabilityResults calculateOverallStability(ChoreographyModel 
			choreographyModel);
	
	public StabilityResults calculateOverallStability(
			List<CoordinationDelegate> coordinatinoDelegates);
	
	public CentralityAnalysis calculateCentrality(ChoreographyModel 
			choreographyModel);
	
	public CentralityAnalysis calculateCentrality(
			List<CoordinationDelegate> coordinatinoDelegates);
	
}
