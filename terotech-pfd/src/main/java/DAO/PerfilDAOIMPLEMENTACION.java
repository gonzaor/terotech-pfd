package DAO;

import CONTROLADOR.Conexion;
import DAO.PerfilDAO;
import MODELO.Perfil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class PerfilDAOIMPLEMENTACION implements PerfilDAO {
    private Conexion conexion;

    public PerfilDAOIMPLEMENTACION() {
        try {
            conexion = Conexion.obtenerInstancia();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void agregarPerfil(Perfil perfil) {
    }

    @Override
    public Perfil obtenerPerfilPorNombre(String nombre) {
        return null;
    }

    @Override
    public List<Perfil> obtenerTodosLosPerfiles() {
        return new ArrayList<>();
    }

    @Override
    public void actualizarPerfil(Perfil perfil) {
    }

    @Override
    public void eliminarPerfil(String nombre) {
    }
}

