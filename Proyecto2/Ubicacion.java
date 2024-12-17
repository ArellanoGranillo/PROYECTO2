package Proyecto2;

import java.util.ArrayList;
import java.util.List;

public class Ubicacion {
    public Ubicacion ubicacionActual;

    public Ubicacion getUbicacionActual() {
        return this.ubicacionActual;
    }

    private int cantidadZorros;
    private int cantidadConejos;
    private int cantidadRaton;
    private int cantidadAguilas;
    private int cantidadBoas;
    private int cantidadBufalos;
    private int cantidadCaballos;
    private int cantidadCabras;
    private int cantidadCiervos;
    private int cantidadLobos;
    private int cantidadOrugas;
    private int cantidadOsos;
    private int cantidadOvejas;
    private int cantidadPatos;
    private int cantidadPuercos;
    private double cantidadPlantas = 10000;
    private int fila;
    private int columna;

    // Lista de animales por especie
    private List<List<Animal>> listaAnimales = new ArrayList<>();
    private List<Animal> listaDeZorros = new ArrayList<>();
    private List<Animal> listaDeConejos = new ArrayList<>();
    private List<Animal> listaDeRaton = new ArrayList<>();
    private List<Animal> listaDeAguilas = new ArrayList<>();
    private List<Animal> listaDeBoas = new ArrayList<>();
    private List<Animal> listaDeBufalos = new ArrayList<>();
    private List<Animal> listaDeCaballos = new ArrayList<>();
    private List<Animal> listaDeCabras = new ArrayList<>();
    private List<Animal> listaDeCiervos = new ArrayList<>();
    private List<Animal> listaDeLobos = new ArrayList<>();
    private List<Animal> listaDeOrugas = new ArrayList<>();
    private List<Animal> listaDeOsos = new ArrayList<>();
    private List<Animal> listaDeOvejas = new ArrayList<>();
    private List<Animal> listaDePatos = new ArrayList<>();
    private List<Animal> listaDePuercos = new ArrayList<>();
    public List<Animal> getAnimales() {
        List<Animal> todosLosAnimales = new ArrayList<>();
        todosLosAnimales.addAll(listaDeZorros);
        todosLosAnimales.addAll(listaDeConejos);
        todosLosAnimales.addAll(listaDeRaton);
        todosLosAnimales.addAll(listaDeAguilas);
        todosLosAnimales.addAll(listaDeBoas);
        todosLosAnimales.addAll(listaDeBufalos);
        todosLosAnimales.addAll(listaDeCaballos);
        todosLosAnimales.addAll(listaDeCabras);
        todosLosAnimales.addAll(listaDeCiervos);
        todosLosAnimales.addAll(listaDeLobos);
        todosLosAnimales.addAll(listaDeOrugas);
        todosLosAnimales.addAll(listaDeOsos);
        todosLosAnimales.addAll(listaDeOvejas);
        todosLosAnimales.addAll(listaDePatos);
        todosLosAnimales.addAll(listaDePuercos);
        return todosLosAnimales;
    }

        public Ubicacion() {
            this.ubicacionActual = this;
            // Inicialización de listas de animales
        listaAnimales.add(listaDeZorros);
        listaAnimales.add(listaDeConejos);
        listaAnimales.add(listaDeRaton);
        listaAnimales.add(listaDeAguilas);
        listaAnimales.add(listaDeBoas);
        listaAnimales.add(listaDeBufalos);
        listaAnimales.add(listaDeCaballos);
        listaAnimales.add(listaDeCabras);
        listaAnimales.add(listaDeCiervos);
        listaAnimales.add(listaDeLobos);
        listaAnimales.add(listaDeOrugas);
        listaAnimales.add(listaDeOsos);
        listaAnimales.add(listaDeOvejas);
        listaAnimales.add(listaDePatos);
        listaAnimales.add(listaDePuercos);

        for (int i = 0; i < 20; i++) {
            listaDeZorros.add(new Zorro(this));  //
            listaDeConejos.add(new Conejo(this));
            listaDeRaton.add(new Raton(this));
            listaDeAguilas.add(new Aguila(this));
            listaDeBoas.add(new Boa(this));
            listaDeBufalos.add(new Bufalo(this));
            listaDeCaballos.add(new Caballo(this));
            listaDeCabras.add(new Cabra(this));
            listaDeCiervos.add(new Ciervo(this));
            listaDeLobos.add(new Lobo(this));
            listaDeOrugas.add(new Oruga(this));
            listaDeOsos.add(new Oso(this));
            listaDeOvejas.add(new Oveja(this));
            listaDePatos.add(new Pato(this));
            listaDePuercos.add(new Puerco(this));
        }

        // Inicializar las cantidades
        cantidadZorros = listaDeZorros.size();
        cantidadConejos = listaDeConejos.size();
        cantidadRaton = listaDeRaton.size();
        cantidadAguilas = listaDeAguilas.size();
        cantidadBoas = listaDeBoas.size();
        cantidadBufalos = listaDeBufalos.size();
        cantidadCaballos = listaDeCaballos.size();
        cantidadCabras = listaDeCabras.size();
        cantidadCiervos = listaDeCiervos.size();
        cantidadLobos = listaDeLobos.size();
        cantidadOrugas = listaDeOrugas.size();
        cantidadOsos = listaDeOsos.size();
        cantidadOvejas = listaDeOvejas.size();
        cantidadPatos = listaDePatos.size();
        cantidadPuercos = listaDePuercos.size();
    }

