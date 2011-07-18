package eu.choreos.analysis;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import util.factory.GraphFactory;
import edu.uci.ics.jung.graph.DirectedGraph;
import eu.choreos.analysis.entity.CentralityAnalysis;
import eu.choreos.analysis.entity.DegreeCentrality;
import eu.choreos.analysis.graph.Edge;
import eu.choreos.analysis.graph.Vertex;

public class CentralitySimpleTest {

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
	
	@Test
	public void shouldCalculateVerticesClosenessCentrality() {
		
		Map<Vertex, Double> expected = expectedCentrality.getVerticesClosenessCentrality();
		Map<Vertex, Double> calculated = actualCentrality.getVerticesClosenessCentrality();
		
		for (Vertex v: expected.keySet()) {
			assertEquals(expected.get(v), calculated.get(v));
		}		
	}

	@Test
	public void shouldCalculateVerticesBetweenessCentrality() {
		
		Map<Vertex, Double> expected = expectedCentrality.getVerticesBetweenessCentrality();
		Map<Vertex, Double> calculated = actualCentrality.getVerticesBetweenessCentrality();
		
		for (Vertex v: expected.keySet()) {
			assertEquals(expected.get(v), calculated.get(v));
		}		
	}

}
