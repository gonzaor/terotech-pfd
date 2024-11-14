package DAO;

import MODELO.Usuario;

import java.util.List;

public interface UsuarioDAO {
    void agregar(Usuario usuario);
    Usuario obtenerPornumeroDocumento(String numeroDocumento);
    List<Usuario> obtenerTodos();
    void actualizar(Usuario usuario);
    void eliminar(String numeroDocumento);

}
