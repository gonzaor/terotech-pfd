package DAO;

import MODELO.Telefono;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface TelefonoDAO {
    void agregarTelefono(Connection connection, Telefono telefono) throws SQLException;
    List<Telefono> obtenerTelefonosPorUsuario(Connection connection, int idUsuario) throws SQLException;
    void eliminarTelefono(Connection connection, int idTelefono) throws SQLException;
    void eliminarTelefonosPorUsuario(Connection connection, int idUsuario) throws SQLException;
}
