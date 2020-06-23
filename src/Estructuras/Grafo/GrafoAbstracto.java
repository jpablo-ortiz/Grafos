package Estructuras.Grafo;

import Estructuras.Arista;
import Estructuras.Vertice;
import Estructuras.Graficacion.Graficador;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/*2. Construya la clase abstracta Grafo.  En esta clase deben aparecer los siguientes métodos:
   	//- Vecinos de un vértice (abstracto).
	//- Costo de una arista (abstracto).
	- Búsqueda en profundidad.
	- Búsqueda en anchura.
	- Dijkstra.
	- Bellman-Ford
	- Prim
	- Kruskal
Los métodos que no son abstractos deben ser agnósticos a la implementación particular del grafo.*/
public abstract class GrafoAbstracto
{
    //===============================//
    //========== Atributos ==========//
    //===============================//

    public ArrayList<Vertice> vertices;
    public final static double INFINITO = Double.POSITIVE_INFINITY;
    public Graficador graficadora;

    //===============================//
    //====== Métodos Abstractos =====//
    //===============================//
    public abstract ArrayList<Vertice> vecinos(Vertice v);

    public abstract double costoArista(Vertice v1, Vertice v2);

    public abstract void agregarVertice(Vertice v);

    public abstract void agregarArista(Vertice desde, Vertice hasta, double costo, boolean biDireccional);

    public abstract void eliminarVertice(Vertice v);

    public abstract void eliminarArista(Vertice desde, Vertice hasta, double costo, boolean biDireccional);

    public abstract void graficar(boolean dirigido);

    //===============================//
    //==== Algoritmos de Búsqueda ===//
    //===============================//
    public ArrayList<Vertice> encontrarRutaMinimaDijkstra(Vertice inicio, Vertice fin)
    {
        if(!Dijkstra(inicio).contains(fin))
        {
            System.out.println("Error, nodo no alcanzable");
            return null;
        }
        Vertice tmp = fin;
        ArrayList<Vertice> camino = new ArrayList<>();
        while(tmp != null)
        {
            camino.add(tmp);
            tmp = tmp.getProcedencia();
        }
        Collections.reverse(camino);
        return camino;
    }

    public ArrayList<Vertice> Dijkstra(Vertice v)
    {
        //Poner todas las marcas en 0
        for(Vertice vertice : vertices)
        {
            vertice.setMarca(0);
            vertice.setDistancia(INFINITO);
        }

        Comparator<Vertice> comparadorDeVertices = new Comparator<Vertice>()
        {
            @Override
            public int compare(Vertice v1, Vertice v2)
            {
                if(v1.getDistancia() > v2.getDistancia())
                {
                    return 1;
                }
                else if(v1.getDistancia() < v2.getDistancia())
                {
                    return -1;
                }
                else
                {
                    return 0;
                }
            }
        };

        ArrayList<Vertice> camino = new ArrayList<>();
        PriorityQueue<Vertice> q = new PriorityQueue<>(comparadorDeVertices);

        //distancia[v.getPosicion()] = 0;
        v.setDistancia(0);
        q.add(v);

        Vertice actual, adyacente;
        double costo;

        while(!q.isEmpty())
        {
            actual = q.poll();
            if(actual.getMarca() == 1)
            {
                continue;
            }
            actual.setMarca(1);

            ArrayList<Vertice> adyacentes = vecinos(actual);
            for(int i = 0; i < adyacentes.size(); i++)
            {
                adyacente = adyacentes.get(i);
                costo = costoArista(actual, adyacente);
                if(adyacente.getMarca() == 0 && actual.getDistancia() + costo < adyacente.getDistancia())
                {
                    //distancia[adyacente.posicion] = distancia[actual.posicion] + costo;  //relajamos el vertice actualizando la distancia
                    adyacente.setDistancia(actual.getDistancia() + costo);
                    //previo[adyacente] = actual; //a su vez actualizamos el vertice previo
                    adyacente.setProcedencia(actual);
                    camino.add(adyacente);
                    //q.add(new Node(adyacente, distancia[adyacente])); //agregamos adyacente a la cola de prioridad
                    q.add(adyacente);
                }
            }
        }
        return camino;
    }

