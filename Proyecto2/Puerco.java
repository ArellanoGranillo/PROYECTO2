package Proyecto2;

import java.util.concurrent.ThreadLocalRandom;

public class Puerco extends Herbivoro {

    public Puerco(Ubicacion ubicacion) {
        super("Puerco", 400, 2, 50, 50, "🐖", ubicacion);
    }

    @Override
    public void comer() {
        int probabilidad = ThreadLocalRandom.current().nextInt(100);
        Ubicacion ubicacion = getUbicacionActual();

        if (probabilidad < 100) {
            if (ubicacion.confirmarSiHayPlanta()) {
                System.out.println(this.getNombreEspecie() + " está comiendo plantas.");
                setLlenura(getLlenura() + 1);
                ubicacion.reducirPlanta(1);
            } else {
                System.out.println(this.getNombreEspecie() + " no encontró plantas.");
            }
        } else if (probabilidad < 90) {
            if (ubicacion.confirmarSiHayOruga()) {
                System.out.println(this.getNombreEspecie() + " está comiendo orugas.");
                setLlenura(getLlenura() + 1);
                ubicacion.reducirOruga(1);
            } else {
                System.out.println(this.getNombreEspecie() + " no encontró orugas.");
            }
        } else if (probabilidad < 50) {
            if (ubicacion.confirmarSiHayRaton()) {
                System.out.println(this.getNombreEspecie() + " está comiendo ratones.");
                setLlenura(getLlenura() + 1);
                ubicacion.reducirRaton(1);
            } else {
                System.out.println(this.getNombreEspecie() + " no encontró ratones.");
            }
        } else {
            System.out.println(this.getNombreEspecie() + " no encontró comida.");
        }
    }

    @Override
    public void mover() {
        Ubicacion ubicacion = getUbicacionActual();
        Ubicacion nuevaUbicacion = SimuladorDeLaIsla.obtenerUbicacionAleatoria();
        int desplazamientoX = ThreadLocalRandom.current().nextInt(-getVelocidad(), getVelocidad() + 1);
        int desplazamientoY = ThreadLocalRandom.current().nextInt(-getVelocidad(), getVelocidad() + 1);
        nuevaUbicacion.actualizarPosicion(desplazamientoX, desplazamientoY);
        System.out.println(this.getNombreEspecie() + " se movió " + desplazamientoX + " filas y " + desplazamientoY + " columnas.");
    }

    @Override
    public Animal reproducirse() {
        Ubicacion ubicacion = getUbicacionActual();

        if (getLlenura() >= getCantidadAlimentoNecesario() && ubicacion.confirmarSiHayEspecieCompatible(this)) {
            double probabilidadReproduccion = Math.random();
            if (probabilidadReproduccion < 0.5) {
                System.out.println("El puerquito ha logrado reproducirse.");
                Puerco nuevoPuerco = new Puerco(ubicacion);
                ubicacion.agregarAnimal(nuevoPuerco);
                return nuevoPuerco;
            } else {
                System.out.println("El puerquito no se ha reproducido.");
            }
        }
        return null;
    }

    @Override
    public boolean verificarMuerte() {
        if (this.getLlenura() <= 0) {
            System.out.println(this.getNombreEspecie() + " ha muerto por falta de comida.");
            return true;
        }
        return false;
    }
}
