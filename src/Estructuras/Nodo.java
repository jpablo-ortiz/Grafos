package Estructuras;

import java.util.ArrayList;

public class Nodo<T>
{
    //===============================//
    //========== Atributos ==========//
    //===============================//
    
    private T valor;
    private Nodo padre;
    private ArrayList<Nodo<T>> hijos;
    private boolean Hoja;
    
    //===============================//
    //======== Constructores ========//
    //===============================//

    public Nodo(Nodo padre, ArrayList<Nodo<T>> hijos, T puntuacion)
    {
        this.padre = padre;
        this.hijos = hijos;
        this.valor = puntuacion;
    }

    public Nodo(T puntuacion)
    {
        this.padre = null;
        this.hijos = new ArrayList<>();
        this.valor = puntuacion;
    }

    public Nodo()
    {
        this.padre = null;
        this.hijos = new ArrayList<>();
    }

    //===============================//
    //=========== Métodos ===========//
    //===============================//
    
    public void agregarHijo(Nodo hijo)
    {
        hijos.add(hijo);
    }

    public void imprimir()
    {
        imprimir("", true);
    }

    private void imprimir(String prefix, boolean isTail)
    {
        System.out.println(prefix + (isTail ? "└── " : "├── ") + valor);
        for (int i = 0; i < hijos.size() - 1; i++)
        {
            hijos.get(i).imprimir(prefix + (isTail ? "    " : "│   "), false);
        }
        if (hijos.size() > 0)
        {
            hijos.get(hijos.size() - 1).imprimir(prefix + (isTail ? "    " : "│   "), true);
        }
    }

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

    public Nodo getPadre()
    {
        return padre;
    }

    public void setPadre(Nodo padre)
    {
        this.padre = padre;
    }

    public ArrayList<Nodo<T>> getHijos()
    {
        return hijos;
    }

    public void setHijos(ArrayList<Nodo<T>> hijos)
    {
        this.hijos = hijos;
    }

    public boolean isHoja()
    {
        return Hoja;
    }

    public void setHoja(boolean Hoja)
    {
        this.Hoja = Hoja;
    }
 
}
