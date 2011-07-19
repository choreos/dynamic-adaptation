package tests.util;

import java.util.HashMap;
import java.util.Map;

import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import eu.choreos.analysis.entity.CentralityAnalysis;
import eu.choreos.analysis.entity.CentralityResults;
import eu.choreos.analysis.entity.DegreeCentrality;
import eu.choreos.analysis.entity.StabilityAnalysis;
import eu.choreos.analysis.entity.StabilityResults;
import eu.choreos.analysis.graph.Edge;
import eu.choreos.analysis.graph.Vertex;

public class GraphFactory {

	public enum TestGraph {SIMPLE, COMPLEX, LOOP};
	
	private Map<TestGraph, DirectedGraph<Vertex,Edge>> graphs = new HashMap<TestGraph, DirectedGraph<Vertex,Edge>>();
	private Map<TestGraph, CentralityAnalysis> centralities = new HashMap<TestGraph, CentralityAnalysis>();
	private Map<TestGraph, StabilityAnalysis> stabilities = new HashMap<TestGraph, StabilityAnalysis>();
	
	
	public DirectedGraph<Vertex,Edge> getSimpleGraph(TestGraph graph){
			
		if (graphs.get(graph) == null) 
			createGraph(graph);
		return graphs.get(graph);
	}
	

	public CentralityAnalysis getSimpleCentralityAnalysis(TestGraph graph) {
	
		if (centralities.get(graph) == null) 
			createGraph(graph);
		return centralities.get(graph);
	}
	
	public StabilityAnalysis getSimpleStabilityAnalysis(TestGraph graph) {
		
		if (stabilities.get(graph) == null) 
			createGraph(graph);
		return stabilities.get(graph);
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
				throw new UnsupportedOperationException();
		}
	}
	
	private void createSimpleGraph() {

		// create graph
		
		DirectedSparseGraph<Vertex,Edge> graph = new DirectedSparseGraph<Vertex,Edge>();
		
		//Creates the vertexes
		Vertex a = new Vertex(1, "A");
		Vertex b = new Vertex(2, "B");
		Vertex c = new Vertex(3, "C");
		
		//Adds the vertexes
		graph.addVertex(a);
		graph.addVertex(b);
		graph.addVertex(c);
		
		//Adds the edges
		graph.addEdge(new Edge(1), a, b);
		graph.addEdge(new Edge(2), b, a);
		graph.addEdge(new Edge(3), a, c);
		
		// create centrality analysis
		
		Map<Vertex, DegreeCentrality> vDegCent = new HashMap<Vertex, DegreeCentrality>();
		vDegCent.put(a, new DegreeCentrality(0.5, 1));
		vDegCent.put(b, new DegreeCentrality(0.5, 0.5));
		vDegCent.put(c, new DegreeCentrality(0.5, 0));

		Map<Vertex, Double> vClosCent = new HashMap<Vertex, Double>();
		vClosCent.put(a, 0.5);
		vClosCent.put(b, 1/3d);
		vClosCent.put(c, 0d);

		Map<Vertex, Double> vBetwCent = new HashMap<Vertex, Double>();
		vBetwCent.put(a, 1d);
		vBetwCent.put(b, 0d);
		vBetwCent.put(c, 0d);

		CentralityResults centralityAnalysis = new CentralityResults(graph);
		centralityAnalysis.setVerticesDegreeCentrality(vDegCent);
		centralityAnalysis.setGraphDegreeCentrality(new DegreeCentrality(0, 0.75));
		centralityAnalysis.setVerticesClosenessCentrality(vClosCent);
		centralityAnalysis.setVerticesBetweenessCentrality(vBetwCent);
		
		StabilityAnalysis stabilityAnalysis = new StabilityResults(200/3d);
		
		graphs.put(TestGraph.SIMPLE, graph);
		centralities.put(TestGraph.SIMPLE, centralityAnalysis);
		stabilities.put(TestGraph.SIMPLE, stabilityAnalysis);
	}

	private void createComplexGraph() {

		// create graph
		
		DirectedSparseGraph<Vertex,Edge> graph = new DirectedSparseGraph<Vertex,Edge>();
		
		//Creates the vertexes
		Vertex a = new Vertex(1, "A");
		Vertex b = new Vertex(2, "B");
		Vertex c = new Vertex(3, "C");
		Vertex d = new Vertex(3, "D");
		Vertex e = new Vertex(3, "E");
		Vertex f = new Vertex(3, "F");
		Vertex g = new Vertex(3, "G");
		Vertex h = new Vertex(3, "H");
		
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
		graph.addEdge(new Edge(1), a, b);
		graph.addEdge(new Edge(2), a, c);
		graph.addEdge(new Edge(3), a, e);
		graph.addEdge(new Edge(4), c, f);
		graph.addEdge(new Edge(5), d, a);
		graph.addEdge(new Edge(6), e, g);
		graph.addEdge(new Edge(7), h, e);
		
		// create centrality analysis
		
		Map<Vertex, DegreeCentrality> vDegCent = new HashMap<Vertex, DegreeCentrality>();
		vDegCent.put(a, new DegreeCentrality(1/7d, 3/7d));
		vDegCent.put(b, new DegreeCentrality(1/7d, 0));
		vDegCent.put(c, new DegreeCentrality(1/7d, 1/7d));
		vDegCent.put(d, new DegreeCentrality(0, 1/7d));
		vDegCent.put(e, new DegreeCentrality(2/7d, 1/7d));
		vDegCent.put(f, new DegreeCentrality(1/7d, 0));
		vDegCent.put(g, new DegreeCentrality(1/7d, 0));
		vDegCent.put(h, new DegreeCentrality(0, 1/7d));

		Map<Vertex, Double> vClosCent = new HashMap<Vertex, Double>();
//		vClosCent.put(a, 0.5);

		Map<Vertex, Double> vBetwCent = new HashMap<Vertex, Double>();
		vBetwCent.put(a, 5d);
		vBetwCent.put(b, 0d);
		vBetwCent.put(c, 2d);
		vBetwCent.put(d, 0d);
		vBetwCent.put(e, 3d);
		vBetwCent.put(f, 0d);
		vBetwCent.put(g, 0d);
		vBetwCent.put(h, 0d);

		CentralityResults centralityAnalysis = new CentralityResults(graph);
		centralityAnalysis.setVerticesDegreeCentrality(vDegCent);
		centralityAnalysis.setGraphDegreeCentrality(new DegreeCentrality(0.0306, 0.0578)); 
		centralityAnalysis.setVerticesClosenessCentrality(vClosCent); // TODO
		centralityAnalysis.setVerticesBetweenessCentrality(vBetwCent); 
		
		StabilityAnalysis stabilityAnalysis = new StabilityResults(89.0625); 
		
		graphs.put(TestGraph.COMPLEX, graph);
		centralities.put(TestGraph.COMPLEX, centralityAnalysis);
		stabilities.put(TestGraph.COMPLEX, stabilityAnalysis);
	}

}
