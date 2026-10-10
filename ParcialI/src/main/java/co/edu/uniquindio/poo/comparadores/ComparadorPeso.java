package co.edu.uniquindio.poo.comparadores;

import co.edu.uniquindio.poo.model.Paquete;
import java.util.Comparator;

public class ComparadorPeso implements Comparator<Paquete> {

    @Override
    public int compare(Paquete paquete1, Paquete paquete2) {
        return Double.compare(
                paquete2.getPeso(),
                paquete1.getPeso()
        );
    }
}