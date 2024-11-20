import CONTROLADOR.Conexion;
import VISTA.menu;
import java.util.Scanner;
import java.sql.SQLException;

public class main {

    public static void main(String[] args) {
        try {


            menu men = new menu();
            Scanner sc = new Scanner(System.in);




            switch(men.mostrarMenuInicioSesion()){

                case 1:
                    Conexion conexion = Conexion.obtenerInstancia();
                    boolean estadoBaseDeDatos = (conexion.getConexion() != null);

                    boolean continuar = true;
                    while (continuar) {
                        men.mostrarMenuPrincipal(estadoBaseDeDatos);

                        System.out.print("Seleccione una opción en el menú principal: ");
                        int opcion = sc.nextInt();
                        sc.nextLine();

                        switch (opcion) {
                            case 1:
                                men.mostrarFormularioRegistro();
                                break;
                            case 2:
                                System.out.println("Función de gestión de perfiles (no implementada).");
                                break;
                            case 3:
                                System.out.println("Función de gestión de funcionalidades (no implementada).");
                                break;
                            case 4:
                                System.out.println("Función de gestión de pagos (no implementada).");
                                break;
                            case 5:
                                System.out.println("Saliendo del sistema...");
                                continuar = false;
                                break;
                            default:
                                System.out.println("Opción no válida. Intente nuevamente.");
                                break;
                        }
                    }

                    sc.close();


                    break;

                case 2:




                    break;





            }




          /*
*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
