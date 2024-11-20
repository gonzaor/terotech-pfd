package DAO;
import CONTROLADOR.Conexion;
import DAO.UsuarioDAO;
import MODELO.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class UsuarioDAOIMPLEMENTACION implements UsuarioDAO {
    Conexion conexion;

    {
        try {
            conexion = Conexion.obtenerInstancia();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean agregar(Usuario usuario) {

    return false;

    }

    @Override
    public Usuario obtenerPornumeroDocumento(String numeroDocumento) {
        return null;
    }

    @Override
    public List<Usuario> obtenerTodos() {
        return new ArrayList<>();
    }

    @Override
    public void actualizar(Usuario usuario) {
    }

    @Override
    public void eliminar(String numeroDocumento) {
    }

}
