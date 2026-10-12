# Parcial I - Estructuras de Datos

## QuindíoExpress

Sistema avanzado para la gestión de operaciones de un centro de distribución y despacho de paquetes en el departamento del Quindío.

### Integrantes

- Adrián Fernando Pérez Naranjo
- Federico Cruz
- Johan Steven Alvarez

### Tecnologías

- Java 21
- Maven
- IntelliJ IDEA

---

## Arquitectura y Organización del Código

El proyecto está estructurado bajo principios de arquitectura limpia y separación de responsabilidades, dividiéndose en los siguientes paquetes principales dentro de `co.edu.uniquindio.poo`:

1. **`QuindioExpress` (Clase Fachada)**:
    - Núcleo centralizador del sistema. Se encarga de sincronizar y coordinar las operaciones entre el registro, las colas de atención y los repartidores, aplicando validaciones defensivas para asegurar que ningún paquete sea retirado de las estructuras ni declarado como entregado sin un repartidor asignado en su zona geográfica.

2. **`model`**: Contiene las entidades del dominio con sus respectivos atributos, constructores y métodos de comparación.
    - `Paquete`: Implementa `Comparable<Paquete>` para el ordenamiento natural por código ascendente. Incluye soporte para prioridades (0 a 5, con valor por defecto de 5).
    - `Repartidor`: Gestiona la identificación única, nombre, zona y disponibilidad del personal de entrega.

3. **`estructuras`**: Implementación de estructuras de datos propias (cumpliendo la restricción académica de no usar el framework de colecciones para listas enlazadas).
    - `Coleccion<T>`: Interfaz genérica base.
    - `ListaSimple<T>`: Lista simplemente enlazada genérica con referencia al último nodo, garantizando inserciones en tiempo $O(1)$.
    - `ListaSimpleIterator<T>`: Iterador personalizado para recorrer la lista mediante bucles for-each.
    - `Nodo<T>`: Unidad básica de enlace con referencias al valor y al nodo siguiente.

4. **`comparadores`**: Criterios de ordenamiento alternativos mediante la interfaz `Comparator<Paquete>`.
    - `ComparadorDespacho`: Criterio multicriterio con tres niveles de desempate (prioridad descendente, tiempo estimado ascendente y código ascendente).
    - `ComparadorPeso`: Ordena por peso de mayor a menor.
    - `ComparadorPrioridad`: Ordena por prioridad de mayor a menor.
    - `ComparadorTiempo`: Ordena por tiempo estimado de menor a menor.

5. **`service`**: Capa de lógica de negocio y gestión algorítmica.
    - `GestorRegistro`: Administra los registros globales, mapas de búsqueda rápida por código (`HashMap`), conjuntos de municipios únicos y ordenados (`TreeSet`), y la asignación de repartidores por zona geográfica.
    - `GestorAtencion`: Controla la cola de atención FIFO (`ArrayDeque`) y el historial de entregas.
    - `GestorPrioridad`: Maneja la cola de prioridad (`PriorityQueue`), algoritmos de recursividad lineal (cálculo de peso por destino y conteo por prioridad mínima), búsqueda binaria recursiva y el algoritmo de Divide y Vencerás para encontrar el paquete más pesado.

6. **`data`**:
    - `DatosIniciales`: Carga automática de los 30 paquetes iniciales distribuidos en los municipios del Quindío y los 6 repartidores activos.

7. **`Main`**:
    - Clase de ejecución principal que contiene la demostración automatizada integral de todas las operaciones, validaciones, algoritmos y estructuras del sistema.

### Ramas de trabajo

- `feature/federico-registro`
- `feature/johan-estructuras`
- `feature/adrian-algoritmos`

---

## Ejecución del Sistema

El sistema cuenta con una demostración automatizada integral en la clase `Main.java` que ejecuta y valida todas las operaciones, estructuras, búsquedas y algoritmos sin necesidad de menús interactivos por consola.

Para compilar y ejecutar el proyecto con Maven:

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="co.edu.uniquindio.poo.Main"
```

# Pruebas Automatizadas
El proyecto incluye suites de pruebas unitarias y de integración ubicadas en el directorio src/test/java/co/edu/uniquindio/poo, las cuales validan el correcto funcionamiento de las estructuras propias, la cola de prioridad y la lógica general del sistema.