package DAO;

import CONTROLADOR.Conexion;
import MODELO.SocioSubcomision;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SocioSubcomisionDAOIMPLEMENTACION implements SocioSubcomisionDAO {

    private static final String INSERT_SOCIO_SUBCOMISION = "INSERT INTO SOCIO_SUBCOMISION (id_socio, id_subcom) VALUES (?, ?)";
    private static final String DELETE_SOCIO_SUBCOMISION = "DELETE FROM SOCIO_SUBCOMISION WHERE id_socio = ? AND id_subcom = ?";
    private static final String SELECT_SUBCOMISIONES_POR_SOCIO = "SELECT * FROM SOCIO_SUBCOMISION WHERE id_socio = ?";
    private static final String SELECT_SOCIOS_POR_SUBCOMISION = "SELECT * FROM SOCIO_SUBCOMISION WHERE id_subcom = ?";

    private Conexion conexion;

    public SocioSubcomisionDAOIMPLEMENTACION() {
        try {
            conexion = Conexion.obtenerInstancia();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Agregar relación entre socio y subcomisión
    @Override
    public void agregarSubcomisionASocio(int idSocio, int idSubcom) throws SQLException {
        try (Connection connection = conexion.getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SOCIO_SUBCOMISION)) {
            preparedStatement.setInt(1, idSocio);
            preparedStatement.setInt(2, idSubcom);
            preparedStatement.executeUpdate();
        }
    }

    // Eliminar la relación entre un socio y una subcomisión
    @Override
    public void eliminarSubcomisionDeSocio(int idSocio, int idSubcom) throws SQLException {
        try (Connection connection = conexion.getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SOCIO_SUBCOMISION)) {
            preparedStatement.setInt(1, idSocio);
            preparedStatement.setInt(2, idSubcom);
            preparedStatement.executeUpdate();
        }
    }

    // Obtener las subcomisiones asociadas a un socio
    @Override
    public List<SocioSubcomision> obtenerSubcomisionesDeSocio(int idSocio) throws SQLException {
        List<SocioSubcomision> subcomisiones = new ArrayList<>();
        try (Connection connection = conexion.getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUBCOMISIONES_POR_SOCIO)) {
            preparedStatement.setInt(1, idSocio);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                SocioSubcomision socioSubcomision = new SocioSubcomision(
                        rs.getInt("id_socio"),
                        rs.getInt("id_subcom")
                );
                subcomisiones.add(socioSubcomision);
            }
        }
        return subcomisiones;
    }

    // Obtener los socios asociados a una subcomisión
    @Override
    public List<SocioSubcomision> obtenerSociosDeSubcomision(int idSubcom) throws SQLException {
        List<SocioSubcomision> socios = new ArrayList<>();
        try (Connection connection = conexion.getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SOCIOS_POR_SUBCOMISION)) {
            preparedStatement.setInt(1, idSubcom);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                SocioSubcomision socioSubcomision = new SocioSubcomision(
                        rs.getInt("id_socio"),
                        rs.getInt("id_subcom")
                );
                socios.add(socioSubcomision);
            }
        }
        return socios;
    }
}
