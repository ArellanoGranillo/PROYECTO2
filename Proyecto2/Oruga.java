package Proyecto2;

public class Oruga extends Herbivoro {

    // Constructor de la clase Oruga
    public Oruga(Ubicacion ubicacionActual) {
        super("Oruga", 0.01, 0, 0, 1000, "🐛", ubicacionActual);
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
        System.out.println(this.getNombreEspecie() + " no puede moverse.");
    }

    @Override
    public Oruga reproducirse() {
        Ubicacion ubicacion = getUbicacionActual();
        if (getLlenura() >= getCantidadAlimentoNecesario() && ubicacion.confirmarSiHayEspecieCompatible(this)) {

            double probabilidadReproduccion = Math.random();
            if (probabilidadReproduccion < 0.5) {
                System.out.println("La oruga ha logrado reproducirse.");
                return new Oruga(ubicacion);
            } else {
                System.out.println("La oruga no se ha reproducido.");
            }
        } else {
            System.out.println("La oruga no tiene suficiente alimento o no hay otra oruga para reproducirse.");
        }
        return null;
    }

    @Override
    public boolean verificarMuerte() {
        if (getLlenura() <= 0) {
            System.out.println(this.getNombreEspecie() + " ha muerto por falta de comida.");
            getUbicacionActual().reducirOruga(1);
            return true; // La oruga muere si su llenura llega a cero
        }
        return false; // La oruga sigue viva
    }
}
