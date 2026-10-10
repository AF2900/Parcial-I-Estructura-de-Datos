package co.edu.uniquindio.poo.estructuras;

class Nodo<T> {
    T valor;
    Nodo<T> siguiente;

    public Nodo(T valor) {
        this.valor = valor;
        this.siguiente = null;
    }
}
