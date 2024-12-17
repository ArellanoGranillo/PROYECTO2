package Proyecto2;

import java.util.concurrent.ThreadLocalRandom;

public class Cabra extends Herbivoro {

    public Cabra(Ubicacion ubicacionActual) {
        super("Cabra", 60, 3, 10, 140, "🐐", ubicacionActual);
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
        if (ubicacion.getCantidadCabras() < 140) {
            Ubicacion nuevaUbicacion = SimuladorDeLaIsla.obtenerUbicacionAleatoria();
            int desplazamientoX = ThreadLocalRandom.current().nextInt(-3, 4);
            int desplazamientoY = ThreadLocalRandom.current().nextInt(-3, 4);
            nuevaUbicacion.actualizarPosicion(desplazamientoX, desplazamientoY);
            System.out.println("La cabra se movió " + desplazamientoX + " filas y " + desplazamientoY + " columnas.");
        } else {
            System.out.println("La ubicación ya tiene el máximo de cabras, ya no caben.");
        }
    }

    @Override
    public Cabra reproducirse() {
        Ubicacion ubicacion = getUbicacionActual();

        if (getLlenura() >= getCantidadAlimentoNecesario() && ubicacion.confirmarSiHayEspecieCompatible(this)) {
            double probabilidadReproduccion = Math.random();
            if (probabilidadReproduccion < 0.5) {
                System.out.println("La cabra ha logrado reproducirse y nació un nuevo cabra.");
                return new Cabra(ubicacion);
            } else {
                System.out.println("La cabra no se ha reproducido.");
            }
        } else {
            System.out.println("La cabra no tiene suficiente alimento o no hay otra cabra para reproducirse.");
        }
        return null;
    }

    @Override
    public boolean verificarMuerte() {
        if (getLlenura() <= 0) {
            System.out.println(this.getNombreEspecie() + " ha muerto por falta de comida.");
            getUbicacionActual().reducirCabra(1);
            return true;
        }
        return false;
    }
}
