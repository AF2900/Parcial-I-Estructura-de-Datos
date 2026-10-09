package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.model.Paquete;
import co.edu.uniquindio.poo.model.Repartidor;

public class Main {

    public static void main(String[] args) {



        Paquete paquete1 = new Paquete(
                "PQ731",
                "Armenia",
                4.5,
                5,
                45
        );

        Paquete paquete2 = new Paquete(
                "PQ105",
                "Salento",
                3.2,
                4,
                25
        );

        Paquete paquete3 = new Paquete(
                "PQ942",
                "Calarca",
                6.7,
                35
        );

        System.out.println(paquete3);

        Repartidor repartidor1 = new Repartidor(
                "R001",
                "Carlos Gomez",
                "Armenia",
                true
        );

        System.out.println(paquete1);
        System.out.println(paquete2);
        System.out.println(repartidor1);

        System.out.println(
                "Comparacion por codigo: "
                        + paquete1.compareTo(paquete2)
        );
    }
}