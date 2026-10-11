package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.estructuras.ListaSimple;

public class PruebaListaSimple {

    private static int pruebasCorrectas = 0;

    public static void main(String[] args) {

        System.out.println(
                "============================================="
        );

        System.out.println(
                "        PRUEBAS LISTA SIMPLE"
        );

        System.out.println(
                "============================================="
        );


        pruebaListaVacia();

        pruebaUnElemento();

        pruebaEliminarPrimero();

        pruebaEliminarMedio();

        pruebaEliminarUltimo();

        pruebaEliminarInexistente();

        pruebaObtener();

        pruebaIterator();

        pruebaPosicionInvalida();


        System.out.println(
                "\n============================================="
        );

        System.out.println(
                "TODAS LAS PRUEBAS DE LISTA FUERON CORRECTAS"
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
    // LISTA VACÍA
    // =========================================================

    private static void pruebaListaVacia() {

        ListaSimple<String> lista =
                new ListaSimple<>();


        verificar(
                lista.estaVacia(),
                "Lista nueva está vacía"
        );


        verificar(
                lista.tamano() == 0,
                "Lista vacía tiene tamaño 0"
        );


        verificar(
                !lista.eliminar("A"),
                "Eliminar en lista vacía devuelve false"
        );
    }


    // =========================================================
    // UN SOLO ELEMENTO
    // =========================================================

    private static void pruebaUnElemento() {

        ListaSimple<String> lista =
                new ListaSimple<>();


        lista.agregar(
                "A"
        );


        verificar(
                !lista.estaVacia(),
                "Lista con un elemento no está vacía"
        );


        verificar(
                lista.tamano() == 1,
                "Lista con un elemento tiene tamaño 1"
        );


        verificar(
                lista.obtener(0).equals("A"),
                "Obtener único elemento"
        );


        verificar(
                lista.eliminar("A"),
                "Eliminar único elemento"
        );


        verificar(
                lista.estaVacia(),
                "Lista vuelve a quedar vacía"
        );
    }


    // =========================================================
    // ELIMINAR PRIMERO
    // =========================================================

    private static void pruebaEliminarPrimero() {

        ListaSimple<String> lista =
                crearListaCincoElementos();


        verificar(
                lista.eliminar("A"),
                "Eliminar primer elemento"
        );


        verificar(
                lista.tamano() == 4,
                "Tamaño correcto después de eliminar primero"
        );


        verificar(
                lista.obtener(0).equals("B"),
                "Segundo elemento pasa a ser primero"
        );
    }


    // =========================================================
    // ELIMINAR DEL MEDIO
    // =========================================================

    private static void pruebaEliminarMedio() {

        ListaSimple<String> lista =
                crearListaCincoElementos();


        verificar(
                lista.eliminar("C"),
                "Eliminar elemento del medio"
        );


        verificar(
                lista.tamano() == 4,
                "Tamaño correcto después de eliminar elemento medio"
        );


        verificar(
                lista.obtener(0).equals("A")
                        && lista.obtener(1).equals("B")
                        && lista.obtener(2).equals("D")
                        && lista.obtener(3).equals("E"),
                "Enlaces correctos después de eliminar elemento medio"
        );
    }


    // =========================================================
    // ELIMINAR ÚLTIMO
    // =========================================================

    private static void pruebaEliminarUltimo() {

        ListaSimple<String> lista =
                crearListaCincoElementos();


        verificar(
                lista.eliminar("E"),
                "Eliminar último elemento"
        );


        verificar(
                lista.tamano() == 4,
                "Tamaño correcto después de eliminar último"
        );


        verificar(
                lista.obtener(3).equals("D"),
                "Penúltimo elemento queda como último"
        );


        /*
         * Agregar después de eliminar el último también
         * permite comprobar que la referencia 'ultimo'
         * quedó correctamente actualizada.
         */
        lista.agregar(
                "F"
        );


        verificar(
                lista.obtener(4).equals("F"),
                "Se puede agregar después de eliminar el último"
        );
    }


    // =========================================================
    // ELEMENTO INEXISTENTE
    // =========================================================

    private static void pruebaEliminarInexistente() {

        ListaSimple<String> lista =
                crearListaCincoElementos();


        int tamanoAnterior =
                lista.tamano();


        verificar(
                !lista.eliminar("Z"),
                "Eliminar elemento inexistente devuelve false"
        );


        verificar(
                lista.tamano() == tamanoAnterior,
                "Eliminar inexistente no modifica tamaño"
        );
    }


    // =========================================================
    // OBTENER
    // =========================================================

    private static void pruebaObtener() {

        ListaSimple<String> lista =
                crearListaCincoElementos();


        verificar(
                lista.obtener(0).equals("A"),
                "Obtener primera posición"
        );


        verificar(
                lista.obtener(2).equals("C"),
                "Obtener posición intermedia"
        );


        verificar(
                lista.obtener(4).equals("E"),
                "Obtener última posición"
        );
    }


    // =========================================================
    // ITERATOR
    // =========================================================

    private static void pruebaIterator() {

        ListaSimple<String> lista =
                crearListaCincoElementos();


        String resultado =
                "";


        for (String elemento :
                lista) {

            resultado += elemento;
        }


        verificar(
                resultado.equals("ABCDE"),
                "Iterator recorre en el orden correcto"
        );
    }


    // =========================================================
    // POSICIONES INVÁLIDAS
    // =========================================================

    private static void pruebaPosicionInvalida() {

        ListaSimple<String> lista =
                crearListaCincoElementos();


        boolean excepcionNegativa =
                false;


        try {

            lista.obtener(
                    -1
            );

        } catch (IndexOutOfBoundsException e) {

            excepcionNegativa =
                    true;
        }


        verificar(
                excepcionNegativa,
                "Posición negativa genera excepción"
        );


        boolean excepcionMayor =
                false;


        try {

            lista.obtener(
                    5
            );

        } catch (IndexOutOfBoundsException e) {

            excepcionMayor =
                    true;
        }


        verificar(
                excepcionMayor,
                "Posición fuera del tamaño genera excepción"
        );
    }


    // =========================================================
    // AUXILIARES
    // =========================================================

    private static ListaSimple<String>
    crearListaCincoElementos() {

        ListaSimple<String> lista =
                new ListaSimple<>();


        lista.agregar("A");

        lista.agregar("B");

        lista.agregar("C");

        lista.agregar("D");

        lista.agregar("E");


        return lista;
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