package eu.choreos.analysis;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import eu.choreos.analysis.entity.CentralityAnalysis;
import eu.choreos.analysis.entity.DegreeCentrality;
import eu.choreos.analysis.graph.Vertex;

/**
 * Centralize the centrality tests, 
 * since they are the same for every type of graph (simple, complex or loop)
 * 
 * @author leofl
 *
 */
public class CentralityBaseTests {

	public static void shouldCalculateVerticeDegreeCentrality(CentralityAnalysis expectedCentrality, 
			CentralityAnalysis actualCentrality) {
		
		Map<Vertex, DegreeCentrality> expected = expectedCentrality.getVerticesDegreeCentrality();
		Map<Vertex, DegreeCentrality> calculated = actualCentrality.getVerticesDegreeCentrality();
		
		for (Vertex v: expected.keySet()) {
			assertEquals(expected.get(v), calculated.get(v));
		}
	}
	
	public static void shouldCalculateGraphDegreeCentrality(CentralityAnalysis expectedCentrality, 
			CentralityAnalysis actualCentrality) {
		
		DegreeCentrality expected = expectedCentrality.getGraphDegreeCentrality();
		DegreeCentrality calculated = actualCentrality.getGraphDegreeCentrality();
		assertEquals(expected, calculated);
	}
	
	public static void shouldCalculateVerticesClosenessCentrality(CentralityAnalysis expectedCentrality, 
			CentralityAnalysis actualCentrality) {
		
		Map<Vertex, Double> expected = expectedCentrality.getVerticesClosenessCentrality();
		Map<Vertex, Double> calculated = actualCentrality.getVerticesClosenessCentrality();
		
		for (Vertex v: expected.keySet()) {
			assertEquals(expected.get(v), calculated.get(v));
		}		
	}

	public static void shouldCalculateVerticesBetweenessCentrality(CentralityAnalysis expectedCentrality, 
			CentralityAnalysis actualCentrality) {
		
		Map<Vertex, Double> expected = expectedCentrality.getVerticesBetweenessCentrality();
		Map<Vertex, Double> calculated = actualCentrality.getVerticesBetweenessCentrality();
		
		for (Vertex v: expected.keySet()) {
			assertEquals(expected.get(v), calculated.get(v));
		}		
	}	
}
