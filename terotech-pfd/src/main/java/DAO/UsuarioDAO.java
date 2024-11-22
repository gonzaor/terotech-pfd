package DAO;
import MODELO.Usuario;
import CONTROLADOR.Conexion;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface UsuarioDAO {
    void agregarUsuario(Usuario usuario) throws SQLException;
    void eliminarUsuario(String numeroDocumento) throws SQLException;
    void actualizarUsuario(String campoAModificar, String nuevoValor, String identificadorUsuario, int tipoIdentificador) throws SQLException;
    Usuario obtenerUsuario(String identificador, int tipoIdentificador) throws SQLException;
    List<Usuario> obtenerTodosLosUsuarios() throws SQLException;



}
