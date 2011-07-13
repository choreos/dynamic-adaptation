package util.factory;

import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import eu.choreos.analysis.graph.Edge;
import eu.choreos.analysis.graph.Vertex;

public class GraphFactory {

	public DirectedGraph<Vertex,Edge> createDependencyGraph(){
		DirectedGraph<Vertex,Edge> graph = new DirectedSparseGraph<Vertex,Edge>();
		
		//Creates the vertexes
		Vertex customer = new Vertex(1, "Customer");
		Vertex advAgency = new Vertex(2, "Adv Agency");
		Vertex designer = new Vertex(3, "Designer");
		
		//Adds the vertexes
		graph.addVertex(customer);
		graph.addVertex(advAgency);
		graph.addVertex(designer);
		
		//Adds the edges
		graph.addEdge(new Edge(1), customer, advAgency);
		graph.addEdge(new Edge(2), advAgency, customer);
		graph.addEdge(new Edge(3), advAgency, designer);
		
		return graph;
	}
	
}
