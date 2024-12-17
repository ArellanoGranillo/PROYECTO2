package Proyecto2;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList; // lo agregue porque después de varios ciclos me arrojaba un error en las listas
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimuladorDeLaIsla {
    private static final int filas = 62;
    private static final int columnas = 22;
    private static final Ubicacion[][] isla = new Ubicacion[filas][columnas]; // Ubicaciones en la matriz de la isla
    private List<Animal> listaAnimales = new CopyOnWriteArrayList<>();// Lista para guardar animales
    private ExecutorService executor; // Pool de hilos
    private Estadistica estadistica;

    // Inicialización de la isla y animales
    public void iniciarIsla() {
        // Configurar el thread con 50 hilos
        executor = Executors.newFixedThreadPool(50);

        // Inicialización de estadísticas
        if (estadistica == null) { // Primera vez que se llama al metodo
            estadistica = new Estadistica(listaAnimales, new ArrayList<>(), new ArrayList<>());
        } else {
            estadistica.reiniciarCiclo(listaAnimales);
        }

        // Inicialización de la isla
        for (int j = 0; j < columnas; j++) {
            for (int i = 0; i < filas; i++) {
                if (isla[i][j] == null) {
                    isla[i][j] = new Ubicacion(); // Crear las celdas de la isla si no existen
                    isla[i][j].ubicacionActual = isla[i][j]; // Asegurarse de que ubicacionActual esté correctamente asignada
                }
            }
        }
        // Inicialización de animales (solo si es la primera vez)
        if (listaAnimales.isEmpty()) {
            inicializarAnimalesConCantidad(Oso.class, 20);
            inicializarAnimalesConCantidad(Lobo.class, 20);
            inicializarAnimalesConCantidad(Boa.class, 20);
            inicializarAnimalesConCantidad(Puerco.class, 20);
            inicializarAnimalesConCantidad(Zorro.class, 20);
            inicializarAnimalesConCantidad(Aguila.class, 20);
            inicializarAnimalesConCantidad(Caballo.class, 20);
            inicializarAnimalesConCantidad(Ciervo.class, 20);
            inicializarAnimalesConCantidad(Conejo.class, 20);
            inicializarAnimalesConCantidad(Raton.class, 20);
            inicializarAnimalesConCantidad(Cabra.class, 20);
            inicializarAnimalesConCantidad(Oveja.class, 20);
            inicializarAnimalesConCantidad(Bufalo.class, 20);
            inicializarAnimalesConCantidad(Pato.class, 20);
            inicializarAnimalesConCantidad(Oruga.class, 20);
        }
        // comportamientos en hilos
        for (Animal animal : listaAnimales) {
            executor.execute(() -> {
                // Mover al animal
                animal.mover();

                // Obtener ubicación destino
                Ubicacion ubicacionDestino = obtenerUbicacionAleatoria();

                //  sincronización para asegurar que solo un animal interactúe con la ubicación
                synchronized (animal.getUbicacionActual()) {
                    animal.comer();
                }
                //  sincronizado para registrar correctamente los eventos con los animales
                synchronized (this) {
                    if (animal.verificarMuerte()) {
                        estadistica.registrarMuerte(animal);
                    } else {
                        Animal nuevoAnimal = animal.reproducirse();
                        if (nuevoAnimal != null) {
                            // sincronizando la modificación de la lista
                            synchronized (listaAnimales) {
                                listaAnimales.add(nuevoAnimal);  // Modificación de la lista
                            }
                            estadistica.registrarNacimiento(nuevoAnimal);
                        }
                    }
                }
            });
        }

        // Esperar a que todas las tareas terminen o si no se completa en 75 segundos terminar
        executor.shutdown();
        try {
            if (!executor.awaitTermination(75, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        // Mostrar estadísticas  al final del ciclo
        estadistica.mostrarEstadisticas();
    }

    // Metodo para agregar una cantidad de animales de una especie a la lista
    private void inicializarAnimalesConCantidad(Class<? extends Animal> tipoAnimal, int cantidad) {
        try {
            for (int i = 0; i < cantidad; i++) {
                Ubicacion ubicacionAleatoria = obtenerUbicacionAleatoria(); // Obtener una ubicación aleatoria
                Animal animal = tipoAnimal.getConstructor(Ubicacion.class).newInstance(ubicacionAleatoria); // Pasar la ubicación al constructor
                listaAnimales.add(animal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Metodo para generar ubicación aleatoria
    public static Ubicacion obtenerUbicacionAleatoria() {
        int filaAleatoria = (int) (Math.random() * filas);
        int columnaAleatoria = (int) (Math.random() * columnas);
        return isla[filaAleatoria][columnaAleatoria];
    }

    public static int getFilas() {
        return filas;
    }

    public static int getColumnas() {
        return columnas;
    }

    public static Ubicacion obtenerUbicacion(int fila, int columna) {
        return isla[fila][columna];
    }
}
