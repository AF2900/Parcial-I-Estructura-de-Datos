package co.edu.uniquindio.poo.estructuras;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaSimpleIterator<T> implements Iterator<T> {
    private Nodo<T> actual;

    public ListaSimpleIterator(Nodo<T> primero) {
        this.actual = primero;
    }

    @Override 
    public boolean hasNext() {
        return actual != null;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No hay mas elementos en la lista.");
        }
        T valor = actual.valor;
        actual = actual.siguiente;
        return valor;
    }
}
