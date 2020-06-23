package Otros;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.AbstractModalGraphMouse;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.BasicVertexLabelRenderer.InsidePositioner;
import edu.uci.ics.jung.visualization.renderers.GradientVertexRenderer;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import org.apache.commons.collections15.Transformer;
import org.apache.commons.collections15.functors.ChainedTransformer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@yakubu please no more changes to file.
/**
 * Shows a graph overlaid on a world map image. Scaling of the graph also scales
 * the image background.
 *
 * @author Tom Nelson
 *
 */
@SuppressWarnings("serial")
public class Graficacion2_Solo_Para_Ver_Ejemplo extends JApplet
{

    /**
     * the graph
     */
    Graph<String, Number> graph;

    /**
     * the visual component and renderer for the graph
     */
    VisualizationViewer<String, Number> vv;

    Map<String, String[]> map = new HashMap<String, String[]>();
    List<String> nodoList;

    /**
     * create an instance of a simple graph with controls to demo the zoom
     * features.
     */
    int ImageWidth = 0;
    int ImageHeight = 0;

    //version test
    /**
     * Configuraci�n
     */
    //jia
    private int MonitorWidth = 1200;
    private int MonitorHeight = 600;
    private static double OrigenLatitude = -0.225;
    private static double OrigenLongitude = -78.525;
    private static double Intervalo = 0.025;
    private static double CuadradosHorizontales = 4;
    private static double CuadradosVerticales = 6;

