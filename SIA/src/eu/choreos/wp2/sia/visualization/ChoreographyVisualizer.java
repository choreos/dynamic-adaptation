package eu.choreos.wp2.sia.visualization;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.apache.commons.collections15.Transformer;

import sun.tools.jar.resources.jar_zh_HK;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.util.Relaxer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.decorators.PickableEdgePaintTransformer;
import edu.uci.ics.jung.visualization.decorators.PickableVertexPaintTransformer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import eu.choreos.wp2.sia.analysis.JungAnalyzer;
import eu.choreos.wp2.sia.analysis.converters.CoordinationDelegatesToGraphConverter;
import eu.choreos.wp2.sia.analysis.entity.report.AntiPattern;
import eu.choreos.wp2.sia.analysis.entity.report.AntiPatternReport;
import eu.choreos.wp2.sia.graph.entity.Edge;
import eu.choreos.wp2.sia.graph.entity.Vertex;
import eu.choreos.wp2.sia.visualization.sample.DullCoordinationDelegatesToGraphConverter;

public class ChoreographyVisualizer {

	public ChoreographyVisualizer(final AntiPatternReport antiPatternReport){
		JFrame jFrame = buildJFrame(antiPatternReport);
		jFrame.setVisible(true);
	}

	private JFrame buildJFrame(AntiPatternReport antiPatternReport) {
		JFrame frame = new JFrame("Choreography Analyzer");
		
		//TODO:Menu bar
		JMenuBar jMenuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		fileMenu.add(exitItem);
		
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		
		JMenuItem aboutItem = new JMenuItem("About");
		helpMenu.add(aboutItem);
		
		jMenuBar.add(fileMenu);
		jMenuBar.add(helpMenu);
		frame.setJMenuBar(jMenuBar);
				
		//Graph Panel (on center)
		VisualizationViewer<Vertex, Edge> graphPanel = 
				buildVisualizationViewer(antiPatternReport);
		frame.getContentPane().add(graphPanel, BorderLayout.CENTER);
		
		//TODO: Info Panel
        /**
		JPanel infoPanel = buildInfoPanel();
        frame.getContentPane().add(infoPanel, BorderLayout.EAST);
		*/
		
		//Control Panel (on south)
        JPanel controlPanel = buildControlsPanel(graphPanel);
        frame.getContentPane().add(controlPanel, BorderLayout.SOUTH);
        
        //Other stuff
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.pack();
		return frame;
	}
	
