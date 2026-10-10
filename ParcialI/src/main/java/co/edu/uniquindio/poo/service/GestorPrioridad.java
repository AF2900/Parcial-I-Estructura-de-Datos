package co.edu.uniquindio.poo.service;

import co.edu.uniquindio.poo.comparadores.ComparadorDespacho;
import co.edu.uniquindio.poo.comparadores.ComparadorPeso;
import co.edu.uniquindio.poo.comparadores.ComparadorPrioridad;
import co.edu.uniquindio.poo.comparadores.ComparadorTiempo;
import co.edu.uniquindio.poo.model.Paquete;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class GestorPrioridad {

    private final PriorityQueue<Paquete> colaPrioridad;


    public GestorPrioridad() {

        colaPrioridad =
                new PriorityQueue<>(
                        new ComparadorDespacho()
                );
    }


    // ---------------------------------------------------------
    // COLA DE PRIORIDAD
    // ---------------------------------------------------------

    public void agregarPaquete(Paquete paquete) {

        if (paquete == null) {
            throw new IllegalArgumentException(
                    "El paquete no puede ser null."
            );
        }

        colaPrioridad.offer(paquete);
    }


    public Paquete verSiguientePaquete() {

        return colaPrioridad.peek();
    }


    public Paquete despacharSiguientePaquete() {

        return colaPrioridad.poll();
    }


    /*
     * Permite eliminar un paquete concreto de la cola.
     *
     * Será necesario durante la integración cuando un paquete
     * sea procesado por otra modalidad y deba desaparecer
     * también de esta estructura.
     *
     * PriorityQueue.remove(Object) tiene costo O(n).
     */
    public boolean eliminarPaquete(
            Paquete paquete) {

        if (paquete == null) {
            return false;
        }

        return colaPrioridad.remove(paquete);
    }


    public boolean contienePaquete(
            Paquete paquete) {

        if (paquete == null) {
            return false;
        }

        return colaPrioridad.contains(paquete);
    }


    public boolean estaVacia() {

        return colaPrioridad.isEmpty();
    }


    public int cantidadPaquetes() {

        return colaPrioridad.size();
    }


    /*
     * Devuelve los paquetes en el orden real de despacho,
     * pero sin modificar la PriorityQueue original.
     */
    public List<Paquete> obtenerPaquetesEnOrdenDespacho() {

        PriorityQueue<Paquete> copia =
                new PriorityQueue<>(
                        colaPrioridad
                );

        List<Paquete> resultado =
                new ArrayList<>();

        while (!copia.isEmpty()) {

            resultado.add(
                    copia.poll()
            );
        }

        return resultado;
    }


    // ---------------------------------------------------------
    // ORDENAMIENTOS ALTERNATIVOS
    // ---------------------------------------------------------

    /*
     * Devuelve una copia ordenada por prioridad
     * de mayor a menor.
     */
    public List<Paquete> ordenarPorPrioridad(
            List<Paquete> paquetes) {

        if (paquetes == null) {
            return new ArrayList<>();
        }

        List<Paquete> copia =
                new ArrayList<>(paquetes);

        copia.sort(
                new ComparadorPrioridad()
        );

        return copia;
    }


    /*
     * Devuelve una copia ordenada por peso
     * de mayor a menor.
     */
    public List<Paquete> ordenarPorPeso(
            List<Paquete> paquetes) {

        if (paquetes == null) {
            return new ArrayList<>();
        }

        List<Paquete> copia =
                new ArrayList<>(paquetes);

        copia.sort(
                new ComparadorPeso()
        );

        return copia;
    }


    /*
     * Devuelve una copia ordenada por tiempo estimado
     * de menor a mayor.
     */
    public List<Paquete> ordenarPorTiempo(
            List<Paquete> paquetes) {

        if (paquetes == null) {
            return new ArrayList<>();
        }

        List<Paquete> copia =
                new ArrayList<>(paquetes);

        copia.sort(
                new ComparadorTiempo()
        );

        return copia;
    }


    /*
     * Utiliza el orden natural de Paquete.
     *
     * Paquete implementa Comparable<Paquete>
     * y su orden natural es por código ascendente.
     */
    public List<Paquete> ordenarPorCodigo(
            List<Paquete> paquetes) {

        if (paquetes == null) {
            return new ArrayList<>();
        }

        List<Paquete> copia =
                new ArrayList<>(paquetes);

        Collections.sort(copia);

        return copia;
    }


    // ---------------------------------------------------------
    // RECURSIVIDAD:
    // PESO TOTAL POR MUNICIPIO
    // ---------------------------------------------------------

    public double calcularPesoPorDestino(
            List<Paquete> paquetes,
            String destino) {

        if (paquetes == null ||
                destino == null ||
                destino.trim().isEmpty()) {

            return 0.0;
        }

        return calcularPesoPorDestinoRecursivo(
                paquetes,
                destino.trim(),
                0
        );
    }


    private double calcularPesoPorDestinoRecursivo(
            List<Paquete> paquetes,
            String destino,
            int indice) {

        /*
         * Caso base:
         * se llegó al final de la lista.
         */
        if (indice >= paquetes.size()) {
            return 0.0;
        }

        Paquete paquete =
                paquetes.get(indice);

        double pesoActual = 0.0;

        if (paquete.getDestino()
                .equalsIgnoreCase(destino)) {

            pesoActual =
                    paquete.getPeso();
        }

        /*
         * Caso recursivo:
         * procesa el elemento actual y continúa
         * con la siguiente posición.
         */
        return pesoActual
                + calcularPesoPorDestinoRecursivo(
                paquetes,
                destino,
                indice + 1
        );
    }


    // ---------------------------------------------------------
    // RECURSIVIDAD:
    // CONTAR PRIORIDAD MÍNIMA
    // ---------------------------------------------------------

    public int contarPaquetesPorPrioridad(
            List<Paquete> paquetes,
            int prioridadMinima) {

        if (paquetes == null) {
            return 0;
        }

        return contarPaquetesRecursivo(
                paquetes,
                prioridadMinima,
                0
        );
    }


    private int contarPaquetesRecursivo(
            List<Paquete> paquetes,
            int prioridadMinima,
            int indice) {

        /*
         * Caso base:
         * se llegó al final de la lista.
         */
        if (indice >= paquetes.size()) {
            return 0;
        }

        Paquete paquete =
                paquetes.get(indice);

        int cuentaActual = 0;

        if (paquete.getPrioridad()
                >= prioridadMinima) {

            cuentaActual = 1;
        }

        /*
         * Caso recursivo.
         */
        return cuentaActual
                + contarPaquetesRecursivo(
                paquetes,
                prioridadMinima,
                indice + 1
        );
    }


    // ---------------------------------------------------------
    // BÚSQUEDA BINARIA RECURSIVA
    // ---------------------------------------------------------

    /*
     * PRECONDICIÓN:
     *
     * La lista recibida debe encontrarse ordenada
     * ascendentemente por código.
     *
     * Para obtenerla puede utilizarse:
     *
     * ordenarPorCodigo(lista)
     */
    public Paquete buscarPaquetePorCodigo(
            List<Paquete> paquetesOrdenados,
            String codigo) {

        if (paquetesOrdenados == null ||
                codigo == null ||
                codigo.trim().isEmpty()) {

            return null;
        }

        return buscarPaquetePorCodigoRecursivo(
                paquetesOrdenados,
                codigo,
                0,
                paquetesOrdenados.size() - 1
        );
    }


    private Paquete buscarPaquetePorCodigoRecursivo(
            List<Paquete> paquetesOrdenados,
            String codigo,
            int izquierda,
            int derecha) {

        /*
         * Caso base:
         * el intervalo quedó vacío.
         */
        if (izquierda > derecha) {
            return null;
        }

        int medio =
                izquierda
                        + (derecha - izquierda) / 2;

        Paquete paqueteMedio =
                paquetesOrdenados.get(medio);

        int comparacion =
                paqueteMedio
                        .getCodigo()
                        .compareTo(codigo);

        /*
         * Se encontró el paquete.
         */
        if (comparacion == 0) {
            return paqueteMedio;
        }

        /*
         * El código buscado está a la derecha.
         */
        if (comparacion < 0) {

            return buscarPaquetePorCodigoRecursivo(
                    paquetesOrdenados,
                    codigo,
                    medio + 1,
                    derecha
            );
        }

        /*
         * El código buscado está a la izquierda.
         */
        return buscarPaquetePorCodigoRecursivo(
                paquetesOrdenados,
                codigo,
                izquierda,
                medio - 1
        );
    }


    // ---------------------------------------------------------
    // DIVIDE Y VENCERÁS:
    // PAQUETE DE MAYOR PESO
    // ---------------------------------------------------------

    public Paquete buscarPaqueteMasPesado(
            List<Paquete> paquetes) {

        if (paquetes == null ||
                paquetes.isEmpty()) {

            return null;
        }

        return buscarMasPesadoRecursivo(
                paquetes,
                0,
                paquetes.size() - 1
        );
    }


    private Paquete buscarMasPesadoRecursivo(
            List<Paquete> paquetes,
            int izquierda,
            int derecha) {

        /*
         * CASO BASE:
         *
         * El subproblema contiene solamente
         * un paquete.
         */
        if (izquierda == derecha) {

            return paquetes.get(
                    izquierda
            );
        }

        /*
         * DIVISIÓN:
         *
         * Se divide el intervalo aproximadamente
         * en dos partes.
         */
        int medio =
                izquierda
                        + (derecha - izquierda) / 2;


        /*
         * SUBPROBLEMA IZQUIERDO.
         */
        Paquete paqueteIzquierdo =
                buscarMasPesadoRecursivo(
                        paquetes,
                        izquierda,
                        medio
                );


        /*
         * SUBPROBLEMA DERECHO.
         */
        Paquete paqueteDerecho =
                buscarMasPesadoRecursivo(
                        paquetes,
                        medio + 1,
                        derecha
                );


        /*
         * COMBINACIÓN:
         *
         * Se comparan las soluciones obtenidas
         * de ambos subproblemas.
         */
        if (paqueteIzquierdo.getPeso()
                >= paqueteDerecho.getPeso()) {

            return paqueteIzquierdo;
        }

        return paqueteDerecho;
    }
}