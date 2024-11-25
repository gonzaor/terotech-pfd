package VISTA;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.io.Console;
import MODELO.*;

public class menu {
    private Scanner scanner = new Scanner(System.in);

    public static void mostrarMenuPrincipalAdmin() {

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   Sistema de gestión de ASUR - ADMIN         ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║ 1. Gestión de Usuarios                       ║");
        System.out.println("║ 2. Gestión de Perfiles                       ║");
        System.out.println("║ 3. Gestión de Funcionalidades                ║");
        System.out.println("║ 4. Gestión de Pagos                          ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║ Estado de la Base de Datos:   Conectada      ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    public static void mostrarMenuPrincipalSocio() {

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   Sistema de gestión de ASUR - SOCIO         ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║ 1. Usuario                                   ║");
        System.out.println("║ 4. Pagos                                     ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║ Estado de la Base de Datos:   Conectada      ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    public static void mostrarMenuPrincipalNoSocio() {

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   Sistema de gestión de ASUR -  NO SOCIO     ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║ 1. Gestión de Usuarios                       ║");
        System.out.println("║ 2. Gestión de Perfiles                       ║");
        System.out.println("║ 3. Gestión de Funcionalidades                ║");
        System.out.println("║ 4. Gestión de Pagos                          ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║ Estado de la Base de Datos:   Conectada      ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    public int mostrarMenuInicioSesion(boolean estadoBaseDeDatos) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║        Sistema de gestión de ASUR    ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║           Inicio de Sesión           ║");
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
                System.out.print("Ingrese su email: ");
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

       //             UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
//                    ACA LOGIN
//                    Usuario logingResult = usuarioDAO.login(usuario, contrasena);

//                    if(logingResult != null) {
//                        System.out.println("Bienvenido " + logingResult.getNombres() + "!");
//                        tipoUsuario tipo = logingResult.getTipoDeUsuario();
//                        if(tipo == tipoUsuario.ADMINISTRADOR){
//                            mostrarMenuPrincipalAdmin();
//                        } else if(tipo == tipoUsuario.SOCIO){
//                            mostrarMenuPrincipalSocio();
//                        } else if(tipo == tipoUsuario.NO_SOCIO){
//                            mostrarMenuPrincipalNoSocio();
//                        }
//                    } else {
//                        System.out.println("No se encontraron usuarios registrados con esa combinacion de documento y contraseña");
//                        System.out.println("Intente nuevamente o comuniquese con un administrador.");
//                        this.mostrarMenuInicioSesion(true);
//                    }
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
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║  1.CI   2.DNI   3.PASAPORTE  4.OTRO  ║");
        System.out.println("╚══════════════════════════════════════╝");
        int tipoDocumentoInput = solicitarInt("Ingrese tipo de documento");
        String tipoDocumento = null;
        switch (tipoDocumentoInput) {
            case 1:
                tipoDocumento = "CI";
                break;
            case 2:
                tipoDocumento = "DNI";
                break;
            case 3:
                tipoDocumento = "PASAPORTE";
                break;
            case 4:
                tipoDocumento = "OTRO";
                break;
            default:
        }
        String numeroDocumento = solicitarDato("Ingrese número de documento");

        String fechaStr = solicitarDato("Ingrese fecha de nacimiento (dd-MM-yyyy)");
        Date fechaNacimiento = cambiarFormatoFecha(fechaStr);

        Direccion domicilio = solicitarDatosDomicilio();
        String email = solicitarDato("Ingrese email");
        String contrasena = solicitarDato("Ingrese contraseña");

        boolean dificultadAuditiva = solicitarBoolean("¿Tiene dificultad auditiva? (si/no)");
        boolean manejaLenguajeDeSenias = solicitarBoolean("¿Maneja lenguaje de señas? (si/no)");

        List<Telefono> telefonos = solicitarTelefonos();

        mostrarResumenDatos(nombres, apellidos, tipoDocumento, numeroDocumento, fechaNacimiento,
                domicilio, email, contrasena, dificultadAuditiva, manejaLenguajeDeSenias, telefonos);

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║ Usuario registrado exitosamente.     ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

//    TERMINAR ESTE METODO Y TESTEAR LOGIN
    private Direccion solicitarDatosDomicilio() {
        Direccion nuevaDireccion = new Direccion();
//      Solicitamos los datos de domicilio
        System.out.println("A conticuacion ingrese sus datos de domicilio.");
        String direccionCalle = solicitarDato("Ingrese calle principal");
        int direccionNumero = solicitarInt("Ingrese numero de puerta");
        String dirrecionApto = solicitarDato("De ser necesario, ingrese numero de apartamento de lo contrario siga sin ingresar datos");
        nuevaDireccion.setCalle(direccionCalle);
        nuevaDireccion.setNroPuerta(direccionNumero);

        if (!dirrecionApto.isEmpty()) {
            nuevaDireccion.setApto(dirrecionApto);
        } else {
            nuevaDireccion.setApto(null);
        }
        return nuevaDireccion;
    }

    private String solicitarDato(String mensaje) {
        System.out.print("╔═ " + mensaje + ": ");
        return scanner.nextLine();
    }

    private int solicitarInt(String mensaje) {
        System.out.print("╔═ " + mensaje + ": ");
        return scanner.nextInt();
    }

    private boolean solicitarBoolean(String mensaje) {
        System.out.print("╔═ " + mensaje + ": ");
        String entrada = scanner.nextLine().toLowerCase().trim();
        return entrada.equals("si");
    }

    private List<Telefono> solicitarTelefonos() {
        List<Telefono> telefonos = new ArrayList<>();
        Boolean agregarNuevoTel = true;
        do {
            Telefono nuevoTel = new Telefono();

            System.out.print("╔═ Ingrese pais: ");
            String pais = scanner.nextLine();
            if (!pais.isEmpty()) {
                nuevoTel.setPais(pais);
            }

            System.out.print("╔═ Ingrese localidad: ");
            String localidad = scanner.nextLine();
            if (!localidad.isEmpty()) {
                nuevoTel.setLocalidad(localidad);
            }

            System.out.print("╔═ Ingrese tipo de telefono (celular/telefono fijo): ");
            String tipoTelefono = scanner.nextLine();
            if (!tipoTelefono.isEmpty()) {
                nuevoTel.setTipoTelefono(tipoTelefono);
            }

            System.out.print("╔═ Ingrese numero: ");
            Long numero = null;
            numero = scanner.nextLong();
            if (numero != null) {
                nuevoTel.setNumero(numero);
            }

            telefonos.add(nuevoTel);

            if(telefonos.size() >= 1){
                System.out.print("╔═ Desea ingresar otro telefono? Si/No: ");
                String ingresarTelefono = scanner.nextLine();
                if(ingresarTelefono == "Si"){
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
            return new SimpleDateFormat("dd-MM-yyyy").parse(fechaStr);
        } catch (Exception e) {
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║ Formato de fecha incorrecto.         ║");
            System.out.println("╚══════════════════════════════════════╝");
            return new Date();
        }
    }

    private void mostrarResumenDatos(String nombres, String apellidos, String tipoDocumento,
                                     String numeroDocumento, Date fechaNacimiento, Direccion domicilio,
                                     String email, String contrasena, boolean dificultadAuditiva,
                                     boolean manejaLenguajeDeSenias, List<Telefono> telefonos) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║ Resumen de Datos del Usuario         ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║ Nombres: " + nombres);
        System.out.println("║ Apellidos: " + apellidos);
        System.out.println("║ Tipo de Documento: " + tipoDocumento);
        System.out.println("║ Número de Documento: " + numeroDocumento);
        System.out.println("║ Fecha de Nacimiento: " + fechaNacimiento);
        System.out.println("║ Domicilio: " + domicilio.toString());
        System.out.println("║ Email: " + email);
        System.out.println("║ Contraseña: " + contrasena);
        System.out.println("║ Dificultad Auditiva: " + dificultadAuditiva);
        System.out.println("║ Maneja Lenguaje de Señas: " + manejaLenguajeDeSenias);
        System.out.println("║ Teléfonos:                           ║");
        if(telefonos != null) {
          for (Telefono telefono : telefonos) {
              System.out.println("║ 1. " + telefono.toString());
          }
        }
        System.out.println("╚══════════════════════════════════════╝");
        System.exit(0);


    }
}
