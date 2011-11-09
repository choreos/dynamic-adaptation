package eu.choreos.wp2.sia.visualization.sample;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

	private String label;
	private List<AntiPattern> listAntiPattern;
	
	public Vertex(String label){
		this.label = label;
		this.listAntiPattern = new ArrayList<AntiPattern>();
	}
	
	public void addAntiPattern(AntiPattern antiPattern){
		this.listAntiPattern.add(antiPattern);
	}
	
	public List<AntiPattern> getListAntiPattern(){
		return listAntiPattern;
	}
	
	public boolean equals(Vertex other){
		return this.label.equals(other.label);
	}
	
	public String toString(){
		return this.label;
	}
	
}
