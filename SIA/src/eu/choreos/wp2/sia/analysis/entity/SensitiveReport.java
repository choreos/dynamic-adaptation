package eu.choreos.wp2.sia.analysis.entity;

import java.util.Set;

import edu.uci.ics.jung.graph.DirectedGraph;
import eu.choreos.wp2.sia.analysis.detectors.HubDetector;
import eu.choreos.wp2.sia.analysis.detectors.SensitiveDetector;

public class SensitiveReport<V,E> {
	
	private Set<V> localSensitive;
	private Set<V> globalSensitive;
	
	public SensitiveReport(DirectedGraph<V,E> graph){
		
		SensitiveDetector<V, E> sensitiveDetector = 
				new SensitiveDetector<V,E>(graph);
	
		this.localSensitive = sensitiveDetector.detectLocalSensitives();
		this.globalSensitive = sensitiveDetector.detectGlobalSensitives();
	}

	public Set<V> getLocalSensitive(){
		return localSensitive;
	}
	
	public Set<V> getGlobalSensitive(){
		return globalSensitive;
	}

}
