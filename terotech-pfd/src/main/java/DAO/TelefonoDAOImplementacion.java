package DAO;

import MODELO.Telefono;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TelefonoDAOImplementacion implements TelefonoDAO {

    // Ya no es necesario mantener una instancia de Conexion
    // Ya que la conexión será pasada como parámetro

    private static final String INSERT_TELEFONO = "INSERT INTO TELEFONOS (id_usuario, pais, localidad, numero, tipo_telefono) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_TELEFONOS_POR_USUARIO = "SELECT * FROM TELEFONOS WHERE id_usuario = ?";
    private static final String DELETE_TELEFONO = "DELETE FROM TELEFONOS WHERE id_telefono = ?";
    private static final String DELETE_TELEFONOS_POR_USUARIO = "DELETE FROM TELEFONOS WHERE id_usuario = ?";

    @Override
    public void agregarTelefono(Connection connection, Telefono telefono) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TELEFONO, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, telefono.getIdUsuario());
            preparedStatement.setString(2, telefono.getPais());
            preparedStatement.setString(3, telefono.getLocalidad());
            preparedStatement.setLong(4, telefono.getNumero());
            preparedStatement.setString(5, telefono.getTipoTelefono());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    telefono.setIdTelefono(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("No se pudo obtener el ID del teléfono insertado.");
                }
            }
        }
    }

    @Override
    public List<Telefono> obtenerTelefonosPorUsuario(Connection connection, int idUsuario) throws SQLException {
        List<Telefono> telefonos = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TELEFONOS_POR_USUARIO)) {
            preparedStatement.setInt(1, idUsuario);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    telefonos.add(new Telefono(
                            rs.getInt("id_telefono"),
                            rs.getInt("id_usuario"),
                            rs.getString("pais"),
                            rs.getString("localidad"),
                            rs.getLong("numero"),
                            rs.getString("tipo_telefono")
                    ));
                }
            }
        }
        return telefonos;
    }

    @Override
    public void eliminarTelefono(Connection connection, int idTelefono) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TELEFONO)) {
            preparedStatement.setInt(1, idTelefono);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void eliminarTelefonosPorUsuario(Connection connection, int idUsuario) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TELEFONOS_POR_USUARIO)) {
            preparedStatement.setInt(1, idUsuario);
            preparedStatement.executeUpdate();
        }
    }
}
