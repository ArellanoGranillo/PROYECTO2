package Proyecto2;

import java.util.concurrent.ThreadLocalRandom;

public class Caballo extends Herbivoro {

    public Caballo(Ubicacion ubicacionActual) {
        super("Caballo", 400, 4, 60, 20, "🐎", ubicacionActual);
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
        if (ubicacion.getCantidadCaballos() < 20) {
            Ubicacion nuevaUbicacion = SimuladorDeLaIsla.obtenerUbicacionAleatoria();
            int desplazamientoX = ThreadLocalRandom.current().nextInt(-4, 5);
            int desplazamientoY = ThreadLocalRandom.current().nextInt(-4, 5);
            nuevaUbicacion.actualizarPosicion(desplazamientoX, desplazamientoY);
            System.out.println("El caballo se movió " + desplazamientoX + " filas y " + desplazamientoY + " columnas.");
        } else {
            System.out.println("La ubicación ya tiene el máximo de caballos.");
        }
    }

    @Override
    public Caballo reproducirse() {
        Ubicacion ubicacion = getUbicacionActual();
        if (getLlenura() >= getCantidadAlimentoNecesario() && ubicacion.confirmarSiHayEspecieCompatible(this)) {

            double probabilidadReproduccion = Math.random();
            if (probabilidadReproduccion < 0.5) {
                System.out.println("El caballo ha logrado reproducirse y nació un nuevo caballo.");
                return new Caballo(ubicacion);
            } else {
                System.out.println("El caballo no se ha reproducido.");
            }
        } else {
            System.out.println("El caballo no tiene suficiente alimento o no hay otro caballo para reproducirse.");
        }
        return null;
    }

    @Override
    public boolean verificarMuerte() {
        if (getLlenura() <= 0) {
            System.out.println(this.getNombreEspecie() + " ha muerto por falta de comida.");
            getUbicacionActual().reducirCaballo(1);
            return true;
        }
        return false; // El caballo sigue vivo
    }
}
