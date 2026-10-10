package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.model.Paquete;
import co.edu.uniquindio.poo.service.GestorPrioridad;
import co.edu.uniquindio.poo.comparadores.ComparadorPeso;
import co.edu.uniquindio.poo.comparadores.ComparadorTiempo;
import co.edu.uniquindio.poo.comparadores.ComparadorPrioridad;
import java.util.Collections;
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
        List<Paquete> paquetesOrdenados = new ArrayList<>(paquetes);
        Collections.sort(paquetesOrdenados);

        Paquete encontrado =
                gestor.buscarPaquetePorCodigo(paquetesOrdenados, "PQ942");

        if (encontrado == null
                || !encontrado.getCodigo().equals("PQ942")) {
            throw new AssertionError(
                    "No se encontró el paquete PQ942."
            );
        }

        System.out.println("Búsqueda de PQ942: " + encontrado);

        Paquete inexistente =
                gestor.buscarPaquetePorCodigo(paquetesOrdenados, "PQ999");

        if (inexistente != null) {
            throw new AssertionError(
                    "La búsqueda encontró un paquete que no existe."
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


        // 8. Código inexistente en la lista ordenada.
        if (gestor.buscarPaquetePorCodigo(
                paquetesOrdenados, "PQ999") != null) {
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

        // 10. Probar desempate por código.
        GestorPrioridad gestorEmpate = new GestorPrioridad();

        Paquete paqueteZ = new Paquete(
                "PQ900", "Armenia", 2.0, 5, 20
        );

        Paquete paqueteA = new Paquete(
                "PQ100", "Salento", 3.0, 5, 20
        );

        gestorEmpate.agregarPaquete(paqueteZ);
        gestorEmpate.agregarPaquete(paqueteA);

        Paquete primero = gestorEmpate.despacharSiguientePaquete();

        if (primero == null
                || !primero.getCodigo().equals("PQ100")) {
            throw new AssertionError(
                    "El desempate por código no funciona correctamente."
            );
        }

        System.out.println("PRUEBA 10 OK: desempate por código.");


        // 11. Probar desempate por tiempo estimado.
        GestorPrioridad gestorTiempo = new GestorPrioridad();

        Paquete paqueteLento = new Paquete(
                "PQ200", "Armenia", 2.0, 4, 50
        );

        Paquete paqueteRapido = new Paquete(
                "PQ300", "Salento", 3.0, 4, 15
        );

        gestorTiempo.agregarPaquete(paqueteLento);
        gestorTiempo.agregarPaquete(paqueteRapido);

        Paquete primeroPorTiempo =
                gestorTiempo.despacharSiguientePaquete();

        if (primeroPorTiempo == null
                || !primeroPorTiempo.getCodigo().equals("PQ300")) {
            throw new AssertionError(
                    "El desempate por tiempo no funciona correctamente."
            );
        }

        System.out.println("PRUEBA 11 OK: desempate por tiempo.");

        // 12. Probar que la prioridad prevalece sobre el tiempo.
        GestorPrioridad gestorPrioridad = new GestorPrioridad();

        Paquete prioridadBaja = new Paquete(
                "PQ400", "Armenia", 2.0, 3, 10
        );

        Paquete prioridadAlta = new Paquete(
                "PQ500", "Salento", 3.0, 5, 60
        );

        gestorPrioridad.agregarPaquete(prioridadBaja);
        gestorPrioridad.agregarPaquete(prioridadAlta);

        Paquete primeroPorPrioridad =
                gestorPrioridad.despacharSiguientePaquete();

        if (primeroPorPrioridad == null
                || !primeroPorPrioridad.getCodigo().equals("PQ500")) {
            throw new AssertionError(
                    "La prioridad debe prevalecer sobre el tiempo."
            );
        }

        System.out.println("PRUEBA 12 OK: prioridad prevalece sobre tiempo.");

        // 13. Orden por peso descendente.
        List<Paquete> porPeso = new ArrayList<>(paquetes);
        porPeso.sort(new ComparadorPeso());

        String[] ordenPeso = {"PQ942", "PQ731", "PQ105", "PQ420"};

        for (int i = 0; i < ordenPeso.length; i++) {
            if (!ordenPeso[i].equals(porPeso.get(i).getCodigo())) {
                throw new AssertionError(
                        "Error en el orden por peso, posición " + i);
            }
        }
        System.out.println("PRUEBA 13 OK: orden por peso.");

        // 14. Orden por tiempo estimado ascendente.
        List<Paquete> porTiempo = new ArrayList<>(paquetes);
        porTiempo.sort(new ComparadorTiempo());

        String[] ordenTiempo = {"PQ105", "PQ420", "PQ942", "PQ731"};

        for (int i = 0; i < ordenTiempo.length; i++) {
            if (!ordenTiempo[i].equals(porTiempo.get(i).getCodigo())) {
                throw new AssertionError(
                        "Error en el orden por tiempo, posición " + i);
            }
        }
        System.out.println("PRUEBA 14 OK: orden por tiempo.");

        // 15. Orden natural por código ascendente (Comparable).
        List<Paquete> porCodigo = new ArrayList<>(paquetes);
        Collections.sort(porCodigo);

        String[] ordenCodigo = {"PQ105", "PQ420", "PQ731", "PQ942"};

        for (int i = 0; i < ordenCodigo.length; i++) {
            if (!ordenCodigo[i].equals(porCodigo.get(i).getCodigo())) {
                throw new AssertionError(
                        "Error en el orden natural, posición " + i);
            }
        }
        System.out.println("PRUEBA 15 OK: orden natural por código.");

        // 16. Orden por prioridad descendente.
        List<Paquete> porPrioridad = new ArrayList<>(paquetes);
        porPrioridad.sort(new ComparadorPrioridad());

        for (int i = 0; i < porPrioridad.size() - 1; i++) {
            if (porPrioridad.get(i).getPrioridad()
                    < porPrioridad.get(i + 1).getPrioridad()) {
                throw new AssertionError(
                        "Error en el orden por prioridad, posición " + i);
            }
        }

        System.out.println("PRUEBA 16 OK: orden por prioridad.");


        System.out.println("\nTodas las pruebas terminaron correctamente.");
    }
}