package Proyecto2;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
public class Lobo extends Carnivoro {

    // Constructor de la clase
    public Lobo(Ubicacion ubicacionActual) {
        super("Lobo", 50, 3, 8, 30, "🐺", ubicacionActual);
    }

    @Override
    public void comer() {
        Random aleatorio = new Random();
        int probabilidad = aleatorio.nextInt(100); // Generar número aleatorio
        boolean comio = false;
        resetearCiclosSinComer();
        if (probabilidad < 10) {
            if (getUbicacionActual().confirmarSiHayCaballo()) {
                System.out.println("El lobo come un caballo.");
                setLlenura(getLlenura() + 40);
                getUbicacionActual().reducirCaballo(1); // Reduce un caballo en la ubicación
                comio = true;
            } else if (probabilidad < 15) {
                if (getUbicacionActual().confirmarSiHayCiervo()) {
                    System.out.println("El lobo come un ciervo.");
                    setLlenura(getLlenura() + 30);
                    getUbicacionActual().reducirCiervo(1);
                    comio = true;
                } else if (probabilidad < 60) {
                    if (getUbicacionActual().confirmarSiHayConejo()) {
                        System.out.println("El lobo come un conejo.");
                        setLlenura(getLlenura() + 5);
                        getUbicacionActual().reducirConejo(1);
                        comio = true;
                    } else if (probabilidad < 10) {
                        if (getUbicacionActual().confirmarSiHayRaton()) {
                            System.out.println("El lobo come un ratón.");
                            setLlenura(getLlenura() + 2);
                            getUbicacionActual().reducirRaton(1);
                            comio = true;
                        } else if (probabilidad < 70) {
                            if (getUbicacionActual().confirmarSiHayOveja()) {
                                System.out.println("El lobo come una oveja.");
                                setLlenura(getLlenura() + 25);
                                getUbicacionActual().reducirOveja(1);
                                comio = true;
                            } else if (probabilidad < 15) {
                                if (getUbicacionActual().confirmarSiHayPuercos()) {
                                    System.out.println("El lobo come un puerco.");
                                    setLlenura(getLlenura() + 35);
                                    getUbicacionActual().reducirPuercos(1);
                                    comio = true;
                                }
                            }
                        }
                    }
                }
            }
        }

        if (!comio) {
            System.out.println("El lobo no encontró comida.");
            incrementarCiclosSinComer();
        }
    }

    @Override
    public void mover() {
        Ubicacion ubicacionActual = getUbicacionActual();
        if (ubicacionActual.getCantidadLobos() < getCantidadMaximaDeEspeciePorLocalidad()) {
            Ubicacion nuevaUbicacion = SimuladorDeLaIsla.obtenerUbicacionAleatoria();
            int desplazamientoX = ThreadLocalRandom.current().nextInt(-3, 4); // Desplazamiento aleatorio entre -3 y 3.
            int desplazamientoY = ThreadLocalRandom.current().nextInt(-3, 4);
            nuevaUbicacion.actualizarPosicion(desplazamientoX, desplazamientoY);
            System.out.println("El lobo se movió" + desplazamientoX + " filas y " + desplazamientoY + " columnas.");
        } else {
            System.out.println("Ya no caben más lobos en esta ubicación.");
        }
    }

    @Override
    public Lobo reproducirse() {
        if (getLlenura() >= getCantidadAlimentoNecesario() && getUbicacionActual().confirmarSiHayEspecieCompatible(this)) {
            double probabilidadReproduccion = Math.random();
            if (probabilidadReproduccion < 0.5) {
                System.out.println("Un nuevo lobo nació.");
                return new Lobo(getUbicacionActual());
            } else {
                System.out.println("El lobo no se reprodujo.");
            }
        } else {
            System.out.println("El lobo no tiene suficiente alimento o no hay otro lobo para reproducirse.");
        }

        return null; // Si no se reproduce, retorna null
    }

    @Override
    public boolean verificarMuerte() {
        if (getCiclosSinComer() > 2) { // Más de 2 ciclos sin comida.
            System.out.println("El lobo ha muerto por falta de alimento :(");
            getUbicacionActual().reducirLobo(1); // Elimina un lobo de la ubicación.
            return true;
        }
        return false; // El lobo sigue vivo.
    }
}
