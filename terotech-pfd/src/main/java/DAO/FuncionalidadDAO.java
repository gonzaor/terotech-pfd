package DAO;

import MODELO.Funcionalidad;
import java.sql.SQLException;
import java.util.List;

public interface FuncionalidadDAO {
    void agregarFuncionalidad(Funcionalidad funcionalidad) throws SQLException;
    List<Funcionalidad> listar() throws SQLException;
    void modificarFuncionalidad(Funcionalidad funcionalidad) throws SQLException;
    void cambiarEstado(Long id, boolean nuevoEstado) throws SQLException;
    List<Funcionalidad> obtenerFuncionalidadesPorPerfil(int idPerfil) throws SQLException;
    void agregarFuncionalidadAPerfil (int idPerfil, int idFuncionalidad) throws SQLException;

   void eliminarFuncionalidadDePerfil (int idPerfil, int idFuncionalidad) throws SQLException;
}

