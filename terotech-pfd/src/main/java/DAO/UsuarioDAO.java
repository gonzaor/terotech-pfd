package DAO;

import MODELO.Usuario;
import java.sql.SQLException;
import java.util.List;

public interface UsuarioDAO {
    void create(Usuario usuario) throws SQLException; // Maneja direcciones y teléfonos
    Usuario read(int id) throws SQLException;
    void update(Usuario usuario) throws SQLException;
    void deactivate(int id) throws SQLException;
    List<Usuario> listAll() throws SQLException;
}
