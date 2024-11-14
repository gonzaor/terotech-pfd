package MODELO;

public class UsuarioFactory {
    public static Usuario crearUsuario(String categoriaSocio) {
        switch (categoriaSocio) {
            case "NO SOCIO":
                return new Usuario();
            case "SOCIO":
                return new Usuario();
            case "ADMINISTRADOR":
                return new Usuario();
            default:
                throw new IllegalArgumentException("Tipo de usuario no válido.");
        }
    }
}