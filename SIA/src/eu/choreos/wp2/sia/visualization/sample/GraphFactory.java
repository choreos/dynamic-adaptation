package eu.choreos.wp2.sia.visualization.sample;

import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import eu.choreos.wp2.sia.analysis.entity.AntiPatternReport;

public abstract class GraphFactory {

	public static DirectedGraph<Vertex, String> createGraph(){// create graph
		
		DirectedGraph<Vertex,String> graph = 
				new DirectedSparseGraph<Vertex,String>();
		
		//Creates the vertexes
		Vertex a = new Vertex("a");
		Vertex b = new Vertex("b");
		Vertex c = new Vertex("c");
		Vertex d = new Vertex("d");
		Vertex e = new Vertex("e");
		Vertex f = new Vertex("f");
		Vertex g = new Vertex("g");
		Vertex h = new Vertex("h");
		
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

		return graph;
	}
	
	public static void addAntiPatterns(DirectedGraph<Vertex, String> graph, 
			AntiPatternReport<Vertex, String> antiPatternsReport){
		
		for(Vertex v : antiPatternsReport.getButterfliesReport().getLocalButterflies()){
			v.addAntiPattern(AntiPattern.LOCAL_BUTTERFLY);
		}
		
		for(Vertex v : antiPatternsReport.getButterfliesReport().getGlobalButterflies()){
			v.addAntiPattern(AntiPattern.GLOBAL_BUTTERFLY);
		}
		
		for(Vertex v : antiPatternsReport.getSensitiveReport().getLocalSensitive()){
			v.addAntiPattern(AntiPattern.LOCAL_SENSITIVE);
		}
		
		for(Vertex v : antiPatternsReport.getSensitiveReport().getGlobalSensitive()){
			v.addAntiPattern(AntiPattern.GLOBAL_SENSITIVE);
		}
		
		for(Vertex v : antiPatternsReport.getHubsReport().getLocalHubs()){
			v.addAntiPattern(AntiPattern.LOCAL_HUB);
		}
		
		for(Vertex v : antiPatternsReport.getHubsReport().getGlobalHubs()){
			v.addAntiPattern(AntiPattern.GLOBAL_HUB);
		}
	}
}
