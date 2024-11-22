package DAO;

import MODELO.Funcionalidad;
import MODELO.Perfil;

import java.util.List;

public interface FuncionalidadDAO {
    void agregarFuncionalidad(Funcionalidad funcionalidad);
    Funcionalidad obtenerPorId(Long id);
    List<Funcionalidad> obtenerTodos();
    void actualizar(Funcionalidad funcionalidad);
    void eliminar(Long id);
    void vincularFuncionalidadConPerfil(Funcionalidad funcionalidad, Perfil perfil);

}
