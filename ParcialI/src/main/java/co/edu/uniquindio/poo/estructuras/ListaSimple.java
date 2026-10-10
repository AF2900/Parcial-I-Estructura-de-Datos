package co.edu.uniquindio.poo.estructuras;

import java.util.Iterator;

public class ListaSimple<T> implements Coleccion<T>, Iterable<T> {
    private Nodo<T> primero;
    private Nodo<T> ultimo;
    private int size;

    public ListaSimple() {
        this.primero = null;
        this.ultimo = null;
        this.size = 0;
    }

    @Override 
    public void agregar(T elemento) {
        Nodo<T> nuevo = new Nodo<>(elemento);
        if (estaVacia()) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.siguiente = nuevo;
            ultimo = nuevo;
        }
        size++;
    }

    @Override
    public boolean eliminar(T elemento) {
        if (estaVacia()) {
            return false;
        }

        if (primero.valor.equals(elemento)) {
            primero = primero.siguiente;
            size--;
            if (size == 0) {
                ultimo = null;
            }
            return true;
        }

        Nodo<T> actual = primero;
        while (actual.siguiente != null) {
            if (actual.siguiente.valor.equals(elemento)) {
                if (actual.siguiente == ultimo) {
                    ultimo = actual;
                }
                actual.siguiente = actual.siguiente.siguiente;
                size--;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public T obtener(int posicion) {
        if (posicion < 0 || posicion >= size) {
            throw new IndexOutOfBoundsException("Posición invalida: " + posicion);
        }
        Nodo<T> actual = primero;
        for (int i = 0; i < posicion; i++) {
            actual = actual.siguiente;
        }
        return actual.valor;
    }

    @Override
    public int tamano() {
        return size;
    }

    @Override
    public boolean estaVacia() {
        return size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListaSimpleIterator<>(primero);
    }
 }
