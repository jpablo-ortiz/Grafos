package Estructuras;

import java.util.ArrayList;

public class Arbol<T>
{
    //===============================//
    //========== Atributos ==========//
    //===============================//
    
    private Nodo<T> raiz;
    private ArrayList<Integer> valoresDefecto;
    
    //===============================//
    //======== Constructores ========//
    //===============================//

    public Arbol(Nodo raiz)
    {
        this.raiz = raiz;
    }

    public Arbol(int hermanos, int profundidad, ArrayList<Integer> valoresDefecto)
    {
        this.raiz = new Nodo();
        this.valoresDefecto = valoresDefecto;
        creacionHijos(raiz, hermanos, profundidad);
    }
    
    //===============================//
    //=========== Métodos ===========//
    //===============================//

    public void creacionHijos(Nodo nodo, int hermanos, int profundidad)
    {
        if (profundidad <= 1)
        {
            for (int i = 0; i < hermanos; i++)
            {
                nodo.agregarHijo( new Nodo(valoresDefecto.get(0)) );
                valoresDefecto.remove(0);
            }
        }
        else if (profundidad > 1)
        {
            for (int i = 0; i < hermanos; i++)
            {
                nodo.agregarHijo(new Nodo());
            }

            for (Object hijos : nodo.getHijos())
            {
                creacionHijos((Nodo) hijos, hermanos, profundidad - 1);
            }
        }
    }
    
    public double minMax(Nodo nodo, int profundidad, double alfa, double beta, boolean jugadorParaGanar) //MinMax con poda Alfa-Beta
    {
        if(profundidad == 0 || nodo.isHoja())
        {
            return (double) nodo.getValor(); // devolver la puntuacion del nodo
        }
        else if(jugadorParaGanar)
        {
            double maxEvaluacion = Double.NEGATIVE_INFINITY;
            for(Object hijoActual: nodo.getHijos())
            {
                double evaluacion = minMax((Nodo<Double>)hijoActual, profundidad -1, alfa, beta, false);
                maxEvaluacion = Math.max(maxEvaluacion, evaluacion);
                alfa = Math.max(alfa, evaluacion);
                if(beta<=alfa)
                {
                    break;
                }
            }
            return maxEvaluacion;
        }
        else
        {
            double minEvaluacion = Double.POSITIVE_INFINITY;
            for(Object hijoActual : nodo.getHijos())
            {
                double evaluacion = minMax((Nodo<Double>)hijoActual, profundidad -1, alfa, beta, true);
                minEvaluacion = Math.min(minEvaluacion, evaluacion);
                beta = Math.min(beta, evaluacion);
                if(beta<=alfa)
                {
                    break;
                }
            }
            return minEvaluacion;
        }
    }
    
    //===============================//
    //====== Getters y Setters ======//
    //===============================//

    public Nodo<T> getRaiz()
    {
        return raiz;
    }

    public void setRaiz(Nodo<T> raiz)
    {
        this.raiz = raiz;
    }

    public ArrayList<Integer> getValoresDefecto()
    {
        return valoresDefecto;
    }

    public void setValoresDefecto(ArrayList<Integer> valoresDefecto)
    {
        this.valoresDefecto = valoresDefecto;
    }

}
