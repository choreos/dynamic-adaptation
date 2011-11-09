package eu.choreos.wp2.sia.graph.util;

import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import eu.choreos.wp2.sia.graph.entity.Edge;
import eu.choreos.wp2.sia.graph.entity.SimpleEdge;
import eu.choreos.wp2.sia.graph.entity.Vertex;

public abstract class GraphUtils{

	public static DirectedSparseGraph<Vertex, Edge> computeTransitiveClosure(
			DirectedGraph<Vertex, Edge> graph) {
		
		DirectedSparseGraph<Vertex, Edge> transitiveClosure = 
				new DirectedSparseGraph<Vertex, Edge>();
		
		for(Vertex v: graph.getVertices()){
			_computeTransitiveClosure(graph, transitiveClosure, v, v);
		}
		
		return transitiveClosure;
	}

	//DFS recursivo
	private static void _computeTransitiveClosure(DirectedGraph<Vertex, Edge> graph, 
			DirectedGraph<Vertex, Edge> transitiveClosure, Vertex v, Vertex w) {
		
		if (!v.equals(w)){
			SimpleEdge simpleEdge = new SimpleEdge(
					v.toString() + "," + w.toString());
			
			transitiveClosure.addEdge(simpleEdge, v, w);	
		}
		
		for (Vertex suc: graph.getSuccessors(w)){			
			if(transitiveClosure.findEdge(v, suc) == null){
				_computeTransitiveClosure(graph, transitiveClosure, v, suc);
			}
		}
	}	
	
}
