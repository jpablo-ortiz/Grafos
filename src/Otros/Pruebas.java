package Otros;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.PickingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.PluggableGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

    public class Pruebas
    {

        Graph<Integer, String> g;

        /**
         * Creates a new instance of SimpleGraphView
         */
        public Pruebas()
        {
            // Graph<V, E> where V is the type of the vertices and E is the type of the edges
            g = new SparseMultigraph<Integer, String>();
            // Add some vertices and edges
            g.addVertex((Integer) 1);
            g.addVertex((Integer) 2);
            g.addVertex((Integer) 3);
            g.addEdge("Edge-A", 1, 2);
            g.addEdge("Edge-B", 2, 3);
        }

        /**
         * @param args the command line arguments
         */
        public static void main(String[] args)
        {
            Pruebas sgv = new Pruebas(); // Creates the graph...
            // Layout<V, E>, VisualizationViewer<V,E>
            Layout<Integer, String> layout = new CircleLayout(sgv.g);
            layout.setSize(new Dimension(300, 300));
            VisualizationViewer<Integer, String> vv = new VisualizationViewer<Integer, String>(layout);
            vv.setPreferredSize(new Dimension(350, 350));
            // Show vertex and edge labels
            vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
            vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());

            // Create our "custom" mouse here. We start with a PluggableGraphMouse
            // Then add the plugins you desire.
            PluggableGraphMouse gm = new PluggableGraphMouse();
            gm.add(new PickingGraphMousePlugin(MouseEvent.BUTTON1_MASK, 0));

            vv.setGraphMouse(gm);
            JFrame frame = new JFrame("Interactive Graph View 3");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(vv);
            frame.pack();
            frame.setVisible(true);
        }

    }

