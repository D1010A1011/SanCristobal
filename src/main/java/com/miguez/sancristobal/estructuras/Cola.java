package com.miguez.sancristobal.estructuras;
 public class Cola{
    private int frente, finalCola, tamaño;
    private int[] cola;

    public Cola(int capacidad) {
        cola = new int[capacidad];
        frente = 0;
        finalCola = -1;
        tamaño = 0;
    }

    public void encolar(int elemento) {
        if (tamaño == cola.length) {
            System.out.println("La cola está llena");
            return;
        }
        finalCola = (finalCola + 1) % cola.length;
        cola[finalCola] = elemento;
        tamaño++;
    }

    public int desencolar() {
        if (tamaño == 0) {
            System.out.println("La cola está vacía");
            return -1;
        }
        int elemento = cola[frente];
        frente = (frente + 1) % cola.length;
        tamaño--;
        return elemento;
    }


    public int frente() {
        if (tamaño == 0) {
            System.out.println("La cola está vacía");
            return -1;
        }
        return cola[frente];
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }


    public boolean estaLlena() {
        return tamaño == cola.length;
    }


    public int obtenerTamaño() {
        return tamaño;
    }
}
