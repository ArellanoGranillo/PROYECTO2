package Proyecto2;

import java.util.concurrent.ThreadLocalRandom;

public class Bufalo extends Herbivoro {

    public Bufalo(Ubicacion ubicacionActual) {
        super("Búfalo", 3, 3, 1.0, 700, "🐃", ubicacionActual);
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
            System.out.println(this.getNombreEspecie() + " no puede comer porque no hay suficientes plantas disponibles.");
        }
    }

    @Override
    public void mover() {
        Ubicacion ubicacion = getUbicacionActual();
        if (ubicacion.getCantidadBufalos() < 10) {
            Ubicacion nuevaUbicacion = SimuladorDeLaIsla.obtenerUbicacionAleatoria();
            int desplazamientoX = ThreadLocalRandom.current().nextInt(-3, 4);
            int desplazamientoY = ThreadLocalRandom.current().nextInt(-3, 4);
            nuevaUbicacion.actualizarPosicion(desplazamientoX, desplazamientoY);
            System.out.println("El búfalo se movió " + desplazamientoX + " filas y " + desplazamientoY + " columnas.");
        } else {
            System.out.println("La ubicación ya tiene el máximo permitido de búfalos.");
        }
    }

    @Override
    public Bufalo reproducirse() {
        Ubicacion ubicacion = getUbicacionActual();
        if (getLlenura() >= getCantidadAlimentoNecesario() && ubicacion.confirmarSiHayEspecieCompatible(this)) {
            double probabilidadReproduccion = Math.random();
            if (probabilidadReproduccion < 0.5) {
                System.out.println("El búfalo ha logrado reproducirse y nació un nuevo búfalo.");
                return new Bufalo(ubicacion);
            } else {
                System.out.println("El búfalo no se ha reproducido.");
            }
        } else {
            System.out.println("El búfalo no tiene suficiente alimento o no hay otro búfalo para reproducirse.");
        }
        return null;
    }

    @Override
    public boolean verificarMuerte() {
        if (getLlenura() <= 0) {
            System.out.println(this.getNombreEspecie() + " ha muerto por falta de comida.");
            getUbicacionActual().reducirBufalo(1);
            return true;
        }
        return false;
    }
}
