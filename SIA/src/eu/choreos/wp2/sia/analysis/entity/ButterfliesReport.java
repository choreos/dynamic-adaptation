package eu.choreos.wp2.sia.analysis.entity;

import java.util.Set;

import edu.uci.ics.jung.graph.DirectedGraph;
import eu.choreos.wp2.sia.analysis.detectors.ButterflyDetector;

public class ButterfliesReport<V,E> {
	
	private Set<V> localButterflies;
	private Set<V> globalButterflies;
	
	public ButterfliesReport(DirectedGraph<V, E> graph){
		ButterflyDetector<V, E> butterflyDetector = 
				new ButterflyDetector<V, E>(graph);
	
		this.localButterflies = 
				butterflyDetector.detectLocalButterflies();
		
		this.globalButterflies = 
				butterflyDetector.detectGlobalButterflies();
	}

	public Set<V> getLocalButterflies(){
		return localButterflies;
	}
	
	public Set<V> getGlobalButterflies(){
		return globalButterflies;
	}
}