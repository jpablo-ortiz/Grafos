package Estructuras.Grafo;

import Estructuras.Vertice;
import Estructuras.Graficacion.Graficador;
import java.util.ArrayList;
import java.util.HashMap;

/*3. Construya las tres implementaciones del grafo en sendas clases que hereden de la clase Grafo del punto anterior.  Se deben construir:
	- Grafo con lista de vecinos de cada vértice.*/
public class GrafoListaAdyacencia extends GrafoAbstracto
{
    //===============================//
    //========== Atributos ==========//
    //===============================//

    //listaAdyacencia: Map[Vertice, Map[Vertice, Seq[Vertice]]]
    public HashMap< Vertice, HashMap<Vertice, Double>> listaAdyacencia; //El mapa interno significa el vertice adyacente y el costo desde el vertice origen hasta el vertice del map interno

    //===============================//
    //======== Constructores ========//
    //===============================//
    public GrafoListaAdyacencia(HashMap< Vertice, HashMap<Vertice, Double>> listaAdyacencia)
    {
        this.listaAdyacencia = listaAdyacencia;
        vertices = new ArrayList<>();
    }

    public GrafoListaAdyacencia()
    {
        listaAdyacencia = new HashMap<>();
        vertices = new ArrayList<>();
    }

    //===============================//
    //=========== Métodos ===========//
    //===============================//
    @Override
    public void agregarVertice(Vertice v)
    {
        if(!vertices.contains(v))
        {
            vertices.add(v);
            listaAdyacencia.put(v, new HashMap<>());
            //System.out.println("Vertice Registrado Exitosamente");
        }
        else
        {
            System.out.println("Ya existe este vertice");
        }
    }

    @Override
    public void eliminarVertice(Vertice v)
    {
        if(vertices.contains(v))
        {
            listaAdyacencia.remove(v);
            for(Vertice vertice : listaAdyacencia.keySet())
            {
                listaAdyacencia.get(vertice).remove(v);
            }
            vertices.remove(v);
            //System.out.println("Vertice eliminado Exitosamente");
        }
        else
        {
            System.out.println("No existe este vertice");
        }
    }

    @Override
    public void agregarArista(Vertice desde, Vertice hasta, double costo, boolean biDireccional)
    {
        if(listaAdyacencia.get(desde) != null)
        {
            if(listaAdyacencia.get(hasta) != null)
            {
                listaAdyacencia.get(desde).put(hasta, costo);
                if(biDireccional)
                {
                    listaAdyacencia.get(hasta).put(desde, costo);
                }
                //System.out.println("Arista regitrada Exitosamente");
            }
            else
            {
                System.out.println("No existe el Vertice hasta");
            }
        }
        else
        {
            System.out.println("No existe el Vertice desde");
        }
    }

    @Override
    public void eliminarArista(Vertice desde, Vertice hasta, double costo, boolean biDireccional)
    {
        listaAdyacencia.get(desde).remove(hasta);
        if(biDireccional)
        {
            listaAdyacencia.get(hasta).remove(desde);
        }
    }

    @Override
    public ArrayList<Vertice> vecinos(Vertice v)
    {
        return new ArrayList<>(listaAdyacencia.get(v).keySet()); //TODO .values() hasta ahi retorna un collection de arreglo de vertices y debo devolver solo un arreglo de vertices
    }

    @Override
    public double costoArista(Vertice v1, Vertice v2)
    {
        return listaAdyacencia.get(v1).getOrDefault(v2, INFINITO);
    }

    @Override
    public void graficar(boolean dirigido)
    {
        //Inicializar Graficadora
        graficadora = new Graficador(dirigido);

        //Poner Vertices y Aristas
        for(Vertice vertice : listaAdyacencia.keySet())
        {
            graficadora.g.addVertex(vertice.getValor() + " {" + vertice.getPosicion() + "}");
            for(Vertice adyacente : listaAdyacencia.get(vertice).keySet())
            {
                graficadora.g.addEdge(Math.random() + "", vertice.getValor() + " {" + vertice.getPosicion() + "}", adyacente.getValor() + " {" + adyacente.getPosicion() + "}");
            }
        }
        //Mostrar
        graficadora.agregarVertices(vertices);
        graficadora.mostrar(700, 700);
    }

}
