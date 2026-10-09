
package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.model.Paquete;
import co.edu.uniquindio.poo.service.GestorPrioridad;

import java.util.ArrayList;
import java.util.List;

public class PruebaGestorPrioridad {

    public static void main(String[] args) {

        GestorPrioridad gestor = new GestorPrioridad();

        Paquete p1 = new Paquete("PQ731", "Armenia", 4.5, 5, 45);
        Paquete p2 = new Paquete("PQ105", "Salento", 3.2, 5, 25);
        Paquete p3 = new Paquete("PQ942", "Calarca", 6.7, 4, 35);
        Paquete p4 = new Paquete("PQ420", "Armenia", 2.0, 3, 30);

        List<Paquete> paquetes = new ArrayList<>();
        paquetes.add(p1);
        paquetes.add(p2);
        paquetes.add(p3);
        paquetes.add(p4);

        // 1. Probar cola de prioridad.
        for (Paquete paquete : paquetes) {
            gestor.agregarPaquete(paquete);
        }

        List<Paquete> ordenados =
                gestor.obtenerPaquetesEnOrdenDespacho();

        String[] ordenEsperado = {
                "PQ105", "PQ731", "PQ942", "PQ420"
        };

        for (int i = 0; i < ordenEsperado.length; i++) {
            if (!ordenEsperado[i].equals(
                    ordenados.get(i).getCodigo())) {
                throw new AssertionError(
                        "Error en el orden de despacho, posición " + i
                );
            }
        }

        System.out.println("PRUEBA 1 OK: orden de despacho.");

        // 2. Probar suma recursiva por destino.
        double pesoArmenia =
                gestor.calcularPesoPorDestino(paquetes, "Armenia");

        if (Math.abs(pesoArmenia - 6.5) > 0.000001) {
            throw new AssertionError(
                    "Peso incorrecto para Armenia: " + pesoArmenia
            );
        }

        System.out.println("PRUEBA 2 OK: peso por destino.");

        // 3. Probar conteo por prioridad mínima.
        int cantidad =
                gestor.contarPaquetesPorPrioridad(paquetes, 4);

        if (cantidad != 3) {
            throw new AssertionError(
                    "Cantidad incorrecta: " + cantidad
            );
        }

        System.out.println("PRUEBA 3 OK: conteo por prioridad.");

        // 4. Probar búsqueda binaria por código.
        Paquete encontrado =
                gestor.buscarPaquetePorCodigo(paquetes, "PQ942");

        if (encontrado == null
                || !encontrado.getCodigo().equals("PQ942")) {
            throw new AssertionError(
                    "No se encontró el paquete PQ942."
            );
        }

        System.out.println("PRUEBA 4 OK: búsqueda binaria.");

        // 5. Probar divide y vencerás.
        Paquete masPesado =
                gestor.buscarPaqueteMasPesado(paquetes);

        if (masPesado == null
                || !masPesado.getCodigo().equals("PQ942")) {
            throw new AssertionError(
                    "El paquete más pesado no es PQ942."
            );
        }

        System.out.println("PRUEBA 5 OK: paquete más pesado.");

        // 6. Verificar que la búsqueda no alteró la lista original.
        if (!paquetes.get(0).getCodigo().equals("PQ731")) {
            throw new AssertionError(
                    "La búsqueda modificó la lista original."
            );
        }

        System.out.println("PRUEBA 6 OK: lista original conservada.");

        System.out.println(
                "\nTodas las pruebas terminaron correctamente."
        );


        // 7. Lista vacía: no debe encontrar paquete más pesado.
        List<Paquete> listaVacia = new ArrayList<>();

        if (gestor.buscarPaqueteMasPesado(listaVacia) != null) {
            throw new AssertionError(
                    "Una lista vacía no debe tener paquete más pesado."
            );
        }

        if (gestor.calcularPesoPorDestino(listaVacia, "Armenia") != 0.0) {
            throw new AssertionError(
                    "El peso de una lista vacía debe ser cero."
            );
        }

        if (gestor.contarPaquetesPorPrioridad(listaVacia, 4) != 0) {
            throw new AssertionError(
                    "Una lista vacía debe tener cero paquetes."
            );
        }

        System.out.println("PRUEBA 7 OK: lista vacía.");

        // 8. Código inexistente.
        if (gestor.buscarPaquetePorCodigo(
                paquetes, "PQ999") != null) {
            throw new AssertionError(
                    "Un código inexistente no debe encontrarse."
            );
        }

        System.out.println("PRUEBA 8 OK: código inexistente.");

        // 9. Cola vacía.
        GestorPrioridad gestorVacio = new GestorPrioridad();

        if (gestorVacio.verSiguientePaquete() != null
                || gestorVacio.despacharSiguientePaquete() != null
                || !gestorVacio.estaVacia()) {
            throw new AssertionError(
                    "La cola vacía no se comporta correctamente."
            );
        }

        System.out.println("PRUEBA 9 OK: cola vacía.");
    }
}