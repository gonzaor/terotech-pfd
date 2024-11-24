package DAO;

import CONTROLADOR.Conexion;
import MODELO.Funcionalidad;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionalidadDAOIMPLEMENTACION implements FuncionalidadDAO {

    private static final String INSERT_FUNCIONALIDAD = "INSERT INTO FUNCIONALIDADES (nombre, descripcion, estado) VALUES (?, ?, ?)";
    private static final String SELECT_TODAS = "SELECT * FROM FUNCIONALIDADES";
    private static final String UPDATE_FUNCIONALIDAD = "UPDATE FUNCIONALIDADES SET descripcion = ?, estado = ? WHERE id_funcionalidad = ?";
    private static final String UPDATE_ESTADO = "UPDATE FUNCIONALIDADES SET estado = ? WHERE id_funcionalidad = ?";
    private static final String SELECT_FUNCIONALIDADES_POR_PERFIL = "SELECT f.* FROM FUNCIONALIDADES f " +
            "JOIN ACCESO a ON f.id_funcionalidad = a.id_funcionalidad " +
            "WHERE a.id_perfil = ?";
    private static final String INSERT_FUNCIONALIDAD_PERFIL = "INSERT INTO ACCESO (id_perfil, id_funcionalidad) VALUES (?, ?)";

    private static final String SELECT_FUNCIONALIDAD_POR_NOMBRE = "SELECT COUNT(*) FROM FUNCIONALIDADES WHERE nombre = ?";

    private static final String DELETE_FUNCIONALIDAD_PERFIL = "DELETE FROM ACCESO WHERE id_perfil = ? AND id_funcionalidad = ?";




    public boolean existeFuncionalidad(String nombre) throws SQLException {
        try (Connection connection = Conexion.obtenerInstancia().getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FUNCIONALIDAD_POR_NOMBRE)) {
            preparedStatement.setString(1, nombre);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void agregarFuncionalidad(Funcionalidad funcionalidad) throws SQLException {
        // Verificar si la funcionalidad ya existe
        if (existeFuncionalidad(funcionalidad.getNombre())) {
            throw new SQLException("Ya existe una funcionalidad con ese nombre.");
        }

        try (Connection connection = Conexion.obtenerInstancia().getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FUNCIONALIDAD)) {
            preparedStatement.setString(1, funcionalidad.getNombre());
            preparedStatement.setString(2, funcionalidad.getDescripcion());
            preparedStatement.setBoolean(3, funcionalidad.isEstado());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Funcionalidad> listar() throws SQLException {
        List<Funcionalidad> funcionalidades = new ArrayList<>();
        try (Connection connection = Conexion.obtenerInstancia().getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TODAS);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                Funcionalidad funcionalidad = new Funcionalidad();
                funcionalidad.setId(rs.getLong("id_funcionalidad"));
                funcionalidad.setNombre(rs.getString("nombre"));
                funcionalidad.setDescripcion(rs.getString("descripcion"));
                funcionalidad.setEstado(rs.getBoolean("estado"));
                funcionalidades.add(funcionalidad);
            }
        }
        return funcionalidades;
    }

    @Override
    public void modificarFuncionalidad(Funcionalidad funcionalidad) throws SQLException {
        try (Connection connection = Conexion.obtenerInstancia().getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FUNCIONALIDAD)) {
            preparedStatement.setString(1, funcionalidad.getDescripcion());
            preparedStatement.setBoolean(2, funcionalidad.isEstado());
            preparedStatement.setLong(3, funcionalidad.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void cambiarEstado(Long id, boolean nuevoEstado) throws SQLException {
        try (Connection connection = Conexion.obtenerInstancia().getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ESTADO)) {
            preparedStatement.setBoolean(1, nuevoEstado);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Funcionalidad> obtenerFuncionalidadesPorPerfil(int idPerfil) throws SQLException {
        List<Funcionalidad> funcionalidades = new ArrayList<>();
        try (Connection connection = Conexion.obtenerInstancia().getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FUNCIONALIDADES_POR_PERFIL)) {
            preparedStatement.setInt(1, idPerfil);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Funcionalidad funcionalidad = new Funcionalidad();
                funcionalidad.setId(rs.getLong("id_funcionalidad"));
                funcionalidad.setNombre(rs.getString("nombre"));
                funcionalidad.setDescripcion(rs.getString("descripcion"));
                funcionalidad.setEstado(rs.getBoolean("estado"));
                funcionalidades.add(funcionalidad);
            }
        }
        return funcionalidades;
    }

    @Override
    public void agregarFuncionalidadAPerfil(int idPerfil, int idFuncionalidad) throws SQLException {
        try (Connection connection = Conexion.obtenerInstancia().getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FUNCIONALIDAD_PERFIL)) {
            preparedStatement.setInt(1, idPerfil);
            preparedStatement.setInt(2, idFuncionalidad);
            preparedStatement.executeUpdate();
        }
    }
    public void eliminarFuncionalidadDePerfil(int idPerfil, int idFuncionalidad) throws SQLException {
        try (Connection connection = Conexion.obtenerInstancia().getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FUNCIONALIDAD_PERFIL)) {
            preparedStatement.setInt(1, idPerfil);
            preparedStatement.setInt(2, idFuncionalidad);
            preparedStatement.executeUpdate();
        }
    }
}
