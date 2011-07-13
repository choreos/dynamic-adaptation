package eu.choreos.analysis.graph;

public class Edge {

	private long id;
	private String label;
	private double weight;
	
	public long getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(long weight) {
		this.weight = weight;
	}

	public Edge(long id) {
		this.id = id;
	}
	
	public Edge(long id, String label) {
		this.id = id;
		this.label = label;
	}
	
	public Edge(long id, double weight) {
		this.id = id;
		this.weight = weight;
	}

	public Edge(long id, String label, double weight) {
		this.id = id;
		this.label = label;
		this.weight = weight;
	}

	
}
