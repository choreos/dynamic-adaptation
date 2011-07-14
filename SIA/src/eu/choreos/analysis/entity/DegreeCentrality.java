package eu.choreos.analysis.entity;

public class DegreeCentrality {

	private final double inCentrality;
	private final double outCentrality;
	// there could be a "total centrality"?

	public DegreeCentrality(double inCentrality, double outCentrality) {
		this.inCentrality = inCentrality;
		this.outCentrality = outCentrality;
	}

	public double getInCentrality() {
		return inCentrality;
	}

	public double getOutCentrality() {
		return outCentrality;
	}
	
}
