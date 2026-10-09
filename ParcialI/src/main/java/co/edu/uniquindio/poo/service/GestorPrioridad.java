
package co.edu.uniquindio.poo.service;

import co.edu.uniquindio.poo.comparadores.ComparadorDespacho;
import co.edu.uniquindio.poo.model.Paquete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class GestorPrioridad {

    private final PriorityQueue<Paquete> colaPrioridad;

    public GestorPrioridad() {
        colaPrioridad = new PriorityQueue<>(new ComparadorDespacho());
    }

    // Agrega un paquete a la cola de prioridad.
    public void agregarPaquete(Paquete paquete) {
        if (paquete == null) {
            throw new IllegalArgumentException(
                    "El paquete no puede ser null."
            );
        }

        colaPrioridad.offer(paquete);
    }

    // Consulta el siguiente paquete sin retirarlo.
    public Paquete verSiguientePaquete() {
        return colaPrioridad.peek();
    }

    // Retira y devuelve el siguiente paquete.
    public Paquete despacharSiguientePaquete() {
        return colaPrioridad.poll();
    }

    public boolean estaVacia() {
        return colaPrioridad.isEmpty();
    }

    public int cantidadPaquetes() {
        return colaPrioridad.size();
    }

    // Devuelve los paquetes ordenados sin modificar la cola original.
    public List<Paquete> obtenerPaquetesEnOrdenDespacho() {
        PriorityQueue<Paquete> copia =
                new PriorityQueue<>(colaPrioridad);

        List<Paquete> resultado = new ArrayList<>();

        while (!copia.isEmpty()) {
            resultado.add(copia.poll());
        }

        return resultado;
    }

    // Suma recursivamente el peso de los paquetes de un destino.
    public double calcularPesoPorDestino(
            List<Paquete> paquetes,
            String destino) {

        if (paquetes == null || destino == null) {
            return 0.0;
        }

        return calcularPesoPorDestinoRecursivo(
                paquetes, destino.trim(), 0
        );
    }

    private double calcularPesoPorDestinoRecursivo(
            List<Paquete> paquetes,
            String destino,
            int indice) {

        if (indice >= paquetes.size()) {
            return 0.0;
        }

        Paquete paquete = paquetes.get(indice);

        double pesoActual = 0.0;

        if (paquete != null
                && paquete.getDestino() != null
                && paquete.getDestino().trim()
                .equalsIgnoreCase(destino)) {

            pesoActual = paquete.getPeso();
        }

        return pesoActual
                + calcularPesoPorDestinoRecursivo(
                paquetes, destino, indice + 1
        );
    }

    // Cuenta recursivamente los paquetes con prioridad
    // mayor o igual al umbral indicado.
    public int contarPaquetesPorPrioridad(
            List<Paquete> paquetes,
            int prioridadMinima) {

        if (paquetes == null) {
            return 0;
        }

        return contarPaquetesRecursivo(
                paquetes, prioridadMinima, 0
        );
    }

    private int contarPaquetesRecursivo(
            List<Paquete> paquetes,
            int prioridadMinima,
            int indice) {

        if (indice >= paquetes.size()) {
            return 0;
        }

        Paquete paquete = paquetes.get(indice);

        int cuentaActual = 0;

        if (paquete != null
                && paquete.getPrioridad() >= prioridadMinima) {
            cuentaActual = 1;
        }

        return cuentaActual
                + contarPaquetesRecursivo(
                paquetes, prioridadMinima, indice + 1
        );
    }

    // Busca un paquete por código mediante búsqueda binaria.
    // Trabaja sobre una copia para no modificar la lista recibida.
    public Paquete buscarPaquetePorCodigo(
            List<Paquete> paquetes,
            String codigo) {

        if (paquetes == null || codigo == null) {
            return null;
        }

        List<Paquete> copia = new ArrayList<>(paquetes);
        Collections.sort(copia);

        int izquierda = 0;
        int derecha = copia.size() - 1;

        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;
            Paquete paqueteMedio = copia.get(medio);

            int comparacion =
                    paqueteMedio.getCodigo().compareTo(codigo);

            if (comparacion == 0) {
                return paqueteMedio;
            } else if (comparacion < 0) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }

        return null;
    }

    // Encuentra recursivamente el paquete de mayor peso
    // mediante divide y vencerás.
    public Paquete buscarPaqueteMasPesado(
            List<Paquete> paquetes) {

        if (paquetes == null || paquetes.isEmpty()) {
            return null;
        }

        List<Paquete> validos = new ArrayList<>();

        for (Paquete paquete : paquetes) {
            if (paquete != null) {
                validos.add(paquete);
            }
        }

        if (validos.isEmpty()) {
            return null;
        }

        return buscarMasPesadoRecursivo(
                validos, 0, validos.size() - 1
        );
    }

    private Paquete buscarMasPesadoRecursivo(
            List<Paquete> paquetes,
            int izquierda,
            int derecha) {

        // Caso base: solo queda un paquete.
        if (izquierda == derecha) {
            return paquetes.get(izquierda);
        }

        // Divide el intervalo en dos partes.
        int medio = izquierda + (derecha - izquierda) / 2;

        // Resuelve cada mitad recursivamente.
        Paquete paqueteIzquierdo =
                buscarMasPesadoRecursivo(
                        paquetes, izquierda, medio
                );

        Paquete paqueteDerecho =
                buscarMasPesadoRecursivo(
                        paquetes, medio + 1, derecha
                );

        // Combina las soluciones: conserva el de mayor peso.
        if (paqueteIzquierdo.getPeso()
                >= paqueteDerecho.getPeso()) {
            return paqueteIzquierdo;
        }

        return paqueteDerecho;
    }
}