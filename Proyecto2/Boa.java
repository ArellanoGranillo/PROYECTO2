package Proyecto2;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Boa extends Carnivoro {

    private int ciclosSinComer = 0;

    public Boa(Ubicacion ubicacionActual) {
        super("Boa", 15, 1, 3, 30, "🐍", ubicacionActual);
    }

    @Override
    public void comer() {
        Random aleatorio = new Random();
        int probabilidad = aleatorio.nextInt(100);
        boolean comio = false;

        // Probabilidades para alimentarse
        if (probabilidad < 15 && getUbicacionActual().confirmarSiHayZorro()) {  // 15% probabilidad de comer zorro
            System.out.println("La boa come un zorro.");
            setLlenura(getLlenura() + 8);
            getUbicacionActual().reducirZorro(1);
            comio = true;
        } else if (probabilidad < 35 && getUbicacionActual().confirmarSiHayConejo()) {  // 20% probabilidad de comer conejo
            System.out.println("La boa come un conejo.");
            setLlenura(getLlenura() + 5);
            getUbicacionActual().reducirConejo(1);
            comio = true;
        } else if (probabilidad < 75 && getUbicacionActual().confirmarSiHayRaton()) {  // 40% probabilidad de comer ratón
            System.out.println("La boa come un ratón.");
            setLlenura(getLlenura() + 2);
            getUbicacionActual().reducirRaton(1);
            comio = true;
        } else if (probabilidad < 85 && getUbicacionActual().confirmarSiHayPato()) {  // 10% probabilidad de comer pato
            System.out.println("La boa come un pato.");
            setLlenura(getLlenura() + 1);
            getUbicacionActual().reducirPato(1);
            comio = true;
        } else {
            aumentarCicloSinComer(); // Si no comió se  aumenta el ciclo sin comer
        }
    }

    public void aumentarCicloSinComer() {
        ciclosSinComer++;
    }

    @Override
    public void mover() {
        Ubicacion ubicacion = getUbicacionActual();
        if (ubicacion.getCantidadCabras() < 30){
            Ubicacion nuevaUbicacion = SimuladorDeLaIsla.obtenerUbicacionAleatoria();
            int desplazamientoX = ThreadLocalRandom.current().nextInt(-1, 2); // ¿qué tanto puede moeverse?
        int desplazamientoY = ThreadLocalRandom.current().nextInt(-1, 2);
            nuevaUbicacion.actualizarPosicion(desplazamientoX, desplazamientoY);
        System.out.println("La boa se movió " + desplazamientoX + " filas " + desplazamientoY + " columnas.");
      } else {
                System.out.println("La ubicación ya tiene el máximo de caballos.");
            }
        }


@Override
    public Boa reproducirse() {
        if (getLlenura() >= getCantidadAlimentoNecesario() && getUbicacionActual().confirmarSiHayBoa()) {
            double probabilidadReproduccion = Math.random();
            if (probabilidadReproduccion < 0.5) {
                System.out.println("Nació una boa bebé.");
                return new Boa(getUbicacionActual()); // Crear una nueva boa
            } else {
                System.out.println("La boa no se reprodujo.");
            }
        }
        return null;
    }

    public boolean verificarMuerte() {
        if (ciclosSinComer > 2) {
            System.out.println("La boa ha muerto por falta de alimento.");
            getUbicacionActual().reducirBoa(1); // Reduce una boa de la ubicación
            return true;
        }
        return false; // El águila está viva
    }
}