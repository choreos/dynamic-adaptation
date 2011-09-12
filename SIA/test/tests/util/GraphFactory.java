package tests.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import eu.choreos.analysis.entity.CentralityAnalysis;
import eu.choreos.analysis.entity.CentralityResults;
import eu.choreos.analysis.entity.DegreeCentrality;
import eu.choreos.analysis.entity.StabilityAnalysis;
import eu.choreos.analysis.entity.StabilityResults;

public class GraphFactory {

	public enum TestGraph {SIMPLE, COMPLEX, LOOP};
	
	private Map<TestGraph, DirectedGraph<String,String>> graphs = new HashMap<TestGraph, DirectedGraph<String,String>>();
	private Map<TestGraph, CentralityAnalysis<String,String>> centralities = new HashMap<TestGraph, CentralityAnalysis<String,String>>();
	private Map<TestGraph, StabilityAnalysis> stabilities = new HashMap<TestGraph, StabilityAnalysis>();
	
	public Map<TestGraph, DirectedGraph<String,String>> getAllGraphs() {
		
		if (graphs.isEmpty()) 
			createAllGraphs();
		return Collections.unmodifiableMap(graphs);
	}
	
	public Map<TestGraph, CentralityAnalysis<String,String>> getAllCentralities() {
		
		if (graphs.isEmpty()) 
			createAllGraphs();
		return Collections.unmodifiableMap(centralities);
	}	
	
	public DirectedGraph<String,String> getGraph(TestGraph graph){
			
		if (graphs.get(graph) == null) 
			createGraph(graph);
		return graphs.get(graph);
	}
	

	public CentralityAnalysis<String,String> getCentralityAnalysis(TestGraph graph) {
	
		if (centralities.get(graph) == null) 
			createGraph(graph);
		return centralities.get(graph);
	}
	
	public StabilityAnalysis getStabilityAnalysis(TestGraph graph) {
		
		if (stabilities.get(graph) == null) 
			createGraph(graph);
		return stabilities.get(graph);
	}

	private void createAllGraphs() {
		
		createSimpleGraph();
		createComplexGraph();
		createLoopGraph();
	}
	
	private void createGraph(TestGraph graph) {
		
		switch (graph) {
			case SIMPLE: 
				createSimpleGraph();
				break;
			case COMPLEX: 
				createComplexGraph();
				break;
			case LOOP:
				createLoopGraph();
				break;
		}
	}
	
	/**
	 *   a <---> b
	 *   |
	 *   |----> c
	 */
	private void createSimpleGraph() {

		// create graph
		
		DirectedSparseGraph<String,String> graph = new DirectedSparseGraph<String,String>();
		
		//Creates the vertexes
		String a = "a";
		String b = "b";
		String c = "c";
		
		//Adds the vertexes
		graph.addVertex(a);
		graph.addVertex(b);
		graph.addVertex(c);
		
		//Adds the edges
		graph.addEdge("1", a, b);
		graph.addEdge("2", b, a);
		graph.addEdge("3", a, c);
		
		// create centrality analysis
		
		Map<String, DegreeCentrality> vDegCent = new HashMap<String, DegreeCentrality>();
		vDegCent.put(a, new DegreeCentrality(0.5, 1));
		vDegCent.put(b, new DegreeCentrality(0.5, 0.5));
		vDegCent.put(c, new DegreeCentrality(0.5, 0));

		Map<String, Double> vClosCent = new HashMap<String, Double>();
		vClosCent.put(a, 1d);
		vClosCent.put(b, 2/3d);
		vClosCent.put(c, Double.NaN);

		Map<String, Double> vBetwCent = new HashMap<String, Double>();
		vBetwCent.put(a, 1d);
		vBetwCent.put(b, 0d);
		vBetwCent.put(c, 0d);

		Map<String, Double> pageRank = new HashMap<String, Double>();
		pageRank.put(a, 0d);
		pageRank.put(b, 0d);
		pageRank.put(c, 0d);

		CentralityResults<String,String> centralityAnalysis = new CentralityResults<String,String>(graph);
		centralityAnalysis.setVerticesDegreeCentrality(vDegCent);
		centralityAnalysis.setGraphDegreeCentrality(new DegreeCentrality(0, 0.75));
		centralityAnalysis.setVerticesClosenessCentrality(vClosCent);
		centralityAnalysis.setVerticesBetweenessCentrality(vBetwCent);
		centralityAnalysis.setVerticesPageRank(pageRank);
		
		StabilityAnalysis stabilityAnalysis = new StabilityResults(0.5555);
		
		graphs.put(TestGraph.SIMPLE, graph);
		centralities.put(TestGraph.SIMPLE, centralityAnalysis);
		stabilities.put(TestGraph.SIMPLE, stabilityAnalysis);
	}
	
