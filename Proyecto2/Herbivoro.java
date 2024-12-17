package Proyecto2;

public abstract class Herbivoro extends Animal {
// llamando a la clase padre
    public Herbivoro(String nombre, double peso, int velocidad, double cantidadAlimentoNecesario, int cantidadMaximaDeEspeciePorLocalidad, String emojiUnicode, Ubicacion ubicacion) {
        super(nombre, peso, velocidad, cantidadAlimentoNecesario, cantidadMaximaDeEspeciePorLocalidad, emojiUnicode, ubicacion);
    }
}
