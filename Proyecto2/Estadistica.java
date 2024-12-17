package Proyecto2;
import java.util.HashMap;

import java.util.List;

import java.util.Map;

// clase para manejar e imprimir las estadísticas totales al final de cada simulación
public class Estadistica {

    // Listas que contienen los animales vivos, muertos y nacidos durante la simulación
    private List<Animal> animalesVivos;
    private List<Animal> animalesMuertos;
    private List<Animal> animalesNacidos;

    private int totalMuertos = 0;
    private int totalAlimentados = 0;
    private int totalNacidos = 0;

    //listas de animales vivos (en un principio), muertos y nacidos (durante el ciclo)

    public Estadistica(List<Animal> animalesVivos, List<Animal> animalesMuertos, List<Animal> animalesNacidos) {
        this.animalesVivos = animalesVivos;
        this.animalesMuertos = animalesMuertos;
        this.animalesNacidos = animalesNacidos;
    }
    // se inicializa una lista de animales
    public void inicializarAnimales(List<Animal> listaAnimales, Class<? extends Animal> tipoAnimal, int cantidad) {
        try {
            for (int i = 0; i < cantidad; i++) {
                Animal animal = tipoAnimal.getConstructor().newInstance();
                listaAnimales.add(animal);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al inicializar animales", e);}// por si llega a ocurrir un error al instanciar los objetos
    }

    //Incrementa el total de animales muertos

    public void incrementarMuertos(int cantidad) {
        totalMuertos += cantidad;
    }

    //Incrementa el total de animales alimentados

    public void incrementarAlimentados(int cantidad) {
        totalAlimentados += cantidad;
    }

    //Incrementa el total de animales nacidos.
    public void incrementarNacidos(int cantidad) {
        totalNacidos += cantidad;
    }

    //Registra el nacimiento de un animal y  actualiza las listas de animales vivos y nacidos

    public void registrarNacimiento(Animal animal) {
        animalesNacidos.add(animal);
        animalesVivos.add(animal);
        incrementarNacidos(1); // Incrementamos el contador acumulativo
    }

    //se agega  la muerte de un animal actualiza  animales vivos y muertos.
    public void registrarMuerte(Animal animal) {
        animalesMuertos.add(animal);
        animalesVivos.remove(animal);
        incrementarMuertos(1); // Incrementamos el contador acumulativo
    }

    //metodo para acumular los datos de cada ciclo,  son mapas para clasificar datos por separado
    public void mostrarEstadisticas() {

        Map<String, Integer> conteoVivos = new HashMap<>();
        Map<String, Integer> conteoMuertos = new HashMap<>();
        Map<String, Integer> conteoNacidos = new HashMap<>();

        // Contar animales vivos
        for (Animal animal : animalesVivos) {
            String especie = animal.getNombreEspecie();
            conteoVivos.put(especie, conteoVivos.getOrDefault(especie, 0) + 1);
        }

        // Contar animales muertos
        for (Animal animal : animalesMuertos) {
            String especie = animal.getNombreEspecie();
            conteoMuertos.put(especie, conteoMuertos.getOrDefault(especie, 0) + 1);
        }

        // Contar animales nacidos
        for (Animal animal : animalesNacidos) {
            String especie = animal.getNombreEspecie();
            conteoNacidos.put(especie, conteoNacidos.getOrDefault(especie, 0) + 1);
        }

        // AQUI se imprimen las estadísticas al final del ciclo

        System.out.println("***************************** Estadísticas *********************************");
        for (Map.Entry<String, Integer> entry : conteoVivos.entrySet()) {
            String especie = entry.getKey();
            int cantidad = entry.getValue();
            String emoji = obtenerEmojiPorEspecie(especie);
            System.out.println("Animales vivos de " + especie + ": " + cantidad + " " + emoji);
        }

        for (Map.Entry<String, Integer> entry : conteoMuertos.entrySet()) {
            String especie = entry.getKey();
            int cantidad = entry.getValue();
            String emoji = obtenerEmojiPorEspecie(especie);
            System.out.println("Animales muertos de " + especie + ": " + cantidad + " " + emoji);
        }

        for (Map.Entry<String, Integer> entry : conteoNacidos.entrySet()) {
            String especie = entry.getKey();
            int cantidad = entry.getValue();
            String emoji = obtenerEmojiPorEspecie(especie);
            System.out.println("Animales nacidos de " + especie + ": " + cantidad + " " + emoji);
        }

        // Mostrar total de las especies
        System.out.println("******************************************************************************");
        System.out.println("Total de animales vivos: " + animalesVivos.size());
        System.out.println("Total de animales muertos este ciclo: " + animalesMuertos.size());
        System.out.println("Total de animales nacidos: " + totalNacidos);
        System.out.println("******************************************************************************");
    }

    //al final de cada cilco se imprime el total de animales que hay y su emoji
    private String obtenerEmojiPorEspecie(String especie) {
        switch (especie) {
            case "Puerco": return "\uD83D\uDC16";  // 🐖
            case "Oso": return "\uD83D\uDC3B";    // 🐻
            case "Boa": return "\uD83D\uDC0D";    // 🐍
            case "Conejo": return "\uD83D\uDC07"; // 🐇
            case "Pato": return "\uD83E\uDD86";   // 🦆
            case "Ratón": return "\uD83D\uDC01";  // 🐁
            case "Cabra": return "\uD83D\uDC10";  // 🐐
            case "Caballo": return "\uD83D\uDC0E"; // 🐴
            case "Zorro": return "\uD83E\uDD8A";  // 🦊
            case "Águila": return "\uD83E\uDD85"; // 🦅
            case "Borrego": return "\uD83D\uDC11"; // 🐑
            case "Oruga": return "\uD83D\uDC1B";  // 🐛
            case "Búfalo": return "\uD83D\uDC03"; // 🐃
            case "Ciervo": return "\uD83E\uDD8C"; // 🦌
            default: return "\uD83C\uDF3F";       // 🌿 Emoji en caso de que no salgan los otros
        }
    }

    public void reiniciarCiclo(List<Animal> animalesVivos) {
        this.animalesVivos = animalesVivos; // Conservar vivos
        this.animalesMuertos.clear();       // Reiniciar muertos del ciclo
        this.animalesNacidos.clear();       // Reiniciar nacidos del ciclo
    }
}