    public Graficacion2_Solo_Para_Ver_Ejemplo()
    {
        setLayout(new BorderLayout());

        //definir puntos de trolebus (latitude y longitude) @autor sa
        map.put("RECREO", new String[]
        {
            "-0.2516682", "-78.521524"
        });	//Recreo                       
        map.put("P14", new String[]
        {
            "-0.2445098", "-78.51902"
        });	//Villaflora
        map.put("P15", new String[]
        {
            "-0.2396436", "-78.51698"
        });	//Chimbacalle N-S                      
        map.put("P16", new String[]
        {
            "-0.2378458", "-78.515976"
        });	//Chimbacalle S-N
        map.put("P17", new String[]
        {
            "-0.2356805", "-78.514816"
        });	//Colina
        map.put("P18", new String[]
        {
            "-0.234052", "-78.514237"
        });	//Jefferson Perez
        map.put("P19", new String[]
        {
            "-0.2312856", "-78.513627"
        });	//Recoleta N-S        
        map.put("P20", new String[]
        {
            "-0.2307005", "-78.513051"
        });	//Recoleta S-N
        map.put("P21", new String[]
        {
            "-0.2263919", "-78.513011"
        });	//P21 Cumanda N-S
        map.put("P22", new String[]
        {
            "-0.226424", "-78.512803"
        });	//P22 Cumanda S-N
        map.put("P23", new String[]
        {
            "-0.2234658", "-78.512542"
        });	//P23 Santo Domingo
        map.put("P24", new String[]
        {
            "-0.2185857", "-78.508601"
        });	//P24 Plaza del Teatro N-S
        map.put("P25", new String[]
        {
            "-0.219605", "-78.50813"
        });	//P25 Plaza del Teatro S-N        
        map.put("P26", new String[]
        {
            "-0.2177808", "-78.505977"
        });	//P26 Hermano Miguel
        map.put("P27", new String[]
        {
            "-0.2169088", "-78.50521"
        });	//P27 Banco Central
        map.put("P28", new String[]
        {
            "-0.214267", "-78.502999"
        });	//P28 La Alameda S-N
        map.put("P29", new String[]
        {
            "-0.2137705", "-78.50293"
        });	//P29 La Alameda N-S                       
        map.put("P30", new String[]
        {
            "-0.2084939", "-78.500255"
        });	//P30 Ejido N-S
        map.put("P31", new String[]
        {
            "-0.2088076", "-78.500032"
        });	//P31 Ejido S-N
        map.put("P32", new String[]
        {
            "-0.2047989", "-78.4988"
        });	//P32 La Mariscal N-S
        map.put("P33", new String[]
        {
            "-0.2041972", "-78.498491"
        });	//P33 La Mariscal S-N
        map.put("P34", new String[]
        {
            "-0.2009718", "-78.49715"
        });	//P34 Santa Clara S-N
        map.put("P35", new String[]
        {
            "-0.201056", "-78.496979"
        });	//P35 Santa Clara N-S
        map.put("P36", new String[]
        {
            "-0.1986325", "-78.496141"
        });	//P36 La Colon S-N
        map.put("P37", new String[]
        {
            "-0.1978432", "-78.495563"
        });	//P37 La Colon N-S
        map.put("P38", new String[]
        {
            "-0.1921587", "-78.493445"
        });	//P38 Cuero y Caicedo S-N
        map.put("P39", new String[]
        {
            "-0.1915098", "-78.493001"
        });	//P39 Cuero y Caicedo N-S                        
        map.put("P40", new String[]
        {
            "-0.1889467", "-78.492149"
        });	//P40 Mariana de Jes�s S-N
        map.put("P41", new String[]
        {
            "-0.1875567", "-78.491303"
        });	//P41 Mariana de Jesus N-S
        map.put("P42", new String[]
        {
            "-0.1853693", "-78.490878"
        });	//P42 El Floron S-N
        map.put("P43", new String[]
        {
            "-0.1846687", "-78.490403"
        });	//P43 El Floron N-S        
        map.put("P44", new String[]
        {
            "-0.1817679", "-78.489808"
        });	//P44 Carolina S-N
        map.put("P45", new String[]
        {
            "-0.1810849", "-78.489336"
        });	//P45 Carolina N-S
        map.put("P46", new String[]
        {
            "-0.1787274", "-78.488954"
        });	//P46 Estadio S-N
        map.put("P47", new String[]
        {
            "-0.1780172", "-78.488621"
        });	//P47 Estadio N-S
        map.put("P48", new String[]
        {
            "-0.172087", "-78.487589"
        });	//P48 La Y S-N
        map.put("P49", new String[]
        {
            "-0.1713146", "-78.487277"
        });	//P49 La Y N-S        
        map.put("LA Y", new String[]
        {
            "-0.1635504", "-78.485374"
        });	//Estaci�n La Y                              

        nodoList = new ArrayList<String>(map.keySet());

        // create a simple graph for the demo        
        graph = new DirectedSparseMultigraph<String, Number>();
        createVertices();
        createEdges();

        ImageIcon mapIcon = null;
        String imageLocation = "/Otros/mapa_quito.png";

        try
        {
            mapIcon = new ImageIcon(getClass().getResource(imageLocation));

            ImageWidth = mapIcon.getIconWidth();
            ImageHeight = mapIcon.getIconHeight();

        } catch (Exception ex)
        {
            System.err.println("Can't load \"" + imageLocation + "\"");
        }

        final ImageIcon icon = mapIcon;

        Dimension layoutSize = new Dimension(ImageWidth, ImageHeight);

        Layout<String, Number> layout = 
            new StaticLayout<String, Number>
            (graph,
                new ChainedTransformer<String, Point2D>
                (
                        new Transformer[]
                        {
                            new CityTransformer(map),
                            new LatLonPixelTransformer(new Dimension(ImageWidth, ImageHeight))
                        }
                )
            );

        layout.setSize(layoutSize);
        vv = new VisualizationViewer<String, Number>(layout,
                new Dimension(MonitorWidth, MonitorHeight));

        if (icon != null)
        {
            vv.addPreRenderPaintable(new VisualizationViewer.Paintable()
            {
                public void paint(Graphics g)
                {
                    Graphics2D g2d = (Graphics2D) g;
                    AffineTransform oldXform = g2d.getTransform();
                    AffineTransform lat
                            = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).getTransform();
                    AffineTransform vat
                            = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW).getTransform();
                    AffineTransform at = new AffineTransform();
                    at.concatenate(g2d.getTransform());
                    at.concatenate(vat);
                    at.concatenate(lat);
                    g2d.setTransform(at);
                    g.drawImage(icon.getImage(), 0, 0,
                            icon.getIconWidth(), icon.getIconHeight(), vv);
                    g2d.setTransform(oldXform);
                }

                public boolean useTransform()
                {
                    return false;
                }
            });
        }

        vv.getRenderer().setVertexRenderer(
                new GradientVertexRenderer<String, Number>(
                        Color.white, Color.red,
                        Color.white, Color.blue,
                        vv.getPickedVertexState(),
                        false));

        // add my listeners for ToolTips
        vv.setVertexToolTipTransformer(new ToStringLabeller<String>());
        vv.setEdgeToolTipTransformer(new Transformer<Number, String>()
        {
            public String transform(Number edge)
            {
                return "E" + graph.getEndpoints(edge).toString();
            }
        });

        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
        vv.getRenderer().getVertexLabelRenderer().setPositioner(new InsidePositioner());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.AUTO);

        final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
        add(panel);
        final AbstractModalGraphMouse graphMouse = new DefaultModalGraphMouse<Object, Object>();
        vv.setGraphMouse(graphMouse);

        vv.addKeyListener(graphMouse.getModeKeyListener());
        vv.setToolTipText("<html><center>Type 'p' for Pick mode<p>Type 't' for Transform mode");

        final ScalingControl scaler = new CrossoverScalingControl();

        vv.scaleToLayout(scaler);

        JButton plus = new JButton("+");
        plus.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                scaler.scale(vv, 1.1f, vv.getCenter());
            }
        });
        JButton minus = new JButton("-");
        minus.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                scaler.scale(vv, 1 / 1.1f, vv.getCenter());
            }
        });

        JButton reset = new JButton("reset");
        reset.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).setToIdentity();
                vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW).setToIdentity();
            }
        });

        JPanel controls = new JPanel();
        controls.add(plus);
        controls.add(minus);
        controls.add(reset);
        add(controls, BorderLayout.SOUTH);
    }

    /**
     * create some vertices
     *
     * @return the Vertices in an array
     */
    private void createVertices()
    {
        for (String nodo : map.keySet())
        {
            graph.addVertex(nodo);
        }
    }

    /**
     * create edges for this demo graph
     */
    void createEdges()
    {

        for (int i = 0; i < map.keySet().size() * 1.3; i++)
        {
            graph.addEdge(new Double(Math.random()), randomNodo(), randomNodo(), EdgeType.DIRECTED);
        }
    }

    private String randomNodo()
    {
        int m = nodoList.size();
        return nodoList.get((int) (Math.random() * m));
    }

    static class CityTransformer implements Transformer<String, String[]>
    {

        Map<String, String[]> map;

        public CityTransformer(Map<String, String[]> map)
        {
            this.map = map;
        }

        /**
         * transform airport code to latlon string
         */
        @Override
        public String[] transform(String city)
        {
            return map.get(city);
        }
    }

    static class LatLonPixelTransformer implements Transformer<String[], Point2D>
    {

        Dimension d;
        int startOffset;

        public LatLonPixelTransformer(Dimension d)
        {
            this.d = d;
        }

        /**
         * transform a lat
         */

        public Point2D transform(String[] latlon)
        {

            double latitude = 0;
            double longitude = 0;

            latitude = Double.parseDouble(latlon[0]);
            latitude = Math.abs(OrigenLatitude) - Math.abs(latitude);
            latitude *= (d.height / (Intervalo * CuadradosVerticales));

            longitude = Double.parseDouble(latlon[1]);
            longitude = Math.abs(OrigenLongitude) - Math.abs(longitude);
            longitude *= (d.width / (Intervalo * CuadradosHorizontales));

            if (latitude > 0)
            {
                latitude = d.height / 2 - latitude;
            }
            else
            {
                latitude = d.height / 2 - latitude;
            }

            if (longitude > 0)
            {
                longitude = d.width / 2 + longitude;
            }
            else
            {
                longitude = d.width / 2 - longitude;
            }

            return new Point2D.Double(longitude, latitude);
        }
    }

    /**
     * a driver for this demo
     */
    public static void main(String[] args)
    {
        // create a frame to hold the graph
        final JFrame frame = new JFrame();
        Container content = frame.getContentPane();
        content.add(new Graficacion2_Solo_Para_Ver_Ejemplo());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

/*package Otros;
        
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;
 */
/**
 * Second JUNG example: Three vertices and three edges. Vertices and edges have
 * labels. The figure of the vertices depends on the label in the vertex. The
 * edges and the vertices can be picked.
 *
 * @author Arieh 'Vainolo' Bibliowicz
 *
 */
/*public class Graficacion2_Solo_Para_Ver_Ejemplo
{

    public Graficacion2_Solo_Para_Ver_Ejemplo()
    {
        DirectedSparseGraph<String, String> g = new DirectedSparseGraph<String, String>();
        g.addVertex("Square");
        g.addVertex("Rectangle");
        g.addVertex("Circle");
        g.addEdge("Edge1", "Square", "Rectangle");
        g.addEdge("Edge2", "Square", "Circle");
        g.addEdge("Edge3", "Circle", "Square");
        VisualizationViewer<String, String> vs = new VisualizationViewer<String, String>(new CircleLayout<String, String>(g), new Dimension(400, 400));

        vs.getRenderContext().setVertexLabelTransformer(new Transformer<String, String>()
        {
            @Override
            public String transform(String arg0)
            {
                return arg0;
            }
        });
        vs.getRenderContext().setEdgeLabelTransformer(new Transformer<String, String>()
        {
            @Override
            public String transform(String arg0)
            {
                return arg0;
            }
        });

        vs.getRenderer().setVertexRenderer(new MyRenderer());

        final DefaultModalGraphMouse<String, Number> graphMouse = new DefaultModalGraphMouse<String, Number>();
        graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
        vs.setGraphMouse(graphMouse);

        JFrame frame = new JFrame();
        frame.getContentPane().add(vs);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    static class MyRenderer implements Renderer.Vertex<String, String>
    {

        @Override
        public void paintVertex(RenderContext<String, String> rc, Layout<String, String> layout, String vertex)
        {
            GraphicsDecorator graphicsContext = rc.getGraphicsContext();
            Point2D center = layout.transform(vertex);
            Shape shape = null;
            Color color = null;
            if (vertex.equals("Square"))
            {
                shape = new Rectangle((int) center.getX() - 10, (int) center.getY() - 10, 20, 20);
                color = new Color(127, 127, 0);
            }
            else if (vertex.equals("Rectangle"))
            {
                shape = new Rectangle((int) center.getX() - 10, (int) center.getY() - 20, 20, 40);
                color = new Color(127, 0, 127);
            }
            else if (vertex.equals("Circle"))
            {
                shape = new Ellipse2D.Double(center.getX() - 10, center.getY() - 10, 20, 20);
                color = new Color(0, 127, 127);
            }
            graphicsContext.setPaint(color);
            graphicsContext.fill(shape);
        }

    }
}*/
