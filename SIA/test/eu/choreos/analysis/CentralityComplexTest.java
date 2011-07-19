package eu.choreos.analysis;

import org.junit.Before;
import org.junit.Test;

import tests.util.GraphFactory;
import tests.util.GraphFactory.TestGraph;
import edu.uci.ics.jung.graph.DirectedGraph;
import eu.choreos.analysis.entity.CentralityAnalysis;
import eu.choreos.analysis.graph.Edge;
import eu.choreos.analysis.graph.Vertex;

/**
 * Tests the centralities calculations with a graph more complex (but not so much)
 * 
 * @author leofl
 *
 */
public class CentralityComplexTest {

	private DependencyAnalyzer analyzer;
	private DirectedGraph<Vertex, Edge> graph;
	private CentralityAnalysis expectedCentrality;
	private CentralityAnalysis actualCentrality;
	
	@Before
	public void setUp() {
		
		GraphFactory factory = new GraphFactory();
		graph = factory.getSimpleGraph(TestGraph.COMPLEX);
		expectedCentrality = factory.getSimpleCentralityAnalysis(TestGraph.COMPLEX);		
		analyzer = new JungAnalyzer(graph);
		actualCentrality = analyzer.calculateCentralityAnalysis();		
	}
	
	@Test
	public void shouldCalculateVerticeDegreeCentrality() {

		CentralityBaseTests.shouldCalculateVerticeDegreeCentrality(expectedCentrality, actualCentrality);
	}

	@Test
	public void shouldCalculateGraphDegreeCentrality() {
		
		CentralityBaseTests.shouldCalculateGraphDegreeCentrality(expectedCentrality, actualCentrality);
	}
	
	@Test
	public void shouldCalculateVerticesBetweenessCentrality() {
		
		CentralityBaseTests.shouldCalculateVerticesBetweenessCentrality(expectedCentrality, actualCentrality);
	}
	
}
