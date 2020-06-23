package Otros;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra
{

    private final int MAX = 10005;  //maximo numero de vértices
    private final int INF = 1 << 30;  //definimos un valor grande que represente la distancia infinita inicial, basta conque sea superior al maximo valor del peso en alguna de las aristas

    List<List<Node>> ady = new ArrayList< List<Node>>();
    int distancia[] = new int[MAX];      //distancia[ u ] distancia de vértice inicial a vértice con ID = u
    boolean visitado[] = new boolean[MAX];      //para vértices visitados
    PriorityQueue< Node> Q = new PriorityQueue<>(); //priority queue propia del c++, usamos el comparador definido para que el de menor valor este en el tope
    int V;                     //numero de vertices
    int previo[] = new int[MAX];         //para la impresion de caminos

    static class Node implements Comparable<Node>
    {
        int first, second;

        Node(int d, int p)
        {                          
            //constructor
            this.first = d; 
            this.second = p;
        }

        public int compareTo(Node other)
        {              
            //es necesario definir un comparador para el correcto funcionamiento del PriorityQueue
            if(second > other.second)
                return 1;
            if(second == other.second)
                return 0;
            return -1;
        }
    };

    //función de inicialización
    void init()
    {
        for(int i = 0; i <= V; ++i)
        {
            distancia[i] = INF;  //inicializamos todas las distancias con valor infinito
            visitado[i] = false; //inicializamos todos los vértices como no visitado
            previo[i] = -1;      //inicializamos el previo del vértice i con -1
        }
    }

    void dijkstra(int inicial)
    {
        init(); //inicializamos nuestros arreglos
        Q.add(new Node(inicial, 0)); //Insertamos el vértice inicial en la Cola de Prioridad
        distancia[inicial] = 0;      //Este paso es importante, inicializamos la distancia del inicial como 0
        int actual, adyacente, peso;
        while(!Q.isEmpty())
        {                   //Mientras cola no este vacia
            actual = Q.element().first;            //Obtengo de la cola el nodo con menor peso, en un comienzo será el inicial
            Q.remove();                           //Sacamos el elemento de la cola
            if(visitado[actual])
            {
                continue; //Si el vértice actual ya fue visitado entonces sigo sacando elementos de la cola
            }
            visitado[actual] = true;         //Marco como visitado el vértice actual

            for(int i = 0; i < ady.get(actual).size(); ++i)
            { 
                //reviso sus adyacentes del vertice actual
                adyacente = ady.get(actual).get(i).first;   //id del vertice adyacente
                peso = ady.get(actual).get(i).second;        //peso de la arista que une actual con adyacente ( actual , adyacente )
                if(!visitado[adyacente])
                {        
                    //si el vertice adyacente no fue visitado
                    relajacion(actual, adyacente, peso); //realizamos el paso de relajacion
                }
            }
        }

        System.out.printf("Distancias mas cortas iniciando en vertice %d\n", inicial);
        for(int i = 1; i <= V; ++i)
        {
            System.out.printf("Vertice %d , distancia mas corta = %d\n", i, distancia[i]);
        }
    }

    //Paso de relajacion
    private void relajacion(int actual, int adyacente, int peso)
    {
        //Si la distancia del origen al vertice actual + peso de su arista es menor a la distancia del origen al vertice adyacente
        if(distancia[actual] + peso < distancia[adyacente])
        {
            distancia[adyacente] = distancia[actual] + peso;  //relajamos el vertice actualizando la distancia
            previo[adyacente] = actual;                         //a su vez actualizamos el vertice previo
            Q.add(new Node(adyacente, distancia[adyacente])); //agregamos adyacente a la cola de prioridad
        }
    }

    //Impresion del camino mas corto desde el vertice inicial y final ingresados
    void print(int destino)
    {
        if(previo[destino] != -1)    //si aun poseo un vertice previo
        {
            print(previo[destino]);  //recursivamente sigo explorando
        }
        System.out.printf("%d ", destino);        //terminada la recursion imprimo los vertices recorridos
    }
}
