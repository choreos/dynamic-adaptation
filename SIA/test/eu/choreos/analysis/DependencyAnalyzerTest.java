package eu.choreos.analysis;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import util.factory.GraphFactory;
import edu.uci.ics.jung.graph.DirectedGraph;
import eu.choreos.analysis.entity.CentralityAnalysis;
import eu.choreos.analysis.entity.DegreeCentrality;
import eu.choreos.analysis.entity.OverallStabilityResults;
import eu.choreos.analysis.graph.Edge;
import eu.choreos.analysis.graph.Vertex;

public class DependencyAnalyzerTest {

	@Test
	@Ignore
	public void shouldCalculateOverallStability() {
		
		GraphFactory graphFactory = new GraphFactory();
		DirectedGraph<Vertex, Edge> dependencyGraph = graphFactory.createDependencyGraph();
		
		JungAnalyzer depAnalyzer = new JungAnalyzer();
		
		OverallStabilityResults overallStabilityResults = 
			depAnalyzer.calculateOverallStability(dependencyGraph);
		
		assertEquals(0.8, overallStabilityResults);
	}

	@Test
	public void shouldCalculateVerticeDegreeCentrality() {
		
		// graph
		GraphFactory factory = new GraphFactory();
		DirectedGraph<Vertex, Edge> graph = factory.createDependencyGraph();
		
		// expected
		CentralityAnalysis centralityExpected = factory.createCentralityResults();
		Map<Vertex, DegreeCentrality> expected = centralityExpected.getVerticesDegreeCentrality();
		
		// calculated
		DependencyAnalyzer analyzer = new JungAnalyzer();
		CentralityAnalysis analysis = analyzer.calculateCentralityMeasures(graph);
		Map<Vertex, DegreeCentrality> calculated = analysis.getVerticesDegreeCentrality();
		
		// test
		for (Vertex v: expected.keySet()) {
			assertEquals(expected.get(v), calculated.get(v));
		}
	}
	
	
	
}
