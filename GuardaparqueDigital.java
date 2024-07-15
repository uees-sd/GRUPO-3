import java.util.Scanner;
import java.util.ArrayList;

public class  GuardaparqueDigital {

    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<String[]> usuarios = new ArrayList<>();
    private static ArrayList<String[]> eventos = new ArrayList<>();
    private static int[][] bpp;

    public static void main(String[] args) {
        String saludo = "BIENVENIDO A GUARDAPARQUE DIGITAL BPP ESPOL";
        System.out.println(saludo);
        int opcion = 0;
        while (opcion != 4) {
            mostrarMenuPrincipal();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    manejarVoluntariado();
                    break;
                case 2:
                    registrarVisita();
                    break;
                case 3:
                    manejarEventos();
                    break;
                case 4:
                    System.out.println("Ha salido del programa. ¡Vuelva pronto!");
                    break;
                default:
                    System.out.println("Ingrese una opción válida.");
                    break;
            }
        }
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n\t1. Voluntariado");
        System.out.println("\t2. Visitantes");
        System.out.println("\t3. Eventos");
        System.out.println("\t4. Salir");
        System.out.print("\nElija una opción: ");
    }

    private static void manejarVoluntariado() {
        // Implementar la lógica para manejar el voluntariado
        // Aquí puedes usar métodos adicionales para las operaciones específicas del voluntariado
        System.out.println("Opción no implementada en este ejemplo.");
    }

    private static void registrarVisita() {
        // Implementar la lógica para registrar visitas
        System.out.println("Opción no implementada en este ejemplo.");
    }

    private static void manejarEventos() {
        // Implementar la lógica para manejar eventos
        System.out.println("Opción no implementada en este ejemplo.");
    }
}
