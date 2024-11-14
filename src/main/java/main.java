import CONTROLADOR.Conexion;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.SQLException;



public class main {

    public static void main(String[] args) {
         try {
            Conexion conexion =
                    Conexion.obtenerInstancia();
            System.out.println("Conexión a la base de datos establecida: " + (conexion.getConexion() != null));
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
