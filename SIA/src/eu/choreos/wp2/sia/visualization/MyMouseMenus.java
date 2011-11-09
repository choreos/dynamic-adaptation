package eu.choreos.wp2.sia.visualization;

/*
 * MyMouseMenus.java
 *
 * Created on March 21, 2007, 3:34 PM; Updated May 29, 2007
 *
 * Copyright March 21, 2007 Grotto Networking
 *
 */

import java.awt.Color;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import eu.choreos.wp2.sia.graph.util.GraphUtils;
import eu.choreos.wp2.sia.visualization.sample.Vertex;

/**
 * A collection of classes used to assemble popup mouse menus for the custom
 * edges and vertices developed in this example.
 * @author Dr. Greg M. Bernstein
 */
public class MyMouseMenus {
    
    public static class EdgeMenu extends JPopupMenu {        
     
        public EdgeMenu() {
            super("Edge Menu");
            this.addSeparator();
            this.addSeparator();
        }
        
    }
    
    
    public static class VertexMenu extends JPopupMenu{
            	
    	public VertexMenu() {
            super("Vertex Menu");
            this.add(new ChangeImpactAnalysis());
        }

        public static class ChangeImpactAnalysis extends JMenuItem implements 
        	VertexMenuListener<Vertex> {
        
        	private DirectedGraph<Vertex, String> graph;
        	private Vertex v;
        	private VisualizationViewer vv;
        	
        	public ChangeImpactAnalysis(){
        		super("Change Impact Analysis");
        	    this.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	System.out.println("Performing Change Impact Analysis...");
                    	
                    	GraphUtils<Vertex, String> graphUtils = 
                    			new GraphUtils<Vertex, String>();
                    	
                    	DirectedGraph<Vertex, String> transitiveClosure = 
                    			graphUtils.computeTransitiveClosure(graph);
                    	
                    	final Collection<Vertex> predecessors = 
                    			transitiveClosure.getPredecessors(v);
                    	
                    	//Paints the vertices
                		Transformer<Vertex,Paint> paintTransformer = new Transformer<Vertex,Paint>() {
                			public Paint transform(Vertex otherVertex) {
                				if (predecessors.contains(otherVertex)){
                					return Color.WHITE;
                				}
                				else if(otherVertex.equals(v)){
                					return Color.GREEN;
                				}
                				else{
                					return Color.YELLOW;
                				}
                			}
                		};
                		
                		System.out.println(vv);
                		vv.getRenderContext().setVertexFillPaintTransformer(paintTransformer);
                		vv.updateUI();
                    }
                    
                });
        	}
        	
			@Override
			public void setVertexAndView(DirectedGraph graph, Vertex v, VisualizationViewer vv) {
				this.graph = graph;
				this.v = v;
				this.vv = vv;
			}
        }
    }
    
}