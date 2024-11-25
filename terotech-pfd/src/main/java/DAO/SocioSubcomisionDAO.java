package DAO;
import MODELO.SocioSubcomision;
import java.sql.SQLException;
import java.util.List;

public interface SocioSubcomisionDAO {
    void agregarSubcomisionASocio(int idSocio, int idSubcom) throws SQLException;
    void eliminarSubcomisionDeSocio(int idSocio, int idSubcom) throws SQLException;
    List<SocioSubcomision> obtenerSubcomisionesDeSocio(int idSocio) throws SQLException;
    List<SocioSubcomision> obtenerSociosDeSubcomision(int idSubcom) throws SQLException;

}
