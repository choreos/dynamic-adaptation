package eu.choreos.analysis.entity;

public class StabilityResults implements StabilityAnalysis {

	private final double overallStability;
	
	public StabilityResults(double overallStability){
		this.overallStability = overallStability;
	}
	
	public double getOverallStability() {
		return overallStability;
	}

	@Override
	public String toString() {
		return String.valueOf("Overall Stability = " + overallStability);
	}
	
}
