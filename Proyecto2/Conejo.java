package Proyecto2;

import java.util.concurrent.ThreadLocalRandom;

public class Conejo extends Herbivoro {

    // Constructor de la clase Conejo
    public Conejo(Ubicacion ubicacionActual) {
        super("Conejo", 2, 2, 0.45, 150, "🐇", ubicacionActual);
    }

    @Override
    public void comer() {
        System.out.println(this.getNombreEspecie() + " está buscando plantas para comer.");
        Ubicacion ubicacion = getUbicacionActual();
        if (ubicacion.confirmarSiHayPlanta()) {
            System.out.println(this.getNombreEspecie() + " está comiendo plantas.");
            setLlenura(getLlenura() + 1);
            ubicacion.reducirPlanta(1); // Reducimos una planta de la ubicación
        } else {
            System.out.println(this.getNombreEspecie() + " no puede comer porque no hay plantas disponibles.");
        }
    }

    @Override
    public void mover() {
        Ubicacion ubicacion = getUbicacionActual();
        if (ubicacion.getCantidadConejos() < 20) {
            Ubicacion nuevaUbicacion = SimuladorDeLaIsla.obtenerUbicacionAleatoria();
            int desplazamientoX = ThreadLocalRandom.current().nextInt(-3, 4);
            int desplazamientoY = ThreadLocalRandom.current().nextInt(-3, 4);
           nuevaUbicacion.actualizarPosicion(desplazamientoX, desplazamientoY);
            System.out.println("El conejo se movió " + desplazamientoX + " filas y " + desplazamientoY + " columnas.");
        } else {
            System.out.println("La ubicación ya tiene el máximo de conejos.");
        }
    }

    @Override
    public Conejo reproducirse() {
        Ubicacion ubicacion = getUbicacionActual();

        if (getLlenura() >= getCantidadAlimentoNecesario() && ubicacion.confirmarSiHayEspecieCompatible(this)) {
            double probabilidadReproduccion = Math.random();
            if (probabilidadReproduccion < 0.5) {
                System.out.println("El conejo ha logrado reproducirse y nació un nuevo conejo.");
                return new Conejo(ubicacion);
            } else {
                System.out.println("El conejo no se ha reproducido.");
            }
        } else {
            System.out.println("El conejo no tiene suficiente alimento o no hay otro conejo para reproducirse.");
        }
        return null;
    }

    @Override
    public boolean verificarMuerte() {
        if (getLlenura() <= 0) {
            System.out.println(this.getNombreEspecie() + " ha muerto por falta de comida.");
            return true; // El conejo muere si su llenura llega a cero
        }
        return false;
    }
}
