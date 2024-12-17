package Proyecto2;

public abstract class Carnivoro extends Animal {
    public Carnivoro(String nombre, double peso, int velocidad, double cantidadAlimentoNecesario,
                     int cantidadMaximaDeEspeciePorLocalidad, String emojiUnicode, Ubicacion ubicacionActual) {
        // Llamamos al constructor de la clase Animal con todos los parámetros necesarios
        super(nombre, peso, velocidad, cantidadAlimentoNecesario, cantidadMaximaDeEspeciePorLocalidad, emojiUnicode, ubicacionActual);
    }

}
