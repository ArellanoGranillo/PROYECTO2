package Proyecto2;

public abstract class Animal implements Mover, Comer, Reproducir {

    // info general de todos los animáles
    private final String nombreEspecie;
    private final double peso; // Peso del animal
    private final int velocidad; // Máximos lugares que puede avanzar por ubicación
    private final double cantidadAlimentoNecesario; // Alimento necesario
    private final int cantidadMaximaPorLocalidad; // Máxima cantidad en una ubicación
    private final String emojiUnicode; // Representación gráfica del animal
    private Ubicacion ubicacionActual; // Ubicación del animal en la isla


    protected double llenura = 0; // ¿cuánto han comido?
    private int ciclosSinComer = 0; // Contador de ciclos sin comer
    private final int maxCiclosSinComer = 2; // máximo de ciclos que pueden estar sin comer

    // Constructor
    public Animal(String nombreEspecie, double peso, int velocidad, double cantidadAlimentoNecesario,
                  int cantidadMaximaPorLocalidad, String emojiUnicode, Ubicacion ubicacionActual) {
        this.nombreEspecie = nombreEspecie;
        this.peso = peso;
        this.velocidad = velocidad;
        this.cantidadAlimentoNecesario = cantidadAlimentoNecesario;
        this.cantidadMaximaPorLocalidad = cantidadMaximaPorLocalidad;
        this.emojiUnicode = emojiUnicode;
        this.ubicacionActual = ubicacionActual;
    }

    // Getters :)
    public String getNombreEspecie() {
        return nombreEspecie;
    }

    public double getPeso() {
        return peso;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public double getCantidadAlimentoNecesario() {
        return cantidadAlimentoNecesario;
    }


   public int getCantidadMaximaDeEspeciePorLocalidad() {
        return cantidadMaximaPorLocalidad;
    }

    public String getEmojiUnicode() {
        return emojiUnicode;
    }

    public Ubicacion getUbicacionActual() {
        return ubicacionActual;
    }

    // Setters :)
    public void setUbicacionActual(Ubicacion ubicacion) {
        this.ubicacionActual = ubicacion;
    }


    public void resetearCiclosSinComer() {
        ciclosSinComer = 0;


    }
        public Animal(String nombreEspecie, double peso, int velocidad, double cantidadAlimentoNecesario, int cantidadMaximaPorLocalidad, String emojiUnicode, double llenuraInicial) {
            this.nombreEspecie = nombreEspecie;
            this.peso = peso;
            this.velocidad = velocidad;
            this.cantidadAlimentoNecesario = cantidadAlimentoNecesario;
            this.cantidadMaximaPorLocalidad = cantidadMaximaPorLocalidad;
            this.emojiUnicode = emojiUnicode;
            this.llenura = llenuraInicial;
            this.ciclosSinComer = 0;
        }

        public double getLlenura() {
            return llenura;
        }

        public void setLlenura(double llenura) {
            this.llenura = Math.max(0, llenura); // No puede ser negativa
        }

        public int getCiclosSinComer() {
            return ciclosSinComer;
        }

        public void incrementarCiclosSinComer() {
            ciclosSinComer++;
        }

      // Verificar si el animal ha muerto
    public boolean verificarMuerte() {
        if (ciclosSinComer > maxCiclosSinComer) {
            System.out.println(nombreEspecie + " ha muerto por falta de alimento.");
            ubicacionActual.reducirAnimal(this);
            return true;
        }
        return false;
    }

}

