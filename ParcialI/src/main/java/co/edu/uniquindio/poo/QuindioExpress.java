package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.estructuras.ListaSimple;
import co.edu.uniquindio.poo.model.Paquete;
import co.edu.uniquindio.poo.model.Repartidor;
import co.edu.uniquindio.poo.service.GestorAtencion;
import co.edu.uniquindio.poo.service.GestorPrioridad;
import co.edu.uniquindio.poo.service.GestorRegistro;

import java.util.List;
import java.util.Set;

public class QuindioExpress {

    private GestorRegistro gestorRegistro;
    private GestorAtencion gestorAtencion;
    private GestorPrioridad gestorPrioridad;


    public QuindioExpress() {

        gestorRegistro = new GestorRegistro();
        gestorAtencion = new GestorAtencion();
        gestorPrioridad = new GestorPrioridad();
    }


    // =========================================================
    // REGISTRO INTEGRADO
    // =========================================================

    public boolean registrarPaquete(Paquete paquete) {

        boolean registrado =
                gestorRegistro.registrarPaquete(paquete);

        /*
         * El paquete solamente entra a las estructuras
         * de atención si el registro fue válido.
         *
         * Las tres estructuras conservan la misma referencia
         * del objeto Paquete.
         */
        if (registrado) {

            gestorAtencion.encolarLlegada(paquete);

            gestorPrioridad.agregarPaquete(paquete);
        }

        return registrado;
    }


    public boolean registrarRepartidor(
            Repartidor repartidor) {

        return gestorRegistro.registrarRepartidor(
                repartidor
        );
    }


    // =========================================================
    // ATENCIÓN FIFO
    // =========================================================

    /*
     * Solamente consulta el siguiente paquete FIFO.
     *
     * peek() no lo elimina de la cola.
     */
    public Paquete verSiguienteFIFO() {

        return gestorAtencion.verSiguiente();
    }


    /*
     * Atiende el siguiente paquete respetando FIFO.
     *
     * IMPORTANTE:
     * primero intenta asignar un repartidor.
     *
     * Si NO hay repartidor disponible, el paquete NO se
     * elimina de ninguna estructura.
     *
     * Si sí existe repartidor, asignarRepartidor()
     * sincroniza las dos colas y retira el paquete
     * tanto de FIFO como de PriorityQueue.
     *
     * El paquete todavía sigue siendo pendiente porque
     * aún no se ha registrado como entregado.
     */
    public Paquete atenderSiguienteFIFO() {

        Paquete paquete =
                gestorAtencion.verSiguiente();

        if (paquete == null) {
            return null;
        }

        Repartidor repartidor =
                asignarRepartidor(paquete);

        if (repartidor == null) {

            /*
             * No hay repartidor.
             *
             * El paquete permanece en las estructuras
             * para poder intentarlo posteriormente.
             */
            return null;
        }

        return paquete;
    }


    // =========================================================
    // ATENCIÓN POR PRIORIDAD
    // =========================================================

    /*
     * Consulta el siguiente paquete de la PriorityQueue
     * sin retirarlo.
     */
    public Paquete verSiguientePrioridad() {

        return gestorPrioridad.verSiguientePaquete();
    }


    /*
     * Selecciona el paquete de mayor prioridad.
     *
     * El criterio ya está definido en ComparadorDespacho:
     *
     * 1. mayor prioridad;
     * 2. menor tiempo estimado;
     * 3. menor código.
     *
     * Primero se intenta asignar un repartidor.
     *
     * Si no existe uno disponible, el paquete permanece
     * en las estructuras.
     */
    public Paquete despacharSiguientePrioridad() {

        Paquete paquete =
                gestorPrioridad.verSiguientePaquete();

        if (paquete == null) {
            return null;
        }

        Repartidor repartidor =
                asignarRepartidor(paquete);

        if (repartidor == null) {

            /*
             * No se elimina el paquete porque todavía
             * no puede ser despachado.
             */
            return null;
        }

        return paquete;
    }


    // =========================================================
    // ASIGNACIÓN DE REPARTIDORES
    // =========================================================

    /*
     * Centralizamos aquí la asignación.
     *
     * GestorRegistro sigue siendo quien decide qué
     * repartidor puede atender el paquete.
     *
     * QuindioExpress solamente se encarga de sincronizar
     * las estructuras cuando la asignación fue exitosa.
     */
    public Repartidor asignarRepartidor(
            Paquete paquete) {

        if (paquete == null) {
            return null;
        }

        Repartidor repartidor =
                gestorRegistro.asignarRepartidor(
                        paquete
                );

        /*
         * Solamente se retira el paquete de las colas
         * cuando realmente fue posible asignarlo.
         *
         * Esto evita perder paquetes cuando no existe
         * un repartidor disponible.
         */
        if (repartidor != null) {

            gestorAtencion.eliminarDeCola(
                    paquete
            );

            gestorPrioridad.eliminarPaquete(
                    paquete
            );
        }

        return repartidor;
    }


    public Repartidor obtenerRepartidorAsignado(
            String codigoPaquete) {

        return gestorRegistro
                .obtenerRepartidorAsignado(
                        codigoPaquete
                );
    }


    public boolean tieneRepartidorAsignado(
            String codigoPaquete) {

        return gestorRegistro
                .tieneRepartidorAsignado(
                        codigoPaquete
                );
    }


    // =========================================================
    // ENTREGA
    // =========================================================

