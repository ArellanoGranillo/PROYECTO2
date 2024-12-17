package Proyecto2;

import java.util.concurrent.ThreadLocalRandom;

public class Oveja extends Herbivoro {

    // Constructor de la clase Oveja
    public Oveja(Ubicacion ubicacionActual) {
        super("Oveja", 70, 3, 15, 140, "🐑", ubicacionActual);
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
        if (ubicacion.getCantidadOvejas() < 140) {
            Ubicacion nuevaUbicacion = SimuladorDeLaIsla.obtenerUbicacionAleatoria();
            int desplazamientoX = ThreadLocalRandom.current().nextInt(-3, 4);
            int desplazamientoY = ThreadLocalRandom.current().nextInt(-3, 4);
            nuevaUbicacion.actualizarPosicion(desplazamientoX, desplazamientoY);
            System.out.println("La oveja se movió " + desplazamientoX + " filas y " + desplazamientoY + " columnas.");
        } else {
            System.out.println("La ubicación ya tiene el máximo de ovejas, ya no caben.");
        }
    }

    @Override
    public Oveja reproducirse() {
        Ubicacion ubicacion = getUbicacionActual();
        if (getLlenura() >= getCantidadAlimentoNecesario() && ubicacion.confirmarSiHayEspecieCompatible(this)) {
            double probabilidadReproduccion = Math.random();
            if (probabilidadReproduccion < 0.5) {
                System.out.println("La oveja ha logrado reproducirse y nació una nueva oveja.");
                return new Oveja(ubicacion);
            } else {
                System.out.println("La oveja no se ha reproducido.");
            }
        } else {
            System.out.println("La oveja no tiene suficiente alimento o no hay otra oveja para reproducirse.");
        }
        return null;
    }

    @Override
    public boolean verificarMuerte() {
        if (getLlenura() <= 0) {
            System.out.println(this.getNombreEspecie() + " ha muerto por falta de comida.");
            getUbicacionActual().reducirOveja(1);
            return true; // La oveja muere si su llenura llega a cero
        }
        return false; // La oveja sigue viva
    }
}
