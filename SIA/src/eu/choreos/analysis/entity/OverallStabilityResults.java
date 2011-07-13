package eu.choreos.analysis.entity;

public class OverallStabilityResults {

	public double value;
	
	public OverallStabilityResults(double value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}
	
}