    public void actualizarPosicion(int desplazamientoX, int desplazamientoY) {
        this.fila += desplazamientoX;
        this.columna += desplazamientoY;
    }



    public void agregarAnimal(Animal animal) {
        if (animal instanceof Zorro) {
            listaDeZorros.add(animal);
            cantidadZorros++;
        } else if (animal instanceof Conejo) {
            listaDeConejos.add(animal);
            cantidadConejos++;
        } else if (animal instanceof Raton) {
            listaDeRaton.add(animal);
            cantidadRaton++;
        } else if (animal instanceof Aguila) {
            listaDeAguilas.add(animal);
            cantidadAguilas++;
        } else if (animal instanceof Boa) {
            listaDeBoas.add(animal);
            cantidadBoas++;
        } else if (animal instanceof Bufalo) {
            listaDeBufalos.add(animal);
            cantidadBufalos++;
        } else if (animal instanceof Caballo) {
            listaDeCaballos.add(animal);
            cantidadCaballos++;
        } else if (animal instanceof Cabra) {
            listaDeCabras.add(animal);
            cantidadCabras++;
        } else if (animal instanceof Ciervo) {
            listaDeCiervos.add(animal);
            cantidadCiervos++;
        } else if (animal instanceof Lobo) {
            listaDeLobos.add(animal);
            cantidadLobos++;
        } else if (animal instanceof Oruga) {
            listaDeOrugas.add(animal);
            cantidadOrugas++;
        } else if (animal instanceof Oso) {
            listaDeOsos.add(animal);
            cantidadOsos++;
        } else if (animal instanceof Oveja) {
            listaDeOvejas.add(animal);
            cantidadOvejas++;
        } else if (animal instanceof Pato) {
            listaDePatos.add(animal);
            cantidadPatos++;
        } else if (animal instanceof Puerco) {
            listaDePuercos.add(animal);
            cantidadPuercos++;
        }
    }

    public void reducirAnimal(Animal animal) {
        if (animal instanceof Zorro) {
            listaDeZorros.remove(animal);
            cantidadZorros = Math.max(0, cantidadZorros - 1);
        } else if (animal instanceof Conejo) {
            listaDeConejos.remove(animal);
            cantidadConejos = Math.max(0, cantidadConejos - 1);
        } else if (animal instanceof Pato) {
            listaDePatos.remove(animal);
            cantidadPatos = Math.max(0, cantidadPatos - 1);
        } else if (animal instanceof Aguila) {
            listaDeAguilas.remove(animal);
            cantidadAguilas = Math.max(0, cantidadAguilas - 1);
        } else if (animal instanceof Boa) {
            listaDeBoas.remove(animal);
            cantidadBoas = Math.max(0, cantidadBoas - 1);
        } else if (animal instanceof Bufalo) {
            listaDeBufalos.remove(animal);
            cantidadBufalos = Math.max(0, cantidadBufalos - 1);
        } else if (animal instanceof Caballo) {
            listaDeCaballos.remove(animal);
            cantidadCaballos = Math.max(0, cantidadCaballos - 1);
        } else if (animal instanceof Cabra) {
            listaDeCabras.remove(animal);
            cantidadCabras = Math.max(0, cantidadCabras - 1);
        } else if (animal instanceof Ciervo) {
            listaDeCiervos.remove(animal);
            cantidadCiervos = Math.max(0, cantidadCiervos - 1);
        } else if (animal instanceof Lobo) {
            listaDeLobos.remove(animal);
            cantidadLobos = Math.max(0, cantidadLobos - 1);
        } else if (animal instanceof Oruga) {
            listaDeOrugas.remove(animal);
            cantidadOrugas = Math.max(0, cantidadOrugas - 1);
        } else if (animal instanceof Oso) {
            listaDeOsos.remove(animal);
            cantidadOsos = Math.max(0, cantidadOsos - 1);
        } else if (animal instanceof Oveja) {
            listaDeOvejas.remove(animal);
            cantidadOvejas = Math.max(0, cantidadOvejas - 1);
        } else if (animal instanceof Puerco) {
            listaDePuercos.remove(animal);
            cantidadPuercos = Math.max(0, cantidadPuercos - 1);
        }
    }

    // Métodos para reducir la cantidad de animales
    public void reducirZorro(int cantidad) {
        cantidadZorros = Math.max(0, cantidadZorros - cantidad);
    }

    public void reducirConejo(int cantidad) {
        cantidadConejos = Math.max(0, cantidadConejos - cantidad);
    }

    public void reducirPato(int cantidad) {
        cantidadPatos = Math.max(0, cantidadPatos - cantidad);
    }

