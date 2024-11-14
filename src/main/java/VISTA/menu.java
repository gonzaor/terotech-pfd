package VISTA;

public class menu {
  
    public static void mostrarMenu(String dbStatus) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   Sistema de gestión de ASUR         ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║ 1. Gestión de Usuarios               ║");
        System.out.println("║ 2. Gestión de Perfiles               ║");
        System.out.println("║ 3. Gestión de Funcionalidades        ║");
        System.out.println("║ 4. Gestión de Pagos                  ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║ Estado de la Base de Datos: " + dbStatus + "  ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

  
}
