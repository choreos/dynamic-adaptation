package eu.choreos.analysis;

import edu.uci.ics.jung.graph.DirectedGraph;
import eu.choreos.analysis.calc.BetweennessCentralityCalculator;
import eu.choreos.analysis.calc.ClosenessCentralityCalculator;
import eu.choreos.analysis.calc.DegreeCentralityCalculator;
import eu.choreos.analysis.calc.PageRankCalculator;
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
		
		StabilityCalculator<V, E> calculator = new StabilityCalculator<V, E>();
		double overallStability = calculator.calculateOverallStability(this.graph);
		return new StabilityResults(overallStability);
	}
	
	public CentralityAnalysis<V, E> calculateCentralityAnalysis(){
		
		if (this.graph == null)
			throw new IllegalStateException("The graph should not be null");		
		
		CentralityResults<V, E> centralityResults = new CentralityResults<V, E>(graph);
		
		DegreeCentralityCalculator<V,E> degreeCalc = new DegreeCentralityCalculator<V,E>();
		centralityResults.setVerticesDegreeCentrality(degreeCalc.calculateVerticesDegreeCentrality(this.graph));
		centralityResults.setGraphDegreeCentrality(degreeCalc.calculateGraphDegreeCentrality(this.graph));
		
		ClosenessCentralityCalculator<V,E> closenessCalc = new ClosenessCentralityCalculator<V,E>();		
		centralityResults.setVerticesClosenessCentrality(closenessCalc.calculateVerticesClosenessCentrality(this.graph));
		
		BetweennessCentralityCalculator<V,E> betweenessCalc = new BetweennessCentralityCalculator<V,E>();				
		centralityResults.setVerticesBetweenessCentrality(betweenessCalc.calculateVerticesBetweennessCentrality(this.graph));
		
		PageRankCalculator<V, E> pageRankCalc = new PageRankCalculator<V, E>();
		centralityResults.setVerticesPageRank(pageRankCalc.calculatePageRank(graph));
		
		return centralityResults;
	}
	
}