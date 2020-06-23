package Estructuras;

public class Arista<T>
{
    //===============================//
    //========== Atributos ==========//
    //===============================//
    
    private Vertice desde;
    private Vertice hasta;
    private double costo;

    //===============================//
    //========= Constructor =========//
    //===============================//
    
    public Arista(Vertice desde, Vertice hasta, double costo)
    {
        this.desde = desde;
        this.hasta = hasta;
        this.costo = costo;
    }

    //===============================//
    //====== Getters y Setters ======//
    //===============================//
    
    public Vertice getDesde()
    {
        return desde;
    }

    public void setDesde(Vertice desde)
    {
        this.desde = desde;
    }

    public Vertice getHasta()
    {
        return hasta;
    }

    public void setHasta(Vertice hasta)
    {
        this.hasta = hasta;
    }

    public double getCosto()
    {
        return costo;
    }

    public void setCosto(double costo)
    {
        this.costo = costo;
    }  
}
