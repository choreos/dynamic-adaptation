package eu.choreos.analysis.entity;

public class CentralityResults {

	private double betweenessCentrality;
	
	private double closenessCentrality;
	
	private double degreeCentrality;
	
	public double getBetweenessCentrality() {
		return betweenessCentrality;
	}
	
	public void setBetweenessCentrality(double betweenessCentrality) {
		this.betweenessCentrality = betweenessCentrality;
	}
	
	public double getClosenessCentrality() {
		return closenessCentrality;
	}
	
	public void setClosenessCentrality(double closenessCentrality) {
		this.closenessCentrality = closenessCentrality;
	}
	
	public double getDegreeCentrality() {
		return degreeCentrality;
	}
	
	public void setDegreeCentrality(double degreeCentrality) {
		this.degreeCentrality = degreeCentrality;
	}
	
}