    public void reducirAguila(int cantidad) {
        cantidadAguilas = Math.max(0, cantidadAguilas - cantidad);
    }

    public void reducirBoa(int cantidad) {
        cantidadBoas = Math.max(0, cantidadBoas - cantidad);
    }

    public void reducirBufalo(int cantidad) {
        cantidadBufalos = Math.max(0, cantidadBufalos - cantidad);
    }

    public void reducirCaballo(int cantidad) {
        cantidadCaballos = Math.max(0, cantidadCaballos - cantidad);
    }

    public void reducirCabra(int cantidad) {
        cantidadCabras = Math.max(0, cantidadCabras - cantidad);
    }

    public void reducirCiervo(int cantidad) {
        cantidadCiervos = Math.max(0, cantidadCiervos - cantidad);
    }

    public void reducirLobo(int cantidad) {
        cantidadLobos = Math.max(0, cantidadLobos - cantidad);
    }

    public void reducirOruga(int cantidad) {
        cantidadOrugas = Math.max(0, cantidadOrugas - cantidad);
    }

    public void reducirOso(int cantidad) {
        cantidadOsos = Math.max(0, cantidadOsos - cantidad);
    }

    public void reducirOveja(int cantidad) {
        cantidadOvejas = Math.max(0, cantidadOvejas - cantidad);
    }

    public void reducirPuerco(int cantidad) {
        cantidadPuercos = Math.max(0, cantidadPuercos - cantidad);
    }

    public boolean confirmarSiHayCaballo() {
        return this.cantidadCaballos > 0;
    }

    public boolean confirmarSiHayCiervo() {
        return this.cantidadCiervos > 0;
    }

    public boolean confirmarSiHayOveja() {
        return this.cantidadOvejas > 0;
    }

    public boolean confirmarSiHayPuercos() {
        return this.cantidadPuercos > 0;
    }


    public boolean confirmarSiHayPlanta(){
        return this.cantidadPlantas > 0;
    }
public boolean confirmarSiHayCabra() {
    return this.cantidadCabras > 0;
}
    public void reducirPuercos(int cantidad) {
        this.cantidadPuercos = Math.max(0, this.cantidadPuercos - cantidad);
    }


    public void reducirPlanta( int cantidad){
        this.cantidadPlantas = Math.max(0, this.cantidadPlantas - cantidad);
    }

    // Métodos de confirmación para la existencia de animales
    public boolean confirmarSiHayZorro() {
        return cantidadZorros > 0;
    }

    public boolean confirmarSiHayConejo() {
        return cantidadConejos > 0;
    }

    public boolean confirmarSiHayPato() {
        return cantidadPatos > 0;
    }

    public boolean confirmarSiHayAguila() {
        return cantidadAguilas > 0;
    }

    public boolean confirmarSiHayBoas(){
        return cantidadBoas >0;
    }

    // Métodos para obtener las cantidades de animales
    public int getCantidadZorros() {
        return cantidadZorros;
    }

    public int getCantidadBoas() {
        return cantidadBoas;
    }

    public int getCantidadLobos() {
        return cantidadLobos;
    }

    public int getCantidadConejos() {
        return cantidadConejos;
    }
public int getCantidadOrugas(){
        return cantidadOrugas;

}

    public int getCantidadCaballos() {
        return cantidadCaballos;
    }
public int getCantidadCiervos(){
        return cantidadCiervos;
}
    public int getCantidadOvejas(){
        return cantidadOvejas;
    }
    public int getCantidadCabras(){
        return cantidadCabras;
    }
    public int getCantidadBufalos(){
        return cantidadBufalos;
    }
    public int getCantidadOsos(){
        return cantidadOsos;
    }

    public int getCantidadPatos() {
        return cantidadPatos;
    }

    public int getCantidadPuercos(){
        return cantidadPuercos;
    }
public int getCantidadRaton(){
        return cantidadRaton;
}
    public int getCantidadAguilas() {
        return cantidadAguilas;
    }
    public boolean confirmarSiHayRaton() {
        return this.cantidadRaton > 0;
    }
    public boolean confirmarSiHayOruga(){
        return this.cantidadOrugas >0;
    }

    public void reducirRaton(int cantidad) {
        // Lógica para reducir la cantidad de ratones en la ubicación
        this.cantidadRaton -= cantidad; // Asegúrate de que la propiedad cantidadDeRaton esté bien definida
    }

    public boolean confirmarSiHayBoa() {
        return this.cantidadBoas > 0;
    }

    public boolean confirmarSiHayEspecieCompatible(Animal animal) {
        Ubicacion ubicacion = getUbicacionActual();
        if (ubicacion == null) {
            System.out.println("Error: La ubicación actual es null.");
            return false;
        }
        List<Animal> animalesEnUbicacion = ubicacion.getAnimales();

        for (Animal otroAnimal : animalesEnUbicacion) {
            if (otroAnimal != animal && otroAnimal.getClass().isAssignableFrom(animal.getClass())) {
                return true;
            }
        }

        return false;
    }



}

