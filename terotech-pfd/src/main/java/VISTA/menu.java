package VISTA;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.io.Console;

public class menu {
    private Scanner scanner = new Scanner(System.in);

    public static void mostrarMenuPrincipal(boolean estadoBaseDeDatos) {
        String textoBD = (estadoBaseDeDatos) ? "Activo " : "Apagada";

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   Sistema de gestión de ASUR         ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║ 1. Gestión de Usuarios               ║");
        System.out.println("║ 2. Gestión de Perfiles               ║");
        System.out.println("║ 3. Gestión de Funcionalidades        ║");
        System.out.println("║ 4. Gestión de Pagos                  ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║ Estado de la Base de Datos: " + textoBD + "  ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    public boolean mostrarMenuInicioSesion() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║        Sistema de gestión de ASUR    ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║             Inicio de Sesión         ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║         1. Iniciar Sesión            ║");
        System.out.println("║         2. Salir                     ║");
        System.out.println("╚══════════════════════════════════════╝");

        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        boolean resultado = false;

        switch (opcion) {
            case 1:
                System.out.println("Iniciar Sesión seleccionado.");
                System.out.print("Ingrese su nombre de usuario: ");
                String usuario = scanner.nextLine();

                Console console = System.console();
                String contrasena;

                if (console != null) {
                    char[] passwordArray = console.readPassword("Ingrese su contraseña: ");
                    contrasena = new String(passwordArray);
                } else {
                    System.out.print("Ingrese su contraseña (no se oculta en esta configuración): ");
                    contrasena = scanner.nextLine();
                }

                System.out.println("Iniciando sesión...");
                try {
                    Thread.sleep(2000);
                    for (int i = 0; i < 50; ++i) System.out.println();
                    resultado = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                System.out.println("Saliendo del sistema...");
                resultado = false;
                break;
            default:
                System.out.println("Opción no válida.");
                resultado = false;
                break;
        }

        return resultado;
    }

    public void mostrarFormularioRegistro() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║         Registro de Usuario          ║");
        System.out.println("╚══════════════════════════════════════╝");

        String nombres = solicitarDato("Ingrese nombres");
        String apellidos = solicitarDato("Ingrese apellidos");
        String tipoDocumento = solicitarDato("Ingrese tipo de documento");
        String numeroDocumento = solicitarDato("Ingrese número de documento");

        String fechaStr = solicitarDato("Ingrese fecha de nacimiento (dd-MM-yyyy)");
        Date fechaNacimiento = cambiarFormatoFecha(fechaStr);

        String domicilio = solicitarDato("Ingrese domicilio");
        String email = solicitarDato("Ingrese email");
        String contrasena = solicitarDato("Ingrese contraseña");

        boolean dificultadAuditiva = solicitarBoolean("¿Tiene dificultad auditiva? (si/no)");
        boolean manejaLenguajeDeSenias = solicitarBoolean("¿Maneja lenguaje de señas? (si/no)");

        List<String> telefonos = solicitarTelefonos();

        mostrarResumenDatos(nombres, apellidos, tipoDocumento, numeroDocumento, fechaNacimiento,
                domicilio, email, contrasena, dificultadAuditiva, manejaLenguajeDeSenias, telefonos);

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║ Usuario registrado exitosamente.     ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    private String solicitarDato(String mensaje) {
        System.out.print("╔═ " + mensaje + ": ");
        return scanner.nextLine();
    }

    private boolean solicitarBoolean(String mensaje) {
        System.out.print("╔═ " + mensaje + ": ");
        String entrada = scanner.nextLine().toLowerCase().trim();
        return entrada.equals("si");
    }


    private List<String> solicitarTelefonos() {
        List<String> telefonos = new ArrayList<>();
        String telefono;
        scanner.nextLine(); // Limpiar el salto de línea

        do {
            System.out.print("╔═ Ingrese teléfono (deje vacío para terminar): ");
            telefono = scanner.nextLine();
            if (!telefono.isEmpty()) {
                telefonos.add(telefono);
            }
        } while (!telefono.isEmpty());

        return telefonos;
    }

    private Date cambiarFormatoFecha(String fechaStr) {
        try {
            return new java.text.SimpleDateFormat("dd-MM-yyyy").parse(fechaStr);
        } catch (Exception e) {
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║ Formato de fecha incorrecto.         ║");
            System.out.println("╚══════════════════════════════════════╝");
            return new Date();
        }
    }

    private void mostrarResumenDatos(String nombres, String apellidos, String tipoDocumento,
                                     String numeroDocumento, Date fechaNacimiento, String domicilio,
                                     String email, String contrasena, boolean dificultadAuditiva,
                                     boolean manejaLenguajeDeSenias, List<String> telefonos) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║ Resumen de Datos del Usuario         ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║ Nombres: " + nombres);
        System.out.println("║ Apellidos: " + apellidos);
        System.out.println("║ Tipo de Documento: " + tipoDocumento);
        System.out.println("║ Número de Documento: " + numeroDocumento);
        System.out.println("║ Fecha de Nacimiento: " + fechaNacimiento);
        System.out.println("║ Domicilio: " + domicilio);
        System.out.println("║ Email: " + email);
        System.out.println("║ Contraseña: " + contrasena);
        System.out.println("║ Dificultad Auditiva: " + dificultadAuditiva);
        System.out.println("║ Maneja Lenguaje de Señas: " + manejaLenguajeDeSenias);
        System.out.println("║ Teléfonos: " + telefonos);
        System.out.println("╚══════════════════════════════════════╝");
    }
}
