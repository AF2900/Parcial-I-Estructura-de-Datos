package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.data.DatosIniciales;
import co.edu.uniquindio.poo.model.Paquete;
import co.edu.uniquindio.poo.model.Repartidor;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== INICIALIZANDO SISTEMA QUINDÍO EXPRESS ===");
        QuindioExpress sistema = new QuindioExpress();

        // 1. Cargar datos iniciales (30 paquetes y 6 repartidores)
        DatosIniciales.cargarDatos(sistema);
        System.out.println("Paquetes registrados inicialmente: " + sistema.obtenerPaquetesRegistro().size());
        System.out.println("Repartidores registrados: " + sistema.obtenerRepartidores().size());

        // 2. Demostración de municipios destino y ordenados
        System.out.println("\nMunicipios destino (sin repetir): " + sistema.obtenerMunicipios());
        System.out.println("Municipios ordenados alfabéticamente: " + sistema.obtenerMunicipiosOrdenados());

        // 3. Búsqueda rápida por código (HashMap O(1))
        String codigoBuscar = "PQ731";
        Paquete paqueteBuscado = sistema.buscarPaquete(codigoBuscar);
        System.out.println("\nBúsqueda de " + codigoBuscar + ": " + paqueteBuscado);

        // 4. Demostración de paquetes por municipio
        String municipioConsulta = "Armenia";
        List<Paquete> paquetesArmenia = sistema.obtenerPaquetesPorMunicipio(municipioConsulta);
        System.out.println("\nPaquetes en " + municipioConsulta + " (" + paquetesArmenia.size() + "):");
        for (Paquete p : paquetesArmenia) {
            System.out.println(" - " + p.getCodigo() + " (Peso: " + p.getPeso() + "kg, Prioridad: " + p.getPrioridad() + ")");
        }

        // 5. Demostración de algoritmos recursivos
        System.out.println("\n=== ALGORITMOS Y ANÁLISIS ===");
        double pesoTotalArmenia = sistema.calcularPesoPorDestino(paquetesArmenia, "Armenia");
        System.out.println("Peso total recursivo para Armenia: " + pesoTotalArmenia + " kg");

        int conteoPrioridad = sistema.contarPaquetesPorPrioridad(sistema.obtenerPaquetesRegistro(), 4);
        System.out.println("Paquetes con prioridad >= 4: " + conteoPrioridad);

        // Búsqueda binaria
        List<Paquete> ordenadosCodigo = sistema.ordenarPorCodigo(sistema.obtenerPaquetesRegistro());
        Paquete encontradoBinaria = sistema.buscarPaquetePorCodigo(ordenadosCodigo, "PQ567");
        System.out.println("Búsqueda binaria de PQ567: " + encontradoBinaria);

        // Divide y vencerás
        Paquete masPesado = sistema.buscarPaqueteMasPesado(sistema.obtenerPaquetesRegistro());
        System.out.println("Paquete más pesado (Divide y Vencerás): " + masPesado);

        // 6. Demostración de asignación y entrega
        System.out.println("\n=== FLUJO DE ASIGNACIÓN Y ENTREGA ===");
        Paquete primerPendiente = sistema.obtenerPaquetesPendientes().get(0);
        System.out.println("Intentando asignar repartidor para el paquete: " + primerPendiente);

        Repartidor repartidorAsignado = sistema.asignarRepartidor(primerPendiente);
        if (repartidorAsignado != null) {
            System.out.println("Repartidor asignado con éxito: " + repartidorAsignado.getNombre() + " (Zona: " + repartidorAsignado.getZona() + ")");

            // Finalizar entrega
            boolean entregaExitosa = sistema.finalizarEntrega(primerPendiente);
            if (entregaExitosa) {
                System.out.println("¡Entrega registrada y finalizada correctamente!");
            }
        }

        // 7. Verificación del historial con la lista simplemente enlazada propia
        System.out.println("\n=== HISTORIAL DE ENTREGAS (Lista Simple Propia) ===");
        System.out.println("Cantidad de elementos en historial: " + sistema.obtenerHistorialEntregas().tamano());
        for (Paquete p : sistema.obtenerHistorialEntregas()) {
            System.out.println("Entregado -> " + p.getCodigo() + " (" + p.getDestino() + ")");
        }

        System.out.println("\n¡Integración de QuindíoExpress completada con éxito!");
    }
}