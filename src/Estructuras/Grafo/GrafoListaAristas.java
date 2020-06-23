package Estructuras.Grafo;

import Estructuras.Arista;
import Estructuras.Vertice;
import Estructuras.Graficacion.Graficador;
import java.util.ArrayList;

/*3. Construya las tres implementaciones del grafo en sendas clases que hereden de la clase Grafo del punto anterior.  Se deben construir:
	- Grafo con lista de aristas.*/
public class GrafoListaAristas extends GrafoAbstracto
{
    //===============================//
    //========== Atributos ==========//
    //===============================//

    //private Arista[] listaAristas;
    public ArrayList<Arista> listaAristas;

    //===============================//
    //======== Constructores ========//
    //===============================//
    public GrafoListaAristas(ArrayList<Vertice> vertices, ArrayList<Arista> listaAristas)
    {
        this.listaAristas = listaAristas;
        this.vertices = vertices;
    }

    public GrafoListaAristas()
    {
        listaAristas = new ArrayList<>();
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
            System.out.println("Vertice Registrado Exitosamente");
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
            vertices.remove(v);
            this.eliminarArista(v, null, INFINITO, true);
            System.out.println("Vertice eliminado Exitosamente");
        }
        else
        {
            System.out.println("No existe este vertice");
        }
    }

    @Override
    public void agregarArista(Vertice desde, Vertice hasta, double costo, boolean biDireccional)
    {
        if(vertices.contains(desde))
        {
            if(vertices.contains(hasta))
            {
                listaAristas.add(new Arista(desde, hasta, costo));
                if(biDireccional)
                {
                    listaAristas.add(new Arista(hasta, desde, costo));
                }
                System.out.println("Arista regitrada Exitosamente");
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
        //Se usa cuando se elimina un vertice y se deben borrar todas las aristas que lo contengan
        if(hasta == null)
        {
            for(Arista aristaActual : listaAristas)
            {
                if(aristaActual.getHasta() == desde || aristaActual.getDesde() == desde)
                {
                    listaAristas.remove(aristaActual);
                }
            }
        }
        else
        {
            for(Arista aristaActual : listaAristas)
            {
                if(biDireccional && aristaActual.getHasta() == hasta && aristaActual.getCosto() == costo)
                {
                    listaAristas.remove(aristaActual);
                }
                if(aristaActual.getDesde() == desde && aristaActual.getCosto() == costo)
                {
                    listaAristas.remove(aristaActual);
                }
            }
        }
    }

    @Override
    public ArrayList<Vertice> vecinos(Vertice v)
    {
        ArrayList<Vertice> resultado = new ArrayList<>();

        for(Arista a : listaAristas)
        {
            if(v == a.getDesde())
            {
                resultado.add(a.getHasta());
            }
            else if(v == a.getHasta())
            {
                resultado.add(a.getDesde());
            }
        }
        return resultado;
    } //(n-1) + (n-2) + (n-3) + ... + 2 + 1 = O(n^2)

    @Override
    public double costoArista(Vertice v1, Vertice v2)
    {
        for(Arista a : listaAristas)
        {
            if((v1 == a.getDesde()) && (v2 == a.getHasta()) || (v2 == a.getDesde()) && (v1 == a.getHasta()))
            {
                return (double) a.getCosto();
            }
        }
        return INFINITO;
    }

    @Override
    public void graficar(boolean dirigido)
    {
        //Inicializar Graficadora
        graficadora = new Graficador(dirigido);

        //Poner Vertices y Aristas
        for(Arista a : listaAristas)
        {
            graficadora.g.addVertex(a.getDesde().getValor() + " {" + a.getDesde().getPosicion() + "}");
            graficadora.g.addVertex(a.getHasta().getValor() + " {" + a.getHasta().getPosicion() + "}");
            graficadora.g.addEdge(Math.random() + "", a.getDesde().getValor() + " {" + a.getDesde().getPosicion() + "}", a.getHasta().getValor() + " {" + a.getHasta().getPosicion() + "}");
        }
        //Mostrar
        graficadora.agregarVertices(vertices);
        graficadora.mostrar(500, 500);
    }

}
