package DAO;

import MODELO.Direccion;
import java.sql.*;

public class DireccionDAOImplementacion implements DireccionDAO {

    // Ya no es necesario mantener una instancia de Conexion
    // Ya que la conexión será pasada como parámetro

    private static final String INSERT_DIRECCION = "INSERT INTO DIRECCIONES (calle, nro_puerta, apto) VALUES (?, ?, ?)";
    private static final String SELECT_DIRECCION = "SELECT * FROM DIRECCIONES WHERE id_direccion = ?";
    private static final String UPDATE_DIRECCION = "UPDATE DIRECCIONES SET calle = ?, nro_puerta = ?, apto = ? WHERE id_direccion = ?";
    private static final String DELETE_DIRECCION = "DELETE FROM DIRECCIONES WHERE id_direccion = ?";

    @Override
    public Direccion agregarDireccion(Connection connection, Direccion direccion) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DIRECCION, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, direccion.getCalle());
            preparedStatement.setInt(2, direccion.getNroPuerta());
            preparedStatement.setString(3, direccion.getApto());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Direccion retornoDir = new Direccion();
                    retornoDir.setIdDireccion(generatedKeys.getInt(1));
                    retornoDir.setCalle(direccion.getCalle());
                    retornoDir.setNroPuerta(direccion.getNroPuerta());
                    retornoDir.setApto(direccion.getApto());
                    return retornoDir;
                } else {
                    throw new SQLException("No se pudo obtener el ID de la dirección insertada.");
                }
            }
        }
    }

    @Override
    public Direccion obtenerDireccion(Connection connection, int idDireccion) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DIRECCION)) {
            preparedStatement.setInt(1, idDireccion);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return new Direccion(
                            rs.getInt("id_direccion"),
                            rs.getString("calle"),
                            rs.getInt("nro_puerta"),
                            rs.getString("apto")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public void actualizarDireccion(Connection connection, Direccion direccion) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DIRECCION)) {
            preparedStatement.setString(1, direccion.getCalle());
            preparedStatement.setInt(2, direccion.getNroPuerta());
            preparedStatement.setString(3, direccion.getApto());
            preparedStatement.setInt(4, direccion.getIdDireccion());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void eliminarDireccion(Connection connection, int idDireccion) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DIRECCION)) {
            preparedStatement.setInt(1, idDireccion);
            preparedStatement.executeUpdate();
        }
    }
}
