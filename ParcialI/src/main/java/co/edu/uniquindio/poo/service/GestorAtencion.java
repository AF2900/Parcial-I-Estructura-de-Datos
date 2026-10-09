package co.edu.uniquindio.poo.service;

import co.edu.uniquindio.poo.estructuras.ListaSimple;
import co.edu.uniquindio.poo.model.Paquete;
import java.util.ArrayDeque;
import java.util.Queue;

public class GestorAtencion {
    private Queue<Paquete> colaLlegada;
    private ListaSimple<Paquete> historialEntregas;

    public GestorAtencion() {
        this.colaLlegada = new ArrayDeque<>();
        this.historialEntregas = new ListaSimple<>();
    }

    public void encolarLlegada(Paquete paquete) {
        if (paquete != null) {
            colaLlegada.offer(paquete);
        }
    }

    public Paquete atenderSiguiente() {
        return colaLlegada.poll();
    }

    public void registrarEntrega(Paquete paquete) {
        if (paquete != null) {
            historialEntregas.agregar(paquete);
        }
    }

    public ListaSimple<Paquete> getHistorialEntregas() {
        return historialEntregas;
    }

    public int getPaquetesPendientes() {
        return colaLlegada.size();
    }
}
