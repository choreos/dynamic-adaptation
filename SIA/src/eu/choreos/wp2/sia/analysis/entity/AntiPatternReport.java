package eu.choreos.wp2.sia.analysis.entity;

import edu.uci.ics.jung.graph.DirectedGraph;

public class AntiPatternReport<V,E> {

	private ButterfliesReport<V,E> butterfliesReport; 
	private SensitiveReport<V,E> sensitiveReport;
	private HubsReport<V,E> hubsReport;
	
	public AntiPatternReport(DirectedGraph<V,E> graph){
		
		this.butterfliesReport = new ButterfliesReport<V,E>(graph);
		this.sensitiveReport = new SensitiveReport<V,E>(graph);
		this.hubsReport = new HubsReport<V,E>(graph);
	}

	public ButterfliesReport<V,E> getButterfliesReport() {
		return butterfliesReport;
	}

	public SensitiveReport<V,E> getSensitiveReport() {
		return sensitiveReport;
	}

	public HubsReport<V,E> getHubsReport() {
		return hubsReport;
	}	
}
