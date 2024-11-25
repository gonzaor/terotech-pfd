package VISTA;

import DAO.UsuarioDAOImpl;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InicioSesion {
    private final Scanner scanner = new Scanner(System.in);

    public void iniciarSesion() {
        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        boolean loginExitoso = false;

        do {
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║          Inicio de Sesión            ║");
            System.out.println("╚══════════════════════════════════════╝");

            System.out.print("Ingrese su correo: ");
            String email = scanner.nextLine();

            if (!esCorreoValido(email)) {
                System.out.println("Formato de correo inválido. Intente nuevamente.");
                continue;
            }

            System.out.print("Ingrese su contraseña: ");
            String contrasena = scanner.nextLine();

            System.out.println("Verificando credenciales...");
            try {
                String tipoUsuario = usuarioDAO.login(email, contrasena);

                if (tipoUsuario != null) {
                    System.out.println("Inicio de sesión exitoso.");
                    loginExitoso = true;

                    switch (tipoUsuario.toLowerCase()) {
                        case "administrador" -> new SistemaAsur().mostrarMenuPrincipalAdmin();
                        case "socio" -> new SistemaAsur().mostrarMenuPrincipalSocio();
                        case "no-socio" -> new SistemaAsur().mostrarMenuPrincipalNoSocio();
                        default -> System.out.println("Tipo de usuario desconocido.");
                    }
                } else {
                    System.out.println("Credenciales incorrectas o cuenta inactiva. Intente nuevamente.");
                }
            } catch (SQLException e) {
                System.out.println("Error al verificar las credenciales: " + e.getMessage());
            }
        } while (!loginExitoso);
    }

    private boolean esCorreoValido(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.matches(regex, email);
    }
}
