package Estructuras.Graficacion;

import Estructuras.Vertice;
import java.awt.Dimension;
import javax.swing.JFrame;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.AbstractGraph;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.PickingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.PluggableGraphMouse;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Graficador
{
    //===============================//
    //========== Atributos ==========//
    //===============================//
    
    public AbstractGraph<String, String> g;
    public Color color1, color2, color3;
    public ArrayList<Vertice> figura;

    //===============================//
    //========= Constructor =========//
    //===============================//
    
    public Graficador(boolean dirigido)
    {
        
        color1 = new Color((float) (Math.random() % 255), (float) (Math.random() % 255), (float) (Math.random() % 255));
        color2 = new Color((float) (Math.random() % 255), (float) (Math.random() % 255), (float) (Math.random() % 255));
        color3 = new Color((float) (Math.random() % 255), (float) (Math.random() % 255), (float) (Math.random() % 255));
        if (dirigido)
        {
            g = new DirectedSparseGraph<>(); //Grafo Dirigido
        }
        else
        {
            g = new UndirectedSparseGraph<>(); //Grafo No Dirigido
        }
    }
    
    //===============================//
    //=========== Métodos ===========//
    //===============================//

    public void agregarVertices(ArrayList<Vertice> figura)
    {
        this.figura = figura;
    }

    public void graficacionPrueba()
    {
        g.addVertex("Vertex1");
        g.addVertex("Vertex2");
        g.addVertex("Vertex3");
        g.addEdge("Edge1", "Vertex1", "Vertex2");
        g.addEdge("Edge2", "Vertex1", "Vertex3");
        g.addEdge("Edge3", "Vertex3", "Vertex1");
        mostrar(500,500);
    }

    public void mostrar(int ancho, int alto)
    {
        VisualizationViewer<String, String> vs = new VisualizationViewer<>(new CircleLayout<String, String>(g), new Dimension(ancho, alto));

        agregarComponentes(vs);

        JFrame frame = new JFrame("Proyecto Algorítmos Mostrar Grafo");
        frame.getContentPane().add(vs);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void agregarComponentes(VisualizationViewer<String, String> vs)
    {
        //Agregar Nombres de Vertices
        vs.getRenderContext().setVertexLabelTransformer((String arg0) -> arg0);
        
        //Agregar Nombres de Aristas
        //vs.getRenderContext().setEdgeLabelTransformer((String arg0) -> arg0);;
        
        //Agragar la opcion de mover vertices
        PluggableGraphMouse gm = new PluggableGraphMouse();
        gm.add(new PickingGraphMousePlugin(MouseEvent.BUTTON1_MASK, 0));
        vs.setGraphMouse(gm);

        //Agregar figura y color a cada vertice
        vs.getRenderer().setVertexRenderer(new Renderer.Vertex<String, String>()
        {
            @Override
            public void paintVertex(RenderContext<String, String> rc, Layout<String, String> layout, String vertex)
            {
                GraphicsDecorator graphicsContextt = rc.getGraphicsContext();
                GraphicsDecorator graphicsContext = rc.getGraphicsContext();
                Point2D center = layout.transform(vertex);
                Shape shape;

                String fig = "Circulo";
                for (Vertice vertice : figura)
                {
                    if(vertex.equals(vertice.getValor()+" {"+vertice.getPosicion()+"}"))
                    {
                        fig = vertice.getFigura();
                    }
                }

                Color delFinal = color1;
                if(fig.equals("Cuadrado"))
                {
                    shape = new Rectangle((int) center.getX() - 10, (int) center.getY() - 10, 20, 20);
                    delFinal = color2;
                }
                else if (fig.equals("Rectangulo"))
                {
                    shape = new Rectangle((int) center.getX() - 10, (int) center.getY() - 20, 20, 40);
                    delFinal = color3;
                }
                else if (fig.equals("Circulo"))
                {
                    shape = new Ellipse2D.Double(center.getX() - 10, center.getY() - 10, 20, 20);
                }
                else
                {
                    shape = new Ellipse2D.Double(center.getX() - 10, center.getY() - 10, 20, 20);
                }

                graphicsContextt.setColor(delFinal);
                graphicsContextt.fill(shape);

                graphicsContext.setColor(new Color(0, 0, 0));
                graphicsContext.draw(shape);

            }
        }
        );
    }

}
