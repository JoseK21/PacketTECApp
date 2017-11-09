package com.example.kenne.packettecapp.Tree;

/**
 * Created by José Núñez on 8/11/2017.
 */

public class NodoAVL<T extends Comparable<T>> {
    private T dato;
    private int altura;
    private NodoAVL hijoI,hijoD;

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public NodoAVL getHijoI() {
        return hijoI;
    }

    public void setHijoI(NodoAVL hijoI) {
        this.hijoI = hijoI;
    }

    public NodoAVL getHijoD() {
        return hijoD;
    }

    public void setHijoD(NodoAVL hijoD) {
        this.hijoD = hijoD;
    }





    public NodoAVL()
    {
        hijoI = null;
        hijoD = null;
        dato = null;
        altura = 0;
    }
    public NodoAVL(T n,NodoAVL hijoIzq,NodoAVL hijoDer)
    {
        hijoI = hijoIzq;
        hijoD = hijoDer;
        dato = n;
        altura = 0;
    }
    public NodoAVL(T n){
        dato = n;
        hijoD = null;
        hijoI = null;
    }




}
