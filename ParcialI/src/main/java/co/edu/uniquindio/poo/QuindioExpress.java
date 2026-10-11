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
        this.gestorRegistro = new GestorRegistro();
        this.gestorAtencion = new GestorAtencion();
        this.gestorPrioridad = new GestorPrioridad();
    }

    // ---------------------------------------------------------
    // REGISTRO INTEGRADO
    // ---------------------------------------------------------

    public boolean registrarPaquete(Paquete paquete) {
        boolean registrado = gestorRegistro.registrarPaquete(paquete);
        if (registrado) {
            gestorAtencion.encolarLlegada(paquete);
            gestorPrioridad.agregarPaquete(paquete);
        }
        return registrado;
    }

    public boolean registrarRepartidor(Repartidor repartidor) {
        return gestorRegistro.registrarRepartidor(repartidor);
    }

    // ---------------------------------------------------------
    // ATENCIÓN Y DESPACHO SINCRONIZADO
    // ---------------------------------------------------------

    public Paquete atenderSiguienteFIFO() {
        Paquete paquete = gestorAtencion.atenderSiguiente();
        if (paquete != null) {
            gestorPrioridad.eliminarPaquete(paquete);
        }
        return paquete;
    }

    public Paquete verSiguienteFIFO() {
        return gestorAtencion.verSiguiente();
    }

    public Paquete despacharSiguientePrioridad() {
        Paquete paquete = gestorPrioridad.despacharSiguientePaquete();
        if (paquete != null) {
            gestorAtencion.eliminarDeCola(paquete);
        }
        return paquete;
    }

    public Paquete verSiguientePrioridad() {
        return gestorPrioridad.verSiguientePaquete();
    }

    // ---------------------------------------------------------
    // ASIGNACIÓN Y ENTREGA
    // ---------------------------------------------------------

    public Repartidor asignarRepartidor(Paquete paquete) {
        return gestorRegistro.asignarRepartidor(paquete);
    }

    public boolean finalizarEntrega(Paquete paquete) {
        if (paquete == null) {
            return false;
        }
        boolean retiradoPendiente = gestorRegistro.retirarPaquetePendiente(paquete.getCodigo());
        boolean asignacionTerminada = gestorRegistro.finalizarAsignacion(paquete.getCodigo());

        if (retiradoPendiente || asignacionTerminada) {
            gestorAtencion.registrarEntrega(paquete);
            return true;
        }
        return false;
    }

    // ---------------------------------------------------------
    // DELEGACIÓN DE CONSULTAS Y REPORTES
    // ---------------------------------------------------------

    public Paquete buscarPaquete(String codigo) {
        return gestorRegistro.buscarPaquete(codigo);
    }

    public List<Paquete> obtenerPaquetesRegistro() {
        return gestorRegistro.obtenerPaquetesRegistro();
    }

    public List<Paquete> obtenerPaquetesPendientes() {
        return gestorRegistro.obtenerPaquetesPendientes();
    }

    public Set<String> obtenerMunicipios() {
        return gestorRegistro.obtenerMunicipios();
    }

    public Set<String> obtenerMunicipiosOrdenados() {
        return gestorRegistro.obtenerMunicipiosOrdenados();
    }

    public List<Paquete> obtenerPaquetesPorMunicipio(String municipio) {
        return gestorRegistro.obtenerPaquetesPorMunicipio(municipio);
    }

    public List<Repartidor> obtenerRepartidores() {
        return gestorRegistro.obtenerRepartidores();
    }

    public ListaSimple<Paquete> obtenerHistorialEntregas() {
        return gestorAtencion.getHistorialEntregas();
    }

    public List<Paquete> obtenerOrdenDespachoPrioritario() {
        return gestorPrioridad.obtenerPaquetesEnOrdenDespacho();
    }

    // ---------------------------------------------------------
    // DELEGACIÓN DE ALGORITMOS Y ORDENAMIENTOS
    // ---------------------------------------------------------

    public double calcularPesoPorDestino(List<Paquete> paquetes, String destino) {
        return gestorPrioridad.calcularPesoPorDestino(paquetes, destino);
    }

    public int contarPaquetesPorPrioridad(List<Paquete> paquetes, int prioridadMinima) {
        return gestorPrioridad.contarPaquetesPorPrioridad(paquetes, prioridadMinima);
    }

    public Paquete buscarPaquetePorCodigo(List<Paquete> paquetesOrdenados, String codigo) {
        return gestorPrioridad.buscarPaquetePorCodigo(paquetesOrdenados, codigo);
    }

    public Paquete buscarPaqueteMasPesado(List<Paquete> paquetes) {
        return gestorPrioridad.buscarPaqueteMasPesado(paquetes);
    }

    public List<Paquete> ordenarPorPrioridad(List<Paquete> paquetes) {
        return gestorPrioridad.ordenarPorPrioridad(paquetes);
    }

    public List<Paquete> ordenarPorPeso(List<Paquete> paquetes) {
        return gestorPrioridad.ordenarPorPeso(paquetes);
    }

    public List<Paquete> ordenarPorTiempo(List<Paquete> paquetes) {
        return gestorPrioridad.ordenarPorTiempo(paquetes);
    }

    public List<Paquete> ordenarPorCodigo(List<Paquete> paquetes) {
        return gestorPrioridad.ordenarPorCodigo(paquetes);
    }
}