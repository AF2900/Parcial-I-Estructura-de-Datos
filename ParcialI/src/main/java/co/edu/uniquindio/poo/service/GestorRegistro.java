package co.edu.uniquindio.poo.service;

import co.edu.uniquindio.poo.model.Paquete;
import co.edu.uniquindio.poo.model.Repartidor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class GestorRegistro {

    // Conserva todos los paquetes en el orden exacto
    // en que fueron registrados durante la jornada.
    private final List<Paquete> paquetesRegistro;

    // Conserva solamente los paquetes que todavía
    // no han sido entregados.
    private final List<Paquete> paquetesPendientes;

    // Permite buscar rápidamente un paquete por código.
    private final Map<String, Paquete> paquetesPorCodigo;

    // Mantiene los municipios sin elementos repetidos.
    private final Set<String> municipios;

    // Mantiene los municipios sin repetir
    // y ordenados alfabéticamente.
    private final Set<String> municipiosOrdenados;

    // Relaciona cada municipio con sus paquetes.
    private final Map<String, List<Paquete>> paquetesPorMunicipio;

    // Permite registrar y consultar repartidores
    // mediante su identificación.
    private final Map<String, Repartidor> repartidores;

    // Relaciona el código de un paquete
    // con el repartidor que tiene asignado.
    private final Map<String, Repartidor> asignaciones;


    public GestorRegistro() {

        paquetesRegistro = new ArrayList<>();

        paquetesPendientes = new ArrayList<>();

        paquetesPorCodigo = new HashMap<>();

        municipios = new HashSet<>();

        municipiosOrdenados = new TreeSet<>();

        paquetesPorMunicipio = new HashMap<>();

        repartidores = new HashMap<>();

        asignaciones = new HashMap<>();
    }


    // ---------------------------------------------------------
    // PAQUETES
    // ---------------------------------------------------------

    public boolean registrarPaquete(Paquete paquete) {

        if (paquete == null) {
            return false;
        }

        if (paquete.getCodigo() == null ||
                paquete.getCodigo().trim().isEmpty()) {
            return false;
        }

        if (paquete.getDestino() == null ||
                paquete.getDestino().trim().isEmpty()) {
            return false;
        }

        if (paquete.getPeso() <= 0) {
            return false;
        }

        // Rango utilizado según aclaración del docente.
        if (paquete.getPrioridad() < 0 ||
                paquete.getPrioridad() > 5) {
            return false;
        }

        if (paquete.getTiempoEstimado() <= 0) {
            return false;
        }

        // No pueden existir dos paquetes con el mismo código.
        if (paquetesPorCodigo.containsKey(
                paquete.getCodigo())) {
            return false;
        }

        // Conserva permanentemente el orden original
        // de registro de la jornada.
        paquetesRegistro.add(paquete);

        // Inicialmente todo paquete registrado está pendiente.
        paquetesPendientes.add(paquete);

        // Asociación código -> Paquete.
        paquetesPorCodigo.put(
                paquete.getCodigo(),
                paquete
        );

        // Municipio sin duplicados.
        municipios.add(
                paquete.getDestino()
        );

        // Municipio ordenado y sin duplicados.
        municipiosOrdenados.add(
                paquete.getDestino()
        );

        // Obtiene la lista correspondiente al municipio.
        List<Paquete> paquetesMunicipio =
                paquetesPorMunicipio.get(
                        paquete.getDestino()
                );

        // Si es el primer paquete para ese municipio,
        // se crea su lista.
        if (paquetesMunicipio == null) {

            paquetesMunicipio = new ArrayList<>();

            paquetesPorMunicipio.put(
                    paquete.getDestino(),
                    paquetesMunicipio
            );
        }

        // Agrega el paquete al municipio correspondiente.
        paquetesMunicipio.add(paquete);

        return true;
    }


    public Paquete buscarPaquete(String codigo) {

        if (codigo == null ||
                codigo.trim().isEmpty()) {
            return null;
        }

        return paquetesPorCodigo.get(codigo);
    }


    public List<Paquete> obtenerPaquetesRegistro() {

        return new ArrayList<>(paquetesRegistro);
    }


    public List<Paquete> obtenerPaquetesPendientes() {

        return new ArrayList<>(paquetesPendientes);
    }


    public boolean estaPendiente(String codigoPaquete) {

        Paquete paquete =
                buscarPaquete(codigoPaquete);

        if (paquete == null) {
            return false;
        }

        return paquetesPendientes.contains(paquete);
    }


    public boolean retirarPaquetePendiente(
            String codigoPaquete) {

        Paquete paquete =
                buscarPaquete(codigoPaquete);

        if (paquete == null) {
            return false;
        }

        return paquetesPendientes.remove(paquete);
    }


    // ---------------------------------------------------------
    // MUNICIPIOS
    // ---------------------------------------------------------

    public Set<String> obtenerMunicipios() {

        return new HashSet<>(municipios);
    }


    public Set<String> obtenerMunicipiosOrdenados() {

        return new TreeSet<>(municipiosOrdenados);
    }


    public List<Paquete> obtenerPaquetesPorMunicipio(
            String municipio) {

        if (municipio == null ||
                municipio.trim().isEmpty()) {
            return new ArrayList<>();
        }

        List<Paquete> paquetes =
                paquetesPorMunicipio.get(municipio);

        if (paquetes == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(paquetes);
    }


    // ---------------------------------------------------------
    // REPARTIDORES
    // ---------------------------------------------------------

    public boolean registrarRepartidor(
            Repartidor repartidor) {

        if (repartidor == null) {
            return false;
        }

        if (repartidor.getIdentificacion() == null ||
                repartidor.getIdentificacion()
                        .trim()
                        .isEmpty()) {
            return false;
        }

        if (repartidor.getNombre() == null ||
                repartidor.getNombre()
                        .trim()
                        .isEmpty()) {
            return false;
        }

        if (repartidor.getZona() == null ||
                repartidor.getZona()
                        .trim()
                        .isEmpty()) {
            return false;
        }

        // La identificación debe ser única.
        if (repartidores.containsKey(
                repartidor.getIdentificacion())) {
            return false;
        }

        repartidores.put(
                repartidor.getIdentificacion(),
                repartidor
        );

        return true;
    }


    public Repartidor buscarRepartidor(
            String identificacion) {

        if (identificacion == null ||
                identificacion.trim().isEmpty()) {
            return null;
        }

        return repartidores.get(identificacion);
    }


    public List<Repartidor> obtenerRepartidores() {

        return new ArrayList<>(
                repartidores.values()
        );
    }


    // ---------------------------------------------------------
    // ASIGNACIONES
    // ---------------------------------------------------------

    public Repartidor asignarRepartidor(
            Paquete paquete) {

        if (paquete == null) {
            return null;
        }

        // El paquete debe existir en el sistema.
        if (!paquetesPorCodigo.containsKey(
                paquete.getCodigo())) {
            return null;
        }

        // No se puede asignar un paquete
        // que ya no está pendiente.
        if (!paquetesPendientes.contains(paquete)) {
            return null;
        }

        // El paquete no puede tener ya otro repartidor.
        if (asignaciones.containsKey(
                paquete.getCodigo())) {
            return null;
        }

        // Busca un repartidor disponible
        // cuya zona coincida con el destino.
        for (Repartidor repartidor :
                repartidores.values()) {

            boolean mismaZona =
                    repartidor.getZona()
                            .equalsIgnoreCase(
                                    paquete.getDestino()
                            );

            if (repartidor.isDisponible()
                    && mismaZona) {

                repartidor.setDisponible(false);

                asignaciones.put(
                        paquete.getCodigo(),
                        repartidor
                );

                return repartidor;
            }
        }

        return null;
    }


    public Repartidor obtenerRepartidorAsignado(
            String codigoPaquete) {

        if (codigoPaquete == null ||
                codigoPaquete.trim().isEmpty()) {
            return null;
        }

        return asignaciones.get(codigoPaquete);
    }


    public boolean tieneRepartidorAsignado(
            String codigoPaquete) {

        if (codigoPaquete == null ||
                codigoPaquete.trim().isEmpty()) {
            return false;
        }

        return asignaciones.containsKey(
                codigoPaquete
        );
    }


    public boolean finalizarAsignacion(
            String codigoPaquete) {

        if (codigoPaquete == null ||
                codigoPaquete.trim().isEmpty()) {
            return false;
        }

        Repartidor repartidor =
                asignaciones.remove(
                        codigoPaquete
                );

        if (repartidor == null) {
            return false;
        }

        // Después de terminar la asignación,
        // el repartidor vuelve a estar disponible.
        repartidor.setDisponible(true);

        return true;
    }
}