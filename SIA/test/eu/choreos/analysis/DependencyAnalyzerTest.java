package eu.choreos.analysis;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Before;
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

	DependencyAnalyzer analyzer;
	DirectedGraph<Vertex, Edge> graph;
	CentralityAnalysis expectedCentrality;
	CentralityAnalysis actualCentrality;
	
	@Before
	public void setUp() {
		
		GraphFactory factory = new GraphFactory();
		graph = factory.createDependencyGraph();
		expectedCentrality = factory.createCentralityResults();		
		analyzer = new JungAnalyzer(graph);
		actualCentrality = analyzer.calculateCentralityAnalysis();		
	}
	
	@Test
	@Ignore
	public void shouldCalculateOverallStability() {
		
		GraphFactory graphFactory = new GraphFactory();
		DirectedGraph<Vertex, Edge> dependencyGraph = graphFactory.createDependencyGraph();
		
		JungAnalyzer depAnalyzer = new JungAnalyzer(dependencyGraph);
		
		OverallStabilityResults overallStabilityResults = 
			depAnalyzer.calculateOverallStability();
		
		assertEquals(0.8, overallStabilityResults);
	}

	@Test
	public void shouldCalculateVerticeDegreeCentrality() {
		
		Map<Vertex, DegreeCentrality> expected = expectedCentrality.getVerticesDegreeCentrality();
		Map<Vertex, DegreeCentrality> calculated = actualCentrality.getVerticesDegreeCentrality();
		
		for (Vertex v: expected.keySet()) {
			assertEquals(expected.get(v), calculated.get(v));
		}
	}
	
	@Test
	public void shouldCalculateGraphDegreeCentrality() {
		
		DegreeCentrality expected = expectedCentrality.getGraphDegreeCentrality();
		DegreeCentrality calculated = actualCentrality.getGraphDegreeCentrality();
		assertEquals(expected, calculated);
	}
	
}