    /*
     * Finaliza oficialmente una entrega.
     *
     * Para poder entregar:
     *
     * 1. el paquete debe existir;
     * 2. debe continuar pendiente;
     * 3. debe tener un repartidor asignado.
     *
     * De esta forma nunca se registra una entrega
     * sin repartidor.
     */
    public boolean finalizarEntrega(
            Paquete paquete) {

        if (paquete == null) {
            return false;
        }

        /*
         * Recuperamos el objeto registrado oficialmente
         * mediante su código.
         */
        Paquete paqueteRegistrado =
                gestorRegistro.buscarPaquete(
                        paquete.getCodigo()
                );

        if (paqueteRegistrado == null) {
            return false;
        }

        /*
         * Debe continuar pendiente.
         */
        if (!gestorRegistro.estaPendiente(
                paqueteRegistrado.getCodigo())) {

            return false;
        }

        /*
         * Debe existir una asignación antes de poder
         * declarar el paquete como entregado.
         */
        if (!gestorRegistro.tieneRepartidorAsignado(
                paqueteRegistrado.getCodigo())) {

            return false;
        }

        /*
         * Eliminación defensiva.
         *
         * Normalmente ya fue retirado de las dos colas
         * al asignar el repartidor.
         *
         * Sin embargo, se vuelve a intentar para garantizar
         * que nunca quede un paquete entregado dentro
         * de las estructuras de atención.
         */
        gestorAtencion.eliminarDeCola(
                paqueteRegistrado
        );

        gestorPrioridad.eliminarPaquete(
                paqueteRegistrado
        );

        /*
         * El paquete deja oficialmente de estar pendiente.
         */
        boolean retiradoPendiente =
                gestorRegistro.retirarPaquetePendiente(
                        paqueteRegistrado.getCodigo()
                );

        if (!retiradoPendiente) {
            return false;
        }

        /*
         * Se registra en el historial propio.
         *
         * GestorAtencion utiliza ListaSimple<Paquete>,
         * no una Collection de Java.
         */
        gestorAtencion.registrarEntrega(
                paqueteRegistrado
        );

        /*
         * Termina la asignación y el repartidor vuelve
         * a estar disponible.
         */
        gestorRegistro.finalizarAsignacion(
                paqueteRegistrado.getCodigo()
        );

        return true;
    }


    // =========================================================
    // CONSULTAS DE PAQUETES
    // =========================================================

    public Paquete buscarPaquete(
            String codigo) {

        return gestorRegistro.buscarPaquete(
                codigo
        );
    }


    public List<Paquete> obtenerPaquetesRegistro() {

        return gestorRegistro
                .obtenerPaquetesRegistro();
    }


    public List<Paquete> obtenerPaquetesPendientes() {

        return gestorRegistro
                .obtenerPaquetesPendientes();
    }


    public boolean estaPendiente(
            String codigoPaquete) {

        return gestorRegistro.estaPendiente(
                codigoPaquete
        );
    }


    // =========================================================
    // MUNICIPIOS
    // =========================================================

    public Set<String> obtenerMunicipios() {

        return gestorRegistro
                .obtenerMunicipios();
    }


    public Set<String> obtenerMunicipiosOrdenados() {

        return gestorRegistro
                .obtenerMunicipiosOrdenados();
    }


    public List<Paquete> obtenerPaquetesPorMunicipio(
            String municipio) {

        return gestorRegistro
                .obtenerPaquetesPorMunicipio(
                        municipio
                );
    }


    // =========================================================
    // REPARTIDORES
    // =========================================================

    public List<Repartidor> obtenerRepartidores() {

        return gestorRegistro
                .obtenerRepartidores();
    }


    // =========================================================
    // HISTORIAL
    // =========================================================

    public ListaSimple<Paquete>
    obtenerHistorialEntregas() {

        return gestorAtencion
                .getHistorialEntregas();
    }


    // =========================================================
    // PRIORITY QUEUE
    // =========================================================

    public List<Paquete>
    obtenerOrdenDespachoPrioritario() {

        return gestorPrioridad
                .obtenerPaquetesEnOrdenDespacho();
    }


    // =========================================================
    // ORDENAMIENTOS
    // =========================================================

    public List<Paquete> ordenarPorPrioridad(
            List<Paquete> paquetes) {

        return gestorPrioridad
                .ordenarPorPrioridad(
                        paquetes
                );
    }


    public List<Paquete> ordenarPorPeso(
            List<Paquete> paquetes) {

        return gestorPrioridad
                .ordenarPorPeso(
                        paquetes
                );
    }


    public List<Paquete> ordenarPorTiempo(
            List<Paquete> paquetes) {

        return gestorPrioridad
                .ordenarPorTiempo(
                        paquetes
                );
    }


    public List<Paquete> ordenarPorCodigo(
            List<Paquete> paquetes) {

        return gestorPrioridad
                .ordenarPorCodigo(
                        paquetes
                );
    }


    // =========================================================
    // ALGORITMOS
    // =========================================================

    public double calcularPesoPorDestino(
            List<Paquete> paquetes,
            String destino) {

        return gestorPrioridad
                .calcularPesoPorDestino(
                        paquetes,
                        destino
                );
    }


    public int contarPaquetesPorPrioridad(
            List<Paquete> paquetes,
            int prioridadMinima) {

        return gestorPrioridad
                .contarPaquetesPorPrioridad(
                        paquetes,
                        prioridadMinima
                );
    }


    public Paquete buscarPaquetePorCodigo(
            List<Paquete> paquetesOrdenados,
            String codigo) {

        return gestorPrioridad
                .buscarPaquetePorCodigo(
                        paquetesOrdenados,
                        codigo
                );
    }


    public Paquete buscarPaqueteMasPesado(
            List<Paquete> paquetes) {

        return gestorPrioridad
                .buscarPaqueteMasPesado(
                        paquetes
                );
    }
}