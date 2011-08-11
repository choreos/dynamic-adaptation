package eu.choreos.analysis;

import edu.uci.ics.jung.graph.DirectedGraph;
import eu.choreos.analysis.calc.BetweenessCentralityCalculator;
import eu.choreos.analysis.calc.ClosenessCentralityCalculator;
import eu.choreos.analysis.calc.DegreeCentralityCalculator;
import eu.choreos.analysis.calc.StabilityCalculator;
import eu.choreos.analysis.entity.CentralityAnalysis;
import eu.choreos.analysis.entity.CentralityResults;
import eu.choreos.analysis.entity.StabilityAnalysis;
import eu.choreos.analysis.entity.StabilityResults;

public class JungAnalyzer<V, E> implements DependencyAnalyzer<V, E> {
	
	// TODO:  in this class we have some heavy computations
	// would be nice having some caching...
	// hint: JUNG API provides a Caching interface
	
	private DirectedGraph<V, E> graph;
	
	public JungAnalyzer() {
	}

	public JungAnalyzer(DirectedGraph<V, E> graph) {
		this.graph = graph;
	}

	public DirectedGraph<V, E> getGraph() {
		return graph;
	}

	public void setGraph(DirectedGraph<V, E> graph) {
		this.graph = graph;
	}

	
	public StabilityAnalysis calculateStabilityAnalysis(){
		
		StabilityCalculator<V, E> calculator = new StabilityCalculator<V, E>(this.graph);
		double overallStability = calculator.calculateOverallStability();
		return new StabilityResults(overallStability);
	}
	
	public CentralityAnalysis<V, E> calculateCentralityAnalysis(){
		
		if (this.graph == null)
			throw new IllegalStateException("The graph should not be null");		
		
		CentralityResults<V, E> centralityResults = new CentralityResults<V, E>(graph);
		
		DegreeCentralityCalculator<V,E> degreeCalc = new DegreeCentralityCalculator<V,E>(this.graph);
		centralityResults.setVerticesDegreeCentrality(degreeCalc.calculateVerticesDegreeCentrality());
		centralityResults.setGraphDegreeCentrality(degreeCalc.calculateGraphDegreeCentrality());
		
		ClosenessCentralityCalculator<V,E> closenessCalc = new ClosenessCentralityCalculator<V,E>(this.graph);		
		centralityResults.setVerticesClosenessCentrality(closenessCalc.calculateVerticesClosenessCentrality());
		
		BetweenessCentralityCalculator<V,E> betweenessCalc = new BetweenessCentralityCalculator<V,E>(this.graph);				
		centralityResults.setVerticesBetweenessCentrality(betweenessCalc.calculateVerticesBetweennessCentrality());
		
		return centralityResults;
	}
	
	
	

	


	

	
}
