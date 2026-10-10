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

        // El código debe ser único.
        if (paquetesPorCodigo.containsKey(
                paquete.getCodigo())) {
            return false;
        }

        // Conserva el orden de registro.
        paquetesRegistro.add(paquete);

        // Permite búsquedas rápidas por código.
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

        // Agrupación municipio -> paquetes.
        List<Paquete> paquetesMunicipio =
                paquetesPorMunicipio.get(
                        paquete.getDestino()
                );

        if (paquetesMunicipio == null) {

            paquetesMunicipio = new ArrayList<>();

            paquetesPorMunicipio.put(
                    paquete.getDestino(),
                    paquetesMunicipio
            );
        }

        paquetesMunicipio.add(paquete);

        return true;
    }
}