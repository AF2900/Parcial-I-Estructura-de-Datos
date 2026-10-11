package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.data.DatosIniciales;
import co.edu.uniquindio.poo.model.Paquete;
import co.edu.uniquindio.poo.model.Repartidor;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println(
                "========================================"
        );

        System.out.println(
                "       SISTEMA QUINDÍO EXPRESS"
        );

        System.out.println(
                "========================================"
        );


        // =====================================================
        // 1. CREAR SISTEMA Y CARGAR DATOS
        // =====================================================

        QuindioExpress sistema =
                new QuindioExpress();

        DatosIniciales.cargarDatos(
                sistema
        );


        System.out.println(
                "\n=== DATOS INICIALES ==="
        );

        System.out.println(
                "Paquetes registrados: "
                        + sistema
                        .obtenerPaquetesRegistro()
                        .size()
        );

        System.out.println(
                "Paquetes pendientes: "
                        + sistema
                        .obtenerPaquetesPendientes()
                        .size()
        );

        System.out.println(
                "Repartidores registrados: "
                        + sistema
                        .obtenerRepartidores()
                        .size()
        );


        // =====================================================
        // 2. MUNICIPIOS
        // =====================================================

        System.out.println(
                "\n=== MUNICIPIOS ==="
        );

        System.out.println(
                "Municipios sin repetir: "
                        + sistema.obtenerMunicipios()
        );

        System.out.println(
                "Municipios ordenados: "
                        + sistema.obtenerMunicipiosOrdenados()
        );


        // =====================================================
        // 3. BÚSQUEDA RÁPIDA POR HASHMAP
        // =====================================================

        System.out.println(
                "\n=== BÚSQUEDA POR CÓDIGO ==="
        );

        Paquete paqueteBuscado =
                sistema.buscarPaquete(
                        "PQ731"
                );

        System.out.println(
                "Resultado de buscar PQ731:"
        );

        System.out.println(
                paqueteBuscado
        );


        // =====================================================
        // 4. PAQUETES POR MUNICIPIO
        // =====================================================

        System.out.println(
                "\n=== PAQUETES DE ARMENIA ==="
        );

        List<Paquete> paquetesArmenia =
                sistema.obtenerPaquetesPorMunicipio(
                        "Armenia"
                );

        for (Paquete paquete :
                paquetesArmenia) {

            System.out.println(
                    paquete
            );
        }


        // =====================================================
        // 5. RECURSIVIDAD
        // =====================================================

        System.out.println(
                "\n=== RECURSIVIDAD ==="
        );

        double pesoArmenia =
                sistema.calcularPesoPorDestino(
                        sistema.obtenerPaquetesRegistro(),
                        "Armenia"
                );

        System.out.println(
                "Peso total de paquetes para Armenia: "
                        + pesoArmenia
                        + " kg"
        );


        int prioridadMinima =
                sistema.contarPaquetesPorPrioridad(
                        sistema.obtenerPaquetesRegistro(),
                        4
                );

        System.out.println(
                "Paquetes con prioridad >= 4: "
                        + prioridadMinima
        );


        // =====================================================
        // 6. BÚSQUEDA BINARIA
        // =====================================================

        System.out.println(
                "\n=== BÚSQUEDA BINARIA ==="
        );

        List<Paquete> paquetesOrdenados =
                sistema.ordenarPorCodigo(
                        sistema.obtenerPaquetesRegistro()
                );

        Paquete encontrado =
                sistema.buscarPaquetePorCodigo(
                        paquetesOrdenados,
                        "PQ567"
                );

        System.out.println(
                "Resultado búsqueda binaria PQ567:"
        );

        System.out.println(
                encontrado
        );


        // =====================================================
        // 7. DIVIDE Y VENCERÁS
        // =====================================================

        System.out.println(
                "\n=== DIVIDE Y VENCERÁS ==="
        );

        Paquete masPesado =
                sistema.buscarPaqueteMasPesado(
                        sistema.obtenerPaquetesRegistro()
                );

        System.out.println(
                "Paquete de mayor peso:"
        );

        System.out.println(
                masPesado
        );


        // =====================================================
        // 8. ORDENAMIENTOS
        // =====================================================

        System.out.println(
                "\n=== ORDENAMIENTOS ==="
        );


        // -----------------------------------------------------
        // 8.1 PRIORIDAD DESCENDENTE
        // -----------------------------------------------------

        List<Paquete> porPrioridad =
                sistema.ordenarPorPrioridad(
                        sistema.obtenerPaquetesRegistro()
                );

        System.out.println(
                "\nOrden por prioridad "
                        + "(mayor a menor):"
        );

        for (int i = 0;
             i < Math.min(5, porPrioridad.size());
             i++) {

            Paquete paquete =
                    porPrioridad.get(i);

            System.out.println(
                    paquete.getCodigo()
                            + " | Prioridad: "
                            + paquete.getPrioridad()
            );
        }


        // -----------------------------------------------------
        // 8.2 PESO DESCENDENTE
        // -----------------------------------------------------

        List<Paquete> porPeso =
                sistema.ordenarPorPeso(
                        sistema.obtenerPaquetesRegistro()
                );

        System.out.println(
                "\nOrden por peso "
                        + "(mayor a menor):"
        );

        for (int i = 0;
             i < Math.min(5, porPeso.size());
             i++) {

            Paquete paquete =
                    porPeso.get(i);

            System.out.println(
                    paquete.getCodigo()
                            + " | Peso: "
                            + paquete.getPeso()
                            + " kg"
            );
        }


        // -----------------------------------------------------
        // 8.3 TIEMPO ASCENDENTE
        // -----------------------------------------------------

        List<Paquete> porTiempo =
                sistema.ordenarPorTiempo(
                        sistema.obtenerPaquetesRegistro()
                );

        System.out.println(
                "\nOrden por tiempo estimado "
                        + "(menor a mayor):"
        );

        for (int i = 0;
             i < Math.min(5, porTiempo.size());
             i++) {

            Paquete paquete =
                    porTiempo.get(i);

            System.out.println(
                    paquete.getCodigo()
                            + " | Tiempo: "
                            + paquete.getTiempoEstimado()
                            + " minutos"
            );
        }


        // -----------------------------------------------------
        // 8.4 ORDEN NATURAL POR CÓDIGO
        // -----------------------------------------------------

        List<Paquete> porCodigo =
                sistema.ordenarPorCodigo(
                        sistema.obtenerPaquetesRegistro()
                );

        System.out.println(
                "\nOrden natural por código "
                        + "(ascendente):"
        );

        for (int i = 0;
             i < Math.min(5, porCodigo.size());
             i++) {

            Paquete paquete =
                    porCodigo.get(i);

            System.out.println(
                    paquete.getCodigo()
            );
        }


        // =====================================================
        // 9. DEMOSTRACIÓN FIFO
        // =====================================================

        System.out.println(
                "\n=== ATENCIÓN FIFO ==="
        );

        Paquete siguienteFIFO =
                sistema.verSiguienteFIFO();

        System.out.println(
                "Siguiente paquete por orden de llegada:"
        );

        System.out.println(
                siguienteFIFO
        );


        Paquete paqueteFIFO =
                sistema.atenderSiguienteFIFO();


        if (paqueteFIFO != null) {

            Repartidor repartidorFIFO =
                    sistema.obtenerRepartidorAsignado(
                            paqueteFIFO.getCodigo()
                    );


            System.out.println(
                    "Paquete despachado por FIFO: "
                            + paqueteFIFO.getCodigo()
            );


            System.out.println(
                    "Repartidor asignado: "
                            + repartidorFIFO.getNombre()
            );


            boolean entregaFIFO =
                    sistema.finalizarEntrega(
                            paqueteFIFO
                    );


            System.out.println(
                    "Entrega FIFO finalizada: "
                            + entregaFIFO
            );

        } else {

            System.out.println(
                    "No fue posible despachar "
                            + "el siguiente paquete FIFO."
            );
        }


        // =====================================================
        // 10. DEMOSTRACIÓN PRIORITYQUEUE
        // =====================================================

        System.out.println(
                "\n=== DESPACHO POR PRIORIDAD ==="
        );


        Paquete siguientePrioridad =
                sistema.verSiguientePrioridad();


        System.out.println(
                "Siguiente paquete según prioridad:"
        );

        System.out.println(
                siguientePrioridad
        );


        Paquete paquetePrioridad =
                sistema.despacharSiguientePrioridad();


        if (paquetePrioridad != null) {

            Repartidor repartidorPrioridad =
                    sistema.obtenerRepartidorAsignado(
                            paquetePrioridad.getCodigo()
                    );


            System.out.println(
                    "Paquete despachado por prioridad: "
                            + paquetePrioridad.getCodigo()
            );


            System.out.println(
                    "Repartidor asignado: "
                            + repartidorPrioridad.getNombre()
            );


            boolean entregaPrioridad =
                    sistema.finalizarEntrega(
                            paquetePrioridad
                    );


            System.out.println(
                    "Entrega por prioridad finalizada: "
                            + entregaPrioridad
            );

        } else {

            System.out.println(
                    "No fue posible despachar "
                            + "el paquete prioritario."
            );
        }


        // =====================================================
        // 11. HISTORIAL DE ENTREGAS
        // =====================================================

        System.out.println(
                "\n=== HISTORIAL DE ENTREGAS ==="
        );


        System.out.println(
                "Cantidad de entregas registradas: "
                        + sistema
                        .obtenerHistorialEntregas()
                        .tamano()
        );


        for (Paquete paquete :
                sistema.obtenerHistorialEntregas()) {

            System.out.println(
                    "Entregado -> "
                            + paquete.getCodigo()
                            + " - "
                            + paquete.getDestino()
            );
        }


        // =====================================================
        // 12. ESTADO FINAL
        // =====================================================

        System.out.println(
                "\n=== ESTADO FINAL ==="
        );


        System.out.println(
                "Paquetes registrados originalmente: "
                        + sistema
                        .obtenerPaquetesRegistro()
                        .size()
        );


        System.out.println(
                "Paquetes pendientes después "
                        + "de las entregas: "
                        + sistema
                        .obtenerPaquetesPendientes()
                        .size()
        );


        System.out.println(
                "\n========================================"
        );

        System.out.println(
                "   DEMOSTRACIÓN FINALIZADA"
        );

        System.out.println(
                "========================================"
        );
    }
}