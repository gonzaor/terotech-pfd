package DAO;

import CONTROLADOR.Conexion;
import MODELO.Pago;

import java.sql.SQLException;
import java.util.List;

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

