package Otros;

import Estructuras.Grafo.GrafoAbstracto;
import Estructuras.Grafo.GrafoListaAdyacencia;
import Estructuras.Grafo.GrafoListaAristas;
import Estructuras.Grafo.GrafoMatrizAdyacencia;
import java.awt.Container;
import javax.swing.JFrame;

public class Main
{

    public static void main(String[] args)
    {
        Main m = new Main();
        m.iniciar();
    }
    
    public void iniciar()
    {
        /*GrafoAbstracto g;
        
        //Lista de Adyacencia
        g = new GrafoListaAdyacencia(true);
        g.graficar(true);*/
        
        //Lista de Aristas
        //g = new GrafoListaAristas(true);
        //g.graficar(false);
        
        //Matriz Adyacencia
        //g = new GrafoMatrizAdyacencia(true);
        //g.graficar(true);
        
        

        // create a frame to hold the graph
        /*final JFrame frame = new JFrame();
        Container content = frame.getContentPane();
        content.add(new Graficacion2_Solo_Para_Ver_Ejemplo());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);*/
    }

    /*
4. Construya soluciones a los siguientes problemas de aplicación.  La clase que resuelve cada problema debe heredar de alguna de las implementaciones del punto anterior:
	- Ubicar 8 damas en un tablero de ajedrez de 8x8 (Pistas para solucionar este ejercicio se encuentran en [Boh92], sección 7.1)
	- Resolver el problema del burro, el león y el trigo.
	- Asignación de labores ([Boh92], ejercicio 7.6)
	- Ejercicios 4.21 y 5.26 de [Das08]
     */
}
