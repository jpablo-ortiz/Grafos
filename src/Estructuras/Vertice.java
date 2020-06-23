package Estructuras;

import static Estructuras.Grafo.GrafoAbstracto.INFINITO;

/*1. Construya la clase Vertice.  
Deje campos para el contenido y otras cosas exigidas por los algoritmos.*/
public class Vertice<T>
{
    //===============================//
    //========== Atributos ==========//
    //===============================//

    private T valor;
    private int posicion; //Llenar al ingresar un vertice
    private int marca;
    private String figura; // "Circulo", "Cuadrado", "Rectangulo"
    
    //Para Dijkstra
    private double distancia;
    private Vertice procedencia;

    //===============================//
    //========= Constructor =========//
    //===============================//
    

    public Vertice(T valor, int posicion, int marca, String figura)
    {
        this.valor = valor;
        this.posicion = posicion;
        this.marca = marca;
        this.figura = figura;
        this.distancia = INFINITO;
    }

    /*public int compareTo(Vertice v)
    {
        if(this.getDistancia()> v.getDistancia())
        {
            return 1;
        }
        else if(this.getDistancia() < v.getDistancia())
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }*/
    
    //===============================//
    //====== Getters y Setters ======//
    //===============================//
    
    public T getValor()
    {
        return valor;
    }

    public void setValor(T valor)
    {
        this.valor = valor;
    }

    public int getPosicion()
    {
        return posicion;
    }

    public void setPosicion(int posicion)
    {
        this.posicion = posicion;
    }

    public int getMarca()
    {
        return marca;
    }

    public void setMarca(int marca)
    {
        this.marca = marca;
    }

    public String getFigura()
    {
        return figura;
    }

    public void setFigura(String figura)
    {
        this.figura = figura;
    }

    public double getDistancia()
    {
        return distancia;
    }

    public void setDistancia(double distancia)
    {
        this.distancia = distancia;
    }

    public Vertice getProcedencia()
    {
        return procedencia;
    }

    public void setProcedencia(Vertice procedencia)
    {
        this.procedencia = procedencia;
    }
    
    
    
}
