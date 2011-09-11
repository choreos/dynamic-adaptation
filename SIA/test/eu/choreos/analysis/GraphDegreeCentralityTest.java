package eu.choreos.analysis;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import tests.util.GraphFactory;
import tests.util.GraphFactory.TestGraph;
import edu.uci.ics.jung.graph.DirectedGraph;
import eu.choreos.analysis.entity.CentralityAnalysis;

public class GraphDegreeCentralityTest {

	private Map<TestGraph, DirectedGraph<String,String>> graphs;
	private Map<TestGraph, CentralityAnalysis<String,String>> expectedCentralities;
	private Map<TestGraph, CentralityAnalysis<String,String>> actualCentralities;
	
	@Before
	public void setUp() throws Exception {
		
		GraphFactory factory = new GraphFactory();
		graphs = factory.getAllGraphs();
		expectedCentralities = factory.getAllCentralities();
		
		actualCentralities = new HashMap<TestGraph, CentralityAnalysis<String,String>>();
		DependencyAnalyzer<String,String> analyzer = new JungAnalyzer<String,String>(graphs.get(TestGraph.SIMPLE));
		actualCentralities.put(TestGraph.SIMPLE, analyzer.calculateCentralityAnalysis());
		analyzer = new JungAnalyzer<String,String>(graphs.get(TestGraph.COMPLEX));
		actualCentralities.put(TestGraph.COMPLEX, analyzer.calculateCentralityAnalysis());
		analyzer = new JungAnalyzer<String,String>(graphs.get(TestGraph.LOOP));
		actualCentralities.put(TestGraph.LOOP, analyzer.calculateCentralityAnalysis());
	}

	@Test
	public void shouldCalculateSimpleGraphDegreeCentrality() {

		CentralityBaseTests.shouldCalculateGraphDegreeCentrality(
				expectedCentralities.get(TestGraph.SIMPLE), actualCentralities.get(TestGraph.SIMPLE));
	}
	
	@Test
	public void shouldCalculateComplexGraphDegreeCentrality() {

		CentralityBaseTests.shouldCalculateGraphDegreeCentrality(
				expectedCentralities.get(TestGraph.COMPLEX), actualCentralities.get(TestGraph.COMPLEX));
	}

	@Test
	public void shouldCalculateLoopGraphDegreeCentrality() {

		CentralityBaseTests.shouldCalculateGraphDegreeCentrality(
				expectedCentralities.get(TestGraph.LOOP), actualCentralities.get(TestGraph.LOOP));
	}

}
