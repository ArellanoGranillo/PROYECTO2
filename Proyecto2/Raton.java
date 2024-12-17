package Proyecto2;
import java.util.concurrent.ThreadLocalRandom;

public class Raton extends Herbivoro {

    public Raton(Ubicacion ubicacion) {
        super("Raton", 1, 6, 0.05, 200, "🐭", ubicacion);
    }

    @Override
    public void comer() {
        Ubicacion ubicacion = getUbicacionActual();
        System.out.println(this.getNombreEspecie() + " está buscando plantas para comer.");

        if (ubicacion.confirmarSiHayPlanta()) {
            System.out.println(this.getNombreEspecie() + " está comiendo plantas.");
            setLlenura(getLlenura() + 1);
            ubicacion.reducirPlanta(1);
        } else {
            System.out.println(this.getNombreEspecie() + " no encontró plantas.");
        }
    }

    @Override
    public void mover() {
        Ubicacion ubicacion = getUbicacionActual();
        if (ubicacion.getCantidadRaton() < 200) {
            Ubicacion nuevaUbicacion = SimuladorDeLaIsla.obtenerUbicacionAleatoria();
            int desplazamientoX = ThreadLocalRandom.current().nextInt(-3, 4);
            int desplazamientoY = ThreadLocalRandom.current().nextInt(-3, 4);
            nuevaUbicacion.actualizarPosicion(desplazamientoX, desplazamientoY);
            System.out.println(this.getNombreEspecie() + " se movió " + desplazamientoX + " filas y " + desplazamientoY + " columnas.");
        } else {
            System.out.println("La ubicación ya tiene el máximo de ratones.");
        }
    }

    @Override
    public Animal reproducirse() {
        Ubicacion ubicacion = getUbicacionActual();
        if (getLlenura() >= getCantidadAlimentoNecesario() && ubicacion.confirmarSiHayEspecieCompatible(this)) {
            double probabilidadReproduccion = Math.random();
            if (probabilidadReproduccion < 0.5) {
                System.out.println("El ratón ha logrado reproducirse y nació un nuevo ratón.");
                return new Raton(ubicacion);
            } else {
                System.out.println("El ratón no se ha reproducido.");
            }
        } else {
            System.out.println("El ratón no tiene suficiente alimento o no hay otro ratón para reproducirse.");
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
