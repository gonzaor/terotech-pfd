package DAO;
import CONTROLADOR.Conexion;
import MODELO.Telefono;
import MODELO.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOIMPLEMENTACION implements UsuarioDAO {
    private Conexion conexion;

    public UsuarioDAOIMPLEMENTACION() {
        try {
            this.conexion = Conexion.obtenerInstancia();
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión", e);
        }
    }

    private static final String DELETE_SCRIPT = "DELETE FROM usuarios WHERE documento = ?";
    private static final String GET_USUARIO_SCRIPT = "SELECT * FROM usuarios WHERE documento = ?";
    private static final String GET_ALL_USUARIOS_SCRIPT = "SELECT * FROM usuarios";

    @Override

    public void agregarUsuario(Usuario usuario) throws SQLException {
        int id_direccion = 0;
        if (usuario.getDomicilio() != null) {
            DireccionDAO direccionDAO = new DireccionDAOImplementacion();
            id_direccion = direccionDAO.agregarDireccion(usuario.getDomicilio());
        }

        String INSERT_USUARIO = "INSERT INTO USUARIOS (tipo_documento, nro_documento, nombre, apellido, fecha_nacimiento, correo, contraseña, id_direccion, estado, tipo_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        int id_usuario = 0;
        try (Connection connection = conexion.getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USUARIO, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, usuario.getTipoDocumento());
            preparedStatement.setInt(2, Integer.parseInt(usuario.getNumeroDocumento()));
            preparedStatement.setString(3, usuario.getNombres());
            preparedStatement.setString(4, usuario.getApellidos());
            preparedStatement.setDate(5, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
            preparedStatement.setString(6, usuario.getEmail());
            preparedStatement.setString(7, usuario.getContrasena());
            preparedStatement.setInt(8, id_direccion);
            preparedStatement.setString(9, usuario.getEstado());
            preparedStatement.setString(10, usuario.getTipoUsuario());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id_usuario = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener el ID del usuario insertado.");
                }
            }
        }

        if (usuario.getTelefonos() != null && !usuario.getTelefonos().isEmpty()) {
            TelefonoDAO telefonoDAO = new TelefonoDAOImplementacion();
            for (Telefono telefono : usuario.getTelefonos()) {
                telefono.setIdUsuario(id_usuario);
                telefonoDAO.agregarTelefono(telefono);
            }
        }
    }

/*
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


   /* public Usuario obtenerUsuario(String identificador, int tipoIdentificador) throws SQLException {
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
                    usuario.getDomicilio(rs.getInt("id_direccion"));
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
    } */
}
