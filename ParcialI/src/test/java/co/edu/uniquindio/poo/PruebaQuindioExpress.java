package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.data.DatosIniciales;
import co.edu.uniquindio.poo.model.Paquete;
import co.edu.uniquindio.poo.model.Repartidor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PruebaQuindioExpress {

    private static int pruebasCorrectas = 0;

    public static void main(String[] args) {

        System.out.println(
                "============================================="
        );

        System.out.println(
                "      PRUEBAS DE QUINDÍO EXPRESS"
        );

        System.out.println(
                "============================================="
        );


        pruebaCargaInicial();

        pruebaMunicipios();

        pruebaBusquedaRapida();

        pruebaCodigoPaqueteDuplicado();

        pruebaValidacionesPaquete();

        pruebaPrioridadPorDefecto();

        pruebaRepartidorDuplicado();

        pruebaFIFO();

        pruebaSinRepartidorNoPierdePaquete();

        pruebaEntregaSinAsignacion();

        pruebaDespachoPorPrioridad();

        pruebaEntregaCompleta();

        pruebaNoEntregarDosVeces();

        pruebaHistorialOrdenExacto();

        pruebaAlgoritmosConDatosIniciales();

        pruebaOrdenamientos();


        System.out.println(
                "\n============================================="
        );

        System.out.println(
                "TODAS LAS PRUEBAS TERMINARON CORRECTAMENTE"
        );

        System.out.println(
                "Pruebas superadas: "
                        + pruebasCorrectas
        );

        System.out.println(
                "============================================="
        );
    }


    // =========================================================
    // 1. DATOS INICIALES
    // =========================================================

    private static void pruebaCargaInicial() {

        QuindioExpress sistema =
                crearSistemaConDatosIniciales();

        verificar(
                sistema.obtenerPaquetesRegistro().size() == 30,
                "Carga de 30 paquetes"
        );

        verificar(
                sistema.obtenerPaquetesPendientes().size() == 30,
                "Los 30 paquetes comienzan pendientes"
        );

        verificar(
                sistema.obtenerRepartidores().size() == 6,
                "Carga de 6 repartidores"
        );
    }


    // =========================================================
    // 2. MUNICIPIOS
    // =========================================================

    private static void pruebaMunicipios() {

        QuindioExpress sistema =
                crearSistemaConDatosIniciales();

        verificar(
                sistema.obtenerMunicipios().size() == 5,
                "Municipios sin repetir"
        );

        List<String> municipiosOrdenados =
                new ArrayList<>(
                        sistema.obtenerMunicipiosOrdenados()
                );

        List<String> esperado =
                Arrays.asList(
                        "Armenia",
                        "Calarca",
                        "Montenegro",
                        "Quimbaya",
                        "Salento"
                );

        verificar(
                municipiosOrdenados.equals(esperado),
                "Municipios ordenados alfabéticamente"
        );
    }


    // =========================================================
    // 3. BÚSQUEDA POR HASHMAP
    // =========================================================

    private static void pruebaBusquedaRapida() {

        QuindioExpress sistema =
                crearSistemaConDatosIniciales();

        Paquete encontrado =
                sistema.buscarPaquete(
                        "PQ731"
                );

        verificar(
                encontrado != null
                        && encontrado
                        .getCodigo()
                        .equals("PQ731"),
                "Búsqueda de paquete existente"
        );

        verificar(
                sistema.buscarPaquete(
                        "PQ999"
                ) == null,
                "Búsqueda de paquete inexistente"
        );
    }


    // =========================================================
    // 4. CÓDIGO ÚNICO
    // =========================================================

    private static void pruebaCodigoPaqueteDuplicado() {

        QuindioExpress sistema =
                new QuindioExpress();

        Paquete paquete1 =
                new Paquete(
                        "PQ100",
                        "Armenia",
                        2.0,
                        3,
                        20
                );

        Paquete paqueteDuplicado =
                new Paquete(
                        "PQ100",
                        "Salento",
                        8.0,
                        5,
                        40
                );

        verificar(
                sistema.registrarPaquete(
                        paquete1
                ),
                "Registro del primer código"
        );

        verificar(
                !sistema.registrarPaquete(
                        paqueteDuplicado
                ),
                "Rechazo de código duplicado"
        );

        verificar(
                sistema.obtenerPaquetesRegistro().size() == 1,
                "Código duplicado no altera el registro"
        );
    }


    // =========================================================
    // 5. VALIDACIONES DE PAQUETE
    // =========================================================

    private static void pruebaValidacionesPaquete() {

        QuindioExpress sistema =
                new QuindioExpress();

        Paquete codigoVacio =
                new Paquete(
                        "",
                        "Armenia",
                        2.0,
                        3,
                        20
                );

        Paquete destinoVacio =
                new Paquete(
                        "PQ101",
                        "",
                        2.0,
                        3,
                        20
                );

        Paquete pesoInvalido =
                new Paquete(
                        "PQ102",
                        "Armenia",
                        0,
                        3,
                        20
                );

        Paquete prioridadMenor =
                new Paquete(
                        "PQ103",
                        "Armenia",
                        2.0,
                        -1,
                        20
                );

        Paquete prioridadMayor =
                new Paquete(
                        "PQ104",
                        "Armenia",
                        2.0,
                        6,
                        20
                );

        Paquete tiempoInvalido =
                new Paquete(
                        "PQ105",
                        "Armenia",
                        2.0,
                        3,
                        0
                );


        verificar(
                !sistema.registrarPaquete(
                        codigoVacio
                ),
                "Rechazo de código vacío"
        );

        verificar(
                !sistema.registrarPaquete(
                        destinoVacio
                ),
                "Rechazo de destino vacío"
        );

        verificar(
                !sistema.registrarPaquete(
                        pesoInvalido
                ),
                "Rechazo de peso no positivo"
        );

        verificar(
                !sistema.registrarPaquete(
                        prioridadMenor
                ),
                "Rechazo de prioridad menor que 0"
        );

        verificar(
                !sistema.registrarPaquete(
                        prioridadMayor
                ),
                "Rechazo de prioridad mayor que 5"
        );

        verificar(
                !sistema.registrarPaquete(
                        tiempoInvalido
                ),
                "Rechazo de tiempo no positivo"
        );

        verificar(
                sistema.obtenerPaquetesRegistro().isEmpty(),
                "Paquetes inválidos no entran al sistema"
        );
    }


    // =========================================================
    // 6. PRIORIDAD POR DEFECTO
    // =========================================================

    private static void pruebaPrioridadPorDefecto() {

        Paquete paquete =
                new Paquete(
                        "PQ200",
                        "Armenia",
                        3.0,
                        25
                );

        verificar(
                paquete.getPrioridad() == 5,
                "Prioridad por defecto igual a 5"
        );
    }


    // =========================================================
    // 7. REPARTIDOR ÚNICO
    // =========================================================

    private static void pruebaRepartidorDuplicado() {

        QuindioExpress sistema =
                new QuindioExpress();

        Repartidor repartidor1 =
                new Repartidor(
                        "R001",
                        "Carlos",
                        "Armenia",
                        true
                );

        Repartidor repartidor2 =
                new Repartidor(
                        "R001",
                        "Andrea",
                        "Salento",
                        true
                );


        verificar(
                sistema.registrarRepartidor(
                        repartidor1
                ),
                "Registro de repartidor válido"
        );

        verificar(
                !sistema.registrarRepartidor(
                        repartidor2
                ),
                "Rechazo de identificación de repartidor duplicada"
        );

        verificar(
                sistema.obtenerRepartidores().size() == 1,
                "Repartidor duplicado no altera el registro"
        );
    }


    // =========================================================
    // 8. FIFO
    // =========================================================

    private static void pruebaFIFO() {

        QuindioExpress sistema =
                new QuindioExpress();

        Paquete primero =
                new Paquete(
                        "PQ300",
                        "Armenia",
                        2.0,
                        1,
                        20
                );

        Paquete segundo =
                new Paquete(
                        "PQ301",
                        "Armenia",
                        3.0,
                        5,
                        10
                );

        Repartidor repartidor =
                new Repartidor(
                        "R300",
                        "Laura",
                        "Armenia",
                        true
                );


        sistema.registrarPaquete(
                primero
        );

        sistema.registrarPaquete(
                segundo
        );

        sistema.registrarRepartidor(
                repartidor
        );


        verificar(
                sistema.verSiguienteFIFO() == primero,
                "FIFO conserva el orden de llegada"
        );


        Paquete atendido =
                sistema.atenderSiguienteFIFO();


        verificar(
                atendido == primero,
                "FIFO atiende el primer paquete registrado"
        );


        verificar(
                sistema.tieneRepartidorAsignado(
                        primero.getCodigo()
                ),
                "Paquete FIFO queda asignado a un repartidor"
        );


        verificar(
                sistema.estaPendiente(
                        primero.getCodigo()
                ),
                "Paquete despachado sigue pendiente hasta entregarse"
        );


        verificar(
                sistema.verSiguienteFIFO() == segundo,
                "FIFO avanza al siguiente paquete"
        );


        verificar(
                !contieneCodigo(
                        sistema.obtenerOrdenDespachoPrioritario(),
                        primero.getCodigo()
                ),
                "Paquete FIFO también se retira de PriorityQueue"
        );
    }


    // =========================================================
    // 9. SIN REPARTIDOR
    // =========================================================

    private static void pruebaSinRepartidorNoPierdePaquete() {

        QuindioExpress sistema =
                new QuindioExpress();

        Paquete paquete =
                new Paquete(
                        "PQ400",
                        "Armenia",
                        4.0,
                        5,
                        20
                );

        sistema.registrarPaquete(
                paquete
        );


        Paquete atendido =
                sistema.atenderSiguienteFIFO();


        verificar(
                atendido == null,
                "No se despacha si no hay repartidor"
        );


        verificar(
                sistema.verSiguienteFIFO() == paquete,
                "Sin repartidor el paquete permanece en FIFO"
        );


        verificar(
                sistema.verSiguientePrioridad() == paquete,
                "Sin repartidor el paquete permanece en PriorityQueue"
        );


        verificar(
                sistema.estaPendiente(
                        paquete.getCodigo()
                ),
                "Sin repartidor el paquete continúa pendiente"
        );
    }


    // =========================================================
    // 10. NO ENTREGAR SIN ASIGNACIÓN
    // =========================================================

    private static void pruebaEntregaSinAsignacion() {

        QuindioExpress sistema =
                new QuindioExpress();

        Paquete paquete =
                new Paquete(
                        "PQ500",
                        "Armenia",
                        2.5,
                        4,
                        30
                );

        sistema.registrarPaquete(
                paquete
        );


        boolean entrega =
                sistema.finalizarEntrega(
                        paquete
                );


        verificar(
                !entrega,
                "No se permite entregar sin repartidor asignado"
        );


        verificar(
                sistema.estaPendiente(
                        paquete.getCodigo()
                ),
                "Entrega inválida mantiene el paquete pendiente"
        );


        verificar(
                sistema
                        .obtenerHistorialEntregas()
                        .tamano() == 0,
                "Entrega inválida no entra al historial"
        );
    }


    // =========================================================
    // 11. PRIORITYQUEUE Y DESEMPATES
    // =========================================================

    private static void pruebaDespachoPorPrioridad() {

        QuindioExpress sistema =
                new QuindioExpress();

        Paquete prioridadBaja =
                new Paquete(
                        "PQ600",
                        "Armenia",
                        2.0,
                        3,
                        5
                );

        Paquete prioridadAltaLento =
                new Paquete(
                        "PQ700",
                        "Armenia",
                        2.0,
                        5,
                        40
                );

        Paquete empateCodigoMayor =
                new Paquete(
                        "PQ300",
                        "Armenia",
                        2.0,
                        5,
                        20
                );

        Paquete empateCodigoMenor =
                new Paquete(
                        "PQ200",
                        "Armenia",
                        2.0,
                        5,
                        20
                );


        sistema.registrarPaquete(
                prioridadBaja
        );

        sistema.registrarPaquete(
                prioridadAltaLento
        );

        sistema.registrarPaquete(
                empateCodigoMayor
        );

        sistema.registrarPaquete(
                empateCodigoMenor
        );


        verificar(
                sistema
                        .verSiguientePrioridad()
                        .getCodigo()
                        .equals("PQ200"),
                "PriorityQueue aplica prioridad, tiempo y código"
        );


        Repartidor repartidor =
                new Repartidor(
                        "R600",
                        "Andrés",
                        "Armenia",
                        true
                );

        sistema.registrarRepartidor(
                repartidor
        );


        Paquete despachado =
                sistema.despacharSiguientePrioridad();


        verificar(
                despachado == empateCodigoMenor,
                "Despacho prioritario selecciona el paquete correcto"
        );


        verificar(
                sistema.verSiguienteFIFO() == prioridadBaja,
                "Despacho prioritario elimina el paquete de FIFO"
        );


        verificar(
                sistema.tieneRepartidorAsignado(
                        despachado.getCodigo()
                ),
                "Despacho prioritario asigna repartidor"
        );
    }


    // =========================================================
    // 12. ENTREGA COMPLETA
    // =========================================================

    private static void pruebaEntregaCompleta() {

        QuindioExpress sistema =
                new QuindioExpress();

        Paquete paquete =
                new Paquete(
                        "PQ800",
                        "Salento",
                        3.0,
                        5,
                        25
                );

        Repartidor repartidor =
                new Repartidor(
                        "R800",
                        "Sofía",
                        "Salento",
                        true
                );


        sistema.registrarPaquete(
                paquete
        );

        sistema.registrarRepartidor(
                repartidor
        );


        Repartidor asignado =
                sistema.asignarRepartidor(
                        paquete
                );


        verificar(
                asignado == repartidor,
                "Asignación encuentra repartidor de la zona"
        );


        verificar(
                !repartidor.isDisponible(),
                "Repartidor asignado queda no disponible"
        );


        verificar(
                sistema.estaPendiente(
                        paquete.getCodigo()
                ),
                "Paquete asignado aún está pendiente"
        );


        boolean entregado =
                sistema.finalizarEntrega(
                        paquete
                );


        verificar(
                entregado,
                "Entrega válida finaliza correctamente"
        );


        verificar(
                !sistema.estaPendiente(
                        paquete.getCodigo()
                ),
                "Paquete entregado sale de pendientes"
        );


        verificar(
                sistema
                        .obtenerHistorialEntregas()
                        .tamano() == 1,
                "Paquete entregado entra al historial"
        );


        verificar(
                sistema
                        .obtenerHistorialEntregas()
                        .obtener(0) == paquete,
                "Historial conserva el mismo objeto Paquete"
        );


        verificar(
                repartidor.isDisponible(),
                "Repartidor vuelve a estar disponible"
        );


        verificar(
                sistema
                        .obtenerPaquetesRegistro()
                        .size() == 1,
                "Registro original se conserva después de entregar"
        );
    }


    // =========================================================
    // 13. NO ENTREGAR DOS VECES
    // =========================================================

    private static void pruebaNoEntregarDosVeces() {

        QuindioExpress sistema =
                new QuindioExpress();

        Paquete paquete =
                new Paquete(
                        "PQ900",
                        "Calarca",
                        4.0,
                        4,
                        30
                );

        Repartidor repartidor =
                new Repartidor(
                        "R900",
                        "Daniel",
                        "Calarca",
                        true
                );


        sistema.registrarPaquete(
                paquete
        );

        sistema.registrarRepartidor(
                repartidor
        );

        sistema.asignarRepartidor(
                paquete
        );


        verificar(
                sistema.finalizarEntrega(
                        paquete
                ),
                "Primera entrega permitida"
        );


        verificar(
                !sistema.finalizarEntrega(
                        paquete
                ),
                "Segunda entrega del mismo paquete rechazada"
        );


        verificar(
                sistema
                        .obtenerHistorialEntregas()
                        .tamano() == 1,
                "Paquete no se duplica en el historial"
        );
    }


    // =========================================================
    // 14. ORDEN DEL HISTORIAL
    // =========================================================

    private static void pruebaHistorialOrdenExacto() {

        QuindioExpress sistema =
                new QuindioExpress();

        Paquete primero =
                new Paquete(
                        "PA001",
                        "Armenia",
                        2.0,
                        5,
                        20
                );

        Paquete segundo =
                new Paquete(
                        "PA002",
                        "Salento",
                        3.0,
                        4,
                        30
                );

        Repartidor repartidor1 =
                new Repartidor(
                        "RA001",
                        "Carlos",
                        "Armenia",
                        true
                );

        Repartidor repartidor2 =
                new Repartidor(
                        "RA002",
                        "Laura",
                        "Salento",
                        true
                );


        sistema.registrarPaquete(
                primero
        );

        sistema.registrarPaquete(
                segundo
        );

        sistema.registrarRepartidor(
                repartidor1
        );

        sistema.registrarRepartidor(
                repartidor2
        );


        sistema.asignarRepartidor(
                primero
        );

        sistema.finalizarEntrega(
                primero
        );


        sistema.asignarRepartidor(
                segundo
        );

        sistema.finalizarEntrega(
                segundo
        );


        verificar(
                sistema
                        .obtenerHistorialEntregas()
                        .tamano() == 2,
                "Historial contiene las dos entregas"
        );


        verificar(
                sistema
                        .obtenerHistorialEntregas()
                        .obtener(0) == primero,
                "Historial conserva primera entrega"
        );


        verificar(
                sistema
                        .obtenerHistorialEntregas()
                        .obtener(1) == segundo,
                "Historial conserva segunda entrega"
        );


        verificar(
                sistema
                        .obtenerPaquetesRegistro()
                        .size() == 2,
                "Registro original conserva ambos paquetes"
        );


        verificar(
                sistema
                        .obtenerPaquetesPendientes()
                        .isEmpty(),
                "No quedan pendientes después de ambas entregas"
        );
    }


    // =========================================================
    // 15. ALGORITMOS CON LOS 30 DATOS
    // =========================================================

    private static void pruebaAlgoritmosConDatosIniciales() {

        QuindioExpress sistema =
                crearSistemaConDatosIniciales();


        double pesoArmenia =
                sistema.calcularPesoPorDestino(
                        sistema.obtenerPaquetesRegistro(),
                        "Armenia"
                );


        verificar(
                Math.abs(
                        pesoArmenia - 26.0
                ) < 0.000001,
                "Recursividad calcula 26.0 kg para Armenia"
        );


        int prioridadMinima =
                sistema.contarPaquetesPorPrioridad(
                        sistema.obtenerPaquetesRegistro(),
                        4
                );


        verificar(
                prioridadMinima == 15,
                "Recursividad encuentra 15 paquetes con prioridad >= 4"
        );


        List<Paquete> ordenados =
                sistema.ordenarPorCodigo(
                        sistema.obtenerPaquetesRegistro()
                );


        Paquete encontrado =
                sistema.buscarPaquetePorCodigo(
                        ordenados,
                        "PQ567"
                );


        verificar(
                encontrado != null
                        && encontrado
                        .getCodigo()
                        .equals("PQ567"),
                "Búsqueda binaria encuentra PQ567"
        );


        Paquete masPesado =
                sistema.buscarPaqueteMasPesado(
                        sistema.obtenerPaquetesRegistro()
                );


        verificar(
                masPesado != null
                        && masPesado
                        .getCodigo()
                        .equals("PQ777"),
                "Divide y vencerás encuentra PQ777 como más pesado"
        );
    }


    // =========================================================
    // 16. ORDENAMIENTOS
    // =========================================================

    private static void pruebaOrdenamientos() {

        QuindioExpress sistema =
                crearSistemaConDatosIniciales();


        List<Paquete> registroOriginal =
                sistema.obtenerPaquetesRegistro();


        List<Paquete> porPrioridad =
                sistema.ordenarPorPrioridad(
                        registroOriginal
                );


        verificar(
                porPrioridad.get(0).getPrioridad() == 5,
                "Orden por prioridad comienza con prioridad 5"
        );


        verificar(
                porPrioridad
                        .get(porPrioridad.size() - 1)
                        .getPrioridad() == 0,
                "Orden por prioridad termina con prioridad 0"
        );


        List<Paquete> porPeso =
                sistema.ordenarPorPeso(
                        registroOriginal
                );


        verificar(
                porPeso
                        .get(0)
                        .getCodigo()
                        .equals("PQ777"),
                "Orden por peso comienza con PQ777"
        );


        List<Paquete> porTiempo =
                sistema.ordenarPorTiempo(
                        registroOriginal
                );


        verificar(
                porTiempo
                        .get(0)
                        .getCodigo()
                        .equals("PQ650"),
                "Orden por tiempo comienza con PQ650"
        );


        List<Paquete> porCodigo =
                sistema.ordenarPorCodigo(
                        registroOriginal
                );


        verificar(
                porCodigo
                        .get(0)
                        .getCodigo()
                        .equals("PQ105"),
                "Orden natural comienza con PQ105"
        );


        verificar(
                sistema
                        .obtenerPaquetesRegistro()
                        .get(0)
                        .getCodigo()
                        .equals("PQ731"),
                "Los ordenamientos no modifican el orden original"
        );
    }


    // =========================================================
    // MÉTODOS AUXILIARES DE LAS PRUEBAS
    // =========================================================

    private static QuindioExpress
    crearSistemaConDatosIniciales() {

        QuindioExpress sistema =
                new QuindioExpress();

        DatosIniciales.cargarDatos(
                sistema
        );

        return sistema;
    }


    private static boolean contieneCodigo(
            List<Paquete> paquetes,
            String codigo) {

        for (Paquete paquete :
                paquetes) {

            if (paquete
                    .getCodigo()
                    .equals(codigo)) {

                return true;
            }
        }

        return false;
    }


    private static void verificar(
            boolean condicion,
            String descripcion) {

        if (!condicion) {

            throw new AssertionError(
                    "PRUEBA FALLIDA: "
                            + descripcion
            );
        }

        pruebasCorrectas++;

        System.out.println(
                "OK - "
                        + descripcion
        );
    }
}