package DAO;

import MODELO.Pago;

import java.sql.SQLException;
import java.util.List;

public interface PagoDAO {
        void ingresar(Pago pago) throws SQLException;
        Pago obtenerPorId(int id) throws SQLException ;
        List<Pago> obtenerTodos() throws SQLException ;
        void modificar(Pago pago) throws SQLException ;
        void eliminar(int id) throws SQLException ;
         List<Pago> obtenerPorUsuario(int idUsuario) throws SQLException ;
}

