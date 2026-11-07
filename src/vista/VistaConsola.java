package vista;

import java.util.List;
import java.util.Scanner;
import modelo.ApiIntegracion;

public class VistaConsola {
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("\n=== INTEGRACIONES DE LA PLATAFORMA ===");
        System.out.println("1. Listar integraciones activas");
        System.out.println("2. Buscar integración por ID");
        System.out.println("3. Ordenar por latencia promedio");
        System.out.println("4. Simular checkout sobre una integración");
        System.out.println("0. Salir");
    }

    public int leerOpcion() {
        System.out.print("Opción: ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String pedirTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public void mostrarLista(String titulo, List<ApiIntegracion> lista) {
        System.out.println("\n--- " + titulo + " (" + lista.size() + ") ---");
        System.out.printf("%-8s | %-20s | %-16s | %-12s | %s%n", "ID", "Nombre", "Tipo", "Estado", "Latencia");
        lista.forEach(System.out::println);
    }

    public void mostrarMensaje(String mensaje) { System.out.println(mensaje); }

    public void mostrarMensajes(List<String> mensajes) { mensajes.forEach(System.out::println); }
}
