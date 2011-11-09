package eu.choreos.wp2.sia.visualization;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPopupMenu;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.EllipseVertexShapeTransformer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import eu.choreos.wp2.sia.analysis.JungAnalyzer;
import eu.choreos.wp2.sia.analysis.entity.AntiPatternReport;
import eu.choreos.wp2.sia.visualization.sample.GraphFactory;
import eu.choreos.wp2.sia.visualization.sample.Vertex;

public class ChoreographyVisualizer {

	public ChoreographyVisualizer(){
		
		DirectedGraph<Vertex, String> graph = GraphFactory.createGraph();
		
		JungAnalyzer<Vertex, String> analyzer = 
				new JungAnalyzer<Vertex, String>();
		
		AntiPatternReport<Vertex, String> antiPatternReport = 
				analyzer.findAntiPatterns(graph);
		
		GraphFactory.addAntiPatterns(graph, antiPatternReport);
		
		JFrame frame = new JFrame("Choreography Visualizer");
		
		// Creates the layout
		Layout<Vertex, String> layout = new CircleLayout<Vertex, String>(graph);
		layout.setSize(new Dimension(640,480)); // sets the initial size of the space
		
		// Creates the visualization viewer
		VisualizationViewer<Vertex,String> vv =
				new VisualizationViewer<Vertex,String>(layout);
		vv.setPreferredSize(new Dimension(800,600)); //Sets the viewing area size

		//Paints the vertices
		Transformer<Vertex,Paint> paintTransformer = new Transformer<Vertex,Paint>() {
			public Paint transform(Vertex v) {
				if (!v.getListAntiPattern().isEmpty()){
					return Color.RED;
				}
				else{
					return Color.YELLOW;
				}
			}
		};
		
		//Creates the appropriate tooltip
		Transformer<Vertex,String> tooltipTransformer = new Transformer<Vertex,String>(){
			public String transform(Vertex v) {
				if (!v.getListAntiPattern().isEmpty()){
					return v.getListAntiPattern().toString();
				}
				else{
					return null;
				}
			}
		};
			
		vv.getRenderContext().setVertexFillPaintTransformer(paintTransformer);
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Vertex>());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
		vv.setVertexToolTipTransformer(tooltipTransformer);
		
		PopupVertexEdgeMenuMousePlugin<Vertex,String> popupPlugin = 
				new PopupVertexEdgeMenuMousePlugin<Vertex,String>(graph);
		
		JPopupMenu vertexMenu = new MyMouseMenus.VertexMenu();
		JPopupMenu edgeMenu = new MyMouseMenus.EdgeMenu();
		
        popupPlugin.setEdgePopup(edgeMenu);
        popupPlugin.setVertexPopup(vertexMenu);
		
		DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
		gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
		gm.add(popupPlugin);
		vv.setGraphMouse(gm);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new ChoreographyVisualizer();
	}
	
}