	/**
	 * f <--- c       |------> b
	 *        ^       |
	 *        |------ a -----> e <---> g
	 *                ^        ^
	 *                |        |--- h
	 *                d
	 */
	private void createComplexGraph() {

		// create graph
		
		DirectedSparseGraph<String,String> graph = new DirectedSparseGraph<String,String>();
		
		//Creates the vertexes
		String a = "a";
		String b = "b";
		String c = "c";
		String d = "d";
		String e = "e";
		String f = "f";
		String g = "g";
		String h = "h";
		
		//Adds the vertexes
		graph.addVertex(a);
		graph.addVertex(b);
		graph.addVertex(c);
		graph.addVertex(c);
		graph.addVertex(d);
		graph.addVertex(e);
		graph.addVertex(f);
		graph.addVertex(g);
		graph.addVertex(h);
		
		//Adds the edges
		graph.addEdge("1", a, b);
		graph.addEdge("2", a, c);
		graph.addEdge("3", a, e);
		graph.addEdge("4", c, f);
		graph.addEdge("5", d, a);
		graph.addEdge("6", e, g);
		graph.addEdge("7", h, e);
		
		// create centrality analysis
		
		Map<String, DegreeCentrality> vDegCent = new HashMap<String, DegreeCentrality>();
		vDegCent.put(a, new DegreeCentrality(1/7d, 3/7d));
		vDegCent.put(b, new DegreeCentrality(1/7d, 0));
		vDegCent.put(c, new DegreeCentrality(1/7d, 1/7d));
		vDegCent.put(d, new DegreeCentrality(0, 1/7d));
		vDegCent.put(e, new DegreeCentrality(2/7d, 1/7d));
		vDegCent.put(f, new DegreeCentrality(1/7d, 0));
		vDegCent.put(g, new DegreeCentrality(1/7d, 0));
		vDegCent.put(h, new DegreeCentrality(0, 1/7d));

		Map<String, Double> vClosCent = new HashMap<String, Double>();
		vClosCent.put(a, 5/7d); 
		vClosCent.put(b, Double.NaN);
		vClosCent.put(c, 1d);
		vClosCent.put(d, 6/13d);
		vClosCent.put(e, 1d);
		vClosCent.put(f, Double.NaN);
		vClosCent.put(g, Double.NaN);
		vClosCent.put(h, 2/3d);

		Map<String, Double> vBetwCent = new HashMap<String, Double>();
		vBetwCent.put(a, 5d);
		vBetwCent.put(b, 0d);
		vBetwCent.put(c, 2d);
		vBetwCent.put(d, 0d);
		vBetwCent.put(e, 3d);
		vBetwCent.put(f, 0d);
		vBetwCent.put(g, 0d);
		vBetwCent.put(h, 0d);

		Map<String, Double> pageRank = new HashMap<String, Double>();
		pageRank.put(a, 0d);
		pageRank.put(b, 0d);
		pageRank.put(c, 0d);
		pageRank.put(d, 0d);
		pageRank.put(e, 0d);
		pageRank.put(f, 0d);
		pageRank.put(g, 0d);
		pageRank.put(h, 0d);
		
		CentralityResults<String,String> centralityAnalysis = new CentralityResults<String,String>(graph);
		centralityAnalysis.setVerticesDegreeCentrality(vDegCent);
		centralityAnalysis.setGraphDegreeCentrality(new DegreeCentrality(0.0306, 0.0578)); 
		centralityAnalysis.setVerticesClosenessCentrality(vClosCent); 
		centralityAnalysis.setVerticesBetweenessCentrality(vBetwCent); 
		centralityAnalysis.setVerticesPageRank(pageRank);
		
		StabilityAnalysis stabilityAnalysis = new StabilityResults(0.7656); 
		
		graphs.put(TestGraph.COMPLEX, graph);
		centralities.put(TestGraph.COMPLEX, centralityAnalysis);
		stabilities.put(TestGraph.COMPLEX, stabilityAnalysis);
	}

	/**
	 *   |-------------- b <-------|
	 *   |               ^         |
	 *   |               |         |
	 *   |---> c ------> a <------ d
	 */
	private void createLoopGraph() {

		// create graph
		
		DirectedSparseGraph<String,String> graph = new DirectedSparseGraph<String,String>();
		
		//Creates the vertexes
		String a = "a";
		String b = "b";
		String c = "c";
		String d = "d";
		
		//Adds the vertexes
		graph.addVertex(a);
		graph.addVertex(b);
		graph.addVertex(c);
		graph.addVertex(d);
		
		//Adds the edges
		graph.addEdge("1", a, b);
		graph.addEdge("2", c, a);
		graph.addEdge("3", b, c);
		graph.addEdge("4", d, a);
		graph.addEdge("5", d, b);
		
		// create centrality analysis
		
		Map<String, DegreeCentrality> vDegCent = new HashMap<String, DegreeCentrality>();
		vDegCent.put(a, new DegreeCentrality(2/3d, 1/3d));
		vDegCent.put(b, new DegreeCentrality(2/3d, 1/3d));
		vDegCent.put(c, new DegreeCentrality(1/3d, 1/3d));
		vDegCent.put(d, new DegreeCentrality(0d, 2/3d));

		Map<String, Double> vClosCent = new HashMap<String, Double>();
		vClosCent.put(a, 4/9d);
		vClosCent.put(b, 4/9d);
		vClosCent.put(c, 4/9d);
		vClosCent.put(d, 3/4d);

		Map<String, Double> vBetwCent = new HashMap<String, Double>();
		vBetwCent.put(a, 1/6d); 
		vBetwCent.put(b, 1/3d); 
		vBetwCent.put(c, 1/6d); 
		vBetwCent.put(d, 0d); 
		
		Map<String, Double> pageRank = new HashMap<String, Double>();
		pageRank.put(a, 0d); 
		pageRank.put(b, 0d); 
		pageRank.put(c, 0d); 
		pageRank.put(d, 0d); 

		CentralityResults<String,String> centralityAnalysis = new CentralityResults<String,String>(graph);
		centralityAnalysis.setVerticesDegreeCentrality(vDegCent);
		centralityAnalysis.setGraphDegreeCentrality(new DegreeCentrality(1/6d, 1/6d));
		centralityAnalysis.setVerticesClosenessCentrality(vClosCent);
		centralityAnalysis.setVerticesBetweenessCentrality(vBetwCent);
		centralityAnalysis.setVerticesPageRank(pageRank);
		
		StabilityAnalysis stabilityAnalysis = new StabilityResults(7/16d);
		
		graphs.put(TestGraph.LOOP, graph);
		centralities.put(TestGraph.LOOP, centralityAnalysis);
		stabilities.put(TestGraph.LOOP, stabilityAnalysis);
	}

}
