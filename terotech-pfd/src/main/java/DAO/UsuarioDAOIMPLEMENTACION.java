package DAO;
import CONTROLADOR.Conexion;
import MODELO.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAOIMPLEMENTACION implements UsuarioDAO {
    private Conexion conexion;

    public UsuarioDAOIMPLEMENTACION() {
        try {
            this.conexion = Conexion.obtenerInstancia();
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión", e);
        }
    }

    private static final String INSERT_SCRIPT = "INSERT INTO usuarios (documento, tipoDocumento, nombre, apellido, correo, id_direccion, id_estado, id_perfil) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_SCRIPT = "DELETE FROM usuarios WHERE documento = ?";
    private static final String GET_USUARIO_SCRIPT = "SELECT * FROM usuarios WHERE documento = ?";
    private static final String GET_ALL_USUARIOS_SCRIPT = "SELECT * FROM usuarios";

    @Override
    public void agregarUsuario(Usuario usuario) throws SQLException {
        try (Connection connection = conexion.getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SCRIPT)) {
            preparedStatement.setString(1, usuario.getNumeroDocumento());
            preparedStatement.setString(2, usuario.getTipoDocumento());
            preparedStatement.setString(3, usuario.getNombres());
            preparedStatement.setString(4, usuario.getApellidos());
            preparedStatement.setString(5, usuario.getEmail());
            preparedStatement.setString(6, usuario.getDomicilio());
            preparedStatement.setString(7, usuario.getEstado()); // Inactivo
            preparedStatement.setString(8, usuario.getNombrePerfil()); // Se llama a la funcion de perfil que retorna su id.
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void eliminarUsuario(String numeroDocumento) throws SQLException {
        try (Connection connection = conexion.getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SCRIPT)) {
            preparedStatement.setString(1, numeroDocumento);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void actualizarUsuario(String campoAModificar, String nuevoValor, String identificadorUsuario, int tipoIdentificador) throws SQLException {
        String campoIdentificador;
        switch (tipoIdentificador) {
            case 1:
                campoIdentificador = "documento";
                break;
            case 2:
                campoIdentificador = "correo";
                break;
            default:
                campoIdentificador = "id";
                break;
        }

        String UPDATE_SCRIPT = "UPDATE usuarios SET " + campoAModificar + " = ? WHERE " + campoIdentificador + " = ?";
        try (Connection connection = conexion.getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SCRIPT)) {
            preparedStatement.setString(1, nuevoValor);
            preparedStatement.setString(2, identificadorUsuario);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Usuario obtenerUsuario(String identificador, int tipoIdentificador) throws SQLException {
        String campoIdentificador;
        switch (tipoIdentificador) {
            case 1:
                campoIdentificador = "documento";
                break;
            case 2:
                campoIdentificador = "correo";
                break;
            default:
                campoIdentificador = "id";
                break;
        }

        String query = "SELECT * FROM usuarios WHERE " + campoIdentificador + " = ?";
        try (Connection connection = conexion.getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, identificador);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNumeroDocumento(rs.getString("documento"));
                    usuario.setTipoDocumento(rs.getString("tipoDocumento"));
                    usuario.setNombres(rs.getString("nombre"));
                    usuario.setApellidos(rs.getString("apellido"));
                    usuario.setEmail(rs.getString("correo"));
                    usuario.setDomicilio(rs.getString("id_direccion"));
                    usuario.setEstado(rs.getString("id_estado"));
                    usuario.setId(rs.getInt("id_perfil"));
                    return usuario;
                }
            }
        }
        return null;
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection connection = conexion.getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USUARIOS_SCRIPT);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.getId(rs.getInt("id"));
                usuario.setNumeroDocumento(rs.getString("documento"));
                usuario.setTipoDocumento(rs.getString("tipoDocumento"));
                usuario.setNombres(rs.getString("nombre"));
                usuario.setApellidos(rs.getString("apellido"));
                usuario.setEmail(rs.getString("correo"));
                usuario.setDomicilio(rs.getString("direccion"));
                usuario.setEstado(rs.getString("id_estado"));
                usuario.getNombrePerfil();
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }
}