	private VisualizationViewer<Vertex, Edge> buildVisualizationViewer(
			final AntiPatternReport antiPatternReport) {
		
		// Creates the visualization viewer
		VisualizationViewer<Vertex, Edge> vv = 
				new VisualizationViewer<Vertex, Edge>(
						new FRLayout<Vertex, Edge>(
								antiPatternReport.getDependencyGraph(), 
								new Dimension(600,480)));
		
		vv.setPreferredSize(new Dimension(640,480)); //Sets the viewing area size
		vv.setBackground(Color.WHITE);
		
		//Sets transformers
		Transformer<Vertex, Paint> vertexPaintTransformer = 
				buildVertexPaintTransformer(vv);
		vv.getRenderContext().setVertexFillPaintTransformer(vertexPaintTransformer);
		
		Transformer<Edge, Paint> edgePaintTransformer = 
				buildEdgePaintTransformer(vv);
		vv.getRenderContext().setEdgeDrawPaintTransformer(edgePaintTransformer);
		
		Transformer<Vertex, String> tooltipTransformer = buildTooltipTransformer(antiPatternReport);
		vv.setVertexToolTipTransformer(tooltipTransformer);
		
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Vertex>());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.E);		
		
		//Sets the graphMouse
		DefaultModalGraphMouse<Vertex, Edge> gm = buildGraphMouse(antiPatternReport);
		vv.setGraphMouse(gm);
		
		return vv;
	}

	private Transformer<Vertex, String> buildTooltipTransformer(
			final AntiPatternReport antiPatternReport) {
		//Creates the appropriate tooltip
		Transformer<Vertex,String> tooltipTransformer = new Transformer<Vertex,String>(){
			public String transform(Vertex v) {
				Collection<AntiPattern> antiPatterns = 
						antiPatternReport.getAntiPatterns(v);
				return antiPatterns.toString();
			}
		};
		return tooltipTransformer;
	}

	private Transformer<Vertex, Paint> buildVertexPaintTransformer(
			VisualizationViewer<Vertex, Edge> vv) {
		//Paints the vertices
		/**
		Transformer<Vertex,Paint> paintTransformer = new Transformer<Vertex,Paint>() {
			public Paint transform(Vertex v) {
				if (antiPatternReport.isAntiPattern(v)){
					return Color.RED;
				}
				else{
					return Color.YELLOW;
				}
			}
		};
		return paintTransformer;
		*/
		Transformer<Vertex, Paint> vertexPaintTransformer = 
				new PickableVertexPaintTransformer<Vertex>(
						vv.getPickedVertexState(), Color.white, Color.yellow);
          
		return vertexPaintTransformer;
	}

	private Transformer<Edge, Paint> buildEdgePaintTransformer(
			VisualizationViewer<Vertex, Edge> vv) {
		
		Transformer<Edge, Paint> edgePaintTransformer = 
				new PickableEdgePaintTransformer<Edge>(
						vv.getPickedEdgeState(), Color.black, Color.red);
						
		return edgePaintTransformer;
	}

	private JPanel buildControlsPanel(final VisualizationViewer<Vertex, Edge> vv) {
		
		//Scale Panel
		final ScalingControl scaler = new CrossoverScalingControl();
        JButton plus = new JButton("+");
        plus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scaler.scale(vv, 1.1f, vv.getCenter());
            }
        });
        JButton minus = new JButton("-");
        minus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scaler.scale(vv, 1 / 1.1f, vv.getCenter());
            }
        });

        JPanel scalePanel = new JPanel();
        scalePanel.setBorder(BorderFactory.createTitledBorder("Zoom"));
        scalePanel.add(plus);
        scalePanel.add(minus);

		//Mouse mode panel        
        final DefaultModalGraphMouse graphMouse = 
        		(DefaultModalGraphMouse)vv.getGraphMouse();
        JComboBox modeBox = graphMouse.getModeComboBox();
        
        JPanel mouseModePanel = new JPanel();
        mouseModePanel.setBorder(BorderFactory
                .createTitledBorder("Mouse Mode"));
        mouseModePanel.add(modeBox);

        //Reset panel
        JButton reset = new JButton("RESET");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            	//Reset the layout
            	Layout<Vertex, Edge> layout = vv.getGraphLayout();
                layout.initialize();
                Relaxer relaxer = vv.getModel().getRelaxer();
                if (relaxer != null) {
                    relaxer.stop();
                    relaxer.prerelax();
                    relaxer.relax();
                }
            }
        });
        
        JPanel resetPanel = new JPanel();
        resetPanel.setBorder(BorderFactory
                .createTitledBorder("Reset"));
        resetPanel.add(reset);

        JPanel controls = new JPanel(); 
        controls.add(scalePanel);
        controls.add(mouseModePanel);
        controls.add(resetPanel);
		return controls;
	}
	
	private DefaultModalGraphMouse<Vertex, Edge> buildGraphMouse(
			final AntiPatternReport antiPatternReport) {
		
		PopupVertexEdgeMenuMousePlugin popupMousePlugin = 
				new PopupVertexEdgeMenuMousePlugin(
						antiPatternReport.getDependencyGraph());
		
		popupMousePlugin.setVertexPopup(new MyMouseMenus.VertexMenu());
		popupMousePlugin.setEdgePopup(new MyMouseMenus.EdgeMenu());
		
		DefaultModalGraphMouse<Vertex, Edge> gm = 
				new DefaultModalGraphMouse<Vertex, Edge>();
		
		gm.setMode(ModalGraphMouse.Mode.PICKING);
		gm.add(popupMousePlugin);
		return gm;
	}

	private JPanel buildInfoPanel() {
		JPanel infoPanel = new JPanel();
        JTextArea jTextArea = new JTextArea("Some text");
        infoPanel.add(jTextArea);
        return infoPanel;
	}

	public static void main(String[] args) {
		
		CoordinationDelegatesToGraphConverter dullCoordelConverter = 
				new DullCoordinationDelegatesToGraphConverter();
		
		JungAnalyzer analyzer = new JungAnalyzer(null, dullCoordelConverter);
		AntiPatternReport antiPatternReport = analyzer.findAntiPatterns(null);
		
		new ChoreographyVisualizer(antiPatternReport);
	}
	
}
