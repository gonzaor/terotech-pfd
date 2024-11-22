package DAO;

import MODELO.Funcionalidad;
import CONTROLADOR.Conexion;
import MODELO.Perfil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class FuncionalidadDAOIMPLEMENTACION implements FuncionalidadDAO {

    private static final String INSERT_SCRIPT = "INSERT INTO funcionalidades (nombre, descripcion, estado) VALUES (?, ?, ?)";

    @Override
    public void agregarFuncionalidad(Funcionalidad funcionalidad) {
        try {
            Connection connection = Conexion.obtenerInstancia().getConexion();

            if (connection == null) {
                throw new SQLException("No se pudo obtener la conexión a la base de datos.");
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SCRIPT)) {
                preparedStatement.setString(1, funcionalidad.getNombre());
                preparedStatement.setString(2, funcionalidad.getDescripcion());
                preparedStatement.setBoolean(3, funcionalidad.isEstado());

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                System.err.println("Error al ejecutar la consulta: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (SQLException e) {
            System.err.println("Error de conexión a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Funcionalidad obtenerPorId(Long id) {
        return null;
    }

    @Override
    public List<Funcionalidad> obtenerTodos() {
        return null;
    }

    @Override
    public void actualizar(Funcionalidad funcionalidad) {
    }

    @Override
    public void eliminar(Long id) {
    }


    @Override
    public void vincularFuncionalidadConPerfil(Funcionalidad funcionalidad, Perfil perfil) {
        if (!perfil.getFuncionalidades().contains(funcionalidad)) {
            perfil.getFuncionalidades().add(funcionalidad);
            funcionalidad.getPerfiles().add(perfil);

        }

    }
}
