package co.edu.uniquindio.poo.comparadores;

import co.edu.uniquindio.poo.model.Paquete;
import java.util.Comparator;

public class ComparadorPrioridad implements Comparator<Paquete> {

    @Override
    public int compare(Paquete paquete1, Paquete paquete2) {
        return Integer.compare(
                paquete2.getPrioridad(),
                paquete1.getPrioridad()
        );
    }
}