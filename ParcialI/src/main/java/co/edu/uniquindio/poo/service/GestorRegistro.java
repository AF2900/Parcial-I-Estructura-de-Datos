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

    // Mantiene los paquetes en el orden exacto en que fueron registrados.
    private final List<Paquete> paquetesRegistro;

    // Permite buscar rápidamente un paquete utilizando su código.
    private final Map<String, Paquete> paquetesPorCodigo;

    // Mantiene los municipios sin elementos repetidos.
    private final Set<String> municipios;

    // Mantiene los municipios sin repetir y ordenados alfabéticamente.
    private final Set<String> municipiosOrdenados;

    // Relaciona cada municipio con sus paquetes.
    private final Map<String, List<Paquete>> paquetesPorMunicipio;

    // Permite registrar y consultar repartidores por identificación.
    private final Map<String, Repartidor> repartidores;

    // Relaciona el código de un paquete con el repartidor asignado.
    private final Map<String, Repartidor> asignaciones;


    public GestorRegistro() {

        paquetesRegistro = new ArrayList<>();

        paquetesPorCodigo = new HashMap<>();

        municipios = new HashSet<>();

        municipiosOrdenados = new TreeSet<>();

        paquetesPorMunicipio = new HashMap<>();

        repartidores = new HashMap<>();

        asignaciones = new HashMap<>();
    }


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

        if (paquete.getPrioridad() < 0 ||
                paquete.getPrioridad() > 5) {
            return false;
        }

        if (paquete.getTiempoEstimado() <= 0) {
            return false;
        }

        // El código del paquete debe ser único.
        if (paquetesPorCodigo.containsKey(
                paquete.getCodigo())) {
            return false;
        }

        // Conserva el orden exacto de registro.
        paquetesRegistro.add(paquete);

        // Permite consultar el paquete rápidamente por código.
        paquetesPorCodigo.put(
                paquete.getCodigo(),
                paquete
        );

        // Guarda el municipio sin repetir.
        municipios.add(
                paquete.getDestino()
        );

        // Guarda el municipio sin repetir y ordenado.
        municipiosOrdenados.add(
                paquete.getDestino()
        );

        // Busca la lista correspondiente al municipio.
        List<Paquete> paquetesMunicipio =
                paquetesPorMunicipio.get(
                        paquete.getDestino()
                );

        // Si todavía no existe una lista para ese municipio,
        // se crea.
        if (paquetesMunicipio == null) {

            paquetesMunicipio = new ArrayList<>();

            paquetesPorMunicipio.put(
                    paquete.getDestino(),
                    paquetesMunicipio
            );
        }

        // Agrega el paquete a la lista de su municipio.
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

    public boolean registrarRepartidor(Repartidor repartidor) {

        if (repartidor == null) {
            return false;
        }

        if (repartidor.getIdentificacion() == null ||
                repartidor.getIdentificacion().trim().isEmpty()) {
            return false;
        }

        if (repartidor.getNombre() == null ||
                repartidor.getNombre().trim().isEmpty()) {
            return false;
        }

        if (repartidor.getZona() == null ||
                repartidor.getZona().trim().isEmpty()) {
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


    public Repartidor buscarRepartidor(String identificacion) {

        if (identificacion == null ||
                identificacion.trim().isEmpty()) {
            return null;
        }

        return repartidores.get(identificacion);
    }


    public List<Repartidor> obtenerRepartidores() {

        return new ArrayList<>(repartidores.values());
    }


    public Repartidor asignarRepartidor(Paquete paquete) {

        if (paquete == null) {
            return null;
        }

        // El paquete debe estar registrado en el sistema.
        if (!paquetesPorCodigo.containsKey(
                paquete.getCodigo())) {
            return null;
        }

        // Evita asignar dos veces el mismo paquete.
        if (asignaciones.containsKey(
                paquete.getCodigo())) {
            return null;
        }

        for (Repartidor repartidor : repartidores.values()) {

            boolean mismaZona =
                    repartidor.getZona()
                            .equalsIgnoreCase(
                                    paquete.getDestino()
                            );

            if (repartidor.isDisponible() &&
                    mismaZona) {

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


    public boolean liberarRepartidor(
            String identificacion) {

        Repartidor repartidor =
                buscarRepartidor(identificacion);

        if (repartidor == null) {
            return false;
        }

        repartidor.setDisponible(true);

        return true;
    }
}