package eu.choreos.wp2.sia.analysis.detectors;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.uci.ics.jung.graph.DirectedGraph;
import eu.choreos.wp2.sia.graph.algorithms.DegreeCentralityCalculator;
import eu.choreos.wp2.sia.graph.entity.DegreeCentrality;
import eu.choreos.wp2.sia.graph.util.GraphUtils;

public abstract class AbstractNodeDetector<V,E> {
	
	protected Set<V> detectLocalNodes(DirectedGraph<V, E> graph) {

		Set<V> localNodes = new HashSet<V>();
		
		DegreeCentralityCalculator<V,E> degreeCalculator = 
				new DegreeCentralityCalculator<V,E>();
		
		Map<V,DegreeCentrality> degreeCentralityResults = 
				degreeCalculator.calculateVerticesDegreeCentrality(graph);
	
		for (V v: degreeCentralityResults.keySet()) {
			DegreeCentrality degreeCentrality = degreeCentralityResults.get(v);
			if (isLocal(degreeCentrality)){
				localNodes.add(v);
			}
		}
		
		return localNodes;
	}
	
	protected Set<V> detectGlobal(DirectedGraph<V, E> graph) {

		Set<V> localNodes = new HashSet<V>();
		
		//Computes the transitive closure
		GraphUtils<V,E> graphUtils = new GraphUtils<V, E>();
		DirectedGraph<V,String> transitiveClosure = 
				graphUtils.computeTransitiveClosure(graph);

		//Calculates degree centrality
		DegreeCentralityCalculator<V,String> degreeCalculator = 
				new DegreeCentralityCalculator<V,String>();
		
		Map<V,DegreeCentrality> degreeCentralityResults = 
				degreeCalculator.calculateVerticesDegreeCentrality(transitiveClosure);
	
		int totalNodes = transitiveClosure.getVertexCount();
		
		for (V v: degreeCentralityResults.keySet()) {
			DegreeCentrality degreeCentrality = degreeCentralityResults.get(v);
			if (isGlobal(degreeCentrality)){
				localNodes.add(v);
			}
		}
		
		return localNodes;
	}
	
	/**	
	public Set<V> detectRelativeLocal(DirectedGraph<V, E> graph) {
		
		Set<V> localNodes = new HashSet<V>();
	
		//Calculates degree centrality for all nodes		
		DegreeCentralityCalculator<V,E> degreeCalculator = 
				new DegreeCentralityCalculator<V,E>();
		
		Map<V,DegreeCentrality> degreeCentralityResults = 
				degreeCalculator.calculateVerticesDegreeCentrality(graph);
		
		//Adds all degree centrality values in stats
		DescriptiveStatistics stats = new DescriptiveStatistics();
		for (V v: degreeCentralityResults.keySet()) {
			DegreeCentrality degreeCentrality = degreeCentralityResults.get(v);
			stats.addValue(degreeCentrality.getInDegree());
		}
		
		//Do a quartile analysis
		QuartileAnalysis quartileAnalysis = 
				DescriptiveStatisticsUtils.doQuartileAnalysis(stats);
		stats.clear();
		
		//Detect butterfly nodes (outliers)
		for (V v: degreeCentralityResults.keySet()) {
			DegreeCentrality degreeCentrality = degreeCentralityResults.get(v);
			if (isRelativeLocal(
					degreeCentrality, 
					quartileAnalysis.getExtremeUpperWhisker())){
				
				localNodes.add(v);
			}	
		}
		
		return localNodes;
	}
	
	public Set<V> detectRelativeGlobal(DirectedGraph<V, E> graph) {
		
		Set<V> localNodes = new HashSet<V>();
	
		//Calculates degree centrality for all nodes		
		DegreeCentralityCalculator<V,E> degreeCalculator = 
				new DegreeCentralityCalculator<V,E>();
		
		Map<V,DegreeCentrality> degreeCentralityResults = 
				degreeCalculator.calculateVerticesDegreeCentrality(graph);
		
		//Adds all degree centrality values in stats
		DescriptiveStatistics stats = new DescriptiveStatistics();
		for (V v: degreeCentralityResults.keySet()) {
			DegreeCentrality degreeCentrality = degreeCentralityResults.get(v);
			stats.addValue(degreeCentrality.getInDegree());
		}
		
		//Do a quartile analysis
		QuartileAnalysis quartileAnalysis = 
				DescriptiveStatisticsUtils.doQuartileAnalysis(stats);
		stats.clear();
		
		//Detect butterfly nodes (outliers)
		for (V v: degreeCentralityResults.keySet()) {
			DegreeCentrality degreeCentrality = degreeCentralityResults.get(v);
			if (isRelativeGlobal(
					degreeCentrality, 
					quartileAnalysis.getExtremeUpperWhisker())){
				
				localNodes.add(v);
			}	
		}
		
		return localNodes;
	}
	
	private boolean isRelativeLocal(
			DegreeCentrality degreeCentrality, double extremeUpperWhisker) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private boolean isRelativeGlobal(
			DegreeCentrality degreeCentrality, double extremeUpperWhisker) {
		// TODO Auto-generated method stub
		return false;
	}
	*/
	
	protected abstract boolean isLocal(DegreeCentrality degreeCentrality);
	
	protected abstract boolean isGlobal(DegreeCentrality degreeCentrality);
	
}
