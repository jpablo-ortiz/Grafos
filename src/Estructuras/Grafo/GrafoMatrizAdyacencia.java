package Estructuras.Grafo;

import Estructuras.Vertice;
import Estructuras.Graficacion.Graficador;
import java.util.ArrayList;

/*3. Construya las tres implementaciones del grafo en sendas clases que hereden de la clase Grafo del punto anterior.  Se deben construir:
	- Grafo con matriz de adyacencias.*/
public class GrafoMatrizAdyacencia extends GrafoAbstracto
{
    //===============================//
    //========== Atributos ==========//
    //===============================//

    public ArrayList< ArrayList<Double>> m_adya;
    //private int n_nodos; sería vertices.size()
    //private Vertice[] nodos; ya existe en grafo abstracto "ArrayList<Vertice> vertices"

    //===============================//
    //======== Constructores ========//
    //===============================//
    
    public GrafoMatrizAdyacencia(ArrayList<ArrayList<Double>> m_adya)
    {
        this.m_adya = m_adya;
        vertices = new ArrayList<>();
    }

    public GrafoMatrizAdyacencia()
    {
        vertices = new ArrayList<>();
        m_adya = new ArrayList<>();
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
            m_adya.add(new ArrayList<>());
            for(int i = 0; i < m_adya.size(); i++)
            {
                //Poner una columna mas a las filas con infinito
                if(i < m_adya.size() - 1) // para omitir el ultimo ya que lo llena el de abajo
                {
                    m_adya.get(i).add(INFINITO);
                    //inicializar la nueva fila (vertice) con Infinitos
                    m_adya.get(m_adya.size() - 1).add(INFINITO);
                }
                else
                {
                    m_adya.get(i).add(0.0);
                }
            }
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
        //TODO
        //Tocaría quitar toda una fila y columna en la matriz de adjacencia (que puede estar en el medio) y quitar el vertice en Vertices
    }

    @Override
    public void agregarArista(Vertice desde, Vertice hasta, double Costo, boolean biDireccional)
    {
        if(vertices.contains(desde))
        {
            if(vertices.contains(hasta))
            {
                m_adya.get(desde.getPosicion()).set(hasta.getPosicion(), Costo);
                if(biDireccional)
                {
                    m_adya.get(hasta.getPosicion()).set(desde.getPosicion(), Costo);
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
        //TODO
        //Toca poner en infinito la posicion en la matriz que relaciona el DESDE y HASTA y si es bidireccional hacer mas
    }

    @Override
    public ArrayList<Vertice> vecinos(Vertice v)
    {
        ArrayList<Vertice> resultado = new ArrayList<>();

        for(int i = 0; i < this.vertices.size(); i++)
        {
            if(i != v.getPosicion() && m_adya.get(v.getPosicion()).get(i) < INFINITO)
            {
                //vecinos.add(this.nodos[i]);
                resultado.add(this.vertices.get(i));
            }
        }
        return resultado;
    }

    @Override
    public double costoArista(Vertice v1, Vertice v2)
    {
        if(m_adya.get(v1.getPosicion()).get(v2.getPosicion()) < INFINITO)
        {
            return m_adya.get(v1.getPosicion()).get(v2.getPosicion());
        }
        else
        {
            return INFINITO;
        }
    }

    @Override
    public void graficar(boolean dirigido)
    {
        //Inicializar Graficadora
        graficadora = new Graficador(dirigido);

        //Poner Vertices y Aristas
        //modificar porque no es nodos sino en grafo abstracto esta vertices (lo mismo) y es arraylist
        for(Vertice vertice : vertices)
        {
            graficadora.g.addVertex(vertice.getValor() + " {" + vertice.getPosicion() + "}");
        }
        for(int i = 0; i < m_adya.size(); i++)
        {
            for(int j = 0; j < m_adya.size(); j++)
            {
                if(m_adya.get(i).get(j) != INFINITO)
                {
                    graficadora.g.addEdge(Math.random() + "", vertices.get(i).getValor() + " {" + vertices.get(i).getPosicion() + "}", vertices.get(j).getValor() + " {" + vertices.get(j).getPosicion() + "}");
                }
            }
        }
        //Mostrar
        graficadora.agregarVertices(vertices);
        graficadora.mostrar(500, 500);
    }

}
