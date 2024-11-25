package DAO;

import CONTROLADOR.Conexion;
import MODELO.Pago;
import MODELO.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class PagoDAOIMPLEMENTACION implements PagoDAO {
    Conexion conexion;

    {
        try {
            conexion = Conexion.obtenerInstancia();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void ingresar(Pago pago) throws SQLException {
        // Validar que los datos del pago sean válidos
        if (pago.getUsuario() == null || pago.getUsuario().getId() <= 0) {
            throw new SQLException("El usuario asociado al pago no es válido.");
        }

        if (pago.getMonto() == null || pago.getMonto() <= 0) {
            throw new SQLException("El monto cobrado debe ser mayor a 0.");
        }

        if (pago.getFecha() == null) {
            throw new SQLException("La fecha de cobro no puede estar vacía.");
        }

        if (pago.getFormaPago() == null || pago.getFormaPago().isEmpty()) {
            throw new SQLException("La forma de cobro es obligatoria.");
        }

        // Verificar si el usuario es socio
        boolean esSocio = verificarSiEsSocio(pago.getUsuario().getId());

        if (esSocio) {
            pago.setTipoPago("Cuota"); // Habilitar cuota
        } else {
            if (pago.getTipoPago() == null || pago.getTipoPago().isEmpty()) {
                throw new SQLException("Si no es cuota, debe especificar actividad o reserva.");
            }
        }

        // Insertar el pago en la base de datos
        String query = "INSERT INTO PAGOS (id_usuario, tipo_pago, fecha_cobro, forma_cobro, monto_cobrado) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = conexion.getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, pago.getUsuario().getId());
            preparedStatement.setString(2, pago.getTipoPago());
            preparedStatement.setDate(3, new java.sql.Date(pago.getFecha().getTime()));
            preparedStatement.setString(4, pago.getFormaPago());
            preparedStatement.setDouble(5, pago.getMonto());
            preparedStatement.executeUpdate();
            System.out.println("Pago registrado correctamente.");
        }
    }

    @Override
    public Pago obtenerPorId(int idPago) throws SQLException {
        // Validar que el ID del pago sea válido
        if (idPago <= 0) {
            throw new SQLException("El ID del pago es inválido.");
        }

        String query = "SELECT * FROM PAGOS WHERE id_pago = ?";
        try (Connection connection = conexion.getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idPago); // Establecemos el ID del pago en la consulta
            ResultSet rs = preparedStatement.executeQuery();

            // Si se encuentra el pago, lo devolvemos
            if (rs.next()) {
                Pago pago = new Pago(
                        rs.getInt("id_pago"),
                        rs.getDate("fecha_cobro"),
                        rs.getFloat("monto_cobrado"),
                        new Usuario(rs.getInt("id_usuario")), // Asegúrate de que el Usuario sea instanciado correctamente
                        rs.getString("tipo_pago"),
                        rs.getString("forma_cobro")
                );
                return pago;
            }
        }

        // Si no se encuentra el pago, retornamos null
        return null;
    }

    // Método para verificar si el usuario es socio
    private boolean verificarSiEsSocio(int idUsuario) throws SQLException {
        String query = "SELECT * FROM SOCIOS WHERE id_usuario = ?";
        try (Connection connection = conexion.getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idUsuario);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next(); // Si hay un resultado, es un socio
        }
    }



    @Override
    public List<Pago> obtenerTodos() throws SQLException {
        List<Pago> pagos = new ArrayList<>();

        // Consulta SQL para obtener todos los pagos
        String query = "SELECT * FROM PAGOS";

        try (Connection connection = conexion.getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet rs = preparedStatement.executeQuery()) {

            // Procesamos los resultados de la consulta
            while (rs.next()) {
                Pago pago = new Pago(
                        rs.getInt("id_pago"),
                        rs.getDate("fecha_cobro"),
                        rs.getFloat("monto_cobrado"),
                        new Usuario(rs.getInt("id_usuario")), // Asegúrate de que el Usuario sea instanciado correctamente
                        rs.getString("tipo_pago"),
                        rs.getString("forma_cobro")
                );
                pagos.add(pago); // Agregamos el pago a la lista
            }
        }

        return pagos; // Retornamos la lista de pagos
    }

    @Override
    public void modificar(Pago pago) throws SQLException {
        // Validar que los datos del pago sean válidos
        if (pago.getId() <= 0) {
            throw new SQLException("El ID del pago es inválido.");
        }
        if (pago.getUsuario() == null || pago.getUsuario().getId() <= 0) {
            throw new SQLException("El usuario asociado al pago no es válido.");
        }
        if (pago.getMonto() == null || pago.getMonto() <= 0) {
            throw new SQLException("El monto cobrado debe ser mayor a 0.");
        }
        if (pago.getFecha() == null) {
            throw new SQLException("La fecha de cobro no puede estar vacía.");
        }
        if (pago.getFormaPago() == null || pago.getFormaPago().isEmpty()) {
            throw new SQLException("La forma de cobro es obligatoria.");
        }

        // Verificar si el pago existe en la base de datos
        if (!existePago(pago.getId())) {
            throw new SQLException("No se encontró el pago con el ID especificado.");
        }

        // Actualizar el pago en la base de datos
        String query = "UPDATE PAGOS SET id_usuario = ?, tipo_pago = ?, fecha_cobro = ?, forma_cobro = ?, monto_cobrado = ? WHERE id_pago = ?";
        try (Connection connection = conexion.getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, pago.getUsuario().getId());  // ID del usuario
            preparedStatement.setString(2, pago.getTipoPago());      // Tipo de pago: "Cuota", "Inscripción", etc.
            preparedStatement.setDate(3, new java.sql.Date(pago.getFecha().getTime()));  // Fecha del cobro
            preparedStatement.setString(4, pago.getFormaPago());    // Forma de cobro
            preparedStatement.setDouble(5, pago.getMonto());        // Monto cobrado
            preparedStatement.setInt(6, pago.getId());              // ID del pago a modificar
            preparedStatement.executeUpdate();  // Ejecutar la actualización
            System.out.println("Pago modificado correctamente.");
        }
    }

    // Método para verificar si el pago existe
    private boolean existePago(int idPago) throws SQLException {
        String query = "SELECT 1 FROM PAGOS WHERE id_pago = ?";
        try (Connection connection = conexion.getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idPago);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next(); // Si existe un resultado, el pago existe
        }
    }

    @Override
    public void eliminar(int idPago) throws SQLException {
        // Validar que el ID del pago sea válido
        if (idPago <= 0) {
            throw new SQLException("El ID del pago es inválido.");
        }

        // Verificar si el pago existe en la base de datos
        if (!existePago(idPago)) {
            throw new SQLException("No se encontró el pago con el ID especificado.");
        }

        // Eliminar el pago de la base de datos
        String query = "DELETE FROM PAGOS WHERE id_pago = ?";
        try (Connection connection = conexion.getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idPago); // ID del pago a eliminar
            preparedStatement.executeUpdate();
            System.out.println("Pago eliminado correctamente.");
        }
    }

    @Override
    public List<Pago> obtenerPorUsuario(int idUsuario) throws SQLException {
        // Validar que el ID del usuario sea válido
        if (idUsuario <= 0) {
            throw new SQLException("El ID del usuario es inválido.");
        }

        List<Pago> pagos = new ArrayList<>();
        String query = "SELECT * FROM PAGOS WHERE id_usuario = ?";

        try (Connection connection = conexion.getConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idUsuario); // Establecemos el ID del usuario en la consulta
            ResultSet rs = preparedStatement.executeQuery();

            // Procesamos los resultados de la consulta
            while (rs.next()) {
                Pago pago = new Pago(
                        rs.getInt("id_pago"),
                        rs.getDate("fecha_cobro"),
                        rs.getFloat("monto_cobrado"),
                        new Usuario(rs.getInt("id_usuario")), // Asegúrate de que el Usuario sea instanciado correctamente
                        rs.getString("tipo_pago"),
                        rs.getString("forma_cobro")
                );
                pagos.add(pago);
            }
        }

        return pagos; // Retornamos la lista de pagos asociados al usuario
    }
}
