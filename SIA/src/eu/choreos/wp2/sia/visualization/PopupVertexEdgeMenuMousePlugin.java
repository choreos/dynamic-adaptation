package eu.choreos.wp2.sia.visualization;

/*
 * PopupVertexEdgeMenuMousePlugin.java
 *
 * Created on March 21, 2007, 12:56 PM; Updated May 29, 2007
 *
 * Copyright March 21, 2007 Grotto Networking
 *
 */

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.JPopupMenu;

import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.AbstractPopupGraphMousePlugin;
import eu.choreos.wp2.sia.visualization.sample.Vertex;

/**
 * A GraphMousePlugin that brings up distinct popup menus when an edge or vertex is
 * appropriately clicked in a graph.  If these menus contain components that implement
 * either the EdgeMenuListener or VertexMenuListener then the corresponding interface
 * methods will be called prior to the display of the menus (so that they can display
 * context sensitive information for the edge or vertex).
 * @author Dr. Greg M. Bernstein
 */
public class PopupVertexEdgeMenuMousePlugin<V, E> extends AbstractPopupGraphMousePlugin {
    
	private JPopupMenu edgePopup, vertexPopup;
    private DirectedGraph<V, E> graph;
    
    /** Creates a new instance of PopupVertexEdgeMenuMousePlugin */
    public PopupVertexEdgeMenuMousePlugin(DirectedGraph<V,E> graph) {
    	this(MouseEvent.BUTTON3_MASK);
    	this.graph = graph;
    }
    
    /**
     * Creates a new instance of PopupVertexEdgeMenuMousePlugin
     * @param modifiers mouse event modifiers see the jung visualization Event class.
     */
    public PopupVertexEdgeMenuMousePlugin(int modifiers) {
        super(modifiers);
    }
    
    /**
     * Implementation of the AbstractPopupGraphMousePlugin method. This is where the 
     * work gets done. You shouldn't have to modify unless you really want to...
     * @param e 
     */
    protected void handlePopup(MouseEvent e) {
        
    	final VisualizationViewer vv =
                (VisualizationViewer)e.getSource();
        
    	Point2D p = e.getPoint();
        
        GraphElementAccessor<V,E> pickSupport = vv.getPickSupport();
        if(pickSupport != null) {
        	final V v = pickSupport.getVertex(vv.getGraphLayout(), p.getX(), p.getY());
            if(v != null) {
                System.out.println("Vertex " + v + " was right clicked");
                vertexPopup = new MyMouseMenus.VertexMenu();
                updateVertexMenu(graph, v, vv, p);
                vertexPopup.show(vv, e.getX(), e.getY());
            } else {
                final E edge = pickSupport.getEdge(vv.getGraphLayout(), p.getX(), p.getY());
                if(edge != null) {
                    System.out.println("Edge " + edge + " was right clicked");
                    updateEdgeMenu(edge, vv, p);
                    edgePopup.show(vv, e.getX(), e.getY());
                }
            }
        }
    }
    
    private void updateVertexMenu(DirectedGraph graph, V v, VisualizationViewer vv, Point2D point) {
        if (vertexPopup == null) return;
        Component[] menuComps = vertexPopup.getComponents();
        for (Component comp: menuComps) {
            if (comp instanceof VertexMenuListener) {
                ((VertexMenuListener)comp).setVertexAndView(graph, v, vv);
            }
            if (comp instanceof MenuPointListener) {
                ((MenuPointListener)comp).setPoint(point);
            }
        }
        
    }
    
    /**
     * Getter for the edge popup.
     * @return 
     */
    public JPopupMenu getEdgePopup() {
        return edgePopup;
    }
    
    /**
     * Setter for the Edge popup.
     * @param edgePopup 
     */
    public void setEdgePopup(JPopupMenu edgePopup) {
        this.edgePopup = edgePopup;
    }
    
    /**
     * Getter for the vertex popup.
     * @return 
     */
    public JPopupMenu getVertexPopup() {
        return vertexPopup;
    }
    
    /**
     * Setter for the vertex popup.
     * @param vertexPopup 
     */
    public void setVertexPopup(JPopupMenu vertexPopup) {
        this.vertexPopup = vertexPopup;
    }
    
    private void updateEdgeMenu(E edge, VisualizationViewer vv, Point2D point) {
        if (edgePopup == null) return;
        Component[] menuComps = edgePopup.getComponents();
        for (Component comp: menuComps) {
            if (comp instanceof EdgeMenuListener) {
                ((EdgeMenuListener)comp).setEdgeAndView(edge, vv);
            }
            if (comp instanceof MenuPointListener) {
                ((MenuPointListener)comp).setPoint(point);
            }
        }
    }
    
}