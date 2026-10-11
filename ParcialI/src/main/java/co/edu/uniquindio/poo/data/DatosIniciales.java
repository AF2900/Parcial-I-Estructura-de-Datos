package co.edu.uniquindio.poo.data;

import co.edu.uniquindio.poo.QuindioExpress;
import co.edu.uniquindio.poo.model.Paquete;
import co.edu.uniquindio.poo.model.Repartidor;

public class DatosIniciales {

    public static void cargarDatos(QuindioExpress sistema) {
        if (sistema == null) {
            return;
        }

        cargarPaquetes(sistema);
        cargarRepartidores(sistema);
    }

    private static void cargarPaquetes(QuindioExpress sistema) {
        sistema.registrarPaquete(new Paquete("PQ731", "Armenia", 4.5, 5, 45));
        sistema.registrarPaquete(new Paquete("PQ105", "Salento", 3.2, 5, 25));
        sistema.registrarPaquete(new Paquete("PQ942", "Calarca", 6.7, 4, 35));
        sistema.registrarPaquete(new Paquete("PQ420", "Armenia", 2.0, 3, 30));
        sistema.registrarPaquete(new Paquete("PQ318", "Montenegro", 8.1, 2, 50));
        sistema.registrarPaquete(new Paquete("PQ567", "Salento", 5.4, 4, 40));
        sistema.registrarPaquete(new Paquete("PQ829", "Armenia", 3.7, 1, 20));
        sistema.registrarPaquete(new Paquete("PQ214", "Quimbaya", 7.3, 5, 55));
        sistema.registrarPaquete(new Paquete("PQ650", "Calarca", 1.8, 2, 18));
        sistema.registrarPaquete(new Paquete("PQ490", "Montenegro", 9.5, 4, 60));
        sistema.registrarPaquete(new Paquete("PQ112", "Armenia", 2.4, 3, 22));
        sistema.registrarPaquete(new Paquete("PQ845", "Quimbaya", 4.9, 5, 48));
        sistema.registrarPaquete(new Paquete("PQ376", "Salento", 6.2, 2, 33));
        sistema.registrarPaquete(new Paquete("PQ901", "Calarca", 10.1, 4, 52));
        sistema.registrarPaquete(new Paquete("PQ255", "Armenia", 3.6, 1, 27));
        sistema.registrarPaquete(new Paquete("PQ688", "Montenegro", 5.8, 3, 44));
        sistema.registrarPaquete(new Paquete("PQ143", "Quimbaya", 7.9, 5, 39));
        sistema.registrarPaquete(new Paquete("PQ534", "Salento", 2.9, 2, 29));
        sistema.registrarPaquete(new Paquete("PQ777", "Calarca", 11.2, 4, 65));
        sistema.registrarPaquete(new Paquete("PQ329", "Armenia", 4.1, 3, 31));
        sistema.registrarPaquete(new Paquete("PQ615", "Montenegro", 6.6, 5, 46));
        sistema.registrarPaquete(new Paquete("PQ188", "Quimbaya", 3.3, 1, 21));
        sistema.registrarPaquete(new Paquete("PQ458", "Salento", 5.0, 4, 36));
        sistema.registrarPaquete(new Paquete("PQ990", "Calarca", 8.7, 2, 58));
        sistema.registrarPaquete(new Paquete("PQ271", "Armenia", 2.6, 5, 24));
        sistema.registrarPaquete(new Paquete("PQ703", "Montenegro", 9.0, 3, 49));
        sistema.registrarPaquete(new Paquete("PQ156", "Quimbaya", 4.4, 2, 32));
        sistema.registrarPaquete(new Paquete("PQ521", "Salento", 6.9, 4, 41));
        sistema.registrarPaquete(new Paquete("PQ834", "Calarca", 7.6, 5, 54));
        sistema.registrarPaquete(new Paquete("PQ397", "Armenia", 3.1, 0, 26));
    }

    private static void cargarRepartidores(QuindioExpress sistema) {
        sistema.registrarRepartidor(new Repartidor("R001", "Carlos Gomez", "Armenia", true));
        sistema.registrarRepartidor(new Repartidor("R002", "Laura Martinez", "Salento", true));
        sistema.registrarRepartidor(new Repartidor("R003", "Andres Ramirez", "Calarca", true));
        sistema.registrarRepartidor(new Repartidor("R004", "Sofia Torres", "Montenegro", true));
        sistema.registrarRepartidor(new Repartidor("R005", "Daniel Lopez", "Quimbaya", true));
        sistema.registrarRepartidor(new Repartidor("R006", "Valentina Ruiz", "Armenia", true));
    }
}