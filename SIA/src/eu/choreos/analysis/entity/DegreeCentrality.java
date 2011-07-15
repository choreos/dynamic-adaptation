package eu.choreos.analysis.entity;

/**
 * In a directed graph we remark the difference between
 * "in centrality", which considers the incoming edges of a vertex,
 * and "out centrality", which considers the outgoing edges of a vertex
 * 
 * @author leofl
 *
 */
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(inCentrality);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(outCentrality);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DegreeCentrality other = (DegreeCentrality) obj;
		if (Double.doubleToLongBits(inCentrality) != Double
				.doubleToLongBits(other.inCentrality))
			return false;
		if (Double.doubleToLongBits(outCentrality) != Double
				.doubleToLongBits(other.outCentrality))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DegreeCentrality [inCentrality=" + inCentrality
				+ ", outCentrality=" + outCentrality + "]";
	}
	
}
