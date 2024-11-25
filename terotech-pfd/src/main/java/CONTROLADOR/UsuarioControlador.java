package CONTROLADOR;

public class UsuarioControlador {
    private UsuarioDAO usuarioDAO;

    public UsuarioControlador() {
        this.usuarioDAO = new UsuarioDAOIMPLEMENTACION();
    }

    public void registrarUsuario(String tipoUsuario, Usuario datosUsuario) {
        Usuario nuevoUsuario = UsuarioFactory.crearUsuario(tipoUsuario);

    }

    public Usuario obtenerUsuarioPorId(String numeroDocumento) {
        return usuarioDAO.obtenerPornumeroDocumento(numeroDocumento);
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioDAO.obtenerTodos();
    }

    public void modificarUsuario(Usuario usuarioModificado) {
        usuarioDAO.actualizar(usuarioModificado);
    }

    public void eliminarUsuario(String numeroDocumento) {
        usuarioDAO.eliminar(numeroDocumento);
    }
}
