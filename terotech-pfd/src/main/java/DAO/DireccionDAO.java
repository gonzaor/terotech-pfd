package DAO;

import MODELO.Direccion;
import java.sql.Connection;
import java.sql.SQLException;

public interface DireccionDAO {
    Direccion agregarDireccion(Connection connection, Direccion direccion) throws SQLException;
    Direccion obtenerDireccion(Connection connection, int idDireccion) throws SQLException;
    void actualizarDireccion(Connection connection, Direccion direccion) throws SQLException;
    void eliminarDireccion(Connection connection, int idDireccion) throws SQLException;
}
