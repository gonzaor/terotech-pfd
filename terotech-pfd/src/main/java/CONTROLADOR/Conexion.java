package CONTROLADOR;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    private static Conexion instanciaUnica;
    private Connection conexion;
    private String url =
            "jdbc:postgresql://localhost:5432/postgres";
    private String usuario = "postgres";
    private String contraseña = "1234";

    private Conexion() throws SQLException {
        this.conexion = DriverManager.getConnection(url, usuario,
                contraseña);
    }

    public static Conexion obtenerInstancia() throws
            SQLException {
        if (instanciaUnica == null) {
            instanciaUnica = new Conexion();
        }
        return instanciaUnica;
    }

    public Connection getConexion() {
        return conexion;
    }






}