    public ArrayList<Vertice> BellmanFord(Vertice ve)
    {
        ArrayList<Arista> aristas = new ArrayList<>();

        ArrayList<Vertice> camino = new ArrayList<>();
        for(Vertice vertice : vertices)
        {
            vertice.setDistancia(INFINITO);
        }
        ve.setDistancia(0);
        camino.add(ve);

        for(int i = 0; i < vertices.size(); i++)
        {
            for(int j = 0; j < vertices.size(); j++)
            {
                aristas.add(new Arista(vertices.get(i), vertices.get(j), costoArista(vertices.get(i), vertices.get(j))));
            }
        }

        for(int i = 1; i < vertices.size(); ++i)
        {
            for(int j = 0; j < aristas.size(); ++j)
            {
                Vertice u = aristas.get(j).getDesde();
                Vertice v = aristas.get(j).getHasta();
                double costo = aristas.get(j).getCosto();
                if(u.getDistancia() != INFINITO && u.getDistancia() + costo < v.getDistancia())
                {
                    v.setDistancia(u.getDistancia() + costo);
                    if(!camino.contains(v))
                    {
                        camino.remove(v);
                        camino.add(v);
                    }
                }
            }
        }

        for(int j = 0; j < aristas.size(); ++j)
        {
            Vertice u = aristas.get(j).getDesde();
            Vertice v = aristas.get(j).getHasta();
            double costo = aristas.get(j).getCosto();
            if(u.getDistancia() != INFINITO && u.getDistancia() + costo < v.getDistancia())
            {
                System.out.println("Graph contains negative weight cycle");
                return null;
            }
        }
        return camino;
    }

