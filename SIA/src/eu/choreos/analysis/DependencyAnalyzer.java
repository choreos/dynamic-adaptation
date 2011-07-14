package eu.choreos.analysis;

import edu.uci.ics.jung.graph.DirectedGraph;
import eu.choreos.analysis.entity.CentralityResults;
import eu.choreos.analysis.entity.OverallStabilityResults;
import eu.choreos.analysis.graph.Edge;
import eu.choreos.analysis.graph.Vertex;

public interface DependencyAnalyzer {

	public OverallStabilityResults calculateOverallStability(DirectedGraph<Vertex, Edge> graph);
	
	public CentralityResults calculateCentralityMeasures(DirectedGraph<Vertex, Edge> graph);
}
