package Proyecto2;

public class Planta {

    private static final int CANTIDAD_MAXIMA = 10000;  // Máximo de plantas en la isla
    private int cantidadPlantas;

    public Planta() {
        this.cantidadPlantas = CANTIDAD_MAXIMA;  // Por defecto tiene la cantidad máxima de plantas
    }

        public void reducirPlanta ( int cantidad){
            cantidadPlantas = Math.max(0, cantidadPlantas - cantidad);
        }

        public int obtenerCantidadPlantas () {
            return cantidadPlantas;
        }

        public void aumentarPlanta ( int cantidad){
            cantidadPlantas = Math.min(CANTIDAD_MAXIMA, cantidadPlantas + cantidad);
        }

        public boolean confirmarSiHayPlanta () {
            return cantidadPlantas > 0;
        }
    }

