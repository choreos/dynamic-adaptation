package util.factory;

import java.util.HashMap;
import java.util.Map;

import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import eu.choreos.analysis.entity.CentralityAnalysis;
import eu.choreos.analysis.entity.CentralityResults;
import eu.choreos.analysis.entity.DegreeCentrality;
import eu.choreos.analysis.graph.Edge;
import eu.choreos.analysis.graph.Vertex;

public class GraphFactory {

	private DirectedGraph<Vertex,Edge> graph;
	private CentralityAnalysis centrality;
	
	public GraphFactory() {
		
		// create graph
		
		graph = new DirectedSparseGraph<Vertex,Edge>();
		
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
		
		CentralityResults results = new CentralityResults(graph);
		results.setVerticesDegreeCentrality(vDegCent);
		results.setGraphDegreeCentrality(new DegreeCentrality(0, 0.75));
		
		centrality = results;
	}
	
	public DirectedGraph<Vertex,Edge> createDependencyGraph(){
			
		return graph;
	}
	
	public CentralityAnalysis createCentralityResults() {
		
		return centrality;
	}
	
}
