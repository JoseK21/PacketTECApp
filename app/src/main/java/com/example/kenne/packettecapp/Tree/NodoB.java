package com.example.kenne.packettecapp.Tree;

/**
 * Created by José Núñez on 8/11/2017.
 */
public class NodoB <T extends Comparable<T>> {
    static int orden;  //orden del arbol
    int cantidad; //cantidad de llaves en un nodo
    //LinkList llaves = new LinkList();
    NodoB hijos[]; //array de hijos
    boolean hoja; //boolean que dice si es hoja o no
    NodoB padre;  //nodo padre


    public NodoB(){

    }

    public NodoB(int orden, NodoB padre)
    {
        this.orden = orden;

        this.padre = padre
        ;

        //llaves = new ListaDoble[2*orden - 1];

        hijos = new NodoB[2*orden];

        hoja = true;

        cantidad = 0;
    }
/*
    public ListaDoble getllave(int index)
    {
        return llaves[index];
    }
    public void setllave(int index,ListaDoble llave)
    {
        llaves[index]= llave;
    }
    */
    public NodoB gethijo(int index)
    {
        return hijos[index];
    }

    public void sethijo(int index,NodoB nodo)
    {
        hijos[index]= nodo;
    }
    public void setPadre(NodoB padre) {
        this.padre = padre;
    }

    public boolean isHoja() {
        return hoja;
    }

    public int getCantidad() {
        return cantidad;
    }

    public static int getOrden() {
        return orden;
    }

    public NodoB getPadre() {
        return padre;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setHoja(boolean hoja) {
        this.hoja = hoja;
    }

    public static void setOrden(int orden) {
        NodoB.orden = orden;
    }
/*
    int compareTo(ListaDoble llave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */
}