package Proyecto2;

import java.util.concurrent.ThreadLocalRandom;

public class Ciervo extends Herbivoro {

    public Ciervo(Ubicacion ubicacionActual) {
        super("Ciervo", 300, 4, 50, 20, "🦌", ubicacionActual);
    }

    @Override
    public void comer() {
        System.out.println(this.getNombreEspecie() + " está buscando plantas para comer.");
        Ubicacion ubicacion = getUbicacionActual();
        if (ubicacion.confirmarSiHayPlanta()) {
            System.out.println(this.getNombreEspecie() + " está comiendo plantas.");
            setLlenura(getLlenura() + 1);
            ubicacion.reducirPlanta(1);
        } else {
            System.out.println(this.getNombreEspecie() + " no puede comer porque no hay plantas disponibles.");
        }
    }

    @Override
    public void mover() {
        Ubicacion ubicacion = getUbicacionActual();
        if (ubicacion.getCantidadCiervos() < 20) {
            Ubicacion nuevaUbicacion = SimuladorDeLaIsla.obtenerUbicacionAleatoria();
            int desplazamientoX = ThreadLocalRandom.current().nextInt(-4, 5);
            int desplazamientoY = ThreadLocalRandom.current().nextInt(-4, 5);
            nuevaUbicacion.actualizarPosicion(desplazamientoX, desplazamientoY);
            System.out.println("El ciervo se movió " + desplazamientoX + " filas y " + desplazamientoY + " columnas.");
        } else {
            System.out.println("La ubicación ya tiene el máximo de ciervos.");
        }
    }

    @Override
    public Ciervo reproducirse() {
        Ubicacion ubicacion = getUbicacionActual();
        if (getLlenura() >= getCantidadAlimentoNecesario() && ubicacion.confirmarSiHayEspecieCompatible(this)) {
            double probabilidadReproduccion = Math.random();
            if (probabilidadReproduccion < 0.5) {
                System.out.println("El ciervo ha logrado reproducirse y nació un nuevo ciervo.");
                return new Ciervo(ubicacion);
            } else {
                System.out.println("El ciervo no se ha reproducido.");
            }
        } else {
            System.out.println("El ciervo no tiene suficiente alimento o no hay otro ciervo para reproducirse.");
        }
        return null;
    }

    @Override
    public boolean verificarMuerte() {
        if (getLlenura() <= 0) {
            System.out.println(this.getNombreEspecie() + " ha muerto por falta de comida.");
            return true; // El ciervo muere si su llenura llega a cero
        }
        return false;
    }
}
