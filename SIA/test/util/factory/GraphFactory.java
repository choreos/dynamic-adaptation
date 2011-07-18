package util.factory;

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

	private DirectedGraph<Vertex,Edge> graph;
	private CentralityAnalysis centrality;
	private StabilityAnalysis stability;
	
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

		Map<Vertex, Double> vClosCent = new HashMap<Vertex, Double>();
		vClosCent.put(a, 0.5);
		vClosCent.put(b, 1/3d);
		vClosCent.put(c, 0d);

		Map<Vertex, Double> vBetwCent = new HashMap<Vertex, Double>();
		vBetwCent.put(a, 1d);
		vBetwCent.put(b, 0d);
		vBetwCent.put(c, 0d);

		CentralityResults results = new CentralityResults(graph);
		results.setVerticesDegreeCentrality(vDegCent);
		results.setGraphDegreeCentrality(new DegreeCentrality(0, 0.75));
		results.setVerticesClosenessCentrality(vClosCent);
		results.setVerticesBetweenessCentrality(vBetwCent);
		
		centrality = results;
		
		stability = new StabilityResults(200/3d);
	}
	
	public DirectedGraph<Vertex,Edge> createDependencyGraph(){
			
		return graph;
	}
	
	public CentralityAnalysis createCentralityResults() {
		
		return centrality;
	}
	
	public StabilityAnalysis createStabilityResults() {
		
		return stability;
	}
}
