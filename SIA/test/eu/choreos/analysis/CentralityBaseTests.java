package eu.choreos.analysis;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import eu.choreos.analysis.entity.CentralityAnalysis;
import eu.choreos.analysis.entity.DegreeCentrality;

/**
 * Centralize the centrality tests, 
 * since they are the same for every type of graph (simple, complex or loop)
 * 
 * @author leofl
 *
 */
public class CentralityBaseTests {

	public static void shouldCalculateVerticeDegreeCentrality(
			CentralityAnalysis<String,String> expectedCentrality, 
			CentralityAnalysis<String,String> actualCentrality) {
		
		Map<String, DegreeCentrality> expected = 
				expectedCentrality.getVerticesDegreeCentrality();
		
		Map<String, DegreeCentrality> calculated = 
				actualCentrality.getVerticesDegreeCentrality();
		
		for (String v: expected.keySet()) {
			assertEquals(expected.get(v), calculated.get(v));
		}
	}
	
	public static void shouldCalculateGraphDegreeCentrality(
			CentralityAnalysis<String,String> expectedCentrality, 
			CentralityAnalysis<String,String> actualCentrality) {
		
		DegreeCentrality expected = 
				expectedCentrality.getGraphDegreeCentrality();
		
		DegreeCentrality calculated = 
				actualCentrality.getGraphDegreeCentrality();
		
		assertEquals(expected, calculated);
	}
	
	public static void shouldCalculateVerticesClosenessCentrality(
			CentralityAnalysis<String,String> expectedCentrality, 
			CentralityAnalysis<String,String> actualCentrality) {
		
		Map<String, Double> expected = 
				expectedCentrality.getVerticesClosenessCentrality();
		
		Map<String, Double> calculated = 
				actualCentrality.getVerticesClosenessCentrality();
		
		for (String v: expected.keySet()) {
			assertEquals("Vertex " + v, expected.get(v), calculated.get(v));
		}
	}

	public static void shouldCalculateVerticesBetweennessCentrality(
			CentralityAnalysis<String,String> expectedCentrality, 
			CentralityAnalysis<String,String> actualCentrality) {
		
		Map<String, Double> expected = 
				expectedCentrality.getVerticesBetweennessCentrality();
		
		Map<String, Double> calculated = 
				actualCentrality.getVerticesBetweennessCentrality();
		
		for (String v: expected.keySet()) {
			assertEquals(expected.get(v), calculated.get(v));
		}		
	}
	
	public static void shouldCalculateVerticesPageRank(
			CentralityAnalysis<String,String> expectedCentrality, 
			CentralityAnalysis<String,String> actualCentrality) {
		
		Map<String, Double> expected = expectedCentrality.getVerticesPageRank();
		Map<String, Double> calculated = actualCentrality.getVerticesPageRank();
		
		for (String v: expected.keySet()) {
			assertEquals(expected.get(v), calculated.get(v));
		}		
	}	
}
