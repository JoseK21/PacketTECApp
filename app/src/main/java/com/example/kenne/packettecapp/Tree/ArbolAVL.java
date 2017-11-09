package com.example.kenne.packettecapp.Tree;

/**
 * Created by JoséNúñez on 8/11/2017.
 */


public class ArbolAVL<T extends Comparable<T>> {
    private NodoAVL raiz;
    private static final int desbalanceAceptado = 1;

    public ArbolAVL() {
        raiz = null;
    }

    public void insertar(T valor) {
        raiz = insertar(valor, raiz);
    }


    public void remover(T valor) {
        raiz = remover(valor, raiz);
    }

    private NodoAVL remover(T valor, NodoAVL nodoActual) {
        if (nodoActual == null)
            return nodoActual;

        int compareResult = valor.compareTo((T) nodoActual.getDato());

        if (compareResult < 0)
            nodoActual.setHijoI(remover(valor, nodoActual.getHijoI()));
        else if (compareResult > 0)
            nodoActual.setHijoD(remover(valor, nodoActual.getHijoD()));
        else if (nodoActual.getHijoI() != null && nodoActual.getHijoD() != null) {
            nodoActual.setDato(findMin(nodoActual.getHijoD()).getDato());
            nodoActual.setHijoD(remover((T) nodoActual.getDato(), nodoActual.getHijoD()));
        } else
            nodoActual = (nodoActual.getHijoI() != null) ? nodoActual.getHijoI() : nodoActual.getHijoD();
        return balancear(nodoActual);
    }

    public T findMin() {
        if (esVacio())
            return null;
        return (T) findMin(raiz).getDato();
    }

    public T findMax() {
        if (esVacio())
            return null;
        return (T) findMax(raiz).getDato();
    }

    public boolean buscar(T x) {
        return buscar(x, raiz);
    }

    public void vaciarArbol() {
        raiz = null;
    }

    public boolean esVacio() {
        return raiz == null;
    }

    private NodoAVL balancear(NodoAVL nodoActual) {
        if (nodoActual == null)
            return nodoActual;

        if (alturaNodoActual(nodoActual.getHijoI()) - alturaNodoActual(nodoActual.getHijoD()) > desbalanceAceptado)
            if (alturaNodoActual(nodoActual.getHijoI().getHijoI()) >= alturaNodoActual(nodoActual.getHijoI().getHijoD()))
                nodoActual = rotarHijoIzq(nodoActual);
            else
                nodoActual = rotarDobleHijoIzq(nodoActual);
        else if (alturaNodoActual(nodoActual.getHijoD()) - alturaNodoActual(nodoActual.getHijoI()) > desbalanceAceptado)
            if (alturaNodoActual(nodoActual.getHijoD()) >= alturaNodoActual(nodoActual.getHijoD().getHijoI()))
                nodoActual = rotarHijoDer(nodoActual);
            else
                nodoActual = rotarDobleHijoDer(nodoActual);

        nodoActual.setAltura(Math.max(alturaNodoActual(nodoActual.getHijoI()), alturaNodoActual(nodoActual.getHijoD())) + 1);
        return nodoActual;
    }

    public void revisarBalance() {
        revisarBalance(raiz);
    }

    private int revisarBalance(NodoAVL nodoActual) {
        if (nodoActual == null)
            return -1;

        if (nodoActual != null) {
            int hl = revisarBalance(nodoActual.getHijoI());
            int hr = revisarBalance(nodoActual.getHijoD());
            if (Math.abs(alturaNodoActual(nodoActual.getHijoI()) - alturaNodoActual(nodoActual.getHijoD())) > 1 ||
                    alturaNodoActual(nodoActual.getHijoI()) != hl || alturaNodoActual(nodoActual.getHijoD()) != hr)
                ;
        }

        return alturaNodoActual(nodoActual);
    }


    private NodoAVL insertar(T valor, NodoAVL nodoActual) {
        if (nodoActual == null)
            return new NodoAVL<T>(valor, null, null);

        int compareResult = valor.compareTo((T) nodoActual.getDato());

        if (compareResult < 0)
            nodoActual.setHijoI(insertar(valor, nodoActual.getHijoI()));
        else if (compareResult > 0)
            nodoActual.setHijoD(insertar(valor, nodoActual.getHijoD()));
        else
            ;
        return balancear(nodoActual);
    }


    private NodoAVL findMin(NodoAVL nodoActual) {
        if (nodoActual == null)
            return nodoActual;

        while (nodoActual.getHijoI() != null)
            nodoActual = nodoActual.getHijoI();
        return nodoActual;
    }

    private NodoAVL findMax(NodoAVL nodoActual) {
        if (nodoActual == null)
            return nodoActual;

        while (nodoActual.getHijoD() != null)
            nodoActual = nodoActual.getHijoD();
        return nodoActual;
    }

    private boolean buscar(T valor, NodoAVL nodoActual) {
        while (nodoActual != null) {
            int compareResult = valor.compareTo((T) nodoActual.getDato());

            if (compareResult < 0)
                nodoActual = nodoActual.getHijoI();
            else if (compareResult > 0)
                nodoActual = nodoActual.getHijoD();
            else
                return true;
        }

        return false;
    }

    private int alturaNodoActual(NodoAVL nodoActual) {
        return nodoActual == null ? -1 : nodoActual.getAltura();
    }

    private NodoAVL rotarHijoIzq(NodoAVL rotarIzq) {
        NodoAVL rotarDer = rotarIzq.getHijoI();
        rotarIzq.setHijoI(rotarDer.getHijoD());
        rotarDer.setHijoD(rotarIzq);
        rotarIzq.setAltura(Math.max(alturaNodoActual(rotarIzq.getHijoI()), alturaNodoActual(rotarIzq.getHijoD())) + 1);
        rotarDer.setAltura(Math.max(alturaNodoActual(rotarDer.getHijoI()), rotarIzq.getAltura()) + 1);
        return rotarDer;
    }

    private NodoAVL rotarHijoDer(NodoAVL rotarDer) {
        NodoAVL rotarIzq = rotarDer.getHijoD();
        rotarDer.setHijoD(rotarIzq.getHijoI());
        rotarIzq.setHijoI(rotarDer);
        rotarDer.setAltura(Math.max(alturaNodoActual(rotarDer.getHijoI()), alturaNodoActual(rotarDer.getHijoD())) + 1);
        rotarIzq.setAltura(Math.max(alturaNodoActual(rotarIzq.getHijoD()), rotarDer.getAltura()) + 1);
        return rotarIzq;
    }

    private NodoAVL rotarDobleHijoIzq(NodoAVL rotacionDoble) {
        rotacionDoble.setHijoI(rotarHijoDer(rotacionDoble.getHijoI()));
        return rotarHijoIzq(rotacionDoble);
    }

    private NodoAVL rotarDobleHijoDer(NodoAVL rotacionDoble) {
        rotacionDoble.setHijoD(rotarHijoIzq(rotacionDoble.getHijoD()));
        return rotarHijoDer(rotacionDoble);
    }

    public void inorder() {
        inorder(raiz);
    }

    private void inorder(NodoAVL nodoActual) {
        if (nodoActual != null) {
            inorder(nodoActual.getHijoI());
            System.out.print(nodoActual.getDato() + " ");
            inorder(nodoActual.getHijoD());
        }
    }

    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL();
        arbol.insertar(20);
        arbol.insertar(30);
        arbol.insertar(10);
        arbol.inorder();
        arbol.remover(20);
        arbol.inorder();
    }
}