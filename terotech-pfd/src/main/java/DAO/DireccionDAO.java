package DAO;

import MODELO.Direccion;

import java.sql.SQLException;

public interface DireccionDAO {
    int agregarDireccion(Direccion direccion) throws SQLException;

}
