package DAO;

import MODELO.Pago;
import java.util.List;

public interface PagoDAO {
        void ingresar(Pago pago);
        Pago obtenerPorId(int id);
        List<Pago> obtenerTodos();
        void modificar(Pago pago);
        void eliminar(int id);
}

