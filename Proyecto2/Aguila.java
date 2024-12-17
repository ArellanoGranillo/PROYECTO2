package Proyecto2;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Aguila extends Carnivoro {

    // info general del águila
    public Aguila(Ubicacion ubicacionActual) {
        super("Águila", 6.0, 3, 1.0, 20, "🦅", ubicacionActual);
    }

    @Override
    public void comer() {
        Random aleatorio = new Random();
        int probabilidad = aleatorio.nextInt(100); //  número aleatorio
        boolean comio = false;
        resetearCiclosSinComer();

        if (probabilidad < 10) { // 10% de probabilidad de comer un zorro
            if (getUbicacionActual().confirmarSiHayZorro()) {
                System.out.println("El águila come un zorro.");
                setLlenura(getLlenura() + 8);
                getUbicacionActual().reducirZorro(1); // Reduce un zorro en la ubicación
                comio = true;
            }
        } else if (probabilidad < 90) { // 90% de probabilidad de comer un conejo
            if (getUbicacionActual().confirmarSiHayConejo()) {
                System.out.println("El águila come un conejo.");
                setLlenura(getLlenura() + 2);
                getUbicacionActual().reducirConejo(1);
                comio = true;
            }
        } else if (probabilidad < 80) { // 80% de probabilidad de comer un pato
            if (getUbicacionActual().confirmarSiHayPato()) {
                System.out.println("El águila come un pato.");
                setLlenura(getLlenura() + 1); //
                getUbicacionActual().reducirPato(1);
                comio = true;
            }
        }

        if (!comio) {
            System.out.println("El águila no encontró comida.");
            incrementarCiclosSinComer();
        }
    }

    @Override
    public void mover() {
        Ubicacion ubicacionActual = getUbicacionActual();
        if (ubicacionActual.getCantidadAguilas() < getCantidadMaximaDeEspeciePorLocalidad()) {
            Ubicacion nuevaUbicacion = SimuladorDeLaIsla.obtenerUbicacionAleatoria();
            int desplazamientoX = ThreadLocalRandom.current().nextInt(-3, 4); // Desplazamiento aleatorio entre -3 y 3.
            int desplazamientoY = ThreadLocalRandom.current().nextInt(-3, 4);
            nuevaUbicacion.actualizarPosicion(desplazamientoX, desplazamientoY);
            System.out.println("El águila se movió a una nueva ubicación: " + ubicacionActual);
        } else {
            System.out.println("Ya no caben más águilas en esta ubicación :( ");
        }
    }

    @Override
    public Aguila reproducirse() {
        if (getLlenura() >= getCantidadAlimentoNecesario() && getUbicacionActual().confirmarSiHayAguila()) {
            double probabilidadReproduccion = Math.random();
            if (probabilidadReproduccion < 0.5) {
                System.out.println("Un nuevo águila nació :) ");
                return new Aguila(getUbicacionActual());
            } else {
                System.out.println("El águila no se reprodujo ");
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean verificarMuerte() {
        if (getCiclosSinComer() > 2) { // Más de 2 ciclos sin comida mueren
            System.out.println("El águila ha muerto por falta de alimento :(");
            getUbicacionActual().reducirAguila(1); // Elimina un águila de la ubicación
            return true;
        }
        return false;
    }
}
