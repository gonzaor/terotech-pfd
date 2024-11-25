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
            connection.setAutoCommit(false); // Iniciar transacción
            System.out.println("Transacción iniciada en create().");

            // Insertar Dirección
            Direccion retornoDir = direccionDAO.agregarDireccion(connection, usuario.getDomicilio());
            usuario.getDomicilio().setIdDireccion(retornoDir.getIdDireccion());
            System.out.println("Dirección insertada con ID: " + retornoDir.getIdDireccion());

            // Insertar Usuario
            String sql = "INSERT INTO USUARIOS (tipo_documento, nro_documento, nombre, apellido, fecha_nacimiento, correo, contrasena, id_direccion, estado, tipo_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
                System.out.println("Usuario insertado en la base de datos.");

                // Obtener ID generado para el usuario
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        usuario.setId(generatedKeys.getInt(1));
                        System.out.println("Usuario creado con ID: " + usuario.getId());
                    } else {
                        throw new SQLException("No se pudo obtener el ID del usuario insertado.");
                    }
                }
            }

            // Insertar Teléfonos
            for (Telefono telefono : usuario.getTelefonos()) {
                telefono.setIdUsuario(usuario.getId());
                telefonoDAO.agregarTelefono(connection, telefono);
                System.out.println("Teléfono insertado con ID: " + telefono.getIdTelefono());
            }

            connection.commit(); // Confirmar transacción
            System.out.println("Transacción confirmada en create().");
        } catch (SQLException e) {
            connection.rollback(); // Revertir transacción en caso de error
            System.err.println("Transacción revertida en create().");
            throw e;
        } finally {
            connection.setAutoCommit(true); // Restaurar autoCommit
            System.out.println("AutoCommit restaurado en create().");
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
            connection.setAutoCommit(false); // Iniciar transacción
            System.out.println("Transacción iniciada en update().");

            // Actualizar Dirección
            direccionDAO.actualizarDireccion(connection, usuario.getDomicilio());
            System.out.println("Dirección actualizada para el usuario ID: " + usuario.getId());

            // Actualizar Usuario
            String sql = "UPDATE USUARIOS SET tipo_documento = ?, nro_documento = ?, nombre = ?, apellido = ?, fecha_nacimiento = ?, correo = ?, contrasena = ?, id_direccion = ?, estado = ?, tipo_usuario = ? WHERE id_usuario = ?";
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
                System.out.println("Usuario actualizado en la base de datos con ID: " + usuario.getId());
            }

            // Eliminar Teléfonos Existentes
            telefonoDAO.eliminarTelefonosPorUsuario(connection, usuario.getId());
            System.out.println("Teléfonos existentes eliminados para el usuario ID: " + usuario.getId());

            // Insertar Nuevos Teléfonos
            for (Telefono telefono : usuario.getTelefonos()) {
                telefono.setIdUsuario(usuario.getId());
                telefonoDAO.agregarTelefono(connection, telefono);
                System.out.println("Teléfono insertado con ID: " + telefono.getIdTelefono());
            }

            connection.commit(); // Confirmar transacción
            System.out.println("Transacción confirmada en update().");
        } catch (SQLException e) {
            connection.rollback(); // Revertir transacción en caso de error
            System.err.println("Transacción revertida en update().");
            throw e;
        } finally {
            connection.setAutoCommit(true); // Restaurar autoCommit
            System.out.println("AutoCommit restaurado en update().");
        }
    }

    @Override
    public void deactivate(int id) throws SQLException {
        Connection connection = conexion.getConexion();
        String sql = "UPDATE USUARIOS SET estado = 'inactivo' WHERE id_usuario = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Usuario desactivado con ID: " + id);
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
                int idUsuario = rs.getInt("id_usuario");
                Usuario usuario = read(idUsuario);
                if (usuario != null) {
                    usuarios.add(usuario);
                }
            }
        }

        return usuarios;
    }
}
