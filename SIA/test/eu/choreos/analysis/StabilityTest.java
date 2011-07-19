package eu.choreos.analysis;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tests.util.GraphFactory;
import tests.util.GraphFactory.TestGraph;
import edu.uci.ics.jung.graph.DirectedGraph;
import eu.choreos.analysis.entity.StabilityAnalysis;
import eu.choreos.analysis.graph.Edge;
import eu.choreos.analysis.graph.Vertex;

public class StabilityTest {

	@Test
	public void shouldCalculateSimpleOverallStability() {
		
		GraphFactory factory = new GraphFactory();
		DirectedGraph<Vertex, Edge> graph = factory.getSimpleGraph(TestGraph.SIMPLE);
		
		StabilityAnalysis expectedStability = factory.getSimpleStabilityAnalysis(TestGraph.SIMPLE);
		
		JungAnalyzer depAnalyzer = new JungAnalyzer(graph);
		StabilityAnalysis actualStability = depAnalyzer.calculateStabilityAnalysis();
		
		assertEquals(expectedStability.getOverallStability(),
				actualStability.getOverallStability(), 0.0001);
	}

	@Test
	public void shouldCalculateComplexOverallStability() {
		
		GraphFactory factory = new GraphFactory();
		DirectedGraph<Vertex, Edge> graph = factory.getSimpleGraph(TestGraph.COMPLEX);
		
		StabilityAnalysis expectedStability = factory.getSimpleStabilityAnalysis(TestGraph.COMPLEX);
		
		JungAnalyzer depAnalyzer = new JungAnalyzer(graph);
		StabilityAnalysis actualStability = depAnalyzer.calculateStabilityAnalysis();
		
		assertEquals(expectedStability.getOverallStability(),
				actualStability.getOverallStability(), 0.0001);
	}

}
