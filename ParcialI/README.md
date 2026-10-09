# Parcial I - Estructuras de Datos

## QuindíoExpress

Sistema para la gestión de operaciones de un centro de distribución.

### Integrantes

- Federico Cruz
- Johan Steven Alvarez
- Adrián Fernando Pérez Naranjo

### Tecnologías

- Java 21
- Maven
- IntelliJ IDEA

### Modelo base

#### Paquete

Atributos:

- codigo
- destino
- peso
- prioridad
- tiempoEstimado

El orden natural de los paquetes se realiza por código ascendente
mediante `Comparable<Paquete>`.

La prioridad utilizada en el proyecto está comprendida entre 0 y 5,
de acuerdo con la aclaración realizada por el docente.
La prioridad por defecto es 5.

#### Repartidor

Atributos:

- identificacion
- nombre
- zona
- disponible

### Organización del código

- `model`: clases del dominio.
- `service`: lógica de operación del sistema.
- `estructuras`: estructuras de datos propias.
- `comparadores`: criterios alternativos de ordenamiento.

### Ramas de trabajo

- `feature/federico-registro`
- `feature/johan-estructuras`
- `feature/adrian-algoritmos`