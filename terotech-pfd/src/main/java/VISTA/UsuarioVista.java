package VISTA;

import java.util.Scanner;
import java.util.regex.Pattern;
import MODELO.*;

public class UsuarioVista {
    private Scanner sc = new Scanner(System.in);

    public void iniciarSesion() {
        boolean loginExitoso = false; // Inicializa el estado de login

        do {
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║          Inicio de Sesión            ║");
            System.out.println("╚══════════════════════════════════════╝");

            String email;
            do {
                System.out.print("Ingrese su correo: ");
                email = sc.nextLine();

                if (!esCorreoValido(email)) {
                    System.out.println("Formato de correo inválido. Intente nuevamente.");
                }
            } while (!esCorreoValido(email));

            System.out.print("Ingrese su contraseña: ");
            String contrasena = sc.nextLine();

            Usuario user = new Usuario();
            user.setEmail(email);
            user.setContrasena(contrasena);

            System.out.println("Verificando credenciales...");
            try {
                Thread.sleep(1500); // Simula un proceso
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restaura el estado de interrupción
                System.out.println("Error al procesar la solicitud. Intente nuevamente.");
                continue;
            }

            String rol = user.login();
            switch (rol) {
                case "admin":
                case "socio":
                case "no-socio":
                    System.out.println("╔══════════════════════════════════════╗");
                    System.out.println("║       Bienvenido al sistema ASUR     ║");
                    System.out.println("╚══════════════════════════════════════╝");
                    loginExitoso = true;
                    break;
                default:
                    System.out.println("╔══════════════════════════════════════╗");
                    System.out.println("║   Credenciales incorrectas o cuenta  ║");
                    System.out.println("║   inactiva. Intente nuevamente.      ║");
                    System.out.println("╚══════════════════════════════════════╝");

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("Error al procesar la solicitud.");
                    }
                    System.out.print("\n".repeat(50));
                    break;
            }
        } while (!loginExitoso);
    }


    private boolean esCorreoValido(String email) {
        // Expresión regular para validar correos electrónicos
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.matches(regex, email);
    }




}
