package eu.choreos.analysis.graph;

public class Vertex {

	private long id;
	
	private String label;
	
	public long getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public Vertex(long id, String label) {
		this.id = id;
		this.label = label;
	}

}
