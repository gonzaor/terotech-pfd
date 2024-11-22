package DAO;

import MODELO.Perfil;
import java.util.List;

public interface PerfilDAO {
    void agregarPerfil(Perfil perfil);
    Perfil obtenerPerfilPorNombre(String nombre);
    List<Perfil> obtenerTodosLosPerfiles();
    void actualizarPerfil(Perfil perfil);
    void eliminarPerfil(String nombre);
    int retornarPerfilUsuario(int documento);


}

