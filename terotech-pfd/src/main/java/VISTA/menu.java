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

    public int mostrarMenuInicioSesion() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║        Sistema de gestión de ASUR    ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║             Inicio de Sesión         ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║         1. Iniciar Sesión            ║");
        System.out.println("║         2. Pre-registro              ║");
        System.out.println("║         3. Salir                     ║");
        System.out.println("╚══════════════════════════════════════╝");

        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        int resultado = 0;

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
                    resultado = 1;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    Thread.sleep(2000);
                    for (int i = 0; i < 50; ++i) System.out.println();
                    this.mostrarFormularioRegistro();
                    resultado = 2;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida.");
                resultado = 0;
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
        String tipoDocumento = solicitarDato("Ingrese tipo de documento (CI, DNI, PASAPORTE U OTRO");
        String numeroDocumento = solicitarDato("Ingrese número de documento");

        String fechaStr = solicitarDato("Ingrese fecha de nacimiento (dd-MM-yyyy)");
        Date fechaNacimiento = cambiarFormatoFecha(fechaStr);

        // SOLICITUD DE DATOS DE DOMICILIO
        Domicilio domicilio = this.solicitarDatosDomicilio();

        String email = solicitarDato("Ingrese email");
        String contrasena = solicitarDato("Ingrese contraseña");

        boolean dificultadAuditiva = solicitarBoolean("¿Tiene dificultad auditiva? (si/no)");
        boolean manejaLenguajeDeSenias = solicitarBoolean("¿Maneja lenguaje de señas? (si/no)");

        // SOLICITUD DE DATOS DE CONTACTO
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

    private Domicilio solicitarDatosDomicilio(){
        Domicilio domicilio = new Domicilio();

        System.out.println("Ingrese datos de domicilio");
        String domicilioCalle = solicitarDato("Ingrese calle");
        this.domicilio.setCalle(domicilioCalle);
        String domicilioNumero = solicitarDato("Ingrese numero de puerta");
        this.domicilio.setNumero(domicilioNumero);
        String domicilioApto = solicitarDato("Si es necesesario, ingrese numero apartamento, de lo contrario continue");
        this.domicilio.setApto(domicilioApto);

        return domicilio;
    }

    private List<Telefono> solicitarTelefonos() {
            List<Telefono> telefonos = new ArrayList<>();
            Boolean agregarNuevoTel;
            do {
                Telefono nuevoTel = new Telefono();

                System.out.print("╔═ Ingrese pais: ");
                pais = scanner.nextLine();
                if (!pais.isEmpty()) {
                    nuevoTel.setPais(pais);
                }

                System.out.print("╔═ Ingrese localidad: ");
                localidad = scanner.nextLine();
                if (!localidad.isEmpty()) {
                    nuevoTel.setLocalidad(localidad);
                }

                System.out.print("╔═ Ingrese numero: ");
                numero = scanner.nextLine();
                if (!numero.isEmpty()) {
                    nuevoTel.setNumero(numero);
                }

                System.out.print("╔═ Ingrese tipo de telefono (celular/telefono fijo): ");
                tipoTelefono = scanner.nextLine();
                if (!tipoTelefono.isEmpty()) {
                    nuevoTel.setTipoTelefono(tipoTelefono);
                }

                telefonos.add(nuevoTel);

                if(telefonos.size() >= 1){
                    System.out.print("╔═ Desea ingresar otro telefono? Si/No: ");
                    ingresarTelefono = scanner.nextLine();
                    if(ingresarTelefono == 'Si'){
                        agregarNuevoTel = true;
                    } else{
                        agregarNuevoTel = false;
                    }
                }
            } while (agregarNuevoTel);

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
        System.out.println("║ Domicilio: " + domicilio.toString();
        System.out.println("║ Email: " + email);
        System.out.println("║ Contraseña: " + contrasena);
        System.out.println("║ Dificultad Auditiva: " + dificultadAuditiva);
        System.out.println("║ Maneja Lenguaje de Señas: " + manejaLenguajeDeSenias);
        System.out.println("║ Teléfonos: " + telefonos.toString());
        System.out.println("╚══════════════════════════════════════╝");
        System.exit(0);


    }
}
