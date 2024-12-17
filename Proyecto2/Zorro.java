package Proyecto2;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Zorro extends Carnivoro {

    // Constructor de la clase
    public Zorro(Ubicacion ubicacionActual) {
        super("Zorro", 8, 2, 2, 30, "🦊", ubicacionActual);
    }

    @Override
    public void comer() {
        Random aleatorio = new Random();
        int probabilidad = aleatorio.nextInt(100); // Generar número aleatorio entre 0 y 99.
        boolean comio = false;
        resetearCiclosSinComer();

        if (probabilidad < 60) { // 60% de probabilidad de comer un conejo
            if (getUbicacionActual().confirmarSiHayConejo()) {
                System.out.println("El zorro come un conejo.");
                setLlenura(getLlenura() + 5); // Comer un conejo aumenta más la llenura.
                getUbicacionActual().reducirConejo(1); // Reduce un conejo en la ubicación.
                comio = true;
            }
        } else if (probabilidad < 90) { // 30% de probabilidad de comer un ratón
            if (getUbicacionActual().confirmarSiHayRaton()) {
                System.out.println("El zorro come un ratón.");
                setLlenura(getLlenura() + 2);
                getUbicacionActual().reducirRaton(1);
                comio = true;
            }
        } else { // 10% de probabilidad de comer un pato
            if (getUbicacionActual().confirmarSiHayPato()) {
                System.out.println("El zorro come un pato.");
                setLlenura(getLlenura() + 1);
                getUbicacionActual().reducirPato(1);
                comio = true;
            }
        }

        if (!comio) {
            System.out.println("El zorro no encontró comida.");
            incrementarCiclosSinComer();
        }
    }

    @Override
    public void mover() {
        Ubicacion ubicacionActual = getUbicacionActual();
        Ubicacion nuevaUbicacion = SimuladorDeLaIsla.obtenerUbicacionAleatoria();
        if (ubicacionActual.getCantidadZorros() < getCantidadMaximaDeEspeciePorLocalidad()) {
            int desplazamientoX = ThreadLocalRandom.current().nextInt(-2, 3); // Desplazamiento entre -2 y 2
            int desplazamientoY = ThreadLocalRandom.current().nextInt(-2, 3);
            nuevaUbicacion.actualizarPosicion(desplazamientoX, desplazamientoY);
            System.out.println("El zorro se movió a una nueva ubicación: " + ubicacionActual);
        } else {
            System.out.println("Ya no caben más zorros en esta ubicación.");
        }
    }

    @Override
    public Zorro reproducirse() {
        if (getLlenura() >= getCantidadAlimentoNecesario() && getUbicacionActual().confirmarSiHayZorro()) {
            double probabilidadReproduccion = Math.random();
            if (probabilidadReproduccion < 0.5) {
                System.out.println("Un nuevo zorro nació.");
                return new Zorro(getUbicacionActual());
            } else {
                System.out.println("El zorro no se reprodujo.");
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean verificarMuerte() {
        if (getCiclosSinComer() > 2) { // Más de 2 ciclos sin comida.
            System.out.println("El zorro ha muerto por falta de alimento :(");
            getUbicacionActual().reducirZorro(1); // Elimina un zorro de la ubicación.
            return true;
        }
        return false; // El zorro sigue vivo.
    }
}
