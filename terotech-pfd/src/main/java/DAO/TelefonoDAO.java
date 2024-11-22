package DAO;

import MODELO.Telefono;
import java.sql.SQLException;

public interface TelefonoDAO {
    void agregarTelefono(Telefono telefono) throws SQLException;
}
