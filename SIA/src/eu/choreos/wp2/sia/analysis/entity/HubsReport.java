package eu.choreos.wp2.sia.analysis.entity;

import java.util.Set;

import edu.uci.ics.jung.graph.DirectedGraph;
import eu.choreos.wp2.sia.analysis.detectors.HubDetector;

public class HubsReport<V,E> {

	private Set<V> localHubs;
	private Set<V> globalHubs;
	
	public HubsReport(DirectedGraph<V,E> graph){
		
		HubDetector<V, E> hubDetector = 
				new HubDetector<V,E>(graph);
	
		this.localHubs = hubDetector.detectLocalHubs();
		this.globalHubs = hubDetector.detectGlobalHubs();
	}

	public Set<V> getLocalHubs(){
		return localHubs;
	}
	
	public Set<V> getGlobalHubs(){
		return globalHubs;
	}
	
}
