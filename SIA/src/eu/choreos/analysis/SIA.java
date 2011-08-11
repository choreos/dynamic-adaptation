package eu.choreos.analysis;

import java.util.List;

import eu.choreos.analysis.entity.CentralityAnalysis;
import eu.choreos.analysis.entity.StabilityAnalysis;
import eu.choreos.middleware.entity.ChoreographyModel;
import eu.choreos.middleware.entity.CoordinationDelegate;

public interface SIA {

	public StabilityAnalysis calculateOverallStability(ChoreographyModel 
			choreographyModel);
	
	public StabilityAnalysis calculateOverallStability(
			List<CoordinationDelegate> coordinatinoDelegates);
	
	public CentralityAnalysis calculateCentrality(ChoreographyModel 
			choreographyModel);
	
	public CentralityAnalysis calculateCentrality(
			List<CoordinationDelegate> coordinatinoDelegates);
	
}