    public double[][] FloydWarshall()
    {

        //Matriz de Camino
        double[][] dist = new double[vertices.size()][vertices.size()];
        int i, j, k;

        // Obtener la matriz de adyacencia en P
        for(i = 0; i < vertices.size(); i++)
        {
            for(j = 0; j < vertices.size(); j++)
            {
                dist[i][j] = (costoArista(vertices.get(i), vertices.get(j)));
            }
        }

        // Iterar
        for(k = 0; k < vertices.size(); k++)
        {
            for(i = 0; i < vertices.size(); i++)
            {
                for(j = 0; j < vertices.size(); j++)
                {
                    if(dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
        return dist;
    }

    //===============================//
    //=========== Métodos ===========//
    //===============================//
    public void crearGrafoPredefinido()
    {
        //Vertices
        agregarVertice(new Vertice<>(1, vertices.size(), 0, "Circulo"));
        agregarVertice(new Vertice<>(2, vertices.size(), 0, "Circulo"));
        agregarVertice(new Vertice<>(3, vertices.size(), 0, "Circulo"));
        agregarVertice(new Vertice<>(4, vertices.size(), 0, "Circulo"));

        //Vertice 1
        agregarArista(vertices.get(0), vertices.get(1), 2.0, false);

        //Vertice 2        
        agregarArista(vertices.get(1), vertices.get(2), 5.0, false);
        agregarArista(vertices.get(1), vertices.get(3), 4.0, false);

        //Vertice 3      
        agregarArista(vertices.get(2), vertices.get(1), 5.0, false);
        agregarArista(vertices.get(2), vertices.get(3), 1.0, false);

        //Vertice 4      
        agregarArista(vertices.get(3), vertices.get(2), 1.0, false);
        agregarArista(vertices.get(3), vertices.get(1), 4.0, false);
    }

    public void explorar(Vertice v, int marca)
    {
        //v.marca = marca;
        v.setMarca(marca);
        List<Vertice> temp = vecinos(v);
        temp.stream()
                .filter(vecino -> vecino.getMarca() == 0)
                .forEach(vecino -> explorar(vecino, marca));
    } // T(n) = 1 + T(n-1) 

    public void dfs()
    {
        int i = 1;
        for(Vertice v : vertices)
        {
            if(v.getMarca() == 0)
            {
                explorar(v, i);
                i++;
            }
        }
    }

    public void dfs(Vertice v)
    {
        int i = 1;
        for(Vertice vActual : vecinos(v))
        {
            if(v.getMarca() == 0)
            {
                explorar(vActual, i);
                i++;
            }
        }
    }

    public void explorarNoRecursivo(Vertice v, int marca)
    {
        v.setMarca(marca);

        ArrayList<Vertice> vecinos = new ArrayList<>(vecinos(v));
        Stack<Vertice> pilaVecinos = new Stack<>();

        pilaVecinos.addAll(vecinos);

        Vertice actual;
        while(!pilaVecinos.empty())
        {
            actual = pilaVecinos.peek();
            pilaVecinos.pop();
            actual.setMarca(marca);

            vecinos.clear();
            vecinos.addAll(vecinos(actual));

            for(Vertice ve : vecinos)
            {
                if(ve.getMarca() != marca)
                {
                    pilaVecinos.push(ve);
                }
            }
        }
    }

    public HashMap<Vertice, Double> distanciasAVerticesSinCosto(Vertice v)
    {
        HashMap<Vertice, Double> distancias = new HashMap<>();
        distancias.put(v, 0.0);

        Queue<Vertice> cola = new LinkedList<>();
        cola.add(v);

        while(!cola.isEmpty())
        {
            Vertice explorado = cola.poll(); // poll es que retorna y elimina
            for(Vertice vecino : vecinos(explorado))
            {
                if(!distancias.containsKey(vecino))
                {
                    cola.add(vecino);
                    distancias.put(vecino, distancias.get(explorado) + 1);
                }
            }
        }
        return distancias;
    }

    public HashMap<Vertice, Double> distanciasAVerticesConCosto(Vertice v)
    {
        HashMap<Vertice, Double> distancias = new HashMap<>();
        distancias.put(v, 0.0);

        Queue<Vertice> cola = new LinkedList<>();
        cola.add(v);

        while(!cola.isEmpty())
        {
            Vertice explorado = cola.poll(); // poll es que retorna y elimina
            for(Vertice vecino : vecinos(explorado))
            {
                if(!distancias.containsKey(vecino))
                {
                    cola.add(vecino);
                    distancias.put(vecino, distancias.get(explorado) + costoArista(explorado, vecino));
                }
            }
        }
        return distancias;
    }

    public HashMap<Vertice, Double> BFS(Vertice v)
    {
        //queue<Vertice> cola;
        Queue<Vertice> cola = new LinkedList<>();
        //map<Vertice, int> mapa;
        HashMap<Vertice, Double> mapa = new HashMap<>();

        //cola.push(v);
        cola.add(v);
        //mapa[v] = 0;
        mapa.put(v, 0.0);
        while(!cola.isEmpty())
        {
            //int aux = cola.front();
            Vertice aux = cola.poll();// poll es que retorna y elimina

            //for(ver : aux.vecinos)
            for(Vertice ver : vecinos(aux))
            {
                //if (mapa.find(ver) == mapa.end())
                if(mapa.get(ver) == ultimoValorMap(mapa))
                {
                    //mapa[ver] = mapa[aux] + 1;
                    mapa.put(ver, mapa.get(aux) + 1);
                    //cola.push(ver);
                    cola.add(ver);
                }
            }
        }
        return mapa;
    }

    //usado para BFS
    public double ultimoValorMap(HashMap<Vertice, Double> map)
    {
        double res = 0.0;

        for(Map.Entry<Vertice, Double> entry : map.entrySet())
        {
            res = entry.getValue();
        }
        return res;
    }

}
