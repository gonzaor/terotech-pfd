package VISTA;

import java.util.Scanner;

public class SistemaAsur {
    private final Scanner scanner = new Scanner(System.in);


    public void iniciarSistema() {
        boolean exit = false;

        while (!exit) {
            System.out.println("╔══════════════════════════════════════════════╗");
            System.out.println("║           Sistema de Gestión ASUR            ║");
            System.out.println("╠══════════════════════════════════════════════╣");
            System.out.println("║ 1. Iniciar Sesión                            ║");
            System.out.println("║ 2. Pre-registro                              ║");
            System.out.println("║ 3. Salir                                     ║");
            System.out.println("╚══════════════════════════════════════════════╝");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> {
                    InicioSesion inicioSesion = new InicioSesion();
                    inicioSesion.iniciarSesion();
                }
                case 2 -> mostrarFormularioRegistro();
                case 3 -> {
                    System.out.println("Saliendo del sistema...");
                    exit = true;
                }
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    public void mostrarFormularioRegistro() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║         Registro de Usuario          ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("Funcionalidad en desarrollo.");
    }

    public void mostrarMenuPrincipalAdmin() {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   Sistema de gestión de ASUR - ADMIN         ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║ 1. Gestión de Usuarios                       ║");
        System.out.println("║ 2. Gestión de Perfiles                       ║");
        System.out.println("║ 3. Gestión de Funcionalidades                ║");
        System.out.println("║ 4. Gestión de Pagos                          ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    public void mostrarMenuPrincipalSocio() {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   Sistema de gestión de ASUR - SOCIO         ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║ 1. Usuario                                   ║");
        System.out.println("║ 2. Pagos                                     ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    public void mostrarMenuPrincipalNoSocio() {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   Sistema de gestión de ASUR - NO SOCIO      ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║ 1. Usuario                                   ║");
        System.out.println("║ 2. Pagos                                     ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }
}
