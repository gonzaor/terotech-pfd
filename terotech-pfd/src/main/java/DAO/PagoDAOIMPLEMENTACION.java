package DAO;

import CONTROLADOR.Conexion;
import MODELO.Pago;
import MODELO.Usuario;

import java.sql.SQLException;
import java.util.List;
import java.sql.*;

public class PagoDAOIMPLEMENTACION implements PagoDAO {
    Conexion conexion;

    try {
        conexion = Conexion.obtenerInstancia();
    } catch (SQLException e) {
        System.out.println("Error al obtener la conexion");
        throw new RuntimeException(e);
    }

    @Override
    public void ingresar(Pago pago) {
            
    }

    @Override
    public Pago obtenerPorId(int id) {

        return null;
    }

    @Override
    public List<Pago> obtenerTodos() {

        return null;
    }

    @Override
    public void modificar (Pago pago) {

    }

    @Override
    public void eliminar(int id) {

    }
}

