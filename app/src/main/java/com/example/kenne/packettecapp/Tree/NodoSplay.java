package com.example.kenne.packettecapp.Tree;

/**
 * Created by José Núñez on 8/11/2017.
 */
public class NodoSplay <T extends Comparable<T>>{
    private NodoSplay izq, der, padre;
    private T elemento;



    NodoSplay(){
        this.elemento = null;
        this.der = null;
        this.izq = null;
        this.padre = null;
    }
    NodoSplay(T elemento){
        this.elemento = elemento;
        this.der = null;
        this.izq = null;
        this.padre = null;
    }
    NodoSplay(T elemento,NodoSplay izq,NodoSplay der,NodoSplay padre){
        this.elemento = elemento;
        this.der = der;
        this.izq = izq;
        this.padre = padre;
    }

    public void setDer(NodoSplay der) {
        this.der = der;
    }

    public T getElemento() {
        return elemento;
    }

    public NodoSplay getDer() {
        return der;
    }

    public NodoSplay getIzq() {
        return izq;
    }

    public NodoSplay getPadre() {
        return padre;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public void setIzq(NodoSplay izq) {
        this.izq = izq;
    }

    public void setPadre(NodoSplay padre) {
        this.padre = padre;
    }
}

