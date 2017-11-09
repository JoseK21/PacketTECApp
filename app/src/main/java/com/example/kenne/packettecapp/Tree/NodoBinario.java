package com.example.kenne.packettecapp.Tree;

/**
 * Created by José Núñez on 8/11/2017.
 */

public class NodoBinario<T extends Comparable<T>> {
    private T dato;
    private NodoBinario hi;
    private NodoBinario hd;
    NodoBinario(T dato){
        this.dato = dato;
    }
    public Object getDato(){
        return dato;
    }
    public NodoBinario getHD() {
        return hd;
    }

    public NodoBinario getHI() {
        return hi;
    }

    public void setHD(NodoBinario hd) {
        this.hd = hd;
    }

    public void setHI(NodoBinario hi) {
        this.hi = hi;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }
}
