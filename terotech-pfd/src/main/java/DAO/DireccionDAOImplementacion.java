package DAO;

import CONTROLADOR.Conexion;
import MODELO.Direccion;
import java.sql.*;

public class DireccionDAOImplementacion implements DireccionDAO {
    private Conexion conexion;

    public DireccionDAOImplementacion() {
        try {
            this.conexion = Conexion.obtenerInstancia();
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión", e);
        }
    }

    private static final String INSERT_DIRECCION = "INSERT INTO DIRECCIONES (calle, nro_puerta, apto) VALUES (?, ?, ?)";

    @Override
    public int agregarDireccion(Direccion direccion) throws SQLException {
        try (Connection connection = conexion.getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DIRECCION, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, direccion.getCalle());
            preparedStatement.setInt(2, direccion.getNroPuerta());
            preparedStatement.setString(3, direccion.getApto());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // id_direccion generado
                } else {
                    throw new SQLException("No se pudo obtener el ID de la dirección insertada.");
                }
            }
        }
    }
}
