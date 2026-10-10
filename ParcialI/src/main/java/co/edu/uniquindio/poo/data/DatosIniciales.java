package co.edu.uniquindio.poo.data;

import co.edu.uniquindio.poo.model.Paquete;
import co.edu.uniquindio.poo.model.Repartidor;
import co.edu.uniquindio.poo.service.GestorRegistro;

public class DatosIniciales {

    public static void cargarDatos(
            GestorRegistro gestor) {

        if (gestor == null) {
            return;
        }

        cargarPaquetes(gestor);
        cargarRepartidores(gestor);
    }


    private static void cargarPaquetes(
            GestorRegistro gestor) {

        gestor.registrarPaquete(
                new Paquete(
                        "PQ731", "Armenia",
                        4.5, 5, 45
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ105", "Salento",
                        3.2, 5, 25
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ942", "Calarca",
                        6.7, 4, 35
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ420", "Armenia",
                        2.0, 3, 30
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ318", "Montenegro",
                        8.1, 2, 50
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ567", "Salento",
                        5.4, 4, 40
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ829", "Armenia",
                        3.7, 1, 20
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ214", "Quimbaya",
                        7.3, 5, 55
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ650", "Calarca",
                        1.8, 2, 18
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ490", "Montenegro",
                        9.5, 4, 60
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ112", "Armenia",
                        2.4, 3, 22
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ845", "Quimbaya",
                        4.9, 5, 48
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ376", "Salento",
                        6.2, 2, 33
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ901", "Calarca",
                        10.1, 4, 52
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ255", "Armenia",
                        3.6, 1, 27
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ688", "Montenegro",
                        5.8, 3, 44
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ143", "Quimbaya",
                        7.9, 5, 39
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ534", "Salento",
                        2.9, 2, 29
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ777", "Calarca",
                        11.2, 4, 65
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ329", "Armenia",
                        4.1, 3, 31
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ615", "Montenegro",
                        6.6, 5, 46
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ188", "Quimbaya",
                        3.3, 1, 21
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ458", "Salento",
                        5.0, 4, 36
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ990", "Calarca",
                        8.7, 2, 58
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ271", "Armenia",
                        2.6, 5, 24
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ703", "Montenegro",
                        9.0, 3, 49
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ156", "Quimbaya",
                        4.4, 2, 32
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ521", "Salento",
                        6.9, 4, 41
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ834", "Calarca",
                        7.6, 5, 54
                )
        );

        gestor.registrarPaquete(
                new Paquete(
                        "PQ397", "Armenia",
                        3.1, 0, 26
                )
        );
    }


    private static void cargarRepartidores(
            GestorRegistro gestor) {

        gestor.registrarRepartidor(
                new Repartidor(
                        "R001",
                        "Carlos Gomez",
                        "Armenia",
                        true
                )
        );

        gestor.registrarRepartidor(
                new Repartidor(
                        "R002",
                        "Laura Martinez",
                        "Salento",
                        true
                )
        );

        gestor.registrarRepartidor(
                new Repartidor(
                        "R003",
                        "Andres Ramirez",
                        "Calarca",
                        true
                )
        );

        gestor.registrarRepartidor(
                new Repartidor(
                        "R004",
                        "Sofia Torres",
                        "Montenegro",
                        true
                )
        );

        gestor.registrarRepartidor(
                new Repartidor(
                        "R005",
                        "Daniel Lopez",
                        "Quimbaya",
                        true
                )
        );

        gestor.registrarRepartidor(
                new Repartidor(
                        "R006",
                        "Valentina Ruiz",
                        "Armenia",
                        true
                )
        );
    }
}