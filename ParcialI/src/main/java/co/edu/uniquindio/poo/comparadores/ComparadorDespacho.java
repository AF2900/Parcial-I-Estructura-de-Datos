package co.edu.uniquindio.poo.comparadores;

import co.edu.uniquindio.poo.model.Paquete;
import java.util.Comparator;

public class ComparadorDespacho implements Comparator<Paquete> {

    @Override
    public int compare(Paquete paquete1, Paquete paquete2) {
        int comparacionPrioridad = Integer.compare(
                paquete2.getPrioridad(),
                paquete1.getPrioridad()
        );

        if (comparacionPrioridad != 0) {
            return comparacionPrioridad;
        }

        int comparacionTiempo = Integer.compare(
                paquete1.getTiempoEstimado(),
                paquete2.getTiempoEstimado()
        );

        if (comparacionTiempo != 0) {
            return comparacionTiempo;
        }

        return paquete1.getCodigo().compareTo(
                paquete2.getCodigo()
        );
    }
}