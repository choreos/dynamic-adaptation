package eu.choreos.analysis;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tests.util.GraphFactory;
import tests.util.GraphFactory.TestGraph;
import edu.uci.ics.jung.graph.DirectedGraph;
import eu.choreos.analysis.entity.StabilityAnalysis;

public class StabilityTest {

	private final double EPSILON = 0.0001;
	
	@Test
	public void shouldCalculateSimpleOverallStability() {
		
		GraphFactory factory = new GraphFactory();
		DirectedGraph<String,String> graph = factory.getGraph(TestGraph.SIMPLE);
		
		StabilityAnalysis expectedStability = factory.getStabilityAnalysis(TestGraph.SIMPLE);
		
		JungAnalyzer<String,String> depAnalyzer = new JungAnalyzer<String,String>(graph);
		StabilityAnalysis actualStability = depAnalyzer.calculateStabilityAnalysis();
		
		assertEquals(expectedStability.getOverallStability(),
				actualStability.getOverallStability(), EPSILON);
	}

	@Test
	public void shouldCalculateComplexOverallStability() {
			
		GraphFactory factory = new GraphFactory();
		DirectedGraph<String,String> graph = factory.getGraph(TestGraph.COMPLEX);
		StabilityAnalysis expectedStability = factory.getStabilityAnalysis(TestGraph.COMPLEX);
		
		JungAnalyzer<String,String> depAnalyzer = new JungAnalyzer<String,String>(graph);
		StabilityAnalysis actualStability = depAnalyzer.calculateStabilityAnalysis();
		
		assertEquals(expectedStability.getOverallStability(),
				actualStability.getOverallStability(), EPSILON);
	}

	@Test
	public void shouldCalculateLoopOverallStability() {
			
		GraphFactory factory = new GraphFactory();
		DirectedGraph<String,String> graph = factory.getGraph(TestGraph.LOOP);
		StabilityAnalysis expectedStability = factory.getStabilityAnalysis(TestGraph.LOOP);
		
		JungAnalyzer<String,String> depAnalyzer = new JungAnalyzer<String,String>(graph);
		StabilityAnalysis actualStability = depAnalyzer.calculateStabilityAnalysis();
		
		assertEquals(expectedStability.getOverallStability(),
				actualStability.getOverallStability(), EPSILON);
	}
	
}
