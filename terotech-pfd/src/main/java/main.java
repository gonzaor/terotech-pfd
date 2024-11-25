import DAO.UsuarioDAOImpl;
import MODELO.Direccion;
import MODELO.Telefono;
import MODELO.Usuario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("╔══════════════════════════════════════════════╗");
            System.out.println("║           Sistema de Gestión ASUR            ║");
            System.out.println("╠══════════════════════════════════════════════╣");
            System.out.println("║ 1. Gestión de Usuarios                       ║");
            System.out.println("║ 2. Salir                                     ║");
            System.out.println("╚══════════════════════════════════════════════╝");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> gestionarUsuarios();
                case 2 -> {
                    System.out.println("Saliendo del sistema...");
                    exit = true;
                }
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private static void gestionarUsuarios() {
        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║              Gestión de Usuarios             ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║ 1. Listar Usuarios                           ║");
        System.out.println("║ 2. Agregar Usuario                           ║");
        System.out.println("║ 3. Modificar Usuario                         ║");
        System.out.println("║ 4. Eliminar Usuario                          ║");
        System.out.println("║ 5. Volver al Menú Principal                  ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (opcion) {
                case 1 -> listarUsuarios(usuarioDAO);
                case 2 -> agregarUsuario(usuarioDAO);
                case 3 -> modificarUsuario(usuarioDAO);
                case 4 -> eliminarUsuario(usuarioDAO);
                case 5 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } catch (Exception e) {
            System.out.println("Error al gestionar usuarios: " + e.getMessage());
        }
    }

    private static void listarUsuarios(UsuarioDAOImpl usuarioDAO) throws Exception {
        List<Usuario> usuarios = usuarioDAO.listAll();

        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            // Calculamos el ancho máximo para cada columna
            int maxId = "ID".length();
            int maxNombre = "Nombre".length();
            int maxApellido = "Apellido".length();
            int maxTipo = "Tipo".length();
            int maxDocumento = "Documento".length();
            int maxEmail = "Email".length();

            for (Usuario user : usuarios) {
                maxId = Math.max(maxId, String.valueOf(user.getId()).length());
                maxNombre = Math.max(maxNombre, user.getNombres().length());
                maxApellido = Math.max(maxApellido, user.getApellidos().length());
                maxTipo = Math.max(maxTipo, user.getTipoDocumento().length());
                maxDocumento = Math.max(maxDocumento, String.valueOf(user.getNumeroDocumento()).length());
                maxEmail = Math.max(maxEmail, user.getEmail().length());
            }

            // Imprimimos la cabecera con ancho dinámico
            String format = "║ %-" + maxId + "s ║ %-" + maxNombre + "s ║ %-" + maxApellido + "s ║ %-" +
                    maxTipo + "s ║ %-" + maxDocumento + "s ║ %-" + maxEmail + "s ║%n";

            String separator = "╠" + "═".repeat(maxId + 2) + "╬" + "═".repeat(maxNombre + 2) + "╬" +
                    "═".repeat(maxApellido + 2) + "╬" + "═".repeat(maxTipo + 2) + "╬" +
                    "═".repeat(maxDocumento + 2) + "╬" + "═".repeat(maxEmail + 2) + "╣";

            System.out.println("╔" + "═".repeat(maxId + 2) + "╦" + "═".repeat(maxNombre + 2) + "╦" +
                    "═".repeat(maxApellido + 2) + "╦" + "═".repeat(maxTipo + 2) + "╦" +
                    "═".repeat(maxDocumento + 2) + "╦" + "═".repeat(maxEmail + 2) + "╗");
            System.out.printf(format, "ID", "Nombre", "Apellido", "Tipo", "Documento", "Email");
            System.out.println(separator);

            // Imprimimos los datos de los usuarios
            for (Usuario user : usuarios) {
                System.out.printf(format,
                        user.getId(),
                        user.getNombres(),
                        user.getApellidos(),
                        user.getTipoDocumento(),
                        user.getNumeroDocumento(),
                        user.getEmail());
            }

            System.out.println("╚" + "═".repeat(maxId + 2) + "╩" + "═".repeat(maxNombre + 2) + "╩" +
                    "═".repeat(maxApellido + 2) + "╩" + "═".repeat(maxTipo + 2) + "╩" +
                    "═".repeat(maxDocumento + 2) + "╩" + "═".repeat(maxEmail + 2) + "╝");
        }
    }

    private static void agregarUsuario(UsuarioDAOImpl usuarioDAO) throws Exception {
        Usuario usuario = new Usuario();

        System.out.print("Ingrese nombre: ");
        usuario.setNombres(scanner.nextLine());

        System.out.print("Ingrese apellido: ");
        usuario.setApellidos(scanner.nextLine());

        String tipoDocumento;
        do {
            System.out.print("Ingrese tipo de documento (CI/DNI/Pasaporte): ");
            tipoDocumento = scanner.nextLine();
            if (!tipoDocumento.equalsIgnoreCase("CI") &&
                    !tipoDocumento.equalsIgnoreCase("DNI") &&
                    !tipoDocumento.equalsIgnoreCase("Pasaporte")) {
                System.out.println("Tipo de documento inválido. Intente nuevamente.");
            } else {
                break;
            }
        } while (true);
        usuario.setTipoDocumento(tipoDocumento);

        int numeroDocumento;
        do {
            System.out.print("Ingrese número de documento: ");
            numeroDocumento = scanner.nextInt();
            scanner.nextLine();
            if (usuario.getTipoDocumento().equalsIgnoreCase("CI") && !validarCedulaUruguay(numeroDocumento)) {
                System.out.println("Número de cédula inválido para Uruguay. Intente nuevamente.");
            } else {
                break;
            }
        } while (true);
        usuario.setNumeroDocumento(numeroDocumento);

        System.out.print("Ingrese fecha de nacimiento (dd-MM-yyyy): ");
        usuario.setFechaNacimiento(parseDate(scanner.nextLine()));

        Direccion direccion = new Direccion();
        System.out.print("Ingrese calle: ");
        direccion.setCalle(scanner.nextLine());
        System.out.print("Ingrese número de puerta: ");
        direccion.setNroPuerta(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Ingrese apartamento (opcional): ");
        direccion.setApto(scanner.nextLine());
        usuario.setDomicilio(direccion);

        List<Telefono> telefonos = new ArrayList<>();
        boolean agregarOtro;
        do {
            Telefono telefono = new Telefono();
            System.out.print("Ingrese país del teléfono: ");
            telefono.setPais(scanner.nextLine());
            System.out.print("Ingrese localidad del teléfono: ");
            telefono.setLocalidad(scanner.nextLine());
            System.out.print("Ingrese tipo de teléfono (celular/fijo): ");
            telefono.setTipoTelefono(scanner.nextLine());
            System.out.print("Ingrese número de teléfono: ");
            telefono.setNumero(scanner.nextLong());
            scanner.nextLine();
            telefonos.add(telefono);

            System.out.print("¿Desea agregar otro teléfono? (true/false): ");
            agregarOtro = scanner.nextBoolean();
            scanner.nextLine();
        } while (agregarOtro);
        usuario.setTelefonos(telefonos);

        String email;
        do {
            System.out.print("Ingrese email: ");
            email = scanner.nextLine();
            if (!validarEmail(email)) {
                System.out.println("Email inválido. Intente nuevamente.");
            } else {
                break;
            }
        } while (true);
        usuario.setEmail(email);

        String contrasena;
        do {
            System.out.print("Ingrese contraseña (mínimo 8 caracteres, al menos una mayúscula y una minúscula): ");
            contrasena = scanner.nextLine();
            if (!validarContrasena(contrasena)) {
                System.out.println("Contraseña inválida. Intente nuevamente.");
            } else {
                break;
            }
        } while (true);
        usuario.setContrasena(contrasena);

        String tipoUsuario;
        do {
            System.out.print("Tipo de Usuario (No-socio/Socio/Administrativo): ");
            tipoUsuario = scanner.nextLine();
            if (!tipoUsuario.equalsIgnoreCase("No-socio") &&
                    !tipoUsuario.equalsIgnoreCase("Socio") &&
                    !tipoUsuario.equalsIgnoreCase("Administrativo")) {
                System.out.println("Tipo de usuario inválido. Intente nuevamente.");
            } else {
                break;
            }
        } while (true);
        usuario.setTipoUsuario(tipoUsuario);

        usuarioDAO.create(usuario);
        System.out.println("Usuario agregado correctamente.");
    }

    private static void modificarUsuario(UsuarioDAOImpl usuarioDAO) throws Exception {
        System.out.print("Ingrese número de documento del usuario a modificar: ");
        int numeroDocumento = scanner.nextInt();
        scanner.nextLine();

        Usuario usuario = usuarioDAO.readByDocumento(numeroDocumento);
        if (usuario != null) {
            System.out.print("Ingrese nuevo nombre (actual: " + usuario.getNombres() + "): ");
            usuario.setNombres(scanner.nextLine());

            System.out.print("Ingrese nuevo apellido (actual: " + usuario.getApellidos() + "): ");
            usuario.setApellidos(scanner.nextLine());

            usuarioDAO.update(usuario);
            System.out.println("Usuario modificado correctamente.");
            listarUsuarios(usuarioDAO); // Lista usuarios después de modificar
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    private static void eliminarUsuario(UsuarioDAOImpl usuarioDAO) throws Exception {
        System.out.print("Ingrese número de documento del usuario a eliminar: ");
        int numeroDocumento = scanner.nextInt();
        scanner.nextLine();

        Usuario usuario = usuarioDAO.readByDocumento(numeroDocumento);
        if (usuario != null) {
            usuarioDAO.deactivate(usuario.getId());
            System.out.println("Usuario eliminado correctamente.");
            listarUsuarios(usuarioDAO); // Lista usuarios después de eliminar
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    private static boolean validarCedulaUruguay(int ci) {
        int[] pesos = {2, 9, 8, 7, 6, 3, 4};
        int suma = 0;
        int digitoVerificador = ci % 10;
        ci /= 10;
        for (int peso : pesos) {
            suma += (ci % 10) * peso;
            ci /= 10;
        }
        int resto = suma % 10;
        int calculado = resto == 0 ? 0 : 10 - resto;
        return calculado == digitoVerificador;
    }

    private static boolean validarEmail(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.matches(regex, email);
    }

    private static boolean validarContrasena(String contrasena) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        return Pattern.matches(regex, contrasena);
    }

    private static Date parseDate(String fecha) {
        try {
            return new SimpleDateFormat("dd-MM-yyyy").parse(fecha);
        } catch (ParseException e) {
            System.out.println("Formato de fecha inválido. Usando la fecha actual.");
            return new Date();
        }
    }
}
