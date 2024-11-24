package DAO;

import MODELO.Perfil;
import java.sql.SQLException;
import java.util.List;

public interface PerfilDAO {
    void agregarPerfil(Perfil perfil) throws SQLException;
    List<Perfil> listar() throws SQLException;
    List<Perfil> listarFiltrado(String nombre, boolean estado) throws SQLException;
    void modificarPerfil(Perfil perfil) throws SQLException;
    void cambiarEstado(Long id, boolean nuevoEstado) throws SQLException;
    void agregarPerfilAUsuario(int idUsuario, int idPerfil) throws SQLException;
     List<Perfil> obtenerPerfilesDeUsuario(int idUsuario) throws SQLException;

}

