package Proyecto2;
import java.util.Scanner;

// Clase principal donde se desarrolla un menú para que el usuario interactúe con la simulación
public class Main {
    public static void main(String[] args) {

        SimuladorDeLaIsla simulacion = new SimuladorDeLaIsla();

        Scanner scanner = new Scanner(System.in);
        String opcion;

        while (true) {
            // Menú interactivo para el usuario
            System.out.println("********************************************");
            System.out.println("*************  Hola usuario :)  ************");
            System.out.println("************* Bienvenido a mi Isla *********");
            System.out.println("🐖🐻🐍🐇🦆🐁🐐 🐴🦊🦅 🐑🐛🌿🐃 🦌");
            System.out.println("En esta isla habitan muchas especies. Hoy tienes");
            System.out.println("la oportunidad de ver cómo funciona la cadena alimenticia.");
            System.out.println("Escribe \"simulación\" para ver cómo los animales buscan");
            System.out.println("comida en la isla, o escribe \"ya me aburrí\" para salir.");
            System.out.print("Escribe una opción ---> ");
            opcion = scanner.nextLine();

            if (opcion.equalsIgnoreCase("simulación")) {
                // hacemos la simulación
                simulacion.iniciarIsla();
                volverASimular(scanner, simulacion);
            } else if (opcion.equalsIgnoreCase("ya me aburrí")) {
                // Si el usuario decide salir
                System.out.println("Hasta luego :)");
                break;
            } else {
                // Validamos opciones invalidas
                System.out.println("Opción inválida :( Intenta nuevamente.");
            }
        }
        scanner.close(); // Cerramos el Scanner
    }

    // Metodo para hacer otra simulación
    private static void volverASimular(Scanner scanner, SimuladorDeLaIsla simulacion) {
        while (true) {
            System.out.println("********************************************************");
            System.out.println("************** ¡La simulación se completó! **************");
            System.out.println("********************************************************");
            System.out.print("Escribe \"otra vez\" para ejecutar otra simulación o \"ya me aburrí\" para finalizar el programa :) : ");
            String opcion = scanner.nextLine();

            if (opcion.equalsIgnoreCase("otra vez")) {
                // Volvemos a ejecutar la simulación
                simulacion.iniciarIsla();
            } else if (opcion.equalsIgnoreCase("ya me aburrí")) {
                // Si el usuario decide salir
                System.out.println("Hasta luego :)");
                break;
            } else {
                               System.out.println("Opción inválida :( Intenta nuevamente.");
            }
        }
    }
}
