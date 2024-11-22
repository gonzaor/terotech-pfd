package DAO;

import CONTROLADOR.Conexion;
import MODELO.Telefono;
import java.sql.*;

public class TelefonoDAOImplementacion implements TelefonoDAO {
    private Conexion conexion;

    public TelefonoDAOImplementacion() {
        try {
            this.conexion = Conexion.obtenerInstancia();
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión", e);
        }
    }

    private static final String INSERT_TELEFONO = "INSERT INTO TELEFONOS (id_usuario, pais, localidad, numero, tipo_telefono) VALUES (?, ?, ?, ?, ?)";

    @Override
    public void agregarTelefono(Telefono telefono) throws SQLException {
        try (Connection connection = conexion.getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TELEFONO)) {
            preparedStatement.setInt(1, telefono.getIdUsuario());
            preparedStatement.setString(2, telefono.getPais());
            preparedStatement.setString(3, telefono.getLocalidad());
            preparedStatement.setLong(4, telefono.getNumero());
            preparedStatement.setString(5, telefono.getTipoTelefono());
            preparedStatement.executeUpdate();
        }
    }
}
