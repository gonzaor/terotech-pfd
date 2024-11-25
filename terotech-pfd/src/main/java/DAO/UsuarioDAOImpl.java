package DAO;

import CONTROLADOR.Conexion;
import MODELO.Usuario;
import MODELO.Direccion;
import MODELO.Telefono;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {
    private final DireccionDAO direccionDAO;
    private final TelefonoDAO telefonoDAO;
    private final Conexion conexion;

    public UsuarioDAOImpl() {
        try {
            this.conexion = Conexion.obtenerInstancia();
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión", e);
        }
        this.direccionDAO = new DireccionDAOImplementacion();
        this.telefonoDAO = new TelefonoDAOImplementacion();
    }

    @Override
    public void create(Usuario usuario) throws SQLException {
        Connection connection = conexion.getConexion();
        try {
            connection.setAutoCommit(false);

            // Insertar Dirección
            Direccion retornoDir = direccionDAO.agregarDireccion(connection, usuario.getDomicilio());
            usuario.getDomicilio().setIdDireccion(retornoDir.getIdDireccion());

            // Insertar Usuario
            String sql = "INSERT INTO USUARIOS (tipo_documento, nro_documento, nombre, apellido, fecha_nacimiento, " +
                    "correo, contrasena, id_direccion, estado, tipo_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, usuario.getTipoDocumento());
                ps.setInt(2, usuario.getNumeroDocumento());
                ps.setString(3, usuario.getNombres());
                ps.setString(4, usuario.getApellidos());
                ps.setDate(5, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
                ps.setString(6, usuario.getEmail());
                ps.setString(7, usuario.getContrasena());
                ps.setInt(8, usuario.getDomicilio().getIdDireccion());
                ps.setString(9, usuario.getEstado());
                ps.setString(10, usuario.getTipoUsuario());
                ps.executeUpdate();

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        usuario.setId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("No se pudo obtener el ID del usuario insertado.");
                    }
                }
            }

            // Insertar Teléfonos
            for (Telefono telefono : usuario.getTelefonos()) {
                telefono.setIdUsuario(usuario.getId());
                telefonoDAO.agregarTelefono(connection, telefono);
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public Usuario read(int id) throws SQLException {
        Connection connection = conexion.getConexion();
        String sql = "SELECT * FROM USUARIOS WHERE id_usuario = ?";
        Usuario usuario = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = mapUsuarioFromResultSet(rs);
                }
            }
        }

        return usuario;
    }

    public Usuario readByDocumento(int numeroDocumento) throws SQLException {
        Connection connection = conexion.getConexion();
        String sql = "SELECT * FROM USUARIOS WHERE nro_documento = ?";
        Usuario usuario = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, numeroDocumento);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = mapUsuarioFromResultSet(rs);
                    usuario = new Usuario();
                    usuario.setId(rs.getInt("id_usuario"));
                    usuario.setTipoDocumento(rs.getString("tipo_documento"));
                    usuario.setNumeroDocumento(rs.getInt("nro_documento"));
                    usuario.setNombres(rs.getString("nombre"));
                    usuario.setApellidos(rs.getString("apellido"));
                    usuario.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                    usuario.setEmail(rs.getString("correo"));
                    usuario.setContrasena(rs.getString("contrasena"));
                    usuario.setEstado(rs.getString("estado"));
                    usuario.setTipoUsuario(rs.getString("tipo_usuario"));

                    // Obtener Dirección
                    int idDireccion = rs.getInt("id_direccion");
                    Direccion direccion = direccionDAO.obtenerDireccion(connection, idDireccion);
                    usuario.setDomicilio(direccion);
                    System.out.println("Dirección obtenida para el usuario ID: " + id);

                    // Obtener Teléfonos
                    List<Telefono> telefonos = telefonoDAO.obtenerTelefonosPorUsuario(connection, id);
                    usuario.setTelefonos(telefonos);
                    System.out.println("Teléfonos obtenidos para el usuario ID: " + id);
                }
            }
        }

        return usuario;
    }

    @Override
    public void update(Usuario usuario) throws SQLException {
        Connection connection = conexion.getConexion();
        try {
            connection.setAutoCommit(false);

            // Actualizar Dirección
            direccionDAO.actualizarDireccion(connection, usuario.getDomicilio());

            // Actualizar Usuario
            String sql = "UPDATE USUARIOS SET tipo_documento = ?, nro_documento = ?, nombre = ?, apellido = ?, " +
                    "fecha_nacimiento = ?, correo = ?, contrasena = ?, id_direccion = ?, estado = ?, tipo_usuario = ? " +
                    "WHERE id_usuario = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, usuario.getTipoDocumento());
                ps.setInt(2, usuario.getNumeroDocumento());
                ps.setString(3, usuario.getNombres());
                ps.setString(4, usuario.getApellidos());
                ps.setDate(5, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
                ps.setString(6, usuario.getEmail());
                ps.setString(7, usuario.getContrasena());
                ps.setInt(8, usuario.getDomicilio().getIdDireccion());
                ps.setString(9, usuario.getEstado());
                ps.setString(10, usuario.getTipoUsuario());
                ps.setInt(11, usuario.getId());
                ps.executeUpdate();
            }

            // Eliminar y Reinsertar Teléfonos
            telefonoDAO.eliminarTelefonosPorUsuario(connection, usuario.getId());
            for (Telefono telefono : usuario.getTelefonos()) {
                telefono.setIdUsuario(usuario.getId());
                telefonoDAO.agregarTelefono(connection, telefono);
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void updateByDocumento(int numeroDocumento, Usuario usuarioNuevo) throws SQLException {
        Usuario usuarioExistente = readByDocumento(numeroDocumento);
        if (usuarioExistente != null) {
            usuarioNuevo.setId(usuarioExistente.getId());
            update(usuarioNuevo);
        } else {
            throw new SQLException("Usuario con documento " + numeroDocumento + " no encontrado.");
        }
    }

    @Override
    public void deactivate(int id) throws SQLException {
        Connection connection = conexion.getConexion();
        String sql = "UPDATE USUARIOS SET estado = 'inactivo' WHERE id_usuario = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void deactivateByDocumento(int numeroDocumento) throws SQLException {
        Usuario usuario = readByDocumento(numeroDocumento);
        if (usuario != null) {
            deactivate(usuario.getId());
        } else {
            throw new SQLException("Usuario con documento " + numeroDocumento + " no encontrado.");
        }
    }

    @Override
    public List<Usuario> listAll() throws SQLException {
        Connection connection = conexion.getConexion();
        String sql = "SELECT * FROM USUARIOS WHERE estado = 'activo'";
        List<Usuario> usuarios = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Usuario usuario = mapUsuarioFromResultSet(rs);
                if (usuario != null) {
                    usuarios.add(usuario);
                }
            }
        }

        return usuarios;
    }

    @Override
    public String login(String email, String password) throws SQLException {
        Connection connection = conexion.getConexion();
        String sql = "SELECT tipo_usuario FROM USUARIOS WHERE correo = ? AND contrasena = ? AND estado = 'activo'";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("tipo_usuario");
                } else {
                    return null; // Credenciales inválidas
                }
            }
        }
    }


    private Usuario mapUsuarioFromResultSet(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("id_usuario"));
        usuario.setTipoDocumento(rs.getString("tipo_documento"));
        usuario.setNumeroDocumento(rs.getInt("nro_documento"));
        usuario.setNombres(rs.getString("nombre"));
        usuario.setApellidos(rs.getString("apellido"));
        usuario.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
        usuario.setEmail(rs.getString("correo"));
        usuario.setContrasena(rs.getString("contrasena"));
        usuario.setEstado(rs.getString("estado"));
        usuario.setTipoUsuario(rs.getString("tipo_usuario"));

        // Obtener Dirección
        int idDireccion = rs.getInt("id_direccion");
        Direccion direccion = direccionDAO.obtenerDireccion(conexion.getConexion(), idDireccion);
        usuario.setDomicilio(direccion);

        // Obtener Teléfonos
        List<Telefono> telefonos = telefonoDAO.obtenerTelefonosPorUsuario(conexion.getConexion(), usuario.getId());
        usuario.setTelefonos(telefonos);

        return usuario;
    }
}
