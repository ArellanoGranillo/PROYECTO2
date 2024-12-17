package Proyecto2;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Oso extends Carnivoro {

    // Constructor de la clase
    public Oso(Ubicacion ubicacionActual) {
        super("Oso", 500, 2, 80, 5, "🐻", ubicacionActual);
    }

    @Override
    public void comer() {
        Random aleatorio = new Random();
        int probabilidad = aleatorio.nextInt(100); // Generar número aleatorio
        boolean comio = false;
        resetearCiclosSinComer();

        if (probabilidad < 80 && getUbicacionActual().confirmarSiHayBoa()) {
            System.out.println("El oso come una boa.");
            setLlenura(getLlenura() + 15);
            getUbicacionActual().reducirBoa(1); // Reduce una boa en la ubicación.
            comio = true;
        } else if (probabilidad < 40 && getUbicacionActual().confirmarSiHayCaballo()) {
            System.out.println("El oso come un caballo.");
            setLlenura(getLlenura() + 40);
            getUbicacionActual().reducirCaballo(1);
            comio = true;
        } else if (probabilidad < 80 && getUbicacionActual().confirmarSiHayCiervo()) {
            System.out.println("El oso come un ciervo.");
            setLlenura(getLlenura() + 50);
            getUbicacionActual().reducirCiervo(1);
            comio = true;
        } else if (probabilidad < 90 && getUbicacionActual().confirmarSiHayRaton()) {
            System.out.println("El oso come un ratón.");
            setLlenura(getLlenura() + 5);
            getUbicacionActual().reducirRaton(1);
            comio = true;
        } else if (probabilidad < 70 && getUbicacionActual().confirmarSiHayCabra()) {
            System.out.println("El oso come una cabra.");
            setLlenura(getLlenura() + 30);
            getUbicacionActual().reducirCabra(1);
            comio = true;
        } else if (probabilidad < 50 && getUbicacionActual().confirmarSiHayPuercos()) {
            System.out.println("El oso come un puerco.");
            setLlenura(getLlenura() + 35);
            getUbicacionActual().reducirPuercos(1);
            comio = true;
        } else if (probabilidad < 10 && getUbicacionActual().confirmarSiHayPato()) {
            System.out.println("El oso come un pato.");
            setLlenura(getLlenura() + 10);
            getUbicacionActual().reducirPato(1);
            comio = true;
        }

        if (!comio) {
            System.out.println("El oso no encontró comida.");
            incrementarCiclosSinComer();
        }
    }

    @Override
    public void mover() {
        Ubicacion ubicacionActual = getUbicacionActual();
        if (ubicacionActual.getCantidadOsos() < getCantidadMaximaDeEspeciePorLocalidad()) {
            Ubicacion nuevaUbicacion = SimuladorDeLaIsla.obtenerUbicacionAleatoria();
            int desplazamientoX = ThreadLocalRandom.current().nextInt(-3, 4); // Desplazamiento aleatorio entre -3 y 3.
            int desplazamientoY = ThreadLocalRandom.current().nextInt(-3, 4);
            nuevaUbicacion.actualizarPosicion(desplazamientoX, desplazamientoY);
            System.out.println("El oso se movió a una nueva ubicación: " + ubicacionActual);
        } else {
            System.out.println("Ya no caben más osos en esta ubicación.");
        }
    }

    @Override
    public Oso reproducirse() {
        if (getLlenura() >= getCantidadAlimentoNecesario() && getUbicacionActual().confirmarSiHayEspecieCompatible(this)) {
            double probabilidadReproduccion = Math.random();
            if (probabilidadReproduccion < 0.5) {
                System.out.println("Un nuevo oso nació.");
                return new Oso(getUbicacionActual());
            } else {
                System.out.println("El oso no se reprodujo.");
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean verificarMuerte() {
        if (getCiclosSinComer() > 2) { // Más de 2 ciclos sin comida
            System.out.println("El oso ha muerto por falta de alimento :(");
            getUbicacionActual().reducirOso(1); // Elimina un oso de la ubicación
            return true;
        }
        return false; // El oso sigue vivo
    }
}
