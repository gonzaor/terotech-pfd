package DAO;

import CONTROLADOR.Conexion;
import MODELO.Perfil;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class PerfilDAOIMPLEMENTACION implements PerfilDAO {
    private Conexion conexion;

    public PerfilDAOIMPLEMENTACION() {
        try {
            conexion = Conexion.obtenerInstancia();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String INSERT_PERFIL = "INSERT INTO PERFILES (nombre_perfil, descripcion, estado) VALUES (?, ?, ?)";
    private static final String SELECT_TODOS = "SELECT * FROM PERFILES";
    private static final String SELECT_FILTRADO = "SELECT * FROM PERFILES WHERE nombre_perfil LIKE ? AND estado = ?";
    private static final String UPDATE_PERFIL = "UPDATE PERFILES SET descripcion = ?, estado = ? WHERE id_perfil = ?";
    private static final String UPDATE_ESTADO = "UPDATE PERFILES SET estado = ? WHERE id_perfil = ?";
    private static final String SELECT_POR_NOMBRE = "SELECT COUNT(*) FROM PERFILES WHERE nombre_perfil = ?";

    @Override
    public void agregarPerfil(Perfil perfil) throws SQLException {
        // Validar si ya existe el nombre del perfil
        try (Connection connection = Conexion.obtenerInstancia().getConexion();
             PreparedStatement validationStatement = connection.prepareStatement(SELECT_POR_NOMBRE)) {

            validationStatement.setString(1, perfil.getNombre());
            ResultSet rs = validationStatement.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                throw new SQLException("Ya existe un perfil con ese nombre.");
            }
        }

        try (Connection connection = Conexion.obtenerInstancia().getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PERFIL)) {
            preparedStatement.setString(1, perfil.getNombre());
            preparedStatement.setString(2, perfil.getDescripcion());
            preparedStatement.setString(3, perfil.getEstado());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Perfil> listar() throws SQLException {
        List<Perfil> perfiles = new ArrayList<>();
        try (Connection connection = Conexion.obtenerInstancia().getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TODOS);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                Perfil perfil = new Perfil();
                perfil.setId(rs.getLong("id_perfil"));
                perfil.setNombre(rs.getString("nombre_perfil"));
                perfil.setDescripcion(rs.getString("descripcion"));
                perfil.setEstado(rs.getString("estado"));
                perfiles.add(perfil);
            }
        }
        return perfiles;
    }

    @Override
    public List<Perfil> listarFiltrado(String nombre, String estado) throws SQLException {
        List<Perfil> perfiles = new ArrayList<>();
        try (Connection connection = Conexion.obtenerInstancia().getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FILTRADO)) {
            preparedStatement.setString(1, "%" + nombre + "%");
            preparedStatement.setString(2, estado);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Perfil perfil = new Perfil();
                    perfil.setId(rs.getLong("id_perfil"));
                    perfil.setNombre(rs.getString("nombre_perfil"));
                    perfil.setDescripcion(rs.getString("descripcion"));
                    perfil.setEstado(rs.getString("estado"));
                    perfiles.add(perfil);
                }
            }
        }
        return perfiles;
    }

    @Override
    public void modificarPerfil(Perfil perfil) throws SQLException {
        try (Connection connection = Conexion.obtenerInstancia().getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PERFIL)) {
            preparedStatement.setString(1, perfil.getDescripcion());
            preparedStatement.setString(2, perfil.getEstado());
            preparedStatement.setLong(3, perfil.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void cambiarEstado(Long id, String nuevoEstado) throws SQLException {
        try (Connection connection = Conexion.obtenerInstancia().getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ESTADO)) {
            preparedStatement.setString(1, nuevoEstado);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        }
    }

    public void agregarPerfilAUsuario(int idUsuario, int idPerfil) throws SQLException {
        String query = "INSERT INTO USUARIO_PERFIL (id_usuario, id_perfil) VALUES (?, ?)";
        try (Connection connection = Conexion.obtenerInstancia().getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idUsuario);
            preparedStatement.setInt(2, idPerfil);
            preparedStatement.executeUpdate();
        }
    }


    public List<Perfil> obtenerPerfilesDeUsuario(int idUsuario) throws SQLException {
        List<Perfil> perfiles = new ArrayList<>();
        String query = "SELECT p.* FROM PERFILES p " +
                "JOIN USUARIO_PERFIL up ON p.id_perfil = up.id_perfil " +
                "WHERE up.id_usuario = ?";

        try (Connection connection = Conexion.obtenerInstancia().getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idUsuario);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Perfil perfil = new Perfil();
                perfil.setId(rs.getLong("id_perfil"));
                perfil.setNombre(rs.getString("nombre_perfil"));
                perfil.setDescripcion(rs.getString("descripcion"));
                perfil.setEstado(rs.getString("estado"));
                perfiles.add(perfil);
            }
        }
        return perfiles;


    }
}

