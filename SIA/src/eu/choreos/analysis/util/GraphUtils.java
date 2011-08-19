package eu.choreos.analysis.util;

import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;

public class GraphUtils<V,E> {

	public DirectedSparseGraph<V, String> computeTransitiveClosure(
			DirectedGraph<V, E> graph) {
		
		DirectedSparseGraph<V, String> transitiveClosure = 
				new DirectedSparseGraph<V, String>();
		
		for(V v: graph.getVertices()){
			_computeTransitiveClosure(graph, transitiveClosure, v, v);
		}
		
		return transitiveClosure;
	}

	//DFS recursivo
	private void _computeTransitiveClosure(DirectedGraph<V, E> graph, 
			DirectedGraph<V, String> transitiveClosure, V v, V w) {
		
		if (!v.equals(w)){
			String edgeName = v.toString() + w.toString();
			transitiveClosure.addEdge(edgeName, v, w);	
		}
		
		for (V suc: graph.getSuccessors(w)){			
			if(transitiveClosure.findEdge(v, suc) == null){
				_computeTransitiveClosure(graph, transitiveClosure, v, suc);
			}
		}
	}	
	
}
