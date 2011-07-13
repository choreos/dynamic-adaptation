package eu.choreos.analysis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import util.factory.BehaviorProtocolAutomatonFactory;
import util.factory.GraphFactory;
import edu.uci.ics.jung.graph.DirectedGraph;
import eu.choreos.analysis.entity.OverallStabilityResults;
import eu.choreos.analysis.graph.Edge;
import eu.choreos.analysis.graph.Vertex;
import eu.choreos.middleware.entity.ChoreographyModel;

public class DependencyAnalyzerTest {

	@Test
	public void shouldCalculateOverallStability() {
		
		GraphFactory graphFactory = new GraphFactory();
		DirectedGraph<Vertex, Edge> dependencyGraph = graphFactory.createDependencyGraph();
		
		DependencyAnalyzer depAnalyzer = new DependencyAnalyzer();
		
		OverallStabilityResults overallStabilityResults = 
			depAnalyzer.calculateOverallStability(dependencyGraph);
		
		assertEquals(0.8, overallStabilityResults);
	}

	@Test
	public void shouldCalculateCentrality() {
		fail("Not yet implemented");
	}
	
	
	
}
