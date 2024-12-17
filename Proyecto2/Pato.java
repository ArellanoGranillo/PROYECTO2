package Proyecto2;


import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Pato extends Herbivoro {


    // Constructor de la clase Pato
    public Pato(Ubicacion ubicacion) {
        super("Pato", 1, 4, 0.15, 200, "🦆", ubicacion);
    }

    @Override
    public void comer() {
        System.out.println(this.getNombreEspecie() + " está buscando plantitas para comer");
        Ubicacion ubicacion = getUbicacionActual();
        Random aleatorio = new Random();
        int probabilidad = aleatorio.nextInt(100);

        if (probabilidad < 70) {
            if (ubicacion.confirmarSiHayPlanta()) {
                System.out.println(this.getNombreEspecie() + " está comiendo plantas");
                setLlenura(getLlenura() + 1);
                ubicacion.reducirPlanta(1);
            } else {
                System.out.println(this.getNombreEspecie() + " no puede comer porque no hay plantas disponibles.");
            }
        } else if (probabilidad < 30) {
            System.out.println(this.getNombreEspecie() + " está buscando orugas para comer.");
            if (ubicacion.confirmarSiHayOruga()) {
                double cantidadOrugas = ubicacion.getCantidadOrugas();
                if (cantidadOrugas > 0) {
                    System.out.println(this.getNombreEspecie() + " está comiendo orugas.");
                    setLlenura(getLlenura() + 1);
                    ubicacion.reducirOruga(1);
                }
            } else {
                System.out.println(this.getNombreEspecie() + " no encontró orugas.");
            }
        } else {
            System.out.println(this.getNombreEspecie() + " no encontró comida.");
        }
    }

    @Override
    public void mover() {
        Ubicacion ubicacion = getUbicacionActual();
        if (ubicacion.getCantidadPatos() < 200) {
            Ubicacion nuevaUbicacion = SimuladorDeLaIsla.obtenerUbicacionAleatoria();
            int desplazamientoX = ThreadLocalRandom.current().nextInt(-4, 5);
            int desplazamientoY = ThreadLocalRandom.current().nextInt(-4, 5);
            nuevaUbicacion.actualizarPosicion(desplazamientoX, desplazamientoY);
            System.out.println("El pato se movió " + desplazamientoX + " filas y " + desplazamientoY + " columnas.");
        } else {
            System.out.println("La ubicación ya tiene el máximo de patos.");
        }
    }

    @Override
    public Animal reproducirse() {
        Ubicacion ubicacion = getUbicacionActual();
        if (getLlenura() >= getCantidadAlimentoNecesario() && ubicacion.confirmarSiHayEspecieCompatible(this)) {
            double probabilidadReproduccion = Math.random();
            if (probabilidadReproduccion < 0.5) {
                System.out.println("El pato ha logrado reproducirse y nació un nuevo pato.");
                return new Pato(ubicacion);
            } else {
                System.out.println("El pato no se ha reproducido.");
            }
        } else {
            System.out.println("El pato no tiene suficiente alimento o no hay otro pato para reproducirse.");
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
