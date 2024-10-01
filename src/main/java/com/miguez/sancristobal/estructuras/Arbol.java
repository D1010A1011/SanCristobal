package com.miguez.sancristobal.estructuras;
import java.util.ArrayList;
import java.util.List;

class Nodo<T> {
    T valor;
    List<Nodo<T>> hijos;

    public Nodo(T valor) {
        this.valor = valor;
        this.hijos = new ArrayList<>();
    }


    public void agregarHijo(Nodo<T> hijo) {
        this.hijos.add(hijo);
    }
}


class Arbol<T> {
    Nodo<T> raiz;

    public Arbol(T valorRaiz) {
        this.raiz = new Nodo<>(valorRaiz);
    }


    public void recorrerPreorden(Nodo<T> nodo) {
        if (nodo == null) return;

        System.out.print(nodo.valor + " ");
        for (Nodo<T> hijo : nodo.hijos) {
            recorrerPreorden(hijo);
        }
    }

    public void agregarHijo(Nodo<T> nodoPadre, T valorHijo) {
        Nodo<T> hijo = new Nodo<>(valorHijo);
        nodoPadre.agregarHijo(hijo);
    }
}