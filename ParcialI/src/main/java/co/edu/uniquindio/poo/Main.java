package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.model.Paquete;
import co.edu.uniquindio.poo.model.Repartidor;
import co.edu.uniquindio.poo.service.GestorPrioridad;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Paquete paquete1 = new Paquete(
                "PQ731", "Armenia", 4.5, 5, 45);

        Paquete paquete2 = new Paquete(
                "PQ105", "Salento", 3.2, 4, 25);

        Paquete paquete3 = new Paquete(
                "PQ942", "Calarca", 6.7, 35);

        Repartidor repartidor1 = new Repartidor(
                "R001", "Carlos Gomez", "Armenia", true);

        System.out.println("=== DATOS DE PRUEBA ===");
        System.out.println(paquete1);
        System.out.println(paquete2);
        System.out.println(paquete3);
        System.out.println(repartidor1);

        System.out.println("\n=== COMPARACIÓN NATURAL ===");
        System.out.println(
                "Comparación por código: "
                        + paquete1.compareTo(paquete2));

        GestorPrioridad gestor = new GestorPrioridad();

        gestor.agregarPaquete(paquete1);
        gestor.agregarPaquete(paquete2);
        gestor.agregarPaquete(paquete3);

        System.out.println("\n=== ORDEN DE DESPACHO ===");

        while (!gestor.estaVacia()) {
            Paquete siguiente = gestor.despacharSiguientePaquete();
            System.out.println(siguiente);
        }

        // Lista de paquetes para demostrar los algoritmos.
        List<Paquete> paquetes = new ArrayList<>();
        paquetes.add(paquete1);
        paquetes.add(paquete2);
        paquetes.add(paquete3);

        System.out.println("\n=== ALGORITMOS ===");

        // 1. Suma recursiva del peso por destino.
        double pesoArmenia =
                gestor.calcularPesoPorDestino(paquetes, "Armenia");
        System.out.println("Peso total para Armenia: " + pesoArmenia);

        // 2. Conteo recursivo por prioridad mínima.
        int cantidadPrioridad =
                gestor.contarPaquetesPorPrioridad(paquetes, 4);
        System.out.println(
                "Paquetes con prioridad >= 4: " + cantidadPrioridad);

        // 3. Búsqueda binaria por código.
        List<Paquete> paquetesOrdenados = new ArrayList<>(paquetes);
        Collections.sort(paquetesOrdenados);

        Paquete encontrado =
                gestor.buscarPaquetePorCodigo(paquetesOrdenados, "PQ105");
        System.out.println("Búsqueda de PQ105: " + encontrado);

        // 4. Divide y vencerás: paquete de mayor peso.
        Paquete masPesado = gestor.buscarPaqueteMasPesado(paquetes);
        System.out.println("Paquete más pesado: " + masPesado);
    }
}